package Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.GuardedObject;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;

import Controller.ControllerMenu.ListenerGuiMenubtnTovarVPonuke;
import Data.AppData;
import gui.GuiTovarWarehouseManagement;
import gui.ManagerGui;

public class ControllerWarehouseManagement extends PsqlMethods{
	
	GuiTovarWarehouseManagement myWarehouseManagement;
	AppData myAppData;
	
	public ControllerWarehouseManagement(GuiTovarWarehouseManagement theView,AppData theModel) {
		this.myWarehouseManagement = theView;
		this.myAppData = theModel;
		myWarehouseManagement.addListenerBtnExit(new ListenerBtnExit());
		myWarehouseManagement.addListenerBtnRefreshTable(new ListenerBtnRefreshTable());
		myWarehouseManagement.addListenerBtnSend(new ListenerBtnSend());
		myWarehouseManagement.addListenerMouseClickedGoodsAtWarehouses(new ListenerMouseClickedGoodsAtWarehouses());
		init();
	}
	
	void init(){
		loadWarehouses("SELECT nazov FROM sklady;");
		loadDataTblStatictic();
		loadDataTblGoodsAtWarehouses();
		myWarehouseManagement.myBtnSend.setEnabled(false);
	}
	
	// ************* | Action Listeners |************* \\
	
	public class ListenerBtnExit implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			myWarehouseManagement.setVisible(false);
		}
	}
	
	public class ListenerBtnRefreshTable implements ActionListener {

		public void actionPerformed(ActionEvent e) {
	//		System.out.println("Btn RefreshTable action Performed");
			loadDataTblGoodsAtWarehouses();
		}
	}
	
	public class ListenerBtnSend implements ActionListener {

		public void actionPerformed(ActionEvent e) {
//			System.out.println("Btn Send action Performed");
			try {
				sendGoods();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
	//			displayErrorMessage(String errorMessage);
			}
		}
	}
	
	class ListenerMouseClickedGoodsAtWarehouses extends MouseAdapter{
         public void mouseClicked(MouseEvent e) {
        	 myWarehouseManagement.myBtnSend.setEnabled(true);
        	 loadWarehouses("SELECT nazov FROM sklady where nazov != '"+myWarehouseManagement.myTblGoodsAtWarehousesData.getValueAt(myWarehouseManagement.getMyTblGoodsAtWarehouses().getSelectedRow(), 1)+"'"); 
         }
     }
	
	// ************* | APP LOGIC methods |************* \\
	
	public void loadWarehouses(String query){
		myWarehouseManagement.myCbDestinationWarehouse.removeAllItems();
		ArrayList<String> loadedCategories = ExecutePsqlSelectForCombobox( query,"nazov");
		for (int i=0;i<loadedCategories.size();i++){
//			System.out.println("Cat"+(i+1)+" = "+loadedCategories.get(i));
			myWarehouseManagement.myCbDestinationWarehouse.addItem(loadedCategories.get(i));
		}
		
	}
	
	public void loadDataTblStatictic(){
		
			myWarehouseManagement.myTblStaticticData.setRowCount(0);
			ArrayList<String> columns = new ArrayList<String>();
			columns.add("sklad");
			columns.add("nazov");
			columns.add("celkova_hodnota_tovaru");
			columns.add("celkova_hmotnost_tovaru_kg");
					
			 ArrayList<Object> dataFromTable = loadDataForTable(querySelectStaticticCommnad(),columns);
			 putDataToTable(columns,dataFromTable, myWarehouseManagement.myTblStaticticData );
		
	}
	
	public void loadDataTblGoodsAtWarehouses(){
		myWarehouseManagement.myTblGoodsAtWarehousesData.setRowCount(0);
		ArrayList<String> columns = new ArrayList<String>();
		columns.add("id");
		columns.add("nazov");
		columns.add("tovar");
		columns.add("pocet_kusov");
		columns.add("cena_za_kus");
				
		 ArrayList<Object> dataFromTable = loadDataForTable(queryGoodsAtWarehousesSelect(),columns);
		 putDataToTable(columns,dataFromTable, myWarehouseManagement.myTblGoodsAtWarehousesData );
	}
	
	public void sendGoods() throws ClassNotFoundException, SQLException{

		int actualRow = myWarehouseManagement.getMyTblGoodsAtWarehouses().getSelectedRow();
		int goodQuantity = (int) myWarehouseManagement.myTblGoodsAtWarehousesData.getValueAt(actualRow, 3);	
		int goodInWarehouseId = (int) myWarehouseManagement.myTblGoodsAtWarehousesData.getValueAt(actualRow, 0);
		String goodType = myWarehouseManagement.myTblGoodsAtWarehousesData.getValueAt(actualRow, 2).toString();
		ArrayList<String> goodIdTemp = ExecutePsqlSelectForCombobox("SELECT id FROM tovar WHERE nazov = '"+goodType+"'","id");
		int goodId = Integer.parseInt(goodIdTemp.get(0));
//		System.out.println("********Good ID: "+goodId);
		int loadedQuantity = Integer.parseInt(myWarehouseManagement.getMyTfQuantity().getText());
		
		
 //   	System.out.println("Loaded quantity: "+loadedQuantity+" actualQuantity"+goodQuantity);
		if (loadedQuantity<=goodQuantity){
//				System.out.println("Good");
			
			ArrayList<String> queries = new ArrayList<String>();
			ArrayList<String> destinationWarehouseId = ExecutePsqlSelectForCombobox("select id from sklady where nazov = '"+myWarehouseManagement.myCbDestinationWarehouse.getSelectedItem()+"'","id");
			ArrayList<String> sourceWarehouseId = ExecutePsqlSelectForCombobox("select id from sklady where nazov = '"+myWarehouseManagement.myTblGoodsAtWarehousesData.getValueAt(actualRow, 1)+"'","id");
			ArrayList<String> isInGoodsAtWarehouses = ExecutePsqlSelectForCombobox("select id from tovar_v_skladoch where sklad = "+destinationWarehouseId.get(0)+" and tovar = '"+ goodId+"'","id");
			
			if (isInGoodsAtWarehouses.size() == 0){
//				System.out.println("** Tovar NIE je v sklade");		
				queries.add(queryInsertToGoodsInWarehouses(destinationWarehouseId.get(0),goodId,loadedQuantity));
			}else 
				{		
				queries.add(queryUpdateADDToGoodsInWarehouses(loadedQuantity, destinationWarehouseId.get(0),goodId));
			}
			
			if (goodQuantity-loadedQuantity == 0){
//				System.out.println("idem mazat");
				queries.add("DELETE from tovar_v_skladoch where id = "+goodInWarehouseId);
				
			}
			else{
			queries.add(queryUpdateSUBToGoodsInWarehouses(loadedQuantity,goodInWarehouseId, goodId));
			}	
			
//			System.out.println("** Tovar je v sklade");
			queries.add(queryInsertToTransportArchiveTable(goodId,sourceWarehouseId.get(0),destinationWarehouseId.get(0),loadedQuantity));
			
			executeTransaction(queries,myWarehouseManagement);
			loadDataTblGoodsAtWarehouses();
			
	}
	else
		myWarehouseManagement.displayErrorMessage("Not enough pieces in the choosen warehouse");
		myWarehouseManagement.myBtnSend.setEnabled(false);
	}
	
	public String querySelectStaticticCommnad(){	
		return ("select "
				+ "tvs.sklad, "
				+ "s.nazov, "
				+ "round((sum(t.hodnota*tvs.pocet_kusov))::numeric,3) as celkova_hodnota_tovaru, "
				+ "round((sum(t.hmotnost*tvs.pocet_kusov/1000))::numeric,3) as celkova_hmotnost_tovaru_kg "
				+ "from tovar_v_skladoch tvs "
				+ "join sklady s on s.id = tvs.sklad "
				+ "join tovar t on t.id = tvs.tovar "
				+ "Group by tvs.sklad,s.nazov "
				+ "Order by celkova_hodnota_tovaru desc"
				);
	}
	
	public String queryGoodsAtWarehousesSelect(){	
		return ("select "
				+ "tvs.id, "
				+ "s.nazov, "
				+ "t.nazov as tovar, "
				+ "tvs.pocet_kusov, "
				+ "round(t.hodnota::numeric,2) as cena_za_kus "
				+ "from tovar_v_skladoch tvs "
				+ "join sklady s on s.id = tvs.sklad "
				+ "join tovar t on t.id = tvs.tovar "
				+ "Order by t.nazov,tvs.pocet_kusov desc"
				);
	}
	
	public String queryInsertToGoodsInWarehouses(String destinationWarehouseId,int goodId, int loadedQuantity){
		return ("INSERT INTO tovar_v_skladoch (sklad,tovar,pocet_kusov) VALUES ("
				+ destinationWarehouseId+","
				+ goodId
				+ ","
				+ loadedQuantity
				+ ")");
	}
	
	public String queryInsertToTransportArchiveTable(int goodId,String sourceWarehouseId,String destinationWarehouseId,int loadedQuantity){
		return ("INSERT INTO zaznamy_presunov_tovaru (tovar,zdrojovy_sklad,cielovy_sklad,pocet_kusov,date,time) "
				+ "VALUES ('"+goodId+"',"
				+ sourceWarehouseId+","
				+ destinationWarehouseId+","
				+ loadedQuantity+","
				+ "'"+getActualDate()+"',"
				+ "'"+getActualTime()+"')");
	}

	public String queryUpdateADDToGoodsInWarehouses(int loadedQuantity, String destinationWarehouseId,int goodId){
	return ("UPDATE tovar_v_skladoch "
			+ "SET pocet_kusov = (pocet_kusov + "+loadedQuantity+") "
			+ "where sklad = "
			+ destinationWarehouseId
			+" and tovar = +"+goodId);
	}
	
	public String queryUpdateSUBToGoodsInWarehouses(int loadedQuantity,int goodInWarehouseId, int goodId){

		return ("UPDATE tovar_v_skladoch "
				+ "SET pocet_kusov = (pocet_kusov - "+loadedQuantity+") "
				+ "where id = "+goodInWarehouseId
				+" and tovar = "+"'"+goodId+"'");
	}

	public String getActualDate(){
		return  new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime()); 
	}
	
	public String getActualTime(){
		return  new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()); 
	}

}
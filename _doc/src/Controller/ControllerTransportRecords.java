package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Controller.ControllerWarehouseManagement.ListenerBtnExit;
import Data.AppData;
import gui.GuiTransportRecords;
import gui.ManagerGui;

public class ControllerTransportRecords extends PsqlMethods{
	
	GuiTransportRecords myGuiArchivPresunov;
	AppData myAppData;

	public ControllerTransportRecords(GuiTransportRecords theView) {
		this.myGuiArchivPresunov = theView;
		myGuiArchivPresunov.addListenerBtnExit(new ListenerBtnExit());
		myGuiArchivPresunov.addListenerbtnApplyFilter(new ListenerBtnApplyFilter());
		myGuiArchivPresunov.addListenerbtnClearFilter(new ListenerBtnClearFilter());
		init();
	}
	
	public void init(){
		loadWarehouses();
		loadCategory();
		loadTable(selectForTranportOfGoodsTable());
	}
	
	// ************* | Action Listeners |************* \\
	
	public class ListenerBtnExit implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			myGuiArchivPresunov.setVisible(false);
		}
	}
	
	public class ListenerBtnApplyFilter implements ActionListener {

		public void actionPerformed(ActionEvent e) {
//			System.out.println("Load table using Filter");
			loadTable(selectForTranportOfGoodsTable()+addFilterForSelect());	
		}
	}
	
	public class ListenerBtnClearFilter implements ActionListener {

		public void actionPerformed(ActionEvent e) {
//			System.out.println("Btn ClearFilter action Performed");
			clearFilters();
		}
	}
	
	// ************* | APP LOGIC methods |************* \\
	
	public void loadWarehouses(){
		ArrayList<String> loadedCategories = ExecutePsqlSelectForCombobox( "SELECT nazov FROM sklady;","nazov");
		myGuiArchivPresunov.myCbSource.removeAllItems();
		myGuiArchivPresunov.myCbDestination.removeAllItems();
		myGuiArchivPresunov.myCbSource.addItem("");
		myGuiArchivPresunov.myCbDestination.addItem("");
		
		for (int i=0;i<loadedCategories.size();i++){
//			System.out.println("Cat"+(i+1)+" = "+loadedCategories.get(i));
			myGuiArchivPresunov.myCbDestination.addItem(loadedCategories.get(i));
			myGuiArchivPresunov.myCbSource.addItem(loadedCategories.get(i));
		}	
	}
	
	public void loadCategory(){
		myGuiArchivPresunov.myCbCategory.removeAllItems();
		ArrayList<String> loadedCategories = ExecutePsqlSelectForCombobox( "SELECT nazov FROM kategoria_tovaru;","nazov");
		myGuiArchivPresunov.myCbCategory.addItem("");
		
		for (int i=0;i<loadedCategories.size();i++){
	//		System.out.println("Cat"+(i+1)+" = "+loadedCategories.get(i));
			myGuiArchivPresunov.myCbCategory.addItem(loadedCategories.get(i));
		}
	}
	
	void clearFilters(){
		myGuiArchivPresunov.myCbCategory.setSelectedIndex(0);
		myGuiArchivPresunov.myCbDestination.setSelectedIndex(0);
		myGuiArchivPresunov.myCbSource.setSelectedIndex(0);
		myGuiArchivPresunov.setTfDateFrom("");
		myGuiArchivPresunov.setTfDateTo("");
		myGuiArchivPresunov.setTfValueFrom("");
		myGuiArchivPresunov.setTfValueTo("");
		
		loadTable(selectForTranportOfGoodsTable());
	}
	
	public void loadTable(String query){
		myGuiArchivPresunov.myTblData.setRowCount(0);
		ArrayList<String> columns = new ArrayList<String>();
		columns.add("zpt.id");
		columns.add("tovar");
		columns.add("kategoria");
		columns.add("zdrojovy_sklad");
		columns.add("cielovy_sklad");
		columns.add("pocet_kusov");
		columns.add("hodnota_tovaru");
		columns.add("date");
		columns.add("time");
		query = (query+" order by zpt.date desc, zpt.time desc ");
	//	"id","tovar","zdrojovy_sklad","cielovy_sklad","pocet_kusov","hodnota_tovaru","presun_uskutocneny"
		 ArrayList<Object> dataFromTable = loadDataForTable(query,columns);
		 putDataToTable(columns,dataFromTable, myGuiArchivPresunov.myTblData );	
	}
	
	public String selectForTranportOfGoodsTable(){
		String temp = ("select zpt.id as \"zpt.id\","
				+ "t.nazov as tovar,"
				+ "k.nazov as kategoria,"
				+ "sz.nazov as zdrojovy_sklad,"
				+ "sc.nazov as cielovy_sklad,"
				+ "zpt.pocet_kusov as pocet_kusov,"
				+ "round((t.hodnota*zpt.pocet_kusov)::numeric,2) as hodnota_tovaru,"
				+ "zpt.date as date, "
				+ "zpt.time as time "
				+ "from zaznamy_presunov_tovaru as zpt "
				+ "join sklady sz on sz.id = zpt.zdrojovy_sklad "
				+ "join sklady sc on sc.id = zpt.cielovy_sklad "
				+ "join tovar t on t.id = zpt.tovar "
				+ "join kategoria_tovaru k on k.nazov = t.kategoria "
				);
					
//		System.out.println(temp);
		return temp;
	}
	
	public String addFilterForSelect(){

		String filterString = "";	
		String category = myGuiArchivPresunov.myCbCategory.getSelectedItem().toString();
		String srcWh = myGuiArchivPresunov.myCbSource.getSelectedItem().toString();
		String dstWh = myGuiArchivPresunov.myCbDestination.getSelectedItem().toString();
		boolean vFromIsEpmty = myGuiArchivPresunov.getMyTfValueFrom().getText().isEmpty();
		boolean vToIsEmpty = myGuiArchivPresunov.getMyTfValueTo().getText().isEmpty();
		int filterCount = 0;
		int vFrom; 
		int vTo;
		try{
			
			
		
		if (!(category == "" && srcWh == "" && dstWh == ""  && vFromIsEpmty == true && vToIsEmpty == true)){ 
//			System.out.println("***FILTER will be used");
			filterString = " Where ";
			if (category != "")	{
				if (filterCount != 0)
					filterString = (filterString+" AND ");
			filterString = (filterString+" kategoria = '"+category+"'");
			filterCount++;
			}
			if (srcWh != "")	{
				if (filterCount != 0)
					filterString = (filterString+" AND ");
			filterString = (filterString+" sz.nazov = '"+srcWh+"'");
			filterCount++;
			}
			if (dstWh != "")	{
				if (filterCount != 0)
					filterString = (filterString+" AND ");
			filterString = (filterString+" sc.nazov = '"+dstWh+"'");
			filterCount++;
			}
			
			if ( vFromIsEpmty != true)	{
				if (filterCount != 0)
					filterString = (filterString+" AND ");
			vFrom = Integer.parseInt(myGuiArchivPresunov.getMyTfValueFrom().getText());
			filterString = (filterString+" round((t.hodnota*zpt.pocet_kusov)::numeric,2) >= "+vFrom);
			filterCount++;
			}
			
			if ( vToIsEmpty != true)	{
				if (filterCount != 0)
					filterString = (filterString+" AND ");
			vTo = Integer.parseInt(myGuiArchivPresunov.getMyTfValueTo().getText());
			filterString = (filterString+" round((t.hodnota*zpt.pocet_kusov)::numeric,2) <= "+vTo);
			filterCount++;
			}
						
		}
		else {
	//		System.out.println("FILTER IS NULL");
		}

	}
	catch(NumberFormatException ex){
		myGuiArchivPresunov.displayErrorMessage("Bad data type! use natural number for Value ");
	}
//	System.out.println("FILTER STRING: "+filterString);
	   return (""+filterString);
	}
	
}
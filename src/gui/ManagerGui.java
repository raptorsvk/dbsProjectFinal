package gui;




import java.util.ArrayList;

/**
 * Manager for GUIs
 */

public class ManagerGui {
	
	public GuiMenu myGuiMenu ;
	public GuiManagementOfProducts myGuiTovarVPonuke ;
	public GuiTransportRecords myGuiArchivPresunov;
	public GuiTovarWarehouseManagement myGuiTovarVSklade;
	
	public ManagerGui(){
		
		myGuiMenu = new GuiMenu();
		myGuiTovarVPonuke = new GuiManagementOfProducts();
		myGuiTovarVSklade = new GuiTovarWarehouseManagement();
		myGuiArchivPresunov = new GuiTransportRecords();
	}
}

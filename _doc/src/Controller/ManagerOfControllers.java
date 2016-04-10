package Controller;

import gui.ManagerGui;

import java.util.ArrayList;

import Data.AppData;

/**
 * Controller Manager create controllers
 */

public class ManagerOfControllers {
	
	public ManagerOfControllers( ManagerGui theView, AppData myAppData){
		new ControllerMenu(theView);
		new ControllerManagementOfProducts(theView.myGuiTovarVPonuke,myAppData);
		new ControllerWarehouseManagement(theView.myGuiTovarVSklade,myAppData);
	}
}

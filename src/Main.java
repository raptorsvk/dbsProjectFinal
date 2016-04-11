
/**
ZADANIE:	Projekt 1. ITERCIA
Predmet:	Databazove systemy
Autor:	 	Jakub Pullmann FIIT STU BRATISLAVA
Cviciaci: 	Ing. Róbert Móro.
*/
import gui.*;
import Controller.ManagerOfControllers;
import Data.AppData;

public class Main {

	public static void main(String[] args) {

		ManagerGui theView = new ManagerGui();		// creating of View
		AppData theModel = new AppData(); // creating Model

		new ManagerOfControllers(theView, theModel);	// Controller
		theView.myGuiMenu.setVisible(true);	// set Main MENU of Program

	}
}



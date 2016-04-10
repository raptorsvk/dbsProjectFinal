package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 Base for all GUI
*/
public class GuiBase extends JFrame {

	// error message dialog
	public void displayErrorMessage(String errorMessage){
		JOptionPane.showMessageDialog(this, errorMessage);
	}
}

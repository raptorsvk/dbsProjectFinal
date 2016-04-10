package Controller;

import gui.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerMenu {
	ManagerGui myManagerGui;

	public ControllerMenu(ManagerGui theView) {
		this.myManagerGui = theView;
		myManagerGui.myGuiMenu.addListenerBtnTovarVPonuke(new  ListenerGuiMenubtnTovarVPonuke());
		myManagerGui.myGuiMenu.addListenerBtnTovarNaSkladoch(new  ListenerGuiMenuBtnTovarNaSkladoch());
		myManagerGui.myGuiMenu.addListenerBtnPresunyTovaru(new  ListenerGuiMenuBtnPresunyTovaru());
	}

	// ************* | Action Listeners |************* \\
	
	public class ListenerGuiMenubtnTovarVPonuke implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			myManagerGui.myGuiTovarVPonuke.setVisible(true);
		}
	}
	
	public class ListenerGuiMenuBtnTovarNaSkladoch implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			myManagerGui.myGuiTovarVSklade.setVisible(true);
		}
	}
	
	public class ListenerGuiMenuBtnPresunyTovaru implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			myManagerGui.myGuiArchivPresunov.setVisible(true);
			new ControllerTransportRecords(myManagerGui.myGuiArchivPresunov);
		}
	}
	
}


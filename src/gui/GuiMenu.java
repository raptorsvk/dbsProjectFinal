package gui;

import java.awt.event.ActionListener;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionEvent;

public class GuiMenu extends JFrame {

	JLabel myLblProgName = new JLabel(" Logistic for Buffet Network");
	JLabel myLblAuthor = new JLabel("Author: Jakub Pullmann FIIT STU");
	JButton myBtnTovarVPonuke = new JButton("Management of Products");
	JButton myBtnTovarNaSkladoch = new JButton("Warehouse Management");
	JButton mybtnPresunyTovaru = new JButton("Transport Records");
	JPanel myMenuPanel = new JPanel();

	public GuiMenu() {

		setMenuDesign();
		getContentPane().add(myMenuPanel);

		mybtnPresunyTovaru.setBounds(60, 182, 220, 40);
		myMenuPanel.add(mybtnPresunyTovaru);

	}

	// ************* | Action Listeners |************* \\

	public void setMenuDesign() {
		int x = 0;
		int y = 10;

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(371, 339);
		myMenuPanel.setLayout(null);

		myLblProgName.setBounds(50, 21, 283, 20);
		myLblProgName.setFont(new Font("Tahoma", Font.BOLD, 16));
		myMenuPanel.add(myLblProgName);
		myLblAuthor.setBounds(10, 264, 203, 20);
		myMenuPanel.add(myLblAuthor);

		myBtnTovarVPonuke.setBounds(60, 80, 220, 40);
		myMenuPanel.add(myBtnTovarVPonuke);
		myBtnTovarNaSkladoch.setBounds(60, 131, 220, 40);
		myMenuPanel.add(myBtnTovarNaSkladoch);
	}

	public void addListenerBtnTovarVPonuke(ActionListener listenForButton) {
		myBtnTovarVPonuke.addActionListener(listenForButton);
	}

	public void addListenerBtnTovarNaSkladoch(ActionListener listenForButton) {
		myBtnTovarNaSkladoch.addActionListener(listenForButton);
	}

	public void addListenerBtnPresunyTovaru(ActionListener listenForButton) {
		mybtnPresunyTovaru.addActionListener(listenForButton);
	}

}

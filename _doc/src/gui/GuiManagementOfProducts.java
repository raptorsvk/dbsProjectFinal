package gui;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Data.DataModelGood;

import java.awt.Font;
import java.awt.event.ActionEvent;

public class GuiManagementOfProducts extends GuiBase {
	JPanel myMenuPanel = new JPanel();
	private final JButton myBtnCreate = new JButton("Create");
	private final JButton myBtnClearDetails = new JButton("Clear Details");
	private JButton myBtnEdit = new JButton("Edit");
	private JButton myBtnExit = new JButton("Exit");
	private JButton myBtnRefreshtable = new JButton("Refresh Table");
	private JTextField myTfName;
	private JTextField myTfValue;
	private JTextField myTfProducer;
	private JTextField myTfBarcode;
	private JTextField myTfWeight;
	private JTextField myTfOrigin;
	public JTable myTblListOfGoods;
	private JLabel myLblListOfGoods;
	private JLabel myLblDetails;
	private JLabel lblActions;
	private JButton button;
	public JComboBox myCbCategory = new JComboBox();
	public DefaultTableModel myDTMListOfGoods;

	public GuiManagementOfProducts() {

		setMenuDesign();
		getContentPane().add(myMenuPanel);

		myMenuPanel.setLayout(null);
		myBtnEdit.setBounds(10, 50, 190, 40);
		myMenuPanel.add(myBtnEdit);
		myBtnCreate.setBounds(10, 101, 190, 40);
		myMenuPanel.add(myBtnCreate);
		myBtnClearDetails.setBounds(10, 152, 190, 40);
		myMenuPanel.add(myBtnClearDetails);
		JLabel myLblName = new JLabel("Name");
		myLblName.setBounds(240, 50, 46, 14);
		myMenuPanel.add(myLblName);
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(240, 80, 63, 14);
		myMenuPanel.add(lblCategory);
		JLabel lblValue = new JLabel("Value (€)");
		lblValue.setBounds(240, 110, 72, 14);
		myMenuPanel.add(lblValue);

		JLabel myLblProducer = new JLabel("Producer\t");
		myLblProducer.setBounds(240, 140, 63, 14);
		myMenuPanel.add(myLblProducer);

		JLabel myLblBarcode = new JLabel("Barcode");
		myLblBarcode.setBounds(240, 170, 63, 14);
		myMenuPanel.add(myLblBarcode);

		JLabel myLblWeight = new JLabel("Weight (g)");
		myLblWeight.setBounds(240, 200, 72, 14);
		myMenuPanel.add(myLblWeight);

		JLabel myLblOrigin = new JLabel("Origin");
		myLblOrigin.setBounds(240, 230, 81, 14);
		myMenuPanel.add(myLblOrigin);

		myTfName = new JTextField();
		myTfName.setBounds(331, 50, 147, 20);
		myMenuPanel.add(myTfName);
		myTfName.setColumns(10);

		myTfValue = new JTextField();
		myTfValue.setColumns(10);
		myTfValue.setBounds(331, 110, 147, 20);
		myMenuPanel.add(myTfValue);

		myTfProducer = new JTextField();
		myTfProducer.setColumns(10);
		myTfProducer.setBounds(331, 140, 147, 20);
		myMenuPanel.add(myTfProducer);

		myTfBarcode = new JTextField();
		myTfBarcode.setColumns(10);
		myTfBarcode.setBounds(331, 170, 147, 20);
		myMenuPanel.add(myTfBarcode);

		myTfWeight = new JTextField();
		myTfWeight.setColumns(10);
		myTfWeight.setBounds(331, 200, 147, 20);
		myMenuPanel.add(myTfWeight);

		myTfOrigin = new JTextField();
		myTfOrigin.setColumns(10);
		myTfOrigin.setBounds(331, 230, 147, 20);
		myMenuPanel.add(myTfOrigin);

		String[] columnNames = { "Name", "Category", "Value (€)", };
		myDTMListOfGoods = new DefaultTableModel();
		for (int i = 0; i < columnNames.length; i++) {
			myDTMListOfGoods.addColumn(columnNames[i]);
		}
		
		myTblListOfGoods = new JTable(myDTMListOfGoods);
		myTblListOfGoods.setBounds(520, 50, 440, 317);
		JScrollPane scrollPane = new JScrollPane(myTblListOfGoods);
		myTblListOfGoods.setFillsViewportHeight(true);
		scrollPane.setBounds(520, 50, 440, 317);
		myMenuPanel.add(scrollPane);

		myLblListOfGoods = new JLabel("List of Goods");
		myLblListOfGoods.setFont(new Font("Tahoma", Font.PLAIN, 14));
		myLblListOfGoods.setBounds(519, 19, 88, 14);
		myMenuPanel.add(myLblListOfGoods);

		myLblDetails = new JLabel("Details");
		myLblDetails.setFont(new Font("Tahoma", Font.PLAIN, 14));
		myLblDetails.setBounds(240, 21, 88, 14);
		myMenuPanel.add(myLblDetails);

		lblActions = new JLabel("Actions");
		lblActions.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblActions.setBounds(10, 19, 88, 14);
		myMenuPanel.add(lblActions);

		myBtnExit.setBounds(872, 8, 88, 31);
		myMenuPanel.add(myBtnExit);

		myBtnRefreshtable.setBounds(10, 204, 190, 40);
		myMenuPanel.add(myBtnRefreshtable);

		myCbCategory.setBounds(331, 80, 147, 20);
		myMenuPanel.add(myCbCategory);
		myCbCategory.addItem("");

	}
	public void setMenuDesign() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 430);
		myMenuPanel.setLayout(null);
	}

	// ************* | Action Listeners |************* \\
	
	// ************* | Action Listeners |************* \\
	
	public void addListenerBtnExit(ActionListener listenForButton) {
		myBtnExit.addActionListener(listenForButton);
	}

	public void addListenerBtnCreate(ActionListener listenForButton) {
		myBtnCreate.addActionListener(listenForButton);
	}

	public void addListenerBtnEdit(ActionListener listenForButton) {
		myBtnEdit.addActionListener(listenForButton);
	}

	public void addListenerBtnCleardetails(ActionListener listenForButton) {
		myBtnClearDetails.addActionListener(listenForButton);
	}

	public void addListenerBtnRefreshtable(ActionListener listenForButton) {
		myBtnRefreshtable.addActionListener(listenForButton);
	}

	public void addListenerMouseClickedListOfGoods(MouseListener ms) {
		myTblListOfGoods.addMouseListener(ms);
	}

	// ************* | Getters & setters |************* \\

	// ************* | Getters && Setters |************* \\

	public String getTfName() {
		return myTfName.getText();
	}

	public void setTfName(String t) {
		this.myTfName.setText(t);
	}

	public float getTfValue() {
		return Float.parseFloat(myTfValue.getText());
	}

	public void setTfValue(String t) {
		this.myTfValue.setText(t);
	}

	public String getTfProducer() {
		return myTfProducer.getText();
	}

	public void setTfProducer(String t) {
		this.myTfProducer.setText(t);
	}

	public String getTfBarcode() {
		return myTfBarcode.getText();
	}

	public void setTfBarcode(String t) {
		this.myTfBarcode.setText(t);
	}

	public float getTfWeight() {
		return Float.parseFloat(myTfWeight.getText());
	}

	public void setTfWeight(String t) {
		this.myTfWeight.setText(t);
	}

	public String getTfOrigin() {
		return myTfOrigin.getText();
	}

	public void setTfOrigin(String t) {
		this.myTfOrigin.setText(t);
	}
}

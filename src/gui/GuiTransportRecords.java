package gui;

import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.event.ActionEvent;

public class GuiTransportRecords extends GuiBase {

	JPanel myMenuPanel = new JPanel();
	private JButton myBtnExit = new JButton("Exit");
	private JTextField myTfDateFrom;
	private JTextField myTfDateTo;
	private JTable myTblTranportOfGoods;
	private JLabel myLblListoOfGoods;
	private JLabel myLblFilter;
	private JLabel myLblValue;
	private JLabel myLblValueFrom;
	private JLabel myLBLValueTo;
	private JTextField myTfValueFrom;
	private JTextField myTfValueTo;
	private JButton myBtnApplyFilter;
	private JButton myBtnClearFilter;
	private JLabel myLblWarehouse;
	private JLabel myLblSource;
	private JLabel myLblDestination;
	public JComboBox myCbSource;
	public JComboBox myCbDestination;
	public JComboBox myCbCategory;
	public DefaultTableModel myTblData;

	public GuiTransportRecords() {

		setMenuDesign();
		getContentPane().add(myMenuPanel);
		myMenuPanel.setLayout(null);

		JLabel myLblLabel = new JLabel("Category");
		myLblLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		myLblLabel.setBounds(10, 48, 70, 14);
		myMenuPanel.add(myLblLabel);

		JLabel myLblDate = new JLabel("Date");
		myLblDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		myLblDate.setBounds(10, 249, 46, 14);

		JLabel myLblDateFrom = new JLabel("From");
		myLblDateFrom.setBounds(10, 274, 46, 14);

		JLabel myLblDateTo = new JLabel("to");
		myLblDateTo.setBounds(10, 304, 46, 14);

		myTfDateFrom = new JTextField();
		myTfDateFrom.setColumns(10);
		myTfDateFrom.setBounds(90, 274, 147, 20);

		myTfDateTo = new JTextField();
		myTfDateTo.setColumns(10);
		myTfDateTo.setBounds(90, 304, 147, 20);
		//------------------------------------------

		String[] columnNames = { "ID", "Goods", "Category", "Source", "Destination", "Count", "Value", "Date", "Time" };
		myTblData = new DefaultTableModel();
		for (int i = 0; i < columnNames.length; i++) {
			myTblData.addColumn(columnNames[i]);
		}
		myTblTranportOfGoods = new JTable(myTblData);
		myTblTranportOfGoods.setBounds(310, 48, 626, 317);

		/*
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		myTblTranportOfGoods.setDefaultRenderer(String.class, centerRenderer);
		*/
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.RIGHT );
		
//		myTblTranportOfGoods.getColumnModel().getColumn(0).setCellRenderer( centerRenderer);
		
	    myTblTranportOfGoods.getColumnModel().getColumn(8).setCellRenderer( centerRenderer);
	    myTblTranportOfGoods.getColumnModel().getColumn(5).setCellRenderer( centerRenderer);
	    myTblTranportOfGoods.getColumnModel().getColumn(6).setCellRenderer( centerRenderer); 
	    myTblTranportOfGoods.getColumnModel().getColumn(7).setCellRenderer( centerRenderer);
		
		myTblTranportOfGoods.setFillsViewportHeight(true); // delete ?
		myTblTranportOfGoods.getColumnModel().getColumn(0).setPreferredWidth(20);
		myTblTranportOfGoods.getColumnModel().getColumn(3).setPreferredWidth(20);
		myTblTranportOfGoods.getColumnModel().getColumn(4).setPreferredWidth(20);
		myTblTranportOfGoods.getColumnModel().getColumn(5).setPreferredWidth(20);
		myTblTranportOfGoods.getColumnModel().getColumn(6).setPreferredWidth(20);
		myTblTranportOfGoods.getColumnModel().getColumn(7).setPreferredWidth(20);
		myTblTranportOfGoods.getColumnModel().getColumn(8).setPreferredWidth(20);
		
		JScrollPane scrollPane = new JScrollPane(myTblTranportOfGoods);

		scrollPane.setBounds(310, 48, 900, 317);
		myMenuPanel.add(scrollPane);

		//------------------------------------------------------
		myLblListoOfGoods = new JLabel("Transport of goods");
		myLblListoOfGoods.setFont(new Font("Tahoma", Font.BOLD, 14));
		myLblListoOfGoods.setBounds(310, 19, 147, 18);
		myMenuPanel.add(myLblListoOfGoods);

		myLblFilter = new JLabel("Filter");
		myLblFilter.setFont(new Font("Tahoma", Font.BOLD, 15));
		myLblFilter.setBounds(10, 19, 88, 14);
		myMenuPanel.add(myLblFilter);

		myBtnExit.setBounds(1122, 6, 88, 31);
		myMenuPanel.add(myBtnExit);

		myLblValue = new JLabel("Value (\u20AC)");
		myLblValue.setFont(new Font("Tahoma", Font.BOLD, 13));
		myLblValue.setBounds(10, 163, 70, 14);
		myMenuPanel.add(myLblValue);

		myLblValueFrom = new JLabel("From");
		myLblValueFrom.setFont(new Font("Tahoma", Font.PLAIN, 11));
		myLblValueFrom.setBounds(10, 188, 46, 14);
		myMenuPanel.add(myLblValueFrom);

		myLBLValueTo = new JLabel("to");
		myLBLValueTo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		myLBLValueTo.setBounds(10, 218, 46, 14);
		myMenuPanel.add(myLBLValueTo);

		myTfValueFrom = new JTextField();
		myTfValueFrom.setColumns(10);
		myTfValueFrom.setBounds(90, 188, 147, 20);
		myMenuPanel.add(myTfValueFrom);

		myTfValueTo = new JTextField();
		myTfValueTo.setColumns(10);
		myTfValueTo.setBounds(90, 218, 147, 20);
		myMenuPanel.add(myTfValueTo);

		myBtnApplyFilter = new JButton("Apply filter");
		myBtnApplyFilter.setBounds(10, 335, 115, 30);
		myMenuPanel.add(myBtnApplyFilter);

		myBtnClearFilter = new JButton("Clear Filter");
		myBtnClearFilter.setBounds(135, 335, 115, 30);
		myMenuPanel.add(myBtnClearFilter);

		myLblWarehouse = new JLabel("Warehouse");
		myLblWarehouse.setFont(new Font("Tahoma", Font.BOLD, 13));
		myLblWarehouse.setBounds(10, 73, 100, 14);
		myMenuPanel.add(myLblWarehouse);

		myLblSource = new JLabel("Source");
		myLblSource.setBounds(10, 103, 46, 14);
		myMenuPanel.add(myLblSource);

		myLblDestination = new JLabel("Destination");
		myLblDestination.setBounds(10, 133, 70, 14);
		myMenuPanel.add(myLblDestination);

		myCbSource = new JComboBox();
		myCbSource.setBounds(90, 100, 147, 20);
		myMenuPanel.add(myCbSource);

		myCbDestination = new JComboBox();
		myCbDestination.setBounds(90, 130, 147, 20);
		myMenuPanel.add(myCbDestination);

		myCbCategory = new JComboBox();
		myCbCategory.setBounds(90, 45, 147, 20);
		myMenuPanel.add(myCbCategory);

	}

	public void setMenuDesign() {
		int x = 0;
		int y = 10;

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1250, 430);
		myMenuPanel.setLayout(null);
	}

	// ************* | Action Listeners |************* \\

	public void addListenerBtnExit(ActionListener listenForButton) {
		myBtnExit.addActionListener(listenForButton);
	}

	public void addListenerbtnApplyFilter(ActionListener listenForButton) {
		myBtnApplyFilter.addActionListener(listenForButton);
	}

	public void addListenerbtnClearFilter(ActionListener listenForButton) {
		myBtnClearFilter.addActionListener(listenForButton);
	}

	// ************* | Getters && Setters |************* \\

	public String getTfDateFrom() {
		return myTfDateFrom.getText();
	}

	public void setTfDateFrom(String t) {
		this.myTfDateFrom.setText(t);
	}

	public String getTfDateTo() {
		return myTfDateTo.getText();
	}

	public void setTfDateTo(String t) {
		this.myTfDateTo.setText(t);
	}

	public float getTfValueFrom() {
		return Float.parseFloat(myTfValueFrom.getText());
	}

	public void setTfValueFrom(String t) {
		this.myTfValueFrom.setText(t);

	}

	public float getTfValueTo() {
		return Float.parseFloat(myTfValueTo.getText());
	}

	public void setTfValueTo(String t) {
		this.myTfValueTo.setText(t);
	}

	public JTextField getMyTfValueFrom() {
		return myTfValueFrom;
	}

	public void setMyTfValueFrom(JTextField myTfValueFrom) {
		this.myTfValueFrom = myTfValueFrom;
	}

	public JTextField getMyTfValueTo() {
		return myTfValueTo;
	}

	public void setMyTfValueTo(JTextField myTfValueTo) {
		this.myTfValueTo = myTfValueTo;
	}
}

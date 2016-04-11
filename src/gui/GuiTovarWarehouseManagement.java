package gui;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.event.ActionEvent;

public class GuiTovarWarehouseManagement extends GuiBase {

	JPanel myMenuPanel = new JPanel();
	private final JButton myBtnDelete = new JButton("Delete");
	private final JButton myBtnCreate = new JButton("Create");
	private final JButton myBtnRefreshTable = new JButton("Refresh table");
	private JButton myBtnExit = new JButton("Exit");
	private JTable myTblGoodsAtWarehouses;
	private JLabel myLblListoOfGoods;
	private JLabel myLblActions;
	private JButton button;
	private JLabel myLblGoodsTransport;
	private JLabel MyLblDestinationWarehouse;
	public JComboBox myCbDestinationWarehouse;
	private JLabel myLblQuantity;
	private JTextField myTfQuantity;
	public JButton myBtnSend;
	private JTable myTblStatistics;
	public DefaultTableModel myTblStaticticData;
	public DefaultTableModel myTblGoodsAtWarehousesData;

	public GuiTovarWarehouseManagement() {

		setMenuDesign();
		getContentPane().add(myMenuPanel);
		myMenuPanel.setLayout(null);

		myBtnDelete.setBounds(10, 316, 170, 30);

		myBtnCreate.setBounds(10, 357, 170, 30);

		myBtnRefreshTable.setBounds(10, 275, 170, 30);
		myMenuPanel.add(myBtnRefreshTable);

		String[] columnNames = { "ID", " Warehouse name", "Goods", "Count", "Value/unit" };
		myTblGoodsAtWarehousesData = new DefaultTableModel();
		for (int i = 0; i < columnNames.length; i++) {
			myTblGoodsAtWarehousesData.addColumn(columnNames[i]);
		}
		myTblGoodsAtWarehouses = new JTable(myTblGoodsAtWarehousesData);

		myTblGoodsAtWarehouses.setFillsViewportHeight(true);
		myTblGoodsAtWarehouses.getColumnModel().getColumn(0).setPreferredWidth(15);

		JScrollPane myScrpGoodsAtWarehouses = new JScrollPane(myTblGoodsAtWarehouses);

		myScrpGoodsAtWarehouses.setBounds(250, 48, 550, 317);
		myMenuPanel.add(myScrpGoodsAtWarehouses);

		myLblListoOfGoods = new JLabel("Goods at Warehouses");
		myLblListoOfGoods.setFont(new Font("Tahoma", Font.PLAIN, 14));
		myLblListoOfGoods.setBounds(250, 19, 158, 14);
		myMenuPanel.add(myLblListoOfGoods);

		myLblActions = new JLabel("Actions");
		myLblActions.setFont(new Font("Tahoma", Font.PLAIN, 14));
		myLblActions.setBounds(10, 246, 88, 14);
		myMenuPanel.add(myLblActions);

		myBtnExit.setBounds(712, 6, 88, 31);
		myMenuPanel.add(myBtnExit);

		myLblGoodsTransport = new JLabel("Goods Transport");
		myLblGoodsTransport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		myLblGoodsTransport.setBounds(10, 19, 190, 14);
		myMenuPanel.add(myLblGoodsTransport);

		MyLblDestinationWarehouse = new JLabel("Destination Warehouse");
		MyLblDestinationWarehouse.setBounds(10, 55, 130, 14);
		myMenuPanel.add(MyLblDestinationWarehouse);

		myCbDestinationWarehouse = new JComboBox();
		myCbDestinationWarehouse.setBounds(10, 80, 147, 20);
		myMenuPanel.add(myCbDestinationWarehouse);

		myLblQuantity = new JLabel("Quantity");
		myLblQuantity.setBounds(10, 115, 88, 14);
		myMenuPanel.add(myLblQuantity);

		myTfQuantity = new JTextField();
		myTfQuantity.setColumns(10);
		myTfQuantity.setBounds(10, 140, 147, 20);
		myMenuPanel.add(myTfQuantity);

		myBtnSend = new JButton("Send");
		myBtnSend.setBounds(10, 178, 170, 30);
		myMenuPanel.add(myBtnSend);

		String[] statisticColumnNames = { "ID", " Warehouse name", "Total value", "Total weight" };
		myTblStaticticData = new DefaultTableModel();
		for (int i = 0; i < statisticColumnNames.length; i++) {
			myTblStaticticData.addColumn(statisticColumnNames[i]);
		}
		myTblStatistics = new JTable(myTblStaticticData);
		myTblStatistics.setBounds(250, 422, 550, 142);
		myTblStatistics.setFillsViewportHeight(true); // delete ?
		myTblStatistics.getColumnModel().getColumn(0).setPreferredWidth(15);

		JScrollPane scrollPane = new JScrollPane(myTblStatistics);

		scrollPane.setBounds(250, 422, 550, 142);
		myMenuPanel.add(scrollPane);

		JLabel myLblStatictics = new JLabel("Statistics");
		myLblStatictics.setFont(new Font("Tahoma", Font.PLAIN, 14));
		myLblStatictics.setBounds(250, 398, 88, 14);
		myMenuPanel.add(myLblStatictics);

	}

	public void setMenuDesign() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(828, 619);
		myMenuPanel.setLayout(null);
	}

	// ************* | Action Listeners |************* \\

	public void addListenerBtnExit(ActionListener listenForButton) {
		myBtnExit.addActionListener(listenForButton);
	}

	public void addListenerBtnCreate(ActionListener listenForButton) {
		myBtnCreate.addActionListener(listenForButton);
	}

	public void addListenerBtnDelete(ActionListener listenForButton) {
		myBtnDelete.addActionListener(listenForButton);
	}

	public void addListenerBtnRefreshTable(ActionListener listenForButton) {
		myBtnRefreshTable.addActionListener(listenForButton);
	}

	public void addListenerBtnSend(ActionListener listenForButton) {
		myBtnSend.addActionListener(listenForButton);
	}

	public void addListenerMouseClickedGoodsAtWarehouses(MouseListener ms) {
		myTblGoodsAtWarehouses.addMouseListener(ms);
	}

	// ************* | Getters && Setters |************* \\

	public int getTfQuantity() {
		return Integer.parseInt(myTfQuantity.getText());
	}

	public void setTfQuantity(String t) {
		this.myTfQuantity.setText(t);
	}

	public JTable getMyTblGoodsAtWarehouses() {
		return myTblGoodsAtWarehouses;
	}

	public void setMyTblGoodsAtWarehouses(JTable myTblGoodsAtWarehouses) {
		this.myTblGoodsAtWarehouses = myTblGoodsAtWarehouses;
	}

	public JTextField getMyTfQuantity() {
		return myTfQuantity;
	}

	public void setMyTfQuantity(JTextField myTfQuantity) {
		this.myTfQuantity = myTfQuantity;
	}

}

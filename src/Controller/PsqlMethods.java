package Controller;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Data.DataModelGood;
import gui.GuiBase;
import gui.GuiTovarWarehouseManagement;

public abstract class PsqlMethods {
	public static void main(String[] args) {
	};

	static final String JDBC_DRIVER = "org.postgresql.Driver";
	static final String DB_URL = "jdbc:postgresql://localhost:5432/tmp2";
	static final String USER = "temp";
	static final String PASS = "temp123";

	public void executePsqlCommand(String command) {

		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			String sql = (command);
			stmt.executeUpdate(sql);
		} catch (SQLException se) {

			se.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} 
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} 
		} 
	}

	public ArrayList<String> ExecutePsqlSelectForCombobox(String query, String column) {
		ArrayList<String> loadedData = new ArrayList<String>();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			c = DriverManager.getConnection(DB_URL, USER, PASS);
			c.setAutoCommit(false);
	//		System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String name = rs.getString(column);
				loadedData.add(name);
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
//		System.out.println("Operation done successfully");
		return loadedData;
	}

	public ArrayList<Object> loadDataForTable(String query, ArrayList<String> listOfColummns) {
		Connection c = null;
		Statement stmt = null;
		ArrayList<Object> tableData = new ArrayList<Object>();
		try {
			Class.forName(JDBC_DRIVER);
			c = DriverManager.getConnection(DB_URL, USER, PASS);
			c.setAutoCommit(false);
//			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				for (int i = 0; i < listOfColummns.size(); i++) {
					tableData.add(rs.getObject(listOfColummns.get(i)));
				}
			}

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
//		System.out.println("Operation done successfully");

		return tableData;

	}

	public void putDataToTable(ArrayList<String> columns, ArrayList<Object> dataFromTable, DefaultTableModel myTblData) {
		int nColumns = columns.size();
		for (int i = 0; i < (dataFromTable.size() / nColumns); i++) {
			Object[] ColumnData = new Object[nColumns];
			System.out.print(i + ". ");

			for (int j = 0; j < nColumns; j++) {
				ColumnData[j] = dataFromTable.get((i * nColumns) + j);
			}
			myTblData.addRow(ColumnData);
		}
	}

	public void executeTransaction(ArrayList<String> listOfQueries, GuiBase myGui) throws SQLException, ClassNotFoundException {
		//   
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			conn.setAutoCommit(false);
//			System.out.println("Opened database successfully");

			stmt = conn.createStatement();
			String sql;
			for (int i = 0; i < listOfQueries.size(); i++) {
				sql = listOfQueries.get(i);
				stmt.executeUpdate(sql);
			}

			conn.commit();
		} catch (SQLException e) {
			if (conn != null) {
				try {
					System.err.println(e.getMessage());
					myGui.displayErrorMessage(e.getMessage() + "Transaction is being rolled back");
					conn.rollback();
				} catch (SQLException excep) {

				}
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}

	}

}

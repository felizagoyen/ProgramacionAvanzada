package Server;

import java.sql.*;

public class ConectionDB {
	private static ConectionDB conection = new ConectionDB();
	private static Connection con;
	private ConectionDB() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String ip = "127.0.0.1";
			String puerto = "3306";
			String database = "preguntados";
			String user = "root";
			String password = "";
			String url = "jdbc:mysql://"+ip+ ':' +puerto+'/'+database;
			con = DriverManager.getConnection(url, user, password);
		}catch(SQLException | ClassNotFoundException e){
			e.printStackTrace();
		}
		
	}
	
	public static ConectionDB getConection() {
		return conection;
	}
	
	public Connection getCon() {
		return con;
	}

	public static void closeConnection() {
		try {
			if (con != null) {
				con.close();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

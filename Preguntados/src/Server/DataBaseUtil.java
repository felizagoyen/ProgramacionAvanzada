package Server;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseUtil {
	private ConectionDB conection = null;
	private Connection con = null;
	
	public DataBaseUtil() {
		conection = ConectionDB.getConection();
		con = conection.getCon();
	}
	
	public void DataBaseClose(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet queryDB(String query){
		Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	
	public User getUserDB(String user){
		ResultSet rs = queryDB("SELECT * FROM `cuentas` WHERE `user` = '"+ user +"'");
		if(rs!=null){
			try {
				while(rs.next())
					return new User(rs.getString("user"), rs.getString("pass"), rs.getInt("TIPO"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}

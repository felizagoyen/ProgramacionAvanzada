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
	
	
	public static void main(String[] args) {
		DataBaseUtil db = new DataBaseUtil();
		ArrayList<User> users = new ArrayList<User>();
		try {
			ResultSet rs = db.queryDB("SELECT * FROM `cuentas`");
			while(rs.next()){
				users.add(new User(rs.getString("user"), rs.getString("pass")));
			}
			for(User user: users){
				System.out.println(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.DataBaseClose();
	}
	
}

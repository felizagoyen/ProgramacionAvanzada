package Server;

import java.sql.*;
import java.util.ArrayList;
import Packages.*;

public class DataBaseUtil {
	private ConnectionDB conection = null;
	private Connection con = null;
	
	public DataBaseUtil() {
		conection = ConnectionDB.getConection();
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
					return new User(rs.getString("USER"), rs.getString("PASS"), rs.getInt("TIPO"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	public ArrayList<Question> getQuestionDB(String categoria){
		ResultSet rs = queryDB("SELECT * FROM `preguntas` WHERE `categoria` = '"+ categoria +"'");
		ArrayList<Question> questions = new ArrayList<Question>();
		if(rs!=null){
			try {
				while(rs.next()){
					ArrayList<String> answer = new ArrayList<String>();
					Integer correctOption=(int) Math.round(Math.random()*10);
					if(correctOption<25)
						correctOption=0;
					else if(correctOption<50)
						correctOption=1;
					else if(correctOption<75)
						correctOption=2;
					else
						correctOption=3;
					answer.add(rs.getString("respuesta1"));
					answer.add(rs.getString("respuesta2"));
					answer.add(rs.getString("respuesta3"));
					answer.set(correctOption, rs.getString("respuestaCorrecta"));
					questions.add(new Question(rs.getString("pregunta"), answer, correctOption, rs.getString("categoria")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return questions;
	}
	
	public ArrayList<String> getCategoryDB(){
		ResultSet rs = queryDB("SELECT DISTINCT `categoria` FROM `preguntas`");
		ArrayList<String> categorias = new ArrayList<String>();
		if(rs!=null){
			try {
				while(rs.next())
					categorias.add(rs.getString("categoria"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return categorias;
	}
	
	public static void main(String[] args) {
		DataBaseUtil db = new DataBaseUtil();
		ArrayList<String> categorias = db.getCategoryDB();
		for(String category: categorias){
			System.out.println(category);
		}
	}
	
}

package Server;

import java.sql.*;
import java.util.ArrayList;

import Packages.Question;

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
	
	public Question getQuestionByID(Integer id) {
		ResultSet rs = queryDB("SELECT * FROM `preguntas` WHERE `id` = '"+ id +"'");
		ArrayList<String> wrongAnswers = new ArrayList<String>();
		try {
			rs.next();
			wrongAnswers.add(rs.getString("respuesta1"));
			wrongAnswers.add(rs.getString("respuesta2"));
			wrongAnswers.add(rs.getString("respuesta3"));
			return new Question(id, rs.getString("pregunta"), rs.getString("categoria"), rs.getString("respuestaCorrecta"), wrongAnswers);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Question> getQuestionDB(String categoria){
		ResultSet rs = queryDB("SELECT * FROM `preguntas` WHERE `categoria` = '"+ categoria +"'");
		ArrayList<Question> questions = new ArrayList<Question>();
		if(rs!=null){
			try {
				while(rs.next()){
					ArrayList<String> answer = new ArrayList<String>();
					answer.add(rs.getString("respuesta1"));
					answer.add(rs.getString("respuesta2"));
					answer.add(rs.getString("respuesta3"));
					questions.add(new Question(null, rs.getString("pregunta"), rs.getString("categoria"),rs.getString("respuestaCorrecta"),answer));
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
	
	public void setQuestionDB(Question question){
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO `preguntados`.`preguntas` (`ID`, `pregunta`, `respuesta1`, `respuesta2`, `respuesta3`, `respuestaCorrecta`, `categoria`) VALUES (?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, getMaxQuestionId() + 1);
			ps.setString(2, question.getQuestion());
			ps.setString(3, question.getWrongAnswers().get(0));
			ps.setString(4, question.getWrongAnswers().get(1));
			ps.setString(5, question.getWrongAnswers().get(2));
			ps.setString(6, question.getCorrectAnswer());
			ps.setString(7, question.getCategory());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	public int getMaxQuestionId() {
		ResultSet rs = queryDB("SELECT MAX(`ID`) FROM `preguntas`");
		try {
			rs.next();
			return rs.getInt(1);	
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public static void main(String[] args) {
		DataBaseUtil db = new DataBaseUtil();
		ArrayList<String> wrongAnswers = new ArrayList<String>();
		wrongAnswers.add("incorreta1");
		wrongAnswers.add("incorreta2");
		wrongAnswers.add("incorreta3");
		Question question = new Question(null, "Cuanto?", "categoria", "correcta", wrongAnswers);
		db.setQuestionDB(question);
	}

	public ArrayList<Question> getQuestionByCategoryDB(String category) {
		ResultSet rs = queryDB("SELECT `id`, `pregunta` FROM `preguntas` WHERE `categoria`='" + category + "'");
		ArrayList<Question> questions = new ArrayList<Question>();
		try {
			if(rs != null)
				while(rs.next())
					questions.add(new Question(rs.getInt("id"), rs.getString("pregunta")));
		} catch (Exception e) {
			e.printStackTrace();
			questions = null;
		}
		return questions;
	}
	
}

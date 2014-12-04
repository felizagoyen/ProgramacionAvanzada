package Server;

import java.sql.*;
import java.util.ArrayList;

import Commons.Question;
import Commons.Score;
import Commons.UserLoginPackage;

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
	
	
	public UserLoginPackage getUserDB(String user){
		ResultSet rs = queryDB("SELECT * FROM `user` WHERE `user` = '"+ user +"'");
		if(rs!=null){
			try {
				while(rs.next())
					return new UserLoginPackage(rs.getString("USER"), rs.getString("PASSWORD"), rs.getInt("USERTYPE"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public Question getQuestionByID(Integer id) {
		ResultSet rs = queryDB("SELECT * FROM `question` WHERE `id` = '"+ id +"'");
		ArrayList<String> wrongAnswers = new ArrayList<String>();
		try {
			rs.next();
			wrongAnswers.add(rs.getString("answer1"));
			wrongAnswers.add(rs.getString("answer2"));
			wrongAnswers.add(rs.getString("answer3"));
			ResultSet rsCategory = queryDB("SELECT * FROM `category` WHERE `id` = '"+ rs.getInt("categoryId") +"'");
			rsCategory.next();
			String categoryName = rsCategory.getString("name");
			
			return new Question(id, rs.getString("question"), categoryName, rs.getString("correctAnswer"), wrongAnswers);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Question> getQuestionDB(String category) {
		ArrayList<Question> questions = new ArrayList<Question>();
		ResultSet rsCategory = queryDB("SELECT * FROM `category` WHERE `name` = '"+ category +"'");
		if(rsCategory != null) {
			try {
				Integer categoryId = rsCategory.getInt("id");
				rsCategory.next();
				ResultSet rs = queryDB("SELECT * FROM `question` WHERE `categoryId` = '"+ categoryId +"'");
				if(rs!=null){
					while(rs.next()){
						ArrayList<String> answer = new ArrayList<String>();
						answer.add(rs.getString("answer1"));
						answer.add(rs.getString("answer2"));
						answer.add(rs.getString("answer3"));
						questions.add(new Question(null, rs.getString("question"), category, rs.getString("correctAnswer"), answer));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return questions;
	}
	
	public ArrayList<String> getCategoryDB(){
		ResultSet rs = queryDB("SELECT `name` FROM `category`");
		ArrayList<String> categories = new ArrayList<String>();
		if(rs != null){
			try {
				while(rs.next())
					categories.add(rs.getString("name"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return categories;
	}
	
	public void setQuestionDB(Question question){
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO `question` (`id`, `question`, `answer1`, `answer2`, `answer3`, `correctAnswer`, `categoryId`) VALUES (?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, getMaxQuestionId() + 1);
			ps.setString(2, question.getQuestion());
			ps.setString(3, question.getWrongAnswers().get(0));
			ps.setString(4, question.getWrongAnswers().get(1));
			ps.setString(5, question.getWrongAnswers().get(2));
			ps.setString(6, question.getCorrectAnswer());
			ps.setInt(7, question.getCategoryId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	public int getMaxQuestionId() {
		ResultSet rs = queryDB("SELECT MAX(`id`) FROM `question`");
		try {
			rs.next();
			return rs.getInt(1);	
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public ArrayList<Question> getQuestionByCategoryDB(String category) {
		ResultSet rsCategory = queryDB("SELECT * FROM `category` WHERE `name` = '"+ category +"'");
		ArrayList<Question> questions = new ArrayList<Question>();
		if(rsCategory != null) {
			try {
				rsCategory.next();
				Integer categoryId = rsCategory.getInt("id");
				ResultSet rs = queryDB("SELECT `id`, `question` FROM `question` WHERE `categoryId`='" + categoryId + "'");
				if(rs != null)
					while(rs.next())
						questions.add(new Question(rs.getInt("id"), rs.getString("question")));
			} catch (Exception e) {
				e.printStackTrace();
				questions = null;
			}
		}
		return questions;
	}
	
	public ArrayList<Score> getTopTenUserDB(String user){
		ResultSet rs = queryDB("SELECT * FROM `score` ORDER BY `gamesWon`");
		ArrayList<Score> topTen = new ArrayList<Score>();
		if(rs != null) {
			try {
				int totalUser = 0;
				while(rs.next() && totalUser <= 10) {
					topTen.add(new Score(rs.getInt("gamesPlayed"), rs.getInt("gamesWon"), rs.getInt("gamesLost"), rs.getInt("correctAnswers"), rs.getInt("wrongAnswers")));
					totalUser++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return topTen;
	}

	public Score getScoreByUserDB(String user){
		ResultSet rs = queryDB("SELECT * FROM `score` WHERE `user` = '" + user + "'");
		if(rs != null) {
			try {
				int totalUser = 0;
				while(rs.next() && totalUser <= 10) {
					return new Score(rs.getInt("gamesPlayed"), rs.getInt("gamesWon"), rs.getInt("gamesLost"), rs.getInt("correctAnswers"), rs.getInt("wrongAnswers"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void updateHistoricalScoreDB(String user, Score score) {
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE `score` SET `gamesPlayed` = ?, `gamesWon` = ?, `gamesLost` = ?, `correctAnswers` = ?, `wrongAnswers` = ? WHERE `user` = ?");
			ps.setInt(1, score.getGamesPlayed());
			ps.setInt(2, score.getGamesWon());
			ps.setInt(3, score.getGamesLost());
			ps.setInt(4, score.getCorrectAnswers());
			ps.setInt(5, score.getWrongAnswers());
			ps.setString(6, user);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

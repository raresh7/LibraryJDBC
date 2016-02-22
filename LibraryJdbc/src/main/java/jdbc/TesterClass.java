package jdbc;
import java.sql.*;

public class TesterClass {

	public static void main(String[] args) {

		try{
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM book");
			while(rs.next())
				System.out.println(rs.getString(1));
			stmt.close();
			con.close();
		}catch(Exception e){
			System.err.println(e);
		}
		
	}

}

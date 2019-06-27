package jdbc_study.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map.Entry;
import java.util.Properties;

public class MySQLjdbcUtil {
//	public static void main(String[] args) throws SQLException {
//		Connection con = getConnection();
//		System.out.println(con);
//		System.out.println(con.getCatalog());
//	}
	public static Connection getConnection(){ //파일 불러오도록 해놓음~~~
		Connection con = null;
		try (InputStream is =ClassLoader.getSystemResourceAsStream("db.properties")){ //인풋스트림으로 읽어옴 db.pro
			Properties prop = new Properties();
			prop.load(is); //properties에 파일이 생김 
			
			for(Entry<Object, Object> e : prop.entrySet()) {
				System.out.printf("%s - %s%n", e.getKey(), e.getValue());
			}	
			
			//데이터베이스에 연결
			con = DriverManager.getConnection(prop.getProperty("url"), prop);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (SQLException e1) {
			System.err.println("해당 데이터베이스 연결정보가 잘못 되었음. 확인요망-");
		}
		return con;
	}
}

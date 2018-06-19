package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 
 * @author arturopavon and raquelnoblejas
 *
 */
public class Connector {
	 public Connection getConnection() throws Exception{
		 Connection connect = null;
		 try {
		  // This will load the MySQL driver, each DB has its own driver
		 Class.forName("com.mysql.jdbc.Driver");
		  //System.out.println("Hola, he creado la clase");
		  // Setup the connection with the DB
		 connect = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/510labs?autoReconnect=true&useSSL=false&user=db510&password=510");
		 }catch(Exception e){
			 System.out.println("Error connecting.");
		 }
		 return connect;
		 }

}

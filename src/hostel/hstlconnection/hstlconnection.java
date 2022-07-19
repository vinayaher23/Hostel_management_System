package hostel.hstlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class hstlconnection {
	
	public static Connection getConnection(){
		
		Connection con=null;
		System.out.println("In the connection class....");
		try{           
			 //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");	// for Ms access
			Class.forName("com.mysql.jdbc.Driver");	// for mysql
			//Class.forName("org.h2.Driver");		//[h2] hypersonic 2 database Driver which is embded java for projects
		}
		catch(ClassNotFoundException cnf){
			Logger.getLogger(hstlconnection.class.getName()).log(Level.SEVERE,null,cnf);
		}
		try{							
			//con=DriverManager.getConnection("jdbc:odbc:hstldb1/newhstldb","",""); // connection for Ms access
			
			con=DriverManager.getConnection("jdbc:mysql://localhost:33060/hostel_db","root","");// connection for Mysql 
					// [h2]hypersonic 2 database my db in embedded(browser and applic don't work simultaneously) mode
			//con=DriverManager.getConnection("jdbc:h2:~/hostel_db","root","");	
					// [h2]hypersonic 2 database my db in server(browser and applic will work simultaneously) mode
			//con=DriverManager.getConnection("jdbc:h2:tcp://localhost/~/hostel_db","root","");
		}								
		catch(SQLException sqe){
			Logger.getLogger(hstlconnection.class.getName()).log(Level.SEVERE,null,sqe);
		}
		return con;
	}

}

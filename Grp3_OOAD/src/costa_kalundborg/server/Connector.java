package costa_kalundborg.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector{

	/*
	 * Dette er den lokale Database på din egen computer
	 */
	private final String
	SERVER					= "localhost",  // database-serveren
	DATABASE				= "ooad",  //"jdbcdatabase", // navnet paa din database = dit studienummer
	USERNAME				= "root", // dit brugernavn = dit studienummer 
	PASSWORD				= ""; // dit password som du har valgt til din database
	private final int PORT = 3306;
	private Connection conn;
	private Statement stm;

	protected Connector() throws InstantiationException, IllegalAccessException,
	ClassNotFoundException, SQLException {
		conn	= connectToDatabase("jdbc:mysql://"+SERVER+":"+PORT+"/"+DATABASE, USERNAME, PASSWORD);
		stm		= conn.createStatement();
	}

	/**
	 * To connect to a MySQL-server
	 * 
	 * @param url must have the form
	 * "jdbc:mysql://<server>/<database>" for default port (3306)
	 * OR
	 * "jdbc:mysql://<server>:<port>/<database>" for specific port
	 * more formally "jdbc:subprotocol:subname"
	 * 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 */
	private Connection connectToDatabase(String url, String username, String password)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException{
		// call the driver class' no argument constructor
		Class.forName("com.mysql.jdbc.Driver").newInstance();

		// get Connection-object via DriverManager
		return (Connection) DriverManager.getConnection(url, username, password);
	}

	protected ResultSet doQuery(String id) throws SQLException	{
		return stm.executeQuery(id); 
	}

	protected int doUpdate(String cmd) throws SQLException {
		return stm.executeUpdate(cmd); 
	}
}
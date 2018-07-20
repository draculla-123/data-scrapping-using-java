
import java.sql.Connection;
import java.sql.DriverManager;

public class DBInfo
{
  public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
  /*public static final String DATABASE_NAME="student";
  public static final String USERNAME="root";
  public static final String PASSWORD="root";
  public static final String PORT="3306";
  public static final String IPADDRESS="localhost:";
  public static final String CONNECTING_DRIVERSTRING="jdbc:mysql:"; */
  
	static
	{
		try
		{
			//load the driver
			Class.forName(DRIVER_CLASS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static Connection getConn()
	{
		Connection con=null;
		try
		{
			 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/data_scrapper","root","root");	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	
}

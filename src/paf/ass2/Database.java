package paf.ass2;

import java.sql.*; 

import javax.sql.DataSource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.*;

class Database 
{ 

	private Connection myConn; 
	private Statement myStmt; 
	
	private static final String DEFAULT_FORMAT = "yyyy-MM-dd hh:mm:ss.S";

	private static final DateFormat _tsFormatter = new java.text.SimpleDateFormat(
	            DEFAULT_FORMAT);


	public Database(String dm, String url, String user, String pswd)
	{ 
		try 
		{ 
			Class.forName(dm); // loads a DriverManager class 
			myConn = DriverManager.getConnection(url, user, pswd); 
			System.out.println("connected to " + url);
			myStmt = myConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
		} //try 
		catch(SQLException sqlexp) 
		{ 
			System.out.println("connection failed: " + sqlexp); 
		} 
		catch (ClassNotFoundException cnfe)
		{ 
			System.out.println("Failed to load driver; " + cnfe); 
		} 

	} 
	
	public Database(DataSource ds)
	{ 
		try 
		{ 
			myConn = ds.getConnection("jeroen","weber");
			myStmt = myConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
		}
		catch(SQLException sqlexp) 
		{ 
			System.out.println("connection failed: " + sqlexp); 
		} 
		catch (Exception cnfe)
		{ 
			System.out.println("Failed to load driver; " + cnfe); 
		} 

	} 

	public String execute(String sql) 
	{ 
		String result = "";
		
		try 
		{ 
			if (isSelect(sql)) 
			{ 
				ResultSet rs = myStmt.executeQuery(sql); 
				System.out.println("Selectquery");
				result = formatResultSet(rs); 
			} 
			else if (isUpdate(sql) || isInsert(sql)) 
			{ 
				System.out.println("Updatequery");
				int rs = myStmt.executeUpdate(sql); 
				result = "# of rows affected = " + rs; 
			} 
			else 
			{
				System.out.println("unrecognized SQL: " + sql); 
			}
		} 
		catch (SQLException sqle) 
		{ 
			System.out.println("Dbase access failure; " + sqle); 
		}  
		catch(Exception oe) 
		{ 
			System.out.println("Request read failure; " + oe); 
		}  
		return result;
	} 
	
	public String executeMultiple(String query1, String query2)
	{
		StringBuffer ret = new StringBuffer();
		
		try
		{
			Statement myStmt2 = myConn.createStatement();
			ResultSet rs = myStmt.executeQuery(query1);
			ResultSet rs2 = myStmt2.executeQuery(query2);
			while (rs.next())
			{
				ret.append("q1val [");
				ret.append(rs.getString(1));
				ret.append("]\r\n");
				
				while (rs2.next())
				{
					ret.append("q2val [");
					ret.append(rs.getString(1));
					ret.append("]\r\n");
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("Execute failure: " + e.toString());
		}
		return ret.toString();
	}
	
	public void updateResultSet(String naam, String sql)
	{
		try
		{
		ResultSet rs = myStmt.executeQuery(sql); 
		rs.first();
		if (rs.getConcurrency() == ResultSet.CONCUR_READ_ONLY) {
	        System.out.println("ResultSet non-updatable.");
	      } else {
	        System.out.println("ResultSet updatable.");
	      }

		rs.absolute(1);
		rs.updateString("NAAM", naam);
		rs.updateRow();
		}
		catch (Exception e)
		{
			System.out.println("Exception occurred [" + e.getMessage() + "]");
		}
	}
	
	public void executePrep()
	{
		try
		{
			Calendar currenttime=Calendar.getInstance();
			java.util.Date currentdate=currenttime.getTime();
			Date startdate = new java.sql.Date(currentdate.getTime());

			PreparedStatement ps=myConn.prepareStatement("insert into student(id, naam, adres, postcode, plaats, geboren) values (?,?,?,?,?,?)");
			ps.setLong(1, getLastId()+1);
			ps.setString(2, "Jeroen");
			ps.setString(3, "Straat 70");
			ps.setString(4, "3535AA");
			ps.setString(5, "Utrecht");
			ps.setDate(6, startdate);
			ps.executeUpdate();
			ps.close();
		}
		catch (Exception e)
		{
		}
	}
 
    private long getLastId()
    {
    	long lastid = 0;
    	try
    	{
		String strQuery = "SELECT MAX(id) as lastID From student ";
		ResultSet rs = myStmt.executeQuery(strQuery);
		if (rs.next()) {
			lastid = rs.getLong("lastID");
		}
    	}
    	catch (Exception e) {}
		return lastid;
    }
 
    private long getLastAfbeeldingId()
    {
    	long lastid = 0;
    	try
    	{
		String strQuery = "SELECT MAX(id) as ID From afbeelding ";
		ResultSet rs = myStmt.executeQuery(strQuery);
		if (rs.next()) {
			lastid = rs.getLong("ID");
		}
    	}
    	catch (Exception e) {}
		return lastid;
    }
    
    public java.sql.Date parseTimestamp(String strTimestamp,
            String format)
    {
        java.sql.Date result = null;
        
        try
        {

        DateFormat formatter = new SimpleDateFormat(format);
        java.util.Date date = formatter.parse(strTimestamp);
        String strTime = _tsFormatter.format(date);
        result = java.sql.Date.valueOf(strTime);
        }
        catch (Exception e)
        {
        }
        return result;
    }
    
    public java.sql.Date parseTimestamp(String strTimestamp) 
    {
  	  java.sql.Date result = null;
  	  
  	  try
  	  {

  	  result = parseTimestamp(strTimestamp, DEFAULT_FORMAT);
  	  }
  	  catch (Exception e){}
  	  return result;
    }
    
	private boolean isSelect(String stmt) 
	{ 
		return (stmt.substring(0, 6).equalsIgnoreCase("SELECT")); 
	} 

	private boolean isUpdate(String stmt) 
	{ 
		return stmt.substring(0, 6).equalsIgnoreCase("UPDATE"); 
	} 

	private boolean isInsert(String stmt) 
	{ 
		return stmt.substring(0, 6).equals("INSERT"); 
	} 

	private String formatResultSet(ResultSet rs) throws SQLException 
	{ 
		ResultSetMetaData rsmd = rs.getMetaData(); 
		int cc = rsmd.getColumnCount(); // = # of columns 
		String result = " table: "; 

		// iterate through each row 
		while(rs.next()) 
		{ 
			result = result + " row: "; 
			// iterate through each column 
			for(int i = 1; i <= cc; i++) 
			{ 
				String colName = " name: " + rsmd.getColumnName(i); 
				String colType = " type: " + rsmd.getColumnType(i); 
				String colVal = " value: " + rs.getString(i); 
				result = result + colName + colType + colVal + "\r\n"; 
			} // for 
		} // while 

		return result; 
	} // formatResultSet 
	
	public void forName()
	{
		// "name" is the class name to load
		Class klasse = null;
		try 
		{
		  klasse = Class.forName("java.lang.String");
		  klasse.getMethods();
		  
		} 
		catch (ClassNotFoundException ex) 
		{
			//handel exceptie af.
		}
	}
	

} // Database




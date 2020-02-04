import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ATMDB {
	//DB Connection Variables
		static Connection connection = null;
		static String databaseName ="";
		static String url = "*********" + databaseName;
		static String username = "*******";
		static String password = "******";
		
		
		public ATMDB() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url, username, password);

			
		}
		
// ---------------------- Check PIN number matches with those stored in database --------------------------------------------------------//
		
		public void checkPIN(String myPIN) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
			
			String myQuery = "SELECT pin from atmaccount.cust_details WHERE pin= '"+ myPIN + "'";
			PreparedStatement ps = null;
		    ResultSet rSet = null;

		    
		    ps = connection.prepareStatement(myQuery);
		    rSet = ps.executeQuery();
		    
		    int count = 0;
		    
		    while(rSet.next()){
				System.out.println(rSet.getString(1));
				count++;
			}
		    
		    
		    if(count != 1){
		    	System.out.println("Your PIN number was not recognised");
		    } else {
		    	System.out.println("Correct PIN number entered");
		    	new AccountFrame(myPIN);
		    	
		    }
		    connection.close();
			
// ----------------------- Get account details from database -----------------------------------------------------/
		}
		public String[] userDetails(String myPIN) throws SQLException{
			String myQuery = "SELECT name, balance, account_number from atmaccount.cust_details WHERE pin= '"+ myPIN + "'";
			PreparedStatement ps = null;
		    ResultSet rSet = null;
		    
		    ps = connection.prepareStatement(myQuery);
		    rSet = ps.executeQuery();
		    
		    while(rSet.next()){
		    	String[] name = {rSet.getString("name"), rSet.getString("balance"), rSet.getString("account_number")};
		    	return name;
				
			}
		    connection.close();
			return null;
			
		}
		
		
// ----------------------- Update balance in database---------------------------------------------------------------/	
		
		public void updateBalance (String newDeposit, String myPIN) throws SQLException{
			PreparedStatement ps = connection.prepareStatement("UPDATE atmaccount.cust_details SET balance= '"+ newDeposit +"' WHERE pin= '"+ myPIN + "'");
			
			int status = ps.executeUpdate();
			
			if (status != 0){
				System.out.println("Database connection successful");
				System.out.println("Record inserted");
				
			}
			connection.close();
		}
		 	
}


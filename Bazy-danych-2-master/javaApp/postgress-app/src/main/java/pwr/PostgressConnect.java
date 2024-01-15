package pwr;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Hello world!
 *
 */
public class PostgressConnect 
{
    String url = "jdbc:postgresql://localhost:5432/postgres";
    String user = "admin";
    String password = "admin";
    private Connection connection;
    
    

    public PostgressConnect(){
        
        connect();
        
       
    }
    public void connect(){
      
        try {
            this.connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<List<String>> executeQuery(String sqlQuery) {
        List<List<String>> resultList = new ArrayList<>();
    
        // Creating a PreparedStatement to execute the query
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            // Executing the query and obtaining the result set
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Retrieving metadata about the result set
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                // Processing the result set
                while (resultSet.next()) {
                    List<String> row = new ArrayList<>();

                    // Collecting data for each column
                    for (int i = 1; i <= columnCount; i++) {
                        // Retrieve data based on column index
                        Object value = resultSet.getObject(i);

                        // Add the value to the row
                        row.add(String.valueOf(value));
                    }

                    // Add the row to the result list
                    resultList.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }

        return resultList;
    }
    
}

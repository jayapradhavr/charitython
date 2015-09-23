import org.json.JSONArray;
import org.json.JSONObject;

import java.beans.Statement;
import java.sql.*;


/**
 * Created by bootcamp on 9/18/15.
 */
public class DatabaseHandler {

    public static String user = "root";
    public static String password = "root123";
    public static String URL = "jdbc:mysql://127.0.0.1/charityProject";

    private java.sql.Statement statement;
    private Connection connection;
    public DatabaseHandler(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, user, password);
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getAvailableItems(){
        JSONObject allData = new JSONObject();
        JSONArray jarr = new JSONArray();
        try {
            ResultSet rs = statement.executeQuery("SELECT * from All_Items where flag = 1");

            while(rs.next())
            {
                JSONObject temp = new JSONObject();
                temp.put("Name", rs.getString(2));
                temp.put("Price", rs.getInt(3));
                temp.put("Unit", rs.getString(5));
                jarr.put(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        allData.put("Products", jarr);
        return allData;
    }

    public JSONObject getStatistics(){
        JSONObject stats = new JSONObject();
        try {
            ResultSet rs = statement.executeQuery("select Item, sum(Quantity) from Transaction group by (Item)");
            while(rs.next()){
                stats.put(rs.getString(1), rs.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stats;
    }


    public JSONObject getAllItems(){
        JSONObject allData = new JSONObject();
        JSONArray jarr = new JSONArray();
        try {
            ResultSet rs = statement.executeQuery("SELECT Name, flag from All_Items");

            while(rs.next())
            {
                JSONObject temp = new JSONObject();
                temp.put("Name", rs.getString(1));
                temp.put("flag", rs.getInt(2));
                jarr.put(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        allData.put("Products", jarr);
        return allData;
    }

    public void modifyAvailableItems(JSONObject items){
        //TODO:Clear table and add these items from json to that table
        try {
            java.sql.Statement stm = connection.createStatement();
            String query = "UPDATE All_Items set flag = 1 where Name in (";
            System.out.println("Got:"+items);
            JSONArray jarr = items.getJSONArray("products");
            System.out.println(jarr.toString(2));
            for (int i = 0; i < jarr.length(); i++) {
                JSONObject ob = jarr.getJSONObject(i);
                if(i == jarr.length() - 1)
                    query += "\"" + ob.getString("Name") + "\")";
                else
                    query += "\"" + ob.getString("Name") + "\",";
            }
            System.out.println(query);
            //clear the table first then add data
            //update All_Items set flag=0 where Name in ("Oil", "XYZ");
            stm.execute("UPDATE All_Items set flag = 0");
            stm.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createNewTransaction(String name, String Item, int quantity, String unit, int price, String email){
        System.out.println("In create new Transaction");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into Transaction(Name, Item, Quantity, Price, unit, Email) values(?,?,?,?,?,?)");
            //preparedStatement.setString(1, emp_ID);
            preparedStatement.setString(1, name);
            //preparedStatement.setString(3, contact);
            preparedStatement.setString(2, Item);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setInt(4, price);
            preparedStatement.setString(5, unit);
            preparedStatement.setString(6, email);

            System.out.println(preparedStatement.toString());
            preparedStatement.execute();

            //TODO:alter table to make quantity field a string

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
//        DatabaseHandler dbh = new DatabaseHandler();
//        //System.out.println(dbh.getAvailableItems());
//       dbh.createNewTransaction("aewr", "fsjfo eorew", "91u293u219", "oil", 3, "Liters",  200);
//        System.out.println(dbh.getStatistics());
    }
}

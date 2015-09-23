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
            ResultSet rs = statement.executeQuery("select Name, total_sell, Target from All_Items where flag = 1");
            while(rs.next()){
                int sell = rs.getInt(2);
                int target = rs.getInt(3);
                String s = Integer.toString(sell) + "/" + Integer.toString(target);
                stats.put(rs.getString(1), s);
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
            System.out.println("Got:" + items);
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

    public void handleCombo(String name, String Item, int quantity, String unit, int price, String email){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into Transaction(Name, Item, Quantity, Price, unit, Email) values(?,?,?,?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, Item);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setInt(4, price);
            preparedStatement.setString(5, unit);
            preparedStatement.setString(6, email);

            System.out.println(preparedStatement.toString());
            preparedStatement.execute();

            if(Item.equals("Combo1")){
                int combo1_amount_sugar = 10;
                int combo1_amount_dal = 1;
                int combo1_amount_rice = 4;
                //Rice + Sugar + Toor Dhal
                java.sql.Statement stm = connection.createStatement();
                ResultSet rs1 = stm.executeQuery("Select total_sell from All_Items where Name=\"Rice\"");
                rs1.next();
                int rice_curr = rs1.getInt(1);
                rice_curr += quantity * combo1_amount_rice;
                ResultSet rs2 = stm.executeQuery("Select total_sell from All_Items where Name=\"Sugar\"");
                rs2.next();
                int Sugar_curr = rs2.getInt(1);
                Sugar_curr += quantity * combo1_amount_sugar;
                ResultSet rs3 = stm.executeQuery("Select total_sell from All_Items where Name=\"Toor Dhal\"");
                rs3.next();
                int dhal_curr = rs3.getInt(1);
                dhal_curr += quantity * combo1_amount_dal;
                String quer = "Update All_Items set total_sell="+rice_curr+" where Name=\"Rice\"";
                stm.execute(quer);
                quer = "Update All_Items set total_sell="+Sugar_curr+" where Name=\"Sugar\"";
                stm.execute(quer);
                quer = "Update All_Items set total_sell="+dhal_curr+" where Name=\"Toor Dhal\"";
                stm.execute(quer);

            }
            else if(Item.equals("Combo2")){
                int combo2_amount_sugar = 2*10;
                int combo2_amount_dal = 2*1;
                int combo2_amount_rice = 2*4;
                java.sql.Statement stm = connection.createStatement();
                ResultSet rs1 = stm.executeQuery("Select total_sell from All_Items where Name=\"Rice\"");
                rs1.next();
                int rice_curr = rs1.getInt(1);
                rice_curr += quantity * combo2_amount_rice;
                ResultSet rs2 = stm.executeQuery("Select total_sell from All_Items where Name=\"Sugar\"");
                rs2.next();
                int Sugar_curr = rs2.getInt(1);
                Sugar_curr += quantity * combo2_amount_sugar;
                ResultSet rs3 = stm.executeQuery("Select total_sell from All_Items where Name=\"Toor Dhal\"");
                rs3.next();
                int dhal_curr = rs3.getInt(1);
                dhal_curr += quantity * combo2_amount_dal;
                String quer = "Update All_Items set total_sell="+rice_curr+" where Name=\"Rice\"";
                stm.execute(quer);
                quer = "Update All_Items set total_sell="+Sugar_curr+" where Name=\"Sugar\"";
                stm.execute(quer);
                quer = "Update All_Items set total_sell="+dhal_curr+" where Name=\"Toor Dhal\"";
                stm.execute(quer);
            }

            //TODO:alter table to makea quantity field a string

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

            PreparedStatement pstm = connection.prepareStatement("Select total_sell from All_Items where Name=?");
            pstm.setString(1, Item);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            int curr_val = rs.getInt(1);
            curr_val += quantity;
            java.sql.Statement stm = connection.createStatement();
            String query = "update All_Items set total_sell=" + curr_val + " where Name=\"" + Item + "\"";
            System.out.println(query);
            stm.execute(query);
            //TODO:alter table to makea quantity field a string

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

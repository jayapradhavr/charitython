import org.json.JSONObject;
import org.json.JSONString;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by bootcamp on 9/18/15.
 */
@WebServlet(name = "addDataServlet", urlPatterns = "/newtransaction")
public class addDataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("In doPost here new transaction");
        String json = request.getParameter("Items");
        JSONObject obj = new JSONObject(json);
        System.out.println(obj.toString(2));
        DatabaseHandler databaseHandler = new DatabaseHandler();

//        String empid = obj.getString("EmpId");
        String name = obj.getString("Name");
  //      String con = obj.getString("Contact");
        String prod = obj.getString("Product");
        int qu = Integer.parseInt(obj.getString("quantity"));
        String unit = obj.getString("Unit");
        int price = Integer.parseInt(obj.getString("Price"));
        String email = obj.getString("Email");

     //   System.out.println("empid-"+empid+" name-"+name+" con-"+con+" prod-"+prod+" quantity-"+qu+" unit-"+unit+" price-"+price+" email-"+email);
//        databaseHandler.createNewTransaction("empid", "lsdkfj", "lsjkf", "lsdjf", 1, "KG", 1000, "sldlfkj");
        databaseHandler.createNewTransaction(name, prod, qu, unit, price, email);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("In doGet");
    }
}

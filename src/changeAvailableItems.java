import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by bootcamp on 9/19/15.
 */
@WebServlet(name = "changeAvailableItems", urlPatterns = "/changeavailableitems")
public class changeAvailableItems extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = request.getParameter("Items");
        JSONObject obj = new JSONObject(json);
        System.out.println(obj.toString(2));
        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.modifyAvailableItems(obj);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

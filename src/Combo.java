import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bootcamp on 9/23/15.
 */
@WebServlet(name = "Combo", urlPatterns = "/combo/*")
public class Combo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        url = url.substring(url.length() - 1);
        System.out.println(url);
        String json = request.getParameter("Items");
        JSONObject obj = new JSONObject(json);
//        System.out.println(obj.toString(2));


        String name = obj.getString("Name");
        String prod = obj.getString("Product");
        int qu = Integer.parseInt(obj.getString("quantity"));
        String unit = obj.getString("Unit");
        int price = Integer.parseInt(obj.getString("Price"));
        String email = obj.getString("Email");

        int combo_choice = Integer.parseInt(url);

        DatabaseHandler dbh = new DatabaseHandler();
        if(combo_choice == 1)
            dbh.handleCombo(name, "Combo1", qu, "N/A", price, email);
        else if(combo_choice == 2)
            dbh.handleCombo(name, "Combo2", qu, "N/A", price, email);

        System.out.println("Done with database management");
        MailUtil sendMailUtil = new MailUtil();
        sendMailUtil.sendMail(email, name, prod, unit, qu, price);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

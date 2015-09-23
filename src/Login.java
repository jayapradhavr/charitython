import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bootcamp on 9/21/15.
 */
@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("inputName");
        String password = request.getParameter("inputPassword");
        if(username.equals("Admin") && password.equals("admin")){
            response.sendRedirect("admin.html");
        }
        else if(username.equals("Charity") && password.equals("charity")){
            response.sendRedirect("charity.html");
        }
        else
            response.sendRedirect("index.html");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

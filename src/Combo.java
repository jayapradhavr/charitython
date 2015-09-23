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
        url = url.substring(7);
        int combo_choice = Integer.parseInt(url);
        if(combo_choice == 1)
        {
            
        }
        else if(combo_choice == 2)
        {

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

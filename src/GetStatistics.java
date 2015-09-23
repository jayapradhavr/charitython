import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by bootcamp on 9/21/15.
 */
@WebServlet(name = "GetStatistics", urlPatterns = "/getstats")
public class GetStatistics extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DatabaseHandler dbh = new DatabaseHandler();
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(dbh.getStatistics());
        out.flush();
    }
}

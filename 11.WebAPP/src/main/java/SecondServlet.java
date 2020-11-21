import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "SecondServlet")
public class SecondServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // http://localhost:8888/webapp/hello-world?name=Tom&surname=Smith
    String name = req.getParameter("name");
    String surname = req.getParameter("surname");
    PrintWriter pw = resp.getWriter();
    pw.println("<html>");
    pw.println("<h1> Hello, " + name + " " + surname + "! </h1>");
    pw.println("</html>");

    boolean isRedirect = false;
    if (isRedirect) {
      resp.sendRedirect("https://www.sql.ru");
//    resp.sendRedirect("/webapp/redirect-jsp");
    } else {
      RequestDispatcher dispatcher = req.getRequestDispatcher("/redirect-jsp");
      dispatcher.forward(req, resp);
    }

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

  }
}

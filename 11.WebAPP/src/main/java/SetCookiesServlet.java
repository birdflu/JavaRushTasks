import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetCookiesServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Cookie cookie1 = new Cookie("some_id", "123");
    Cookie cookie2 = new Cookie("some_name", "Tom");

    cookie1.setMaxAge(24*60*60);
    cookie2.setMaxAge(24*60*60);

    response.addCookie(cookie1);
    response.addCookie(cookie2);
  }
}

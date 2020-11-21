import logic.Cart;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "SecondServlet")
public class SecondServlet extends HttpServlet {
  protected enum RedirectType {
    OUTER_REDIRECT, INNER_REDIRECT, FORWARD
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    boolean doTestParameter = false;
    boolean doTestRedirect = false;
    boolean doTestSessionCount = false;
    boolean doTestSessionCart = false;
    boolean doTestSessionAuthority= false;

    if (doTestParameter) { testParameters(req, resp); }
    if (doTestRedirect) { testRedirect(req, resp, RedirectType.OUTER_REDIRECT); }
    if (doTestSessionCount) { testSessionCount(req, resp); }
    if (doTestSessionCart) { testSessionAuthority(req, resp); }
    if (doTestSessionAuthority) { testSessionAuthority(req, resp); }

  }

  private void testSessionAuthority(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    String user = (String) session.getAttribute("current_user");

    if (user == null) {
      // response для анонимного пользователя
      // авторизация
      // регистрация
      int ID = 123123;
      session.setAttribute("current_user", ID);
    } else {
      // response для авторизованного пользователя
    }
  }

  private void testSessionCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // localhost:8888/webapp/hello-world?name=Car&quantity=3
    HttpSession session = req.getSession();
    Cart cart = (Cart) session.getAttribute("cart");

    String name = req.getParameter("name");
    int quantity = Integer.parseInt(req.getParameter("quantity"));

    if (cart == null) {
      cart = new Cart();
      cart.setName(name);
      cart.setQuantity(quantity);
    }

    session.setAttribute("cart", cart);

    getServletContext().getRequestDispatcher("/showCart.jsp").forward(req, resp);
  }

  private void testSessionCount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    // localhost:8888/webapp/hello-world
    HttpSession session = req.getSession();
    Integer count = (Integer) session.getAttribute("count");

    if (count == null) {
      count = 1;
      session.setAttribute("count", count);
    } else {
      session.setAttribute("count", ++count);
    }

    PrintWriter pw = resp.getWriter();

    pw.println("<html>");
    pw.println("<h1> You count is: " + count + " </h1>");
    pw.println("</html>");
  }

  private void testRedirect(HttpServletRequest req,
                            HttpServletResponse resp,
                            RedirectType redirectType) throws IOException, ServletException {
    if (redirectType == RedirectType.OUTER_REDIRECT) {
      resp.sendRedirect("https://www.sql.ru");
    }
    if (redirectType == RedirectType.INNER_REDIRECT) {
      resp.sendRedirect("/webapp/redirect-jsp");
    }
    if (redirectType == RedirectType.FORWARD) {
      RequestDispatcher dispatcher = req.getRequestDispatcher("/redirect-jsp");
      dispatcher.forward(req, resp);
    }
  }

  private void testParameters(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    // http://localhost:8888/webapp/hello-world?name=Tom&surname=Smith
    String name = req.getParameter("name");
    String surname = req.getParameter("surname");
    PrintWriter pw = resp.getWriter();
    pw.println("<html>");
    pw.println("<h1> Hello, " + name + " " + surname + "! </h1>");
    pw.println("</html>");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

  }
}

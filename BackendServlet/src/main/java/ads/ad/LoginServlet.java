package ads.ad;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;

import ads.ConnectionPoolImpl;
import java.sql.Connection;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ad-login")
public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();

		out.append("<!DOCTYPE html>");
		out.append("<html lang=\"en\">");
		out.append("");
		out.append("<head>");
		out.append("  <meta charset=\"utf-8\">");
		out.append("  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">");
		out.append("");
		out.append("  <title>TravelVN - Đăng nhập</title>");
		out.append("  <meta content=\"\" name=\"description\">");
		out.append("  <meta content=\"\" name=\"keywords\">");
		out.append("");
		out.append("  <!-- Favicons -->");
		out.append("  <link href=\"assets/img/Logo.svg\" rel=\"icon\">");
		out.append("  <link href=\"assets/img/apple-touch-icon.png\" rel=\"apple-touch-icon\">");
		out.append("");
		out.append("  <!-- Google Fonts -->");
		out.append("  <link href=\"https://fonts.gstatic.com\" rel=\"preconnect\">");
		out.append(
				"  <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i\" rel=\"stylesheet\">");
		out.append("");
		out.append("  <!-- Vendor CSS Files -->");
		out.append("  <link href=\"assets/vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"assets/vendor/bootstrap-icons/bootstrap-icons.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"assets/vendor/boxicons/css/boxicons.min.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"assets/vendor/quill/quill.snow.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"assets/vendor/quill/quill.bubble.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"assets/vendor/remixicon/remixicon.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"assets/vendor/simple-datatables/style.css\" rel=\"stylesheet\">");
		out.append("");
		out.append("  <!-- Template Main CSS File -->");
		out.append("  <link href=\"assets/css/style.css\" rel=\"stylesheet\">");
		out.append("");
		out.append("</head>");
		out.append("");
		out.append("<body>");
		out.append("");
		out.append("  <main>");
		out.append("    <div class=\"container\">");
		out.append("");
		out.append(
				"      <section class=\"section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4\">");
		out.append("        <div class=\"container\">");
		out.append("          <div class=\"row justify-content-center\">");
		out.append(
				"            <div class=\"col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center\">");
		out.append("");
		out.append("              <div class=\"d-flex justify-content-center py-4\">");
		out.append("                <a href=\"index.html\" class=\"logo d-flex align-items-center w-auto\">");
//		out.append("                  <img src=\"assets/img/Logo.svg\" alt=\"\">");
		out.append("                  <span class=\"d-none d-lg-block\">TravelVN</span>");
		out.append("                </a>");
		out.append("              </div><!-- End Logo -->");
		out.append("");
		out.append("              <div class=\"card mb-3\">");
		out.append("");
		out.append("                <div class=\"card-body\">");
		out.append("");
		out.append("                  <div class=\"p-0\">");
		out.append("                    <h5 class=\"card-title text-center pb-2 fs-4\">Đăng nhập vào tài khoản</h5>");
		out.append("                  </div>");
		out.append("");
		
		// Hiển thị lỗi khi usename hoặc pasword
		String error = req.getParameter("error");
		if ("1".equals(error)) {
		    out.append("<div class=\"text-danger text-center small mb-2\" role=\"alert\">Tên đăng nhập hoặc mật khẩu không đúng!</div>");
		}
		
		out.append("                  <form class=\"row g-3 needs-validation\" method=\"post\" action=\"ad-login\" novalidate>");
		out.append("");
		out.append("                    <div class=\"col-12\">");
		out.append("                      <label for=\"yourUsername\" class=\"form-label\">Tên đăng nhập</label>");
		out.append("                      <div class=\"input-group has-validation\">");
		out.append("                        <span class=\"input-group-text\" id=\"inputGroupPrepend\">@</span>");
		out.append(
				"                        <input type=\"text\" name=\"username\" class=\"form-control\" id=\"yourUsername\" required>");
		out.append("                        <div class=\"invalid-feedback\">Vui lòng nhập tên đăng nhập!</div>");
		out.append("                      </div>");
		out.append("                    </div>");
		out.append("");
		out.append("                    <div class=\"col-12\">");
		out.append("                      <label for=\"yourPassword\" class=\"form-label\">Mật khẩu</label>");
		out.append("                      <input type=\"password\" name=\"password\" class=\"form-control\" id=\"yourPassword\" required>");
		out.append("                      <div class=\"invalid-feedback\">Vui lòng nhập mật khẩu!</div>");
		out.append("                    </div>");
		out.append("");
//		out.append("                    <div class=\"col-12\">");
//		out.append("                      <div class=\"form-check\">");
//		out.append("                        <input class=\"form-check-input\" type=\"checkbox\" name=\"remember\" value=\"true\" id=\"rememberMe\">");
//		out.append("                        <label class=\"form-check-label small\" for=\"rememberMe\">Ghi nhớ tôi</label>");
//		out.append("                      </div>");
//		out.append("                    </div>");
		out.append("                    <div class=\"col-12 mt-4\">");
		out.append("                      <button class=\"btn btn-primary w-100\" type=\"submit\">Đăng nhập</button>");
		out.append("                    </div>");
//		out.append("                    <div class=\"col-12\">");
//		out.append("                      <p class=\"small mb-0\">Bạn chưa có tài khoản? <a href=\"pages-register.html\">Đăng ký tài khoản</a></p>");
//		out.append("                    </div>");
		out.append("                  </form>");
		out.append("");
		out.append("                </div>");
		out.append("              </div>");
		out.append("");
		out.append("            </div>");
		out.append("          </div>");
		out.append("        </div>");
		out.append("");
		out.append("      </section>");
		out.append("");
		out.append("    </div>");
		out.append("  </main><!-- End #main -->");
		out.append("");
		out.append(
				"  <a href=\"#\" class=\"back-to-top d-flex align-items-center justify-content-center\"><i class=\"bi bi-arrow-up-short\"></i></a>");
		out.append("");
		out.append("  <!-- Vendor JS Files -->");
		out.append("  <script src=\"assets/vendor/apexcharts/apexcharts.min.js\"></script>");
		out.append("  <script src=\"assets/vendor/bootstrap/js/bootstrap.bundle.min.js\"></script>");
		out.append("  <script src=\"assets/vendor/chart.js/chart.umd.js\"></script>");
		out.append("  <script src=\"assets/vendor/echarts/echarts.min.js\"></script>");
		out.append("  <script src=\"assets/vendor/quill/quill.js\"></script>");
		out.append("  <script src=\"assets/vendor/simple-datatables/simple-datatables.js\"></script>");
		out.append("  <script src=\"assets/vendor/tinymce/tinymce.min.js\"></script>");
		out.append("  <script src=\"assets/vendor/php-email-form/validate.js\"></script>");
		out.append("");
		out.append("  <!-- Template Main JS File -->");
		out.append("  <script src=\"assets/js/main.js\"></script>");
		out.append("");
		out.append("</body>");
		out.append("");
		out.append("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String username = req.getParameter("username");
	    String password = req.getParameter("password");

	    ConnectionPoolImpl pool = (ConnectionPoolImpl) ConnectionPoolImpl.getInstance();
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        conn = pool.getConnection("LoginServlet"); // trả về java.sql.Connection
	        stmt = conn.prepareStatement("SELECT * FROM user_account WHERE username = ? AND password = ?");
	        stmt.setString(1, username);
	        stmt.setString(2, password);
	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            HttpSession session = req.getSession();
	            session.setAttribute("username", username);
	            resp.sendRedirect("ad/ad-dashboard");
	        } else {
	            resp.sendRedirect("ad/ad-login?error=1");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        resp.sendRedirect("ad/ad-login?error=1");
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            if (conn != null) pool.releaseConnection(conn, "LoginServlet");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}


}

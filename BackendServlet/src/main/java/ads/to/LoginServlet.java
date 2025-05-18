package ads.to;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.Session;

import ads.ConnectionPoolImpl;
import ads.objects.BookingObject;
import ads.objects.UserAccount;
import ads.user.Booking;
import ads.user.BookingImpl;
import ads.user.UserAccountFunction;
import ads.user.UserAccountFunctionImpl;
import ads.util.Validate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

//	private Connection con;
//	private ConnectionPoolImpl cp;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();

		String err = request.getParameter("err");

		out.append("<!DOCTYPE html>");
		out.append("<html lang=\"en\">");
		out.append("");
		out.append("<head>");
		out.append("  <meta charset=\"utf-8\">");
		out.append("  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">");
		out.append("");
		out.append("  <title>Đăng nhập</title>");
		out.append("  <meta content=\"\" name=\"description\">");
		out.append("  <meta content=\"\" name=\"keywords\">");
		out.append("");

		out.append("");
		out.append("  <!-- Favicons -->");
		out.append("  <link href=\"resources/Logo.svg\" rel=\"icon\">");
		out.append("  <link href=\"assets/img/apple-touch-icon.png\" rel=\"apple-touch-icon\">");
		out.append("");
		out.append("  <!-- Google Fonts -->");
		out.append("  <link href=\"https://fonts.gstatic.com\" rel=\"preconnect\">");
		out.append(
				"  <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i\" rel=\"stylesheet\">");
		out.append("  <!-- Vendor CSS Files -->");
		out.append("  <link href=\"assets/vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"assets/vendor/bootstrap-icons/bootstrap-icons.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"assets/vendor/boxicons/css/boxicons.min.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"assets/vendor/quill/quill.snow.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"assets/vendor/quill/quill.bubble.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"assets/vendor/remixicon/remixicon.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"assets/vendor/simple-datatables/style.css\" rel=\"stylesheet\">");
		out.append("  <script src=\"assets/vendor/jquery/jquery-3.7.1.min.js\"></script>");
		out.append("  <script src=\"js/user.js\"></script>");
		out.append("");
		out.append("  <!-- Template Main CSS File -->");
		out.append("  <link href=\"assets/css/style.css\" rel=\"stylesheet\">");
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
		out.append("              <div class=\"d-flex justify-content-center py-4 pe-2 \">");
		out.append("                <a href=\"index.html\" class=\"logo d-flex align-items-center w-auto\">");
		out.append("                  <img src=\"resources/Logo.svg\" alt=\"\" style=\"height: 100px; width: 40px;\">");
		out.append("                  <span class=\"d-block\">TravelVN</span>");
		out.append("                </a>");
		out.append("              </div><!-- End Logo -->");
		out.append("");
		out.append("              <div class=\"card mb-3\">");
		out.append("");
		out.append("                <div class=\"card-body\">");
		out.append("");
		out.append("                  <div class=\"pt-4 pb-2\">");
		out.append("                    <h5 class=\"card-title text-center pb-0 fs-4\">Đăng nhập</h5>");
		out.append("");
		out.append("                  </div>");
		out.append("");
		System.out.println(err);
		if (err == null) {
			out.append(
					"                  <form name=\"frmLogin\" class=\"row g-3 needs-validation\" novalidate method=\"post\" action=\"Login\">");
		} else {
			if (err.equalsIgnoreCase("notok")) {
				System.out.println("NOW");
				out.append(
						"                  <form name=\"frmLogin\" class=\"row g-3 was-validated\" method=\"post\" action=\"Login\">");
			} else {
				out.append(
						"                  <form name=\"frmLogin\" class=\"row g-3 needs-validation\" novalidate method=\"post\" action=\"Login\">");
			}
		}
		out.append("");
		out.append("                    <div class=\"col-12\">");
		out.append("                      <label for=\"yourUsername\" class=\"form-label\">Tên tài khoản</label>");
		out.append("                      <div class=\"input-group has-validation\">");
		out.append("                        <span class=\"input-group-text\" id=\"inputGroupPrepend\">@</span>");

		if (err != null) {
			if (err.equalsIgnoreCase("notok")) {
				out.append(
						"                        <input type=\"text\" name=\"txtUserName\" class=\"form-control is-invalid\" id=\"txtUserName\" required>");
				out.append(
						"                        <div class=\"invalid-feedback\">Không tồn tại tài khoản hoặc mật khẩu sai</div>");
			}
		} else {
			out.append(
					"                        <input type=\"text\" name=\"txtUserName\" class=\"form-control\" id=\"txtUserName\" required>");
			out.append("                        <div class=\"invalid-feedback\">Không tồn tại tài khoản hoặc mật khẩu sai</div>");
		}

//		 out.append("                        <input type=\"text\" name=\"txtUserName\" class=\"form-control\" id=\"txtUserName\" required>");
//		 out.append("                        <div class=\"invalid-feedback\">Hãy nhập tên tài khoản!</div>");
		out.append("                      </div>");
		out.append("                    </div>");
		out.append("");
		out.append("                    <div class=\"col-12\">");
		out.append("                      <label for=\"yourPassword\" class=\"form-label\">Mật khẩu</label>");
		out.append(
				"                      <input type=\"password\" name=\"txtPassword\" class=\"form-control\" id=\"txtPassword\" required>");
		out.append("                      <div class=\"invalid-feedback\">Hãy nhập mật khẩu!</div>");
		out.append("                    </div>");
		out.append("");
//		 out.append("                    <div class=\"col-12\">");
//		 out.append("                      <div class=\"form-check\">");
//		 out.append("                        <input class=\"form-check-input\" type=\"checkbox\" name=\"remember\" value=\"true\" id=\"rememberMe\">");
//		 out.append("                        <label class=\"form-check-label\" for=\"rememberMe\">Ghi nhớ tôi</label>");
//		 out.append("                      </div>");
//		 out.append("                    </div>");
		out.append("                    <div class=\"col-12\">");
		out.append(
				"                      <button class=\"btn btn-primary w-100 mt-2 mb-4\" type=\"submit\">Đăng nhập</button>");
//		 out.append("                      <button class=\"btn btn-primary w-100 mt-2 mb-4\" type=\"button\" onclick=\"login(document.frmLogin)\">Đăng nhập</button>");
		out.append("                    </div>");
		out.append("                  </form>");
		out.append("                </div>");
		out.append("              </div>");
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
		// TODO Auto-generated method stub

//		doGet(req, resp);
		String name = req.getParameter("txtUserName");
		String pass = req.getParameter("txtPassword");

		if (Validate.validateUserName(name) && Validate.validatePwd(pass)) {
			name = name.trim();
			pass = pass.trim();

			UserAccount userAccount = UserAccountFunctionImpl.getInstance().getUserAccount(name, pass);

			System.out.println(userAccount);

			if (userAccount != null) {
				HttpSession session = req.getSession();
				session.setAttribute("userLogined", userAccount);

				// Chuyển hướng
				if (userAccount.getAdministrator() != null) {
					resp.sendRedirect("/adv/ad/userview");
				} else {
					resp.sendRedirect("/adv/to/tour");
				}
			} else {
				resp.sendRedirect("/adv/Login?err=notok");
			}

		} else {
//			doGet(req, resp);
//			doGet();
			resp.sendRedirect("/adv/Login?err=param");
		}
	}

}

package ads.ad;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import ads.objects.UserAccount;
import ads.user.UserAccountFunction;
import ads.user.UserAccountFunctionImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ad-account-management-recent-delete")
public class AccountRecentDeleteServlet extends HttpServlet {
	private UserAccountFunction userAccountFunction = new UserAccountFunctionImpl();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("username") == null) {
			resp.sendRedirect(req.getContextPath() + "/ad-login");
			return;
		}

		String username = (String) session.getAttribute("username");
		
		ArrayList<UserAccount> userAccounts = userAccountFunction.getRecentDelelteAccounts();
		
		PrintWriter out = resp.getWriter();
		
		out.append("<!DOCTYPE html>");
		out.append("<html lang=\"en\">");
		out.append("");
		out.append("<head>");
		out.append("  <meta charset=\"utf-8\">");
		out.append("  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">");
		out.append("");
		out.append("  <title>TravelVN - Quản lý tài khoản</title>");
		out.append("  <meta content=\"\" name=\"description\">");
		out.append("  <meta content=\"\" name=\"keywords\">");
		out.append("");
		out.append("  <!-- Favicons -->");
		out.append("  <link href=\"assets/img/Logo.svg\" rel=\"icon\">");
		out.append("  <link href=\"assets/img/apple-touch-icon.png\" rel=\"apple-touch-icon\">");
		out.append("");
		out.append("  <!-- Google Fonts -->");
		out.append("  <link href=\"https://fonts.gstatic.com\" rel=\"preconnect\">");
		out.append("  <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i\" rel=\"stylesheet\">");
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
		out.append("</head>");
		out.append("");
		out.append("<body>");
		out.append("");
		out.append("    <!-- ======= Header ======= -->");
		out.append("    <header id=\"header\" class=\"header fixed-top d-flex align-items-center\">");
		out.append("");
		out.append("        <div class=\"d-flex align-items-center justify-content-between\">");
		out.append("            <a href=\"index.html\" class=\"logo d-flex align-items-center\">");
		out.append("                <img src=\"assets/img/Logo.svg\" alt=\"\">");
		out.append("                <span class=\"d-none d-lg-block\">TravelVN</span>");
		out.append("            </a>");
		out.append("            <i class=\"bi bi-list toggle-sidebar-btn\"></i>");
		out.append("        </div><!-- End Logo -->");
		out.append("");
		out.append("        <nav class=\"header-nav ms-auto\">");
		out.append("            <ul class=\"d-flex align-items-center\">");
		out.append("");
		out.append("                <li class=\"nav-item dropdown pe-3\">");
		out.append("");
		out.append("                    <a class=\"nav-link nav-profile d-flex align-items-center pe-0\" href=\"#\" data-bs-toggle=\"dropdown\">");
		out.append("                        <span class=\"d-none d-md-block dropdown-toggle ps-2\">" + username + "</span>");
		out.append("                    </a>");
		out.append("");
		out.append("                    <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow profile\">");
		out.append("                        <li class=\"dropdown-header\">");
		out.append("                            <h6>" + username + "</h6>");
		out.append("                            <span>Người quản lý</span>");
		out.append("                        </li>");
		out.append("                        <li>");
		out.append("                            <hr class=\"dropdown-divider\">");
		out.append("                        </li>");
		out.append("");
		out.append("                        <li>");
		out.append("                            <a class=\"dropdown-item d-flex align-items-center\" href=\"" + req.getContextPath() + "/ad-profile-management\">");
		out.append("                                <i class=\"bi bi-person\"></i>");
		out.append("                                <span>Hồ sơ của tôi</span>");
		out.append("                            </a>");
		out.append("                        </li>");
		out.append("                        <li>");
		out.append("                            <hr class=\"dropdown-divider\">");
		out.append("                        </li>");
		out.append("");
		out.append("                        <li>");
		out.append("                            <a class=\"dropdown-item d-flex align-items-center\" href=\"" + req.getContextPath() + "/ad-logout\">");
		out.append("                                <i class=\"bi bi-box-arrow-right\"></i>");
		out.append("                                <span>Đăng xuất</span>");
		out.append("                            </a>");
		out.append("                        </li>");
		out.append("");
		out.append("                    </ul><!-- End Profile Dropdown Items -->");
		out.append("                </li><!-- End Profile Nav -->");
		out.append("");
		out.append("            </ul>");
		out.append("        </nav><!-- End Icons Navigation -->");
		out.append("");
		out.append("    </header><!-- End Header -->");
		out.append("");
		out.append("  <!-- ======= Sidebar ======= -->");
		out.append("  <aside id=\"sidebar\" class=\"sidebar\">");
		out.append("");
		out.append("    <ul class=\"sidebar-nav\" id=\"sidebar-nav\">");
		out.append("");
		out.append("      <!-- Dashboard Nav -->");
		out.append("      <li class=\"nav-item\">");
		out.append("        <a class=\"nav-link collapsed\" href=\"" + req.getContextPath() + "/ad-dashboard\">");
		out.append("          <i class=\"bx bx-category\"></i>");
		out.append("          <span>Tổng quan</span>");
		out.append("        </a>");
		out.append("      </li>");
		out.append("      <!-- End Dashboard Nav -->");
		out.append("");
		out.append("      <!-- Account Management Nav -->");
		out.append("      <li class=\"nav-item\">");
		out.append("        <a class=\"nav-link\" href=\"" + req.getContextPath() + "/ad-account-management\">");
		out.append("          <i class=\"bx bx-user-circle\"></i>");
		out.append("          <span>Quản lý tài khoản</span>");
		out.append("        </a>");
		out.append("      </li>");
		out.append("      <!-- End Account Management Nav -->");
		out.append("");
		out.append("      <!-- Staff Management Nav -->");
		out.append("      <li class=\"nav-item\">");
		out.append("        <a class=\"nav-link collapsed\" data-bs-target=\"#staff-management-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.append("          <i class=\"bx bx-user\"></i><span>Quản lý nhân viên</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.append("        </a>");
		out.append("        <ul id=\"staff-management-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">");
		out.append("          <li>");
		out.append("            <a href=\"" + req.getContextPath() + "/ad-touroperator-management\">");
		out.append("              <i class=\"bi bi-circle\"></i><span style=\"font-size: 12px;\">Điều hành Tour</span>");
		out.append("            </a>");
		out.append("          </li>");
		out.append("          <li>");
		out.append("            <a href=\"" + req.getContextPath() + "/ad-tourguide-management\">");
		out.append("              <i class=\"bi bi-circle\"></i><span style=\"font-size: 12px;\">Hướng dẫn viên</span>");
		out.append("            </a>");
		out.append("          </li>");
		out.append("        </ul>");
		out.append("      </li>");
		out.append("      <!-- End Staff Management Nav -->");
		out.append("");
		out.append("      <!-- Customer Management Nav -->");
		out.append("      <li class=\"nav-item\">");
		out.append("        <a class=\"nav-link collapsed\" href=\"" + req.getContextPath() + "/ad-customer-management\">");
		out.append("          <i class=\"bx bx-group\"></i>");
		out.append("          <span>Quản lý khách hàng</span>");
		out.append("        </a>");
		out.append("      </li>");
		out.append("      <!-- End Customer Management Nav -->");
		out.append("");
		out.append("      <!-- Tour Management Nav -->");
		out.append("      <li class=\"nav-item\">");
		out.append("        <a class=\"nav-link collapsed\" href=\"" + req.getContextPath() + "/ad-tour-management\">");
		out.append("          <i class=\"bx bx-food-menu\"></i>");
		out.append("          <span>Quản lý Tour</span>");
		out.append("        </a>");
		out.append("      </li>");
		out.append("      <!-- End Tour Management Nav -->");
		out.append("");
		out.append("      <!-- Review Management Nav -->");
		out.append("      <li class=\"nav-item\">");
		out.append("        <a class=\"nav-link collapsed\" href=\"" + req.getContextPath() + "/ad-tourrating-management\">");
		out.append("          <i class=\"bx bx-message-square-detail\"></i>");
		out.append("          <span>Duyệt đánh giá</span>");
		out.append("        </a>");
		out.append("      </li>");
		out.append("      <!-- End Review Management Nav -->");
		out.append("    </ul>");
		out.append("");
		out.append("  </aside><!-- End Sidebar-->");
		out.append("");
		out.append("  <main id=\"main\" class=\"main\">");
		out.append("");
		out.append("    <div class=\"pagetitle d-flex\">");
		out.append("      <h1>Tài khoản đã xóa gần đây</h1>");
		out.append("      <nav class=\"ms-auto\">");
		out.append("        <ol class=\"breadcrumb\">");
		out.append("          <li class=\"breadcrumb-item active\"><a href=\"" + req.getContextPath() + "/ad-account-management\">Quản lý tài khoản</a></li>");
		out.append("          <li class=\"breadcrumb-item active\">Xóa gần đây</li>");
		out.append("        </ol>");
		out.append("      </nav>");
		out.append("    </div><!-- End Page Title -->");
		out.append("");
		out.append("    <section class=\"section\">");
		out.append("        <div class=\"row\">");
		out.append("          <div class=\"col-lg-12\">");
		out.append("  ");
		out.append("            <div class=\"card\">");
		out.append("              <div class=\"card-body\">");
		out.append("                <div class=\"d-flex justify-content-between mt-4 mb-3\">");
		out.append("                  <div class=\"d-flex align-items-center\">");
		out.append("                    <select id=\"recordCount\" class=\"p-1 ps-2\">");
		out.append("                      <option value=\"5\">5</option>");
		out.append("                      <option value=\"10\" selected>10</option>");
		out.append("                      <option value=\"20\">20</option>");
		out.append("                      <option value=\"all\">Tất cả</option>");
		out.append("                    </select>");
		out.append("                    <label for=\"recordCount\" class=\"ms-2 me-4\">bản ghi / trang</label>");
		out.append("                    <div>");
		out.append("                      <input type=\"text\" id=\"searchInput\" class=\"p-1 ps-2\" placeholder=\"Tìm kiếm...\" />");
		out.append("                    </div>");
		out.append("                  </div>");
		out.append("                  <div>");
		out.append("                    <button type=\"button\" class=\"btn btn-outline-primary btn-sm\" id=\"toggleCheckAll\">Chọn tất cả</button>");
		out.append("                    <button type=\"button\" class=\"btn btn-success btn-sm\" id=\"restoreSelectedBtn\">Khôi phục</button>");
		out.append("                    <button type=\"button\" class=\"btn btn-danger btn-sm\" id=\"deleteSelectedBtn\">Xóa</button>");
		out.append("                  </div>");
		out.append("                </div>");
		out.append("");
		out.append("                <!-- Table with stripped rows -->");
		out.append("                <table class=\"table table-hover\" id=\"myTable\">");
		out.append("                  <thead>");
		out.append("                    <tr>");
		out.append("                      <th class=\"text-center\">#</th>");
		out.append("                      <th class=\"text-center\">ID</th>");
		out.append("                      <th>Tên đăng nhập</th>");
		out.append("                      <th>Mật khẩu</th>");
		out.append("                      <th>Email</th>");
		out.append("                      <th>Quyền</th>");
		out.append("                      <th class=\"text-center\">Trạng thái</th>");
		out.append("  					  <th class=\"text-center\">Lựa chọn</th>");
		out.append("                      <th class=\"text-center\">Hành động</th>");
		out.append("                    </tr>");
		out.append("                  </thead>");
		out.append("                  <tbody>");
		
		for (UserAccount userAccount : userAccounts) {
			Integer id = -1;
			String role = "";
			if(userAccount.getAdministrator() != null) {
				id = userAccount.getAdministrator().getId();
				role = "Người quản lý";
			}
			else if(userAccount.getTourOperator() != null) {
				id = userAccount.getTourOperator().getId();
				role = "Người điều hành Tour";
			}
			else if (userAccount.getTourGuide() != null) {
				id = userAccount.getTourGuide().getId();
				role = "Hướng dẫn viên du lịch";
			}
			else if (userAccount.getC() != null) {
				id = userAccount.getC().getId();
				role = "Khách hàng";
			}
			
			boolean isCurrentUser = userAccount.getUsername().equals(username);
			
			out.append("                    <tr>");
			out.append("                      <th class=\"text-center\"></th>");
			out.append("                      <td class=\"text-center\">" + id + "</td>");
			out.append("                      <td>" + userAccount.getUsername() + "</td>");
			out.append("                      <td>*********</td>");
			out.append("                      <td>" + userAccount.getEmail() + "</td>");
			out.append("                      <td>" + role + "</td>");
			out.append("                      <td class=\"text-center\">" + userAccount.getStatus() + "</td>");
			out.append("                      <td class=\"text-center\"><input type=\"checkbox\" name=\"usernames\" value=\"" + userAccount.getUsername() + "\"></td>");
			out.append("                      <td class=\"text-center\">");
			out.append("                        <button type=\"button\" class=\"btn btn-success btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#confirmRestoreModal-" + userAccount.getUsername() + "\"><i class=\"bi bi-arrow-counterclockwise\"></i></button>");
			out.append("                        <button type=\"button\" class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#confirmDeleteModal-" + userAccount.getUsername() + "\"><i class=\"bx bx-trash\"></i></button>");
			out.append("                      </td>");
			out.append("                    </tr>");
			
			// Modal xác nhận xóa cho mỗi account
			out.append("      <!-- Modal Xác Nhận Xóa -->");
			out.append("      <div class=\"modal fade\" id=\"confirmDeleteModal-" + userAccount.getUsername() + "\" tabindex=\"-1\" aria-labelledby=\"confirmDeleteModalLabel\" aria-hidden=\"true\">");
			out.append("        <div class=\"modal-dialog\">");
			out.append("          <div class=\"modal-content\">");
			out.append("            <div class=\"modal-header text-bg-danger\">");
			out.append("              <h5 class=\"modal-title text-light fw-bold\" id=\"confirmDeleteModalLabel\">Xác nhận xóa</h5>");
			out.append("              <button type=\"button\" class=\"btn-close fs-2\" data-bs-dismiss=\"modal\" aria-label=\"Đóng\"></button>");
			out.append("            </div>");
			out.append("            <div class=\"modal-body text-dark text-start\">");
			out.append("              	Bạn có chắc chắn muốn xóa vĩnh viễn tài khoản <strong>" + userAccount.getUsername() + "</strong> này không?");
			out.append("            </div>");
			out.append("            <div class=\"modal-footer\">");
			out.append("              <form method=\"post\" action=\"" + req.getContextPath() + "/ad-account-management-recent-delete/delete\">");
			out.append("                  <input type='hidden' name='username' value='" + userAccount.getUsername() + "'>");
			out.append("              	  <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Hủy</button>");
			out.append("              	  <button type=\"submit\" class=\"btn btn-danger\">Xóa</button>");
			out.append("              </form>");
			out.append("            </div>");
			out.append("          </div>");
			out.append("        </div>");
			out.append("      </div>");
			
			// Modal xác khôi phục cho mỗi account
			out.append("      <!-- Modal Xác Nhận Khôi Phục -->");
			out.append("      <div class=\"modal fade\" id=\"confirmRestoreModal-" + userAccount.getUsername() + "\" tabindex=\"-1\" aria-labelledby=\"confirmRestoreModalLabel\" aria-hidden=\"true\">");
			out.append("        <div class=\"modal-dialog\">");
			out.append("          <div class=\"modal-content\">");
			out.append("            <div class=\"modal-header text-bg-success\">");
			out.append("              <h5 class=\"modal-title text-light fw-bold\" id=\"confirmRestoreModalLabel\">Xác nhận khôi phục</h5>");
			out.append("              <button type=\"button\" class=\"btn-close fs-2\" data-bs-dismiss=\"modal\" aria-label=\"Đóng\"></button>");
			out.append("            </div>");
			out.append("            <div class=\"modal-body text-dark text-start\">");
			out.append("              	Bạn có muốn khôi phục tài khoản <strong>" + userAccount.getUsername() + "</strong> này không?");
			out.append("            </div>");
			out.append("            <div class=\"modal-footer\">");
			out.append("              <form method=\"post\" action=\"" + req.getContextPath() + "/ad-account-management-recent-delete/restore\">");
			out.append("                  <input type='hidden' name='username' value='" + userAccount.getUsername() + "'>");
			out.append("              	  <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Hủy</button>");
			out.append("              	  <button type=\"submit\" class=\"btn btn-success\">Khôi phục</button>");
			out.append("              </form>");
			out.append("            </div>");
			out.append("          </div>");
			out.append("        </div>");
			out.append("      </div>");
		}
		
		out.append("                  </tbody>");
		out.append("                </table>");
		out.append("                <!-- End Table with stripped rows -->");
		
		// JS xử lý chọn và khôi phục
		out.append("<script>");
		out.append("  let allChecked = false;");
		out.append("");
		out.append("  document.getElementById(\"toggleCheckAll\").addEventListener(\"click\", function () {");
		out.append("    const checkboxes = document.querySelectorAll(\"input[name='usernames']\");");
		out.append("    allChecked = !allChecked;");
		out.append("    checkboxes.forEach(cb => cb.checked = allChecked);");
		out.append("    this.textContent = allChecked ? \"Bỏ chọn tất cả\" : \"Chọn tất cả\";");
		out.append("  });");
		out.append("");
		out.append("  document.getElementById(\"restoreSelectedBtn\").addEventListener(\"click\", function () {");
		out.append("    const selected = [];");
		out.append("    document.querySelectorAll(\"input[name='usernames']:checked\").forEach(cb => {");
		out.append("      selected.push(cb.value);");
		out.append("    });");
		out.append("");
		out.append("    if (selected.length === 0) {");
		out.append("      alert(\"Vui lòng chọn ít nhất một tài khoản để khôi phục.\");");
		out.append("      return;");
		out.append("    }");
		out.append("");
		out.append("    const params = selected.map(u => \"usernames=\" + encodeURIComponent(u)).join(\"&\");");
		out.append("");
		out.append("    fetch(\"" + req.getContextPath() + "/ad-account-management-recent-delete/restore\", {");
		out.append("      method: \"POST\",");
		out.append("      headers: {");
		out.append("        \"Content-Type\": \"application/x-www-form-urlencoded\"");
		out.append("      },");
		out.append("      body: params");
		out.append("    })");
		out.append("    .then(res => {");
		out.append("      if (res.ok) {");
		out.append("        location.reload();");
		out.append("      } else {");
		out.append("        alert(\"Có lỗi xảy ra khi khôi phục.\");");
		out.append("      }");
		out.append("    })");
		out.append("    .catch(err => {");
		out.append("      console.error(err);");
		out.append("      alert(\"Lỗi kết nối server.\");");
		out.append("    });");
		out.append("  });");
		out.append("</script>");

		// Modal xác nhận xóa các tài khoản đã chọn
		out.append("<div class='modal fade' id='confirmDeleteModal' tabindex='-1' aria-labelledby='confirmDeleteModalLabel' aria-hidden='true'>");
		out.append("  <div class='modal-dialog'>");
		out.append("    <div class='modal-content'>");
		out.append("      <div class='modal-header text-bg-danger'>");
		out.append("        <h5 class='modal-title' id='confirmDeleteModalLabel'>Xác nhận xóa</h5>");
		out.append("        <button type='button' class='btn-close fs-2' data-bs-dismiss='modal' aria-label='Đóng'></button>");
		out.append("      </div>");
		out.append("      <div class='modal-body text-dark'>");
		out.append("        Bạn có chắc chắn muốn xóa các tài khoản đã chọn không?");
		out.append("      </div>");
		out.append("      <div class='modal-footer'>");
		out.append("        <button type='button' class='btn btn-secondary' data-bs-dismiss='modal'>Hủy</button>");
		out.append("        <button type='button' class='btn btn-danger' id='confirmDeleteBtn'>Xác nhận</button>");
		out.append("      </div>");
		out.append("    </div>");
		out.append("  </div>");
		out.append("</div>");
		
		// JS xử lý chọn và xóa các tài khoản đã chọn
		out.append("<script>");
		out.append("  let selectedToDelete = [];");

		out.append("  document.getElementById(\"deleteSelectedBtn\").addEventListener(\"click\", function () {");
		out.append("    selectedToDelete = [];");
		out.append("    document.querySelectorAll(\"input[name='usernames']:checked\").forEach(cb => {");
		out.append("      selectedToDelete.push(cb.value);");
		out.append("    });");

		out.append("    if (selectedToDelete.length === 0) {");
		out.append("      alert(\"Vui lòng chọn ít nhất một tài khoản để xóa.\");");
		out.append("      return;");
		out.append("    }");

		out.append("    const modal = new bootstrap.Modal(document.getElementById('confirmDeleteModal'));");
		out.append("    modal.show();");
		out.append("  });");

		out.append("  document.getElementById(\"confirmDeleteBtn\").addEventListener(\"click\", function () {");
		out.append("    const form = document.createElement(\"form\");");
		out.append("    form.method = \"POST\";");
		out.append("    form.action = \"" + req.getContextPath() + "/ad-account-management-recent-delete/delete\";");

		out.append("    selectedToDelete.forEach(username => {");
		out.append("      const input = document.createElement(\"input\");");
		out.append("      input.type = \"hidden\";");
		out.append("      input.name = \"usernames\";");
		out.append("      input.value = username;");
		out.append("      form.appendChild(input);");
		out.append("    });");

		out.append("    document.body.appendChild(form);");
		out.append("    form.submit();");
		out.append("  });");
		out.append("</script>");
		
		out.append("  </main><!-- End #main -->");
		out.append("");
		out.append("  <!-- ======= Footer ======= -->");
		out.append("  <footer id=\"footer\" class=\"footer\">");
		out.append("    <div class=\"copyright\">");
		out.append("      &copy; Copyright <strong><span>TravelVN</span></strong>. All Rights Reserved");
		out.append("    </div>");
		out.append("  </footer><!-- End Footer -->");
		out.append("");
		out.append("  <a href=\"#\" class=\"back-to-top d-flex align-items-center justify-content-center\"><i class=\"bi bi-arrow-up-short\"></i></a>");
		out.append("");
		out.append("  <!-- JS Customize -->");
		out.append("  <script src=\"assets/js/dataTableCustom.js\"></script>");
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
}

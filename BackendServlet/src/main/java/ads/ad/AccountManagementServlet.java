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

@WebServlet("/ad-account-management")
public class AccountManagementServlet extends HttpServlet{
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
		
		ArrayList<UserAccount> userAccounts = userAccountFunction.getFirst100UserAccounts();
		
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
		out.append("              <i class=\"bi bi-circle\"></i><span>Người điều hành Tour</span>");
		out.append("            </a>");
		out.append("          </li>");
		out.append("          <li>");
		out.append("            <a href=\"" + req.getContextPath() + "/ad-tourguide-management\">");
		out.append("              <i class=\"bi bi-circle\"></i><span>Hướng dẫn viên du lịch</span>");
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
		out.append("      <h1>Quản lý tài khoản</h1>");
		out.append("      <nav class=\"ms-auto\">");
		out.append("        <ol class=\"breadcrumb\">");
		out.append("          <li class=\"breadcrumb-item active\">Quản lý tài khoản</li>");
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
		out.append("                  <button type=\"button\" class=\"btn btn-success btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#addNewModal\"><i class=\"bx bx-user-plus me-1\"></i>Thêm mới</button>");
		out.append("                </div>");
		out.append("");
		out.append("                <div class=\"mb-2\"><a href=\"" + req.getContextPath() + "/ad-account-management-recent-delete\">Đã xóa gần đây</a></div>");
		
		out.append("                <!-- Table with stripped rows -->");
		out.append("                <table class=\"table table-hover\" id=\"myTable\">");
		out.append("                  <thead>");
		out.append("                    <tr>");
		out.append("                      <th class = \"text-center\">#</th>");
		out.append("                      <th class = \"text-center\">ID</th>");
		out.append("                      <th>Tên đăng nhập</th>");
		out.append("                      <th>Mật khẩu</th>");
		out.append("                      <th>Email</th>");
		out.append("                      <th>Quyền</th>");
		out.append("                      <th class = \"text-center\">Trạng thái</th>");
		out.append("                      <th class = \"text-center\">Hành động</th>");
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
			out.append("                      <td>" + userAccount.getPassword() + "</td>");
			out.append("                      <td>" + userAccount.getEmail() + "</td>");
			out.append("                      <td>" + role + "</td>");
			out.append("                      <td class=\"text-center\">" + userAccount.getStatus() + "</td>");
			
			String lockIcon = "";
			if(userAccount.getStatus().equals("OFF")) {
				lockIcon = "bx bxs-lock-open";
			}
			else {
				lockIcon = "bx bxs-lock";
			}
		
			out.append("                      <td class=\"text-center\">");
			
			if(!isCurrentUser) {
				out.append("                    <button type='button' class='btn btn-warning btn-sm text-white' data-bs-toggle='modal' data-bs-target='#confirmLockModal-" + userAccount.getUsername() + "'><i class='" + lockIcon + "'></i></button>");
			}
			else {
				out.append("                    <button type='button' class='btn btn-secondary btn-sm text-white' disabled><i class='" + lockIcon + "'></i></button>");
			}
			
			out.append("                        <button type=\"button\" class=\"btn btn-primary btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#changeInforModal-" + userAccount.getUsername() + "\"><i class=\"bx bxs-pencil\"></i></button>");
			out.append("                        <button type=\"button\" class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#confirmDeleteModal-" + userAccount.getUsername() + "\"><i class=\"bx bx-trash\"></i></button>");
			out.append("                      </td>");
			out.append("                    </tr>");
			
			// Modal xác nhận khóa cho mỗi account
			String titleLock = "";
			String msgLock = "";
			String btnLock = "";
			if(userAccount.getStatus().equals("OFF")) {
				titleLock = "Xác nhận khóa";
				msgLock = "khóa";
				btnLock = "Khóa";
			}
			else {
				titleLock = "Xác nhận mở khóa";
				msgLock = "mở khóa";
				btnLock = "Mở khóa";
			}
			
			out.append("      <!-- Modal Xác Nhận Khóa -->");
			out.append("      <div class=\"modal fade\" id=\"confirmLockModal-" + userAccount.getUsername() + "\" tabindex=\"-1\" aria-labelledby=\"confirmLockModalLabel\" aria-hidden=\"true\">");
			out.append("        <div class=\"modal-dialog\">");
			out.append("          <div class=\"modal-content\">");
			out.append("            <div class=\"modal-header text-bg-warning\">");
			out.append("              <h5 class=\"modal-title text-light fw-bold\" id=\"confirmLockModalLabel\">" + titleLock + "</h5>");
			out.append("              <button type=\"button\" class=\"btn-close fs-2\" data-bs-dismiss=\"modal\" aria-label=\"Đóng\"></button>");
			out.append("            </div>");
			out.append("            <div class=\"modal-body text-dark text-start\">");
			out.append("              	Bạn có chắc chắn muốn " + msgLock + " tài khoản <strong>" + userAccount.getUsername() + "</strong> không?");
			out.append("            </div>");
			out.append("            <div class=\"modal-footer\">");
			out.append("              <form method=\"post\" action=\"" + req.getContextPath() + "/ad-account-management/lock\">");
			out.append("                  <input type='hidden' name='username' value='" + userAccount.getUsername() + "'>");
			out.append("              	  <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Hủy</button>");
			out.append("              	  <button type=\"submit\" class=\"btn btn-warning\">" + btnLock + "</button>");
			out.append("              </form>");
			out.append("            </div>");
			out.append("          </div>");
			out.append("        </div>");
			out.append("      </div>");
			
			// Modal sửa thông tin cho mỗi account
			out.append("      <!-- Modal Sửa Thông Tin -->");
			out.append("      <div class=\"modal fade\" id=\"changeInforModal-" + userAccount.getUsername() + "\" tabindex=\"-1\" aria-labelledby=\"changeInforModalLabel\" aria-hidden=\"true\">");
			out.append("        <div class=\"modal-dialog modal-md\">");
			out.append("          <div class=\"modal-content\">");
			out.append("            <div class=\"modal-header text-bg-primary\">");
			out.append("              <h5 class=\"modal-title fw-bold\" id=\"changeInforModalLabel\">Sửa thông tin tài khoản</h5>");
			out.append("              <button type=\"button\" class=\"btn-close fs-2\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
			out.append("            </div>");
			out.append("            <div class=\"modal-body\">");
			out.append("              <form id=\"formChangeInfor\" action=\"" + req.getContextPath() + "/ad-account-management/update\" method=\"POST\">");
			out.append("                <div class=\"row\">");
			out.append("                  <div class=\"col-md-12 mb-2\">");
			out.append("                    <label for=\"id\" class=\"form-label\">ID</label>");
			out.append("                    <input type=\"text\" class=\"form-control\" id=\"id\" name=\"id\" value=\"" + id + "\" disabled>");
			out.append("                  </div>");
			out.append("                </div>");
			out.append("      ");
			out.append("                <div class=\"row\">");
			out.append("                  <div class=\"col-md-12 mb-2\">");
			out.append("                    <label for=\"username\" class=\"form-label\">Tên đăng nhập</label>");
			out.append("                    <input type=\"text\" class=\"form-control\" id=\"username\" name=\"username\" value=\"" + userAccount.getUsername() + "\" disabled>");
			out.append("                    <input type=\"hidden\" class=\"form-control\" id=\"username\" name=\"username\" value=\"" + userAccount.getUsername() + "\">");
			out.append("                  </div>");
			out.append("                </div>");
			out.append("      ");
			out.append("                <div class=\"row\">");
			out.append("                  <div class=\"col-md-12 mb-2\">");
			out.append("                    <label for=\"password\" class=\"form-label\">Mật khẩu</label>");
			out.append("                    <input type=\"text\" class=\"form-control\" id=\"password\" name=\"password\" value=\"" + userAccount.getPassword() + "\" required>");
			out.append("                  </div>");
			out.append("                </div>");
			out.append("      ");
			out.append("                <div class=\"row\">");
			out.append("                  <div class=\"col-md-12 mb-2\">");
			out.append("                    <label for=\"email\" class=\"form-label\">Email</label>");
			out.append("                    <input type=\"email\" class=\"form-control\" id=\"email\" name=\"email\" value=\"" + userAccount.getEmail() + "\" required>");
			out.append("                  </div>");
			out.append("                </div>");
			out.append("                ");
			out.append("                <div class=\"row\">");
			out.append("                  <div class=\"col-md-12 mb-3\">");
			out.append("                    <label for=\"role\" class=\"form-label\">Quyền</label>");
			out.append("                    <input type=\"text\" class=\"form-control\" id=\"role\" name=\"role\" value=\"" + role + "\" disabled>");
			out.append("                  </div>");
			out.append("                </div>");
			out.append("");
			out.append("                <button type=\"submit\" class=\"btn btn-primary w-100\">Lưu thay đổi</button>");
			out.append("              </form>");
			out.append("            </div>");
			out.append("          </div>");
			out.append("        </div>");
			out.append("      </div>");
			
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
			out.append("              	Bạn có muốn chuyển tài khoản <strong>" + userAccount.getUsername() + "</strong> vào thùng rác không?");
			out.append("            </div>");
			out.append("            <div class=\"modal-footer\">");
			out.append("              <form method=\"post\" action=\"" + req.getContextPath() + "/ad-account-management/delete-soft\">");
			out.append("                  <input type='hidden' name='username' value='" + userAccount.getUsername() + "'>");
			out.append("              	  <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Hủy</button>");
			out.append("              	  <button type=\"submit\" class=\"btn btn-danger\">Chuyển vào thùng rác</button>");
			out.append("              </form>");
			out.append("            </div>");
			out.append("          </div>");
			out.append("        </div>");
			out.append("      </div>");
		}
		
		out.append("                    ");
		out.append("                  </tbody>");
		out.append("                </table>");
		out.append("                <!-- End Table with stripped rows -->");
		out.append("");
		out.append("                <div class=\"d-flex align-items-center justify-content-between\">");
		out.append("                  <span id=\"recordInfo\"></span>");
		out.append("                  <div id=\"pagination\"></div>");
		out.append("                </div>");
		out.append("  ");
		out.append("                ");
		out.append("              </div>");
		out.append("            </div>");
		out.append("  ");
		out.append("          </div>");
		out.append("        </div>");
		out.append("      </section>");
		out.append("      ");
		
		
		out.append("      <!-- Modal Thêm Mới -->");
		out.append("      <div class=\"modal fade\" id=\"addNewModal\" tabindex=\"-1\" aria-labelledby=\"addNewModalLabel\" aria-hidden=\"true\">");
		out.append("        <div class=\"modal-dialog modal-md\">");
		out.append("          <div class=\"modal-content\">");
		out.append("            <div class=\"modal-header text-bg-success\">");
		out.append("              <h5 class=\"modal-title fw-bold tb\" id=\"addNewModalLabel\">Thêm mới tài khoản</h5>");
		out.append("              <button type=\"button\" class=\"btn-close fs-2\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		out.append("            </div>");
		out.append("            <div class=\"modal-body\">");
		out.append("              <form id=\"formAddNew\" action=\"" + req.getContextPath() + "/ad-account-management/add\" method=\"POST\">");
		out.append("                <div class=\"row\">");
		out.append("                  <div class=\"col-md-12 mb-2\">");
		out.append("                    <label for=\"username\" class=\"form-label\">Tên đăng nhập</label>");
		out.append("                    <input type=\"text\" class=\"form-control\" id=\"username\" name=\"username\" required>");
		out.append("                  </div>");
		out.append("                </div>");
		out.append("      ");
		out.append("                <div class=\"row\">");
		out.append("                  <div class=\"col-md-12 mb-2\">");
		out.append("                    <label for=\"password\" class=\"form-label\">Mật khẩu</label>");
		out.append("                    <input type=\"text\" class=\"form-control\" id=\"password\" name=\"password\" required>");
		out.append("                  </div>");
		out.append("                </div>");
		out.append("      ");
		out.append("                <div class=\"row\">");
		out.append("                  <div class=\"col-md-12 mb-2\">");
		out.append("                    <label for=\"email\" class=\"form-label\">Email</label>");
		out.append("                    <input type=\"email\" class=\"form-control\" id=\"email\" name=\"email\" required>");
		out.append("                  </div>");
		out.append("                </div>");
		out.append("                ");
		out.append("                <div class=\"row\">");
		out.append("                  <div class=\"col-md-12 mb-2\">");
		out.append("                    <label for=\"id\" class=\"form-label\">ID</label>");
		out.append("                    <input type=\"text\" class=\"form-control\" id=\"id\" name=\"id\" required>");
		out.append("                  </div>");
		out.append("                </div>");
		out.append("      ");
		out.append("                <div class=\"row\">");
		out.append("                  <div class=\"col-md-12 mb-3\">");
		out.append("                    <label for=\"role\" class=\"form-label\">Quyền</label>");
		out.append("                    <select class=\"form-select\" id=\"role\" name=\"role\" required>");
		out.append("                      <option value=\"customer\">Khách hàng</option>");
		out.append("                      <option value=\"administrator\">Người quản lý</option>");
		out.append("                      <option value=\"tourGuide\">Hướng dẫn viên du lịch</option>");
		out.append("                      <option value=\"tourOperator\">Người điều hành Tour</option>");
		out.append("                    </select>");
		out.append("                  </div>");
		out.append("                </div>");
		out.append("");
		out.append("                <button type=\"submit\" class=\"btn btn-success w-100\">Lưu</button>");
		out.append("              </form>");
		out.append("            </div>");
		out.append("          </div>");
		out.append("        </div>");
		out.append("      </div>");
		out.append("");
		
		
//		out.append("      <!-- Modal Xác Nhận Khóa -->");
//		out.append("      <div class=\"modal fade\" id=\"confirmLockModal\" tabindex=\"-1\" aria-labelledby=\"confirmLockModalLabel\" aria-hidden=\"true\">");
//		out.append("        <div class=\"modal-dialog\">");
//		out.append("          <div class=\"modal-content\">");
//		out.append("            <div class=\"modal-header text-bg-warning\">");
//		out.append("              <h5 class=\"modal-title text-light fw-bold\" id=\"confirmLockModalLabel\">Xác nhận khóa tài khoản</h5>");
//		out.append("              <button type=\"button\" class=\"btn-close fs-2\" data-bs-dismiss=\"modal\" aria-label=\"Đóng\"></button>");
//		out.append("            </div>");
//		out.append("            <div class=\"modal-body text-dark\">");
//		out.append("              Bạn có chắc chắn muốn khóa tài khoản này không?");
//		out.append("            </div>");
//		out.append("            <div class=\"modal-footer\">");
//		out.append("              <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Hủy</button>");
//		out.append("              <button type=\"button\" class=\"btn btn-warning text-white\" id=\"confirmLockBtn\">Khóa</button>");
//		out.append("            </div>");
//		out.append("          </div>");
//		out.append("        </div>");
//		out.append("      </div>");
		
		
//		out.append("      <!-- Modal Sửa Thông Tin -->");
//		out.append("      <div class=\"modal fade\" id=\"changeInforModal\" tabindex=\"-1\" aria-labelledby=\"changeInforModalLabel\" aria-hidden=\"true\">");
//		out.append("        <div class=\"modal-dialog modal-md\">");
//		out.append("          <div class=\"modal-content\">");
//		out.append("            <div class=\"modal-header text-bg-primary\">");
//		out.append("              <h5 class=\"modal-title fw-bold\" id=\"changeInforModalLabel\">Sửa thông tin tài khoản</h5>");
//		out.append("              <button type=\"button\" class=\"btn-close fs-2\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
//		out.append("            </div>");
//		out.append("            <div class=\"modal-body\">");
//		out.append("              <form id=\"formChangeInfor\">");
//		out.append("                <div class=\"row\">");
//		out.append("                  <div class=\"col-md-12 mb-2\">");
//		out.append("                    <label for=\"id\" class=\"form-label\">ID</label>");
//		out.append("                    <input type=\"text\" class=\"form-control\" id=\"id\" value=\"TO001\" disabled>");
//		out.append("                  </div>");
//		out.append("                </div>");
//		out.append("      ");
//		out.append("                <div class=\"row\">");
//		out.append("                  <div class=\"col-md-12 mb-2\">");
//		out.append("                    <label for=\"username\" class=\"form-label\">Tên đăng nhập</label>");
//		out.append("                    <input type=\"text\" class=\"form-control\" id=\"username\" value=\"longpham1504\" disabled>");
//		out.append("                  </div>");
//		out.append("                </div>");
//		out.append("      ");
//		out.append("                <div class=\"row\">");
//		out.append("                  <div class=\"col-md-12 mb-2\">");
//		out.append("                    <label for=\"password\" class=\"form-label\">Mật khẩu</label>");
//		out.append("                    <input type=\"text\" class=\"form-control\" id=\"password\" value=\"fdsfjjfsdlfjdkslfldsfds\" required>");
//		out.append("                  </div>");
//		out.append("                </div>");
//		out.append("      ");
//		out.append("                <div class=\"row\">");
//		out.append("                  <div class=\"col-md-12 mb-2\">");
//		out.append("                    <label for=\"email\" class=\"form-label\">Email</label>");
//		out.append("                    <input type=\"email\" class=\"form-control\" id=\"email\" value=\"pvl@gmail.com\" required>");
//		out.append("                  </div>");
//		out.append("                </div>");
//		out.append("                ");
//		out.append("                <div class=\"row\">");
//		out.append("                  <div class=\"col-md-12 mb-4\">");
//		out.append("                    <label for=\"role\" class=\"form-label\">Quyền</label>");
//		out.append("                    <input type=\"text\" class=\"form-control\" id=\"role\" value=\"Khách hàng\" disabled>");
//		out.append("                  </div>");
//		out.append("                </div>");
//		out.append("");
//		out.append("                <button type=\"submit\" class=\"btn btn-primary w-100\">Lưu thay đổi</button>");
//		out.append("              </form>");
//		out.append("            </div>");
//		out.append("          </div>");
//		out.append("        </div>");
//		out.append("      </div>");
		out.append("");
		
		
//		out.append("      <!-- Modal Xác Nhận Xóa -->");
//		out.append("      <div class=\"modal fade\" id=\"confirmDeleteModal\" tabindex=\"-1\" aria-labelledby=\"confirmDeleteModalLabel\" aria-hidden=\"true\">");
//		out.append("        <div class=\"modal-dialog\">");
//		out.append("          <div class=\"modal-content\">");
//		out.append("            <div class=\"modal-header text-bg-danger\">");
//		out.append("              <h5 class=\"modal-title text-light fw-bold\" id=\"confirmDeleteModalLabel\">Xác nhận xóa</h5>");
//		out.append("              <button type=\"button\" class=\"btn-close fs-2\" data-bs-dismiss=\"modal\" aria-label=\"Đóng\"></button>");
//		out.append("            </div>");
//		out.append("            <div class=\"modal-body text-dark\">");
//		out.append("              Bạn có chắc chắn muốn xóa tài khoản này không?");
//		out.append("            </div>");
//		out.append("            <div class=\"modal-footer\">");
//		out.append("              <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Hủy</button>");
//		out.append("              <button type=\"submit\" class=\"btn btn-danger\">Xóa</button>");
//		out.append("            </div>");
//		out.append("          </div>");
//		out.append("        </div>");
//		out.append("      </div>");
		
		
		
		out.append("      <!-- End Modal -->");
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

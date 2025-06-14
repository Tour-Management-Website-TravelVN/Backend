package ads.ad;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.commons.lang3.StringEscapeUtils;

import ads.objects.Customer;
import ads.objects.TourOperator;
import ads.user.CustomerFunction;
import ads.user.CustomerFunctionImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ad/customer-management")
public class CustomerManagementServlet extends HttpServlet {
	private CustomerFunction customerFunction = new CustomerFunctionImpl();

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
			resp.sendRedirect(req.getContextPath() + "/ad/login");
			return;
		}

		String username = (String) session.getAttribute("username");
		
		ArrayList<Customer> customers = customerFunction.getFirst100Customers();
		
		PrintWriter out = resp.getWriter();
		
		out.append("<!DOCTYPE html>");
		out.append("<html lang=\"en\">");
		out.append("");
		out.append("<head>");
		out.append("  <meta charset=\"utf-8\">");
		out.append("  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">");
		out.append("");
		out.append("  <title>TravelVN - Quản lý khách hàng</title>");
		out.append("  <meta content=\"\" name=\"description\">");
		out.append("  <meta content=\"\" name=\"keywords\">");
		out.append("");
		out.append("  <!-- Favicons -->");
		out.append("  <link href=\"/adv/assets/img/Logo.svg\" rel=\"icon\">");
		out.append("  <link href=\"/adv/assets/img/apple-touch-icon.png\" rel=\"apple-touch-icon\">");
		out.append("");
		out.append("  <!-- Google Fonts -->");
		out.append("  <link href=\"https://fonts.gstatic.com\" rel=\"preconnect\">");
		out.append("  <link");
		out.append("    href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i\"");
		out.append("    rel=\"stylesheet\">");
		out.append("");
		out.append("  <!-- Vendor CSS Files -->");
		out.append("  <link href=\"/adv/assets/vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"/adv/assets/vendor/bootstrap-icons/bootstrap-icons.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"/adv/assets/vendor/boxicons/css/boxicons.min.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"/adv/assets/vendor/quill/quill.snow.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"/adv/assets/vendor/quill/quill.bubble.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"/adv/assets/vendor/remixicon/remixicon.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"/adv/assets/vendor/simple-datatables/style.css\" rel=\"stylesheet\">");
		out.append("");
		out.append("  <!-- Template Main CSS File -->");
		out.append("  <link href=\"/adv/assets/css/style.css\" rel=\"stylesheet\">");
		out.append("</head>");
		out.append("");
		out.append("<body>");
		out.append("");
//		out.append("    <!-- ======= Header ======= -->");
//		out.append("    <header id=\"header\" class=\"header fixed-top d-flex align-items-center\">");
//		out.append("");
//		out.append("        <div class=\"d-flex align-items-center justify-content-between\">");
//		out.append("            <a href=\"index.html\" class=\"logo d-flex align-items-center\">");
//		out.append("                <img src=\"/adv/assets/img/Logo.svg\" alt=\"\">");
//		out.append("                <span class=\"d-none d-lg-block\">TravelVN</span>");
//		out.append("            </a>");
//		out.append("            <i class=\"bi bi-list toggle-sidebar-btn\"></i>");
//		out.append("        </div><!-- End Logo -->");
//		out.append("");
//		out.append("        <nav class=\"header-nav ms-auto\">");
//		out.append("            <ul class=\"d-flex align-items-center\">");
//		out.append("");
//		out.append("                <li class=\"nav-item dropdown pe-3\">");
//		out.append("");
//		out.append("                    <a class=\"nav-link nav-profile d-flex align-items-center pe-0\" href=\"#\" data-bs-toggle=\"dropdown\">");
//		out.append("                        <span class=\"d-none d-md-block dropdown-toggle ps-2\">" + username + "</span>");
//		out.append("                    </a>");
//		out.append("");
//		out.append("                    <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow profile\">");
//		out.append("                        <li class=\"dropdown-header\">");
//		out.append("                            <h6>" + username + "</h6>");
//		out.append("                            <span>Người quản lý</span>");
//		out.append("                        </li>");
//		out.append("                        <li>");
//		out.append("                            <hr class=\"dropdown-divider\">");
//		out.append("                        </li>");
//		out.append("");
//		out.append("                        <li>");
//		out.append("                            <a class=\"dropdown-item d-flex align-items-center\" href=\"" + req.getContextPath() + "/ad/profile-management\">");
//		out.append("                                <i class=\"bi bi-person\"></i>");
//		out.append("                                <span>Hồ sơ của tôi</span>");
//		out.append("                            </a>");
//		out.append("                        </li>");
//		out.append("                        <li>");
//		out.append("                            <hr class=\"dropdown-divider\">");
//		out.append("                        </li>");
//		out.append("");
//		out.append("                        <li>");
//		out.append("                            <a class=\"dropdown-item d-flex align-items-center\" href=\"" + req.getContextPath() + "/ad/logout\">");
//		out.append("                                <i class=\"bi bi-box-arrow-right\"></i>");
//		out.append("                                <span>Đăng xuất</span>");
//		out.append("                            </a>");
//		out.append("                        </li>");
//		out.append("");
//		out.append("                    </ul><!-- End Profile Dropdown Items -->");
//		out.append("                </li><!-- End Profile Nav -->");
//		out.append("");
//		out.append("            </ul>");
//		out.append("        </nav><!-- End Icons Navigation -->");
//		out.append("");
//		out.append("    </header><!-- End Header -->");
		
		RequestDispatcher h = req.getRequestDispatcher("/heAd");
		if (h != null) {
			h.include(req, resp);
		}
		
		out.append("");
		out.append("    <!-- ======= Sidebar ======= -->");
		out.append("    <aside id=\"sidebar\" class=\"sidebar\">");
		out.append("");
		out.append("        <ul class=\"sidebar-nav\" id=\"sidebar-nav\">");
		out.append("");
		out.append("            <!-- Dashboard Nav -->");
		out.append("            <li class=\"nav-item\">");
		out.append("                <a class=\"nav-link collapsed\" href=\"" + req.getContextPath() + "/ad/ad-dashboard\">");
		out.append("                    <i class=\"bx bx-category\"></i>");
		out.append("                    <span>Tổng quan</span>");
		out.append("                </a>");
		out.append("            </li>");
		out.append("            <!-- End Dashboard Nav -->");
		out.append("");
		out.append("            <!-- Account Management Nav -->");
		out.append("            <li class=\"nav-item\">");
		out.append("                <a class=\"nav-link collapsed\" href=\"" + req.getContextPath() + "/ad/account-management\">");
		out.append("                    <i class=\"bx bx-user-circle\"></i>");
		out.append("                    <span>Quản lý tài khoản</span>");
		out.append("                </a>");
		out.append("            </li>");
		out.append("            <!-- End Account Management Nav -->");
		out.append("");
		out.append("            <!-- Staff Management Nav -->");
		out.append("            <li class=\"nav-item\">");
		out.append("                <a class=\"nav-link collapsed\" data-bs-target=\"#staff-management-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.append("                    <i class=\"bx bx-user\"></i><span>Quản lý nhân viên</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.append("                </a>");
		out.append("                <ul id=\"staff-management-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">");
		out.append("                    <li>");
		out.append("                        <a href=\"" + req.getContextPath() + "/ad/touroperator-management\">");
		out.append("              <i class=\"bi bi-circle\"></i><span style=\"font-size: 12px;\">Điều hành Tour</span>");
		out.append("                        </a>");
		out.append("                    </li>");
		out.append("                    <li>");
		out.append("                        <a href=\"" + req.getContextPath() + "/ad/tourguide-management\">");
		out.append("              <i class=\"bi bi-circle\"></i><span style=\"font-size: 12px;\">Hướng dẫn viên</span>");
		out.append("                        </a>");
		out.append("                    </li>");
		out.append("                </ul>");
		out.append("            </li>");
		out.append("            <!-- End Staff Management Nav -->");
		out.append("");
		out.append("            <!-- Customer Management Nav -->");
		out.append("            <li class=\"nav-item\">");
		out.append("                <a class=\"nav-link\" href=\"" + req.getContextPath() + "/ad/customer-management\">");
		out.append("                    <i class=\"bx bx-group\"></i>");
		out.append("                    <span>Quản lý khách hàng</span>");
		out.append("                </a>");
		out.append("            </li>");
		out.append("            <!-- End Customer Management Nav -->");
		out.append("");
		out.append("            <!-- Tour Management Nav -->");
		out.append("            <li class=\"nav-item\">");
		out.append("                <a class=\"nav-link collapsed\" href=\"" + req.getContextPath() + "/ad/tour-management\">");
		out.append("                    <i class=\"bx bx-food-menu\"></i>");
		out.append("                    <span>Quản lý Tour</span>");
		out.append("                </a>");
		out.append("            </li>");
		out.append("            <!-- End Tour Management Nav -->");
		out.append("");
		out.append("            <!-- Review Management Nav -->");
		out.append("            <li class=\"nav-item\">");
		out.append("                <a class=\"nav-link collapsed\" href=\"" + req.getContextPath() + "/ad/tourrating-management\">");
		out.append("                    <i class=\"bx bx-message-square-detail\"></i>");
		out.append("                    <span>Duyệt đánh giá</span>");
		out.append("                </a>");
		out.append("            </li>");
		out.append("            <!-- End Review Management Nav -->");
		out.append("        </ul>");
		out.append("");
		out.append("    </aside><!-- End Sidebar-->");
		out.append("");
		out.append("  <main id=\"main\" class=\"main\">");
		out.append("");
		out.append("    <div class=\"pagetitle d-flex\">");
		out.append("      <h1>Quản lý khách hàng</h1>");
		out.append("      <nav class=\"ms-auto\">");
		out.append("        <ol class=\"breadcrumb\">");
		out.append("          <li class=\"breadcrumb-item active\">Quản lý khách hàng</li>");
		out.append("        </ol>");
		out.append("      </nav>");
		out.append("    </div><!-- End Page Title -->");
		out.append("");
		out.append("    <section class=\"section\">");
		out.append("      <div class=\"row\">");
		out.append("        <div class=\"col-lg-12\">");
		out.append("");
		out.append("          <div class=\"card\">");
		out.append("            <div class=\"card-body\">");
		out.append("              <div class=\"d-flex justify-content-between mt-4 mb-3\">");
		out.append("                <div class=\"d-flex align-items-center\">");
		out.append("                  <select id=\"recordCount\" class=\"p-1 ps-2\">");
		out.append("                    <option value=\"5\">5</option>");
		out.append("                    <option value=\"10\" selected>10</option>");
		out.append("                    <option value=\"20\">20</option>");
		out.append("                    <option value=\"all\">Tất cả</option>");
		out.append("                  </select>");
		out.append("                  <label for=\"recordCount\" class=\"ms-2 me-4\">bản ghi / trang</label>");
		out.append("                  <div>");
		out.append("                    <input type=\"text\" id=\"searchInput\" class=\"p-1 ps-2\" placeholder=\"Tìm kiếm...\" />");
		out.append("                  </div>");
		out.append("                </div>");
		out.append("              </div>");
		out.append("");
		out.append("              <!-- Table with stripped rows -->");
		out.append("              <table class=\"table table-hover\" id=\"myTable\">");
		out.append("                <thead>");
		out.append("                  <tr>");
		out.append("                    <th class=\"text-center\">#</th>");
		out.append("                    <th class=\"text-center\">ID</th>");
		out.append("                    <th>Họ và tên</th>");
		out.append("                    <th data-type=\"date\" data-format=\"DD/MM/YYYY\">Ngày sinh</th>");
		out.append("                    <th>Giới tính</th>");
		out.append("                    <th>Quốc tịch</th>");
		out.append("                    <th>CCCD</th>");
		out.append("                    <th>Hộ chiếu</th>");
		out.append("                    <th>Số điện thoại</th>");
		out.append("                    <th class=\"text-center\">Hành động</th>");
		out.append("                  </tr>");
		out.append("                </thead>");
		out.append("                <tbody>");
		
		for (Customer customer : customers) {
			String fullName = customer.getLastname() + " " + customer.getFirstname();
			String gender = customer.getGender() ? "Nam" : "Nữ";
			// Định dạng ngày
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			// Copy
			String copyContent = "Mã khách hàng: " + customer.getId() + "\n"
						+ "Họ và tên: " + fullName +"\n"
						+ "Ngày sinh: " + customer.getDateOfBirth().format(formatter) + "\n"
						+ "Giới tính: " + gender + "\n"
						+ "Quốc tịch: " + customer.getNationality() + "\n"
						+ "CCCD: " + customer.getCitizenId() + "\n"
						+ "Hộ chiếu: " + customer.getPassport() + "\n"
						+ "Số điện thoại: " + customer.getPhoneNumber();
			String encodedContent = StringEscapeUtils.escapeHtml4(copyContent);
			
			out.append("                  <tr>");
			out.append("                    <th class=\"text-center\"></th>");
			out.append("                    <td class=\"text-center\">" + customer.getId() + "</td>");
			out.append("                    <td>" + fullName + "</td>");
			out.append("                    <td>" + customer.getDateOfBirth().format(formatter) + "</td>");
			out.append("                    <td>" + gender + "</td>");
			out.append("                    <td>" + customer.getNationality() + "</td>");
			out.append("                    <td>" + customer.getCitizenId() + "</td>");
			out.append("                    <td>" + customer.getPassport() + "</td>");
			out.append("                    <td>" + customer.getPhoneNumber() + "</td>");
			out.append("                    <td class=\"text-center\">");
			out.append("                      <button type=\"button\" class=\"btn btn-outline-primary btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#moreInforModal-" + customer.getId() + "\"><i class=\"ri-more-fill\"></i></button>");
			out.append("                      <button type=\"button\" class=\"btn btn-primary btn-sm copy-btn\" title=\"Sao chép\" data-content=\"" + encodedContent + "\"><i class=\"ri-file-copy-line\"></i></button>");
			out.append("                    </td>");
			out.append("                  </tr>");
			
			// Modal xem chi tiết từng Customer
			out.append("    <!-- Modal Xem Chi Tiết -->");
			out.append("    <div class=\"modal fade\" id=\"moreInforModal-" + customer.getId() + "\" tabindex=\"-1\" aria-labelledby=\"moreInforModalLabel\" aria-hidden=\"true\">");
			out.append("      <div class=\"modal-dialog modal-md\">");
			out.append("        <div class=\"modal-content\">");
			out.append("          <div class=\"modal-header text-bg-primary\">");
			out.append("            <h5 class=\"modal-title fw-bold\" id=\"moreInforModalLabel\">Thông tin chi tiết khách hàng - " + customer.getId() + "</h5>");
			out.append("            <button type=\"button\" class=\"btn-close fs-2\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
			out.append("          </div>");
			out.append("          <div class=\"modal-body\">");
			out.append("            <div class=\"row\">");
			out.append("              <div class=\"col-md-6 mb-2\">");
			out.append("                <label for=\"lastName\" class=\"form-label\">Họ</label>");
			out.append("                <input type=\"text\" class=\"form-control\" id=\"lastName\" value=\"" + customer.getLastname() + "\" disabled>");
			out.append("              </div>");
			out.append("              <div class=\"col-md-6 mb-2\">");
			out.append("                <label for=\"firstName\" class=\"form-label\">Tên</label>");
			out.append("                <input type=\"text\" class=\"form-control\" id=\"firstName\" value=\"" + customer.getFirstname() + "\" disabled>");
			out.append("              </div>");
			out.append("            </div>");
			out.append("");
			out.append("            <div class=\"row\">");
			out.append("              <div class=\"col-md-6 mb-2\">");
			out.append("                <label for=\"dob\" class=\"form-label\">Ngày sinh</label>");
			out.append("                <input type=\"date\" class=\"form-control\" id=\"dob\" value=\"" + customer.getDateOfBirth() + "\" disabled>");
			out.append("              </div>");
			out.append("              <div class=\"col-md-6 mb-2\">");
			out.append("                <label for=\"gender\" class=\"form-label\">Giới tính</label>");
			out.append("                <input type=\"text\" class=\"form-control\" id=\"gender\" value=\"" + gender + "\" disabled>");
			out.append("              </div>");
			out.append("            </div>");
			out.append("");
			out.append("            <div class=\"row\">");
			out.append("              <div class=\"col-md-6 mb-2\">");
			out.append("                <label for=\"nationality\" class=\"form-label\">Quốc tịch</label>");
			out.append("                <input type=\"text\" class=\"form-control\" id=\"nationality\" value=\"" + customer.getNationality() + "\" disabled>");
			out.append("              </div>");
			out.append("              <div class=\"col-md-6 mb-2\">");
			out.append("                <label for=\"citizenId\" class=\"form-label\">CCCD</label>");
			out.append("                <input type=\"text\" class=\"form-control\" id=\"citizenId\" value=\"" + customer.getCitizenId() + "\" disabled>");
			out.append("              </div>");
			out.append("            </div>");
			out.append("");
			out.append("            <div class=\"row\">");
			out.append("              <div class=\"col-md-6 mb-2\">");
			out.append("                <label for=\"phoneNumber\" class=\"form-label\">Số điện thoại</label>");
			out.append("                <input type=\"text\" class=\"form-control\" id=\"phoneNumber\" value=\"" + customer.getPhoneNumber() + "\" disabled>");
			out.append("              </div>");
			out.append("              <div class=\"col-md-6 mb-2\">");
			out.append("                <label for=\"passport\" class=\"form-label\">Hộ chiếu</label>");
			out.append("                <input type=\"text\" class=\"form-control\" id=\"passport\" value=\"" + customer.getPassport() + "\" disabled>");
			out.append("              </div>");
			out.append("            </div>");
			out.append("");
			out.append("            <div class=\"row\">");
			out.append("              <div class=\"col-md-12 mb-2\">");
			out.append("                <label for=\"address\" class=\"form-label\">Địa chỉ</label>");
			out.append("                <input type=\"text\" class=\"form-control\" id=\"address\" value=\"" + customer.getAddress() + "\" disabled>");
			out.append("              </div>");
			out.append("            </div>");
			out.append("");
			out.append("            <div class=\"row\">");
			out.append("              <div class=\"col-md-12 mb-2\">");
			out.append("                <label for=\"note\" class=\"form-label\">Ghi chú</label>");
			out.append("                <input type=\"text\" class=\"form-control\" id=\"note\" value=\"" + customer.getNote() + "\" disabled>");
			out.append("              </div>");
			out.append("            </div>");
			out.append("          </div>");
			out.append("        </div>");
			out.append("      </div>");
			out.append("    </div>");
		}
		
		out.append("                </tbody>");
		out.append("              </table>");
		out.append("              <!-- End Table with stripped rows -->");
		out.append("");
		out.append("              <div class=\"d-flex align-items-center justify-content-between\">");
		out.append("                <span id=\"recordInfo\"></span>");
		out.append("                <div id=\"pagination\"></div>");
		out.append("              </div>");
		out.append("            </div>");
		out.append("          </div>");
		out.append("");
		out.append("        </div>");
		out.append("      </div>");
		out.append("    </section>");
		out.append("");
		
		
		out.append("    <!-- End Modal -->");
		out.append("  </main><!-- End #main -->");
		out.append("");
		out.append("  <!-- ======= Footer ======= -->");
		out.append("  <footer id=\"footer\" class=\"footer\">");
		out.append("    <div class=\"copyright\">");
		out.append("      &copy; Copyright <strong><span>TravelVN</span></strong>. All Rights Reserved");
		out.append("    </div>");
		out.append("  </footer><!-- End Footer -->");
		out.append("");
		out.append("  <a href=\"#\" class=\"back-to-top d-flex align-items-center justify-content-center\"><i");
		out.append("      class=\"bi bi-arrow-up-short\"></i></a>");
		out.append("");
		out.append("  <!-- JS Customize -->");
		out.append("  <script src=\"/adv/assets/js/dataTableCustom.js\"></script>");
		out.append("");
		
		// Copy nội dung
		out.append("<script>");
		out.append("  document.addEventListener(\"DOMContentLoaded\", function () {");
		out.append("    const copyButtons = document.querySelectorAll(\".copy-btn\");");
		out.append("    copyButtons.forEach(button => {");
		out.append("      button.addEventListener(\"click\", function () {");
		out.append("        const textToCopy = this.getAttribute(\"data-content\");");
		out.append("        if (!textToCopy) return;");
		out.append("        navigator.clipboard.writeText(textToCopy);");
		out.append("      });");
		out.append("    });");
		out.append("  });");
		out.append("</script>");




		out.append("  <!-- Vendor JS Files -->");
		out.append("  <script src=\"/adv/assets/vendor/apexcharts/apexcharts.min.js\"></script>");
		out.append("  <script src=\"/adv/assets/vendor/bootstrap/js/bootstrap.bundle.min.js\"></script>");
		out.append("  <script src=\"/adv/assets/vendor/chart.js/chart.umd.js\"></script>");
		out.append("  <script src=\"/adv/assets/vendor/echarts/echarts.min.js\"></script>");
		out.append("  <script src=\"/adv/assets/vendor/quill/quill.js\"></script>");
		out.append("  <script src=\"/adv/assets/vendor/simple-datatables/simple-datatables.js\"></script>");
		out.append("  <script src=\"/adv/assets/vendor/tinymce/tinymce.min.js\"></script>");
		out.append("  <script src=\"/adv/assets/vendor/php-email-form/validate.js\"></script>");
		out.append("");
		out.append("  <!-- Template Main JS File -->");
		out.append("  <script src=\"/adv/assets/js/main.js\"></script>");
		out.append("");
		out.append("</body>");
		out.append("");
		out.append("</html>");
	}
}

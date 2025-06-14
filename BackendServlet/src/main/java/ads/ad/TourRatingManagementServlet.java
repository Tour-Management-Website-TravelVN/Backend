package ads.ad;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import ads.objects.TourOperator;
import ads.objects.TourRating;
import ads.user.AdministratorFunction;
import ads.user.AdministratorFunctionImpl;
import ads.user.TourRatingFunction;
import ads.user.TourRatingFunctionImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ad/tourrating-management")
public class TourRatingManagementServlet extends HttpServlet {
	private TourRatingFunction tourRatingFunction = new TourRatingFunctionImpl();
	private AdministratorFunction administratorFunction = new AdministratorFunctionImpl();

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
		
		String statusFilter = req.getParameter("statusFilter");
        if (statusFilter == null) {
            statusFilter = "all";
        }
        
        ArrayList<TourRating> tourRatings = new ArrayList<TourRating>();
        
        if("all".equals(statusFilter)) {
            tourRatings = tourRatingFunction.getFirst100TourRatings();
        }
        else if("pending".equals(statusFilter)) {
        	tourRatings = tourRatingFunction.getTourRatingsByStatus("Chờ duyệt");
        }
        else if("approved".equals(statusFilter)) {
        	tourRatings = tourRatingFunction.getTourRatingsByStatus("Duyệt");
        }
        else if("rejected".equals(statusFilter)) {
        	tourRatings = tourRatingFunction.getTourRatingsByStatus("Từ chối duyệt");
        }
		
		PrintWriter out = resp.getWriter();
		
		out.append("<!DOCTYPE html>");
		out.append("<html lang=\"en\">");
		out.append("");
		out.append("<head>");
		out.append("  <meta charset=\"utf-8\">");
		out.append("  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">");
		out.append("");
		out.append("  <title>TravelVN - Quản lý đánh giá</title>");
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
		out.append("                <a class=\"nav-link collapsed\" href=\"" + req.getContextPath() + "/ad/customer-management\">");
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
		out.append("                <a class=\"nav-link\" href=\"" + req.getContextPath() + "/ad/tourrating-management\">");
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
		out.append("      <h1>Quản lý đánh giá</h1>");
		out.append("      <nav class=\"ms-auto\">");
		out.append("        <ol class=\"breadcrumb\">");
		out.append("          <li class=\"breadcrumb-item active\">Quản lý đánh giá</li>");
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
		out.append("                <div class=\"d-flex align-items-center justify-content-center\">");
		out.append("                  <select id=\"recordCount\" class=\"p-1\">");
		out.append("                    <option value=\"5\">5</option>");
		out.append("                    <option value=\"10\" selected>10</option>");
		out.append("                    <option value=\"20\">20</option>");
		out.append("                    <option value=\"all\">Tất cả</option>");
		out.append("                  </select>");
		out.append("                  <label for=\"recordCount\" class=\"ms-2 me-4\">bản ghi / trang</label>");
		out.append("");
		out.append("                  <div>");
		out.append("                    <input type=\"text\" id=\"searchInput\" class=\"p-1 ps-2\" placeholder=\"Tìm kiếm...\" />");
		out.append("                  </div>");
		out.append("");
		out.append("                  <label for=\"ratingStatusFilter\" class=\"ms-4 me-2\">Trạng thái: </label>");
		out.append("                  <select id=\"ratingStatusFilter\" class=\"p-1\" onchange=\"location.href='ad-tourrating-management?statusFilter='+this.value\">");
		out.append("                    <option value=\"all\" " + ("all".equals(statusFilter) ? "selected" : "") + ">Tất cả</option>");
		out.append("                    <option value=\"pending\" " + ("pending".equals(statusFilter) ? "selected" : "") + ">Chờ duyệt</option>");
		out.append("                    <option value=\"approved\" " + ("approved".equals(statusFilter) ? "selected" : "") + ">Đã duyệt</option>");
		out.append("                    <option value=\"rejected\" " + ("rejected".equals(statusFilter) ? "selected" : "") + ">Từ chối duyệt</option>");
		out.append("                  </select>");
		out.append("                </div>");
		out.append("              </div>");
		out.append("");
		out.append("              <!-- Table with stripped rows -->");
		out.append("              <table class=\"table table-hover\" id=\"myTable\">");
		out.append("                <thead>");
		out.append("                  <tr>");
		out.append("                    <th class=\"text-center\">#</th>");
		out.append("                    <th class=\"text-center\">ID</th>");
		out.append("                    <th>Đơn vị Tour</th>");
		out.append("                    <th class=\"text-center\">Khách hàng</th>");
		out.append("                    <th class=\"text-center\">Điểm đánh giá</th>");
		out.append("                    <th>Bình luận</th>");
		out.append("                    <th>Trạng thái</th>");
		out.append("                    <th class=\"text-center\">Hành động</th>");
		out.append("                  </tr>");
		out.append("                </thead>");
		out.append("                <tbody>");
		
		for (TourRating tourRating : tourRatings) {
			out.append("                  <tr>");
			out.append("                    <th class=\"text-center\"></th>");
			out.append("                    <td class=\"text-center\">" + tourRating.getId() + "</td>");
			out.append("                    <td>" + tourRating.getTourUnit().getTourUnitId() + "</td>");
			out.append("                    <td class=\"text-center\">" + tourRating.getC().getId() + "</td>");
			out.append("                    <td class=\"text-center\">" + tourRating.getRatingValue() + "</td>");
			out.append("                    <td>" + tourRating.getComment() + "</td>");
			out.append("                    <td>" + tourRating.getStatus() + "</td>");
			out.append("                    <td class=\"text-center\">");
			out.append("                      <form action=\"" + req.getContextPath() + "/ad/tourrating-management/set-status\" method=\"POST\">");
			out.append("                        <button type=\"button\" class=\"btn btn-outline-primary btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#moreInforModal-" + tourRating.getId() + "\"><i class=\"ri-more-fill\"></i></button>");
			out.append("                        <input type=\"hidden\" name=\"tourRatingId\" value=\"" + tourRating.getId() + "\">");
			out.append("                        <input type=\"hidden\" name=\"administratorId\" value=\"" + administratorFunction.getAdministratorByUsername(username).getId() + "\">");
			out.append("                        <button type=\"submit\" class=\"btn btn-success btn-sm\" name=\"action\" value=\"approved\"><i class=\"ri-check-fill\"></i></button>");
			out.append("                        <button type=\"submit\" class=\"btn btn-danger btn-sm\" name=\"action\" value=\"rejected\"><i class=\"ri-close-fill\"></i></button>");
			out.append("                      </form>");
			out.append("                    </td>");
			out.append("                  </tr>");
			
			// Modal xem chi tiết từng đánh giá
			out.append("    <!-- Modal Xem Chi Tiết -->");
			out.append("    <div class=\"modal fade\" id=\"moreInforModal-" + tourRating.getId() + "\" tabindex=\"-1\" aria-labelledby=\"moreInforModalLabel\" aria-hidden=\"true\">");
			out.append("      <div class=\"modal-dialog modal-md\">");
			out.append("        <div class=\"modal-content\">");
			out.append("          <div class=\"modal-header text-bg-primary\">");
			out.append("            <h5 class=\"modal-title fw-bold\" id=\"moreInforModalLabel\">Thông tin chi tiết đánh giá</h5>");
			out.append("            <button type=\"button\" class=\"btn-close fs-2\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
			out.append("          </div>");
			out.append("          <div class=\"modal-body\">");
			out.append("            <div class=\"row\">");
			out.append("              <div class=\"col-md-6 mb-2\">");
			out.append("                <label for=\"ratingId\" class=\"form-label\">Mã đánh giá</label>");
			out.append("                <input type=\"text\" class=\"form-control\" id=\"ratingId\" value=\"" + tourRating.getId() + "\" disabled>");
			out.append("              </div>");
			out.append("              <div class=\"col-md-6 mb-2\">");
			out.append("                <label for=\"administratorId\" class=\"form-label\">Người duyệt</label>");
			out.append("                <input type=\"text\" class=\"form-control\" id=\"administratorId\" value=\"" + tourRating.getAdministrator().getId() + "\" disabled>");
			out.append("              </div>");
			out.append("            </div>");
			out.append("");
			out.append("            <div class=\"row\">");
			out.append("              <div class=\"col-md-6 mb-2\">");
			out.append("                <label for=\"customerId\" class=\"form-label\">Khách hàng</label>");
			out.append("                <input type=\"text\" class=\"form-control\" id=\"customerId\" value=\"" + tourRating.getC().getId() + "\" disabled>");
			out.append("              </div>");
			out.append("              <div class=\"col-md-6 mb-2\">");
			out.append("                <label for=\"tourUnitId\" class=\"form-label\">Đơn vị Tour</label>");
			out.append("                <input type=\"text\" class=\"form-control\" id=\"tourUnitId\" value=\"" + tourRating.getTourUnit().getTourUnitId() + "\" disabled>");
			out.append("              </div>");
			out.append("            </div>");
			out.append("");
			out.append("            <div class=\"row\">");
			out.append("              <div class=\"col-md-6 mb-2\">");
			out.append("                <label for=\"ratingValue\" class=\"form-label\">Điểm đánh giá</label>");
			out.append("                <input type=\"text\" class=\"form-control\" id=\"ratingValue\" value=\"" + tourRating.getRatingValue() + "\" disabled>");
			out.append("              </div>");
			out.append("              <div class=\"col-md-6 mb-2\">");
			out.append("                <label for=\"status\" class=\"form-label\">Trạng thái</label>");
			out.append("                <input type=\"text\" class=\"form-control\" id=\"phoneNumber\" value=\"" + tourRating.getStatus() + "\" disabled>");
			out.append("              </div>");
			out.append("            </div>");
			out.append("");
			out.append("            <div class=\"row\">");
			out.append("              <div class=\"col-md-12\">");
			out.append("                <label for=\"comment\" class=\"form-label\">Bình luận</label>");
			out.append("                <textarea class=\"form-control\" id=\"comment\" rows=\"4\" disabled>" + tourRating.getComment() + "</textarea>");
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

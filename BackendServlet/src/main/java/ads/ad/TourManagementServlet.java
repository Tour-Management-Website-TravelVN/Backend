package ads.ad;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import ads.objects.Category;
import ads.objects.Image;
import ads.objects.Tour;
import ads.objects.UserAccount;
import ads.user.CategoryFunction;
import ads.user.CategoryFunctionImpl;
import ads.user.TourFunction;
import ads.user.TourFunctionImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ad-tour-management")
public class TourManagementServlet extends HttpServlet {
	private TourFunction tourFunction = new TourFunctionImpl();
	private CategoryFunction categoryFunction = new CategoryFunctionImpl();

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
		
		// Tất cả danh mục
		ArrayList<Category> categories = categoryFunction.getAllCategories();
		
		// Danh sách Tour theo CategoryId
		String categoryIdParam = req.getParameter("categoryId");
		ArrayList<Tour> tours;

		if (categoryIdParam != null && !categoryIdParam.isEmpty()) {
			try {
				int categoryId = Integer.parseInt(categoryIdParam);
				tours = tourFunction.getToursByCategoryId(categoryId);
			} catch (NumberFormatException e) {
				tours = tourFunction.getAllTours();
			}
		} else {
			tours = tourFunction.getAllTours();
		}
		
		PrintWriter out = resp.getWriter();
		
		out.append("<!DOCTYPE html>");
		out.append("<html lang=\"en\">");
		out.append("");
		out.append("<head>");
		out.append("  <meta charset=\"utf-8\">");
		out.append("  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">");
		out.append("");
		out.append("  <title>TravelVN - Quản lý Tour</title>");
		out.append("  <meta content=\"\" name=\"description\">");
		out.append("  <meta content=\"\" name=\"keywords\">");
		out.append("");
		out.append("  <!-- Favicons -->");
		out.append("  <link href=\"assets/img/Logo.svg\" rel=\"icon\">");
		out.append("  <link href=\"assets/img/apple-touch-icon.png\" rel=\"apple-touch-icon\">");
		out.append("");
		out.append("  <!-- Google Fonts -->");
		out.append("  <link href=\"https://fonts.gstatic.com\" rel=\"preconnect\">");
		out.append("  <link");
		out.append("    href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i\"");
		out.append("    rel=\"stylesheet\">");
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
		out.append("        <a class=\"nav-link collapsed\" href=\"" + req.getContextPath() + "/ad-account-management\">");
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
		out.append("        <a class=\"nav-link\" href=\"" + req.getContextPath() + "/ad-tour-management\">");
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
		out.append("      <h1>Danh sách Tour</h1>");
		out.append("      <nav class=\"ms-auto\">");
		out.append("        <ol class=\"breadcrumb\">");
		out.append("          <li class=\"breadcrumb-item active\">Danh sách Tour</li>");
		out.append("        </ol>");
		out.append("      </nav>");
		out.append("    </div><!-- End Page Title -->");
		out.append("");
		out.append("    <section class=\"section\">");
		out.append("      <div class=\"row\">");
		out.append("        <div class=\"col-lg-12\">");
		out.append("          <label for=\"categoryFilter\" class=\"me-2\">Danh mục: </label>");
		
		out.append("          <select id=\"categoryFilter\" class=\"p-1 mb-3\">");
		String selectedAll = "";
		if (req.getParameter("categoryId") == null || req.getParameter("categoryId").equals("all")) {
		    selectedAll = "selected";
		}
		out.append("            <option value=\"all\" " + selectedAll + ">Tất cả</option>");
		for (Category category : categories) {
		    String selected = "";
		    if (req.getParameter("categoryId") != null && req.getParameter("categoryId").equals(String.valueOf(category.getId()))) {
		        selected = "selected";
		    }
		    out.append("            <option value=\"" + category.getId() + "\" " + selected + ">" + category.getCategoryName() + "</option>");
		}
		out.append("          </select>");

		
		// Xử lý chọn
		out.append("<script>");
		out.append("  document.getElementById('categoryFilter').addEventListener('change', function() {");
		out.append("    const selectedCategoryId = this.value;");
		out.append("    window.location.href = '" + req.getRequestURI() + "?categoryId=' + selectedCategoryId;");
		out.append("  });");
		out.append("</script>");
		
		
		for (Tour tour : tours) {
			String imageUrl = "";
			String imageName = "";
			if (tour.getImageSet() != null && !tour.getImageSet().isEmpty()) {
			    Image firstImage = tour.getImageSet().iterator().next();
			    imageUrl = firstImage.getUrl();
			    imageName = firstImage.getImageName();
			}
			String tourName = tour.getTourName();
			String tourId = tour.getTourId();
			String categoryName = tour.getCategory().getCategoryName();
			String tourOperatorName = tour.getTourOperator().getLastname() + " " + tour.getTourOperator().getFirstname();
			
			out.append("          <a class=\"card mb-3 text-decoration-none text-dark\" style=\"cursor: pointer;\">");
			out.append("            <div class=\"row g-0\">");
			out.append("              <div class=\"col-md-2\">");
			out.append("                <img src=\"" + imageUrl + "\" class=\"img-fluid rounded-start\" alt=\"" + imageName + "\">");
			out.append("              </div>");
			out.append("              <div class=\"col-md-10\">");
			out.append("                <div class=\"card-body pb-0\">");
			out.append("                  <h5 class=\"mt-3 fw-semibold mb-1\" title=\"" + tourName + "\" style=\"max-width: 1000px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;\">" + tourName + "</h5>");
			out.append("");
			out.append("                  <div class=\"d-flex\">");
			out.append("                    <span class=\"me-2\">ID:</span>");
			out.append("                    <span>" + tourId + "</span>");
			out.append("                  </div>");
			out.append("");
			out.append("                  <div class=\"d-flex\">");
			out.append("                    <span class=\"me-2\">Danh mục:</span>");
			out.append("                    <span>" + categoryName + "</span>");
			out.append("                  </div>");
			out.append("");
			out.append("                  <div class=\"d-flex\">");
			out.append("                    <span class=\"me-2\">Người điều hành:</span>");
			out.append("                    <span>" + tourOperatorName + "</span>");
			out.append("                  </div>");
			out.append("");
			out.append("                </div>");
			out.append("              </div>");
			out.append("            </div>");
			out.append("          </a>");
		}
		
//		out.append("          <a class=\"card mb-3 text-decoration-none text-dark\" style=\"cursor: pointer;\">");
//		out.append("            <div class=\"row g-0\">");
//		out.append("              <div class=\"col-md-2\">");
//		out.append("                <img src=\"assets/img/card.png\" class=\"img-fluid rounded-start\" alt=\"...\">");
//		out.append("              </div>");
//		out.append("              <div class=\"col-md-10\">");
//		out.append("                <div class=\"card-body pb-0\">");
//		out.append("                  <h5 class=\"mt-3 fw-semibold mb-1\">Hà Nội - Sapa - Hà Khẩu - Check In Nóc Nhà Đông Dương Fansipan</h5>");
//		out.append("");
//		out.append("                  <div class=\"d-flex\">");
//		out.append("                    <span class=\"me-2\">ID:</span>");
//		out.append("                    <span>T001-1440-2N1D</span>");
//		out.append("                  </div>");
//		out.append("");
//		out.append("                  <div class=\"d-flex\">");
//		out.append("                    <span class=\"me-2\">Danh mục:</span>");
//		out.append("                    <span>Du lịch biển</span>");
//		out.append("                  </div>");
//		out.append("");
//		out.append("                  <div class=\"d-flex\">");
//		out.append("                    <span class=\"me-2\">Người điều hành:</span>");
//		out.append("                    <span>Phạm Việt Long</span>");
//		out.append("                  </div>");
//		out.append("");
//		out.append("                </div>");
//		out.append("              </div>");
//		out.append("            </div>");
//		out.append("          </a>");
		
		
		out.append("");
		out.append("        </div>");
		out.append("      </div>");
		out.append("    </section>");
		out.append("");
		out.append("");
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

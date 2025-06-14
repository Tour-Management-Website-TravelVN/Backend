package ads.to;

import java.awt.List;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import ads.user.TourFunctionImpl;
import ads.util.AmountOfCustomerPredictor;
import ads.util.LoadData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;

@WebServlet("/to/tour/ad-dashboard")
public class DashboardServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 resp.setCharacterEncoding("UTF-8");
		 Instances trainingData;
			try {
				trainingData = LoadData.getCategoryYearlyBookingData();
			 Instances inputData = LoadData.getInputDataForCurrentYear();

			 LinearRegression model = AmountOfCustomerPredictor.trainModel(trainingData);

			  
			
		    String username = "kdt";

		    String filter = req.getParameter("filter");

		    resp.setContentType("application/json");
		    PrintWriter out = resp.getWriter();
		    Gson gson = new Gson();

		    if ("year".equals(filter) || "month".equals(filter) || "day".equals(filter)) {
		        ArrayList<Map<String, Object>> stats = (ArrayList<Map<String, Object>>) TourFunctionImpl.getInstance().getStatsForEchart(filter);
		        out.print(gson.toJson(stats));
		        out.flush();
		        return;
		    }

		    if ("nextyear".equals(filter)) {
		         ArrayList<Map<String, Double>> prediction =  AmountOfCustomerPredictor.predictNextYearBookings(model, inputData);
		         ArrayList<Map<String, Object>> finalResult = new ArrayList<>();

		         for (Map<String, Double> entry : prediction) {
		             for (Map.Entry<String, Double> e : entry.entrySet()) {
		                 Map<String, Object> map = new HashMap<>();
		                 map.put("category", e.getKey()); // String
		                 map.put("total", e.getValue().intValue()); // Chuyển từ Double → Integer
		                 finalResult.add(map);
		             }
		         }
		        out.print(gson.toJson(finalResult));
		        out.flush();
		        return;
		    }

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    // ✅ Trả HTML
		    resp.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = resp.getWriter(); // ✅ Chỉ mở writer khi chắc chắn là HTML

		    out.append("<!DOCTYPE html>");
		    out.append("<html lang=\"en\">");
		out.append("");
		out.append("<head>");
		out.append("    <meta charset=\"utf-8\">");
		out.append("    <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">");
		out.append("");
		out.append("    <title>TravelVN - Báo cáo thống kê</title>");
		out.append("    <meta content=\"\" name=\"description\">");
		out.append("    <meta content=\"\" name=\"keywords\">");
		out.append("");
		out.append("    <!-- Favicons -->");
		out.append("    <link href=\"assets/img/favicon.png\" rel=\"icon\">");
		out.append("    <link href=\"assets/img/apple-touch-icon.png\" rel=\"apple-touch-icon\">");
		out.append("");
		out.append("    <!-- Google Fonts -->");
		out.append("    <link href=\"https://fonts.gstatic.com\" rel=\"preconnect\">");
		out.append("    <link");
		out.append(
				"        href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i\"");
		out.append("        rel=\"stylesheet\">");
		out.append("");
		out.append("  <!-- Vendor CSS Files -->");
		out.append("  <link href=\"../../assets/vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"../../assets/vendor/bootstrap-icons/bootstrap-icons.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"../../assets/vendor/boxicons/css/boxicons.min.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"../../assets/vendor/quill/quill.snow.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"../../assets/vendor/quill/quill.bubble.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"../../assets/vendor/remixicon/remixicon.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"../../assets/vendor/simple-datatables/style.css\" rel=\"stylesheet\">");
		out.append("");
		out.append("");
		out.append("  <!-- Template Main CSS File -->");
		out.append("  <link href=\"../../css/style.css\" rel=\"stylesheet\">");

		out.append("</head>");
		out.append("");
		out.append("<body>");
		out.append("");
		out.append("    <!-- ======= Header ======= -->");
		out.append("    <header id=\"header\" class=\"header fixed-top d-flex align-items-center\">");
		out.append("");
		out.append("        <div class=\"d-flex align-items-center justify-content-between\">");
		out.append("            <a href=\"index.html\" class=\"logo d-flex align-items-center\">");
		out.append("                <img src=\"assets/img/logo.png\" alt=\"\">");
		out.append("                <span class=\"d-none d-lg-block\">TravelVN</span>");
		out.append("            </a>");
		out.append("            <i class=\"bi bi-list toggle-sidebar-btn\"></i>");
		out.append("        </div><!-- End Logo -->");
		out.append("");
		out.append("        <div class=\"search-bar\">");
		out.append("            <form class=\"search-form d-flex align-items-center\" method=\"POST\" action=\"#\">");
		out.append(
				"                <input type=\"text\" name=\"query\" placeholder=\"Tìm kiếm\" title=\"Enter search keyword\">");
		out.append("                <button type=\"submit\" title=\"Search\"><i class=\"bi bi-search\"></i></button>");
		out.append("            </form>");
		out.append("        </div><!-- End Search Bar -->");
		out.append("");
		out.append("        <nav class=\"header-nav ms-auto\">");
		out.append("            <ul class=\"d-flex align-items-center\">");
		out.append("");
		out.append("                <li class=\"nav-item d-block d-lg-none\">");
		out.append("                    <a class=\"nav-link nav-icon search-bar-toggle \" href=\"#\">");
		out.append("                        <i class=\"bi bi-search\"></i>");
		out.append("                    </a>");
		out.append("                </li><!-- End Search Icon-->");
		out.append("");
		out.append("                <li class=\"nav-item dropdown\">");
		out.append("");
		out.append("                    <a class=\"nav-link nav-icon\" href=\"#\" data-bs-toggle=\"dropdown\">");
		out.append("                        <i class=\"bi bi-bell\"></i>");
		out.append("                        <span class=\"badge bg-primary badge-number\">4</span>");
		out.append("                    </a><!-- End Notification Icon -->");
		out.append("");
		out.append(
				"                    <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow notifications\">");
		out.append("                        <li class=\"dropdown-header\">");
		out.append("                            Bạn có 4 thông báo mới");
		out.append(
				"                            <a href=\"#\"><span class=\"badge rounded-pill bg-primary p-2 ms-2\">Xem tất cả</span></a>");
		out.append("                        </li>");
		out.append("                        <li>");
		out.append("                            <hr class=\"dropdown-divider\">");
		out.append("                        </li>");
		out.append("");
		out.append("                        <li class=\"notification-item\">");
		out.append("                            <i class=\"bi bi-exclamation-circle text-warning\"></i>");
		out.append("                            <div>");
		out.append("                                <h4>Lorem Ipsum</h4>");
		out.append("                                <p>Quae dolorem earum veritatis oditseno</p>");
		out.append("                                <p>30 phút trước</p>");
		out.append("                            </div>");
		out.append("                        </li>");
		out.append("");
		out.append("                        <li>");
		out.append("                            <hr class=\"dropdown-divider\">");
		out.append("                        </li>");
		out.append("");
		out.append("                        <li class=\"notification-item\">");
		out.append("                            <i class=\"bi bi-x-circle text-danger\"></i>");
		out.append("                            <div>");
		out.append("                                <h4>Atque rerum nesciunt</h4>");
		out.append("                                <p>Quae dolorem earum veritatis oditseno</p>");
		out.append("                                <p>1 giờ trước</p>");
		out.append("                            </div>");
		out.append("                        </li>");
		out.append("");
		out.append("                        <li>");
		out.append("                            <hr class=\"dropdown-divider\">");
		out.append("                        </li>");
		out.append("");
		out.append("                        <li class=\"notification-item\">");
		out.append("                            <i class=\"bi bi-check-circle text-success\"></i>");
		out.append("                            <div>");
		out.append("                                <h4>Sit rerum fuga</h4>");
		out.append("                                <p>Quae dolorem earum veritatis oditseno</p>");
		out.append("                                <p>2 giờ trước</p>");
		out.append("                            </div>");
		out.append("                        </li>");
		out.append("");
		out.append("                        <li>");
		out.append("                            <hr class=\"dropdown-divider\">");
		out.append("                        </li>");
		out.append("");
		out.append("                        <li class=\"notification-item\">");
		out.append("                            <i class=\"bi bi-info-circle text-primary\"></i>");
		out.append("                            <div>");
		out.append("                                <h4>Dicta reprehenderit</h4>");
		out.append("                                <p>Quae dolorem earum veritatis oditseno</p>");
		out.append("                                <p>4 giờ trước</p>");
		out.append("                            </div>");
		out.append("                        </li>");
		out.append("");
		out.append("                        <li>");
		out.append("                            <hr class=\"dropdown-divider\">");
		out.append("                        </li>");
		out.append("                        <li class=\"dropdown-footer\">");
		out.append("                            <a href=\"#\">Hiển thị tất cả thông báo</a>");
		out.append("                        </li>");
		out.append("");
		out.append("                    </ul><!-- End Notification Dropdown Items -->");
		out.append("");
		out.append("                </li><!-- End Notification Nav -->");
		out.append("");
		out.append("                <li class=\"nav-item dropdown\">");
		out.append("");
		out.append("                    <a class=\"nav-link nav-icon\" href=\"#\" data-bs-toggle=\"dropdown\">");
		out.append("                        <i class=\"bi bi-chat-left-text\"></i>");
		out.append("                        <span class=\"badge bg-success badge-number\">3</span>");
		out.append("                    </a><!-- End Messages Icon -->");
		out.append("");
		out.append("                    <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow messages\">");
		out.append("                        <li class=\"dropdown-header\">");
		out.append("                            Bạn có 3 tin nhắn mới");
		out.append(
				"                            <a href=\"#\"><span class=\"badge rounded-pill bg-primary p-2 ms-2\">Xem tất cả</span></a>");
		out.append("                        </li>");
		out.append("                        <li>");
		out.append("                            <hr class=\"dropdown-divider\">");
		out.append("                        </li>");
		out.append("");
		out.append("                        <li class=\"message-item\">");
		out.append("                            <a href=\"#\">");
		out.append(
				"                                <img src=\"assets/img/messages-1.jpg\" alt=\"\" class=\"rounded-circle\">");
		out.append("                                <div>");
		out.append("                                    <h4>Maria Hudson</h4>");
		out.append(
				"                                    <p>Velit asperiores et ducimus soluta repudiandae labore officia est ut...</p>");
		out.append("                                    <p>4 giờ trước</p>");
		out.append("                                </div>");
		out.append("                            </a>");
		out.append("                        </li>");
		out.append("                        <li>");
		out.append("                            <hr class=\"dropdown-divider\">");
		out.append("                        </li>");
		out.append("");
		out.append("                        <li class=\"message-item\">");
		out.append("                            <a href=\"#\">");
		out.append(
				"                                <img src=\"assets/img/messages-2.jpg\" alt=\"\" class=\"rounded-circle\">");
		out.append("                                <div>");
		out.append("                                    <h4>Anna Nelson</h4>");
		out.append(
				"                                    <p>Velit asperiores et ducimus soluta repudiandae labore officia est ut...</p>");
		out.append("                                    <p>6 giờ trước</p>");
		out.append("                                </div>");
		out.append("                            </a>");
		out.append("                        </li>");
		out.append("                        <li>");
		out.append("                            <hr class=\"dropdown-divider\">");
		out.append("                        </li>");
		out.append("");
		out.append("                        <li class=\"message-item\">");
		out.append("                            <a href=\"#\">");
		out.append(
				"                                <img src=\"assets/img/messages-3.jpg\" alt=\"\" class=\"rounded-circle\">");
		out.append("                                <div>");
		out.append("                                    <h4>David Muldon</h4>");
		out.append(
				"                                    <p>Velit asperiores et ducimus soluta repudiandae labore officia est ut...</p>");
		out.append("                                    <p>8 giờ trước</p>");
		out.append("                                </div>");
		out.append("                            </a>");
		out.append("                        </li>");
		out.append("                        <li>");
		out.append("                            <hr class=\"dropdown-divider\">");
		out.append("                        </li>");
		out.append("");
		out.append("                        <li class=\"dropdown-footer\">");
		out.append("                            <a href=\"#\">Hiển thị tất cả tin nhắn</a>");
		out.append("                        </li>");
		out.append("");
		out.append("                    </ul><!-- End Messages Dropdown Items -->");
		out.append("");
		out.append("                </li><!-- End Messages Nav -->");
		out.append("");
		out.append("                <li class=\"nav-item dropdown pe-3\">");
		out.append("");
		out.append(
				"                    <a class=\"nav-link nav-profile d-flex align-items-center pe-0\" href=\"#\" data-bs-toggle=\"dropdown\">");
		out.append(
				"                        <img src=\"assets/img/profile-img.jpg\" alt=\"Profile\" class=\"rounded-circle\">");
		out.append("                        <span class=\"d-none d-md-block dropdown-toggle ps-2\"> kdt</span>");
		out.append("                    </a><!-- End Profile Iamge Icon -->");
		out.append("");
		out.append("                    <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow profile\">");
		out.append("                        <li class=\"dropdown-header\">");
		out.append("                            <h6>kdt</h6>");
		out.append("                            <span>Người quản lý</span>");
		out.append("                        </li>");
		out.append("                        <li>");
		out.append("                            <hr class=\"dropdown-divider\">");
		out.append("                        </li>");
		out.append("");
		out.append("                        <li>");
		out.append(
				"                            <a class=\"dropdown-item d-flex align-items-center\" href=\"users-profile.html\">");
		out.append("                                <i class=\"bi bi-person\"></i>");
		out.append("                                <span>Hồ sơ của tôi</span>");
		out.append("                            </a>");
		out.append("                        </li>");
		out.append("                        <li>");
		out.append("                            <hr class=\"dropdown-divider\">");
		out.append("                        </li>");
		out.append("");
		out.append("                        <li>");
		out.append("                            <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">");
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
		out.append("    <!-- ======= Sidebar ======= -->");
		out.append("    <aside id=\"sidebar\" class=\"sidebar\">");
		out.append("");
		out.append("        <ul class=\"sidebar-nav\" id=\"sidebar-nav\">");
		out.append("");
		out.append("            <!-- Dashboard Nav -->");
		out.append("            <li class=\"nav-item\">");
		out.append("                <a class=\"nav-link\" href=\"/Backend/ad-dashboard\">");
		out.append("                    <i class=\"bx bx-category\"></i>");
		out.append("                    <span>Bảng điều khiển</span>");
		out.append("                </a>");
		out.append("            </li>");
		out.append("            <!-- End Dashboard Nav -->");
		out.append("");
		out.append("            <!-- Account Management Nav -->");
		out.append("            <li class=\"nav-item\">");
		out.append("                <a class=\"nav-link collapsed\" href=\"/Backend/ad-account-management\">");
		out.append("                    <i class=\"bx bx-user-circle\"></i>");
		out.append("                    <span>Quản lý tài khoản</span>");
		out.append("                </a>");
		out.append("            </li>");
		out.append("            <!-- End Account Management Nav -->");
		out.append("");
		out.append("            <!-- Staff Management Nav -->");
		out.append("            <li class=\"nav-item\">");
		out.append(
				"                <a class=\"nav-link collapsed\" data-bs-target=\"#staff-management-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.append(
				"                    <i class=\"bx bx-user\"></i><span>Quản lý nhân viên</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.append("                </a>");
		out.append(
				"                <ul id=\"staff-management-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">");
		out.append("                    <li>");
		out.append("                        <a href=\"staff-management-tour-operator.html\">");
		out.append("                            <i class=\"bi bi-circle\"></i><span>Người điều hành Tour</span>");
		out.append("                        </a>");
		out.append("                    </li>");
		out.append("                    <li>");
		out.append("                        <a href=\"staff-management-tour-guide.html\">");
		out.append("                            <i class=\"bi bi-circle\"></i><span>Hướng dẫn viên du lịch</span>");
		out.append("                        </a>");
		out.append("                    </li>");
		out.append("                </ul>");
		out.append("            </li>");
		out.append("            <!-- End Staff Management Nav -->");
		out.append("");
		out.append("            <!-- Customer Management Nav -->");
		out.append("            <li class=\"nav-item\">");
		out.append("                <a class=\"nav-link collapsed\" href=\"customer-management.html\">");
		out.append("                    <i class=\"bx bx-group\"></i>");
		out.append("                    <span>Quản lý khách hàng</span>");
		out.append("                </a>");
		out.append("            </li>");
		out.append("            <!-- End Customer Management Nav -->");
		out.append("");
		out.append("            <!-- Tour Management Nav -->");
		out.append("            <li class=\"nav-item\">");
		out.append("                <a class=\"nav-link collapsed\" href=\"tour-management.html\">");
		out.append("                    <i class=\"bx bx-food-menu\"></i>");
		out.append("                    <span>Quản lý Tour</span>");
		out.append("                </a>");
		out.append("            </li>");
		out.append("            <!-- End Tour Management Nav -->");
		out.append("");
		out.append("            <!-- Report Nav -->");
		out.append("            <li class=\"nav-item\">");
		out.append("                <a class=\"nav-link collapsed\" href=\"report.html\">");
		out.append("                    <i class=\"bx bx-bar-chart-alt-2\"></i>");
		out.append("                    <span>Báo cáo thống kê</span>");
		out.append("                </a>");
		out.append("            </li>");
		out.append("            <!-- End Report Nav -->");
		out.append("");
		out.append("            <!-- Review Management Nav -->");
		out.append("            <li class=\"nav-item\">");
		out.append("                <a class=\"nav-link collapsed\" href=\"review-management.html\">");
		out.append("                    <i class=\"bx bx-message-square-detail\"></i>");
		out.append("                    <span>Duyệt đánh giá</span>");
		out.append("                </a>");
		out.append("            </li>");
		out.append("            <!-- End Review Management Nav -->");
		out.append("        </ul>");
		out.append("");
		out.append("    </aside><!-- End Sidebar-->");
		out.append("");
		out.append("    <main id=\"main\" class=\"main\">");
		out.append("");
		out.append("        <div class=\"pagetitle d-flex\">");
		out.append("            <h1>Bảng điều khiển</h1>");
		out.append("            <nav class=\"ms-auto\">");
		out.append("                <ol class=\"breadcrumb\">");
		out.append("                    <li class=\"breadcrumb-item\"><a href=\"index.html\">Trang chủ</li></a></li>");
		out.append("                    <li class=\"breadcrumb-item active\">Bảng điều khiển</li>");
		out.append("                </ol>");
		out.append("            </nav>");
		out.append("        </div><!-- End Page Title -->");
		out.append("");
		out.append("        <section class=\"section dashboard\">");
		out.append("            <!-- 3 Card -->");
		out.append("            <div class=\"row\">");
		out.append("                <!-- Lượng đặt Tour Card -->");
		out.append("                <div class=\"col-xxl-4 col-md-6\">");
		out.append("                    <div class=\"card info-card sales-card\">");
		out.append("");
		out.append("                        <div class=\"filter\">");
		out.append(
				"                            <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.append("                            <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.append("                                <li class=\"dropdown-header text-start\">");
		out.append("                                    <h6>Lọc</h6>");
		out.append("                                </li>");
		out.append("");
		out.append("                                <li><a class=\"dropdown-item\" href=\"#\">Ngày</a></li>");
		out.append("                                <li><a class=\"dropdown-item\" href=\"#\">Tháng</a></li>");
		out.append("                                <li><a class=\"dropdown-item\" href=\"#\">Năm</a></li>");
		out.append("                            </ul>");
		out.append("                        </div>");
		out.append("");
		out.append("                        <div class=\"card-body\">");
		out.append("                            <h5 class=\"card-title\">Lượng đặt Tour <span>| Ngày</span></h5>");
		out.append("");
		out.append("                            <div class=\"d-flex align-items-center\">");
		out.append(
				"                                <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
		out.append("                                    <i class=\"bi bi-cart\"></i>");
		out.append("                                </div>");
		out.append("                                <div class=\"ps-3\">");
		out.append("                                    <h6>145</h6>");
		out.append(
				"                                    <span class=\"text-success small pt-1 fw-bold\">12%</span> <span");
		out.append("                                        class=\"text-muted small pt-2 ps-1\">gia tăng</span>");
		out.append("");
		out.append("                                </div>");
		out.append("                            </div>");
		out.append("                        </div>");
		out.append("");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("                <!-- End Lượng đặt Tour Card -->");
		out.append("");
		out.append("                <!-- Doanh thu Card -->");
		out.append("                <div class=\"col-xxl-4 col-md-6\">");
		out.append("                    <div class=\"card info-card revenue-card\">");
		out.append("");
		out.append("                        <div class=\"filter\">");
		out.append(
				"                            <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.append("                            <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.append("                                <li class=\"dropdown-header text-start\">");
		out.append("                                    <h6>Lọc</h6>");
		out.append("                                </li>");
		out.append("");
		out.append("                                <li><a class=\"dropdown-item\" href=\"#\">Ngày</a></li>");
		out.append("                                <li><a class=\"dropdown-item\" href=\"#\">Tháng</a></li>");
		out.append("                                <li><a class=\"dropdown-item\" href=\"#\">Năm</a></li>");
		out.append("                            </ul>");
		out.append("                        </div>");
		out.append("");
		out.append("                        <div class=\"card-body\">");
		out.append("                            <h5 class=\"card-title\">Doanh thu <span>| Tháng</span></h5>");
		out.append("");
		out.append("                            <div class=\"d-flex align-items-center\">");
		out.append(
				"                                <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
		out.append("                                    <i class=\"bi bi-currency-dollar\"></i>");
		out.append("                                </div>");
		out.append("                                <div class=\"ps-3\">");
		out.append("                                    <h6>$3,264</h6>");
		out.append(
				"                                    <span class=\"text-success small pt-1 fw-bold\">8%</span> <span");
		out.append("                                        class=\"text-muted small pt-2 ps-1\">gia tăng</span>");
		out.append("");
		out.append("                                </div>");
		out.append("                            </div>");
		out.append("                        </div>");
		out.append("");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("                <!-- End Doanh thu Card -->");
		out.append("");
		out.append("                <!-- Lượng khách hàng Card -->");
		out.append("                <div class=\"col-xxl-4 col-xl-12\">");
		out.append("");
		out.append("                    <div class=\"card info-card customers-card\">");
		out.append("");
		out.append("                        <div class=\"filter\">");
		out.append(
				"                            <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.append("                            <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.append("                                <li class=\"dropdown-header text-start\">");
		out.append("                                    <h6>Lọc</h6>");
		out.append("                                </li>");
		out.append("");
		out.append("                                <li><a class=\"dropdown-item\" href=\"#\">Ngày</a></li>");
		out.append("                                <li><a class=\"dropdown-item\" href=\"#\">Tháng</a></li>");
		out.append("                                <li><a class=\"dropdown-item\" href=\"#\">Năm</a></li>");
		out.append("                            </ul>");
		out.append("                        </div>");
		out.append("");
		out.append("                        <div class=\"card-body\">");
		out.append("                            <h5 class=\"card-title\">Lượng khách hàng <span>| Năm</span></h5>");
		out.append("");
		out.append("                            <div class=\"d-flex align-items-center\">");
		out.append(
				"                                <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
		out.append("                                    <i class=\"bi bi-people\"></i>");
		out.append("                                </div>");
		out.append("                                <div class=\"ps-3\">");
		out.append("                                    <h6>1244</h6>");
		out.append(
				"                                    <span class=\"text-danger small pt-1 fw-bold\">12%</span> <span");
		out.append("                                        class=\"text-muted small pt-2 ps-1\">giảm bớt</span>");
		out.append("");
		out.append("                                </div>");
		out.append("                            </div>");
		out.append("");
		out.append("                        </div>");
		out.append("                    </div>");
		out.append("");
		out.append("                </div>");
		out.append("                <!-- End Lượng khách hàng Card -->");
		out.append("            </div>");
		out.append("");
		out.append("            <!-- Báo cáo tổng quan (Biểu đồ 3 đường: Lượng đặt Tour, Doanh thu, Khách hàng) -->");
		out.append("            <div class=\"row\">");
		out.append("                <div class=\"col-12\">");
		out.append("                    <div class=\"card\">");
		out.append("");
		out.append("                        <div class=\"filter\">");
		out.append(
				"                            <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.append("                            <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.append("                                <li class=\"dropdown-header text-start\">");
		out.append("                                    <h6>Lọc</h6>");
		out.append("                                </li>");
		out.append("");
		out.append("                                <li><a class=\"dropdown-item\" href=\"#\">Ngày</a></li>");
		out.append("                                <li><a class=\"dropdown-item\" href=\"#\">Tháng</a></li>");
		out.append("                                <li><a class=\"dropdown-item\" href=\"#\">Năm</a></li>");
		out.append("                            </ul>");
		out.append("                        </div>");
		out.append("");
		out.append("                        <div class=\"card-body\">");
		out.append("                            <h5 class=\"card-title\">Báo cáo <span>| Ngày</span></h5>");
		out.append("");
		out.append("                            <!-- Line Chart -->");
		out.append("                            <div id=\"reportsChart\"></div>");
		out.append("");
		out.append("                            <script>");
		out.append("                                document.addEventListener(\"DOMContentLoaded\", () => {");
		out.append("                                    new ApexCharts(document.querySelector(\"#reportsChart\"), {");
		out.append("                                        series: [{");
		out.append("                                            name: 'Lượng Tour',");
		out.append("                                            data: [31, 40, 28, 51, 42, 82, 56],");
		out.append("                                        }, {");
		out.append("                                            name: 'Doanh thu',");
		out.append("                                            data: [11, 32, 45, 32, 34, 52, 41]");
		out.append("                                        }, {");
		out.append("                                            name: 'Khách hàng',");
		out.append("                                            data: [15, 11, 32, 18, 9, 24, 11]");
		out.append("                                        }],");
		out.append("                                        chart: {");
		out.append("                                            height: 350,");
		out.append("                                            type: 'area',");
		out.append("                                            toolbar: {");
		out.append("                                                show: false");
		out.append("                                            },");
		out.append("                                        },");
		out.append("                                        markers: {");
		out.append("                                            size: 4");
		out.append("                                        },");
		out.append("                                        colors: ['#4154f1', '#2eca6a', '#ff771d'],");
		out.append("                                        fill: {");
		out.append("                                            type: \"gradient\",");
		out.append("                                            gradient: {");
		out.append("                                                shadeIntensity: 1,");
		out.append("                                                opacityFrom: 0.3,");
		out.append("                                                opacityTo: 0.4,");
		out.append("                                                stops: [0, 90, 100]");
		out.append("                                            }");
		out.append("                                        },");
		out.append("                                        dataLabels: {");
		out.append("                                            enabled: false");
		out.append("                                        },");
		out.append("                                        stroke: {");
		out.append("                                            curve: 'smooth',");
		out.append("                                            width: 2");
		out.append("                                        },");
		out.append("                                        xaxis: {");
		out.append("                                            type: 'datetime',");
		out.append(
				"                                            categories: [\"2018-09-19T00:00:00.000Z\", \"2018-09-19T01:30:00.000Z\", \"2018-09-19T02:30:00.000Z\", \"2018-09-19T03:30:00.000Z\", \"2018-09-19T04:30:00.000Z\", \"2018-09-19T05:30:00.000Z\", \"2018-09-19T06:30:00.000Z\"]");
		out.append("                                        },");
		out.append("                                        tooltip: {");
		out.append("                                            x: {");
		out.append("                                                format: 'dd/MM/yy HH:mm'");
		out.append("                                            },");
		out.append("                                        }");
		out.append("                                    }).render();");
		out.append("                                });");
		out.append("                            </script>");
		out.append("                            <!-- End Line Chart -->");
		out.append("");
		out.append("                        </div>");
		out.append("");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("            </div>");
		out.append(
				"            <!-- End Báo cáo tổng quan (Biểu đồ 3 đường: Lượng đặt Tour, Doanh thu, Khách hàng) -->");
		out.append("");
		out.append("            <!-- 2 biểu đồ (Top đánh giá, Xu hướng Tour)-->");
		out.append("            <div class=\"row mb-4\">");
		out.append("                <!-- Top 5 Tour được đánh giá cao nhất -->");
		out.append("                <div class=\"col-7\">");
		out.append("                    <div class=\"card h-100\">");
		out.append("");
		out.append("                        <div class=\"filter\">");
		out.append(
				"                            <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.append("                            <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.append("                                <li class=\"dropdown-header text-start\">");
		out.append("                                    <h6>Lọc</h6>");
		out.append("                                </li>");
		out.append("");
		out.append("                                <li><a class=\"dropdown-item\" href=\"#\">Ngày</a></li>");
		out.append("                                <li><a class=\"dropdown-item\" href=\"#\">Tháng</a></li>");
		out.append("                                <li><a class=\"dropdown-item\" href=\"#\">Năm</a></li>");
		out.append("                            </ul>");
		out.append("                        </div>");
		out.append("");
		out.append("                        <div class=\"card-body\">");
		out.append(
				"                            <h5 class=\"card-title\">Tour được đánh giá cao nhất <span>| Ngày</span></h5>");
		out.append("");
		out.append("                            <!-- Bar Chart -->");
		out.append(
				"                            <div id=\"barChart\" style=\"min-height: 400px;\" class=\"echart\"></div>");
		out.append("");
		out.append("                            <script>");
		out.append("                                document.addEventListener(\"DOMContentLoaded\", () => {");
		out.append(
				"                                    echarts.init(document.querySelector(\"#barChart\")).setOption({");
		out.append("                                        xAxis: {");
		out.append("                                            type: 'category',");
		out.append(
				"                                            data: ['Tuyên Quang', 'Hà Nội', 'Đà Năng', 'Sapa', 'Hồ Chí Minh']");
		out.append("                                        },");
		out.append("                                        yAxis: {");
		out.append("                                            type: 'value'");
		out.append("                                        },");
		out.append("                                        series: [{");
		out.append("                                            data: [120, 200, 150, 80, 70],");
		out.append("                                            type: 'bar'");
		out.append("                                        }]");
		out.append("                                    });");
		out.append("                                });");
		out.append("                            </script>");
		out.append("                            <!-- End Bar Chart -->");
		out.append("");
		out.append("                        </div>");
		out.append("");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("");
		out.append("                <!-- Xu hướng Tour (Thể hiện theo tỷ lệ category) -->");
		out.append("                <div class=\"col-5\">");
		out.append("                    <div class=\"card h-100\">");
		out.append("                        <div class=\"filter\">");
		out.append(
				"                            <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.append("                            <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.append("                                <li class=\"dropdown-header text-start\">");
		out.append("                                    <h6>Lọc</h6>");
		out.append("                                </li>");
		out.append("");
		out.append("<li><button class=\"dropdown-item bg-warning\" onclick=\"loadChart('nextyear')\">Năm sau (Dự đoán)</button></li>");
		out.append("<li><button class=\"dropdown-item\" onclick=\"loadChart('year')\">Năm</button></li>");
		out.append("<li><button class=\"dropdown-item\" onclick=\"loadChart('month')\">Tháng</button></li>");
		out.append("<li><button class=\"dropdown-item\" onclick=\"loadChart('day')\">Ngày</button></li>");

		out.append("                            </ul>");
		out.append("                        </div>");
		out.append("");
		out.append("                        <div class=\"card-body pb-0\">");
		out.append("                            <h5 class=\"card-title\" id=\"fil\">Xu hướng Tour <span >| </span></h5>");
		out.append("");
		
	
        out.append(
				"                            <div id=\"trafficChart\" style=\"min-height: 400px;\" class=\"echart\"></div>");
		out.append("");
        out.append("<script>");
        out.append("document.addEventListener('DOMContentLoaded', function () {");
        out.append("    loadChart('year');");
        out.append("});");
        out.append("function loadChart(filter) {");
        out.append("fetch('/adv/to/tour/ad-dashboard?filter='+filter)");
        out.append("  .then(res => res.json())");
        out.append("  .then(rawData => {");
        out.append("    const chartData = rawData.map(item => ({");
        out.append("      value: item.total,");
        out.append("      name: item.category");
        out.append("    }));");
        out.append("document.getElementById('fil').innerHTML = 'Xu hướng Tour <span>| ' + ");
        out.append("(filter === 'year' ? 'Năm' : (filter === 'month' ? 'Tháng' :(filter === 'day' ? 'Ngày':'Năm sau'))) + '</span>'; ");

        
        out.append("    var chartDom = document.querySelector('#trafficChart');");
        out.append("    var myChart = echarts.init(chartDom);");
        out.append("    var option = {");
        out.append("      tooltip: { trigger: 'item' },");
        out.append("      legend: { top: '0%', left: 'center' },");
        out.append("      series: [{");
        out.append("        name: 'Tour theo thể loại',");
        out.append("        type: 'pie',");
        out.append("        radius: ['40%', '70%'],");
        out.append("        avoidLabelOverlap: false,");
        out.append("        label: { show: false, position: 'center' },");
        out.append("        emphasis: {");
        out.append("          label: {");
        out.append("            show: true,");
        out.append("            fontSize: '18',");
        out.append("            fontWeight: 'bold'");
        out.append("          }");
        out.append("        },");
        out.append("        labelLine: { show: false },");
        out.append("        data: chartData ");
        out.append("      }]");
        out.append("    };");
        out.append("    myChart.setOption(option);");

        out.append("  });");

        out.append(" }");


        out.append("</script>");


		out.append("");
		out.append("                        </div>");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("            </div>");
		out.append("");
		out.append("            <!-- Tour được đặt gần đây -->");
		out.append("            <div class=\"row\">");
		out.append("                <div class=\"col-12\">");
		out.append("                    <div class=\"card recent-sales overflow-auto\">");
		out.append("");
		out.append("                        <div class=\"card-body\">");
		out.append("                            <h5 class=\"card-title\">Tour đặt gần đây <span>| Ngày</span></h5>");
		out.append("");
		out.append("                            <table class=\"table table-borderless datatable\">");
		out.append("                                <thead>");
		out.append("                                    <tr>");
		out.append("                                        <th scope=\"col\">#</th>");
		out.append("                                        <th scope=\"col\">ID</th>");
		out.append("                                        <th scope=\"col\">Tour</th>");
		out.append("                                        <th scope=\"col\">Khách hàng</th>");
		out.append("                                        <th scope=\"col\">Giá</th>");
		out.append("                                        <th scope=\"col\">Trạng thái</th>");
		out.append("                                    </tr>");
		out.append("                                </thead>");
		out.append("                                <tbody>");
		out.append("                                    <tr>");
		out.append("                                        <th>1</a></th>");
		out.append("                                        <td>T001</td>");
		out.append("                                        <td>Tuyên Quang</td>");
		out.append("                                        <td>Phạm Việt Long</td>");
		out.append("                                        <td>$64</td>");
		out.append(
				"                                        <td><span class=\"badge bg-success\">Đã khởi hành</span></td>");
		out.append("                                    </tr>");
		out.append("                                    <tr>");
		out.append("                                        <th>2</a></th>");
		out.append("                                        <td>T002</td>");
		out.append("                                        <td>Đà Nẵng</td>");
		out.append("                                        <td>Phạm Việt Long</td>");
		out.append("                                        <td>$47</td>");
		out.append(
				"                                        <td><span class=\"badge bg-warning\">Chưa khởi hành</span></td>");
		out.append("                                    </tr>");
		out.append("                                    <tr>");
		out.append("                                        <th>3</a></th>");
		out.append("                                        <td>T003</td>");
		out.append("                                        <td>Sapa</td>");
		out.append("                                        <td>Phạm Việt Long</td>");
		out.append("                                        <td>$147</td>");
		out.append(
				"                                        <td><span class=\"badge bg-success\">Đã khởi hành</span></td>");
		out.append("                                    </tr>");
		out.append("                                    <tr>");
		out.append("                                        <th>4</a></th>");
		out.append("                                        <td>T004</td>");
		out.append("                                        <td>Hồ Chí Minh</td>");
		out.append("                                        <td>Phạm Việt Long</td>");
		out.append("                                        <td>$67</td>");
		out.append(
				"                                        <td><span class=\"badge bg-warning\">Chưa khởi hành</span></td>");
		out.append("                                    </tr>");
		out.append("                                    <tr>");
		out.append("                                        <th>5</a></th>");
		out.append("                                        <td>T005</td>");
		out.append("                                        <td>Hội An</td>");
		out.append("                                        <td>Phạm Việt Long</td>");
		out.append("                                        <td>$165</td>");
		out.append(
				"                                        <td><span class=\"badge bg-success\">Đã phê duyệt</span></td>");
		out.append("                                    </tr>");
		out.append("                                </tbody>");
		out.append("                            </table>");
		out.append("");
		out.append("                        </div>");
		out.append("");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("            </div>");
		out.append("");
		out.append("            <!-- Tour chạy nhất -->");
		out.append("            <div class=\"row\">");
		out.append("                <div class=\"col-12\">");
		out.append("                    <div class=\"card top-selling overflow-auto\">");
		out.append("");
		out.append("                        <div class=\"filter\">");
		out.append(
				"                            <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.append("                            <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.append("                                <li class=\"dropdown-header text-start\">");
		out.append("                                    <h6>Lọc</h6>");
		out.append("                                </li>");
		out.append("");
		out.append("                                <li><a class=\"dropdown-item\" href=\"#\">Ngày</a></li>");
		out.append("                                <li><a class=\"dropdown-item\" href=\"#\">Tháng</a></li>");
		out.append("                                <li><a class=\"dropdown-item\" href=\"#\">Năm</a></li>");
		out.append("                            </ul>");
		out.append("                        </div>");
		out.append("");
		out.append("                        <div class=\"card-body pb-0\">");
		out.append("                            <h5 class=\"card-title\">Tour chạy nhất <span>| Ngày</span></h5>");
		out.append("");
		out.append("                            <table class=\"table table-borderless\">");
		out.append("                                <thead>");
		out.append("                                    <tr>");
		out.append("                                        <th scope=\"col\"></th>");
		out.append("                                        <th scope=\"col\">Tour</th>");
		out.append("                                        <th scope=\"col\">Giá</th>");
		out.append("                                        <th scope=\"col\">Đã đặt</th>");
		out.append("                                        <th scope=\"col\">Doanh thu</th>");
		out.append("                                    </tr>");
		out.append("                                </thead>");
		out.append("                                <tbody>");
		out.append("                                    <tr>");
		out.append(
				"                                        <th scope=\"row\"><a href=\"#\"><img src=\"assets/img/product-1.jpg\" alt=\"\"></a>");
		out.append("                                        </th>");
		out.append("                                        <td>Tuyên Quang</td>");
		out.append("                                        <td>$64</td>");
		out.append("                                        <td class=\"fw-bold\">124</td>");
		out.append("                                        <td>$5,828</td>");
		out.append("                                    </tr>");
		out.append("                                    <tr>");
		out.append(
				"                                        <th scope=\"row\"><a href=\"#\"><img src=\"assets/img/product-1.jpg\" alt=\"\"></a>");
		out.append("                                        </th>");
		out.append("                                        <td>Tuyên Quang</td>");
		out.append("                                        <td>$64</td>");
		out.append("                                        <td class=\"fw-bold\">124</td>");
		out.append("                                        <td>$5,828</td>");
		out.append("                                    </tr>");
		out.append("                                    <tr>");
		out.append(
				"                                        <th scope=\"row\"><a href=\"#\"><img src=\"assets/img/product-1.jpg\" alt=\"\"></a>");
		out.append("                                        </th>");
		out.append("                                        <td>Tuyên Quang</td>");
		out.append("                                        <td>$64</td>");
		out.append("                                        <td class=\"fw-bold\">124</td>");
		out.append("                                        <td>$5,828</td>");
		out.append("                                    </tr>");
		out.append("                                    <tr>");
		out.append(
				"                                        <th scope=\"row\"><a href=\"#\"><img src=\"assets/img/product-1.jpg\" alt=\"\"></a>");
		out.append("                                        </th>");
		out.append("                                        <td>Tuyên Quang</td>");
		out.append("                                        <td>$64</td>");
		out.append("                                        <td class=\"fw-bold\">124</td>");
		out.append("                                        <td>$5,828</td>");
		out.append("                                    </tr>");
		out.append("                                </tbody>");
		out.append("                            </table>");
		out.append("");
		out.append("                        </div>");
		out.append("");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("            </div>");
		out.append("        </section>");
		out.append("");
		out.append("    </main><!-- End #main -->");
		out.append("");
		out.append("    <!-- ======= Footer ======= -->");
		out.append("    <footer id=\"footer\" class=\"footer\">");
		out.append("        <div class=\"copyright\">");
		out.append("            &copy; Copyright <strong><span>TravelVN</span></strong>. All Rights Reserved");
		out.append("        </div>");
		out.append("    </footer><!-- End Footer -->");
		out.append("");
		out.append("    <a href=\"#\" class=\"back-to-top d-flex align-items-center justify-content-center\"><i");
		out.append("            class=\"bi bi-arrow-up-short\"></i></a>");
		out.append("");
		out.append("  <!-- Vendor JS Files -->");
		out.append("  <script src=\"../../assets/vendor/apexcharts/apexcharts.min.js\"></script>");
		out.append("  <script src=\"../../assets/vendor/bootstrap/js/bootstrap.bundle.min.js\"></script>");
		out.append("  <script src=\"../../assets/vendor/chart.js/chart.umd.js\"></script>");
		out.append("  <script src=\"../../assets/vendor/echarts/echarts.min.js\"></script>");
		out.append("  <script src=\"../../assets/vendor/quill/quill.js\"></script>");
		out.append("  <script src=\"../../assets/vendor/simple-datatables/simple-datatables.js\"></script>");
		out.append("  <script src=\"../../assets/vendor/tinymce/tinymce.min.js\"></script>");
		out.append("  <script src=\"../../assets/vendor/php-email-form/validate.js\"></script>");
		out.append("  <!-- Template Main JS File -->");
		out.append("  <script src=\"../../assets/js/main.js\"></script>");
		out.append("</body>");
		out.append("");
		out.append("</html>");
	}
	public boolean isInteger(String s) {
	    try {
	        Integer.parseInt(s);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}

}

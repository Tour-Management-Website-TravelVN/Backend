package ads.ad;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import ads.objects.Booking;
import ads.user.ReportFunction;
import ads.user.ReportFunctionImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ad-dashboard")
public class DashboardServlet extends HttpServlet {
	private ReportFunction reportFunction = new ReportFunctionImpl();

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
		
		// Định dạng tiền
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
		symbols.setGroupingSeparator('.');
		DecimalFormat moneyFormatter = new DecimalFormat("#,##0 đ", symbols);
		
		// Thông tin cho biểu đồ tổng quan (3 đường) theo tháng trong năm
		ArrayList<Object> overViewReportOfYear = reportFunction.getOverViewReportOfYear();
		ArrayList<String> timeMonth = (ArrayList<String>) overViewReportOfYear.get(0);
		ArrayList<BigDecimal> revenueMonth = (ArrayList<BigDecimal>) overViewReportOfYear.get(1);
		ArrayList<BigDecimal> costMonth = (ArrayList<BigDecimal>) overViewReportOfYear.get(2);
		ArrayList<BigDecimal> profitMonth = (ArrayList<BigDecimal>) overViewReportOfYear.get(3);
		
		// Thông tin cho biểu đồ tổng quan (3 đường) theo ngày trong tháng
		ArrayList<Object> overViewReportOfMonth = reportFunction.getOverViewReportOfMonth();
		ArrayList<String> timeDay = (ArrayList<String>) overViewReportOfMonth.get(0);
		ArrayList<BigDecimal> revenueDay = (ArrayList<BigDecimal>) overViewReportOfMonth.get(1);
		ArrayList<BigDecimal> costDay = (ArrayList<BigDecimal>) overViewReportOfMonth.get(2);
		ArrayList<BigDecimal> profitDay = (ArrayList<BigDecimal>) overViewReportOfMonth.get(3);
		
		// Thông tin 50 Tour được đặt gần nhất
		// TourUnitId, tourName, customerName, bookingDate, status
		ArrayList<Booking> recentBookings = reportFunction.get50RecentBooking();
		
		// Thông tin 50 Tour được đặt nhiều nhất
		ArrayList<Object> topBookingTours = reportFunction.get50TopBookingTour();

		PrintWriter out = resp.getWriter();

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
		out.append("    <link href=\"assets/img/Logo.svg\" rel=\"icon\">");
		out.append("    <link href=\"assets/img/apple-touch-icon.png\" rel=\"apple-touch-icon\">");
		out.append("");
		out.append("    <!-- Google Fonts -->");
		out.append("    <link href=\"https://fonts.gstatic.com\" rel=\"preconnect\">");
		out.append("    <link");
		out.append("        href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i\"");
		out.append("        rel=\"stylesheet\">");
		out.append("");
		out.append("    <!-- Vendor CSS Files -->");
		out.append("    <link href=\"assets/vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");
		out.append("    <link href=\"assets/vendor/bootstrap-icons/bootstrap-icons.css\" rel=\"stylesheet\">");
		out.append("    <link href=\"assets/vendor/boxicons/css/boxicons.min.css\" rel=\"stylesheet\">");
		out.append("    <link href=\"assets/vendor/quill/quill.snow.css\" rel=\"stylesheet\">");
		out.append("    <link href=\"assets/vendor/quill/quill.bubble.css\" rel=\"stylesheet\">");
		out.append("    <link href=\"assets/vendor/remixicon/remixicon.css\" rel=\"stylesheet\">");
		out.append("    <link href=\"assets/vendor/simple-datatables/style.css\" rel=\"stylesheet\">");
		out.append("");
		out.append("    <!-- Template Main CSS File -->");
		out.append("    <link href=\"assets/css/style.css\" rel=\"stylesheet\">");
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
		out.append("    <!-- ======= Sidebar ======= -->");
		out.append("    <aside id=\"sidebar\" class=\"sidebar\">");
		out.append("");
		out.append("        <ul class=\"sidebar-nav\" id=\"sidebar-nav\">");
		out.append("");
		out.append("            <!-- Dashboard Nav -->");
		out.append("            <li class=\"nav-item\">");
		out.append("                <a class=\"nav-link\" href=\"" + req.getContextPath() + "/ad-dashboard\">");
		out.append("                    <i class=\"bx bx-category\"></i>");
		out.append("                    <span>Tổng quan</span>");
		out.append("                </a>");
		out.append("            </li>");
		out.append("            <!-- End Dashboard Nav -->");
		out.append("");
		out.append("            <!-- Account Management Nav -->");
		out.append("            <li class=\"nav-item\">");
		out.append("                <a class=\"nav-link collapsed\" href=\"" + req.getContextPath() + "/ad-account-management\">");
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
		out.append("                        <a href=\"" + req.getContextPath() + "/ad-touroperator-management\">");
		out.append("                            <i class=\"bi bi-circle\"></i><span>Người điều hành Tour</span>");
		out.append("                        </a>");
		out.append("                    </li>");
		out.append("                    <li>");
		out.append("                        <a href=\"" + req.getContextPath() + "/ad-tourguide-management\">");
		out.append("                            <i class=\"bi bi-circle\"></i><span>Hướng dẫn viên du lịch</span>");
		out.append("                        </a>");
		out.append("                    </li>");
		out.append("                </ul>");
		out.append("            </li>");
		out.append("            <!-- End Staff Management Nav -->");
		out.append("");
		out.append("            <!-- Customer Management Nav -->");
		out.append("            <li class=\"nav-item\">");
		out.append("                <a class=\"nav-link collapsed\" href=\"" + req.getContextPath() + "/ad-customer-management\">");
		out.append("                    <i class=\"bx bx-group\"></i>");
		out.append("                    <span>Quản lý khách hàng</span>");
		out.append("                </a>");
		out.append("            </li>");
		out.append("            <!-- End Customer Management Nav -->");
		out.append("");
		out.append("            <!-- Tour Management Nav -->");
		out.append("            <li class=\"nav-item\">");
		out.append("                <a class=\"nav-link collapsed\" href=\"" + req.getContextPath() + "/ad-tour-management\">");
		out.append("                    <i class=\"bx bx-food-menu\"></i>");
		out.append("                    <span>Quản lý Tour</span>");
		out.append("                </a>");
		out.append("            </li>");
		out.append("            <!-- End Tour Management Nav -->");
		out.append("");
		out.append("            <!-- Review Management Nav -->");
		out.append("            <li class=\"nav-item\">");
		out.append("                <a class=\"nav-link collapsed\" href=\"" + req.getContextPath() + "/ad-tourrating-management\">");
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
		out.append("            <h1>Tổng quan</h1>");
		out.append("            <nav class=\"ms-auto\">");
		out.append("                <ol class=\"breadcrumb\">");
		out.append("                    <li class=\"breadcrumb-item active\">Tổng quan</li>");
		out.append("                </ol>");
		out.append("            </nav>");
		out.append("        </div><!-- End Page Title -->");
		out.append("");
		out.append("        <section class=\"section dashboard\">");
		out.append("            <!-- 3 Card -->");
		out.append("            <div class=\"row\">");
		
		// 3 Card
		String bookingFilter = req.getParameter("bookingFilter");
		if (bookingFilter == null) bookingFilter = "day";

		String revenueFilter = req.getParameter("revenueFilter");
		if (revenueFilter == null) revenueFilter = "day";

		String customerFilter = req.getParameter("customerFilter");
		if (customerFilter == null) customerFilter = "day";
		
		// Card lượt đặt Tour
		int bookings;
		switch (bookingFilter) {
		    case "month" -> bookings = reportFunction.getMonthlyTourBookings();
		    case "year" -> bookings = reportFunction.getYearlyTourBookings();
		    default -> bookings = reportFunction.getDailyTourBookings();
		}

		String bookingFilterLabel = switch (bookingFilter) {
		    case "month" -> "Tháng";
		    case "year" -> "Năm";
		    default -> "Ngày";
		};
		
		out.append("                <!-- Lượng đặt Tour Card -->");
		out.append("                <div class=\"col-xxl-4 col-md-6\">");
		out.append("                    <div class=\"card info-card sales-card\">");
		out.append("");
		out.append("                        <div class=\"filter\">");
		out.append("                            <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.append("                            <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.append("                                <li class=\"dropdown-header text-start\"><h6>Lọc</h6></li>");
		out.append("                                <li><a class=\"dropdown-item\" href=\"" + req.getContextPath() + "/ad-dashboard?bookingFilter=day&revenueFilter=" + revenueFilter  + "&customerFilter=" + customerFilter + "\">Ngày</a></li>");
		out.append("                                <li><a class=\"dropdown-item\" href=\"" + req.getContextPath() + "/ad-dashboard?bookingFilter=month&revenueFilter=" + revenueFilter + "&customerFilter=" + customerFilter + "\">Tháng</a></li>");
		out.append("                                <li><a class=\"dropdown-item\" href=\"" + req.getContextPath() + "/ad-dashboard?bookingFilter=year&revenueFilter=" + revenueFilter + "&customerFilter=" + customerFilter + "\">Năm</a></li>");
		out.append("                            </ul>");
		out.append("                        </div>");
		out.append("");
		out.append("                        <div class=\"card-body\">");
		out.append("                            <h5 class=\"card-title\">Lượng đặt Tour <span>| " + bookingFilterLabel + "</span></h5>");
		out.append("");
		out.append("                            <div class=\"d-flex align-items-center\">");
		out.append("                                <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
		out.append("                                    <i class=\"bi bi-cart\"></i>");
		out.append("                                </div>");
		out.append("                                <div class=\"ps-3\">");
		out.append("                                    <h6>" + bookings + "</h6>");
		out.append("                                </div>");
		out.append("                            </div>");
		out.append("                        </div>");
		out.append("");
		out.append("                    </div>");
		out.append("                </div>");
		
		// Card doanh thu
		BigDecimal revenue;
		switch (revenueFilter) {
		    case "month" -> revenue = reportFunction.getMonthlyRevenue();
		    case "year" -> revenue = reportFunction.getYearlyTourRevenue();
		    default -> revenue = reportFunction.getDailyRevenue();
		}
		String revenueFormat = moneyFormatter.format(revenue);

		String revenueFilterLabel = switch (revenueFilter) {
		    case "month" -> "Tháng";
		    case "year" -> "Năm";
		    default -> "Ngày";
		};
		
		out.append("                <!-- Doanh thu Card -->");
		out.append("                <div class=\"col-xxl-4 col-md-6\">");
		out.append("                    <div class=\"card info-card revenue-card\">");
		out.append("");
		out.append("                        <div class=\"filter\">");
		out.append("                            <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.append("                            <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.append("                                <li class=\"dropdown-header text-start\"><h6>Lọc</h6></li>");
		out.append("                                <li><a class=\"dropdown-item\" href=\"" + req.getContextPath() + "/ad-dashboard?bookingFilter=" + bookingFilter + "&revenueFilter=day&customerFilter=" + customerFilter + "\">Ngày</a></li>");
		out.append("                                <li><a class=\"dropdown-item\" href=\"" + req.getContextPath() + "/ad-dashboard?bookingFilter=" + bookingFilter + "&revenueFilter=month&customerFilter=" + customerFilter + "\">Tháng</a></li>");
		out.append("                                <li><a class=\"dropdown-item\" href=\"" + req.getContextPath() + "/ad-dashboard?bookingFilter=" + bookingFilter + "&revenueFilter=year&customerFilter=" + customerFilter + "\">Năm</a></li>");
		out.append("                            </ul>");
		out.append("                        </div>");
		out.append("");
		out.append("                        <div class=\"card-body\">");
		out.append("                            <h5 class=\"card-title\">Doanh thu <span>| " + revenueFilterLabel + "</span></h5>");
		out.append("");
		out.append("                            <div class=\"d-flex align-items-center\">");
		out.append("                                <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
		out.append("                                    <i class=\"bi bi-currency-dollar\"></i>");
		out.append("                                </div>");
		out.append("                                <div class=\"ps-3\">");
		out.append("                                    <h6>" + revenueFormat + "</h6>");
		out.append("                                </div>");
		out.append("                            </div>");
		out.append("                        </div>");
		out.append("");
		out.append("                    </div>");
		out.append("                </div>");
		
		// Card lượng khách hàng
		int customers;
		switch (customerFilter) {
		    case "month" -> customers = reportFunction.getMonthlyCustomers();
		    case "year" -> customers = reportFunction.getYearlyCustomers();
		    default -> customers = reportFunction.getDailyCustomers();
		}

		String customerFilterLabel = switch (customerFilter) {
		    case "month" -> "Tháng";
		    case "year" -> "Năm";
		    default -> "Ngày";
		};
		
		out.append("                <!-- Lượng khách hàng Card -->");
		out.append("                <div class=\"col-xxl-4 col-xl-12\">");
		out.append("");
		out.append("                    <div class=\"card info-card customers-card\">");
		out.append("");
		out.append("                        <div class=\"filter\">");
		out.append("                            <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.append("                            <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.append("                                <li class=\"dropdown-header text-start\"><h6>Lọc</h6></li>");
		out.append("                                <li><a class=\"dropdown-item\" href=\"" + req.getContextPath() + "/ad-dashboard?bookingFilter=" + bookingFilter + "&revenueFilter=" + revenueFilter  + "&customerFilter=day\">Ngày</a></li>");
		out.append("                                <li><a class=\"dropdown-item\" href=\"" + req.getContextPath() + "/ad-dashboard?bookingFilter=" + bookingFilter + "&revenueFilter=" + revenueFilter + "&customerFilter=month\">Tháng</a></li>");
		out.append("                                <li><a class=\"dropdown-item\" href=\"" + req.getContextPath() + "/ad-dashboard?bookingFilter=" + bookingFilter + "&revenueFilter=" + revenueFilter + "&customerFilter=year\">Năm</a></li>");
		out.append("                            </ul>");
		out.append("                        </div>");
		out.append("");
		out.append("                        <div class=\"card-body\">");
		out.append("                            <h5 class=\"card-title\">Lượng khách hàng <span>| " + customerFilterLabel + "</span></h5>");
		out.append("");
		out.append("                            <div class=\"d-flex align-items-center\">");
		out.append("                                <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
		out.append("                                    <i class=\"bi bi-people\"></i>");
		out.append("                                </div>");
		out.append("                                <div class=\"ps-3\">");
		out.append("                                    <h6>" + customers + "</h6>");
		out.append("                                </div>");
		out.append("                            </div>");
		out.append("");
		out.append("                        </div>");
		out.append("                    </div>");
		out.append("");
		out.append("                </div>");
		out.append("            </div>");
		
		// Báo cáo tổng quan (3 đường)
//		out.append("            <!-- Báo cáo tổng quan (Biểu đồ 3 đường: Doanh thu, Chi phí, Lợi nhuận) -->");
//		out.append("            <div class=\"row\">");
//		out.append("                <div class=\"col-12\">");
//		out.append("                    <div class=\"card\">");
//		out.append("");
//		out.append("                        <div class=\"filter\">");
//		out.append("                            <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
//		out.append("                            <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
//		out.append("                                <li class=\"dropdown-header text-start\">");
//		out.append("                                    <h6>Lọc</h6>");
//		out.append("                                </li>");
//		out.append("");
//		out.append("                                <li><a class=\"dropdown-item\" href=\"#\">Tháng</a></li>");
//		out.append("                                <li><a class=\"dropdown-item\" href=\"#\">Năm</a></li>");
//		out.append("                            </ul>");
//		out.append("                        </div>");
//		out.append("");
//		out.append("                        <div class=\"card-body\">");
//		out.append("                            <h5 class=\"card-title\">Báo cáo <span>| Năm</span></h5>");
//		out.append("");
//		out.append("                            <!-- Line Chart -->");
//		out.append("                            <div id=\"reportsChart\"></div>");
//		out.append("");
//		out.append("                            <script>");
//		out.append("                                document.addEventListener(\"DOMContentLoaded\", () => {");
//		out.append("                                    new ApexCharts(document.querySelector(\"#reportsChart\"), {");
//		out.append("                                        series: [{");
//		out.append("                                            name: 'Doanh thu',");
////		out.append("                                            data: [31, 40, 28, 51, 42, 82, 56],");
//		out.append("                                            data: " + bigDecimalArraytoJSArray(revenueMonth) + ",");
////		out.append("                                            data: " + bigDecimalArraytoJSArray(revenueDay) + ",");
//		out.append("                                        }, {");
//		out.append("                                            name: 'Chi phí',");
////		out.append("                                            data: [11, 32, 45, 32, 34, 52, 41]");
//		out.append("                                            data: " + bigDecimalArraytoJSArray(costMonth) + "");
////		out.append("                                            data: " + bigDecimalArraytoJSArray(costDay) + "");
//		out.append("                                        }, {");
//		out.append("                                            name: 'Lợi nhuận',");
////		out.append("                                            data: [15, 11, 32, 18, 9, 24, 11]");
//		out.append("                                            data: " + bigDecimalArraytoJSArray(profitMonth) + "");
////		out.append("                                            data: " + bigDecimalArraytoJSArray(profitDay) + "");
//		out.append("                                        }],");
//		out.append("                                        chart: {");
//		out.append("                                            height: 350,");
//		out.append("                                            type: 'area',");
//		out.append("                                            toolbar: {");
//		out.append("                                                show: false");
//		out.append("                                            },");
//		out.append("                                        },");
//		out.append("                                        markers: {");
//		out.append("                                            size: 4");
//		out.append("                                        },");
//		out.append("                                        colors: ['#4154f1', '#2eca6a', '#ff771d'],");
//		out.append("                                        fill: {");
//		out.append("                                            type: \"gradient\",");
//		out.append("                                            gradient: {");
//		out.append("                                                shadeIntensity: 1,");
//		out.append("                                                opacityFrom: 0.3,");
//		out.append("                                                opacityTo: 0.4,");
//		out.append("                                                stops: [0, 90, 100]");
//		out.append("                                            }");
//		out.append("                                        },");
//		out.append("                                        dataLabels: {");
//		out.append("                                            enabled: false");
//		out.append("                                        },");
//		out.append("                                        stroke: {");
//		out.append("                                            curve: 'smooth',");
//		out.append("                                            width: 2");
//		out.append("                                        },");
//		out.append("                                        xaxis: {");
//		out.append("                                            type: 'datetime',");
////		out.append("                                            categories: [\"2018-09-19T00:00:00.000Z\", \"2018-09-19T01:30:00.000Z\", \"2018-09-19T02:30:00.000Z\", \"2018-09-19T03:30:00.000Z\", \"2018-09-19T04:30:00.000Z\", \"2018-09-19T05:30:00.000Z\", \"2018-09-19T06:30:00.000Z\"]");
//		out.append("                                            categories: " + stringArraytoJSArray(timeMonth) + "");
////		out.append("                                            categories: " + stringArraytoJSArray(timeDay) + "");
//		out.append("                                        },");
//		out.append("                                        tooltip: {");
//		out.append("                                            x: {");
////		out.append("                                                format: 'dd/MM/yy HH:mm'");
//		out.append("                                                format: 'MM/yyyy'");
////		out.append("                                                format: 'dd/MM/yyyy'");
//		out.append("                                            },");
//		out.append("                                        }");
//		out.append("                                    }).render();");
//		out.append("                                });");
//		out.append("                            </script>");
//		out.append("                            <!-- End Line Chart -->");
//		out.append("                        </div>");
//		out.append("                    </div>");
//		out.append("                </div>");
//		out.append("            </div>");
//		out.append("            <!-- End Báo cáo tổng quan (Biểu đồ 3 đường: Doanh thu, Chi phí, Lợi nhuận) -->");

		out.append("            <!-- Báo cáo tổng quan (Biểu đồ 3 đường: Doanh thu, Chi phí, Lợi nhuận) -->");
		out.append("            <div class=\"row\">");
		out.append("                <div class=\"col-12\">");
		out.append("                    <div class=\"card\">");

		out.append("                        <div class=\"filter\">");
		out.append("                            <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.append("                            <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.append("                                <li class=\"dropdown-header text-start\">");
		out.append("                                    <h6>Lọc</h6>");
		out.append("                                </li>");
		out.append("                                <li><a class=\"dropdown-item\" onclick=\"loadReport('month')\">Tháng</a></li>");
		out.append("                                <li><a class=\"dropdown-item\" onclick=\"loadReport('year')\">Năm</a></li>");
		out.append("                            </ul>");
		out.append("                        </div>");

		out.append("                        <div class=\"card-body\">");
		out.append("                            <h5 class=\"card-title\">Báo cáo <span id=\"reportModeLabel\">| Năm</span></h5>");

		out.append("                            <!-- Line Chart -->");
		out.append("                            <div id=\"reportsChart\"></div>");

		out.append("                            <script>");
		out.append("                                let chart;");
		out.append("                                function loadReport(mode) {");
		out.append("                                    if (chart) chart.destroy();");

		out.append("                                    const revenueMonth = " + bigDecimalArraytoJSArray(revenueMonth) + ";");
		out.append("                                    const costMonth = " + bigDecimalArraytoJSArray(costMonth) + ";");
		out.append("                                    const profitMonth = " + bigDecimalArraytoJSArray(profitMonth) + ";");
		out.append("                                    const timeMonth = " + stringArraytoJSArray(timeMonth) + ";");

		out.append("                                    const revenueDay = " + bigDecimalArraytoJSArray(revenueDay) + ";");
		out.append("                                    const costDay = " + bigDecimalArraytoJSArray(costDay) + ";");
		out.append("                                    const profitDay = " + bigDecimalArraytoJSArray(profitDay) + ";");
		out.append("                                    const timeDay = " + stringArraytoJSArray(timeDay) + ";");

		out.append("                                    const series = [");
		out.append("                                        { name: 'Doanh thu', data: mode === 'year' ? revenueMonth : revenueDay },");
		out.append("                                        { name: 'Chi phí', data: mode === 'year' ? costMonth : costDay },");
		out.append("                                        { name: 'Lợi nhuận', data: mode === 'year' ? profitMonth : profitDay }");
		out.append("                                    ];");

		out.append("                                    const categories = mode === 'year' ? timeMonth : timeDay;");
		out.append("                                    const format = mode === 'year' ? 'MM/yyyy' : 'dd/MM/yyyy';");

		out.append("                                    document.getElementById(\"reportModeLabel\").innerHTML = mode === 'year' ? '| Năm' : '| Tháng';");

		out.append("                                    chart = new ApexCharts(document.querySelector(\"#reportsChart\"), {");
		out.append("                                        series: series,");
		out.append("                                        chart: { height: 350, type: 'area', toolbar: { show: false } },");
		out.append("                                        markers: { size: 4 },");
		out.append("                                        colors: ['#4154f1', '#2eca6a', '#ff771d'],");
		out.append("                                        fill: {");
		out.append("                                            type: \"gradient\",");
		out.append("                                            gradient: { shadeIntensity: 1, opacityFrom: 0.3, opacityTo: 0.4, stops: [0, 90, 100] }");
		out.append("                                        },");
		out.append("                                        dataLabels: { enabled: false },");
		out.append("                                        stroke: { curve: 'smooth', width: 2 },");
		out.append("                                        xaxis: { type: 'datetime', categories: categories },");
		out.append("                                        tooltip: { x: { format: format } }");
		out.append("                                    });");
		out.append("                                    chart.render();");
		out.append("                                }");

		out.append("                                document.addEventListener(\"DOMContentLoaded\", () => {");
		out.append("                                    loadReport('year');");
		out.append("                                });");
		out.append("                            </script>");
		out.append("                            <!-- End Line Chart -->");

		out.append("                        </div>");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("            </div>");
		out.append("            <!-- End Báo cáo tổng quan -->");
		
		
		// 2 biểu đồ
		// Lứa tuổi
		out.append("            <!-- 2 biểu đồ (Top đánh giá, Xu hướng Tour)-->");
		out.append("            <div class=\"row mb-4\">");
		out.append("                <!-- Lứa tuổi -->");
		out.append("                <div class=\"col-7\">");
		out.append("                    <div class=\"card h-100\">");
		out.append("");
		out.append("                        <div class=\"filter\">");
		out.append("                            <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
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
		
		out.append("                        <div class=\"card-body\">");
		out.append("                            <h5 class=\"card-title\">Lứa tuổi chủ yếu <span>| Ngày</span></h5>");
		out.append("");
		out.append("                            <!-- Stacked Bar Chart -->");
		out.append("                            <canvas id=\"stakedBarChart\" style=\"max-height: 400px;\"></canvas>");
		out.append("                            <script>");
		out.append("                                document.addEventListener(\"DOMContentLoaded\", () => {");
		out.append("                                    new Chart(document.querySelector('#stakedBarChart'), {");
		out.append("                                        type: 'bar',");
		out.append("                                        data: {");
		out.append("                                            labels: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7'],");
		out.append("                                            datasets: [{");
		out.append("                                                label: 'Người lớn',");
		out.append("                                                data: [75, 15, 18, 48, 74],");
		out.append("                                                backgroundColor: 'rgb(255, 99, 132)',");
		out.append("                                            },");
		out.append("                                            {");
		out.append("                                                label: 'Trẻ em',");
		out.append("                                                data: [11, 1, 12, 62, 95],");
		out.append("                                                backgroundColor: 'rgb(75, 192, 192)',");
		out.append("                                            },");
		out.append("                                            {");
		out.append("                                                label: 'Trẻ nhỏ',");
		out.append("                                                data: [44, 5, 22, 35, 62],");
		out.append("                                                backgroundColor: 'rgb(255, 205, 86)',");
		out.append("                                            },");
		out.append("                                            {");
		out.append("                                                label: 'Trẻ sơ sinh',");
		out.append("                                                data: [44, 5, 22, 35, 62],");
		out.append("                                                backgroundColor: 'rgb(255, 150, 70)',");
		out.append("                                            }");
		out.append("                                            ]");
		out.append("                                        },");
		out.append("                                        options: {");
		out.append("                                            plugins: {");
		out.append("                                                title: {");
		out.append("                                                    display: false,");
		out.append("                                                    text: 'Chart.js Bar Chart - Stacked'");
		out.append("                                                },");
		out.append("                                            },");
		out.append("                                            responsive: true,");
		out.append("                                            scales: {");
		out.append("                                                x: {");
		out.append("                                                    stacked: true,");
		out.append("                                                },");
		out.append("                                                y: {");
		out.append("                                                    stacked: true");
		out.append("                                                }");
		out.append("                                            }");
		out.append("                                        }");
		out.append("                                    });");
		out.append("                                });");
		out.append("                            </script>");
		out.append("");
		out.append("                        </div>");
		out.append("");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("");
		
		
		// Xu hướng Tour
		out.append("                <!-- Xu hướng Tour (Thể hiện theo tỷ lệ category) -->");
		out.append("                <div class=\"col-5\">");
		out.append("                    <div class=\"card h-100\">");
		out.append("                        <div class=\"filter\">");
		out.append("                            <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
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
		out.append("                            <h5 class=\"card-title\">Xu hướng Tour <span>| Ngày</span></h5>");
		out.append("");
		out.append("                            <div id=\"trafficChart\" style=\"min-height: 400px;\" class=\"echart\"></div>");
		out.append("");
		out.append("                            <script>");
		out.append("                                document.addEventListener(\"DOMContentLoaded\", () => {");
		out.append("                                    echarts.init(document.querySelector(\"#trafficChart\")).setOption({");
		out.append("                                        tooltip: {");
		out.append("                                            trigger: 'item'");
		out.append("                                        },");
		out.append("                                        legend: {");
		out.append("                                            top: '0%',");
		out.append("                                            left: 'center'");
		out.append("                                        },");
		out.append("                                        series: [{");
		out.append("                                            name: 'Access From',");
		out.append("                                            type: 'pie',");
		out.append("                                            radius: ['40%', '70%'],");
		out.append("                                            avoidLabelOverlap: false,");
		out.append("                                            label: {");
		out.append("                                                show: false,");
		out.append("                                                position: 'center'");
		out.append("                                            },");
		out.append("                                            emphasis: {");
		out.append("                                                label: {");
		out.append("                                                    show: true,");
		out.append("                                                    fontSize: '18',");
		out.append("                                                    fontWeight: 'bold'");
		out.append("                                                }");
		out.append("                                            },");
		out.append("                                            labelLine: {");
		out.append("                                                show: false");
		out.append("                                            },");
		out.append("                                            data: [{");
		out.append("                                                value: 1048,");
		out.append("                                                name: 'Du lịch biển'");
		out.append("                                            },");
		out.append("                                            {");
		out.append("                                                value: 735,");
		out.append("                                                name: 'Du lịch núi'");
		out.append("                                            },");
		out.append("                                            {");
		out.append("                                                value: 580,");
		out.append("                                                name: 'Du lịch văn hóa'");
		out.append("                                            },");
		out.append("                                            {");
		out.append("                                                value: 484,");
		out.append("                                                name: 'Du lịch ẩm thực'");
		out.append("                                            },");
		out.append("                                            {");
		out.append("                                                value: 300,");
		out.append("                                                name: 'Du lịch sinh thái'");
		out.append("                                            },");
		out.append("                                            {");
		out.append("                                                value: 300,");
		out.append("                                                name: 'Du lịch mạo hiểm'");
		out.append("                                            }");
		out.append("                                            ]");
		out.append("                                        }]");
		out.append("                                    });");
		out.append("                                });");
		out.append("                            </script>");
		out.append("");
		out.append("                        </div>");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("            </div>");
		out.append("");
		
		
		// Danh sách các Tour được đặt gần đây
		out.append("            <!-- Tour được đặt gần đây -->");
		out.append("            <div class=\"row\">");
		out.append("                <div class=\"col-12\">");
		out.append("                    <div class=\"card recent-sales overflow-auto\">");
		out.append("");
		out.append("                        <div class=\"card-body\">");
		out.append("                            <h5 class=\"card-title\">Tour đặt gần đây</h5>");
		out.append("");
		out.append("                            <table class=\"table table-borderless datatable\">");
		out.append("                                <thead>");
		out.append("                                    <tr>");
		out.append("                                        <th scope=\"col\">#</th>");
		out.append("                                        <th scope=\"col\">Tour</th>");
		out.append("                                        <th scope=\"col\">Khách hàng</th>");
		out.append("                                        <th scope=\"col\">Ngày đặt</th>");
		out.append("                                        <th scope=\"col\">Trạng thái</th>");
		out.append("                                    </tr>");
		out.append("                                </thead>");
		out.append("                                <tbody>");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());;
		int orderRecentBooking = 0;
		for(Booking recentBooking : recentBookings) {
			orderRecentBooking++;
			String tourName = recentBooking.getTourUnit().getTour().getTourName();
			String customerName = recentBooking.getC().getLastname() + " " + recentBooking.getC().getFirstname();
			String bookingDate = formatter.format(recentBooking.getBookingDate());
			String status = "N/A";
			String statusColor = "bg-secondary";
			if(recentBooking.getStatus().equals("C")) {
				status = "Bị hủy";
				statusColor = "bg-danger";
			}
			else if(recentBooking.getStatus().equals("D")) {
				status = "Đã hoàn thành";
				statusColor = "bg-success";
			}
			else if(recentBooking.getStatus().equals("O")) {
				status = "Đang tiến hành";
				statusColor = "bg-primary";
			}
			else if(recentBooking.getStatus().equals("W")) {
				status = "Chờ duyệt";
				statusColor = "bg-warning";
			}
			else if(recentBooking.getStatus().equals("P")) {
				status = "Đang chuẩn bị";
				statusColor = "bg-secondary";
			}
			
			out.append("                                    <tr>");
			out.append("                                        <th>" + orderRecentBooking + "</a></th>");
			out.append("                                        <td title=\"" + tourName + "\" style=\"max-width: 500px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;\">" + tourName + "</td>");
			out.append("                                        <td>" + customerName + "</td>");
			out.append("                                        <td>" + bookingDate + "</td>");
			out.append("                                        <td><span class=\"badge " + statusColor + "\">" + status + "</span></td>");
			out.append("                                    </tr>");
		}
		
		out.append("                                </tbody>");
		out.append("                            </table>");
		out.append("");
		out.append("                        </div>");
		out.append("");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("            </div>");
		
		
		// Danh sách Tour chạy nhất
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
		out.append("                            <table class=\"table table-borderless datatable\">");
		out.append("                                <thead>");
		out.append("                                    <tr>");
		out.append("                                        <th scope=\"col\">#</th>");
		out.append("                                        <th scope=\"col\">Tour</th>");
		out.append("                                        <th scope=\"col\">Đã đặt</th>");
		out.append("                                        <th scope=\"col\">Doanh thu</th>");
		out.append("                                    </tr>");
		out.append("                                </thead>");
		out.append("                                <tbody>");
		
		ArrayList<String> tourNames = (ArrayList<String>) topBookingTours.get(0);
		ArrayList<Integer> totalBookings = (ArrayList<Integer>) topBookingTours.get(1);
		ArrayList<BigDecimal> totalRevenues = (ArrayList<BigDecimal>) topBookingTours.get(2);
		for(int i = 0; i < tourNames.size(); i++) {
			String tourName = tourNames.get(i);
			int totalBooking = totalBookings.get(i);
			BigDecimal totalRevenue = totalRevenues.get(i);
			String totalRenenueFormat =  moneyFormatter.format(totalRevenue);
			
			out.append("                                    <tr>");
			out.append("                                        <td>" + (i + 1) + "</td>");
			out.append("                                        <td title=\"" + tourName + "\" style=\"max-width: 650px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;\">" + tourName + "</td>");
			out.append("                                        <td>" + totalBooking + "</td>");
			out.append("                                        <td>" + totalRenenueFormat + "</td>");
			out.append("                                    </tr>");
		}
		
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
		out.append("    <!-- Vendor JS Files -->");
		out.append("    <script src=\"assets/vendor/apexcharts/apexcharts.min.js\"></script>");
		out.append("    <script src=\"assets/vendor/bootstrap/js/bootstrap.bundle.min.js\"></script>");
		out.append("    <script src=\"assets/vendor/chart.js/chart.umd.js\"></script>");
		out.append("    <script src=\"assets/vendor/echarts/echarts.min.js\"></script>");
		out.append("    <script src=\"assets/vendor/quill/quill.js\"></script>");
		out.append("    <script src=\"assets/vendor/simple-datatables/simple-datatables.js\"></script>");
		out.append("    <script src=\"assets/vendor/tinymce/tinymce.min.js\"></script>");
		out.append("    <script src=\"assets/vendor/php-email-form/validate.js\"></script>");
		out.append("");
		out.append("    <!-- Template Main JS File -->");
		out.append("    <script src=\"assets/js/main.js\"></script>");
		out.append("</body>");
		out.append("");
		out.append("</html>");
	}
	
	private String bigDecimalArraytoJSArray(ArrayList<BigDecimal> list) {
	    StringBuilder sb = new StringBuilder("[");
	    for (int i = 0; i < list.size(); i++) {
	        Object item = list.get(i);
	        if (item instanceof String) {
	            sb.append("\"").append(item).append("\"");
	        } else {
	            sb.append(item);
	        }
	        if (i < list.size() - 1) sb.append(", ");
	    }
	    sb.append("]");
	    return sb.toString();
	}
	
	private String stringArraytoJSArray(ArrayList<String> list) {
	    StringBuilder sb = new StringBuilder("[");
	    for (int i = 0; i < list.size(); i++) {
	        Object item = list.get(i);
	        if (item instanceof String) {
	            sb.append("\"").append(item).append("\"");
	        } else {
	            sb.append(item);
	        }
	        if (i < list.size() - 1) sb.append(", ");
	    }
	    sb.append("]");
	    return sb.toString();
	}

}

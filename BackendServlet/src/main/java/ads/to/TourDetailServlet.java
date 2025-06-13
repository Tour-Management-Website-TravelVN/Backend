package ads.to;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import ads.library.TourLibrary;
import ads.user.TourFunctionImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/to/tour/tour_detail")
public class TourDetailServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String tourId = request.getParameter("tourid");
		if(tourId == null) {
			response.sendRedirect("/adv/to/tour");
			return;
		};
		
		HttpSession session = request.getSession();
		String actionTour = (String) session.getAttribute("actxionTour");
		if(actionTour!=null) session.removeAttribute("actionTour");
		
		PrintWriter out = response.getWriter();
		
		out.append("<!DOCTYPE html>");
		out.append("<html lang=\"en\">");
		out.append("");
		out.append("<head>");
		out.append("<meta charset=\"utf-8\">");
		out.append("<meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">");
		out.append("");
		out.append("<title>Chi tiết tour</title>");
		out.append("<meta content=\"\" name=\"description\">");
		out.append("<meta content=\"\" name=\"keywords\">");
		out.append("");
		out.append("<!-- Favicons -->");
		out.append("  <link href=\"/adv/resources/Logo.svg\" rel=\"icon\">");
		out.append("  <link href=\"/adv/assets/img/apple-touch-icon.png\" rel=\"apple-touch-icon\">");
		out.append("  <!-- Google Fonts -->");
		out.append("  <link href=\"https://fonts.gstatic.com\" rel=\"preconnect\">");
		out.append(
				"  <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i\" rel=\"stylesheet\">");
		out.append("  <!-- Vendor CSS Files -->");
		out.append("  <link href=\"/adv/assets/vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"/adv/assets/vendor/bootstrap-icons/bootstrap-icons.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"/adv/assets/vendor/boxicons/css/boxicons.min.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"/adv/assets/vendor/quill/quill.snow.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"/adv/assets/vendor/quill/quill.bubble.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"/adv/assets/vendor/remixicon/remixicon.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"/adv/assets/vendor/simple-datatables/style.css\" rel=\"stylesheet\">");
		out.append("  <script src=\"https://kit.fontawesome.com/0b7fe90b3a.js\" crossorigin=\"anonymous\"></script>");
		out.append("  <script src=\"/adv/assets/vendor/jquery/jquery-3.7.1.min.js\"></script>");
		out.append("  <!-- Template Main CSS File -->");
		out.append("  <link href=\"/adv/css/style.css\" rel=\"stylesheet\">");
		out.append("</head>");
		out.append("");
		out.append("<body>");
		
		RequestDispatcher h = request.getRequestDispatcher("/he");
		if (h != null) {
			h.include(request, response);
		}
		RequestDispatcher s = request.getRequestDispatcher("/side");
		if (s != null) {
			s.include(request, response);
		}
		
		out.append("<main id=\"main\" class=\"main \">");
		out.append("");
		out.append("<div class=\"pagetitle d-flex\">");
		out.append("<h1>Chi tiết tour</h1>");
		out.append("<nav class=\"ms-auto\">");
		out.append("<ol class=\"breadcrumb\">");
		out.append("<li class=\"breadcrumb-item\"><a href=\"index.html\"><i class=\"bi bi-house-door\"></i></a></li>");
		out.append("<li class=\"breadcrumb-item\">Quản lý tour</li>");
		out.append("<li class=\"breadcrumb-item active\">Chi tiết tour</li>");
		out.append("</ol>");
		out.append("</nav>");
		out.append("</div><!-- End Page Title -->");
		
		out.append(TourLibrary.viewTourDetail(tourId, actionTour));
		
//		out.append("<section class=\"section\">");
//		out.append("<div class=\"row\">");
//		out.append("<div class=\"col-md-3 col-lg-\">");
//		out.append("<div class=\"card\">");
//		out.append("<div class=\"card-body\">");
//		out.append("<h5 class=\"card-title\">Hình ảnh</h5>");
//		out.append("<div class=\"list-group\">");
//		out.append("<a href=\"#\" class=\"list-group-item list-group-item-action\">");
//		out.append("<img src=\"assets/img/chi-phi-di-da-lat-1.jpeg\" class=\"img-fluid\" alt=\"...\">");
//		out.append("</a>");
//		out.append("<a href=\"#\" class=\"list-group-item list-group-item-action\">");
//		out.append("<img src=\"assets/img/chi-phi-di-da-lat-6.jpg\" class=\"img-fluid\" alt=\"...\">");
//		out.append("</a>");
//		out.append("<a href=\"#\" class=\"list-group-item list-group-item-action\">");
//		out.append(
//				"<img src=\"assets/img/481088087_959148229729702_4478781852400038459_n.jpg\" class=\"img-fluid\" alt=\"...\">");
//		out.append("</a>");
//		out.append("</div>");
//		out.append("</div>");
//		out.append("</div>");
//		out.append("</div>");
//		out.append("<div class=\"col-md-9\">");
//		out.append("<div class=\"card\">");
//		out.append("<div class=\"card-body\">");
//		out.append("<div class=\"row\">");
//		out.append("<h5 class=\"card-title col-md-4 ms-2\">Thông tin tour</h5>");
//		out.append(
//				"<a href=\"edit-tour.html\" onclick=\"openForm()\" class=\"fw-bold col-md-1 mt-3 ms-auto text-end fs-3\"> ");
//		out.append("<i class=\"bi bi-gear-fill text-warning\"></i>");
//		out.append("</a>");
//		out.append("</div>");
//		out.append("<div class=\"row lh-sm\">");
//		out.append("<div class=\"col-md-7\">");
//		out.append("<p><strong>Mã tour:</strong> T001-00001-3N2Đ</p>");
//		out.append("<p><strong>Tên:</strong> Đà Lạt 3 Ngày 2 Đêm</p>");
//		out.append("<p><strong>Thể loại tour:</strong> Ô tô, Máy bay, Tàu hoả, Tàu thuỷ</p>");
//		out.append("<p><strong>Thời gian lý tưởng:</strong> Tháng 12 - Tháng 3</p>");
//		out.append("</div> <div class=\"col-md-5\">");
//		out.append("<p><strong>Thời lượng:</strong> 3 Ngày 2 Đêm</p>");
//		out.append("<p><strong>Giá:</strong> 3.000.000 VND</p>");
//		out.append("<p><strong>Khởi hành:</strong> Hà Nội</p>");
//		out.append("<p><strong>Mã người điều hành:</strong>1</p>");
//		out.append("</div>");
//		out.append("</div class=\"row\">");
//		out.append("<p><strong>Điểm tham quan:</strong> Hồ Xuân Hương, Thung Lũng Tình Yêu, Đồi Mộng Mơ</p>");
//		out.append("<p><strong>Ẩm thực:</strong> Phương tiện di chuyển, Chỗ ở, Bữa ăn</p>");
//		out.append("<p><strong>Đã bao gồm:</strong> Phương tiện di chuyển, Chỗ ở, Bữa ăn</p>");
//		out.append("<p><strong>Chưa bao gồm:</strong> Chi phí cá nhân, Bảo hiểm</p>");
//		out.append("<p><strong>Phương tiện:</strong> Ô tô, Máy bay, Tàu hoả, Tàu thuỷ</p>");
//		out.append(
//				"<p><strong>Mô tả:</strong> Đây là mô tả chi tiết về tour, bao gồm tất cả các điểm nổi bật và thông tin quan trọng.</p>");
//		out.append("</div>");
//		out.append("</div>");
//		out.append("");
//		out.append("</div>");
//		out.append("        ");
//		out.append("</div>");
//		out.append("<div class=\"row\">");
//		out.append("<div class=\"card\">");
//		out.append("<div class=\"card-body\">");
//		out.append("<div class=\"row\">");
//		out.append("<h5 class=\"card-title col ms-3\">Chương trình tour</h5>");
//		out.append(
//				"<a href=\"add-tourprogram.html\" class=\"col-md-1 mt-2 text-end fs-3\"> <i class=\"bi bi-calendar2-plus\"></i>");
//		out.append("</a>");
//		out.append("</div>");
//		out.append("<table class=\"table table-sm table-striped\">");
//		out.append("<thead>");
//		out.append("<tr>");
//		out.append("<th scope=\"col\">#</th>");
//		out.append("<th scope=\"col\">Địa điểm</th>");
//		out.append("<th scope=\"col\">Ngày</th>");
//		out.append("<th scope=\"col\">Món ăn</th>");
//		out.append("<th scope=\"col\">Mô tả</th>");
//		out.append("<th scope=\"col\">Tuỳ chọn</th>");
//		out.append("</tr>");
//		out.append("</thead>");
//		out.append("<tbody>");
//		out.append("<tr>");
//		out.append("<th scope=\"row\">1</th>");
//		out.append("<td>Brandon Jacob</td>");
//		out.append("<td>Designer</td>");
//		out.append("<td>28</td>");
//		out.append("<td></td>");
//		out.append("<td class=\"row\">");
//		out.append("<a  href=\"edit-tourprogram.html\" class=\"text-warning col\"><i class=\"bi bi-pencil-fill\"></i>");
//		out.append("</a>");
//		out.append("                ");
//		out.append("<a href=\"#\" class=\"text-danger col\"><i class=\"bi bi-trash3-fill\"></i>");
//		out.append("</a></td>");
//		out.append("</tr>");
//		out.append("<tr>");
//		out.append("<th scope=\"row\">2</th>");
//		out.append("<td>Bridie Kessler</td>");
//		out.append("<td>Developer</td>");
//		out.append("<td>35</td>");
//		out.append("<td></td>");
//		out.append("<td class=\"row\">");
//		out.append("<a  href=\"edit-tourprogram.html\" class=\"text-warning col\"><i class=\"bi bi-pencil-fill\"></i>");
//		out.append("</a>");
//		out.append("                ");
//		out.append("<a href=\"#\" class=\"text-danger col\"><i class=\"bi bi-trash3-fill\"></i>");
//		out.append("</a></td>");
//		out.append("</tr>");
//		out.append("<tr>");
//		out.append("<th scope=\"row\">3</th>");
//		out.append("<td>Ashleigh Langosh</td>");
//		out.append("<td>Finance</td>");
//		out.append("<td>45</td>");
//		out.append("<td></td>");
//		out.append("<td class=\"row\">");
//		out.append("<a  href=\"edit-tourprogram.html\" class=\"text-warning col\"><i class=\"bi bi-pencil-fill\"></i>");
//		out.append("</a>");
//		out.append("                ");
//		out.append("<a href=\"#\" class=\"text-danger col\"><i class=\"bi bi-trash3-fill\"></i>");
//		out.append("</a></td>");
//		out.append("</tr>");
//		out.append("<tr>");
//		out.append("<th scope=\"row\">4</th>");
//		out.append("<td>Angus Grady</td>");
//		out.append("<td>HR</td>");
//		out.append("<td>34</td>");
//		out.append("<td></td>");
//		out.append("<td class=\"row\">");
//		out.append("<a  href=\"edit-tourprogram.html\" class=\"text-warning col\"><i class=\"bi bi-pencil-fill\"></i>");
//		out.append("</a>");
//		out.append("                ");
//		out.append("<a href=\"#\" class=\"text-danger col\"><i class=\"bi bi-trash3-fill\"></i>");
//		out.append("</a></td>");
//		out.append("</tr>");
//		out.append("<tr>");
//		out.append("<th scope=\"row\">5</th>");
//		out.append("<td>Raheem Lehner</td>");
//		out.append("<td>Dynamic Division Officer</td>");
//		out.append("<td>47</td>");
//		out.append("<td></td>");
//		out.append("        ");
//		out.append("<td class=\"row\">");
//		out.append("<a  href=\"edit-tourprogram.html\" class=\"text-warning col\"><i class=\"bi bi-pencil-fill\"></i>");
//		out.append("</a>");
//		out.append("<a href=\"#\" class=\"text-danger col \"><i class=\"bi bi-trash3-fill\"></i>");
//		out.append("</a></td>");
//		out.append("</tr>");
//		out.append("<tr>");
//		out.append("<th scope=\"row\">5</th>");
//		out.append("<td>Raheem Lehner</td>");
//		out.append("<td>Dynamic Division Officer</td>");
//		out.append("<td>47</td>");
//		out.append("<td></td>");
//		out.append("        ");
//		out.append("<td class=\"row\">");
//		out.append("<a  href=\"edit-tourprogram.html\" class=\"text-warning col\"><i class=\"bi bi-pencil-fill\"></i>");
//		out.append("</a>");
//		out.append("<a href=\"#\" class=\"text-danger col \"><i class=\"bi bi-trash3-fill\"></i>");
//		out.append("</a></td>");
//		out.append("</tr>");
//		out.append("<tr>");
//		out.append("<th scope=\"row\">5</th>");
//		out.append("<td>Raheem Lehner</td>");
//		out.append("<td>Dynamic Division Officer</td>");
//		out.append("<td>47</td>");
//		out.append("<td></td>");
//		out.append("        ");
//		out.append("<td class=\"row\">");
//		out.append("<a  href=\"edit-tourprogram.html\" class=\"text-warning col\"><i class=\"bi bi-pencil-fill\"></i>");
//		out.append("</a>");
//		out.append("<a href=\"#\" class=\"text-danger col \"><i class=\"bi bi-trash3-fill\"></i>");
//		out.append("</a></td>");
//		out.append("</tr>");
//		out.append("<tr>");
//		out.append("<th scope=\"row\">5</th>");
//		out.append("<td>Raheem Lehner</td>");
//		out.append("<td>Dynamic Division Officer</td>");
//		out.append("<td>47</td>");
//		out.append("<td></td>");
//		out.append("        ");
//		out.append("<td class=\"row\">");
//		out.append("<a  href=\"edit-tourprogram.html\" class=\"text-warning col\"><i class=\"bi bi-pencil-fill\"></i>");
//		out.append("</a>");
//		out.append("<a href=\"#\" class=\"text-danger col \"><i class=\"bi bi-trash3-fill\"></i>");
//		out.append("</a></td>");
//		out.append("</tr>");
//		out.append("<tr>");
//		out.append("<th scope=\"row\">5</th>");
//		out.append("<td>Raheem Lehner</td>");
//		out.append("<td>Dynamic Division Officer</td>");
//		out.append("<td>47</td>");
//		out.append("<td></td>");
//		out.append("        ");
//		out.append("<td class=\"row\">");
//		out.append("<a  href=\"edit-tourprogram.html\" class=\"text-warning col\"><i class=\"bi bi-pencil-fill\"></i>");
//		out.append("</a>");
//		out.append("<a href=\"#\" class=\"text-danger col \"><i class=\"bi bi-trash3-fill\"></i>");
//		out.append("</a></td>");
//		out.append("</tr>");
//		out.append("             ");
//		out.append("</tbody>");
//		out.append("           ");
//		out.append("</table>");
//		out.append("<div class=\"d-flex rows\">");
//		out.append(
//				"<a href=\"Tour_unit_management.html\" class=\"fw-bold text-end col\"> Quản lý các đơn vị tour <i class=\"bi-arrow-right\"></i></a>");
//		out.append("<!-- End small tables -->");
//		out.append("</div>");
//		out.append("</div>");
//		out.append("</div>");
//		out.append("");
//		out.append("</div>");
//		out.append("</div>");
//		out.append("</section>");
		out.append("</main><!-- End #main -->");
		
		if(actionTour != null) {
			if(actionTour.equalsIgnoreCase("Thêm tour thành công")) {
				out.append("<script>localStorage.removeItem(\"tourFormDraft\")</script>");
			} else if(actionTour.equalsIgnoreCase("Sửa tour thành công")){
				out.append("<script>")
				.append("let tourFormDraft2 = localStorage.getItem(\"tourFormDraft2\");")
				.append("if(tourFormDraf2){")
				.append("if(tourFormDraft2.tour.tourId==\"").append(tourId).append("\") localStorage.removeItem(\"tourFormDraft2\")")
				.append("}")
				.append("</script>");
			}
		}
		
		
		RequestDispatcher fo = request.getRequestDispatcher("/fo");
		if (fo != null) {
			fo.include(request, response);
		}
		
		out.append(
				"<a href=\"#\" class=\"back-to-top d-flex align-items-center justify-content-center\"><i class=\"bi bi-arrow-up-short\"></i></a>");
		out.append("");
		out.append("<!-- Vendor JS Files -->");
		out.append("<script src=\"/adv/assets/vendor/apexcharts/apexcharts.min.js\"></script>");
		out.append("<script src=\"/adv/assets/vendor/bootstrap/js/bootstrap.bundle.min.js\"></script>");
		out.append("<script src=\"/adv/assets/vendor/chart.js/chart.umd.js\"></script>");
		out.append("<script src=\"/adv/assets/vendor/echarts/echarts.min.js\"></script>");
		out.append("<script src=\"/adv/assets/vendor/quill/quill.js\"></script>");
		out.append("<script src=\"/adv/assets/vendor/simple-datatables/simple-datatables.js\"></script>");
		out.append("<script src=\"/adv/assets/vendor/tinymce/tinymce.min.js\"></script>");
		out.append("<script src=\"/adv/assets/vendor/php-email-form/validate.js\"></script>");
		out.append("<!-- Template Main JS File -->");
		out.append("<script src=\"/adv/assets/js/main.js\"></script>");
		out.append("");
		out.append("</body>");
		out.append("");
		out.append("</html>");
		
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
		String tourId = req.getParameter("tourid");
		if(req.getParameter("action").equalsIgnoreCase("del") && tourId!=null) {
			if(TourFunctionImpl.getInstance().deleteTourByTourId(tourId)) {
				req.getSession().setAttribute("actionTour", "Đã xóa tour "+tourId);
				resp.sendRedirect("/adv/to/tour");
			} else {
				req.getSession().setAttribute("actionTour", "Không thể xóa tour "+tourId);
				//resp.sendRedirect("/adv/to/tour/tour_detail?"+URLEncoder.encode(tourId, "UTF-8"));
			}
				
		}
//		doGet(req, resp);
	}

}

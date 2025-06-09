package ads.to;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import ads.objects.TourUnit;
import ads.user.TourUnitFunctionImpl;
import ads.util.AmountOfCustomerPredictor;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/to/tour/Tour-Unit")
public class TourUnitServlet extends HttpServlet {

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

		String tour_id = request.getParameter("tour_id");
		

		PrintWriter out = response.getWriter();
		out.append("<!DOCTYPE html>");
		out.append("<html lang=\"en\">");
		out.append("");
		out.append("<head>");
		out.append("  <meta charset=\"utf-8\">");
		out.append("  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">");
		out.append("");
		out.append("  <title>Quản lý đơn vị tour	</title>");
		out.append("  <meta content=\"\" name=\"description\">");
		out.append("  <meta content=\"\" name=\"keywords\">");
		out.append("");
		out.append("  <!-- Favicons -->");
		out.append("  <link href=\"../../assets/img/Logo.svg\" rel=\"icon\"> ");
		out.append("  <link href=\"../../assets/img/apple-touch-icon.png\" rel=\"apple-touch-icon\">");
		out.append("  <!-- Google Fonts -->");
		out.append("  <link href=\"https://fonts.gstatic.com\" rel=\"preconnect\">");
		out.append("  <link href='https://fonts.googleapis.com/css?family=Be Vietnam Pro' rel='stylesheet'>");
		out.append(
				"  <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i&subset=vietnamese\" rel=\"stylesheet\">");
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

		RequestDispatcher h = request.getRequestDispatcher("/he");
		String message = request.getParameter("message");
		String error = request.getParameter("error");

		h.include(request, response);

		RequestDispatcher side = request.getRequestDispatcher("/side");

		side.include(request, response);
		side.include(request, response);
		out.append("<!-- Modal thông báo -->");
		out.append(
				"<div class=\"modal fade\" id=\"notificationModal\" tabindex=\"-1\" aria-labelledby=\"modalLabel\" aria-hidden=\"true\">");
		out.append("  <div class=\"modal-dialog modal-dialog-centered\">");
		out.append("    <div class=\"modal-content\">");
		out.append("      <div class=\"modal-header\">");
		out.append("        <h5 class=\"modal-title\" id=\"modalLabel\">Thông báo</h5>");
		out.append(
				"        <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Đóng\"></button>");
		out.append("      </div>");
		out.append("      <div class=\"modal-body\" id=\"modalMessage\">");
		out.append("        <!-- Nội dung thông báo sẽ được JS đổ vào -->");
		out.append("      </div>");
		out.append("      <div class=\"modal-footer\">");
		out.append("        <button type=\"button\" class=\"btn btn-primary\" data-bs-dismiss=\"modal\">Đóng</button>");
		out.append("      </div>");
		out.append("    </div>");
		out.append("  </div>");
		out.append("</div>");
		out.append("");
		out.append("<script>");
		out.append("  function getParameterByName(name) {");
		out.append("    const urlParams = new URLSearchParams(window.location.search);");
		out.append("    return urlParams.get(name);");
		out.append("  }");
		out.append("");
		out.append("  window.onload = function() {");
		out.append("    const message = getParameterByName('message');");
		out.append("    const error = getParameterByName('error');");
		out.append("    const modalMessage = document.getElementById('modalMessage');");
		out.append("");
		out.append("    if (message) {");
		out.append("      modalMessage.textContent = \"Cập nhật thành công! " + message + "\";");
		out.append("      var modal = new bootstrap.Modal(document.getElementById('notificationModal'));");
		out.append("      modal.show();");
		out.append("    } else if (error) {");
		out.append("      modalMessage.textContent = \"Cập nhật thất bại! " + error + "\";");
		out.append("      var modal = new bootstrap.Modal(document.getElementById('notificationModal'));");
		out.append("      modal.show();");
		out.append("    }");
		out.append("  }");
		out.append("</script>");
		out.append("  <main id=\"main\" class=\"main\">");
		out.append("");
		out.append("<div class=\"pagetitle d-flex\">");
		out.append("<h1>Danh sách đơn vị tour</h1>");
		out.append("<nav class=\"ms-auto\">");
		out.append("<ol class=\"breadcrumb\">");
		out.append("<a href=\"index.html\"><li class=\"breadcrumb-item\"><i class=\"bi bi-house-door\"></i></a></li>");
		out.append("<li class=\"breadcrumb-item\">Quản lý tour</li>");
		out.append("<li class=\"breadcrumb-item\">Chi tiết tour</li>");
		out.append("<li class=\"breadcrumb-item active\">Danh sách đơn vị tour</li>");
		out.append("</ol>");
		out.append("</nav>");
		out.append("</div><!-- End Page Title -->");
		out.append("");
		out.append("<section class=\"section\">");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");
		out.append("");
		out.append("<div class=\"card\">");
		out.append("<div class=\"card-body\">");
		out.append("<!-- Table with stripped rows -->");
		out.append("<table class=\"table datatable table-striped\" style=\"font-size: small;\">");
		out.append("<thead >");
		out.append("<tr class=\"d-flex\">");
		
		out.append("<div class=\"row\">");

		out.append("<a href=\"Tour-Unit-Add?tourId="+tour_id+"\" class=\"btn-primary col\">");
		out.append(
				"<div class=\"d-flex mt-3\"><div class=\"btn btn-success fw-bold me-3 ms-auto\"> <i class=\"bi bi-plus-circle-fill\"></i>");
		out.append("Thêm đơn vị tour mới </div>");
		out.append("</a>");
		out.append("<a href=\"?tour_id="+tour_id+"&recordArea=1\" id=\"load\" class=\"btn-primary col\">");
		out.append(
				"<div class=\"d-flex\"><div class=\"fw-bold me-3 ms-auto fw-bold fs-3\"> <i class=\"bi bi-arrow-repeat\"></i>");
		out.append("</div>");
		
		out.append("</a>");
		out.append("</div>");
		
		out.append("</tr>");
		out.append("<tr>");
		out.append("<th>");
		out.append("<b>ID đơn vị tour</b>");
		out.append("</th>");
		out.append("<th>Giá người lớn</th>");
		out.append("<th>Tối đa (người)</th>");
		out.append("<th>Người điều hành</th>");
		out.append("<th data-type=\"date\" data-format=\"YYYY/DD/MM\">Ngày khởi hành</th>");
		out.append("<th data-type=\"date\" data-format=\"YYYY/DD/MM\">Ngày trở về</th>");
		out.append("<th class=\"text-warning\">Số lượng (dự kiến)</th>");
		out.append("<th>Tuỳ chọn</th>");
		out.append("</tr>");
		out.append("</thead>");
		out.append("<tbody>");
		TourUnitFunctionImpl dao = TourUnitFunctionImpl.getInstance();
		int maxPage = TourUnitFunctionImpl.getInstance().getMaxPage();
		ArrayList<TourUnit> tmp = dao.getAllTourUnit(tour_id,Integer.parseInt(request.getParameter("recordArea")));
		tmp.forEach(to -> {		
			out.append("<tr>");
			out.append("<td id=\"tourUnitId\">" + to.getTourUnitId() + "</td>");
			out.append("<td id=\"adultPrice\">" + to.getAdultTourPrice() + "</td>");
			out.append("<td id=\"maximumCapacity\">" + to.getMaximumCapacity() + "</td>");
			out.append("<td id=\"tourOperator\">" + to.getTourOperator().getFirstname() + " "
					+ to.getTourOperator().getLastname() + "</td>");
			out.append("<td id=\"departureDate\">" + to.getDepartureDate() + "</td>");
			out.append("<td id=\"returnDate\">" + to.getReturnDate() + "</td>");
			int p = 0;
			try {
				AmountOfCustomerPredictor.trainModel();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(to.getDiscount() != null)
				{
					p = (int) AmountOfCustomerPredictor.predict(to.getAdultTourPrice().doubleValue(), 5, to.getDiscount().getDiscountValue().doubleValue(), to.getPrivateRoomPrice().doubleValue(), to.getChildTourPrice().doubleValue(), to.getMaximumCapacity().shortValue()) ;
					if( p > 0 )
					out.append("<td id=\"predictACustomer\" class=\"bg-warning\">" +p + "</td>");
					else
						out.append("<td id=\"predictACustomer\" class=\"bg-warning\">0</td>");

				}
				else
				{
					p = (int) AmountOfCustomerPredictor.predict(to.getAdultTourPrice().doubleValue(), 5, 0, to.getPrivateRoomPrice().doubleValue(), to.getChildTourPrice().doubleValue(), to.getMaximumCapacity().shortValue());
					if(p > 0) {out.append("<td id=\"predictACustomer\" class=\"bg-warning\">" +p  + "</td>"); }else { out.append("<td id=\"predictACustomer\" class=\"bg-warning\">0</td>");}
					

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			out.append("<td >");
			out.append("<a  href=\"Customer-Edit?tourUnitId=" + to.getTourUnitId()
					+ "\" class=\"btn btn-sm btn-primary me-1\"><i class=\"bi bi-pencil-fill\"></i>");
			out.append("</a>");
			out.append("<a href=\"#\" class=\"btn btn-sm btn-danger me-1\" data-bs-toggle=\"modal\" ");
			out.append("data-bs-target=\"#staticBackdrop_" + to.getTourUnitId() + "\">");
			out.append("<i class=\"bi bi-trash3-fill\"></i></a>");
			out.append("<a href=\"#\" class=\"btn btn-sm btn-secondary\" data-bs-toggle=\"modal\" ");
			out.append("data-bs-target=\"#tourUnitDetailModal_" + to.getTourUnitId() + "\">");
			out.append("<i class=\"bi bi-info-circle\"></i></a>");
			out.append("</td>");
			out.append("</tr>");
			out.append("<!-- Modal Xem Chi Tiết -->");
			out.append(TourUnitServlet.getDetailModal(to));
			out.append("<!-- Modal Xoá -->");
			out.append(TourUnitServlet.getDelModal(to));
		});

		out.append("</tbody>");
		out.append("</table>");
		out.append("<!-- End Table with stripped rows -->");
		out.append("");
		out.append("</div>");
		out.append("</div>");
		out.append("");
		out.append("</div>");
		out.append("</div>");
		out.append("</section>");
		out.append("");
		out.append("  </main><!-- End #main -->");
		out.append("<script>");
		out.append("  setTimeout(function() {");
		out.append("    var alert = document.querySelector('.alert');");
		out.append("    if (alert) {");
		out.append("      alert.classList.remove('show');");
		out.append("      alert.classList.add('fade');");
		out.append("    }");
		out.append("  }, 3000);");
		
		out.append("let current = 1;");
		out.append("const tourId = "+tour_id+";");
		 out.append(" document.getElementById(\"load\").addEventListener(\"click\", function (e) {");
		 out.append("   e.preventDefault(); ");
		 out.append("   current++;");
		out.append("");
		 out.append("   const newUrl = `?tour_id=${tourId}&recordArea=${current}`;");
		 out.append("   window.location.href = newUrl; ");
		 out.append(" });");
		out.append("</script>");

		RequestDispatcher fo = request.getRequestDispatcher("/fo");
		fo.include(request, response);


		out.append(
				"  <a href=\"#\" class=\"back-to-top d-flex align-items-center justify-content-center\"><i class=\"bi bi-arrow-up-short\"></i></a>");
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
		out.append("");
		out.append("</body>");
		out.append("");
		out.append("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(req, resp);
	}

	
	private static StringBuilder getDetailModal(TourUnit to) {
	    StringBuilder out = new StringBuilder("");
	    out.append("<!-- Modal Xem Chi Tiết -->");
	    out.append("<div class=\"modal fade\" id=\"tourUnitDetailModal_" + to.getTourUnitId()
	            + "\" tabindex=\"-1\" aria-labelledby=\"tourUnitDetailModalLabel_" + to.getTourUnitId()
	            + "\" aria-hidden=\"true\">");
	    out.append("  <div class=\"modal-dialog modal-lg\">");
	    out.append("    <div class=\"modal-content\">");
	    out.append("<div class=\"modal-header\">");
	    out.append("<h5 class=\"modal-title\" id=\"tourUnitDetailModalLabel_" + to.getTourUnitId()
	            + "\">Chi tiết đơn vị tour</h5>");
	    out.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
	    out.append("</div>");
	    out.append("<div class=\"modal-body\">");
	    out.append("<div class=\"row\">");

	    // Cột trái
	    out.append("<div class=\"col-md-6\">");
	    out.append("<p><strong><i class='bi bi-hash'></i> ID đơn vị tour:</strong> <span>" + to.getTourUnitId() + "</span></p>");
	    out.append("<p><strong><i class='bi bi-geo-alt'></i> Trực thuộc tour:</strong> <span>" + to.getTour().getTourName() + "</span></p>");
	    out.append("<p><strong><i class='bi bi-person'></i> Giá người lớn:</strong> <span>" + to.getAdultTourPrice() + "</span></p>");
	    out.append("<p><strong><i class='bi bi-emoji-smile'></i> Giá trẻ em:</strong> <span>" + to.getChildTourPrice() + "</span></p>");
	    out.append("<p><strong><i class='bi bi-person-badge'></i> Giá trẻ em 6-10 tuổi:</strong> <span>" + to.getToddlerTourPrice() + "</span></p>");
	    out.append("<p><strong><i class='bi bi-baby'></i> Giá trẻ sơ sinh:</strong> <span>" + to.getBabyTourPrice() + "</span></p>");
	    out.append("<p><strong><i class='bi bi-house'></i> Giá phòng đơn:</strong> <span>" + to.getPrivateRoomPrice() + "</span></p>");
	    out.append("<p><strong><i class='bi bi-cash-coin'></i> Tổng giá dịch vụ:</strong> <span>" + to.getTotalAdditionalCost() + "</span></p>");
	    if(to.getFestival()!= null)
	        out.append("<p><strong><i class='bi bi-calendar-event'></i> Lễ hội:</strong> <span>" + to.getFestival().getFestivalName() + "</span></p>");
	    if (to.getDiscount() != null)
	        out.append("<p><strong><i class='bi bi-tag'></i> Giảm giá:</strong> <span>" + to.getDiscount().getDiscountName() + "</span></p>");
	    out.append("</div>");

	    // Cột phải
	    out.append("<div class=\"col-md-6\">");
	    out.append("<p><strong><i class='bi bi-box-arrow-right'></i> Ngày khởi hành:</strong> <span>" + to.getDepartureDate() + "</span></p>");
	    out.append("<p><strong><i class='bi bi-box-arrow-left'></i> Ngày trở về:</strong> <span>" + to.getReturnDate() + "</span></p>");
	    out.append("<p><strong><i class='bi bi-currency-dollar'></i> Chi phí người lớn:</strong> <span>" + to.getAdultTourCost() + "</span></p>");
	    out.append("<p><strong><i class='bi bi-currency-dollar'></i> Chi phí trẻ em:</strong> <span>" + to.getChildTourCost() + "</span></p>");
	    out.append("<p><strong><i class='bi bi-currency-dollar'></i> Chi phí trẻ 6-10 tuổi:</strong> <span>" + to.getToddlerTourCost() + "</span></p>");
	    out.append("<p><strong><i class='bi bi-currency-dollar'></i> Chi phí trẻ sơ sinh:</strong> <span>" + to.getBabyTourCost() + "</span></p>");
	    out.append("<p><strong><i class='bi bi-people'></i> Số người tham gia tối đa:</strong> <span>" + to.getMaximumCapacity() + "</span></p>");
	    out.append("<p><strong><i class='bi bi-check-circle'></i> Còn lại (chỗ):</strong> <span>" + to.getAvailableCapacity() + "</span></p>");
	    out.append("<p><strong><i class='bi bi-person-gear'></i> Người điều hành:</strong> <span>" + to.getTourOperator().getFirstname() + " " + to.getTourOperator().getLastname() + "</span></p>");
	    out.append("</div>");

	    out.append("</div>"); // end row
	    out.append("</div>"); // end modal-body
	    out.append("<div class=\"modal-footer\">");
	    out.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Đóng</button>");
	    out.append("</div>");
	    out.append("</div>");
	    out.append("  </div>");
	    out.append("</div>");

	    return out;
	}

	private static StringBuilder getDelModal(TourUnit to)
	{
		StringBuilder out = new StringBuilder("");
		out.append("<div class=\"modal\" id=\"staticBackdrop_" + to.getTourUnitId()
				+ "\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\"");
		out.append("aria-labelledby=\"staticBackdropLabel_" + to.getTourUnitId() + "\" aria-hidden=\"true\">");
		out.append("<div class=\"modal-dialog modal-dialog-centered\">");
		out.append("<div class=\"modal-content\">");

		out.append("<div class=\"modal-header bg-danger\">");
		out.append("<h5 class=\"modal-title text-light fw-bold fs-4\" id=\"staticBackdropLabel_"
				+ to.getTourUnitId() + "\">Xác nhận xóa</h5>");
		out.append(
				"<button type=\"button\" class=\"btn-close fs-3\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		out.append("</div>");

		out.append("<div class=\"modal-body\">");
		out.append("Bạn có muốn xóa &lt;" + to.getTourUnitId() + "&gt;?");
		out.append("</div>");

		out.append("<div class=\"modal-footer\">");
		out.append("<button type=\"button\" class=\"btn btn-danger\" data-bs-dismiss=\"modal\">Hủy</button>");
		out.append("<a href=\"Tour-Unit-Delete?tourUnitId=" + to.getTourUnitId()
				+ "\" class=\"btn btn-primary\"><i class=\"bi bi-trash3-fill me-2\"></i>Xác nhận</a>");
		out.append("</div>");

		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		return out;
	}
}

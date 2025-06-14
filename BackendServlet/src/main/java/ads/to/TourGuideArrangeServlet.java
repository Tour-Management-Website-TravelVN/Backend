package ads.to;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import ads.objects.TourGuide;
import ads.objects.TourOperator;
import ads.objects.TourUnit;
import ads.user.BookingFunctionImpl;
import ads.user.TourGuideFunction;
import ads.user.TourGuideFunctionImpl;
import ads.user.TourUnitFunctionImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/to/tour/Arrange-Tour-Guide")
public class TourGuideArrangeServlet extends HttpServlet {
	private TourGuideFunction tourGuideFunction = new TourGuideFunctionImpl();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		 String message = req.getParameter("message");
			String error = req.getParameter("error");
		
		 RequestDispatcher h = req.getRequestDispatcher("/he");
		 RequestDispatcher side = req.getRequestDispatcher("/side");
		 RequestDispatcher foot = req.getRequestDispatcher("/fo");

		String tour_unit_id = req.getParameter("tourUnitId");
		ArrayList<TourGuide> tourGuides = tourGuideFunction.getTourGuidesCurrentWorking();
		
		PrintWriter out = resp.getWriter();
		
		out.append("<!DOCTYPE html>");
		out.append("<html lang=\"en\">");
		out.append("");
		out.append("<head>");
		out.append("  <meta charset=\"utf-8\">");
		out.append("  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">");
		out.append("");
		out.append("  <title>Chọn hướng dẫn viên</title>");
		out.append("  <meta content=\"\" name=\"description\">");
		out.append("  <meta content=\"\" name=\"keywords\">");
		out.append("");
		out.append("  <!-- Favicons -->");
		out.append("  <link href=\"../../assets/img/Logo.svg\" rel=\"icon\"> ");
		out.append("  <link href=\"../../assets/img/apple-touch-icon.png\" rel=\"apple-touch-icon\">");
		out.append("");
		out.append("  <!-- Google Fonts -->");
		out.append("  <link href=\"https://fonts.gstatic.com\" rel=\"preconnect\">");
		out.append("  <link");
		out.append("    href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i\"");
		out.append("    rel=\"stylesheet\">");
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
		h.include(req, resp);
		side.include(req, resp);
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
			out.append("    const error1 = getParameterByName('error1');");
			out.append("    const error = getParameterByName('error');");
			out.append("    const modalMessage = document.getElementById('modalMessage');");
			out.append("");
			out.append("    if (message) {");
			out.append("      modalMessage.textContent = \"Cập nhật thành công! " + message + "\";");
			out.append("      var modal = new bootstrap.Modal(document.getElementById('notificationModal'));");
			out.append("      modal.show();");
			out.append("    }else if (error1) {");
			out.append("      modalMessage.textContent = \"Vui lòng chọn ít nhất 1 người hướng dẫn viên\";");
			out.append("      var modal = new bootstrap.Modal(document.getElementById('notificationModal'));");
			out.append("      modal.show();");
			out.append("    } else if (error) {");
			out.append("      modalMessage.textContent = \"Cập nhật thất bại! " + error + "\";");
			out.append("      var modal = new bootstrap.Modal(document.getElementById('notificationModal'));");
			out.append("      modal.show();");
			out.append("    }");
			out.append("  }");
			out.append("</script>");
		
		out.append("");
		 out.append("  <main id=\"main\" class=\"main\">");
		 out.append("");
		 out.append("    <div class=\"pagetitle d-flex\">");
		 out.append("      <h1>Lựa chọn hướng dẫn viên</h1>");
		 out.append("      <nav class=\"ms-auto\">");
		 out.append("        <ol class=\"breadcrumb\">");
		 out.append("          <li class=\"breadcrumb-item\"><i class=\"bi bi-house-door\"></i></a></li>");
		 out.append("          <li class=\"breadcrumb-item\">Lựa chọn hướng dẫn viên</li>");
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
		 out.append("<form method=\"post\" action=\"?tourUnitId="+tour_unit_id+"\">");
		 out.append("<div class=\"d-flex flex-wrap gap-2 mb-3\">");
		 out.append("  <button name=\"action\" value=\"approve\" class=\"btn btn-success mt-2\"><i class=\"bi bi-check2-circle\"></i> Chọn các hướng dẫn viên </button>");
		 out.append("</div>");

		 out.append("<div class=\"table-responsive\">");
		 out.append("  <table class=\"table table-striped datatable\" style=\"font-size: small;\">");

		 out.append("    <thead>");
		 out.append("      <tr>");
		 out.append("        <th><b>Họ và tên</b></th>");
		 out.append("        <th>Ngày sinh</th>");
		 out.append("        <th>Số điện thoại</th>");
		 out.append("        <th>Số căn cước</th>");
		 out.append("        <th>ID thẻ</th>");
		 out.append("        <th>Ngày hết hợp đồng</th>");
		 out.append("        <th>Lựa chọn</th>");
		 out.append("      </tr>");
		 out.append("    </thead>");

		 out.append("    <tbody>");
		 if (tourGuides != null) {
			 tourGuides.forEach(tmp -> {
		         out.append("      <tr>");
		         out.append("        <td>" + tmp.getLastname() +" "+tmp.getFirstname() + "</td>");
		         out.append("        <td>" + tmp.getDateOfBirth() + "</td>");
		         out.append("        <td>" + tmp.getPhoneNumber() + "</td>");
		         out.append("        <td>" + tmp.getCitizenId() + "</td>");
		         out.append("        <td>" + tmp.getCardId() + "</td>");
		         if(tmp.getEndDate() != null)
		         {
			         String nd = (tmp.getEndDate().toString() != null) ? tmp.getEndDate().toString() : "&lt;trống&gt;";
			         out.append("        <td>" + nd + "</td>");

		         }
		         else
		         out.append("        <td>Vẫn đang làm việc</td>");
//		         out.append("        <td class=\"row\">");
//		         out.append("          <a class=\"btn btn-secondary btn-sm text-light col\" data-bs-toggle=\"modal\" data-bs-target=\"#bookingDetailModal_" + tmp.getBookingId() + "\"><i class=\"bi bi-info-circle\"></i></a>");
//		         out.append("        </td>");
		         out.append("        <td>");
		         out.append("          <input class=\"ms-4\" type=\"checkbox\" id=\"booking\" name=\"booking\" value=\"" + tmp.getId() + "\">");
		         out.append("        </td>");
		         out.append("      </tr>");
		     });
		 }
		 out.append("    </tbody>");
		 out.append("  </table>");
		 out.append("</div>");

			 out.append("              <!-- End Table with stripped rows -->");
			 out.append("</form>");
			 out.append("            </div>");
			 out.append("          </div>");
			 out.append("");
			 out.append("        </div>");
			 out.append("      </div>");
			 out.append("    </section>");
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
	    String[] bookingIds = req.getParameterValues("booking");
	    String tourUnitId = req.getParameter("tourUnitId");
	    TourUnit t  = TourUnitFunctionImpl.getInstance().getById(tourUnitId);
	    if (bookingIds == null || bookingIds.length == 0) {
	        // Không có checkbox nào được chọn
	        resp.sendRedirect("Arrange-Tour-Guide?tourUnitId="+tourUnitId+"&error1=No tour guide selected!");
	        return;
	    }
	    for(String s: bookingIds)
	    {
	    	boolean c = TourUnitFunctionImpl.getInstance().isGuide(Integer.parseInt(s), t.getDepartureDate(), t.getReturnDate());
	    	if(c)
	    	{
	    		System.out.println(c);
		        resp.sendRedirect("Tour-Unit?tour_id="+t.getTour().getTourId()+"&error=Updated Failed!&recordArea=1");
		        return;
	    	}    	
	    }
	    for(String s: bookingIds)
	    {
	    	boolean rs = TourUnitFunctionImpl.getInstance().arrangeTourGuide(Integer.parseInt(s), tourUnitId,10) ;	
	    }
        resp.sendRedirect("Tour-Unit?tour_id="+t.getTour().getTourId()+"&message=Updated Successfully!&recordArea=1");
        
	    
	}
}

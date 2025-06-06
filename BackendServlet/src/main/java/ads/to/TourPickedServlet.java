package ads.to;

import java.io.IOException;


import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ads.objects.Tour;
import ads.user.TourFunctionImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/to/tour/Tour-Picked")
public class TourPickedServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html; charset=UTF-8");

		 PrintWriter out = response.getWriter();
		 RequestDispatcher h = request.getRequestDispatcher("/he");
		 RequestDispatcher side = request.getRequestDispatcher("/side");
		 RequestDispatcher foot = request.getRequestDispatcher("/fo");
		 out.append("<!DOCTYPE html>");
			out.append("<html lang=\"en\">");
			out.append("");
			out.append("<head>");
			out.append("  <meta charset=\"utf-8\">");
			out.append("  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">");
			out.append("");
			out.append("  <title>Sửa đơn vị tour</title>");
			out.append("  <meta content=\"\" name=\"description\">");
			out.append("  <meta content=\"\" name=\"keywords\">");
			out.append("");
			out.append("  <!-- Favicons -->");
			out.append("  <link href=\"../../assets/img/Logo.svg\" rel=\"icon\"> ");
			out.append("  <link href=\"../../assets/img/apple-touch-icon.png\" rel=\"apple-touch-icon\">");
			out.append("");
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
		 h.include(request, response);
		 side.include(request, response);
		 List<Tour> list_tour =  TourFunctionImpl.getInstance().getAllTourNameAndId();

out.append("<div class=\"modal fade\" id=\"chooseTourModal\" tabindex=\"-1\" aria-labelledby=\"chooseTourLabel\" aria-hidden=\"true\"\r\n"
		+ "     data-bs-backdrop=\"static\" data-bs-keyboard=\"false\">");
out.append("  <div class=\"modal-dialog modal-dialog-centered\">");
out.append("    <div class=\"modal-content\">");
out.append("      <form id=\"tourForm\" action=\"Tour-Unit-Add\" method=\"get\">");
out.append("        <div class=\"modal-header\">");
out.append("          <h5 class=\"modal-title\" id=\"chooseTourLabel\">Lựa chọn tour</h5>");
out.append("          <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Đóng\"></button>");
out.append("        </div>");
out.append("        <div class=\"modal-body\">");
out.append("          <label for=\"tourSelect\" class=\"form-label\">Chọn tour:</label>");

out.append("<select class=\"form-select\" id=\"tourId\" name=\"tourId\" required>");
for(Tour d: list_tour )
{
	 out.append("<option value=\""+d.getTourId()+"\">"+d.getTourName()+"</option> ");

}
out.append("          </select>");
out.append("        </div>");
out.append("        <div class=\"modal-footer\">");
out.append("          <button type=\"submit\" class=\"btn btn-success\">Xác nhận</button>");
out.append("          <a href=\"Tour-Unit\" class=\"btn btn-secondary\" >Hủy</a>");
out.append("        </div>");
out.append("      </form>");
out.append("    </div>");
out.append("  </div>");
out.append("</div>");
out.append("<script>");
out.append("  window.addEventListener('load', function() {");
out.append("    var myModal = new bootstrap.Modal(document.getElementById('chooseTourModal'));");
out.append("    myModal.show();");
out.append("  });");

out.append("</script>");
out.append("<script>");
out.append("  $(document).ready(function() {");
out.append("    $('#tourForm').select2({");
out.append("      placeholder: \"-- Chọn tour --\",");
out.append("      width: '100%'");
out.append("    });");
out.append("  });");
out.append("</script>");

		 foot.include(request, response);
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
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(req,response);
		
		

	}
	
	

}

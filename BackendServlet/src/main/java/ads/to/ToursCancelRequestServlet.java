package ads.to;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ads.objects.Booking;
import ads.user.BookingFunctionImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/to/Cancel-Request")
public class ToursCancelRequestServlet extends HttpServlet{

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
		 List<Booking> list_b = new ArrayList<Booking>();
		 list_b = BookingFunctionImpl.getInstance().getBookingsWait(1);
		 String message = request.getParameter("message");
		String error = request.getParameter("error");

		 out.append("<!DOCTYPE html>");
		 out.append("<html lang=\"en\">");
		 out.append("");
		 out.append("<head>");
		 out.append("  <meta charset=\"utf-8\">");
		 out.append("  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">");
		 out.append("");
		 out.append("  <title>Yêu cầu huỷ tour</title>");
		 out.append("  <meta content=\"\" name=\"description\">");
		 out.append("  <meta content=\"\" name=\"keywords\">");
		 out.append("");
		 out.append("  <!-- Favicons -->");
		 out.append("  <link href=\"assets/img/favicon.png\" rel=\"icon\">");
		 out.append("  <link href=\"assets/img/apple-touch-icon.png\" rel=\"apple-touch-icon\">");
		 out.append("");
		 out.append("  <!-- Google Fonts -->");
		 out.append("  <link href=\"https://fonts.gstatic.com\" rel=\"preconnect\">");
		 out.append("  <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i\" rel=\"stylesheet\">");
		 out.append("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css\">\r\n"
		 		+ "");
		 out.append("  <!-- Vendor CSS Files -->");
			out.append("  <link href=\"../assets/vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");
			out.append("  <link href=\"../assets/vendor/bootstrap-icons/bootstrap-icons.css\" rel=\"stylesheet\">");
			out.append("  <link href=\"../assets/vendor/boxicons/css/boxicons.min.css\" rel=\"stylesheet\">");
			out.append("  <link href=\"../assets/vendor/quill/quill.snow.css\" rel=\"stylesheet\">");
			out.append("  <link href=\"../assets/vendor/quill/quill.bubble.css\" rel=\"stylesheet\">");
			out.append("  <link href=\"../assets/vendor/remixicon/remixicon.css\" rel=\"stylesheet\">");
			out.append("  <link href=\"../assets/vendor/simple-datatables/style.css\" rel=\"stylesheet\">");
			out.append("  <script src=\"https://kit.fontawesome.com/0b7fe90b3a.js\" crossorigin=\"anonymous\"></script>");
			out.append("  <script src=\"/adv/assets/vendor/jquery/jquery-3.7.1.min.js\"></script>");
			out.append("  <!-- Template Main CSS File -->");
			out.append("  <link href=\"../css/style.css\" rel=\"stylesheet\">");
		
		 out.append("</head>");
		 out.append("");
		 out.append("<body>");
		 out.append("");
		 RequestDispatcher h = request.getRequestDispatcher("/he");
		 if (h != null) {
				h.include(request, response);
			}
		 RequestDispatcher side = request.getRequestDispatcher("/side");
		 if (side != null) {
				side.include(request, response);
			}
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
			out.append("      modalMessage.textContent = \"Vui lòng chọn ít nhất 1 giao dịch\";");
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
		 out.append("    <div class=\"pagetitle d-flex\">");
		 out.append("      <h1>Duyệt yêu cầu huỷ tour</h1>");
		 out.append("      <nav class=\"ms-auto\">");
		 out.append("        <ol class=\"breadcrumb\">");
		 out.append("          <li class=\"breadcrumb-item\"><i class=\"bi bi-house-door\"></i></a></li>");
		 out.append("          <li class=\"breadcrumb-item\">Duyệt yêu cầu huỷ tour</li>");
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
		 out.append("<form method=\"post\" action=\"\">");
		 out.append("<div class=\"d-flex flex-wrap gap-2 mb-3\">");
		 out.append("  <button name=\"action\" value=\"approve\" class=\"btn btn-success\"><i class=\"bi bi-check2-circle\"></i> Đồng ý tất cả lựa chọn</button>");
		 out.append("  <button name=\"action\" value=\"reject\" class=\"btn btn-danger\"><i class=\"bi bi-x-circle\"></i> Từ chối tất cả lựa chọn</button>");
		 out.append("</div>");

		 out.append("<!-- Table with stripped rows and responsive wrapper -->");
		 out.append("<div class=\"table-responsive\">");
		 out.append("  <table class=\"table table-striped datatable\" style=\"font-size: small;\">");

		 out.append("    <thead>");
		 out.append("      <tr>");
		 out.append("        <th><b>Mã đặt tour</b></th>");
		 out.append("        <th>Ngày đặt tour</th>");
		 out.append("        <th>Số lượng trẻ sơ sinh</th>");
		 out.append("        <th>Số lượng trẻ em</th>");
		 out.append("        <th>Số lượng người lớn</th>");
		 out.append("        <th>Ghi chú</th>");
		 out.append("        <th>Tuỳ chọn</th>");
		 out.append("        <th>Lựa chọn</th>");
		 out.append("      </tr>");
		 out.append("    </thead>");

		 out.append("    <tbody>");
		 if (list_b != null) {
		     list_b.forEach(tmp -> {
		         out.append("      <tr>");
		         out.append(getDetailModal(tmp).toString());
		         out.append("        <td>" + tmp.getBookingId() + "</td>");
		         out.append("        <td>" + tmp.getBookingDate() + "</td>");
		         out.append("        <td>" + tmp.getBabyNumber() + "</td>");
		         out.append("        <td>" + tmp.getChildNumber() + "</td>");
		         out.append("        <td>" + tmp.getAdultNumber() + "</td>");
		         String nd = (tmp.getNote() != null) ? tmp.getNote() : "&lt;trống&gt;";
		         out.append("        <td>" + nd + "</td>");
		         out.append("        <td class=\"row\">");
		         out.append("          <a class=\"btn btn-secondary btn-sm text-light col\" data-bs-toggle=\"modal\" data-bs-target=\"#bookingDetailModal_" + tmp.getBookingId() + "\"><i class=\"bi bi-info-circle\"></i></a>");
		         out.append("        </td>");
		         out.append("        <td>");
		         out.append("          <input class=\"ms-4\" type=\"checkbox\" id=\"booking\" name=\"booking\" value=\"" + tmp.getBookingId() + "\">");
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
		 out.append("      &copy; Copyright <strong><span>NiceAdmin</span></strong>. All Rights Reserved");
		 out.append("    </div>");
		 out.append("    <div class=\"credits\">");
		 out.append("      <!-- All the links in the footer should remain intact. -->");
		 out.append("      <!-- You can delete the links only if you purchased the pro version. -->");
		 out.append("      <!-- Licensing information: https://bootstrapmade.com/license/ -->");
		 out.append("      <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->");
		 out.append("      Designed by <a href=\"https://bootstrapmade.com/\">BootstrapMade</a>");
		 out.append("    </div>");
		 out.append("  </footer><!-- End Footer -->");
		 out.append("");
		 out.append("  <a href=\"#\" class=\"back-to-top d-flex align-items-center justify-content-center\"><i class=\"bi bi-arrow-up-short\"></i></a>");
		 out.append("");
		 out.append("  <!-- Vendor JS Files -->");
			out.append("  <script src=\"../assets/vendor/apexcharts/apexcharts.min.js\"></script>");
			out.append("  <script src=\"../assets/vendor/bootstrap/js/bootstrap.bundle.min.js\"></script>");
			out.append("  <script src=\"../assets/vendor/chart.js/chart.umd.js\"></script>");
			out.append("  <script src=\"../assets/vendor/echarts/echarts.min.js\"></script>");
			out.append("  <script src=\"../assets/vendor/quill/quill.js\"></script>");
			out.append("  <script src=\"../assets/vendor/simple-datatables/simple-datatables.js\"></script>");
			out.append("  <script src=\"../assets/vendor/tinymce/tinymce.min.js\"></script>");
			out.append("  <script src=\"../assets/vendor/php-email-form/validate.js\"></script>");
			out.append("  <!-- Template Main JS File -->");
			out.append("  <script src=\"../assets/js/main.js\"></script>");
			out.append("");
		 out.append("");
		 out.append("</body>");
		 out.append("");
		 out.append("</html>");                                                                                                                                                                                                                                                           
		    
	}
	private static StringBuilder getDetailModal(Booking b)
	{
		StringBuilder out  = new StringBuilder();
		out.append("<!-- Modal xem chi tiết Booking -->");
		out.append("<div class=\"modal fade\" id=\"bookingDetailModal_"+b.getBookingId()+"\" tabindex=\"-1\" aria-labelledby=\"bookingDetailModalLabel_"+b.getBookingId()+"\" aria-hidden=\"true\" >");
		out.append("  <div class=\"modal-dialog modal-lg modal-dialog-centered\">");
		out.append("    <div class=\"modal-content shadow\">");
		out.append("      <div class=\"modal-header bg-primary text-white\">");
		out.append("        <h5 class=\"modal-title\"><i class=\"bi bi-receipt\"></i> Chi tiết đơn đặt tour</h5>");
		out.append("        <button type=\"button\" class=\"btn-close btn-close-white\" data-bs-dismiss=\"modal\"></button>");
		out.append("      </div>");
		out.append("      <div class=\"modal-body\">");
		out.append("        <dl class=\"row\">");
		out.append("          <dt class=\"col-sm-4\"><i class=\"bi bi-hash\"></i> Mã đơn: </dt>");
		out.append("          <dd class=\"col-sm-8\" id=\"bookingId\">"+b.getBookingId()+"</dd>");
		out.append("");
		out.append("          <dt class=\"col-sm-4\"><i class=\"bi bi-person\"></i> Khách hàng: </dt>");
		out.append("          <dd class=\"col-sm-8\" id=\"customerName\">"+b.getC().getLastname()+" "+b.getC().getFirstname() +"</dd>");
		out.append("");
		out.append("          <dt class=\"col-sm-4\"><i class=\"bi bi-geo-alt\"></i> Tour: </dt>");
		out.append("          <dd class=\"col-sm-8\" id=\"tourId\">"+b.getTourUnit().getTourUnitId()+"</dd>");
		out.append("");
		out.append("          <dt class=\"col-sm-4\"><i class=\"bi bi-calendar\"></i> Ngày đặt:</dt>");
		out.append("          <dd class=\"col-sm-8\" id=\"bookingDate\">"+b.getBookingDate()+"</dd>");
		out.append("");
		out.append("          <dt class=\"col-sm-4\"><i class=\"bi bi-info-circle\"></i> Trạng thái:</dt>");
		out.append("          <dd class=\"col-sm-8\" id=\"bookingStatus\">"+b.getStatus()+"</dd>");
		out.append("");
		out.append("          <dt class=\"col-sm-4\"><i class=\"bi bi-chat-left-text\"></i> Ghi chú:</dt>");
		String nd = (b.getNote() != null) ? b.getNote():" < trống >";
		out.append("          <dd class=\"col-sm-8\" id=\"bookingNote\">"+nd+"</dd>");
		out.append("");
		out.append("          <dt class=\"col-sm-4\"><i class=\"bi bi-people-fill\"></i> Số Người lớn:</dt>");
		out.append("          <dd class=\"col-sm-8\" id=\"adultNumber\">"+b.getAdultNumber()+"</dd>");
		out.append("");
		out.append("          <dt class=\"col-sm-4\"><i class=\"bi bi-emoji-smile\"></i> Trẻ em:</dt>");
		out.append("          <dd class=\"col-sm-8\" id=\"childNumber\">"+b.getChildNumber()+"</dd>");
		out.append("");
		out.append("          <dt class=\"col-sm-4\"><i class=\"bi bi-baby\"></i> Em bé:</dt>");
		out.append("          <dd class=\"col-sm-8\" id=\"babyNumber\">"+b.getBabyNumber()+"</dd>");
		out.append("");
		out.append("          <dt class=\"col-sm-4\"><i class=\"bi bi-door-closed\"></i> Phòng riêng:</dt>");
		out.append("          <dd class=\"col-sm-8\" id=\"privateRoomNumber\">"+b.getPrivateRoomNumber()+"</dd>");
		out.append("");
		out.append("          <dt class=\"col-sm-4\"><i class=\"bi bi-cash-coin\"></i> Tổng tiền:</dt>");
		out.append("          <dd class=\"col-sm-8\" id=\"totalAmount\">"+b.getTotalAmount()+"</dd>");
		out.append("");
//		out.append("          <dt class=\"col-sm-4\"><i class=\"bi bi-credit-card\"></i> Mã thanh toán:</dt>");
//		out.append("          <dd class=\"col-sm-8\" id=\"paymentId\">"+b.getPaymentId()+"</dd>");
//		out.append("");
		out.append("          <dt class=\"col-sm-4\"><i class=\"bi bi-upc-scan\"></i> Mã đơn hàng:</dt>");
		out.append("          <dd class=\"col-sm-8\" id=\"orderCode\">"+b.getOrderCode()+"</dd>");
		out.append("        </dl>");
		out.append("");
		out.append("        <h6><i class=\"bi bi-people\"></i> Người đi cùng:</h6>");
		out.append("        <ul id=\"companionList\" class=\"list-group\">");
		out.append("          <!-- companionCustomerSet sẽ render ở đây -->");
		out.append("        </ul>");
		out.append("      </div>");
		out.append("    </div>");
		out.append("  </div>");
		out.append("</div>");
		out.append("");
		return out;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String[] bookingIds = req.getParameterValues("booking");
	    String action = req.getParameter("action");

	    boolean isApproved = action.equalsIgnoreCase("approved");
	    if (bookingIds == null || bookingIds.length == 0) {
	        // Không có checkbox nào được chọn
	        resp.sendRedirect("Cancel-Request?error1=No bookings selected!");
	        return;
	    }
	    System.out.println(bookingIds[0]);
	    boolean result = BookingFunctionImpl.getInstance()
	                        .editBookingCancelRequest(Arrays.asList(bookingIds), isApproved);
	    
	    if (result) {
	        resp.sendRedirect("Cancel-Request?message=Updated Successfully!");
	    } else {
	        resp.sendRedirect("Cancel-Request?error=Updated Failed!");
	    }
	}

	
	

}

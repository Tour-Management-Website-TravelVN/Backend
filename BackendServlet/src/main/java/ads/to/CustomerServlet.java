package ads.to;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

import ads.JDBCconfig;
import ads.objects.Customer;
import ads.objects.TourUnit;
import ads.user.CustomerFunctionImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/to/Customer")
public class CustomerServlet extends HttpServlet{

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
		out.append("<!DOCTYPE html>");
		out.append("<html lang=\"en\">");
		out.append("");
		out.append("<head>");
		out.append("  <meta charset=\"utf-8\">");
		out.append("  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">");
		out.append("");
		out.append("  <title>Khách hàng</title>");
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
		out.append("");
		out.append("  <!-- Vendor CSS Files -->");
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
		out.append("");
		
		out.append("</head>");
		out.append("");
		out.append("<body>");
		out.append("");
		RequestDispatcher h = request.getRequestDispatcher("/he");
		if (h != null) {
			h.include(request, response);
		}
		RequestDispatcher s = request.getRequestDispatcher("/side");
		if (s != null) {
			s.include(request, response);
		}
		String message = request.getParameter("message");
		String error = request.getParameter("error");
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
		out.append("");
		out.append("  <main id=\"main\" class=\"main\">");
		out.append("");
		out.append("    <div class=\"pagetitle d-flex\">");
		out.append("      <h1>Danh sách khách hàng</h1>");
		out.append("      <nav class=\"ms-auto\">");
		out.append("        <ol class=\"breadcrumb\">");
		out.append("          <li class=\"breadcrumb-item\"><i class=\"bi bi-house-door\"></i></a></li>");
		out.append("          <li class=\"breadcrumb-item\">Quản lý thông tin khách hàng</li>");
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
		out.append("              <!-- Table with stripped rows -->");
		out.append("              <table class=\"table datatable table-striped\" style=\"font-size: smaller;\">");
		out.append("                <thead>");
		out.append("                  <tr class=\"d-flex\">");
		out.append("                    <div class=\"row\">");
		out.append("                    <div class=\"col-md-4\">");
		out.append("                      <select id=\"tourUnitSelect\" class=\"form-select mt-3\">");
		out.append("                        <option value=\"\" selected disabled>Chọn mã đơn vị tour</option>");
		out.append("                        <option value=\"T001\">T001 - Đơn vị A</option>");

		out.append("                      </select>");
		out.append("                    </div>");
		out.append("              </div>");
		out.append("                  </tr>");
		out.append("                  <tr>");
		out.append("                    <th>");
		out.append("                      <b>Họ và tên</b>");
		out.append("                    </th>");
		out.append("                    <th>Giới tính</th>");
		out.append("                    <th>Quốc tịch</th>");
		out.append("                    <th data-type=\"date\" data-format=\"YYYY/DD/MM\">Ngày sinh</th>");
		out.append("                    <th>Số căn cước/passport</th>");
		out.append("                    <th>Số điện thoại</th>");
		out.append("                    <th>Tuỳ chọn</th>");
		out.append("                  </tr>");
		out.append("                </thead>");
		out.append("                <tbody>");
		
		ArrayList<Customer> tmp =  CustomerFunctionImpl.getInstance().getCustomers(1);
		tmp.forEach(c ->{
		out.append("                  <tr>");
		out.append("                    <td>");
		out.append("                      <b>"+c.getLastname()+" "+c.getFirstname()+"</b>");
		out.append("                    </td>"); 
		String gender = (c.getGender())?"Nam" :"Nữ";
		out.append("                    <td>"+gender+"</td>");
		out.append("                    <td>"+c.getNationality()+"</td>");
		out.append("                    <td data-type=\"date\" data-format=\"YYYY/DD/MM\">"+c.getDateOfBirth()+"</td>");
		out.append("                    <td>"+c.getPassport()+"</td>");
		out.append("                    <td>"+c.getPhoneNumber()+"</td>");
		
		out.append("<td >");
		out.append("<a href=\"#\" class=\"btn btn-sm btn-primary me-1\" data-bs-toggle=\"modal\" ");
		out.append("data-bs-target=\"#editCustomerModal_" + c.getId() + "\">");
		out.append("<i class=\"bi bi-pen-fill\"></i></a>");
		out.append("<a href=\"#\" class=\"btn btn-sm btn-danger me-1\" data-bs-toggle=\"modal\" ");
		out.append("data-bs-target=\"#staticBackdrop_" + c.getId() + "\">");
		out.append("<i class=\"bi bi-trash3-fill\"></i></a>");
		out.append("<a href=\"#\" class=\"btn btn-sm btn-secondary\" data-bs-toggle=\"modal\" ");
		out.append("data-bs-target=\"#tourUnitDetailModal_" + c.getId() + "\">");
		out.append("<i class=\"bi bi-info-circle\"></i></a>");
		out.append("</td>");
		out.append("                  </tr>");
		out.append("<!-- Modal Xem Chi Tiết -->");
		out.append(getDetailModal(c));
		out.append("<!-- Modal Xoá -->");
		out.append(getDelModal(c));
		out.append(getEditModal(c));
		});
		out.append("                </tbody>");
	
		out.append("              </table>");
		out.append("              <!-- End Table with stripped rows -->");
		out.append("");
		out.append("            </div>");
		out.append("          </div>");
		out.append("");
		out.append("        </div>");
		out.append("      </div>");
		out.append("    </section>");
		out.append("");
		out.append("  </main><!-- End #main -->");
		out.append("");
		RequestDispatcher fo = request.getRequestDispatcher("/fo");
		fo.include(request, response);

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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		Connection c = JDBCconfig.ind
		
		
		doGet(req, resp);
	}
	private static StringBuilder getDelModal(Customer to)
	{
		StringBuilder out = new StringBuilder("");
		out.append("<div class=\"modal\" id=\"staticBackdrop_" + to.getId()
				+ "\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\"");
		out.append("aria-labelledby=\"staticBackdropLabel_" + to.getId() + "\" aria-hidden=\"true\">");
		out.append("<div class=\"modal-dialog modal-dialog-centered\">");
		out.append("<div class=\"modal-content\">");

		out.append("<div class=\"modal-header bg-danger\">");
		out.append("<h5 class=\"modal-title text-light fw-bold fs-4\" id=\"staticBackdropLabel_"
				+ to.getId() + "\">Xác nhận xóa</h5>");
		out.append(
				"<button type=\"button\" class=\"btn-close fs-3\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		out.append("</div>");

		out.append("<div class=\"modal-body\">");
		out.append("Bạn có muốn xóa &lt;" + to.getLastname()+" "+ to.getFirstname() + "&gt;?");
		out.append("</div>");

		out.append("<div class=\"modal-footer\">");
		out.append("<button type=\"button\" class=\"btn btn-danger\" data-bs-dismiss=\"modal\">Hủy</button>");
		out.append("<a href=\"Customer-delete?c_id=" + to.getId()
				+ "\" class=\"btn btn-primary\"><i class=\"bi bi-trash3-fill me-2\"></i>Xác nhận</a>");
		out.append("</div>");

		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		return out;
	}
	
	private static StringBuilder getDetailModal(Customer to )
	{
		StringBuilder out  = new StringBuilder("");
		out.append("<!-- Modal Xem Chi Tiết -->");
		out.append("<div class=\"modal fade\" id=\"tourUnitDetailModal_" + to.getId()
				+ "\" tabindex=\"-1\" aria-labelledby=\"tourUnitDetailModalLabel_" + to.getId()
				+ "\" aria-hidden=\"true\">");
		out.append("  <div class=\"modal-dialog modal-lg\">");
		out.append("    <div class=\"modal-content\">");
		out.append("<div class=\"modal-header\">");
		out.append("<h5 class=\"modal-title\" id=\"tourUnitDetailModalLabel_" + to.getId()
				+ "\">Chi tiết Khách hàng</h5>");
		out.append(
				"<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		out.append("</div>");
		out.append("<div class=\"modal-body\">");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-md-6\">");
		out.append("<p><strong>ID Khách hàng:</strong> <span id=\"tourUnitId\">" + to.getId()
				+ "</span></p>");
		out.append("<p><strong>Họ và tên</strong> <span id=\"tourUnitId\">" + to.getLastname() + " "+ to.getFirstname()
				+ "</span></p>");
		String gender = (to.getGender())?"Nam" :"Nữ";
		String text = to.getNote() == null ?"Trống":to.getNote();

		out.append("<p><strong>Giới tính:</strong> <span id=\"adultPrice\">" + gender
				+ "</span></p>");
		out.append("<p><strong>Ngày sinh:</strong> <span id=\"status\">" + to.getDateOfBirth()
				+ "</span></p>");
		out.append("<p><strong>Số hộ chiếu:</strong> <span id=\"childPrice\">" + to.getPassport()
				+ "</span></p>");
		out.append("<p><strong>Số điện thoại:</strong> <span id=\"status\">" + to.getPhoneNumber()
				+ "</span></p>");
		out.append("<p><strong>Quốc tịch:</strong> <span id=\"status\">" + to.getNationality()
				+ "</span></p>");
		out.append("<p><strong>Địa chỉ:</strong> <span id=\"status\">" + to.getAddress()
				+ "</span></p>");
		out.append("<p><strong>Ghi chú:</strong> <span id=\"status\">" + text
				+ "</span></p>");
		
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"modal-footer\">");
		out.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Đóng</button>");
		out.append("</div>");
		out.append("</div>");
		out.append("  </div>");
		out.append("</div>");
		
		
		
		return out;
	}
	private static StringBuilder getEditModal(Customer to) {
	    StringBuilder out = new StringBuilder("");
	    out.append("<!-- Modal Sửa Khách Hàng -->");
	    out.append("<div class=\"modal fade\" id=\"editCustomerModal_" + to.getId() + "\" tabindex=\"-1\" aria-labelledby=\"editCustomerModalLabel_" + to.getId() + "\" aria-hidden=\"true\">");
	    out.append("  <div class=\"modal-dialog modal-lg\">");
	    out.append("    <div class=\"modal-content\">");

	    // Header
	    out.append("      <div class=\"modal-header\">");
	    out.append("        <h5 class=\"modal-title\" id=\"editCustomerModalLabel_" + to.getId() + "\">Sửa thông tin Khách hàng</h5>");
	    out.append("        <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
	    out.append("      </div>");

	    // Body (form)
	    out.append("      <div class=\"modal-body\">");
	    out.append("        <form method=\"post\" action=\"/adv/to/Customer-edit?c_id="+to.getId()+"\">"); // <-- đổi theo URL xử lý POST
	    out.append("          <input type=\"hidden\" name=\"id\" value=\"" + to.getId() + "\"required/>");

	    // Họ tên
	    out.append("          <div class=\"row mb-3\">");
	    out.append("            <div class=\"col-md-6\">");
	    out.append("              <label>Họ:</label>");
	    out.append("              <input type=\"text\" name=\"lastname\" class=\"form-control\" value=\"" + to.getLastname() + "\" required/>");
	    out.append("            </div>");
	    out.append("            <div class=\"col-md-6\">");
	    out.append("              <label>Tên:</label>");
	    out.append("              <input type=\"text\" name=\"firstname\" class=\"form-control\" value=\"" + to.getFirstname() + "\" required/>");
	    out.append("            </div>");
	    out.append("          </div>");
	    out.append("          <div class=\"row mb-3\">");

	    // Giới tính
	    out.append("          <div class=\"col-md-6 mb-3\">");
	    out.append("            <label>Giới tính:</label>");
	    out.append("            <select name=\"gender\" class=\"form-select\">");
	    out.append("              <option value=\"true\"" + (to.getGender() ? " selected" : "") + ">Nam</option>");
	    out.append("              <option value=\"false\"" + (!to.getGender() ? " selected" : "") + ">Nữ</option>");
	    out.append("            </select>");
	    out.append("          </div>");
	    // dob
	    out.append("          <div class=\"col-md-6 mb-3\">");
	    out.append("            <label>Ngày sinh:</label>");
	    out.append("            <input type=\"date\" name=\"dob\" class=\"form-control\" value=\"" + to.getDateOfBirth() + "\"/>");
	    out.append("          </div>");
	    
	    out.append("          </div>");

	    // Hộ chiếu
	    out.append("          <div class=\"mb-3\">");
	    out.append("            <label>Số hộ chiếu:</label>");
	    out.append("            <input type=\"text\" name=\"passport\" class=\"form-control\" value=\"" + to.getPassport() + "\" required/>");
	    out.append("          </div>");

	    // Số điện thoại
	    out.append("          <div class=\"mb-3\">");
	    out.append("            <label>Số điện thoại:</label>");
	    out.append("            <input type=\"text\" name=\"phone\" class=\"form-control\" value=\"" + to.getPhoneNumber() + "\" required/>");
	    out.append("          </div>");

	    // Quốc tịch
	    out.append("          <div class=\"mb-3\">");
	    out.append("            <label>Quốc tịch:</label>");
	    out.append("            <input type=\"text\" name=\"nationality\" class=\"form-control\" value=\"" + to.getNationality() + "\"/>");
	    out.append("          </div>");

	    // Địa chỉ
	    out.append("          <div class=\"mb-3\">");
	    out.append("            <label>Địa chỉ:</label>");
	    out.append("            <input type=\"text\" name=\"address\" class=\"form-control\" value=\"" + to.getAddress() + "\" required/>");
	    out.append("          </div>");

	    // Ghi chú
	    out.append("          <div class=\"mb-3\">");
	    out.append("            <label>Ghi chú:</label>");
	    out.append("            <textarea name=\"note\" class=\"form-control\" rows=\"3\">" + (to.getNote() == null ? "" : to.getNote()) + "</textarea>");
	    out.append("          </div>");

	    // Footer
	    out.append("          <div class=\"modal-footer\">");
	    out.append("            <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Hủy</button>");
	    out.append("            <button type=\"submit\" class=\"btn btn-primary\">Lưu thay đổi</button>");
	    out.append("          </div>");

	    out.append("        </form>");
	    out.append("      </div>");

	    out.append("    </div>");
	    out.append("  </div>");
	    out.append("</div>");

	    return out;
	}

	

}

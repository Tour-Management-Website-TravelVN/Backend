package ads.to;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

import ads.JDBCconfig;
import ads.objects.Customer;
import ads.user.CustomerFunctionImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/to/Customer-edit")
public class CustomerEditServlet extends HttpServlet{

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
		out.append("                    <th>Ghi chú</th>");
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
		String text = c.getNote() == null ?"Trống":c.getNote();
		out.append("                    <td>"+text+"</td>");
		out.append("<td >");
		out.append("<a  href=\"Tour-Unit-Edit?tourUnitId=" + c.getId()
				+ "\" class=\"btn btn-sm btn-primary me-1\"><i class=\"bi bi-pencil-fill\"></i>");
		out.append("</a>");
		out.append("<a href=\"#\" class=\"btn btn-sm btn-danger me-1\" data-bs-toggle=\"modal\" ");
		out.append("data-bs-target=\"#staticBackdrop_" + c.getId() + "\">");
		out.append("<i class=\"bi bi-trash3-fill\"></i></a>");
		out.append("<a href=\"#\" class=\"btn btn-sm btn-secondary\" data-bs-toggle=\"modal\" ");
		out.append("data-bs-target=\"#tourUnitDetailModal_" + c.getId() + "\">");
		out.append("<i class=\"bi bi-info-circle\"></i></a>");
		out.append("</td>");
		out.append("                  </tr>");
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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		Connection c = JDBCconfig.ind
		  String id = req.getParameter("id");
		    String firstname = req.getParameter("firstname");
		    String lastname = req.getParameter("lastname");
		    boolean gender = Boolean.parseBoolean(req.getParameter("gender"));
		    String passport = req.getParameter("passport");
		    String phone = req.getParameter("phone");
		    String nationality = req.getParameter("nationality");
		    String address = req.getParameter("address");
		    String note = req.getParameter("note");
		    LocalDate dob = LocalDate.parse(req.getParameter("dob")) ;

		    // Gọi DAO update
		    Customer updated = Customer.builder()
		    		.id(Integer.parseInt(id))
		    		.firstname(firstname)
		    		.lastname(lastname)
		    		.gender(gender)
		    		.passport(passport)
		    		.phoneNumber(phone)
		    		.nationality(nationality)
		    		.address(address)
		    		.note(note)
		    		.dateOfBirth(dob)
		    		.build(); // khởi tạo từ dữ liệu trên
		    boolean updated_rs = CustomerFunctionImpl.getInstance().updateCustomer(updated);

		  
		    if (updated_rs) {
		        resp.sendRedirect("Customer?message=Updated Successfully!");
		    } else {
		        resp.sendRedirect("Customer?error=Updated Failed!");
		    }
		
		doGet(req, resp);
	}
	
	

}

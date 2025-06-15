package ads.to;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

import ads.objects.Administrator;
import ads.objects.Booking;
import ads.objects.TourOperator;
import ads.objects.UserAccount;
import ads.user.AdministratorFunction;
import ads.user.AdministratorFunctionImpl;
import ads.user.TourOperatorFunctionImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/to/to-profile-management")
public class ProfileTOManagementServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		HttpSession session = req.getSession(false);
		
		UserAccount username =  (UserAccount) session.getAttribute("userLogined");
		
		// Thông tin admin
		TourOperator to = TourOperatorFunctionImpl.getInstance().getTOById(username.getTourOperator().getId());
		
		System.out.println(to.toString());
		PrintWriter out = resp.getWriter();

		out.append("<!DOCTYPE html>");
		out.append("<html lang=\"en\">");
		out.append("");
		out.append("<head>");
		out.append("  <meta charset=\"utf-8\">");
		out.append("  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">");
		out.append("");
		out.append("  <title>TravelVN - Hồ sơ</title>");
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
		out.append("  <link href=\"../assets/vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"../assets/vendor/bootstrap-icons/bootstrap-icons.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"../assets/vendor/boxicons/css/boxicons.min.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"../assets/vendor/quill/quill.snow.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"../assets/vendor/quill/quill.bubble.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"../assets/vendor/remixicon/remixicon.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"../assets/vendor/simple-datatables/style.css\" rel=\"stylesheet\">");
		out.append("");
		out.append("  <!-- Template Main CSS File -->");
		out.append("  <link href=\"../assets/css/style.css\" rel=\"stylesheet\">");
		out.append("");
		
		out.append("</head>");
		out.append("");
		out.append("<body>");
		out.append("");
		 RequestDispatcher h = req.getRequestDispatcher("/he");
		 if (h != null) {
				h.include(req, resp);
			}
		 RequestDispatcher side = req.getRequestDispatcher("/side");
		 if (side != null) {
				side.include(req, resp);
		 }
		out.append("");
		out.append("  <main id=\"main\" class=\"main\">");
		out.append("");
		out.append("    <div class=\"pagetitle d-flex\">");
		out.append("      <h1>Hồ sơ</h1>");
		out.append("      <nav class=\"ms-auto\">");
		out.append("        <ol class=\"breadcrumb\">");
		out.append("          <li class=\"breadcrumb-item active\">Hồ sơ</li>");
		out.append("        </ol>");
		out.append("      </nav>");
		out.append("    </div><!-- End Page Title -->");
		out.append("");
		out.append("    <section class=\"section profile\">");
		out.append("      <div class=\"row\">");
		out.append("");
		out.append("        <div class=\"col-xl-12\">");
		out.append("");
		out.append("          <div class=\"card\">");
		out.append("            <div class=\"card-body pt-3\">");
		out.append("              <!-- Bordered Tabs -->");
		out.append("              <ul class=\"nav nav-tabs nav-tabs-bordered\">");
		out.append("                <li class=\"nav-item\">");
		out.append("                  <button class=\"nav-link active d-flex align-items-center\" data-bs-toggle=\"tab\"");
		out.append("                    data-bs-target=\"#profile-overview\"><i class=\"bx bx-info-circle me-1\"></i>Tổng quan</button>");
		out.append("                </li>");
		out.append("");
		out.append("                <li class=\"nav-item\">");
		out.append("                  <button class=\"nav-link d-flex align-items-center\" data-bs-toggle=\"tab\"");
		out.append("                    data-bs-target=\"#profile-edit\"><i class=\"bx bx-pencil me-1\"></i>Sửa hồ sơ</button>");
		out.append("                </li>");
		out.append("");
		out.append("                <li class=\"nav-item\">");
		out.append("                  <button class=\"nav-link d-flex align-items-center\" data-bs-toggle=\"tab\"");
		out.append("                    data-bs-target=\"#profile-change-password\"><i class=\"bx bx-lock me-1\"></i>Đổi mật khẩu</button>");
		out.append("                </li>");
		out.append("              </ul>");
		out.append("              <div class=\"tab-content pt-2\">");
		out.append("");
		out.append("                <div class=\"tab-pane fade show active profile-overview\" id=\"profile-overview\">");
		out.append("");
		out.append("                  <div class=\"row mt-3\">");
		out.append("                    <div class=\"col-lg-1 col-md-4 label\">Công ty</div>");
		out.append("                    <div class=\"col-lg-11 col-md-8\">TravelVN</div>");
		out.append("                  </div>");
		out.append("");
		
		// Hồ sơ
		out.append("                  <div class=\"row\">");

		out.append("                    <div class=\"col-lg-4 col-md-4 label\">Số điện thoại</div>");
		out.append("                    <div class=\"col-lg-8 col-md-8\">" + to.getPhoneNumber() + "</div>");
		out.append("                    <div class=\"col-lg-4 col-md-4 label\">Họ và tên</div>");
		out.append("                    <div class=\"col-lg-8 col-md-8\">" + to.getFirstname()+" " +to.getLastname()+ "</div>");
		out.append("                    <div class=\"col-lg-4 col-md-4 label\">Năm sinh</div>");
		out.append("                    <div class=\"col-lg-8 col-md-8\">" + to.getDateOfBirth() + "</div>");
		out.append("                    <div class=\"col-lg-4 col-md-4 label\">Giới tính</div>");
		String gt = to.getGender()?"nam":"nữ";
		out.append("                    <div class=\"col-lg-8 col-md-8\">" + gt + "</div>");
		out.append("                    <div class=\"col-lg-4 col-md-4 label\">Địa chỉ</div>");
		out.append("                    <div class=\"col-lg-8 col-md-8\">" + to.getAddress() + "</div>");
		out.append("                    <div class=\"col-lg-4 col-md-4 label\">Số căn cước</div>");
		out.append("                    <div class=\"col-lg-8 col-md-8\">" + to.getCitizenId() + "</div>");
		out.append("                    <div class=\"col-lg-4 col-md-4 label\">Quê quán</div>");
		out.append("                    <div class=\"col-lg-8 col-md-8\">" + to.getHometown() + "</div>");
		out.append("                  </div>");
		out.append("");
		out.append("                </div>");
		out.append("");
		out.append("                <div class=\"tab-pane fade profile-edit pt-3\" id=\"profile-edit\">");
		out.append("");
		
		// Sửa hồ sơ
		out.append("<!-- Profile Edit Form -->");
		out.append("<form action=\"\" method=\"POST\">");

		// Company (readonly)
		out.append("  <input type=\"hidden\" name=\"tour_operator_id\" value=\""+ username.getTourOperator().getId()+"\">\r\n"
		+ "");
		out.append("  <div class=\"row mb-3\">");
		out.append("    <label for=\"company\" class=\"col-md-4 col-lg-2 col-form-label\">Công ty</label>");
		out.append("    <div class=\"col-md-8 col-lg-10\">");
		out.append("      <input name=\"company\" type=\"text\" class=\"form-control\" id=\"company\" value=\"TravelVN\" disabled>");
		out.append("    </div>");
		out.append("  </div>");

		// Email
		out.append("  <div class=\"row mb-3\">");
		out.append("    <label for=\"Email\" class=\"col-md-4 col-lg-2 col-form-label\">Email</label>");
		out.append("    <div class=\"col-md-8 col-lg-10\">");
		out.append("      <input name=\"username\" type=\"hidden\" class=\"form-control\" value=\"" + username + "\">");
		out.append("      <input name=\"email\" type=\"email\" class=\"form-control\" id=\"email\" value=\"" + to.getUserAccount().getEmail() + "\" required>");
		out.append("    </div>");
		out.append("  </div>");

		// Firstname
		out.append("  <div class=\"row mb-3\">");
		out.append("    <label for=\"Firstname\" class=\"col-md-4 col-lg-2 col-form-label\">Họ đệm</label>");
		out.append("    <div class=\"col-md-8 col-lg-10\">");
		out.append("      <input name=\"firstname\" type=\"text\" class=\"form-control\" id=\"lirstname\" value=\"" + to.getFirstname() + "\" required>");
		out.append("    </div>");
		out.append("  </div>");

		// Lastname
		out.append("  <div class=\"row mb-3\">");
		out.append("    <label for=\"Lastname\" class=\"col-md-4 col-lg-2 col-form-label\">Tên</label>");
		out.append("    <div class=\"col-md-8 col-lg-10\">");
		out.append("      <input name=\"lastname\" type=\"text\" class=\"form-control\" id=\"lastname\" value=\"" + to.getLastname() + "\" required>");
		out.append("    </div>");
		out.append("  </div>");

		// Date of Birth
		out.append("  <div class=\"row mb-3\">");
		out.append("    <label for=\"DateOfBirth\" class=\"col-md-4 col-lg-2 col-form-label\">Ngày sinh</label>");
		out.append("    <div class=\"col-md-8 col-lg-10\">");
		out.append("      <input name=\"dateofbirth\" type=\"date\" class=\"form-control\" id=\"DateOfBirth\" value=\"" + to.getDateOfBirth() + "\" required>");
		out.append("    </div>");
		out.append("  </div>");

		// Gender
		out.append("  <div class=\"row mb-3\">");
		out.append("    <label class=\"col-md-4 col-lg-2 col-form-label\">Giới tính</label>");
		out.append("    <div class=\"col-md-8 col-lg-10\">");
		out.append("      <div class=\"form-check form-check-inline\">");
		out.append("        <input class=\"form-check-input\" type=\"radio\" name=\"gender\" id=\"genderMale\" value=\"true\" " + (to.getGender() ? "checked" : "") + " required>");
		out.append("        <label class=\"form-check-label\" for=\"genderMale\">Nam</label>");
		out.append("      </div>");
		out.append("      <div class=\"form-check form-check-inline\">");
		out.append("        <input class=\"form-check-input\" type=\"radio\" name=\"gender\" id=\"genderFemale\" value=\"false\" " + (!to.getGender() ? "checked" : "") + " required>");
		out.append("        <label class=\"form-check-label\" for=\"genderFemale\">Nữ</label>");
		out.append("      </div>");
		out.append("    </div>");
		out.append("  </div>");

		// Address
		out.append("  <div class=\"row mb-3\">");
		out.append("    <label for=\"Address\" class=\"col-md-4 col-lg-2 col-form-label\">Địa chỉ</label>");
		out.append("    <div class=\"col-md-8 col-lg-10\">");
		out.append("      <input name=\"address\" type=\"text\" class=\"form-control\" id=\"address\" value=\"" + to.getAddress() + "\" required>");
		out.append("    </div>");
		out.append("  </div>");

		// Phone number
		out.append("  <div class=\"row mb-3\">");
		out.append("    <label for=\"PhoneNumber\" class=\"col-md-4 col-lg-2 col-form-label\">Số điện thoại</label>");
		out.append("    <div class=\"col-md-8 col-lg-10\">");
		out.append("      <input name=\"phonenumber\" type=\"text\" class=\"form-control\" id=\"phonenumber\" value=\"" + to.getPhoneNumber() + "\" required>");
		out.append("    </div>");
		out.append("  </div>");

		// Citizen ID
		out.append("  <div class=\"row mb-3\">");
		out.append("    <label for=\"CitizenId\" class=\"col-md-4 col-lg-2 col-form-label\">Số CCCD</label>");
		out.append("    <div class=\"col-md-8 col-lg-10\">");
		out.append("      <input name=\"citizenid\" type=\"text\" class=\"form-control\" id=\"citizenid\" value=\"" + to.getCitizenId() + "\" required>");
		out.append("    </div>");
		out.append("  </div>");

		// Hometown
		out.append("  <div class=\"row mb-3\">");
		out.append("    <label for=\"Hometown\" class=\"col-md-4 col-lg-2 col-form-label\">Quê quán</label>");
		out.append("    <div class=\"col-md-8 col-lg-10\">");
		out.append("      <input name=\"hometown\" type=\"text\" class=\"form-control\" id=\"Hometown\" value=\"" + to.getHometown() + "\" required>");
		out.append("    </div>");
		out.append("  </div>");

		// Submit
		out.append("  <div class=\"text-center\">");
		out.append("    <button type=\"submit\" class=\"btn btn-primary\">Lưu thay đổi</button>");
		out.append("  </div>");

		out.append("</form><!-- End Profile Edit Form -->");

		out.append("");
		out.append("                </div>");
		out.append("");
		
		// Đổi mật khẩu
		out.append("                <div class=\"tab-pane fade pt-3\" id=\"profile-change-password\">");
		out.append("                  <!-- Change Password Form -->");
		out.append("                  <form action=\"" + req.getContextPath() + "/to/change-password?id="+username.getTourOperator().getId()+"\" id=\"changePasswordForm\" method=\"POST\">");
		out.append("");
		out.append("                    <input name=\"username\" type=\"hidden\" class=\"form-control\" id=\"username\" value=\"" + username + "\">");
		out.append("                    <div class=\"row mb-3\">");
		out.append("                      <label for=\"currentPassword\" class=\"col-md-4 col-lg-2 col-form-label\">Mật khẩu hiện tại</label>");
		out.append("                      <div class=\"col-md-8 col-lg-10\">");
		out.append("                        <input name=\"currentPassword\" type=\"password\" class=\"form-control\" id=\"currentPassword\">");
		out.append("                      </div>");
		out.append("                    </div>");
		out.append("");
		out.append("                    <div class=\"row mb-3\">");
		out.append("                      <label for=\"newPassword\" class=\"col-md-4 col-lg-2 col-form-label\">Mật khẩu mới</label>");
		out.append("                      <div class=\"col-md-8 col-lg-10\">");
		out.append("                        <input name=\"newPassword\" type=\"password\" class=\"form-control\" id=\"newPassword\">");
		out.append("                      </div>");
		out.append("                    </div>");
		out.append("");
		out.append("                    <div class=\"row mb-3\">");
		out.append("                      <label for=\"renewPassword\" class=\"col-md-4 col-lg-2 col-form-label\">Nhập lại mật khẩu mới</label>");
		out.append("                      <div class=\"col-md-8 col-lg-10\">");
		out.append("                        <input name=\"renewPassword\" type=\"password\" class=\"form-control\" id=\"renewPassword\">");
		out.append("                      </div>");
		out.append("                    </div>");
		out.append("");
		out.append("                    <div class=\"text-center\">");
		out.append("                      <button type=\"submit\" class=\"btn btn-primary\">Đổi mật khẩu</button>");
		out.append("                    </div>");
		out.append("                  </form><!-- End Change Password Form -->");
		out.append("<script>");
		out.append("  document.getElementById('changePasswordForm').addEventListener('submit', function(event) {");
		out.append("    var newPass = document.getElementById('newPassword').value;");
		out.append("    var renewPass = document.getElementById('renewPassword').value;");
		out.append("    if (newPass !== renewPass) {");
		out.append("      event.preventDefault();");
		out.append("      alert('Mật khẩu mới và nhập lại mật khẩu không khớp!');");
		out.append("    }");
		out.append("  });");
		out.append("</script>");

		out.append("");
		out.append("                </div>");
		
		
		out.append("              </div><!-- End Bordered Tabs -->");
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
		out.append("      &copy; Copyright <strong><span>TravelVN</span></strong>. All Rights Reserved");
		out.append("    </div>");
		out.append("    <div class=\"credits\">");
		out.append("      <!-- All the links in the footer should remain intact. -->");
		out.append("      <!-- You can delete the links only if you purchased the pro version. -->");
		out.append("      <!-- Licensing information: https://bootstrapmade.com/license/ -->");
		out.append("      <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->");
		out.append("      <!-- Designed by <a href=\"https://bootstrapmade.com/\">BootstrapMade</a> -->");
		out.append("    </div>");
		out.append("  </footer><!-- End Footer -->");
		out.append("");
		out.append("  <a href=\"#\" class=\"back-to-top d-flex align-items-center justify-content-center\"><i");
		out.append("      class=\"bi bi-arrow-up-short\"></i></a>");
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
		out.append("");
		out.append("  <!-- Template Main JS File -->");
		out.append("  <script src=\"../assets/js/main.js\"></script>");
		out.append("");
		out.append("</body>");
		out.append("");
		out.append("</html>");
	}	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("tour_operator_id"));
            TourOperator to = TourOperatorFunctionImpl.getInstance().getTOById(id);
            
            String firstName = req.getParameter("firstname");
            String lastName = req.getParameter("lastname");
            LocalDate dob = LocalDate.parse(req.getParameter("dateofbirth"));
            boolean gender = req.getParameter("gender").equals("true") ? true : false;
            String phoneNumber = req.getParameter("phonenumber");
            String citizenId = req.getParameter("citizenid");
            String hometown = req.getParameter("hometown");
            String address = req.getParameter("address");
 
//            // Kiểm tra số điện thoại đã tồn tại chưa
//            if (tourOperatorFunction.isPhoneNumberExist(phoneNumber)) {
//                resp.sendRedirect(req.getContextPath() + "/ad-touroperator-management?error=phone_exist");
//                return;
//            }
//            
//            // Kiểm tra citizen_id đã tồn tại chưa
//            if (tourOperatorFunction.isCitizenIdExist(citizenId)) {
//            	resp.sendRedirect(req.getContextPath() + "/ad-touroperator-management?error=citizen_id_exist");
//                return;
//            }

            TourOperator updatedOperator = new TourOperator();
            updatedOperator.setId(id);
            updatedOperator.setFirstname(firstName);
            updatedOperator.setLastname(lastName);
            updatedOperator.setDateOfBirth(dob);
            updatedOperator.setGender(gender);
            updatedOperator.setPhoneNumber(phoneNumber);
            updatedOperator.setCitizenId(citizenId);
            updatedOperator.setHometown(hometown);
            updatedOperator.setAddress(address);
            updatedOperator.setStartDate(to.getStartDate());
            updatedOperator.setEndDate(to.getEndDate());
            updatedOperator.setSalary(to.getSalary());

            boolean success = TourOperatorFunctionImpl.getInstance().updateTourOperator(updatedOperator);

            if(success) {
                
                resp.sendRedirect("/adv/to/to-profile-management?success=update");
            }
            else {
               
                resp.sendRedirect("/adv/to/to-profile-management?error=update");
            }
        } catch (Exception e) {
            e.printStackTrace();
   
            resp.sendRedirect("/to-profile-management");
        }
    }
}

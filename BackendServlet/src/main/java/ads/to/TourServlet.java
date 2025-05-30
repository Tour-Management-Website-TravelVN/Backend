package ads.to;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import ads.library.TourLibrary;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/to/tour")
public class TourServlet extends HttpServlet {

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

		String page = request.getParameter("page");
		String keyword = request.getParameter("keyword");
		
		int currentPage = 1;
		if(page!=null)currentPage = Integer.parseInt(page);
		if(keyword==null) keyword = "";
		
		HttpSession session = request.getSession();
		String actionTour = (String) session.getAttribute("actionTour");
		if(actionTour!=null) session.removeAttribute("actionTour");
		
		PrintWriter out = response.getWriter();
		out.append("<!DOCTYPE html>");
		out.append("<html lang=\"en\">");
		out.append("<head>");
		out.append("  <meta charset=\"utf-8\">");
		out.append("  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">");
		out.append("  <title>Quản lý tour</title>");
		out.append("  <meta content=\"\" name=\"description\">");
		out.append("  <meta content=\"\" name=\"keywords\">");
		out.append("  <!-- Favicons -->");

		out.append("  <link href=\"../resources/Logo.svg\" rel=\"icon\">");
		out.append("  <link href=\"../assets/img/apple-touch-icon.png\" rel=\"apple-touch-icon\">");
		out.append("  <!-- Google Fonts -->");
		out.append("  <link href=\"https://fonts.gstatic.com\" rel=\"preconnect\">");
		out.append(
				"  <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i\" rel=\"stylesheet\">");
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

		RequestDispatcher h = request.getRequestDispatcher("/he");
		if (h != null) {
			h.include(request, response);
		}
		RequestDispatcher s = request.getRequestDispatcher("/side");
		if (s != null) {
			s.include(request, response);
		}

		out.append("""
				<main id="main" class="main">

				<div class="pagetitle d-flex">
				  <h1 class="align-middle">Danh sách tour</h1>
				  <nav class="ms-auto">
				    <ol class="breadcrumb">
				      <li class="breadcrumb-item"><a href="index.html"><i class="bi bi-house-door"></i></a></li>
				      <li class="breadcrumb-item">Quản lý tour</li>
				    </ol>
				  </nav>
				</div><!-- End Page Title -->

				<section class="section">
				  <div class="row d-flex">
				    <div class="col-sm-4 col-md-4 fixed-filter ms-auto">
				      <a href="/adv/to/tour/tour_form?action=add" class="btn btn-success"><i class="bi bi-plus-circle"></i>
				        Thêm tour mới
				      </a>
				    </div>
				    <div class="col-sm-8 col-md-8 fixed-filter ms-auto">
				      <div class="card mb-3 form-floating">
				        <form class="d-flex" role="search" method="POST" action="/adv/to/tour">
				          <input type="text" class="form-control" name="tourName" placeholder="Tên tour">
				          <button type="submit" class="btn btn-primary">
				            <i class="bi bi-search"></i>
				          </button>
				        </form>
				      </div>
				    </div>
				  </div>


				  <div class="row">
				    <!-- ...existing code... -->
				    <!-- ...existing code... -->
				    <div class="col-sm-12">""");

		out.append(TourLibrary.viewToursList(currentPage, keyword));
		
		out.append("</div>");
		out.append("</div>");
		
		out.append(TourLibrary.viewPageNavigation(currentPage, keyword));
		
//		out.append("""
//				    <nav aria-label="Page navigation example">
//				      <ul class="pagination justify-content-center">
//				        <li class="page-item"><a class="page-link" href="#"><i class="bi bi-arrow-left"></i></a></li>
//				        <li class="page-item"><a class="page-link" href="#">1</a></li>
//				        <li class="page-item"><a class="page-link" href="#">2</a></li>
//				        <li class="page-item"><a class="page-link" href="#">3</a></li>
//				        <li class="page-item"><a class="page-link">...</a></li>
//				        <li class="page-item"><a class="page-link" href="#">7</a></li>
//
//				        <li class="page-item"><a class="page-link" href="#"><i class="bi bi-arrow-right"></i></a></li>
//				      </ul>
//				    </nav><!-- End Basic Pagination -->
//				  </section>
//
//				  <!-- Lớp phủ -->
//
//
//				</main><!-- End #main -->
//						""");
		if(actionTour!=null) {
			out.append("<!-- Toast Container -->");
			out.append("<div class=\"toast position-fixed bottom-0 end-0\" style=\"z-index: 999999;\" id=\"myToast\" role=\"alert\" aria-live=\"assertive\" aria-atomic=\"true\">");
			out.append("<div class=\"toast-header\">");
			out.append("<strong class=\"me-auto text-warning\">Thông báo</strong>");
			out.append("<small>Vừa xong</small>");
			out.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"toast\" aria-label=\"Close\"></button>");
			out.append("</div>");
			out.append("<div class=\"toast-body bg-white\">").append(actionTour).append("</div>");
			out.append("<!-- Show Toast -->");
			
			out.append("<script>");
			out.append("$(function () {");
			out.append("const toastEl = document.getElementById('myToast');");
			out.append("const toast = new bootstrap.Toast(toastEl);");
			out.append("toast.show();");
			out.append("})");
			out.append("</script>");
		}
		out.append("</section>");
		out.append("</main>");

//		out.append("  <main id=\"main\" class=\"main\">");
//		out.append("");
//		out.append("    <div class=\"pagetitle d-flex\">");
//		out.append("      <h1 class=\"align-middle\">Danh sách tour</h1>");
//		out.append("      <nav class=\"ms-auto\">");
//		out.append("        <ol class=\"breadcrumb\">");
//		out.append(
//				"          <li class=\"breadcrumb-item\"><a href=\"index.html\"><i class=\"bi bi-house-door\"></i></a></li>");
//		out.append("          <li class=\"breadcrumb-item\">Quản lý tour</li>");
//		out.append("        </ol>");
//		out.append("      </nav>");
//		out.append("    </div><!-- End Page Title -->");
//		out.append("");
//		out.append("    <section class=\"section\">");
//		out.append("      <div class=\"row d-flex\">");
//		out.append("      <div class=\"col-sm-9 fixed-filter ms-auto\">");
//		out.append(
//				"            <a href=\"add-tour.html\" class=\"btn btn-success\"><i class=\"bi bi-plus-circle\"></i>");
//		out.append("              Thêm tour mới ");
//		out.append("            </a>");
//		out.append("        </div>");
//		out.append("        <div class=\"col-sm-3 fixed-filter ms-auto\">");
//		out.append("          <div class=\"card mb-3 form-floating\">");
//		out.append("              <form class=\"d-flex\" role=\"search\" method=\"POST\" action=\"#\">");
//		out.append(
//				"                <input type=\"text\" class=\"form-control\" id=\"tourName\" placeholder=\"Tên tour\" >");
//		out.append("                <button type=\"submit\" class=\"btn btn-primary\">");
//		out.append("                  <i class=\"bi bi-search\"></i>");
//		out.append("                </button>");
//		out.append("              </form>");
//		out.append("          </div>");
//		out.append("        </div>");
//		out.append("      </div>");
//		out.append("  ");
//		out.append("");
//		out.append("        <div class=\"row\">");
//		out.append("          <!-- ...existing code... -->");
//		out.append("<!-- ...existing code... -->");
//		out.append("        <div class=\"col-sm-12\">");
//		out.append("      ");
//		out.append("    ");
//		out.append("              <div class=\"card mb-3\">");
//		out.append("                <div class=\"row g-0\">");
//		out.append("                  ");
//		out.append("                  <div class=\"col-md-4\">");
//		out.append("                    ");
//		out.append("                    <a href=\"Tour-Detail\">");
//		out.append(
//				"                    <img  src=\"../assets/img/chi-phi-di-da-lat-1.jpeg\" class=\"img-fluid rounded-start\" alt=\"...\">");
//		out.append("                  </a>");
//		out.append("                  </div>");
//		out.append("                  ");
//		out.append("                  <div class=\"col-md-8\">");
//		out.append("                    <div class=\"card-body\">");
//		out.append("                    <div class=\"d-flex row\">");
//		out.append("                      <a href=\"Tour_Detail\" class=\"col-sm-8\" style=\"transform: scale(1.0);\">");
//		out.append("                      <h5 class=\"card-title\">Đà Lạt 3 Ngày 2 đêm</h5>");
//		out.append("                    </a>");
//		out.append("                    <a href=\"edit-tour.html\" class=\"fw-bold col-sm-2 mt-3 text-end\"> ");
//		out.append("                      <i class=\"bi bi-gear-fill text-warning\"></i>");
//		out.append("                    </a>");
//		out.append(
//				"                    <a href=\"Tour_unit_management.html\" class=\"fw-bold col-sm-2 mt-3 text-end\"> ");
//		out.append("                      <i class=\"bi bi-trash3-fill text-danger\"></i>");
//		out.append("          ");
//		out.append("                    </a>");
//		out.append("                  </div>");
//		out.append("                  <div class=\"row\">");
//		out.append("                  <div class=\"col-md-6\">");
//		out.append("                    <p class=\"card-text\"><strong>Mã tour:</strong> T001-00001</p>");
//		out.append("                    <p class=\"card-text\"><strong>Thời lượng:</strong> 3 Ngày 2 Đêm</p>");
//		out.append("                    <p class=\"card-text\"><strong>Phương tiện:</strong> Ô tô, Máy bay</p>");
//		out.append("                    <p class=\"card-text\"><strong>Điểm đi:</strong> Hà Nội</p>");
//		out.append("                    <p class=\"card-text\"><strong>Điểm đến:</strong> Đà Lạt</p>");
//		out.append("                  </div>");
//		out.append("                    <div class=\"col-md-6\">");
//		out.append("                      <p class=\"card-text\"><strong>Thời điểm tạo:</strong> 2025-04-01</p>");
//		out.append(
//				"                      <p class=\"card-text\"><strong>Thời điểm cập nhật gần nhất:</strong> 2025-04-07</p>");
//		out.append(
//				"                      <p class=\"card-text\"><strong>Người điều hành sửa gần nhất:</strong> Nguyễn Văn A</p>");
//		out.append("                      <p class=\"card-text\"><strong>Thể loại:</strong> Du lịch nghỉ dưỡng</p>");
//		out.append("                    </div>");
//		out.append("                  </div>");
//		out.append("                  </div>");
//		out.append("          ");
//		out.append("                   ");
//		out.append("                  </div>");
//		out.append("                </div>");
//		out.append("              </div><!-- End Card with an image on left -->");
//		out.append("");
//		out.append("              <div class=\"card mb-3\">");
//		out.append("                <div class=\"row g-0\">");
//		out.append("                  <div class=\"col-md-4\">");
//		out.append("                    <a href=\"Tour_detail.html\">");
//		out.append(
//				"                    <img src=\"../assets/img/chi-phi-di-da-lat-1.jpeg\" class=\"img-fluid rounded-start\" alt=\"...\">");
//		out.append("                  </a>");
//		out.append("                  </div>");
//		out.append("                  <div class=\"col-md-8\">");
//		out.append("                  ");
//		out.append("                    <div class=\"card-body\">");
//		out.append("                      <div class=\"d-flex row\">");
//		out.append("                      <a href=\"Tour_detail.html\" class=\"col-sm-8\" style=\"transform: scale(1.0);\">");
//		out.append("                      <h5 class=\"card-title\">Đà Lạt 3 Ngày 2 đêm</h5>");
//		out.append("                    </a>");
//		out.append(
//				"                    <a href=\"Tour_unit_management.html\" class=\"fw-bold col-sm-2 mt-3 text-end\"> ");
//		out.append("                      <i class=\"bi bi-gear-fill text-warning\"></i>");
//		out.append("                    </a>");
//		out.append(
//				"                    <a href=\"Tour_unit_management.html\" class=\"fw-bold col-sm-2 mt-3 text-end\"> ");
//		out.append("                      <i class=\"bi bi-trash3-fill text-danger\"></i>");
//		out.append("          ");
//		out.append("                    </a>");
//		out.append("                  </div>");
//		out.append(
//				"                      <p class=\"card-text\">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>");
//		out.append("          ");
//		out.append("                    </div>");
//		out.append("                  </div>");
//		out.append("                </div>");
//		out.append("              </div><!-- End Card with an image on left -->");
//		out.append("  ");
//		out.append("              <div class=\"card mb-3\">");
//		out.append("                <div class=\"row g-0\">");
//		out.append("                  <div class=\"col-md-4\">");
//		out.append("                    <a href=\"Tour_detail.html\">");
//		out.append(
//				"                    <img src=\"../assets/img/481088087_959148229729702_4478781852400038459_n.jpg\" class=\"img-fluid rounded-start\" alt=\"...\">");
//		out.append("                  </a>");
//		out.append("                  </div>");
//		out.append("                  <div class=\"col-md-8\">");
//		out.append("                  ");
//		out.append("                    <div class=\"card-body\">");
//		out.append("                      <div class=\"d-flex row\">");
//		out.append("                      <a href=\"Tour_detail.html\" class=\"col-sm-8\" style=\"transform: scale(1.0);\">");
//		out.append("                      <h5 class=\"card-title\" >Đà Lạt 3 Ngày 2 đêm</h5>");
//		out.append("                    </a>");
//		out.append(
//				"                    <a href=\"Tour_unit_management.html\" class=\"fw-bold col-sm-2 mt-3 text-end\"> ");
//		out.append("                      <i class=\"bi bi-gear-fill text-warning\"></i>");
//		out.append("                    </a>");
//		out.append(
//				"                    <a href=\"Tour_unit_management.html\" class=\"fw-bold col-sm-2 mt-3 text-end\"> ");
//		out.append("                      <i class=\"bi bi-trash3-fill text-danger\"></i>");
//		out.append("          ");
//		out.append("                    </a>");
//		out.append("                  </div>");
//		out.append(
//				"                      <p class=\"card-text\">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>");
//		out.append("          ");
//		out.append("                    </div>");
//		out.append("                  </div>");
//		out.append("                </div>");
//		out.append("              </div><!-- End Card with an image on left -->");
//		out.append(" ");
//		out.append("              <div class=\"card mb-3\">");
//		out.append("                <div class=\"row g-0\">");
//		out.append("                  <div class=\"col-md-4\">");
//		out.append("                    <a href=\"Tour_detail.html\">");
//		out.append(
//				"                    <img src=\"../assets/img/481088087_959148229729702_4478781852400038459_n.jpg\" class=\"img-fluid rounded-start\" alt=\"...\">");
//		out.append("                  </a>");
//		out.append("                  </div>");
//		out.append("                  <div class=\"col-md-8\">");
//		out.append("                  ");
//		out.append("                    <div class=\"card-body\">");
//		out.append("                      <div class=\"d-flex row\">");
//		out.append("                      <a href=\"Tour_detail.html\" class=\"col-sm-8\" style=\"transform: scale(1.0);\">");
//		out.append("                      <h5 class=\"card-title\">Đà Lạt 3 Ngày 2 đêm</h5>");
//		out.append("                    </a>");
//		out.append(
//				"                    <a href=\"Tour_unit_management.html\" class=\"fw-bold col-sm-2 mt-3 text-end\"> ");
//		out.append("                      <i class=\"bi bi-gear-fill text-warning\"></i>");
//		out.append("                    </a>");
//		out.append(
//				"                    <a href=\"Tour_unit_management.html\" class=\"fw-bold col-sm-2 mt-3 text-end\"> ");
//		out.append("                      <i class=\"bi bi-trash3-fill text-danger\"></i>");
//		out.append("          ");
//		out.append("                    </a>");
//		out.append("                  </div>");
//		out.append(
//				"                      <p class=\"card-text\">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>");
//		out.append("          ");
//		out.append("                    </div>");
//		out.append("                  </div>");
//		out.append("                </div>");
//		out.append("              </div><!-- End Card with an image on left -->");
//		out.append("    </a>");
//		out.append("      </div>");
//		out.append("    </div>");
//		out.append("    <nav aria-label=\"Page navigation example\">");
//		out.append("      <ul class=\"pagination justify-content-center\">");
//		out.append(
//				"        <li class=\"page-item \"><a class=\"page-link\" href=\"#\"><i class=\"bi bi-arrow-left\"></i>");
//		out.append("        </a></li>");
//		out.append("        <li class=\"page-item active\"><a class=\"page-link\" href=\"#\">1</a></li>");
//		out.append("        <li class=\"page-item\"><a class=\"page-link\" href=\"#\">2</a></li>");
//		out.append("        <li class=\"page-item\"><a class=\"page-link\" href=\"#\">3</a></li>");
//		out.append("        <li class=\"page-item\"><a class=\"page-link\" href=\"#\">...</a></li>");
//		out.append("        <li class=\"page-item\"><a class=\"page-link\" href=\"#\">7</a></li>");
//		out.append("");
//		out.append(
//				"        <li class=\"page-item\"><a class=\"page-link\" href=\"#\"><i class=\"bi bi-arrow-right\"></i></a></li>");
//		out.append("      </ul>");
//		out.append("    </nav><!-- End Basic Pagination -->");
//		out.append("    </section>");
//		out.append("");
//		out.append("  <!-- Lớp phủ -->");
//		out.append("  ");
//		out.append("");
//		out.append("  </main><!-- End #main -->");

		RequestDispatcher fo = request.getRequestDispatcher("/fo");
		if (fo != null) {
			fo.include(request, response);
		}

		out.append(
				"  <a href=\"#\" class=\"back-to-top d-flex align-items-center justify-content-center\"><i class=\"bi bi-arrow-up-short\"></i></a>");
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

	    String tourName = req.getParameter("tourName");
	    if (tourName == null || tourName.trim().isEmpty()) {
	        // Redirect về trang đầu nếu không nhập gì
	        resp.sendRedirect("/adv/to/tour?page=1");
	        return;
	    }

	    String encodedKeyword = URLEncoder.encode(tourName, "UTF-8");
	    resp.sendRedirect("/adv/to/tour?page=1&keyword=" + encodedKeyword);
	}

}

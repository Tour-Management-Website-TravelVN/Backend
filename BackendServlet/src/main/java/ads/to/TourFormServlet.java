package ads.to;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ads.library.TourLibrary;
import ads.objects.Tour;
import ads.objects.TourProgram;
import ads.objects.UserAccount;
import ads.user.ImageFunctionImpl;
import ads.user.TourFunctionImpl;
import ads.util.CloudinaryService;
import ads.util.GsonProvider;
import ads.util.Validate;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB
		maxFileSize = 1024 * 1024 * 5, // 5MB
		maxRequestSize = 1024 * 1024 * 10 // 10MB
)
@WebServlet("/to/tour/tour_form")
public class TourFormServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String action = request.getParameter("action");
		if (action == null) {
			log.info("VAO DAY");
			response.sendRedirect("/adv/to/tour");
			return;
		}

		PrintWriter out = response.getWriter();

		out.append("<!DOCTYPE html>");
		out.append("<html lang=\"en\">");
		out.append("");
		out.append("<head>");
		out.append("  <meta charset=\"utf-8\">");
		out.append("  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">");
		out.append("");
		if (action.equalsIgnoreCase("add")) {
			out.append("  <title>Thêm tour</title>");
		} else if (action.equalsIgnoreCase("updateImgs")) {
			out.append("  <title>Cập nhật ảnh tour</title>");
		} else if(action.equalsIgnoreCase("update")){
			out.append("  <title>Cập nhật thông tin tour</title>");
		} else {
			
		}

		out.append("  <meta content=\"\" name=\"description\">");
		out.append("  <meta content=\"\" name=\"keywords\">");
		out.append("");
		out.append("  <!-- Favicons -->");
		out.append("  <link href=\"/adv/resources/Logo.svg\" rel=\"icon\"");
		out.append("  <link href=\"/adv/assets/img/apple-touch-icon.png\" rel=\"apple-touch-icon\">");
		out.append("");
		out.append("  <!-- Google Fonts -->");
		out.append("  <link href=\"https://fonts.gstatic.com\" rel=\"preconnect\">");
		out.append(
				"  <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i\" rel=\"stylesheet\">");
		out.append("");
		out.append("  <!-- Vendor CSS Files -->");
		out.append("  <link href=\"/adv/assets/vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"/adv/assets/vendor/bootstrap-icons/bootstrap-icons.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"/adv/assets/vendor/boxicons/css/boxicons.min.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"/adv/assets/vendor/quill/quill.snow.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"/adv/assets/vendor/quill/quill.bubble.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"/adv/assets/vendor/remixicon/remixicon.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"/adv/assets/vendor/simple-datatables/style.css\" rel=\"stylesheet\">");
		out.append("");
		out.append("  <!-- Template Main CSS File -->");
		out.append("<script src=\"/adv/assets/vendor/jquery/jquery-3.7.1.min.js\"></script>);");
		out.append("  <link href=\"/adv/assets/css/style.css\" rel=\"stylesheet\">");
		out.append("</head>");
		out.append("");
		out.append("<body>");

		RequestDispatcher h = request.getRequestDispatcher("/he");
		if (h != null) {
			h.include(request, response);
		}

		RequestDispatcher si = request.getRequestDispatcher("/side");
		if (si != null) {
			si.include(request, response);
		}

		if (action.equalsIgnoreCase("add")) {
			out.append(TourLibrary.formAddTour());
		} else if(action.equalsIgnoreCase("updateImgs")) {
			out.append(TourLibrary.formUpdateImgsTour(request));
		} else if(action.equalsIgnoreCase("update")) {
			out.append(TourLibrary.formUpdateTour(request));
		}
		/*
		 * out.append( """ <main id="main" class="main">
		 * 
		 * <div class="pagetitle d-flex"> <h1>Thêm tour mới</h1> <nav class="ms-auto">
		 * <ol class="breadcrumb"> <li class="breadcrumb-item"><a href="index.html"><i
		 * class="bi bi-house-door"></i></a></li> <li class="breadcrumb-item">Quản lý
		 * tour</li> <li class="breadcrumb-item active">Thêm tour mới</li> </ol> </nav>
		 * </div><!-- End Page Title -->
		 * 
		 * <section class="section"> <form class="needs-validation" id="tourForm"
		 * novalidate method="post" action="/adv/richtext"
		 * enctype="multipart/form-data"> <div class="row"> <div class="col"> <!--
		 * ...existing code... --> <div class="card"> <div class="card-body"> <h5
		 * class="card-title">Nhập thông tin cho tour mới</h5>
		 * 
		 * <!-- General Form Elements -->
		 * 
		 * <div class="row mb-3"> <div class="col-md-6"> <div class="form-floating">
		 * <input type="text" class="form-control bg-light" id="tourId"
		 * placeholder="Mã tour" readonly> <label for="tourId">Mã tour</label> </div>
		 * </div> <div class="col-md-6  mt-3 mt-md-0"> <div class="form-floating">
		 * <input class="form-control" type="file" id="tourImage" placeholder="Ảnh"
		 * multiple accept="/image/*"> <label for="tourImage">Ảnh</label> </div> </div>
		 * </div>
		 * 
		 * 
		 * <div class="d-flex overflow-auto gap-3 p-2 border col-12 mb-3"
		 * style="height: 250px; width: auto;"> <div class="position-relative"
		 * style="min-width: 200px"> <img src=
		 * "https://baodongnai.com.vn/file/e7837c02876411cd0187645a2551379f/dataimages/201706/original/images1920558_4053279_16.jpg"
		 * class="img-thumbnail position-absolute top-0 end-0"
		 * style="width: 100%; height: 220px;" /> <button
		 * class="btn btn-danger rounded-circle py-0 px-1 position-absolute top-0 end-0"
		 * > <i class="bi bi-x text-white fw-bold fs-5"></i> </button> </div>
		 * 
		 * <div class="position-relative" style="min-width: 200px"> <img src=
		 * "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRdvHiMePqz1GKwY38h5_Nfx0ga731PEC0l7A&s"
		 * class="img-thumbnail position-absolute top-0 end-0"
		 * style="width: 100%; height: 220px;" /> <button
		 * class="btn btn-danger rounded-circle py-0 px-1 position-absolute top-0 end-0"
		 * > <i class="bi bi-x text-white fw-bold fs-5"></i> </button> </div>
		 * 
		 * <div class="position-relative" style="min-width: 200px"> <img
		 * src="https://cdn-media.sforum.vn/storage/app/media/anh-dep-83.jpg"
		 * class="img-thumbnail position-absolute top-0 end-0"
		 * style="width: 100%; height: 220px;" /> <button
		 * class="btn btn-danger rounded-circle py-0 px-1 position-absolute top-0 end-0"
		 * > <i class="bi bi-x text-white fw-bold fs-5"></i> </button> </div>
		 * 
		 * <div class="w-100 h100 d-flex align-items-center me-3"> <i
		 * class="bi bi-plus-circle text-primary h1"></i> </div> </div>
		 * 
		 * <div class="form-floating mb-3"> <input type="text" class="form-control"
		 * id="tourName" placeholder="Tên tour" required> <label for="tourName">Tên
		 * tour</label> <div class="invalid-feedback">Tên tour nằm trong khoảng 15 đến
		 * 255 ký tự.</div> </div> <div class="row mb-3"> <div class="col-md-6"> <div
		 * class="form-floating"> <input type="number" class="form-control"
		 * id="tourDuration" placeholder="Thời lượng tour" required> <label
		 * for="tourDuration">Thời lượng tour (ngày)</label> <div
		 * class="invalid-feedback">Từ 1 đến 60 ngày</div>
		 * 
		 * </div> </div> <div class="col-md-6 mt-3 mt-md-0"> <div class="form-floating">
		 * <input type="text" class="form-control" id="tourDeparture"
		 * placeholder="Nơi khởi hành" required> <label for="tourDeparture">Nơi khởi
		 * hành</label> <div class="invalid-feedback">Độ dài từ 6 đến 255 ký tự</div>
		 * </div> </div> </div> <div class="form-floating mb-3"> <input type="text"
		 * class="form-control" id="tourAttractions" placeholder="Điểm tham quan"
		 * required> <label for="tourAttractions">Điểm tham quan</label> <div
		 * class="invalid-feedback">Độ dài từ 6 đến 255 ký tự</div> </div> <div
		 * class="form-floating mb-3"> <input type="text" class="form-control"
		 * id="tourTarget" placeholder="Đối tượng nhắm tới" required> <label
		 * for="tourTarget">Đối tượng thích hợp</label> <div class="invalid-feedback">Độ
		 * dài từ 6 đến 255 ký tự</div>
		 * 
		 * </div> <div class="form-floating mb-3"> <input type="text"
		 * class="form-control" id="tourCuisine" placeholder="Ẩm thực" required> <label
		 * for="tourCuisine">Ẩm thực</label> <div class="invalid-feedback">Độ dài từ 6
		 * đến 255 ký tự</div>
		 * 
		 * </div> <div class="row mb-3"> <div class="col-md-6"> <div
		 * class="form-floating"> <input type="text" class="form-control"
		 * id="tourIdealTime" placeholder="Thời điểm lý tưởng" required> <label
		 * for="tourIdealTime">Thời điểm lý tưởng</label> <div
		 * class="invalid-feedback">Độ dài từ 6 đến 255 ký tự</div> </div>
		 * 
		 * </div> <div class="col-md-6 mt-3 mt-md-0"> <div class="form-floating">
		 * <select class="form-select" id="idCategory" required> <option value=""
		 * selected disabled hidden>Chọn thể loại</option> <option value="1">Du lịch
		 * biển</option> <option value="2">Du lịch núi</option> <option value="3">Du
		 * lịch văn hóa</option> <option value="4">Du lịch ẩm thực</option> <option
		 * value="5">Du lịch sinh thái</option> <option value="6">Du lịch mạo
		 * hiểm</option> </select> <label for="floatingSelect">Thể loại</label> <div
		 * class="invalid-feedback">Vui lòng chọn thể loại.</div> </div> </div> </div>
		 * 
		 * <div class="mb-0"> <div class="card pt-3"> <div class="card-body"> <label
		 * for="quill-included" class="mb-1">Đã bao gồm</label> <div
		 * id="quill-included"></div> <div class="invalid-feedback">Độ dài từ 20 ký tự
		 * trở lên.</div> </div> </div> </div>
		 * 
		 * <div class="mb-0"> <div class="card pt-3 mt-0"> <div class="card-body">
		 * <label for="quill-not-included" class="mb-1">Chưa bao gồm</label> <div
		 * id="quill-not-included"></div> <div class="invalid-feedback">Độ dài từ 20 ký
		 * tự trở lên.</div> </div> </div> </div> <div class="mb-0"> <div
		 * class="card pt-3"> <div class="card-body"> <label for="quill-description"
		 * class="mb-1">Mô tả</label> <div id="quill-description"></div> <div
		 * class="invalid-feedback">Độ dài từ 20 ký tự trở lên.</div> </div> </div>
		 * </div>
		 * 
		 * <div class="row mb-3"> <legend class="col-form-label col-sm-auto pt-0">Phương
		 * tiện</legend> <div class="col-sm-9 ms-0"> <div
		 * class="form-check form-check-inline"> <input class="form-check-input"
		 * type="checkbox" id="vehicleCar" checked> <label class="form-check-label"
		 * for="vehicleCar">Ô tô</label> </div> <div
		 * class="form-check form-check-inline"> <input class="form-check-input"
		 * type="checkbox" id="vehiclePlane" checked> <label class="form-check-label"
		 * for="vehiclePlane">Máy bay</label> </div> <div
		 * class="form-check form-check-inline"> <input class="form-check-input"
		 * type="checkbox" id="vehicleTrain" checked> <label class="form-check-label"
		 * for="vehicleTrain">Tàu hoả</label> </div> <div
		 * class="form-check form-check-inline"> <input class="form-check-input"
		 * type="checkbox" id="vehicleShip" checked> <label class="form-check-label"
		 * for="vehicleShip">Tàu thuỷ</label> </div> </div> </div> <div
		 * class="row mb-3 d-flex justify-content-center justify-content-md-end"> <div
		 * class="col-auto"> <button type="button" class="btn btn-danger"
		 * data-bs-toggle="modal" data-bs-target="#staticBackdrop"
		 * style="width: 150px;">Hủy bỏ</button> </div> <div class="col-auto"> <button
		 * type="submit" class="btn btn-primary" style="width: 150px;">Thêm tour
		 * mới</button> </div>
		 * 
		 * </div>
		 * 
		 * <!-- Modal --> <div class="modal" id="staticBackdrop"
		 * data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel"
		 * aria-hidden="true"> <div class="modal-dialog modal-dialog-centered"> <div
		 * class="modal-content"> <div class="modal-header"> <h5
		 * class="modal-title text-danger fw-bold fs-4" id="staticBackdropLabel">Xác
		 * nhận thoát</h5> <button type="button" class="btn-close fs-3"
		 * data-bs-dismiss="modal" aria-label="Close"></button> </div> <div
		 * class="modal-body"> Thông tin tour sẽ không được lưu? </div> <div
		 * class="modal-footer"> <button type="button" class="btn btn-danger"
		 * data-bs-dismiss="modal">Hủy</button> <button type="button"
		 * class="btn btn-primary">Xác nhận</button> </div> </div> </div> </div>
		 * 
		 * </div> </div>
		 * 
		 * <hr>
		 * 
		 * <div class="card"> <div class="card-body"> <h5 class="card-title">Chương
		 * trình tour</h5> <div class="card"> <div
		 * class="card-header bg-secondary text-white text-center text-md-start">Ngày
		 * <span class="day">1</span></div>
		 * 
		 * <ul class="list-group list-group-flush"> <li class="list-group-item pb-4">
		 * <div class="row"> <div class="col-md-8 col-sm-12"> <div
		 * class="form-floating"> <input type="text" class="form-control"
		 * id="tourIdealTime" placeholder="Thời điểm lý tưởng"> <label>Địa điểm chương
		 * trình</label> </div> </div>
		 * 
		 * <div class="col-md-3 col-sm-12 ms-0 meal"> <p
		 * class="mb-1 pt-1 ps-1 ms-lg-0 ps-lg-0 ps-md-0 ms-md-0">Bữa ăn</p> <div
		 * class="d-flex justify-content-md-between justify-content-sm-evenly"> <div
		 * class="form-check form-check-inline"> <label> <input class="form-check-input"
		 * type="checkbox" value="Sáng" checked> Sáng </label>
		 * 
		 * </div> <div class="form-check form-check-inline"> <label> <input
		 * class="form-check-input" type="checkbox" value="Trưa" checked> Trưa </label>
		 * 
		 * </div> <div class="form-check form-check-inline"> <label> <input
		 * class="form-check-input" type="checkbox" value="Tối" checked> Tối </label>
		 * </div> </div> </div>
		 * 
		 * <div class="col-12 mt-2 h-100"> <label class="mb-1">Nội dung chương
		 * trình</label> <div class="quill-editor-2"></div> <textarea name="content"
		 * id="quillContent" hidden></textarea> </div>
		 * 
		 * </div> </li> </ul>
		 * 
		 * </div>
		 * 
		 * <button type="submit" id="saveAll" class="btn btn-primary float-end"
		 * style="width: 150px;">Lưu</button> </div>
		 * 
		 * </div>
		 * 
		 * 
		 * </div> </div> <!-- ...existing code... -->
		 * 
		 * 
		 * </div> </div>
		 * 
		 * </div>
		 * 
		 * 
		 * </div> </form><!-- End General Form Elements --> </section>
		 * 
		 * </main><!-- End #main --> """);
		 */

		RequestDispatcher fo = request.getRequestDispatcher("/fo");
		if (fo != null) {
			fo.include(request, response);
		}

		out.append(
				"  <a href=\"#\" class=\"back-to-top d-flex align-items-center justify-content-center\"><i class=\"bi bi-arrow-up-short\"></i></a>");
		out.append("");
		out.append("  <!-- Vendor JS Files -->");
		out.append("  <script src=\"/adv/assets/vendor/apexcharts/apexcharts.min.js\"></script>");
		out.append("  <script src=\"/adv/assets/vendor/bootstrap/js/bootstrap.bundle.min.js\"></script>");
		out.append("  <script src=\"/adv/assets/vendor/chart.js/chart.umd.js\"></script>");
		out.append("  <script src=\"/adv/assets/vendor/echarts/echarts.min.js\"></script>");
		out.append("  <script src=\"/adv/assets/vendor/quill/quill.js\"></script>");
		out.append("  <script src=\"/adv/assets/vendor/simple-datatables/simple-datatables.js\"></script>");
		out.append("  <script src=\"/adv/assets/vendor/tinymce/tinymce.min.js\"></script>");
		out.append("  <script src=\"/adv/assets/vendor/php-email-form/validate.js\"></script>");
		/*
		 * out.append(""" <script> // Khởi tạo Quill Editor cho "Đã bao gồm" var
		 * quillIncluded = new Quill('#quill-included', { theme: 'snow' });
		 * 
		 * // Khởi tạo Quill Editor cho "Chưa bao gồm" var quillNotIncluded = new
		 * Quill('#quill-not-included', { theme: 'snow' });
		 * 
		 * // Khởi tạo Quill Editor cho "Mô tả" var quillDescription = new
		 * Quill('#quill-description', { theme: 'snow' });
		 * 
		 * // Khởi tạo Quill Editor cho "Mô tả" // var quillDescription = new
		 * Quill('#quill-description', { // theme: 'snow' // });
		 * 
		 * document.addEventListener('DOMContentLoaded', function () {
		 * document.querySelectorAll('.quill-editor-2').forEach(function (el) { const
		 * quill = new Quill(el, { modules: { toolbar: [ [{ font: [] }, { size: [] }],
		 * ['bold', 'italic', 'underline', 'strike'], [{ color: [] }, { background: []
		 * }], [{ script: 'super' }, { script: 'sub' }], [{ list: 'ordered' }, { list:
		 * 'bullet' }, { indent: '-1' }, { indent: '+1' }], ['direction', { align: []
		 * }], ['link', 'image', 'video'], ['clean'] ] }, theme: 'snow' });
		 * 
		 * el.__quill = quill; }); });
		 * 
		 * document.getElementById('tourForm').addEventListener('submit', function () {
		 * document.querySelectorAll('.quill-editor-2').forEach(function (el, index) {
		 * const quill = el.__quill; if (quill) { const contentHtml =
		 * quill.root.innerHTML; // Lấy HTML const contentText = quill.getText(); // Lấy
		 * plain text console.log(`Editor ${index + 1} (HTML):`, contentHtml);
		 * console.log(`Editor ${index + 1} (Text):`, contentText);
		 * document.getElementById('quillContent').value = contentHtml; } else {
		 * console.log("HELLO2"); } }); });
		 * 
		 * </script> """);
		 */
		out.append("  <!-- Template Main JS File -->");
		out.append("  <script src=\"/adv/assets/js/main.js\"></script>");
		
		if (action.equalsIgnoreCase("add"))
			out.append("<script type=\"module\" src=\"/adv/assets/js/tour/form-tour.js\"></script>");
		else if(action.equalsIgnoreCase("updateImgs"))
			out.append("<script src=\"/adv/assets/js/tour/form-tour-update-img.js\"></script>");
		else if(action.equalsIgnoreCase("update"))
			out.append("<script type=\"module\" src=\"/adv/assets/js/tour/form-update-tour.js\"></script>");
		
		out.append("");
		out.append("</body>");
		out.append("");
		out.append("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		if (action == null) {
			resp.sendRedirect("/adv/to/tour");
			return;
		}

		if (action.equalsIgnoreCase("add")) {
			try {
				String tourJson = req.getParameter("tour");
				String tourProgramsJson = req.getParameter("tourPrograms");
				int categoryId = Integer.parseInt(req.getParameter("categoryId"));

				log.info("TOURJSON: {}", tourJson);
				log.info("TPJ: {}", tourProgramsJson);
				
				Tour tour = GsonProvider.getGson().fromJson(tourJson, Tour.class);
				List<TourProgram> programs = GsonProvider.getGson().fromJson(tourProgramsJson, new TypeToken<List<TourProgram>>() {
				}.getType());

				if (!Validate.validateTour(tour) || !Validate.validateTourProgramList(programs)) {
					log.info("VLD1: {}", Validate.validateTour(tour));
					log.info("VLD2: {}", Validate.validateTourProgramList(programs));
					resp.setContentType("application/json");
					resp.getWriter().write("{\"text\":\"Có lỗi xảy ra!\"}");
					return;
				}

				// Xử lý file upload
				List<String> urls = CloudinaryService.getInstance().getImgUrlsFromCloud(req);

				if (urls.size() == 0) {
					resp.setContentType("application/json");
					resp.getWriter().write("{\"text\":\"Có lỗi xảy ra với ảnh!\"}");
					return;
				}

				// Xử lý richtext
				programs.forEach(program -> {
					program.setDesciption(processRichText(program.getDesciption()));
				});

				UserAccount userAccount = (UserAccount) req.getSession().getAttribute("userLogined");
				int tocId = userAccount.getTourOperator().getId();
				System.out.println("FLAG1");
				String tourId = TourFunctionImpl.getInstance().addTour(tour, urls, programs, categoryId, tocId);
				System.out.println("FLAG1, tourId = "+tourId);
				if (tourId == "") {
					resp.setContentType("application/json");
					resp.getWriter().write("{\"text\":\"Có lỗi xảy ra khi lưu!\"}");
					return;
				}

//				String tourId = "T996-31564-2N1Đ";
				String urlDirect = "/adv/to/tour/tour_detail?tourid=" + URLEncoder.encode(tourId, "UTF-8");
				log.info("url {}", urlDirect);
				// Phản hồi
				resp.setContentType("application/json");
				req.getSession().setAttribute("actionTour", "Thêm tour thành công");
				// Servlet
				resp.getWriter().write("{\"redirect\":\""+urlDirect+"\"}");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (action.equalsIgnoreCase("updateImgs")) {
			try {
				String tourId = req.getParameter("tourid");
				String delImages = req.getParameter("delImages");
				
				if (tourId == "") {
					resp.setContentType("application/json");
					resp.getWriter().write("{\"text\":\"Có lỗi xảy ra khi lưu!\"}");
					return;
				}
				
				// Xử lý file upload
				List<String> urls = CloudinaryService.getInstance().getImgUrlsFromCloud(req);

//				if (urls.size() == 0) {
//					resp.setContentType("application/json");
//					resp.getWriter().write("{\"text\":\"Có lỗi xảy ra với ảnh!\"}");
//					return;
//				}

				UserAccount userAccount = (UserAccount) req.getSession().getAttribute("userLogined");
				int touId = userAccount.getTourOperator().getId();
	
//				String tourId = TourFunctionImpl.getInstance().addTour(tour, urls, programs, categoryId, tocId);

				if(!ImageFunctionImpl.getInstance().updateImgsByTourId(tourId, delImages, urls, touId)) {
					resp.setContentType("application/json");
					resp.getWriter().write("{\"text\":\"Có lỗi xảy ra khi lưu ảnh!\"}");
					return;
				}
				

				String urlDirect = "/adv/to/tour/tour_detail?tourid=" + URLEncoder.encode(tourId, "UTF-8");
				log.info("url {}", urlDirect);
				// Phản hồi
				resp.setContentType("application/json");
				req.getSession().setAttribute("actionTour", "Đã cập nhật ảnh tour");
				// Servlet
				resp.getWriter().write("{\"redirect\":\""+urlDirect+"\"}");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(action.equals("update")) {
			try {
				//Các thông tin nhận gồm tour, category, programs, delImg, newImg
				String tourJson = req.getParameter("tour");
				String tourProgramsJson = req.getParameter("tourPrograms");
				int categoryId = Integer.parseInt(req.getParameter("categoryId"));
				
				String tourId = req.getParameter("tourid");
				String delImages = req.getParameter("delImages");
				
				UserAccount userAccount = (UserAccount) req.getSession().getAttribute("userLogined");
				int touId = userAccount.getTourOperator().getId();
				
				if (tourId == "") {
					resp.setContentType("application/json");
					resp.getWriter().write("{\"text\":\"Có lỗi xảy ra khi lưu!\"}");
					return;
				}
				
				//tour, programs
				Tour tour = GsonProvider.getGson().fromJson(tourJson, Tour.class);
				tour.setTourId(tourId);
				
				List<TourProgram> programs = GsonProvider.getGson().fromJson(tourProgramsJson, new TypeToken<List<TourProgram>>() {
				}.getType());

				if (!Validate.validateTour(tour) || !Validate.validateTourProgramList(programs)) {
					resp.setContentType("application/json");
					resp.getWriter().write("{\"text\":\"Có lỗi xảy ra!\"}");
					return;
				}

				// Xử lý file upload
				List<String> urls = CloudinaryService.getInstance().getImgUrlsFromCloud(req);


				// Xử lý richtext
				programs.forEach(program -> {
					program.setDesciption(processRichText(program.getDesciption()));
					log.info("FINAL HTML for program {}: {}", program.getId(), program.getDesciption()); // log rõ từng program
				});
				
//				String tourId = TourFunctionImpl.getInstance().addTour(tour, urls, programs, categoryId, tocId);
				
				log.info("URL SIZE {}", urls.size());
				
				/** Cho chạy chung transaction của tour để còn roll back
				 *	Cập nhật thông tin tour (+cate, +tou), cập nhật chương trình, cập nhật ảnh như dưới
				 **/
				if(!TourFunctionImpl.getInstance().updateTour(tour, delImages, urls, programs, categoryId, touId)) {
					resp.setContentType("application/json");
					resp.getWriter().write("{\"text\":\"Có lỗi xảy ra khi lưu ảnh!\"}");
					return;
				}
				
				String urlDirect = "/adv/to/tour/tour_detail?tourid=" + URLEncoder.encode(tourId, "UTF-8");
				log.info("url {}", urlDirect);
				// Phản hồi
				resp.setContentType("application/json");
				req.getSession().setAttribute("actionTour", "Sửa tour thành công");
				// Servlet
				resp.getWriter().write("{\"redirect\":\""+urlDirect+"\"}");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

//		// Nhận HTML String từ client
//		String htmlString = req.getParameter("content");
//
//		log.info(htmlString);

		// Jsoup parse HTML
//		Document doc = Jsoup.parse(htmlString);
//		Elements imgs = doc.select("img[src^=data:image/]");
//
//		imgs.forEach(img -> {
//			String base64Data = img.attr("src").split(",")[1];// lấy phần base64 sau dấu ","
//			byte[] imageBytes = Base64.getDecoder().decode(base64Data);
//
//			// Thay thế src base64 bằng URL Cloudinary
//			img.attr("src", CloudinaryService.getInstance().getImgUrlsAfterUpload(imageBytes));
//		});
//
//		String processedHtml = doc.body().html();

//		resp.getWriter().write(processedHtml);
	}

//	private String processRichText(String htmlString) {
//		// Cho phép các tag HTML cơ bản, như <b>, <i>, <p>, <ul>, <li>, <a>, ...
//		Document doc = Jsoup.parse(htmlString);
//		Elements imgs = doc.select("img[src^=data:image/]");
//
//		imgs.forEach(img -> {
//			try {
//				String base64Data = img.attr("src").split(",")[1];// lấy phần base64 sau dấu ","
//				byte[] imageBytes = Base64.getDecoder().decode(base64Data);
//
//				// Thay thế src base64 bằng URL Cloudinary
//				// Upload
//		        String uploadedUrl = CloudinaryService.getInstance().getImgUrlsAfterUpload(imageBytes);
//		        if (uploadedUrl != null) {
//		            img.attr("src", uploadedUrl);
//		        } else {
//		            System.out.println("Upload ảnh thất bại (null URL)");
//		        }
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//			
//		});
//
//		log.info("BEFORE: {}", htmlString);
//		htmlString = Jsoup.clean(htmlString, Safelist.relaxed());
//		
//		log.info("AFTER: {}", htmlString);
//		
//		String processedHtml = doc.body().html();
//
//		return processedHtml;
//	}
	
	private String processRichText(String htmlString) {
	    // Parse HTML gốc
		log.info("HTML INIT {}", htmlString);
		
	    Document doc = Jsoup.parse(htmlString);
	    Elements imgs = doc.select("img[src^=data:image/]");

	    imgs.forEach(img -> {
	        try {
	            String base64Data = img.attr("src").split(",")[1]; // lấy phần base64 sau dấu ","
	            byte[] imageBytes = Base64.getDecoder().decode(base64Data);

	            // Upload ảnh và thay thế
	            String uploadedUrl = CloudinaryService.getInstance().getImgUrlsAfterUpload(imageBytes);
	            if (uploadedUrl != null) {
	                img.attr("src", uploadedUrl);
	            } else {
	                System.out.println("Upload ảnh thất bại (null URL)");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    });

	    // Lấy lại nội dung HTML sau khi xử lý ảnh
	    String updatedHtml = doc.body().html();

	    log.info("HTML UPDATE {}",updatedHtml);
	    
	    // Cho phép base64 hoặc url (http/https) tồn tại trong <img src="">
	    Safelist safelist = Safelist.relaxed();
	    safelist.addProtocols("img", "src", "http", "https", "data");

	    // Clean nội dung đã xử lý
	    String cleanedHtml = Jsoup.clean(updatedHtml, safelist);

	    log.info("HTML CLEAN {}", cleanedHtml);
	    
	    return cleanedHtml;
	}

}

package ads.to;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.lang3.StringUtils;

import ads.library.DiscountLibrary;
import ads.objects.Discount;
import ads.user.DiscountFunctionImpl;
import ads.util.GsonProvider;
import ads.util.Validate;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/to/discount")
@MultipartConfig
public class DiscountServlet extends HttpServlet {

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
		PrintWriter out = response.getWriter();
		
		if(request.getParameter("check")!=null) {
			int discountId = Integer.parseInt(request.getParameter("discountid"));
			boolean rs = DiscountFunctionImpl.getInstance().canUpOrDel(discountId);
			response.setContentType("application/json");
			out.append("{\"check\":").append(String.valueOf(rs)).append("}");
			log.info("CHECK {}", rs);
			return;
		}
		
		log.info("GO!");

		out.append("<!DOCTYPE html>");
		out.append("<html lang=\"en\">");
		out.append("");
		out.append("<head>");
		out.append("  <meta charset=\"utf-8\">");
		out.append("  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">");
		out.append("");
		out.append("  <title>Quản lý giảm giá</title>");
		out.append("  <meta content=\"\" name=\"description\">");
		out.append("  <meta content=\"\" name=\"keywords\">");
		out.append("");
		out.append("  <!-- Favicons -->");
		out.append("  <link href=\"../resources/Logo.svg\" rel=\"icon\"");
		out.append("  <link href=\"../assets/img/apple-touch-icon.png\" rel=\"apple-touch-icon\">");
		out.append("");
		out.append("  <!-- Google Fonts -->");
		out.append("  <link href=\"https://fonts.gstatic.com\" rel=\"preconnect\">");
		out.append(
				"  <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i\" rel=\"stylesheet\">");
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
		
		out.append("<script src=\"/adv/assets/vendor/jquery/jquery-3.7.1.min.js\"></script>");
		out.append("<script type=\"module\" src=\"/adv/assets/js/discount/discount.js\"></script>");
		
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

		/*out.append(
				"""
						 		<main id="main" class="main">

						  <div class="pagetitle d-flex">
						    <h1>Danh sách chương trình giảm giá</h1>
						    <nav class="ms-auto">
						      <ol class="breadcrumb">
						        <li class="breadcrumb-item"><i class="bi bi-house-door"></i></a></li>
						        <li class="breadcrumb-item">Chương trình giảm giá</li>
						      </ol>
						    </nav>
						  </div><!-- End Page Title -->

						  <section class="section">
						    <!-- <a href="add-discount.html" class="btn btn-success mb-2"> <i class="bi bi-plus-circle"></i>
						      Thêm mới </a> -->
						    <div class="row">
						      <div class="col-lg-12">

						        <div class="card">
						          <div class="card-body">
						            <div class="d-flex mt-3">
						              <!-- <div class="btn btn-info fw-bold me-2 ms-auto text-white"> <i class="bi bi-eye-fill"></i>
						                Sửa hiển thị </div> -->
						              <div class="btn btn-success fw-bold me-2 ms-auto" data-bs-toggle="modal" data-bs-target="#addDis"> <i
						                  class="bi bi-plus-circle-fill"></i>
						                Thêm giảm giá </div>
						            </div>
						            <!-- Table with stripped rows -->
						            <table class="table datatable">
						              <thead>
						                <tr>
						                  <th style="width: 10%;">ID</th>
						                  <th style="width: 50%;">Chương trình giảm giá</th>
						                  <th style="width: 20%;">Giá trị</th>
						                  <!-- <th style="width: 10%;">Đơn vị</th> -->
						                  <!-- <th data-type="date" data-format="YYYY/DD/MM">Start Date</th> -->
						                  <th style="width: 10%;">Tùy chọn</th>
						                </tr>
						              </thead>
						              <tbody>
						                <tr>
						                  <td>1</td>
						                  <td>Giảm giá 50%</td>
						                  <td align="center" class="text-justify">
						                    50%</td>
						                  <!-- <td align="center">
						                    %
						                  </td> -->
						                  <td class="d-flex justify-content-evenly" data-order="null" data-search="null">
						                    <button type="button" class="btn btn-primary" data-bs-toggle="modal"
						                      data-bs-target="#updateDis"><i class="bi bi-pencil-fill"></i></button>
						                    <button type="button" class="btn btn-danger" data-bs-toggle="modal"
						                      data-bs-target="#staticBackdrop"><i class="bi bi-trash3-fill"></i></button>
						                  </td>
						                </tr>
						                <tr>
						                  <td>2</td>
						                  <td>Giảm giá 10%</td>
						                  <td align="center" class="text-justify">
						                    10%</td>
						                  <!-- <td align="center">
						                    %
						                  </td> -->
						                  <td class="d-flex justify-content-evenly">
						                    <button type="button" class="btn btn-primary" data-bs-toggle="modal"
						                      data-bs-target="#updateDis"><i class="bi bi-pencil-fill"></i></button>
						                    <button type="button" class="btn btn-danger" data-bs-toggle="modal"
						                      data-bs-target="#staticBackdrop"><i class="bi bi-trash3-fill"></i></button>
						                  </td>
						                </tr>
						              </tbody>
						            </table>

						            <!-- Modal Add Fes-->
						            <div class="modal fade" id="addDis" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
						              aria-labelledby="staticBackdropLabel" aria-hidden="true">
						              <div class="modal-dialog modal-md modal-dialog-centered">
						                <div class="modal-content py-4 px-3 rounded-5">
						                  <div class="modal-body">
						                    <div class="d-flex justify-content-between align-items-center">
						                      <h3 class="text-success fw-bold">Thêm chương trình giảm giá</h3>
						                      <button class="btn-close fs-2" data-bs-dismiss="modal"></button>
						                    </div>
						                    <hr>

						                    <form class="needs-validation">
						                      <div class="form-floating mb-3">
						                        <input type="text" class="form-control" id="floatingInput">
						                        <label>Tên chương trình giảm giá</label>
						                      </div>
						                      <div class="invalid-feedback">Please fill out this field.</div>

						                      <div class="row">

						                        <div class="col">
						                          <div class="form-floating mb-3">
						                            <input type="number" class="form-control" id="floatingInput">
						                            <label for="floatingInput">Giá trị</label>
						                          </div>
						                          <div class="invalid-feedback">Please fill out this field.</div>
						                        </div>
						                        <div class="col-auto text-center position-relative">
						                          <div class="form-floating">
						                            <select class="form-select" id="floatingSelect"
						                              aria-label="Floating label select example">
						                              <option value="VND" selected>VND</option>
						                              <option value="%">%</option>
						                            </select>
						                            <label for="floatingSelect">Đơn vị</label>
						                          </div>
						                        </div>

						                      </div>

						                      <div class="d-flex justify-content-center">
						                        <button type="button" class="btn btn-danger me-1" style="width: 30%;"
						                          data-bs-dismiss="modal">Hủy</button>
						                        <button type="button" class="btn btn-success ms-1" style="width: 30%;">Thêm</button>
						                      </div>
						                    </form>

						                  </div>
						                </div>
						              </div>
						            </div>

						            <!-- Modal Update Fes-->
						            <div class="modal" id="updateDis" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
						              aria-labelledby="staticBackdropLabel" aria-hidden="true">
						              <div class="modal-dialog modal-md modal-dialog-centered">
						                <div class="modal-content py-4 px-3 rounded-5">
						                  <div class="modal-body">
						                    <div class="d-flex justify-content-between align-items-center">
						                      <h3 class="text-primary fw-bold">Sửa chương trình giảm giá</h3>
						                      <button class="btn-close fs-2" data-bs-dismiss="modal"></button>
						                    </div>
						                    <hr>

						                    <form class="needs-validation">

						                      <div class="row">
						                        <div class="col-2">
						                          <div class="form-floating mb-3">
						                            <input type="text" class="form-control border-0 ms-1" id="floatingInput" value="1"
						                              readonly>
						                            <label>Mã</label>
						                          </div>
						                        </div>
						                        <div class="col">
						                          <div class="form-floating mb-3">
						                            <input type="text" class="form-control" id="floatingInput">
						                            <label>Tên chương trình giảm giá</label>
						                          </div>
						                          <div class="invalid-feedback">Please fill out this field.</div>
						                        </div>
						                      </div>


						                      <div class="row">

						                        <div class="col">
						                          <div class="form-floating mb-3">
						                            <input type="number" class="form-control" id="floatingInput">
						                            <label for="floatingInput">Giá trị</label>
						                          </div>
						                          <div class="invalid-feedback">Please fill out this field.</div>
						                        </div>
						                        <div class="col-auto text-center position-relative">
						                          <div class="form-floating">
						                            <select class="form-select" id="floatingSelect"
						                              aria-label="Floating label select example">
						                              <option value="VND" selected>VND</option>
						                              <option value="%">%</option>
						                            </select>
						                            <label for="floatingSelect">Đơn vị</label>
						                          </div>
						                        </div>

						                      </div>

						                      <div class="d-flex justify-content-center">
						                        <button type="button" class="btn btn-danger me-1" style="width: 30%;"
						                          data-bs-dismiss="modal">Hủy</button>
						                        <button type="button" class="btn btn-primary ms-1" style="width: 30%;">Sửa</button>
						                      </div>
						                    </form>

						                  </div>
						                </div>
						              </div>
						            </div>

						            <!-- Modal -->
						            <div class="modal" id="staticBackdrop" data-bs-keyboard="false" tabindex="-1"
						              aria-labelledby="staticBackdropLabel" aria-hidden="true">
						              <div class="modal-dialog modal-dialog-centered">
						                <div class="modal-content">
						                  <div class="modal-header">
						                    <h5 class="modal-title text-danger fw-bold fs-4" id="staticBackdropLabel">Xác nhận xóa</h5>
						                    <button type="button" class="btn-close fs-3" data-bs-dismiss="modal" aria-label="Close"></button>
						                  </div>
						                  <div class="modal-body">
						                    Bạn có muốn xóa &lt;Tên chương trình giảm giá&gt;?
						                  </div>
						                  <div class="modal-footer">
						                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Hủy</button>
						                    <button type="button" class="btn btn-primary">Xác nhận</button>
						                  </div>
						                </div>
						              </div>
						            </div>
						 		</div>
						        </div>

						      </div>
						    </div>
						  </section>

						</main><!-- End #main -->
						 		""");
		out.append("  </main><!-- End #main -->");*/

		out.append(DiscountLibrary.viewDiscount(request));
		
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
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		resp.setContentType("application/json");
		
		String action = req.getParameter("action");
		if(StringUtils.isBlank(action)) return;
		
		try {
			if(action.equalsIgnoreCase("add")) {
				String discountJson = req.getParameter("discount");
				
				log.info("DiscountJson: {}", discountJson);
				
				if(StringUtils.isBlank(discountJson)) {
					resp.getWriter().write("{\"rs\":false}");
					return;
				}
				
				Discount discount = GsonProvider.getGson().fromJson(discountJson, Discount.class);
				
				if(Validate.validateDiscount(discount)) {
					resp.getWriter().write("{\"rs\":false}");
					return;
				}
				
				Discount addedDiscount = DiscountFunctionImpl.getInstance().addDiscount(discount);
				
				if(addedDiscount==null) {
					resp.getWriter().write("{\"rs\":false}");
					return;
				}
				
				String discountRes = GsonProvider.getGson().toJson(addedDiscount, Discount.class);
				resp.getWriter().write("{\"rs\":true, \"discount\": "+discountRes+" }");
				
			} else if(action.equalsIgnoreCase("update")) {
				String discountJson = req.getParameter("discount");
				Discount discount = GsonProvider.getGson().fromJson(discountJson, Discount.class);
				
				if(Validate.validateDiscount(discount)) {
					resp.getWriter().write("{\"rs\":false}");
					return;
				}
				
				if(!DiscountFunctionImpl.getInstance().updateDiscount(discount)) {
					resp.getWriter().write("{\"rs\":false}");
					return;
				} else {
					resp.getWriter().write("{\"rs\":true}");
				}

			} else if(action.equalsIgnoreCase("del")) {
				int discountId = Integer.parseInt(req.getParameter("discountid"));
				if(!DiscountFunctionImpl.getInstance().deleteDiscount(discountId)) {
					resp.getWriter().write("{\"rs\":false}");
					return;
				} else {
					resp.getWriter().write("{\"rs\":true}");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resp.getWriter().write("{\"text\":\"Có lỗi xảy ra!\"}");
		}
		
	}

}

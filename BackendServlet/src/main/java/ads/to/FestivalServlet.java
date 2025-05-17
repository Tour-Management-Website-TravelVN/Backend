package ads.to;

import java.io.IOException;
import java.io.PrintWriter;

import ads.user.BookingImpl;
import ads.user.UserLibrary;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/to/festival")
public class FestivalServlet extends HttpServlet {

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
		
		out.append("<!DOCTYPE html>");
		out.append("<html lang=\"en\">");
		out.append("");
		out.append("<head>");
		out.append("  <meta charset=\"utf-8\">");
		out.append("  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">");
		out.append("");
		out.append("  <title>Quản lý lễ hội</title>");
		out.append("  <meta content=\"\" name=\"description\">");
		out.append("  <meta content=\"\" name=\"keywords\">");
		out.append("");
		out.append("  <!-- Favicons -->");
		out.append("  <link href=\"../resources/Logo.svg\" rel=\"icon\">");
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

		out.append(
				"""
								<main id="main" class="main">

						  <div class="pagetitle d-flex">
						    <h1>Danh sách lễ hội</h1>
						    <nav class="ms-auto">
						      <ol class="breadcrumb">
						        <li class="breadcrumb-item"><i class="bi bi-house-door"></i></a></li>
						        <li class="breadcrumb-item">Quản lý lễ hội</li>
						      </ol>
						    </nav>
						  </div><!-- End Page Title -->

						  <section class="section">
						    <div class="row">
						      <div class="col-lg-12">
						        <div class="card">
						          <div class="card-body">
						            <div class="d-flex mt-3">
						              <!-- <div class="btn btn-info fw-bold me-2 ms-auto text-white"> <i class="bi bi-eye-fill"></i>
						                Sửa hiển thị </div> -->
						              <div class="btn btn-success fw-bold me-2 ms-auto" data-bs-toggle="modal" data-bs-target="#addFes"> <i
						                  class="bi bi-plus-circle-fill"></i>
						                Thêm lễ hội </div>
						            </div>
						            <!-- Table with stripped rows -->
						            <!-- Table with stripped rows -->
						            <!--<table class="table datatable">
						              <thead>
						                <tr>
						                  <th style="width: 5%;">ID</th>
						                  <th style="width: 20%;">Tên lễ hội</th>
						                  <th style="width: 30%;">Mô tả</th>
						                  <th style="width: 10%;">Hiển thị</th>-->
						                  <!-- <th data-type="date" data-format="YYYY/DD/MM">Start Date</th> -->
						                  <!--<th style="width: 5%;">Tùy chọn</th>
						                </tr>
						              </thead>
						              <tbody>
						                <tr>
						                  <td data-bs-toggle="modal" data-bs-target="#viewFes" style="cursor: pointer;">1</td>
						                  <td data-bs-toggle="modal" data-bs-target="#viewFes" style="cursor: pointer;">Giỗ tổ Hùng Vương</td>
						                  <td class="text-justify" data-bs-toggle="modal" data-bs-target="#viewFes" style="cursor: pointer;">
						                    Giỗ tổ Hùng Vương 10/3</td>
						                  <td align="center" data-order="Hiện" data-search="Hiện">
						                    <i class="bi bi-eye text-primary fs-4"></i>
						                    <span class="d-none">Hiện</span>
						                  </td>
						                  <td class="d-flex justify-content-evenly">
						                    <button type="button" class="btn btn-primary" data-bs-toggle="modal"
						                      data-bs-target="#updateFes"><i class="bi bi-pencil-fill"></i></button>
						                    <button type="button" class="btn btn-danger" data-bs-toggle="modal"
						                      data-bs-target="#staticBackdrop"><i class="bi bi-trash3-fill"></i></button>
						                  </td>
						                </tr>
						                <tr>
						                  <td data-bs-toggle="modal" data-bs-target="#viewFes" style="cursor: pointer;">2</td>
						                  <td data-bs-toggle="modal" data-bs-target="#viewFes" style="cursor: pointer;">Quốc tế lao động</td>
						                  <td class="text-justify" data-bs-toggle="modal" data-bs-target="#viewFes" style="cursor: pointer;">
						                    Ngày lễ 01/05</td>
						                  <td align="center" data-order="Ẩn" data-search="Ẩn">
						                    <i class="bi bi-eye-slash text-danger fs-4"></i>
						                    <span class="d-none">Ẩn</span>
						                  </td>
						                  <td class="d-flex justify-content-evenly">
						                    <button type="button" class="btn btn-primary" data-bs-toggle="modal"
						                      data-bs-target="#updateFes"><i class="bi bi-pencil-fill"></i></button>
						                    <button type="button" class="btn btn-danger" data-bs-toggle="modal"
						                      data-bs-target="#staticBackdrop"><i class="bi bi-trash3-fill"></i></button>
						                  </td>
						                </tr>
						              </tbody>
						            </table>-->
										""");
		out.append(UserLibrary.viewUser(new BookingImpl().getBookings(null, 0, (byte)0)));
		out.append("""
						            <!-- Modal Add Fes-->
						            <div class="modal fade" id="addFes" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
						              aria-labelledby="staticBackdropLabel" aria-hidden="true">
						              <div class="modal-dialog modal-lg modal-dialog-centered">
						                <div class="modal-content py-4 px-3 rounded-5">
						                  <div class="modal-body">
						                    <div class="d-flex justify-content-between align-items-center">
						                      <h3 class="text-success fw-bold">Thêm lễ hội</h3>
						                      <button class="btn-close fs-2" data-bs-dismiss="modal"></button>
						                    </div>
						                    <hr>

						                    <form class="needs-validation">
						                      <div class="row">
						                        <div class="col">
						                          <div class="form-floating mb-3">
						                            <input type="text" class="form-control" id="floatingInput">
						                            <label for="floatingInput">Tên lễ hội</label>
						                          </div>
						                          <div class="invalid-feedback">Please fill out this field.</div>
						                        </div>
						                        <div class="col-auto text-center position-relative">
						                          <small class="text-secondary">Hiển thị</small><br>
						                          <i class="bi bi-eye text-primary fs-3 mb-5"></i>
						                        </div>
						                      </div>
						                      <div class="form-floating mb-3">
						                        <textarea class="form-control" style="height: 200px;"></textarea>
						                        <label>Mô tả</label>
						                      </div>
						                      <div class="invalid-feedback">Please fill out this field.</div>
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

						            <!-- Modal View Fes-->
						            <div class="modal" id="viewFes" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
						              aria-labelledby="staticBackdropLabel" aria-hidden="true">
						              <div class="modal-dialog modal-lg modal-dialog-centered">
						                <div class="modal-content py-4 px-3 rounded-5">
						                  <div class="modal-body">
						                    <div class="d-flex justify-content-between align-items-center">
						                      <h3 class="text-dark fw-bold">Thông tin chi tiết</h3>
						                      <button class="btn-close fs-2" data-bs-dismiss="modal"></button>
						                    </div>
						                    <hr>

						                    <form class="needs-validation">
						                      <div class="row">
						                        <div class="col-2">
						                          <div class="form-floating">
						                            <input type="text" class="form-control border-0 ms-0" value="1" readonly>
						                            <label for="floatingInput">Mã lễ hội</label>
						                          </div>
						                        </div>
						                        <div class="col">
						                          <div class="form-floating mb-3">
						                            <input type="text" class="form-control border-0 ms-1" value="Hoa Cỏ" readonly>
						                            <label for="floatingInput">Tên lễ hội</label>
						                          </div>
						                        </div>
						                        <div class="col-auto text-center position-relative">
						                          <small class="text-secondary">Hiển thị</small><br>
						                          <i class="bi bi-eye text-primary fs-3 mb-5"></i>
						                        </div>
						                      </div>

						                      <div class="form-floating mb-3">
						                        <textarea class="form-control border-0 ms-0" style="height: 200px;">poldih</textarea>
						                        <label>Mô tả</label>
						                      </div>

						                      <div class="d-flex justify-content-center">
						                        <button type="button" class="btn btn-danger me-1" style="width: 30%;" data-bs-toggle="modal"
						                          data-bs-target="#staticBackdrop">Xóa</button>
						                        <button type="button" class="btn btn-primary ms-1" style="width: 30%;" data-bs-toggle="modal"
						                          data-bs-target="#updateFes">Sửa</button>
						                      </div>
						                    </form>

						                  </div>
						                </div>
						              </div>
						            </div>

						            <!-- Modal Update Fes-->
						            <div class="modal" id="updateFes" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
						              aria-labelledby="staticBackdropLabel" aria-hidden="true">
						              <div class="modal-dialog modal-lg modal-dialog-centered">
						                <div class="modal-content py-4 px-3 rounded-5">
						                  <div class="modal-body">
						                    <div class="d-flex justify-content-between align-items-center">
						                      <h3 class="text-primary fw-bold">Sửa lễ hội</h3>
						                      <button class="btn-close fs-2" data-bs-dismiss="modal"></button>
						                    </div>
						                    <hr>

						                    <form class="needs-validation">
						                      <div class="row">
						                        <div class="col-2">
						                          <div class="form-floating">
						                            <input type="text" class="form-control border-0 ms-0" value="1" readonly>
						                            <label for="floatingInput">Mã lễ hội</label>
						                          </div>
						                        </div>
						                        <div class="col">
						                          <div class="form-floating mb-3">
						                            <input type="text" class="form-control" id="floatingInput">
						                            <label for="floatingInput">Tên lễ hội</label>
						                          </div>
						                        </div>
						                        <div class="col-auto text-center position-relative">
						                          <small class="text-secondary">Hiển thị</small><br>
						                          <i class="bi bi-eye text-primary fs-3 mb-5"></i>
						                        </div>
						                      </div>
						                      <div class="invalid-feedback">Please fill out this field.</div>
						                      <div class="form-floating mb-3">
						                        <textarea class="form-control" style="height: 200px;"></textarea>
						                        <label>Mô tả</label>
						                      </div>
						                      <div class="invalid-feedback">Please fill out this field.</div>
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
						                    Bạn có muốn xóa &lt;Tên lễ hội&gt;?
						                  </div>
						                  <div class="modal-footer">
						                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Hủy</button>
						                    <button type="button" class="btn btn-primary">Xác nhận</button>
						                  </div>
						                </div>
						              </div>
						            </div>

						            
						            <!-- End Table with stripped rows -->

						          </div>
						        </div>

						      </div>
						    </div>
						  </section>

						</main><!-- End #main -->
								""");

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
		doGet(req, resp);
	}

}

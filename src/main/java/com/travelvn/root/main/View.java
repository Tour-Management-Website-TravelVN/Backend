package com.travelvn.root.main;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/view")
public class View extends HttpServlet{

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
		//tham chieu header
		 
		 RequestDispatcher h = request.getRequestDispatcher("/he");
		 if (h!= null) {
			h.include(request, response);
		 }
		 RequestDispatcher s = request.getRequestDispatcher("/side");
		 if (s!= null) {
			s.include(request, response);
		 }
		  
			
			out.append("");
			out.append("  <main id=\"main\" class=\"main\">");
			out.append("");
			out.append("    <div class=\"pagetitle d-flex \">");
			out.append("      ");
			out.append("      <h1 class=\"fs-5\" >Tổng quan dữ liệu</h1>  ");
			out.append("      <nav class=\"ms-auto\">");
			out.append("        <ol class=\"breadcrumb\">");
			out.append(
					"          <li class=\"breadcrumb-item\"><a href=\"index.html\"><i class=\"bi bi-house-door\"></i></a></li>");
			out.append("          <li class=\"breadcrumb-item active\">Tổng quan dữ liệu</li>");
			out.append("        </ol>");
			out.append("      </nav>");
			out.append("    </div><!-- End Page Title -->");
			out.append("");
			out.append("    <section class=\"section dashboard\">");
			out.append("      <div class=\"row\">");
			out.append("");
			out.append("        <!-- Left side columns -->");
			out.append("        <div class=\"col-lg-8\">");
			out.append("          <div class=\"row\">");
			out.append("");
			out.append("            <!-- Sales Card -->");
			out.append("            <div class=\"col-xxl-4 col-md-6\">");
			out.append("              <div class=\"card info-card sales-card\">");
			out.append("");
			out.append("                <div class=\"filter\">");
			out.append(
					"                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
			out.append("                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
			out.append("                    <li class=\"dropdown-header text-start\">");
			out.append("                      <h6>Lọc</h6>");
			out.append("                    </li>");
			out.append("");
			out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Hôm nay</a></li>");
			out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Tháng này</a></li>");
			out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Năm nay</a></li>");
			out.append("                  </ul>");
			out.append("                </div>");
			out.append("");
			out.append("                <div class=\"card-body\">");
			out.append("                  <h5 class=\"card-title\">Tour đã bán <span>| Hôm nay</span></h5>");
			out.append("");
			out.append("                  <div class=\"d-flex align-items-center\">");
			out.append(
					"                    <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
			out.append("                      <i class=\"bi bi-cart\"></i>");
			out.append("                    </div>");
			out.append("                    <div class=\"ps-3\">");
			out.append("                      <h6>145</h6>");
			out.append(
					"                      <span class=\"text-muted small pt-2 ps-1\">Tăng</span> <span class=\"text-success small pt-1 fw-bold\">12%</span> ");
			out.append("");
			out.append("                    </div>");
			out.append("                  </div>");
			out.append("                </div>");
			out.append("");
			out.append("              </div>");
			out.append("            </div><!-- End Sales Card -->");
			out.append("");
			out.append("            <!-- Revenue Card -->");
			out.append("            <div class=\"col-xxl-4 col-md-6\">");
			out.append("              <div class=\"card info-card revenue-card\">");
			out.append("");
			out.append("                <div class=\"filter\">");
			out.append(
					"                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
			out.append("                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
			out.append("                    <li class=\"dropdown-header text-start\">");
			out.append("                      <h6>Lọc</h6>");
			out.append("                    </li>");
			out.append("");
			out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Hôm nay</a></li>");
			out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Tháng này</a></li>");
			out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Năm nay</a></li>");
			out.append("                  </ul>");
			out.append("                </div>");
			out.append("");
			out.append("                <div class=\"card-body\">");
			out.append("                  <h5 class=\"card-title\">Doanh thu <span>| Tháng này</span></h5>");
			out.append("");
			out.append("                  <div class=\"d-flex align-items-center\">");
			out.append(
					"                    <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
			out.append("                      <i class=\"bi bi-currency-dollar\"></i>");
			out.append("                    </div>");
			out.append("                    <div class=\"ps-3\">");
			out.append("                      <h6>160.000.00Đ</h6>");
			out.append(
					"                      <span class=\"text-muted small pt-2 ps-1\">Tăng  </span><span class=\"text-success small pt-1 fw-bold\">8%</span> ");
			out.append("");
			out.append("                    </div>");
			out.append("                  </div>");
			out.append("                </div>");
			out.append("");
			out.append("              </div>");
			out.append("            </div><!-- End Revenue Card -->");
			out.append("");
			out.append("            <!-- Customers Card -->");
			out.append("            <div class=\"col-xxl-4 col-xl-12\">");
			out.append("");
			out.append("              <div class=\"card info-card customers-card\">");
			out.append("");
			out.append("                <div class=\"filter\">");
			out.append(
					"                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
			out.append("                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
			out.append("                    <li class=\"dropdown-header text-start\">");
			out.append("                      <h6>Lọc</h6>");
			out.append("                    </li>");
			out.append("");
			out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Hôm nay</a></li>");
			out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Tháng này</a></li>");
			out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Năm nay</a></li>");
			out.append("                  </ul>");
			out.append("                </div>");
			out.append("");
			out.append("                <div class=\"card-body\">");
			out.append("                  <h5 class=\"card-title\">Số khách hàng <span>| Năm nay</span></h5>");
			out.append("");
			out.append("                  <div class=\"d-flex align-items-center\">");
			out.append(
					"                    <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
			out.append("                      <i class=\"bi bi-people\"></i>");
			out.append("                    </div>");
			out.append("                    <div class=\"ps-3\">");
			out.append("                      <h6>1244</h6>");
			out.append(
					"                      <span class=\"text-muted small pt-2 ps-1\">Giảm </span> <span class=\"text-danger small pt-1 fw-bold\">12%</span> ");
			out.append("");
			out.append("                    </div>");
			out.append("                  </div>");
			out.append("");
			out.append("                </div>");
			out.append("              </div>");
			out.append("");
			out.append("            </div><!-- End Customers Card -->");
			out.append("");
			out.append("            <!-- Reports -->");
			out.append("            <div class=\"col-12\">");
			out.append("              <div class=\"card\">");
			out.append("");
			out.append("                <div class=\"filter\">");
			out.append(
					"                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
			out.append("                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
			out.append("                    <li class=\"dropdown-header text-start\">");
			out.append("                      <h6>Filter</h6>");
			out.append("                    </li>");
			out.append("");
			out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Hôm nay</a></li>");
			out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Tháng này</a></li>");
			out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Năm nay</a></li>");
			out.append("                  </ul>");
			out.append("                </div>");
			out.append("");
			out.append("                <div class=\"card-body\">");
			out.append("                  <h5 class=\"card-title\">Báo cáo <span>/Hôm nay</span></h5>");
			out.append("");
			out.append("                  <!-- Line Chart -->");
			out.append("                  <div id=\"reportsChart\"></div>");
			out.append("");
			out.append("                  <script>");
			out.append("                   ");
			out.append("                    document.addEventListener(\"DOMContentLoaded\", () => {");
			out.append("                      new ApexCharts(document.querySelector(\"#reportsChart\"), {");
			out.append("                        series: [{");
			out.append("                          name: 'Bán ra',");
			out.append("                          data: [31, 40, 28, 51, 42, 82, 56],");
			out.append("                        }, {");
			out.append("                          name: 'Doanh thu',");
			out.append("                          data: [11, 32, 45, 32, 34, 52, 41]");
			out.append("                        }, {");
			out.append("                          name: 'Lượng khách hàng',");
			out.append("                          data: [15, 11, 32, 18, 9, 24, 11]");
			out.append("                        }],");
			out.append("                        chart: {");
			out.append("                          height: 350,");
			out.append("                          type: 'area',");
			out.append("                          toolbar: {");
			out.append("                            show: false");
			out.append("                          },");
			out.append("                        },");
			out.append("                        markers: {");
			out.append("                          size: 4");
			out.append("                        },");
			out.append("                        colors: ['#4154f1', '#2eca6a', '#ff771d'],");
			out.append("                        fill: {");
			out.append("                          type: \"gradient\",");
			out.append("                          gradient: {");
			out.append("                            shadeIntensity: 1,");
			out.append("                            opacityFrom: 0.3,");
			out.append("                            opacityTo: 0.4,");
			out.append("                            stops: [0, 90, 100]");
			out.append("                          }");
			out.append("                        },");
			out.append("                        dataLabels: {");
			out.append("                          enabled: false");
			out.append("                        },");
			out.append("                        stroke: {");
			out.append("                          curve: 'smooth',");
			out.append("                          width: 2");
			out.append("                        },");
			out.append("                        xaxis: {");
			out.append("                          type: 'datetime',");
			out.append(
					"                          categories: [\"2018-09-19T00:00:00.000Z\", \"2018-09-19T01:30:00.000Z\", \"2018-09-19T02:30:00.000Z\", \"2018-09-19T03:30:00.000Z\", \"2018-09-19T04:30:00.000Z\", \"2018-09-19T05:30:00.000Z\", \"2018-09-19T06:30:00.000Z\"]");
			out.append("                        },");
			out.append("                        tooltip: {");
			out.append("                          x: {");
			out.append("                            format: 'dd/MM/yy HH:mm'");
			out.append("                          },");
			out.append("                        }");
			out.append("                      }).render();");
			out.append("                    });");
			out.append("                  </script>");
			out.append("                  <!-- End Line Chart -->");
			out.append("");
			out.append("                </div>");
			out.append("");
			out.append("              </div>");
			out.append("            </div><!-- End Reports -->");
			out.append("");
			out.append("            <!-- Recent Sales -->");
			out.append("            <div class=\"col-12\">");
			out.append("              <div class=\"card recent-sales overflow-auto\">");
			out.append("                <div class=\"filter\">");
			out.append(
					"                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
			out.append("                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
			out.append("                    <li class=\"dropdown-header text-start\">");
			out.append("                      <h6>Lọc</h6>");
			out.append("                    </li>");
			out.append("");
			out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Hôm nay</a></li>");
			out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Tháng này</a></li>");
			out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Năm nay</a></li>");
			out.append("                  </ul>");
			out.append("                </div>");
			out.append("");
			out.append("                <div class=\"card-body\">");
			out.append("                  <h5 class=\"card-title\">Đã bán <span>| Hôm nay</span></h5>");
			out.append("");
			out.append("                  <table class=\"table table-borderless datatable\">");
			out.append("                    <thead>");
			out.append("                      <tr>");
			out.append("                        <th scope=\"col\">ID</th>");
			out.append("                        <th scope=\"col\">Tên khách hàng</th>");
			out.append("                        <th scope=\"col\">Tour</th>");
			out.append("                        <th scope=\"col\">Giá bán</th>");
			out.append("                        <th scope=\"col\">Trạng thái</th>");
			out.append("                      </tr>");
			out.append("                    </thead>");
			out.append("                    <tbody>");
			out.append("                      <tr>");
			out.append("                        <th scope=\"row\"><a href=\"#\">#2457</a></th>");
			out.append("                        <td>Brandon Jacob</td>");
			out.append("                        <td><a href=\"#\" class=\"text-primary\">At praesentium minu</a></td>");
			out.append("                        <td>$64</td>");
			out.append("                        <td><span class=\"badge bg-success\">Approved</span></td>");
			out.append("                      </tr>");
			out.append("                      <tr>");
			out.append("                        <th scope=\"row\"><a href=\"#\">#2147</a></th>");
			out.append("                        <td>Bridie Kessler</td>");
			out.append(
					"                        <td><a href=\"#\" class=\"text-primary\">Blanditiis dolor omnis similique</a></td>");
			out.append("                        <td>$47</td>");
			out.append("                        <td><span class=\"badge bg-warning\">Pending</span></td>");
			out.append("                      </tr>");
			out.append("                      <tr>");
			out.append("                        <th scope=\"row\"><a href=\"#\">#2049</a></th>");
			out.append("                        <td>Ashleigh Langosh</td>");
			out.append(
					"                        <td><a href=\"#\" class=\"text-primary\">At recusandae consectetur</a></td>");
			out.append("                        <td>$147</td>");
			out.append("                        <td><span class=\"badge bg-success\">Approved</span></td>");
			out.append("                      </tr>");
			out.append("                      <tr>");
			out.append("                        <th scope=\"row\"><a href=\"#\">#2644</a></th>");
			out.append("                        <td>Angus Grady</td>");
			out.append(
					"                        <td><a href=\"#\" class=\"text-primar\">Ut voluptatem id earum et</a></td>");
			out.append("                        <td>$67</td>");
			out.append("                        <td><span class=\"badge bg-danger\">Rejected</span></td>");
			out.append("                      </tr>");
			out.append("                      <tr>");
			out.append("                        <th scope=\"row\"><a href=\"#\">#2644</a></th>");
			out.append("                        <td>Raheem Lehner</td>");
			out.append(
					"                        <td><a href=\"#\" class=\"text-primary\">Sunt similique distinctio</a></td>");
			out.append("                        <td>$165</td>");
			out.append("                        <td><span class=\"badge bg-success\">Approved</span></td>");
			out.append("                      </tr>");
			out.append("                    </tbody>");
			out.append("                  </table>");
			out.append("");
			out.append("                </div>");
			out.append("");
			out.append("              </div>");
			out.append("            </div><!-- End Recent Sales -->");
			out.append("");
			out.append("            <!-- Top Selling -->");
			out.append("            <div class=\"col-12\">");
			out.append("              <div class=\"card top-selling overflow-auto\">");
			out.append("");
			out.append("                <div class=\"filter\">");
			out.append(
					"                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
			out.append("                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
			out.append("                    <li class=\"dropdown-header text-start\">");
			out.append("                      <h6>Lọc</h6>");
			out.append("                    </li>");
			out.append("");
			out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Hôm nay</a></li>");
			out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Tháng này</a></li>");
			out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Năm nay</a></li>");
			out.append("                  </ul>");
			out.append("                </div>");
			out.append("");
			out.append("                <div class=\"card-body pb-0\">");
			out.append("                  <h5 class=\"card-title\">Bán chạy nhất <span>| Hôm nay</span></h5>");
			out.append("");
			out.append("                  <table class=\"table table-borderless\">");
			out.append("                    <thead>");
			out.append("                      <tr>");
			out.append("                        <th scope=\"col\">Hình ảnh</th>");
			out.append("                        <th scope=\"col\">Tour</th>");
			out.append("                        <th scope=\"col\">Giá</th>");
			out.append("                        <th scope=\"col\">Đã đặt</th>");
			out.append("                        <th scope=\"col\">Doanh thu</th>");
			out.append("                      </tr>");
			out.append("                    </thead>");
			out.append("                    <tbody>");
			out.append("                      <tr>");
			out.append(
					"                        <th scope=\"row\"><a href=\"#\"><img src=\"assets/img/product-1.jpg\" alt=\"\"></a></th>");
			out.append(
					"                        <td><a href=\"#\" class=\"text-primary fw-bold\">Ut inventore ipsa voluptas nulla</a></td>");
			out.append("                        <td>$64</td>");
			out.append("                        <td class=\"fw-bold\">124</td>");
			out.append("                        <td>$5,828</td>");
			out.append("                      </tr>");
			out.append("                      <tr>");
			out.append(
					"                        <th scope=\"row\"><a href=\"#\"><img src=\"assets/img/product-2.jpg\" alt=\"\"></a></th>");
			out.append(
					"                        <td><a href=\"#\" class=\"text-primary fw-bold\">Exercitationem similique doloremque</a></td>");
			out.append("                        <td>$46</td>");
			out.append("                        <td class=\"fw-bold\">98</td>");
			out.append("                        <td>$4,508</td>");
			out.append("                      </tr>");
			out.append("                      <tr>");
			out.append(
					"                        <th scope=\"row\"><a href=\"#\"><img src=\"assets/img/product-3.jpg\" alt=\"\"></a></th>");
			out.append(
					"                        <td><a href=\"#\" class=\"text-primary fw-bold\">Doloribus nisi exercitationem</a></td>");
			out.append("                        <td>$59</td>");
			out.append("                        <td class=\"fw-bold\">74</td>");
			out.append("                        <td>$4,366</td>");
			out.append("                      </tr>");
			out.append("                      <tr>");
			out.append(
					"                        <th scope=\"row\"><a href=\"#\"><img src=\"assets/img/product-4.jpg\" alt=\"\"></a></th>");
			out.append(
					"                        <td><a href=\"#\" class=\"text-primary fw-bold\">Officiis quaerat sint rerum error</a></td>");
			out.append("                        <td>$32</td>");
			out.append("                        <td class=\"fw-bold\">63</td>");
			out.append("                        <td>$2,016</td>");
			out.append("                      </tr>");
			out.append("                      <tr>");
			out.append(
					"                        <th scope=\"row\"><a href=\"#\"><img src=\"assets/img/product-5.jpg\" alt=\"\"></a></th>");
			out.append(
					"                        <td><a href=\"#\" class=\"text-primary fw-bold\">Sit unde debitis delectus repellendus</a></td>");
			out.append("                        <td>$79</td>");
			out.append("                        <td class=\"fw-bold\">41</td>");
			out.append("                        <td>$3,239</td>");
			out.append("                      </tr>");
			out.append("                    </tbody>");
			out.append("                  </table>");
			out.append("");
			out.append("                </div>");
			out.append("");
			out.append("              </div>");
			out.append("            </div><!-- End Top Selling -->");
			out.append("");
			out.append("          </div>");
			out.append("        </div><!-- End Left side columns -->");
			out.append("");
			out.append("        <!-- Right side columns -->");
			out.append("        <div class=\"col-lg-4\">");
			out.append("");
			out.append("          <!-- Recent Activity -->");
			out.append("          <div class=\"card\">");
			out.append("            <div class=\"filter\">");
			out.append(
					"              <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
			out.append("              <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
			out.append("                <li class=\"dropdown-header text-start\">");
			out.append("                  <h6>Lọc</h6>");
			out.append("                </li>");
			out.append("");
			out.append("                <li><a class=\"dropdown-item\" href=\"#\">Hôm nay</a></li>");
			out.append("                <li><a class=\"dropdown-item\" href=\"#\">Tháng này</a></li>");
			out.append("                <li><a class=\"dropdown-item\" href=\"#\">Năm nay</a></li>");
			out.append("              </ul>");
			out.append("            </div>");
			out.append("");
			out.append("            <div class=\"card-body\">");
			out.append("              <h5 class=\"card-title\">Hoạt động <span>| Hôm nay</span></h5>");
			out.append("");
			out.append("              <div class=\"activity\">");
			out.append("");
			out.append("                <div class=\"activity-item d-flex\">");
			out.append("                  <div class=\"activite-label\">32 min</div>");
			out.append(
					"                  <i class='bi bi-circle-fill activity-badge text-success align-self-start'></i>");
			out.append("                  <div class=\"activity-content\">");
			out.append(
					"                    Quia quae rerum <a href=\"#\" class=\"fw-bold text-dark\">explicabo officiis</a> beatae");
			out.append("                  </div>");
			out.append("                </div><!-- End activity item-->");
			out.append("");
			out.append("                <div class=\"activity-item d-flex\">");
			out.append("                  <div class=\"activite-label\">56 min</div>");
			out.append(
					"                  <i class='bi bi-circle-fill activity-badge text-danger align-self-start'></i>");
			out.append("                  <div class=\"activity-content\">");
			out.append("                    Voluptatem blanditiis blanditiis eveniet");
			out.append("                  </div>");
			out.append("                </div><!-- End activity item-->");
			out.append("");
			out.append("                <div class=\"activity-item d-flex\">");
			out.append("                  <div class=\"activite-label\">2 hrs</div>");
			out.append(
					"                  <i class='bi bi-circle-fill activity-badge text-primary align-self-start'></i>");
			out.append("                  <div class=\"activity-content\">");
			out.append("                    Voluptates corrupti molestias voluptatem");
			out.append("                  </div>");
			out.append("                </div><!-- End activity item-->");
			out.append("");
			out.append("                <div class=\"activity-item d-flex\">");
			out.append("                  <div class=\"activite-label\">1 day</div>");
			out.append("                  <i class='bi bi-circle-fill activity-badge text-info align-self-start'></i>");
			out.append("                  <div class=\"activity-content\">");
			out.append(
					"                    Tempore autem saepe <a href=\"#\" class=\"fw-bold text-dark\">occaecati voluptatem</a> tempore");
			out.append("                  </div>");
			out.append("                </div><!-- End activity item-->");
			out.append("");
			out.append("                <div class=\"activity-item d-flex\">");
			out.append("                  <div class=\"activite-label\">2 days</div>");
			out.append(
					"                  <i class='bi bi-circle-fill activity-badge text-warning align-self-start'></i>");
			out.append("                  <div class=\"activity-content\">");
			out.append("                    Est sit eum reiciendis exercitationem");
			out.append("                  </div>");
			out.append("                </div><!-- End activity item-->");
			out.append("");
			out.append("                <div class=\"activity-item d-flex\">");
			out.append("                  <div class=\"activite-label\">4 weeks</div>");
			out.append(
					"                  <i class='bi bi-circle-fill activity-badge text-muted align-self-start'></i>");
			out.append("                  <div class=\"activity-content\">");
			out.append("                    Dicta dolorem harum nulla eius. Ut quidem quidem sit quas");
			out.append("                  </div>");
			out.append("                </div><!-- End activity item-->");
			out.append("");
			out.append("              </div>");
			out.append("");
			out.append("            </div>");
			out.append("          </div><!-- End Recent Activity -->");
			out.append("");
			out.append("          <!-- Budget Report -->");
			out.append("          <div class=\"card\">");
			out.append("            <div class=\"filter\">");
			out.append(
					"              <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
			out.append("              <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
			out.append("                <li class=\"dropdown-header text-start\">");
			out.append("                  <h6>Filter</h6>");
			out.append("                </li>");
			out.append("");
			out.append("                <li><a class=\"dropdown-item\" href=\"#\">Today</a></li>");
			out.append("                <li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>");
			out.append("                <li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>");
			out.append("              </ul>");
			out.append("            </div>");
			out.append("");
			out.append("            <div class=\"card-body pb-0\">");
			out.append("              <h5 class=\"card-title\">Báo cáo ngân sách <span>| Tháng này</span></h5>");
			out.append("");
			out.append("              <div id=\"budgetChart\" style=\"min-height: 400px;\" class=\"echart\"></div>");
			out.append("");
			out.append("              <script>");
			out.append("                document.addEventListener(\"DOMContentLoaded\", () => {");
			out.append(
					"                  var budgetChart = echarts.init(document.querySelector(\"#budgetChart\")).setOption({");
			out.append("                    legend: {");
			out.append("                      data: ['Allocated Budget', 'Actual Spending']");
			out.append("                    },");
			out.append("                    radar: {");
			out.append("                      indicator: [{");
			out.append("                          name: 'Sales',");
			out.append("                          max: 6500");
			out.append("                        },");
			out.append("                        {");
			out.append("                          name: 'Administration',");
			out.append("                          max: 16000");
			out.append("                        },");
			out.append("                        {");
			out.append("                          name: 'Information Technology',");
			out.append("                          max: 30000");
			out.append("                        },");
			out.append("                        {");
			out.append("                          name: 'Customer Support',");
			out.append("                          max: 38000");
			out.append("                        },");
			out.append("                        {");
			out.append("                          name: 'Development',");
			out.append("                          max: 52000");
			out.append("                        },");
			out.append("                        {");
			out.append("                          name: 'Marketing',");
			out.append("                          max: 25000");
			out.append("                        }");
			out.append("                      ]");
			out.append("                    },");
			out.append("                    series: [{");
			out.append("                      name: 'Budget vs spending',");
			out.append("                      type: 'radar',");
			out.append("                      data: [{");
			out.append("                          value: [4200, 3000, 20000, 35000, 50000, 18000],");
			out.append("                          name: 'Allocated Budget'");
			out.append("                        },");
			out.append("                        {");
			out.append("                          value: [5000, 14000, 28000, 26000, 42000, 21000],");
			out.append("                          name: 'Actual Spending'");
			out.append("                        }");
			out.append("                      ]");
			out.append("                    }]");
			out.append("                  });");
			out.append("                });");
			out.append("              </script>");
			out.append("");
			out.append("            </div>");
			out.append("          </div><!-- End Budget Report -->");
			out.append("");
			out.append("          <!-- Website Traffic -->");
			out.append("          <div class=\"card\">");
			out.append("            <div class=\"filter\">");
			out.append(
					"              <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
			out.append("              <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
			out.append("                <li class=\"dropdown-header text-start\">");
			out.append("                  <h6>Filter</h6>");
			out.append("                </li>");
			out.append("");
			out.append("                <li><a class=\"dropdown-item\" href=\"#\">Today</a></li>");
			out.append("                <li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>");
			out.append("                <li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>");
			out.append("              </ul>");
			out.append("            </div>");
			out.append("");
			out.append("            <div class=\"card-body pb-0\">");
			out.append("              <h5 class=\"card-title\">Lưu lượng truy cập <span>| Hôm nay</span></h5>");
			out.append("");
			out.append("              <div id=\"trafficChart\" style=\"min-height: 400px;\" class=\"echart\"></div>");
			out.append("");
			out.append("              <script>");
			out.append("");
			out.append("                document.addEventListener(\"DOMContentLoaded\", () => {");
			out.append("                  echarts.init(document.querySelector(\"#trafficChart\")).setOption({");
			out.append("                    tooltip: {");
			out.append("                      trigger: 'item'");
			out.append("                    },");
			out.append("                    legend: {");
			out.append("                      top: '5%',");
			out.append("                      left: 'center'");
			out.append("                    },");
			out.append("                    series: [{");
			out.append("                      name: 'Access From',");
			out.append("                      type: 'pie',");
			out.append("                      radius: ['40%', '70%'],");
			out.append("                      avoidLabelOverlap: false,");
			out.append("                      label: {");
			out.append("                        show: false,");
			out.append("                        position: 'center'");
			out.append("                      },");
			out.append("                      emphasis: {");
			out.append("                        label: {");
			out.append("                          show: true,");
			out.append("                          fontSize: '18',");
			out.append("                          fontWeight: 'bold'");
			out.append("                        }");
			out.append("                      },");
			out.append("                      labelLine: {");
			out.append("                        show: false");
			out.append("                      },");
			out.append("                      data: [{");
			out.append("                          value: 1048,");
			out.append("                          name: 'Search Engine'");
			out.append("                        },");
			out.append("                        {");
			out.append("                          value: 735,");
			out.append("                          name: 'Direct'");
			out.append("                        },");
			out.append("                        {");
			out.append("                          value: 580,");
			out.append("                          name: 'Email'");
			out.append("                        },");
			out.append("                        {");
			out.append("                          value: 484,");
			out.append("                          name: 'Union Ads'");
			out.append("                        },");
			out.append("                        {");
			out.append("                          value: 300,");
			out.append("                          name: 'Video Ads'");
			out.append("                        }");
			out.append("                      ]");
			out.append("                    }]");
			out.append("                  });");
			out.append("                });");
			out.append("");
			out.append("              </script>");
			out.append("");
			out.append("            </div>");
			out.append("          </div><!-- End Website Traffic -->");
			out.append("");
			out.append("          <!-- News & Updates Traffic -->");
			out.append("          <div class=\"card\">");
			out.append("            <div class=\"filter\">");
			out.append(
					"              <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
			out.append("              <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
			out.append("                <li class=\"dropdown-header text-start\">");
			out.append("                  <h6>Lọc</h6>");
			out.append("                </li>");
			out.append("");
			out.append("                <li><a class=\"dropdown-item\" href=\"#\">Hôm nay</a></li>");
			out.append("                <li><a class=\"dropdown-item\" href=\"#\">Tháng này</a></li>");
			out.append("                <li><a class=\"dropdown-item\" href=\"#\">Năm nay</a></li>");
			out.append("              </ul>");
			out.append("            </div>");
			out.append("");
			out.append("            <div class=\"card-body pb-0\">");
			out.append("              <h5 class=\"card-title\">Tin tức &amp; Cập nhật <span>| Hôm nay</span></h5>");
			out.append("");
			out.append("              <div class=\"news\">");
			out.append("                <div class=\"post-item clearfix\">");
			out.append("                  <img src=\"assets/img/news-1.jpg\" alt=\"\">");
			out.append("                  <h4><a href=\"#\">Nihil blanditiis at in nihil autem</a></h4>");
			out.append(
					"                  <p>Sit recusandae non aspernatur laboriosam. Quia enim eligendi sed ut harum...</p>");
			out.append("                </div>");
			out.append("");
			out.append("                <div class=\"post-item clearfix\">");
			out.append("                  <img src=\"assets/img/news-2.jpg\" alt=\"\">");
			out.append("                  <h4><a href=\"#\">Quidem autem et impedit</a></h4>");
			out.append(
					"                  <p>Illo nemo neque maiores vitae officiis cum eum turos elan dries werona nande...</p>");
			out.append("                </div>");
			out.append("");
			out.append("                <div class=\"post-item clearfix\">");
			out.append("                  <img src=\"assets/img/news-3.jpg\" alt=\"\">");
			out.append("                  <h4><a href=\"#\">Id quia et et ut maxime similique occaecati ut</a></h4>");
			out.append(
					"                  <p>Fugiat voluptas vero eaque accusantium eos. Consequuntur sed ipsam et totam...</p>");
			out.append("                </div>");
			out.append("");
			out.append("                <div class=\"post-item clearfix\">");
			out.append("                  <img src=\"assets/img/news-4.jpg\" alt=\"\">");
			out.append("                  <h4><a href=\"#\">Laborum corporis quo dara net para</a></h4>");
			out.append(
					"                  <p>Qui enim quia optio. Eligendi aut asperiores enim repellendusvel rerum cuder...</p>");
			out.append("                </div>");
			out.append("");
			out.append("                <div class=\"post-item clearfix\">");
			out.append("                  <img src=\"assets/img/news-5.jpg\" alt=\"\">");
			out.append("                  <h4><a href=\"#\">Et dolores corrupti quae illo quod dolor</a></h4>");
			out.append(
					"                  <p>Odit ut eveniet modi reiciendis. Atque cupiditate libero beatae dignissimos eius...</p>");
			out.append("                </div>");
			out.append("");
			out.append("              </div><!-- End sidebar recent posts-->");
			out.append("");
			out.append("            </div>");
			out.append("          </div><!-- End News & Updates -->");
			out.append("");
			out.append("        </div><!-- End Right side columns -->");
			out.append("");
			out.append("      </div>");
			out.append("    </section>");
			out.append("");
			out.append("  </main><!-- End #main -->");
			
			
			 RequestDispatcher f = request.getRequestDispatcher("/side");
			 if (f!= null) {
				f.include(request, response);
			 }
			
			out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		doGet(req, resp);
	}
	
	

}

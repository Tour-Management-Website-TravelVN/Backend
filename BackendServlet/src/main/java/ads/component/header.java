package ads.component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/he")
public class header extends HttpServlet{

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
			out.append("  <!-- ======= Header ======= -->");
			out.append("  <header id=\"header\" class=\"header fixed-top d-flex align-items-center\">");
			out.append("");
			out.append("    <div class=\"d-flex align-items-center justify-content-between\">");
			out.append("      <a href=\"index.html\" class=\"logo d-flex align-items-center\" style=\"height: max-content;\">");
			out.append("        <img src=\"../resources/Asset 2.svg\" alt=\"\" style=\"height: 100px; width: 40px;\" >");
			out.append("        <span class=\"d-none d-lg-block\">TravelVN</span>");
			out.append("      </a>");
			out.append("    </div><!-- End Logo -->");
			out.append("    <i class=\"bi bi-list toggle-sidebar-btn\"></i>");
			out.append("");
			out.append("    <div class=\"search-bar\">");
			out.append("      <form class=\"search-form d-flex align-items-center\" method=\"GET\" action=\"#\">");
			out.append(
					"        <input type=\"text\" name=\"query\" placeholder=\"Tìm kiếm\" title=\"Enter search keyword\" id=\"name\">");
			out.append(
					"        <button type=\"submit\" title=\"Search\"><i class=\"bi bi-search\" onclick=getData()></i></button>");
			out.append("      </form>");
			out.append("    </div><!-- End Search Bar -->");
			out.append("");
			out.append("    <nav class=\"header-nav ms-auto\">");
			out.append("      <ul class=\"d-flex align-items-center\">");
			out.append("");
			out.append("        <li class=\"nav-item d-block d-lg-none\">");
			out.append("          <a class=\"nav-link nav-icon search-bar-toggle \" href=\"#\">");
			out.append("            <i class=\"bi bi-search\"></i>");
			out.append("          </a>");
			out.append("        </li><!-- End Search Icon-->");
			out.append("");
//			out.append("        <li class=\"nav-item dropdown\">");
//			out.append("");
//			out.append("          <a class=\"nav-link nav-icon\" href=\"#\" data-bs-toggle=\"dropdown\">");
//			out.append("            <i class=\"bi bi-bell\"></i>");
//			out.append("            <span class=\"badge bg-primary badge-number\">4</span>");
//			out.append("          </a><!-- End Notification Icon -->");
//			out.append("");
//			out.append("          <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow notifications\">");
//			out.append("            <li class=\"dropdown-header\">");
//			out.append("              You have 4 new notifications");
//			out.append(
//					"              <a href=\"#\"><span class=\"badge rounded-pill bg-primary p-2 ms-2\">View all</span></a>");
//			out.append("            </li>");
//			out.append("            <li>");
//			out.append("              <hr class=\"dropdown-divider\">");
//			out.append("            </li>");
//			out.append("");
//			out.append("            <li class=\"notification-item\">");
//			out.append("              <i class=\"bi bi-exclamation-circle text-warning\"></i>");
//			out.append("              <div>");
//			out.append("                <h4>Lorem Ipsum</h4>");
//			out.append("                <p>Quae dolorem earum veritatis oditseno</p>");
//			out.append("                <p>30 min. ago</p>");
//			out.append("              </div>");
//			out.append("            </li>");
//			out.append("");
//			out.append("            <li>");
//			out.append("              <hr class=\"dropdown-divider\">");
//			out.append("            </li>");
//			out.append("");
//			out.append("            <li class=\"notification-item\">");
//			out.append("              <i class=\"bi bi-x-circle text-danger\"></i>");
//			out.append("              <div>");
//			out.append("                <h4>Atque rerum nesciunt</h4>");
//			out.append("                <p>Quae dolorem earum veritatis oditseno</p>");
//			out.append("                <p>1 hr. ago</p>");
//			out.append("              </div>");
//			out.append("            </li>");
//			out.append("");
//			out.append("            <li>");
//			out.append("              <hr class=\"dropdown-divider\">");
//			out.append("            </li>");
//			out.append("");
//			out.append("            <li class=\"notification-item\">");
//			out.append("              <i class=\"bi bi-check-circle text-success\"></i>");
//			out.append("              <div>");
//			out.append("                <h4>Sit rerum fuga</h4>");
//			out.append("                <p>Quae dolorem earum veritatis oditseno</p>");
//			out.append("                <p>2 hrs. ago</p>");
//			out.append("              </div>");
//			out.append("            </li>");
//			out.append("");
//			out.append("            <li>");
//			out.append("              <hr class=\"dropdown-divider\">");
//			out.append("            </li>");
//			out.append("");
//			out.append("            <li class=\"notification-item\">");
//			out.append("              <i class=\"bi bi-info-circle text-primary\"></i>");
//			out.append("              <div>");
//			out.append("                <h4>Dicta reprehenderit</h4>");
//			out.append("                <p>Quae dolorem earum veritatis oditseno</p>");
//			out.append("                <p>4 hrs. ago</p>");
//			out.append("              </div>");
//			out.append("            </li>");
//			out.append("");
//			out.append("            <li>");
//			out.append("              <hr class=\"dropdown-divider\">");
//			out.append("            </li>");
//			out.append("            <li class=\"dropdown-footer\">");
//			out.append("              <a href=\"#\">Show all notifications</a>");
//			out.append("            </li>");
//			out.append("");
//			out.append("          </ul><!-- End Notification Dropdown Items -->");
//			out.append("");
//			out.append("        </li><!-- End Notification Nav -->");
//			out.append("");
//			out.append("        <li class=\"nav-item dropdown\">");
//			out.append("");
//			out.append("          <a class=\"nav-link nav-icon\" href=\"#\" data-bs-toggle=\"dropdown\">");
//			out.append("            <i class=\"bi bi-chat-left-text\"></i>");
//			out.append("            <span class=\"badge bg-success badge-number\">3</span>");
//			out.append("          </a><!-- End Messages Icon -->");
//			out.append("");
//			out.append("          <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow messages\">");
//			out.append("            <li class=\"dropdown-header\">");
//			out.append("              You have 3 new messages");
//			out.append(
//					"              <a href=\"#\"><span class=\"badge rounded-pill bg-primary p-2 ms-2\">View all</span></a>");
//			out.append("            </li>");
//			out.append("            <li>");
//			out.append("              <hr class=\"dropdown-divider\">");
//			out.append("            </li>");
//			out.append("");
//			out.append("            <li class=\"message-item\">");
//			out.append("              <a href=\"#\">");
//			out.append("                <img src=\"assets/img/messages-1.jpg\" alt=\"\" class=\"rounded-circle\">");
//			out.append("                <div>");
//			out.append("                  <h4>Maria Hudson</h4>");
//			out.append(
//					"                  <p>Velit asperiores et ducimus soluta repudiandae labore officia est ut...</p>");
//			out.append("                  <p>4 hrs. ago</p>");
//			out.append("                </div>");
//			out.append("              </a>");
//			out.append("            </li>");
//			out.append("            <li>");
//			out.append("              <hr class=\"dropdown-divider\">");
//			out.append("            </li>");
//			out.append("");
//			out.append("            <li class=\"message-item\">");
//			out.append("              <a href=\"#\">");
//			out.append("                <img src=\"assets/img/messages-2.jpg\" alt=\"\" class=\"rounded-circle\">");
//			out.append("                <div>");
//			out.append("                  <h4>Anna Nelson</h4>");
//			out.append(
//					"                  <p>Velit asperiores et ducimus soluta repudiandae labore officia est ut...</p>");
//			out.append("                  <p>6 hrs. ago</p>");
//			out.append("                </div>");
//			out.append("              </a>");
//			out.append("            </li>");
//			out.append("            <li>");
//			out.append("              <hr class=\"dropdown-divider\">");
//			out.append("            </li>");
//			out.append("");
//			out.append("            <li class=\"message-item\">");
//			out.append("              <a href=\"#\">");
//			out.append("                <img src=\"assets/img/messages-3.jpg\" alt=\"\" class=\"rounded-circle\">");
//			out.append("                <div>");
//			out.append("                  <h4>David Muldon</h4>");
//			out.append(
//					"                  <p>Velit asperiores et ducimus soluta repudiandae labore officia est ut...</p>");
//			out.append("                  <p>8 hrs. ago</p>");
//			out.append("                </div>");
//			out.append("              </a>");
//			out.append("            </li>");
//			out.append("            <li>");
//			out.append("              <hr class=\"dropdown-divider\">");
//			out.append("            </li>");
//			out.append("");
//			out.append("            <li class=\"dropdown-footer\">");
//			out.append("              <a href=\"#\">Show all messages</a>");
//			out.append("            </li>");
//			out.append("");
//			out.append("          </ul><!-- End Messages Dropdown Items -->");
//			out.append("");
//			out.append("        </li><!-- End Messages Nav -->");
//			out.append("");
			out.append("        <li class=\"nav-item dropdown pe-3\">");
			out.append("");
			out.append("          <a class=\"nav-link nav-profile d-flex align-items-center pe-0\" href=\"#\" data-bs-toggle=\"dropdown\">");
//			out.append("            <img src=\"assets/img/profile-img.jpg\" alt=\"Profile\" class=\"rounded-circle\">");
			out.append("            <span class=\"d-none d-md-block dropdown-toggle ps-2\">Kiều Đức Thịnh</span>");
			out.append("          </a><!-- End Profile Iamge Icon -->");
			out.append("");
			out.append("          <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow profile\">");
			out.append("            <li class=\"dropdown-header\">");
			out.append("              <h6>Kiều Đức Thịnh</h6>");
			out.append("              <span>Business analyst</span>");
			out.append("            </li>");
			out.append("            <li>");
			out.append("              <hr class=\"dropdown-divider\">");
			out.append("            </li>");
			out.append("");
			out.append("            <li>");
			out.append(
					"              <a class=\"dropdown-item d-flex align-items-center\" href=\"users-profile.html\">");
			out.append("                <i class=\"bi bi-person\"></i>");
			out.append("                <span>Hồ sơ cá nhân</span>");
			out.append("              </a>");
			out.append("            </li>");
			out.append("            <li>");
			out.append("              <hr class=\"dropdown-divider\">");
			out.append("            </li>");
			out.append("");
			out.append("            <li>");
			out.append(
					"              <a class=\"dropdown-item d-flex align-items-center\" href=\"users-profile.html\">");
			out.append("                <i class=\"bi bi-gear\"></i>");
			out.append("                <span>Cài đặt tài khoản</span>");
			out.append("              </a>");
			out.append("            </li>");
			out.append("            <li>");
			out.append("              <hr class=\"dropdown-divider\">");
			out.append("            </li>");
//			out.append("");
//			out.append("            <li>");
//			out.append("              <a class=\"dropdown-item d-flex align-items-center\" href=\"pages-faq.html\">");
//			out.append("                <i class=\"bi bi-question-circle\"></i>");
//			out.append("                <span>Trợ giúp</span>");
//			out.append("              </a>");
//			out.append("            </li>");
			out.append("            <li>");
			out.append("              <hr class=\"dropdown-divider\">");
			out.append("            </li>");
			out.append("");
			out.append("            <li>");
			out.append("              <a class=\"dropdown-item d-flex align-items-center\" href=\"/travelvn_dashboard_be/user/logout\">");
			out.append("                <i class=\"bi bi-box-arrow-right\"></i>");
			out.append("                <span>Đăng xuất</span>");
			out.append("              </a>");
			out.append("            </li>");
			out.append("");
			out.append("          </ul><!-- End Profile Dropdown Items -->");
			out.append("        </li><!-- End Profile Nav -->");
			out.append("");
			out.append("      </ul>");
			out.append("    </nav><!-- End Icons Navigation -->");
			out.append("");
			out.append("  </header><!-- End Header -->");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(req, resp);
	}
	
	

}

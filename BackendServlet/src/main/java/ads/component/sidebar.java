package ads.component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/side")
public class sidebar extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html; charset=UTF-8");

		 HashMap<String, String> collapsed = new HashMap();
		 
		 String uri = request.getRequestURI();
		 String path = uri.substring(request.getContextPath().length());
		 
		 //Số trang
		 String[] pages = {"tour", "festival", "discount", "tourguide", "canceltour", "customer", "myinfo"};
		 
		 //Duyệt đẩy hashmap
		 Arrays.stream(pages).forEach(page -> {
			 if(path.contains(page)) {
				 collapsed.put(page, "");
			 }
		 });
		 
		 PrintWriter out = response.getWriter();
		 out.append("");
			out.append("  <!-- ======= Sidebar ======= -->");
			out.append("  <aside id=\"sidebar \" class=\"sidebar\">");
			out.append("");
			out.append("    <ul class=\"sidebar-nav\" id=\"sidebar-nav\">");
//			out.append("");
//			out.append("      <li class=\"nav-item\">");
//			out.append("        <a class=\"nav-link \" href=\"index.html\">");
//			out.append("          <i class=\"bi bi-grid\"></i>");
//			out.append("          <span>Dashboard</span>");
//			out.append("        </a>");
//			out.append("      </li><!-- End Dashboard Nav -->");
//			out.append("");
			out.append("      <li class=\"nav-item\">");
			out.append("        <a class=\"nav-link "+collapsed.getOrDefault("tour", "collapsed")+"\"  href=\"/adv/to/tour\">");
			out.append("          <i class=\"bi bi-layout-text-window-reverse\"></i><span>Quản lý tour</span>");
			out.append("        </a>");
//			out.append(
//					"        <ul id=\"tables-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">");
//			out.append("          <li>");
//			out.append("            <a href=\"tables-general.html\">");
//			out.append("              <i class=\"bi bi-circle\"></i><span>Table general</span>");
//			out.append("            </a>");
//			out.append("          </li>");
//			out.append("          <li>");
//			out.append("            <a href=\"Tour_unit_management.html\">");
//			out.append("              <i class=\"bi bi-circle\"></i><span>Danh sách tour</span>");
//			out.append("            </a>");
//			out.append("          </li>");
//			out.append("        </ul>");
			out.append("      </li><!-- End Tables Nav -->");
			out.append("");
			out.append("      <li class=\"nav-item\">");
			out.append(
					"        <a class=\"nav-link "+collapsed.getOrDefault("festival", "collapsed")+"\" data-bs-target=\"#charts-nav\" href=\"/adv/to/festival\">");
			out.append("          <i class=\"bi bi-flower1\"></i>");
			out.append("");
			out.append("    <span>Quản lý lễ hội</span>");
			out.append("        </a>");
			out.append("      </li><!-- End Charts Nav -->");
			out.append("      <li class=\"nav-item\">");
			out.append(
					"        <a class=\"nav-link "+collapsed.getOrDefault("discount", "collapsed")+"\" data-bs-target=\"#charts-nav\" href=\"/adv/to/discount\">");
			out.append("          <i class=\"bi bi-coin\"></i>");
			out.append("");
			out.append("    <span>Chương trình giảm giá</span>");
			out.append("        </a>");
			out.append("      </li><!-- End Charts Nav -->");
			out.append("      <li class=\"nav-item\">");
			out.append(
					"        <a class=\"nav-link "+collapsed.getOrDefault("tourguide", "collapsed")+"\" data-bs-target=\"#charts-nav\" href=\"tour_guide_management.html\">");
			out.append("          <i class=\"bi bi-flag-fill\"></i>");
			out.append("");
			out.append("    <span>Hướng dẫn viên</span>");
			out.append("        </a>");
			out.append("      </li><!-- End Charts Nav -->");
			out.append("");
			out.append("      ");
			out.append("      <li class=\"nav-item\">");
			out.append(
					"        <a class=\"nav-link "+collapsed.getOrDefault("canceltour", "collapsed")+"\" data-bs-target=\"#charts-nav\" href=\"/adv/to/Cancel-Request\">");
			out.append("          <i class=\"bi bi-card-checklist\"></i>");
			out.append("          <span>Yêu cầu huỷ tour</span>");
			out.append("        </a>");
			out.append("      </li>");
			out.append("      <li class=\"nav-item\">");
			out.append(
					"        <a class=\"nav-link "+collapsed.getOrDefault("customer", "collapsed")+"\" data-bs-target=\"#charts-nav\" href=\"/adv/to/Customer\">");
			out.append("          <i class=\"bi bi-people-fill\"></i>");
			out.append("          <span>Khách hàng</span>");
			out.append("        </a>");
			out.append("      </li><!-- End Charts Nav -->");
			out.append("");
			out.append("");
			out.append("      <li class=\"nav-item\">");
			out.append("        <a class=\"nav-link "+collapsed.getOrDefault("myinfo", "collapsed")+"\" href=\"users-profile.html\">");
			out.append("          <i class=\"bi bi-person\"></i>");
			out.append("          <span>Thông tin cá nhân</span>");
			out.append("        </a>");
			out.append("      </li><!-- End Profile Page Nav -->");
//			out.append("");
//			out.append("      <li class=\"nav-heading\">Khác</li>");
//			out.append("");
//			out.append("");
//			out.append("      <li class=\"nav-item\">");
//			out.append("        <a class=\"nav-link collapsed\" href=\"pages-faq.html\">");
//			out.append("          <i class=\"bi bi-question-circle\"></i>");
//			out.append("          <span>Hướng dẫn sử dụng</span>");
//			out.append("        </a>");
//			out.append("      </li><!-- End F.A.Q Page Nav -->");
//			out.append("");
//			out.append("      <li class=\"nav-item\">");
//			out.append("        <a class=\"nav-link collapsed\" href=\"pages-contact.html\">");
//			out.append("          <i class=\"bi bi-envelope\"></i>");
//			out.append("          <span>Liên hệ với admin</span>");
//			out.append("        </a>");
//			out.append("      </li><!-- End Contact Page Nav -->");
//			out.append("      <li class=\"nav-item\">");
//			out.append("        <a class=\"nav-link collapsed\" href=\"pages-contact.html\">");
//			out.append("          <i class=\"bi bi-gear\"></i>");
//			out.append("          <span>Cài đặt</span>");
//			out.append("        </a>");
//			out.append("      </li><!-- End Contact Page Nav -->");
//			out.append("");
//			out.append("");
			out.append("    </ul>");
			out.append("");
			out.append("  </aside><!-- End Sidebar-->");
		    
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(req, resp);
	}
	
	

}

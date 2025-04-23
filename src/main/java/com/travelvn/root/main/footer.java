package com.travelvn.root.main;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/fo")
public class footer extends HttpServlet{

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
		
			
			
			out.append("");
			out.append("  <!-- ======= Footer ======= -->");
			out.append("  <footer id=\"footer\" class=\"footer\">");

			out.append("    </div>");
			out.append("  </footer><!-- End Footer -->");
			out.append("");
			out.append(
					"  <a href=\"#\" class=\"back-to-top d-flex align-items-center justify-content-center\"><i class=\"bi bi-arrow-up-short\"></i></a>");
			out.append("  <!-- Vendor JS Files -->");
			out.append("  <script src=\"assets/vendor/apexcharts/apexcharts.min.js\"></script>");
			out.append("  <script src=\"assets/vendor/bootstrap/js/bootstrap.bundle.min.js\"></script>");
			out.append("  <script src=\"assets/vendor/chart.js/chart.umd.js\"></script>");
			out.append("  <script src=\"assets/vendor/echarts/echarts.min.js\"></script>");
			out.append("  <script src=\"assets/vendor/quill/quill.js\"></script>");
			out.append("  <script src=\"assets/vendor/simple-datatables/simple-datatables.js\"></script>");
			out.append("  <script src=\"assets/vendor/tinymce/tinymce.min.js\"></script>");
			out.append("  <script src=\"assets/vendor/php-email-form/validate.js\"></script>");
			out.append("  <!-- Template Main JS File -->");
			out.append("  <script src=\"assets/js/main.js\"></script>");
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

package ads.to;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

import ads.JDBCconfig;
import ads.objects.Customer;
import ads.user.CustomerFunctionImpl;
import ads.user.TourUnitFunctionImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/to/Customer-delete")
public class CustomerDeleteServlet extends HttpServlet{

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
		 String id  = request.getParameter("c_id");
		 CustomerFunctionImpl dao = CustomerFunctionImpl.getInstance();
		
		 
		 boolean updated = dao.deleteById(id);

		  if (updated) {
		        response.sendRedirect("Customer?message=Deleted Successfully!");
		   } else {
		        response.sendRedirect("Customer?error=Deleted Failed!");
		    }
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
		
		
		
		
		doGet(req, resp);
	}
	
	

}

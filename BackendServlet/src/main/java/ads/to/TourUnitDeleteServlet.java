package ads.to;

import java.io.IOException;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import ads.objects.TourUnit;
import ads.user.TourUnitFunctionImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/to/tour/Tour-Unit-Delete")
public class TourUnitDeleteServlet extends HttpServlet{

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
		 RequestDispatcher h = request.getRequestDispatcher("/he");
		 RequestDispatcher side = request.getRequestDispatcher("/side");
		 RequestDispatcher foot = request.getRequestDispatcher("/fo");
		 out.append("<!DOCTYPE html>");
			out.append("<html lang=\"en\">");
			out.append("");
			out.append("<head>");
			out.append("  <meta charset=\"utf-8\">");
			out.append("  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">");
			out.append("");
			out.append("  <title>Xoá đơn vị tour</title>");
			out.append("  <meta content=\"\" name=\"description\">");
			out.append("  <meta content=\"\" name=\"keywords\">");
			out.append("");
			out.append("  <!-- Favicons -->");
			out.append("  <link href=\"../assets/img/Logo.svg\" rel=\"icon\"> ");
			out.append("  <link href=\"../assets/img/apple-touch-icon.png\" rel=\"apple-touch-icon\">");
			out.append("");
			out.append("  <!-- Google Fonts -->");
			out.append("  <link href=\"https://fonts.gstatic.com\" rel=\"preconnect\">");
			out.append("  <link href='https://fonts.googleapis.com/css?family=Be Vietnam Pro' rel='stylesheet'>");
			out.append(
					"  <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i&subset=vietnamese\" rel=\"stylesheet\">");
			out.append("");
			out.append("  <!-- Vendor CSS Files -->");
			out.append("  <link href=\"../../assets/vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");
			out.append("  <link href=\"../../assets/vendor/bootstrap-icons/bootstrap-icons.css\" rel=\"stylesheet\">");
			out.append("  <link href=\"../../assets/vendor/boxicons/css/boxicons.min.css\" rel=\"stylesheet\">");
			out.append("  <link href=\"../../assets/vendor/quill/quill.snow.css\" rel=\"stylesheet\">");
			out.append("  <link href=\"../../assets/vendor/quill/quill.bubble.css\" rel=\"stylesheet\">");
			out.append("  <link href=\"../../assets/vendor/remixicon/remixicon.css\" rel=\"stylesheet\">");
			out.append("  <link href=\"../../assets/vendor/simple-datatables/style.css\" rel=\"stylesheet\">");
			out.append("");
			out.append("");
			out.append("  <!-- Template Main CSS File -->");
			out.append("  <link href=\"../../css/style.css\" rel=\"stylesheet\">");
		 h.include(request, response);
		 side.include(request, response);
		 String id  = request.getParameter("tourUnitId");
		 String tour_id = TourUnitFunctionImpl.getInstance().getById(id).getTour().getTourId();
		 TourUnitFunctionImpl dao = TourUnitFunctionImpl.getInstance();
		 foot.include(request, response);
		 
		 boolean updated = dao.deleteById(id);

		  if (updated) {
		        response.sendRedirect("Tour-Unit?tour_id="+tour_id+"&recordArea=1&message=Deleted Successfully!");
		   } else {
		        response.sendRedirect("Tour-Unit?tour_id="+tour_id+"&recordArea=1&error=Deleted Failed!");
		    }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		 
	}
	
	

}

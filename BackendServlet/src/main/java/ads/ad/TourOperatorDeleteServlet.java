package ads.ad;

import java.io.IOException;

import ads.user.TourOperatorFunction;
import ads.user.TourOperatorFunctionImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ad/touroperator-management/delete")
public class TourOperatorDeleteServlet extends HttpServlet {
	private TourOperatorFunction tourOperatorFunction = new TourOperatorFunctionImpl();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            resp.sendRedirect(req.getContextPath() + "/ad/login");
            return;
        }

        int tourOperatorId = Integer.parseInt(req.getParameter("tourOperatorId"));

        boolean isDeleted = tourOperatorFunction.deleteTourOperatorAndAccount(tourOperatorId);
        
        if (isDeleted) {
            resp.sendRedirect(req.getContextPath() + "/ad/touroperator-management?success=delete");
        }
        else {
            resp.sendRedirect(req.getContextPath() + "/ad/touroperator-management?error=delete");
        }
    }
}

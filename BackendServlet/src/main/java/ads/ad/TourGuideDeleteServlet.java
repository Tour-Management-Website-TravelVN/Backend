package ads.ad;

import java.io.IOException;

import ads.user.TourGuideFunction;
import ads.user.TourGuideFunctionImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ad-tourguide-management/delete")
public class TourGuideDeleteServlet extends HttpServlet {
	private TourGuideFunction tourGuideFunction = new TourGuideFunctionImpl();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            resp.sendRedirect(req.getContextPath() + "/ad-login");
            return;
        }

        int tourGuideId = Integer.parseInt(req.getParameter("tourGuideId"));

        boolean isDeleted = tourGuideFunction.deleteTourGuideAndAccount(tourGuideId);
        
        if (isDeleted) {
            resp.sendRedirect(req.getContextPath() + "/ad-tourguide-management?success=delete");
        }
        else {
            resp.sendRedirect(req.getContextPath() + "/ad-tourguide-management?error=delete");
        }
    }
}

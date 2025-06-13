package ads.ad;

import java.io.IOException;

import ads.user.TourRatingFunction;
import ads.user.TourRatingFunctionImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ad-tourrating-management/set-status")
public class TourRatingSetStatusServlet extends HttpServlet {
	private TourRatingFunction tourRatingFunction = new TourRatingFunctionImpl();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int tourRatingId = Integer.parseInt(request.getParameter("tourRatingId"));
            int administratorId = Integer.parseInt(request.getParameter("administratorId"));
            String action = request.getParameter("action");
            
            boolean isSuccess = false;
            if ("approved".equals(action)) {
                isSuccess = tourRatingFunction.setStatus(tourRatingId, administratorId, "Duyệt");
            }
            else if ("rejected".equals(action)) {
            	isSuccess = tourRatingFunction.setStatus(tourRatingId, administratorId, "Từ chối duyệt");
            }
            
            if(isSuccess) {
            	response.sendRedirect(request.getContextPath() + "/ad-tourrating-management?success=set-status");
            }
            else {
            	response.sendRedirect(request.getContextPath() + "/ad-tourrating-management?error=set-status");
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Có lỗi xảy ra khi cập nhật trạng thái");
            response.sendRedirect(request.getContextPath() + "/ad-tourrating-management?error=set-status");
        }
    }
}

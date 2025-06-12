package ads.ad;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ad-logout")
public class LogoutServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xóa session nếu có
        HttpSession session = request.getSession(false); // không tạo mới session
        if (session != null) {
            session.invalidate();
        }

        // Chuyển hướng về trang đăng nhập
        response.sendRedirect(request.getContextPath() + "/ad-login");
    }
}

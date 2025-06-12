package ads.ad;

import java.io.IOException;

import ads.objects.Administrator;
import ads.user.AdministratorFunction;
import ads.user.AdministratorFunctionImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ad-profile-management/change-password")
public class ProfileChangePasswordServlet extends HttpServlet {
	private AdministratorFunction administratorFunction = new AdministratorFunctionImpl();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("username") == null) {
			resp.sendRedirect(req.getContextPath() + "/ad-login");
			return;
		}
		
		String username = (String) session.getAttribute("username");

        String currentPassword = req.getParameter("currentPassword");
        String newPassword = req.getParameter("newPassword");
        String renewPassword = req.getParameter("renewPassword");

        Administrator admin = administratorFunction.getAdministratorByUsername(username);

        if (currentPassword == null || newPassword == null || renewPassword == null ||
            currentPassword.isEmpty() || newPassword.isEmpty() || renewPassword.isEmpty()) {
            req.setAttribute("error", "Vui lòng điền đầy đủ thông tin.");
            req.getRequestDispatcher("/ad-profile-management").forward(req, resp);
            return;
        }

        if (!newPassword.equals(renewPassword)) {
            req.setAttribute("error", "Mật khẩu mới và xác nhận không khớp.");
            req.getRequestDispatcher("/ad-profile-management").forward(req, resp);
            return;
        }

        // Kiểm tra mật khẩu hiện tại
        boolean match = currentPassword.equals(admin.getUserAccount().getPassword());

        if (!match) {
            req.setAttribute("error", "Mật khẩu hiện tại không đúng.");
            req.getRequestDispatcher("/ad-profile-management").forward(req, resp);
            return;
        }

        boolean isChange = administratorFunction.changePassword(admin.getUserAccount().getUsername(), newPassword);

        if (isChange) {
            session.invalidate();
            resp.sendRedirect(req.getContextPath() + "/ad-login?message=password-changed");
            return;
        } else {
            req.setAttribute("error", "Đổi mật khẩu thất bại. Vui lòng thử lại.");
            resp.sendRedirect(req.getContextPath() + "/ad-profile-management?error=update");
            return;
        }
    }
}

package ads.ad;

import java.io.IOException;

import ads.user.UserAccountFunction;
import ads.user.UserAccountFunctionImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ad-account-management-recent-delete/restore")
public class AccountRestoreServlet extends HttpServlet {
	private UserAccountFunction userAccountFunction = new UserAccountFunctionImpl();

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
        
        String restore = req.getParameter("restore");
        if ("all".equals(restore)) {
            boolean isRestoredAll = userAccountFunction.restoreAllAccounts();
            
            if (isRestoredAll) {
                resp.sendRedirect(req.getContextPath() + "/ad-account-management-recent-delete?success=restore_all");
            } else {
                resp.sendRedirect(req.getContextPath() + "/ad-account-management-recent-delete?error=restore_all");
            }
            return;
        }

        String username = req.getParameter("username");
        
        if (username == null || username.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Username is required");
            return;
        }

        boolean isRestored = userAccountFunction.restoreAccount(username);
        
        if (isRestored) {
            resp.sendRedirect(req.getContextPath() + "/ad-account-management-recent-delete?success=restore");
        }
        else {
            resp.sendRedirect(req.getContextPath() + "/ad-account-management-recent-delete?error=restore");
        }
    }
}

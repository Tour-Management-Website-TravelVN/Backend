package ads.ad;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import ads.user.UserAccountFunction;
import ads.user.UserAccountFunctionImpl;

@WebServlet("/ad-account-management-recent-delete/delete")
public class AccountDeleteServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserAccountFunction userAccountFunction = new UserAccountFunctionImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            resp.sendRedirect(req.getContextPath() + "/ad-login");
            return;
        }
        
        String delete = req.getParameter("delete");
        if ("all".equals(delete)) {
            boolean isDeletedAll = userAccountFunction.deleteAllSoftDeleteAccounts();
            
            if (isDeletedAll) {
                resp.sendRedirect(req.getContextPath() + "/ad-account-management-recent-delete?success=delete_all");
            } else {
                resp.sendRedirect(req.getContextPath() + "/ad-account-management-recent-delete?error=delete_all");
            }
            return;
        }

        String username = req.getParameter("username");
        
        if (username == null || username.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Username is required");
            return;
        }

        boolean isDeleted = userAccountFunction.deleteUserAccount(username);
        
        if (isDeleted) {
            resp.sendRedirect(req.getContextPath() + "/ad-account-management-recent-delete?success=delete");
        }
        else {
            resp.sendRedirect(req.getContextPath() + "/ad-account-management-recent-delete?error=delete");
        }
    }
}
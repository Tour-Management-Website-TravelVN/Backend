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

@WebServlet("/ad/account-management-recent-delete/restore")
public class AccountRestoreServlet extends HttpServlet {
    private UserAccountFunction userAccountFunction = new UserAccountFunctionImpl();
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            resp.sendRedirect(req.getContextPath() + "/ad/login");
            return;
        }

        // Trường hợp khôi phục nhiều tài khoản đã chọn
        String[] usernames = req.getParameterValues("usernames");
        if (usernames != null && usernames.length > 0) {
            boolean allSuccess = true;
            for (String username : usernames) {
                boolean restored = userAccountFunction.restoreAccount(username);
                if (!restored) {
                    allSuccess = false;
                    break; // Nếu một cái lỗi, có thể break hoặc continue tùy yêu cầu
                }
            }
            if (allSuccess) {
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Không thể khôi phục một số tài khoản");
            }
            return;
        }

        
        // Khôi phục 1
        String username = req.getParameter("username");
        if (username == null || username.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Username is required");
            return;
        }
        boolean isRestored = userAccountFunction.restoreAccount(username);
        if (isRestored) {
            resp.sendRedirect(req.getContextPath() + "/ad/account-management-recent-delete?success=restore");
        } else {
            resp.sendRedirect(req.getContextPath() + "/ad/account-management-recent-delete?error=restore");
        }
    }

}

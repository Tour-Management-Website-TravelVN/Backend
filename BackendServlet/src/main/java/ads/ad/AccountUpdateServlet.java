package ads.ad;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ads.user.UserAccountFunction;
import ads.user.UserAccountFunctionImpl;

@WebServlet("/ad-account-management/update")
public class AccountUpdateServlet extends HttpServlet {
    private UserAccountFunction userAccountFunction = new UserAccountFunctionImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        
        boolean isUpdated = userAccountFunction.updateUserAccount(username, password, email);
        
        if (isUpdated) {
            resp.sendRedirect(req.getContextPath() + "/ad-account-management?success=update");
        }
        else {
            resp.sendRedirect(req.getContextPath() + "/ad-account-management?error=update");
        }
    }
}
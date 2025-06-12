package ads.ad;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ads.user.UserAccountFunction;
import ads.user.UserAccountFunctionImpl;

@WebServlet("/ad-account-management/lock")
public class AccountLockServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserAccountFunction userAccountFunction = new UserAccountFunctionImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        if (request.getSession(false) == null || request.getSession().getAttribute("username") == null) {
            response.sendRedirect(request.getContextPath() + "/ad-login");
            return;
        }

        String username = request.getParameter("username");
        
        try {
            boolean success = userAccountFunction.toggleAccountStatus(username);
            
            if (success) {
                response.sendRedirect(request.getContextPath() + "/ad-account-management?success=Khóa/mở khóa tài khoản thành công");
            } else {
                response.sendRedirect(request.getContextPath() + "/ad-account-management?error=Không thể khóa/mở khóa tài khoản");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/ad-account-management?error=Lỗi hệ thống");
        }
    }
}
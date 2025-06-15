//package ads.to;
//
//import java.io.IOException;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import ads.objects.Administrator;
//import ads.objects.TourOperator;
//import ads.user.AdministratorFunction;
//import ads.user.AdministratorFunctionImpl;
//import ads.user.UserAccountFunctionImpl;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//@WebServlet("/to/change-password")
//public class ProfileChangePasswordServlet extends HttpServlet {
//	private AdministratorFunction administratorFunction = new AdministratorFunctionImpl();
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	
//	
//	@Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//
//      
//
//        String currentPassword = req.getParameter("currentPassword");
//        String newPassword = req.getParameter("newPassword");
//        String renewPassword = req.getParameter("renewPassword");
//
//        String id= req.getParameter("id");
//
//        PasswordEncoder p = new BCryptPasswordEncoder(10);
//        if (!newPassword.equals(renewPassword)) {
//            req.setAttribute("error", "Mật khẩu mới và xác nhận không khớp.");
//            req.getRequestDispatcher("/ad-profile-management").forward(req, resp);
//            return;
//        }
//        
//        // Kiểm tra mật khẩu hiện tại
//       boolean match = currentPassword.equals(UserAccountFunctionImpl.getInstance().getTOPassById(Integer.parseInt(id)));
//
//        if (!match) {
//            req.setAttribute("error", "Mật khẩu hiện tại không đúng.");
//            req.getRequestDispatcher("to/to-profile-management").forward(req, resp);
//            return;
//        }
//
//        boolean isChange = UserAccountFunctionImpl.getInstance().toPassChange(p.encode(renewPassword), Integer.parseInt(id));
//
//        if (isChange) {
//            resp.sendRedirect(req.getContextPath() + "/to-profile-management?message=password-changed");
//            return;
//        } else {
//            resp.sendRedirect(req.getContextPath() + "/to-profile-management?error=update");
//            return;
//        }
//    }
//}

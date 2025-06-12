package ads.ad;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import ads.objects.TourGuide;
import ads.user.AdministratorFunction;
import ads.user.AdministratorFunctionImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ad-profile-management/update")
public class ProfileUpdateServlet extends HttpServlet {
	private AdministratorFunction administratorFunction = new AdministratorFunctionImpl();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = req.getParameter("username");
            String email = req.getParameter("email");
            
//            // Kiểm tra số điện thoại đã tồn tại chưa
//            if (tourGuideFunction.isPhoneNumberExist(phoneNumber)) {
//                resp.sendRedirect(req.getContextPath() + "/ad-touroperator-management?error=phone_exist");
//                return;
//            }
//            
//            // Kiểm tra citizen_id đã tồn tại chưa
//            if (tourGuideFunction.isCitizenIdExist(citizenId)) {
//            	resp.sendRedirect(req.getContextPath() + "/ad-touroperator-management?error=citizen_id_exist");
//                return;
//            }
//            
//            // Kiểm tra card_id đã tồn tại chưa
//            if (tourGuideFunction.isCardIdExist(cardId)) {
//            	resp.sendRedirect(req.getContextPath() + "/ad-touroperator-management?error=card_id_exist");
//                return;
//            }

            boolean isSuccess = administratorFunction.changeEmail(email, username);

            if(isSuccess) {
                req.getSession().setAttribute("message", "Cập nhật thông tin thành công!");
                req.getSession().setAttribute("messageType", "success");
                resp.sendRedirect(req.getContextPath() + "/ad-profile-management?success=update");
            }
            else {
                req.getSession().setAttribute("message", "Cập nhật thông tin thất bại!");
                req.getSession().setAttribute("messageType", "error");
                resp.sendRedirect(req.getContextPath() + "/ad-profile-management?error=update");
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.getSession().setAttribute("message", "Lỗi hệ thống: " + e.getMessage());
            req.getSession().setAttribute("messageType", "error");
            resp.sendRedirect(req.getContextPath() + "/ad-profile-management");
        }
    }

}

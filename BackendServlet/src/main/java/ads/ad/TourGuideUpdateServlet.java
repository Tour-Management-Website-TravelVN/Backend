package ads.ad;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import ads.objects.TourGuide;
import ads.objects.TourOperator;
import ads.user.TourGuideFunction;
import ads.user.TourGuideFunctionImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ad/tourguide-management/update")
public class TourGuideUpdateServlet extends HttpServlet {
	private TourGuideFunction tourGuideFunction = new TourGuideFunctionImpl();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            LocalDate dob = LocalDate.parse(req.getParameter("dob"));
            boolean gender = req.getParameter("gender").equals("male") ? true : false;
            String phoneNumber = req.getParameter("phoneNumber");
            String citizenId = req.getParameter("citizenId");
            String hometown = req.getParameter("hometown");
            String address = req.getParameter("address");
            LocalDate startDate = LocalDate.parse(req.getParameter("startDate"));
            LocalDate endDate = LocalDate.parse(req.getParameter("endDate"));
            BigDecimal salary = new BigDecimal(req.getParameter("salary"));
            String cardId = req.getParameter("cardId");
            String language = req.getParameter("language");
            
//            // Kiểm tra số điện thoại đã tồn tại chưa
//            if (tourGuideFunction.isPhoneNumberExist(phoneNumber)) {
//                resp.sendRedirect(req.getContextPath() + "/ad/touroperator-management?error=phone_exist");
//                return;
//            }
//            
//            // Kiểm tra citizen_id đã tồn tại chưa
//            if (tourGuideFunction.isCitizenIdExist(citizenId)) {
//            	resp.sendRedirect(req.getContextPath() + "/ad/touroperator-management?error=citizen_id_exist");
//                return;
//            }
//            
//            // Kiểm tra card_id đã tồn tại chưa
//            if (tourGuideFunction.isCardIdExist(cardId)) {
//            	resp.sendRedirect(req.getContextPath() + "/ad/touroperator-management?error=card_id_exist");
//                return;
//            }

            TourGuide tourGuide = new TourGuide();
            tourGuide.setId(id);
            tourGuide.setFirstname(firstName);
            tourGuide.setLastname(lastName);
            tourGuide.setDateOfBirth(dob);
            tourGuide.setGender(gender);
            tourGuide.setPhoneNumber(phoneNumber);
            tourGuide.setCitizenId(citizenId);
            tourGuide.setHometown(hometown);
            tourGuide.setAddress(address);
            tourGuide.setStartDate(startDate);
            tourGuide.setEndDate(endDate);
            tourGuide.setSalary(salary);
            tourGuide.setCardId(cardId);
            tourGuide.setLanguage(language);

            boolean success = tourGuideFunction.updateTourGuide(tourGuide);

            if(success) {
                req.getSession().setAttribute("message", "Cập nhật thông tin thành công!");
                req.getSession().setAttribute("messageType", "success");
                resp.sendRedirect(req.getContextPath() + "/ad/tourguide-management?success=update");
            }
            else {
                req.getSession().setAttribute("message", "Cập nhật thông tin thất bại!");
                req.getSession().setAttribute("messageType", "error");
                resp.sendRedirect(req.getContextPath() + "/ad/tourguide-management?error=update");
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.getSession().setAttribute("message", "Lỗi hệ thống: " + e.getMessage());
            req.getSession().setAttribute("messageType", "error");
            resp.sendRedirect(req.getContextPath() + "/ad/tourguide-management");
        }
    }
}

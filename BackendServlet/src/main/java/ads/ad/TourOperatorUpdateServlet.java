package ads.ad;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import ads.objects.TourOperator;
import ads.user.TourOperatorFunction;
import ads.user.TourOperatorFunctionImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ad/touroperator-management/update")
public class TourOperatorUpdateServlet extends HttpServlet {
	private TourOperatorFunction tourOperatorFunction = new TourOperatorFunctionImpl();

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
            
//            // Kiểm tra số điện thoại đã tồn tại chưa
//            if (tourOperatorFunction.isPhoneNumberExist(phoneNumber)) {
//                resp.sendRedirect(req.getContextPath() + "/ad/touroperator-management?error=phone_exist");
//                return;
//            }
//            
//            // Kiểm tra citizen_id đã tồn tại chưa
//            if (tourOperatorFunction.isCitizenIdExist(citizenId)) {
//            	resp.sendRedirect(req.getContextPath() + "/ad/touroperator-management?error=citizen_id_exist");
//                return;
//            }

            TourOperator updatedOperator = new TourOperator();
            updatedOperator.setId(id);
            updatedOperator.setFirstname(firstName);
            updatedOperator.setLastname(lastName);
            updatedOperator.setDateOfBirth(dob);
            updatedOperator.setGender(gender);
            updatedOperator.setPhoneNumber(phoneNumber);
            updatedOperator.setCitizenId(citizenId);
            updatedOperator.setHometown(hometown);
            updatedOperator.setAddress(address);
            updatedOperator.setStartDate(startDate);
            updatedOperator.setEndDate(endDate);
            updatedOperator.setSalary(salary);

            boolean success = tourOperatorFunction.updateTourOperator(updatedOperator);

            if(success) {
                req.getSession().setAttribute("message", "Cập nhật thông tin thành công!");
                req.getSession().setAttribute("messageType", "success");
                resp.sendRedirect(req.getContextPath() + "/ad/touroperator-management?success=update");
            }
            else {
                req.getSession().setAttribute("message", "Cập nhật thông tin thất bại!");
                req.getSession().setAttribute("messageType", "error");
                resp.sendRedirect(req.getContextPath() + "/ad/touroperator-management?error=update");
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.getSession().setAttribute("message", "Lỗi hệ thống: " + e.getMessage());
            req.getSession().setAttribute("messageType", "error");
            resp.sendRedirect(req.getContextPath() + "/ad/touroperator-management");
        }
    }
}

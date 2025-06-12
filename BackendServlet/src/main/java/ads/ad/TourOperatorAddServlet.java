package ads.ad;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ads.objects.TourOperator;
import ads.user.TourOperatorFunction;
import ads.user.TourOperatorFunctionImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ad-touroperator-management/add")
public class TourOperatorAddServlet extends HttpServlet {
    private TourOperatorFunction tourOperatorFunction = TourOperatorFunctionImpl.getInstance();
    
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        try {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String dob = request.getParameter("dob");
            String gender = request.getParameter("gender");
            String phoneNumber = request.getParameter("phoneNumber");
            String citizenId = request.getParameter("citizenId");
            String hometown = request.getParameter("hometown");
            String address = request.getParameter("address");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String salary = request.getParameter("salary");
            
            // Kiểm tra số điện thoại đã tồn tại chưa
            if (tourOperatorFunction.isPhoneNumberExist(phoneNumber)) {
                response.sendRedirect(request.getContextPath() + "/ad-touroperator-management?error=phone_exist");
                return;
            }
            
            // Kiểm tra citizen_id đã tồn tại
            if (tourOperatorFunction.isCitizenIdExist(citizenId)) {
                response.sendRedirect(request.getContextPath() + "/ad-touroperator-management?error=citizen_id_exist");
                return;
            }
            
            LocalDate dateOfBirth = null;
            if (dob != null && !dob.isEmpty()) {
                dateOfBirth = LocalDate.parse(dob, DateTimeFormatter.ISO_LOCAL_DATE);
            }
            
            boolean isMale = "Male".equals(gender);
            
            BigDecimal salaryValue = null;
            if (salary != null && !salary.isEmpty()) {
                try {
                    salaryValue = new BigDecimal(salary);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Lương không hợp lệ");
                }
            }
            
            LocalDate startDateValue = null;
            if (startDate != null && !startDate.isEmpty()) {
                startDateValue = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
            }
            
            LocalDate endDateValue = null;
            if (endDate != null && !endDate.isEmpty()) {
                endDateValue = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);
            }
            
            TourOperator tourOperator = new TourOperator();
            tourOperator.setFirstname(firstName);
            tourOperator.setLastname(lastName);
            tourOperator.setDateOfBirth(dateOfBirth);
            tourOperator.setGender(isMale);
            tourOperator.setAddress(address);
            tourOperator.setPhoneNumber(phoneNumber);
            tourOperator.setCitizenId(citizenId);
            tourOperator.setHometown(hometown);
            tourOperator.setSalary(salaryValue);
            tourOperator.setStartDate(startDateValue);
            tourOperator.setEndDate(endDateValue);
            
            boolean isSuccess = tourOperatorFunction.addTourOperator(tourOperator);
            
            if (isSuccess) {
                response.sendRedirect(request.getContextPath() + "/ad-touroperator-management?success=add");
            } else {
                response.sendRedirect(request.getContextPath() + "/ad-touroperator-management?error=add");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message", "Có lỗi xảy ra: " + e.getMessage());
            session.setAttribute("messageType", "error");
            response.sendRedirect(request.getContextPath() + "/ad-touroperator-management?error=add");
        }
    }
}
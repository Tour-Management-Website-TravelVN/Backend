package ads.ad;

import java.io.IOException;
import java.io.PrintWriter;

import ads.objects.Administrator;
import ads.objects.Customer;
import ads.objects.TourGuide;
import ads.objects.TourOperator;
import ads.objects.UserAccount;
import ads.user.UserAccountFunction;
import ads.user.UserAccountFunctionImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ad/account-management/add")
public class AccountAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserAccountFunction userAccountFunction = new UserAccountFunctionImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	HttpSession session = request.getSession();
        
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String role = request.getParameter("role");
            
            if(userAccountFunction.isUsernameExist(username)) {
            	session.setAttribute("errorMessage", "Thêm tài khoản thất bại! Tên đăng nhập đã tồn tại!");
                response.sendRedirect(request.getContextPath() + "/ad/account-management?error=add_username_exist");
            }
            
            if(userAccountFunction.isEmailExist(email)) {
            	session.setAttribute("errorMessage", "Thêm tài khoản thất bại! Email đã tồn tại!");
                response.sendRedirect(request.getContextPath() + "/ad/account-management?error=add_email_exist");
            }

            UserAccount newAccount = new UserAccount();
            newAccount.setUsername(username);
            newAccount.setPassword(password);
            newAccount.setEmail(email);
            newAccount.setStatus("OFF"); // Mặc định

            switch(role) {
                case "customer":
                	if(userAccountFunction.isCustomerIdExist(id)) {
                    	session.setAttribute("errorMessage", "Thêm tài khoản thất bại! ID đã tồn tại!");
                        response.sendRedirect(request.getContextPath() + "/ad/account-management?error=add_id_exist");
                    }
                    Customer customer = new Customer();
                    customer.setId(id);
                    newAccount.setC(customer);
                    break;
                case "administrator":
                	if(userAccountFunction.isAdministratorIdExist(id)) {
                    	session.setAttribute("errorMessage", "Thêm tài khoản thất bại! ID đã tồn tại!");
                        response.sendRedirect(request.getContextPath() + "/ad/account-management?error=add_id_exist");
                    }
                    Administrator administrator = new Administrator();
                    administrator.setId(id);
                    newAccount.setAdministrator(administrator);
                    break;
                case "tourGuide":
                	if(userAccountFunction.isTourGuideIdExist(id)) {
                    	session.setAttribute("errorMessage", "Thêm tài khoản thất bại! ID đã tồn tại!");
                        response.sendRedirect(request.getContextPath() + "/ad/account-management?error=add_id_exist");
                    }
                    TourGuide tourGuide = new TourGuide();
                    tourGuide.setId(id);
                    newAccount.setTourGuide(tourGuide);
                    break;
                case "tourOperator":
                	if(userAccountFunction.isTourOperatorIdExist(id)) {
                    	session.setAttribute("errorMessage", "Thêm tài khoản thất bại! ID đã tồn tại!");
                        response.sendRedirect(request.getContextPath() + "/ad/account-management?error=add_id_exist");
                    }
                    TourOperator tourOperator = new TourOperator();
                    tourOperator.setId(id);
                    newAccount.setTourOperator(tourOperator);
                    break;
                default:
                    request.getSession().setAttribute("errorMessage", "Invalid role");
                    response.sendRedirect(request.getContextPath() + "/ad/account-management");
                    return;
            }

            boolean isSuccess = userAccountFunction.addUserAccount(newAccount);

            if(isSuccess) {
                session.setAttribute("successMessage", "Thêm tài khoản thành công!");
                response.sendRedirect(request.getContextPath() + "/ad/account-management?success=add");
            }
            else {
                session.setAttribute("errorMessage", "Thêm tài khoản thất bại!");
                response.sendRedirect(request.getContextPath() + "/ad/account-management?error=add");
            }

        } catch (Exception e) {
        	e.printStackTrace();
            session.setAttribute("errorMessage", "Lỗi server: " + e.getMessage());
        }
    }
}
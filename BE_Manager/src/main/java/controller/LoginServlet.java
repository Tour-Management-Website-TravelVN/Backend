package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Ví dụ giả lập user/pass
    private final String USERNAME = "admin";
    private final String PASSWORD = "123456";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy username, password từ form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Kiểm tra đăng nhập
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            // Đăng nhập thành công, tạo session lưu user
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            // Redirect tới trang chính (dashboard hoặc home)
            response.sendRedirect("home.jsp");
        } else {
            // Đăng nhập thất bại, gửi thông báo lỗi về login.jsp
            request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
        }
    }

    // Cho phép truy cập form login qua GET (chuyển tiếp tới login.jsp)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
    }
}

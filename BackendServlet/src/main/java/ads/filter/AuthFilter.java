package ads.filter;

import java.io.IOException;

import ads.objects.UserAccount;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//Lọc kiểm tra đăng nhập
public class AuthFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		//Kiểm tra session đã đăng nhập chưa.
		HttpSession session = req.getSession(false); //Không tạo session mới
		if(session!=null && session.getAttribute("userLogined")!=null) {
			// Cho qua để hiển thị giao diện
			chain.doFilter(request, response);
		} else {
			// Quay trở lại giao diện
			res.sendRedirect("adv/Login");
		}
		
		chain.doFilter(request, response);
		// TODO Auto-generated method stub
	}

}

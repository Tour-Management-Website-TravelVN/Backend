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

public class AdministratorFilter implements Filter{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		//Đảm bảo đã đi qua filter auth thì xác nhận vào trang này
		HttpSession session = req.getSession(false);
		UserAccount login = (UserAccount) session.getAttribute("userLogined");
		
		if(login.getAdministrator() == null) {
			res.sendRedirect("../to/tour");
		} else {
			chain.doFilter(request, response);
		}
		
		// TODO Auto-generated method stub
		
	}
	
}

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

//Người điều hành tour mới được phép vào trang của người điều hành tour
public class TourOperatorFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		//Đảm bảo đã đi qua filter auth thì xác nhận vào trang này
		HttpSession session = req.getSession(false);
		UserAccount login = (UserAccount) session.getAttribute("userLogined");
		
		if(login.getTourOperator() == null) {
			if(login.getAdministrator() == null) {
				res.sendRedirect("../Login?err=unauth");
			} else {
				res.sendRedirect("../ad/userview");
			}
		} else {
			chain.doFilter(request, response);
		}
//		else {
////			res.sendRedirect("../to/tour");
//			chain.doFilter(request, response);
//		}
		
		// TODO Auto-generated method stub
		
	}

}

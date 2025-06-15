package ads.filter;

import java.io.File;
import java.util.EnumSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import ads.ConnectionPoolImpl;
import ads.util.SchedulerUtil;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class FilterInitializer implements ServletContextListener{
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		
		//1. Đăng ký AuthFilter chạy trước
		FilterRegistration.Dynamic authFilter = context.addFilter("AuthFilter", new AuthFilter());
		authFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/to/*", "/ad/*");
		
		//2. Đăng ký TourOperatorFilter chạy
		FilterRegistration.Dynamic toFilter = context.addFilter("TOFilter", new TourOperatorFilter());
		toFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/to/*");

		//3. Đăng ký AdministratorFilter chạy
		FilterRegistration.Dynamic adFilter = context.addFilter("ADFilter", new AdministratorFilter());
		adFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/ad/*");
	
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ConnectionPoolImpl.getInstance().close();
		//com.mysql.cj.jdbc.AbandonedConnectionCleanupThread.checkedShutdown();
	}
	
}

package ads.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import ads.objects.BookingAggregate;
import ads.user.StatisticFunction;
import ads.user.StatisticFunctionImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@WebListener
public class SchedulerUtil implements Runnable, ServletContextListener {

	private ScheduledExecutorService scheduler;
	
	private static String stackedBarChartDataFile /* = "data/StackedBarChartData.dat" */;

//	private static volatile SchedulerUtil instance;
//	
//	public static SchedulerUtil getInstance() {
//		if(instance == null) {
//			synchronized (SchedulerUtil.class) {
//				if(instance==null) {
//					instance = new SchedulerUtil();
//				}
//			}
//		}
//		return instance;
//	}

	private void saveStackedBarChartData() {
		List<BookingAggregate> data = StatisticFunctionImpl.getInstance().getStackedBarChartData();
		try {
			// Tạo thư mục nếu chưa tồn tại
			File file = new File(stackedBarChartDataFile);
			File parent = file.getParentFile();
			if (parent != null && !parent.exists()) {
				parent.mkdirs(); // tạo thư mục "data"
			}

			System.out.println("Saving to: " + file.getAbsolutePath());

			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
				oos.writeObject(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<BookingAggregate> getStackedBarChartData() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(stackedBarChartDataFile))) {
			List<BookingAggregate> data = (List<BookingAggregate>) ois.readObject();
			return data;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		saveStackedBarChartData();
		System.out.println("RUNNING");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext context = sce.getServletContext();
		
		String stackedBarChartDataRealPath = context.getRealPath("/data/StackedBarChartData.dat");

		scheduler = Executors.newSingleThreadScheduledExecutor();

		//SchedulerUtil task = SchedulerUtil.getInstance();
		SchedulerUtil.stackedBarChartDataFile = stackedBarChartDataRealPath;
		//task.setStackedBarChartDataFile(stackedBarChartDataRealPath);
		
		// Chạy lần đầu ngay lập tức, sau đó mỗi 10 phút
		scheduler.scheduleAtFixedRate(new SchedulerUtil(), // task cần chạy
				0, // delay ban đầu (0 giây = chạy ngay)
				10, // lặp lại sau mỗi 10 phút
				TimeUnit.MINUTES);

		System.out.println("Lịch trình đã được khởi động.");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdownNow();
        }
        System.out.println("Lịch trình đã dừng.");
	}

}

package ads.util;

import java.util.List;

import ads.objects.Tour;
import ads.objects.TourProgram;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Validate {
	
	/**
	 * Kiểm tra tên đăng nhập có hợp lệ
	 * Tên đăng nhập 8 đến 40 ký tự, chứa các ký tự chữ và số
	 * @param s Tên đăng nhập
	 * @return Tính hợp lệ
	 */
	public static boolean validateUserName(String s) {
		if(s == null) return false;
		s = s.trim();
		return s.matches("^[a-zA-Z0-9]{8,40}$");
	}
	
	/**
	 * Kiểm tra tên đăng nhập có hợp lệ
	 * Mật khẩu 6 đến 20 ký tự
	 * @param s Tên đăng nhập
	 * @return Tính hợp lệ
	 */
	public static boolean validatePwd(String s) {
		return validateLength(s, 6, 20);
	}
	
	/**
	 * Kiêm tra độ dài chuỗi
	 * @param s Chuỗi nhập
	 * @param min Độ dài nhỏ nhất
	 * @param max Độ dài lớn nhất
	 * @return Kết quả kiểm tra
	 */
	public static boolean validateLength(String s, int min, int max) {
		if(s==null) return false;
		s=s.trim();
		return s.length()>=min && s.length()<=max;
	}
	
	/**
	 * Kiêm tra độ dài chuỗi
	 * @param s Chuỗi nhập
	 * @param min Độ dài nhỏ nhất
	 * @return Kết quả kiểm tra
	 */
	public static boolean validateLength(String s, int min) {
		if(s==null) return false;
		s=s.trim();
		return s.length()>=min;
	}
	
	/**
	 * Kiểm tra thông tin tour có đúng khôgn
	 * @param tour 
	 * @return Tính hợp lệ của tour
	 */
	public static boolean validateTour(Tour tour) {
		int count = 0;
		if(!validateLength(tour.getTourName(), 15, 255)) return false;
		
		log.info("FLAG {}", count++);
		
		try {
			int duration = Integer.parseInt(tour.getDuration().split("N")[0]);
			if(duration<1 || duration >60) return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
		log.info("FLAG {}", count++);
		
		if(!validateLength(tour.getTargetAudience(), 6, 255)) return false;
		log.info("FLAG {}", count++);
		if(!validateLength(tour.getDeparturePlace(), 6, 255)) return false;
		log.info("FLAG {}", count++);
		if(!validateLength(tour.getPlacesToVisit(), 6, 255)) return false;
		log.info("FLAG {}", count++);
		if(!validateLength(tour.getCuisine(), 6, 255)) return false;
		log.info("FLAG {}", count++);
		if(!validateLength(tour.getIdealTime(), 6, 255)) return false;
		log.info("FLAG {}", count++);
		
		if(!validateLength(tour.getDescription(), 20)) return false;
		log.info("FLAG {}", count++);
		if(!validateLength(tour.getInclusions(), 20)) return false;
		log.info("FLAG {}", count++);
		if(!validateLength(tour.getExclusions(), 20)) return false;
		log.info("FLAG {}", count++);
		
		return true;
	}
	
	/**
	 * Kiểm tra thông tin chương trình tour có đúng không
	 * @param programs
	 * @return Tính hợp lệ của chương trình
	 */
	public static boolean validateTourProgramList(List<TourProgram> programs) {
		for (TourProgram tourProgram : programs) {
			if(!validateLength(tourProgram.getLocations(), 10, 255)) return false;
			if(!validateLength(tourProgram.getDesciption(), 20)) return false;
		}
		return true;
	}
}

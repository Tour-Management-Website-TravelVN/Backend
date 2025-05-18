package ads.util;

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
}

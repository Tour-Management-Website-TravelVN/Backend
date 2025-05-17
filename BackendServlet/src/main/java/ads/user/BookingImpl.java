package ads.user;

import java.util.ArrayList;

import ads.objects.BookingObject;
import ads.objects.TourUnit;

public class BookingImpl implements Booking {

	@Override
	public boolean addBooking(BookingObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editBooking(BookingObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delBooking(BookingObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<BookingObject> getBookings(BookingObject similar, int at, byte total) {
		// TODO Auto-generated method stub
		
		//Số lượng ngẫu nhiên 300-500 bản ghi
		int n = 300 + (int)(Math.random()*200);
		
		String firstname = "An Bảo Hà Huyền Nghĩa Trang Trung Bình Hoa Hồng Nhung Hưng Hùng Yến Huy";
		String lastname = "Triệu Đinh Lý Trần Lê Nguyễn Đoàn Ngô";
		String account = "ljlskjflsdflselkfwel";
		
		//Danh sach ten va ho
		String[] fNames = firstname.split(" ");
		String[] lNames = lastname.split(" ");
		
		int index1, index2;
		
		ArrayList<BookingObject> bookings = new ArrayList<BookingObject>();
		BookingObject item = null;
		for(int i=1; i<=n; i++) {
			item = new BookingObject();
			
			//Ngẫu nhiên họ tên
			index1 = (int)(Math.random()*fNames.length);
			index2 = (int)(Math.random()*lNames.length);
			
			item.setNote(fNames[index1]+" "+lNames[index2]);
			
			item.setTour_unit_id(fNames[index1]+" "+lNames[index2]+" "+Math.random()*100);
			
			bookings.add(item);
		}
		
		return bookings;
	}

	@Override
	public BookingObject getObject(int id) {
		// TODO Auto-generated method stub
		
		BookingObject bookingObject = new BookingObject();
		bookingObject.setBooking_id(1);
		
		return null;
	}

	@Override
	public BookingObject getObject(int orderCode, String status) {
		// TODO Auto-generated method stub
		BookingObject bookingObject = new BookingObject();
		bookingObject.setNote("admin21345");
		return null;
	}

	@Override
	public BookingObject getObject(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}

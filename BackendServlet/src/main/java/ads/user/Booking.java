package ads.user;

import java.util.ArrayList;

import ads.objects.BookingObject;

public interface Booking {
	boolean addBooking(BookingObject item);
	boolean editBooking(BookingObject item);
	boolean delBooking(BookingObject item);
	
	ArrayList<BookingObject> getBookings(BookingObject similar, int at, byte total);
	BookingObject getObject(int id);
	BookingObject getObject(String id);
	BookingObject getObject(int orderCode, String status);
}

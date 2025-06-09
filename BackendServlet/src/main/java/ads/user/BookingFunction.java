package ads.user;

import java.util.ArrayList;
import java.util.List;

import ads.objects.Booking;

public interface BookingFunction {
	boolean addBooking(Booking item);
	boolean editBookingCancelRequest(List<String> bookingIds,boolean isApproved);
	boolean delBooking(Booking item);
	ArrayList<Booking> getBookingsWait(int page);
	ArrayList<Booking> getBookings(int page);
	Booking getObject(int id);
	Booking getObject(String id);
	Booking getObject(int orderCode, String status);
}

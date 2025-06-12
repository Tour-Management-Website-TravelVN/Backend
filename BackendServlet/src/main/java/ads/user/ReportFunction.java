package ads.user;

import java.math.BigDecimal;
import java.util.ArrayList;

import ads.objects.Booking;

public interface ReportFunction {
	public int getDailyTourBookings();
	public int getMonthlyTourBookings();
	public int getYearlyTourBookings();
	
	public BigDecimal getDailyRevenue();
	public BigDecimal getMonthlyRevenue();
	public BigDecimal getYearlyTourRevenue();
	
	public int getDailyCustomers();
	public int getMonthlyCustomers();
	public int getYearlyCustomers();
	
	// Thời gian, Doanh thu, Chi phí, Lợi nhuận các tháng trong năm
	public ArrayList<Object> getOverViewReportOfYear();
	
	// Thời gian, Doanh thu, Chi phí, Lợi nhuận các ngày trong tháng
	public ArrayList<Object> getOverViewReportOfMonth();
	
	// TourUnitId, tourName, customerName, bookingDate, status
	public ArrayList<Booking> get50RecentBooking();
	
	// Tên Tour, Số lượng đặt, Tổng doanh thu
	public ArrayList<Object> get50TopBookingTour();
}

package ads.objects;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//import lombok.*;
//
//@Getter
//@Setter
//@Builder
public class BookingObject {
	private int booking_id;
	private short adult_number;
	private short baby_number;
	private LocalDateTime booking_date;
	private short child_number;
	private String note;
	private int payment_id;
	private char status;
	private short toddler_number;
	private BigDecimal total_amount;
	private int c_id;
	private String tour_unit_id;
	private long expired_at;
	private long order_code;
	private short private_room_number;
	
	public BookingObject() {
		
	}
	
	public int getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}
	public short getAdult_number() {
		return adult_number;
	}
	public void setAdult_number(short adult_number) {
		this.adult_number = adult_number;
	}
	public short getBaby_number() {
		return baby_number;
	}
	public void setBaby_number(short baby_number) {
		this.baby_number = baby_number;
	}
	public LocalDateTime getBooking_date() {
		return booking_date;
	}
	public void setBooking_date(LocalDateTime booking_date) {
		this.booking_date = booking_date;
	}
	public short getChild_number() {
		return child_number;
	}
	public void setChild_number(short child_number) {
		this.child_number = child_number;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public short getToddler_number() {
		return toddler_number;
	}
	public void setToddler_number(short toddler_number) {
		this.toddler_number = toddler_number;
	}
	public BigDecimal getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(BigDecimal total_amount) {
		this.total_amount = total_amount;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public String getTour_unit_id() {
		return tour_unit_id;
	}
	public void setTour_unit_id(String tour_unit_id) {
		this.tour_unit_id = tour_unit_id;
	}
	public long getExpired_at() {
		return expired_at;
	}
	public void setExpired_at(long expired_at) {
		this.expired_at = expired_at;
	}
	public long getOrder_code() {
		return order_code;
	}
	public void setOrder_code(long order_code) {
		this.order_code = order_code;
	}
	public short getPrivate_room_number() {
		return private_room_number;
	}
	public void setPrivate_room_number(short private_room_number) {
		this.private_room_number = private_room_number;
	}
	
}

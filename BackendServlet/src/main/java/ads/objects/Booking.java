package ads.objects;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Booking {
    private String bookingId;

    private Customer c;

    private TourUnit tourUnit;

    private Instant bookingDate;

    private Byte babyNumber;

    private Byte toddlerNumber;

    private Byte childNumber;

    private Byte adultNumber;

    private String status;

    private String note;

    private String paymentId;

    private BigDecimal totalAmount;

    private Long orderCode;

    /**
     * Thời điểm hệ thống nhận được yêu cầu hủy tour.
     * Trước đây dùng để lưu thời gian hết hạn thanh toán (không còn sử dụng).
     */
    private Long expiredAt;

    private Byte privateRoomNumber;

    private Set<CompanionCustomer> companionCustomerSet = new HashSet<>();

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public Customer getC() {
		return c;
	}

	public void setC(Customer c) {
		this.c = c;
	}

	public TourUnit getTourUnit() {
		return tourUnit;
	}

	public void setTourUnit(TourUnit tourUnit) {
		this.tourUnit = tourUnit;
	}

	public Instant getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Instant bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Byte getBabyNumber() {
		return babyNumber;
	}

	public void setBabyNumber(Byte babyNumber) {
		this.babyNumber = babyNumber;
	}

	public Byte getToddlerNumber() {
		return toddlerNumber;
	}

	public void setToddlerNumber(Byte toddlerNumber) {
		this.toddlerNumber = toddlerNumber;
	}

	public Byte getChildNumber() {
		return childNumber;
	}

	public void setChildNumber(Byte childNumber) {
		this.childNumber = childNumber;
	}

	public Byte getAdultNumber() {
		return adultNumber;
	}

	public void setAdultNumber(Byte adultNumber) {
		this.adultNumber = adultNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(Long orderCode) {
		this.orderCode = orderCode;
	}

	public Long getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(Long expiredAt) {
		this.expiredAt = expiredAt;
	}

	public Byte getPrivateRoomNumber() {
		return privateRoomNumber;
	}

	public void setPrivateRoomNumber(Byte privateRoomNumber) {
		this.privateRoomNumber = privateRoomNumber;
	}

	public Set<CompanionCustomer> getCompanionCustomerSet() {
		return companionCustomerSet;
	}

	public void setCompanionCustomerSet(Set<CompanionCustomer> companionCustomerSet) {
		this.companionCustomerSet = companionCustomerSet;
	}
    
    
}
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

    private TourUnitObject tourUnit;

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
}
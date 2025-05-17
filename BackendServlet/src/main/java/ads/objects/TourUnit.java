package ads.objects;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TourUnit {
    private String tourUnitId;

    private Festival festival;

    private Tour tour;

    private Discount discount;

    private TourOperator tourOperator;

    private TourOperator lastUpdatedOperator;

    private LocalDate departureDate;

    private LocalDate returnDate;

    private BigDecimal adultTourPrice;

    private BigDecimal childTourPrice;

    private BigDecimal toddlerTourPrice;

    private BigDecimal babyTourPrice;

    private BigDecimal adultTourCost;

    private BigDecimal childTourCost;

    private BigDecimal toddlerTourCost;

    private BigDecimal babyTourCost;

    private BigDecimal privateRoomPrice;

    private Instant createdTime;

    private Instant lastUpdatedTime;

    private Short maximumCapacity;

    private Short availableCapacity;

    private BigDecimal totalAdditionalCost;

    private Set<Guide> guideSet = new HashSet<>();

    private Set<Booking> bookingSet = new HashSet<>();

    private Set<TourRating> tourRatingSet = new HashSet<>();
}
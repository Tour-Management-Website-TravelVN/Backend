package com.travelvn.tourbookingsytem.dto.response;

import com.travelvn.tourbookingsytem.model.Booking;
import com.travelvn.tourbookingsytem.model.Guide;
import com.travelvn.tourbookingsytem.model.TourRating;
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

public class TourUnitResponse {

    private String tourUnitId;

    private FestivalResponse festival;

    private TourResponse tour;

    private DiscountResponse discount;

    private TourOperatorResponse tourOperator;

    private TourOperatorResponse lastUpdatedOperator;

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

//    private Set<Guide> guideSet;

//    private Set<Booking> bookingSet;

//    private Set<TourRating> tourRatingSet;
}
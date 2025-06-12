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

	public String getTourUnitId() {
		return tourUnitId;
	}

	public void setTourUnitId(String tourUnitId) {
		this.tourUnitId = tourUnitId;
	}

	public Festival getFestival() {
		return festival;
	}

	public void setFestival(Festival festival) {
		this.festival = festival;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public TourOperator getTourOperator() {
		return tourOperator;
	}

	public void setTourOperator(TourOperator tourOperator) {
		this.tourOperator = tourOperator;
	}

	public TourOperator getLastUpdatedOperator() {
		return lastUpdatedOperator;
	}

	public void setLastUpdatedOperator(TourOperator lastUpdatedOperator) {
		this.lastUpdatedOperator = lastUpdatedOperator;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public BigDecimal getAdultTourPrice() {
		return adultTourPrice;
	}

	public void setAdultTourPrice(BigDecimal adultTourPrice) {
		this.adultTourPrice = adultTourPrice;
	}

	public BigDecimal getChildTourPrice() {
		return childTourPrice;
	}

	public void setChildTourPrice(BigDecimal childTourPrice) {
		this.childTourPrice = childTourPrice;
	}

	public BigDecimal getToddlerTourPrice() {
		return toddlerTourPrice;
	}

	public void setToddlerTourPrice(BigDecimal toddlerTourPrice) {
		this.toddlerTourPrice = toddlerTourPrice;
	}

	public BigDecimal getBabyTourPrice() {
		return babyTourPrice;
	}

	public void setBabyTourPrice(BigDecimal babyTourPrice) {
		this.babyTourPrice = babyTourPrice;
	}

	public BigDecimal getAdultTourCost() {
		return adultTourCost;
	}

	public void setAdultTourCost(BigDecimal adultTourCost) {
		this.adultTourCost = adultTourCost;
	}

	public BigDecimal getChildTourCost() {
		return childTourCost;
	}

	public void setChildTourCost(BigDecimal childTourCost) {
		this.childTourCost = childTourCost;
	}

	public BigDecimal getToddlerTourCost() {
		return toddlerTourCost;
	}

	public void setToddlerTourCost(BigDecimal toddlerTourCost) {
		this.toddlerTourCost = toddlerTourCost;
	}

	public BigDecimal getBabyTourCost() {
		return babyTourCost;
	}

	public void setBabyTourCost(BigDecimal babyTourCost) {
		this.babyTourCost = babyTourCost;
	}

	public BigDecimal getPrivateRoomPrice() {
		return privateRoomPrice;
	}

	public void setPrivateRoomPrice(BigDecimal privateRoomPrice) {
		this.privateRoomPrice = privateRoomPrice;
	}

	public Instant getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Instant createdTime) {
		this.createdTime = createdTime;
	}

	public Instant getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Instant lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public Short getMaximumCapacity() {
		return maximumCapacity;
	}

	public void setMaximumCapacity(Short maximumCapacity) {
		this.maximumCapacity = maximumCapacity;
	}

	public Short getAvailableCapacity() {
		return availableCapacity;
	}

	public void setAvailableCapacity(Short availableCapacity) {
		this.availableCapacity = availableCapacity;
	}

	public BigDecimal getTotalAdditionalCost() {
		return totalAdditionalCost;
	}

	public void setTotalAdditionalCost(BigDecimal totalAdditionalCost) {
		this.totalAdditionalCost = totalAdditionalCost;
	}

	public Set<Guide> getGuideSet() {
		return guideSet;
	}

	public void setGuideSet(Set<Guide> guideSet) {
		this.guideSet = guideSet;
	}

	public Set<Booking> getBookingSet() {
		return bookingSet;
	}

	public void setBookingSet(Set<Booking> bookingSet) {
		this.bookingSet = bookingSet;
	}

	public Set<TourRating> getTourRatingSet() {
		return tourRatingSet;
	}

	public void setTourRatingSet(Set<TourRating> tourRatingSet) {
		this.tourRatingSet = tourRatingSet;
	}
    
    
}
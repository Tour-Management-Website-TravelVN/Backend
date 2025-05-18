package ads.objects;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourUnitObject {
	
	    private String tourUnitId;

	    private BigDecimal adultTourCost;

	    private BigDecimal adultTourPrice;

	    private Short availableCapacity;

	    private BigDecimal babyTourCost;

	    private BigDecimal babyTourPrice;

	    private BigDecimal childTourCost;

	    private BigDecimal childTourPrice;

	    private Date createdTime;

	    private LocalDate departureDate;

	    private Date lastUpdatedTime;
	    
	    private Short maximumCapacity;

	    private BigDecimal privateRoomPrice;
	    
	    private LocalDate returnDate;

	    private BigDecimal toddlerTourCost;
	    
	    private BigDecimal toddlerTourPrice;

	    private BigDecimal totalAdditionalCost;

	    private DiscountObject discount;

	    private FestivalObject festival;

	    private TourOperatorObject lastUpdatedOperator;
	    
	    private TourObject tour;
	    
	    private TourOperatorObject tourOperator;
	   
	    private Set<Guide> guideSet = new HashSet<>();

	
	    private Set<Booking> bookingSet = new HashSet<>();

	    
	    private Set<TourRating> tourRatingSet = new HashSet<>();


		
	
	
}

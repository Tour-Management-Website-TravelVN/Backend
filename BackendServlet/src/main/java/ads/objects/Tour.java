package ads.objects;

import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Tour {
    private String tourId;

    private Category category;

    private TourOperator tourOperator;

    private TourOperator lastUpdatedOperator;

    private String tourName;

    private String duration;

    private String vehicle;

    private String targetAudience;

    private String departurePlace;

    private String placesToVisit;

    private String cuisine;

    private String idealTime;

    private String description;

    private Instant createdTime;

    private Instant lastUpdatedTime;

    private String inclusions;

    private String exclusions;

    private Set<TourProgram> tourProgramSet = new HashSet<>();

    private Set<Image> imageSet = new HashSet<>();

    private Set<TourUnit> tourUnitSet = new HashSet<>();

	public String getTourId() {
		return tourId;
	}

	public void setTourId(String tourId) {
		this.tourId = tourId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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

	public String getTourName() {
		return tourName;
	}

	public void setTourName(String tourName) {
		this.tourName = tourName;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public String getTargetAudience() {
		return targetAudience;
	}

	public void setTargetAudience(String targetAudience) {
		this.targetAudience = targetAudience;
	}

	public String getDeparturePlace() {
		return departurePlace;
	}

	public void setDeparturePlace(String departurePlace) {
		this.departurePlace = departurePlace;
	}

	public String getPlacesToVisit() {
		return placesToVisit;
	}

	public void setPlacesToVisit(String placesToVisit) {
		this.placesToVisit = placesToVisit;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public String getIdealTime() {
		return idealTime;
	}

	public void setIdealTime(String idealTime) {
		this.idealTime = idealTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getInclusions() {
		return inclusions;
	}

	public void setInclusions(String inclusions) {
		this.inclusions = inclusions;
	}

	public String getExclusions() {
		return exclusions;
	}

	public void setExclusions(String exclusions) {
		this.exclusions = exclusions;
	}

	public Set<TourProgram> getTourProgramSet() {
		return tourProgramSet;
	}

	public void setTourProgramSet(Set<TourProgram> tourProgramSet) {
		this.tourProgramSet = tourProgramSet;
	}

	public Set<Image> getImageSet() {
		return imageSet;
	}

	public void setImageSet(Set<Image> imageSet) {
		this.imageSet = imageSet;
	}

	public Set<TourUnit> getTourUnitSet() {
		return tourUnitSet;
	}

	public void setTourUnitSet(Set<TourUnit> tourUnitSet) {
		this.tourUnitSet = tourUnitSet;
	}
    
    
}
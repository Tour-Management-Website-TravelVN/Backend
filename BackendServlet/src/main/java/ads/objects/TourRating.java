package ads.objects;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TourRating {
    private Integer id;

    private TourUnit tourUnit;

    private Administrator administrator;

    private Customer c;

    private Byte ratingValue;

    private String comment;

    private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TourUnit getTourUnit() {
		return tourUnit;
	}

	public void setTourUnit(TourUnit tourUnit) {
		this.tourUnit = tourUnit;
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public Customer getC() {
		return c;
	}

	public void setC(Customer c) {
		this.c = c;
	}

	public Byte getRatingValue() {
		return ratingValue;
	}

	public void setRatingValue(Byte ratingValue) {
		this.ratingValue = ratingValue;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    
}
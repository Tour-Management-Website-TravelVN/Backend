package ads.objects;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Administrator {
    private Integer id;

    private UserAccount userAccount;

    private Set<TourRating> tourRatingSet = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public Set<TourRating> getTourRatingSet() {
		return tourRatingSet;
	}

	public void setTourRatingSet(Set<TourRating> tourRatingSet) {
		this.tourRatingSet = tourRatingSet;
	}
    
    
}
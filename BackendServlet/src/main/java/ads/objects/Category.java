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
public class Category {
    private Integer id;

    private String categoryName;

    private String description;

    private Set<Tour> tourSet = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Tour> getTourSet() {
		return tourSet;
	}

	public void setTourSet(Set<Tour> tourSet) {
		this.tourSet = tourSet;
	}
    
    
}
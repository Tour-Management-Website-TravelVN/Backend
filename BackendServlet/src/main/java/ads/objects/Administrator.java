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
}
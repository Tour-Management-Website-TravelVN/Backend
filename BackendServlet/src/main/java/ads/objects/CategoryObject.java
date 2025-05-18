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
public class CategoryObject {
    private Integer id;

    private String categoryName;

    private String description;

    private Set<TourObject> tourSet = new HashSet<>();
}
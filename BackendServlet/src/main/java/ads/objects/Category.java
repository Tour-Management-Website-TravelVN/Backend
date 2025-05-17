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
}
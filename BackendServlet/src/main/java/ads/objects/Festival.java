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
public class Festival {
    private Integer id;

    private String festivalName;

    private String description;

    private Boolean displayStatus = false;

    private Set<TourUnit> tourUnitSet = new HashSet<>();
}
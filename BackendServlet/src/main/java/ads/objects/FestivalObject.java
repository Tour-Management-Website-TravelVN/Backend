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
public class FestivalObject {
    private Integer id;

    private String festivalName;

    private String description;

    private Boolean displayStatus = false;

    private Set<TourUnitObject> tourUnitSet = new HashSet<>();
}
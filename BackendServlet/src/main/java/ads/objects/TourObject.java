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
public class TourObject {
    private String tourId;

    private CategoryObject category;

    private TourOperatorObject tourOperator;

    private TourOperatorObject lastUpdatedOperator;

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

    private Set<TourUnitObject> tourUnitSet = new HashSet<>();
}
package ads.objects;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Guide {
    private GuideId id;

    private TourUnitObject tourUnit;

    private TourGuide tourGuide;

    private TourOperatorObject tourOperator;

    private Instant updatedAt;

}
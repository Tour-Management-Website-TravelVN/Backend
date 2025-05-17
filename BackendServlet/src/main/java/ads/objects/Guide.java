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

    private TourUnit tourUnit;

    private TourGuide tourGuide;

    private TourOperator tourOperator;

    private Instant updatedAt;

}
package ads.objects;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TourRating {
    private Integer id;

    private TourUnit tourUnit;

    private Administrator administrator;

    private Customer c;

    private Byte ratingValue;

    private String comment;

    private String status;

}
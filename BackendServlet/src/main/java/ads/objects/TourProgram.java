package ads.objects;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TourProgram {
    private Integer id;

    private TourObject tour;

    private String locations;

    private Byte day;

    private String mealDescription;

    private String desciption;

}
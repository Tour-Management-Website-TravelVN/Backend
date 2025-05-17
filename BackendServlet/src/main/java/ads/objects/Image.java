package ads.objects;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Image {
    private Integer id;

    private String imageName;

    private String url;

    private Tour tour;

}
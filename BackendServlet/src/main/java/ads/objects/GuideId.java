package ads.objects;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GuideId implements java.io.Serializable {
    private static final long serialVersionUID = -6169060593995685383L;

    private String tourUnitId;

    private Integer tourGuideId;

}
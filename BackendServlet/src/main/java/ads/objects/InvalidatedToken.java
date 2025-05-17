package ads.objects;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InvalidatedToken {
    private String tokenId;

    private Instant expiryDate;
}

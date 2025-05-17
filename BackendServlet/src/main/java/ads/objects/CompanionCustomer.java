package ads.objects;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CompanionCustomer {
    private Integer id;

    private Booking booking;

    private Customer c;

}
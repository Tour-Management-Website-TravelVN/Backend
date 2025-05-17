package ads.objects;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserAccount {
    private String username;

    private String password;

    private Customer c;

    private Administrator administrator;

    private TourGuide tourGuide;

    private TourOperator tourOperator;

    private String status;

    private String email;

}
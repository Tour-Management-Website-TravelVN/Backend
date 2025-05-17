package ads.objects;

import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(exclude = {"userAccount", "bookingSet", "companionCustomerSet", "tourRatingSet"})
public class Customer {
    private Integer id;

    private String firstname;

    private String lastname;

    private LocalDate dateOfBirth;

    private Boolean gender = false;

    private String nationality;

    private String citizenId;

    private String passport;

    private String phoneNumber;

    private String note;

    private String address;

    private UserAccount userAccount;

    private Set<Booking> bookingSet = new HashSet<>();

    private Set<CompanionCustomer> companionCustomerSet = new HashSet<>();

    private Set<TourRating> tourRatingSet = new HashSet<>();
}
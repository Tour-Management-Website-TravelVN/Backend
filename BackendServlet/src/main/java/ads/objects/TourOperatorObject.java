package ads.objects;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TourOperatorObject {
    private Integer id;

    private String firstname;

    private String lastname;

    private LocalDate dateOfBirth;

    private Boolean gender = false;

    private String address;

    private String phoneNumber;

    private String citizenId;

    private String hometown;

    private BigDecimal salary;

    private LocalDate startDate;

    private LocalDate endDate;

    private Set<TourObject> tourCreatedSet = new HashSet<>();

    private Set<TourObject> tourUpdatedSet = new HashSet<>();

    private Set<TourUnitObject> tourUnitCreatedSet = new HashSet<>();

    private Set<TourObject> tourUnitUpdatedSet = new HashSet<>();

    private Set<Guide> guideSet = new HashSet<>();

    private UserAccount userAccount;
}
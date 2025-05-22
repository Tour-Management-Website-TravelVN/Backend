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
public class TourOperator {
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

    private Set<Tour> tourCreatedSet = new HashSet<>();

    private Set<Tour> tourUpdatedSet = new HashSet<>();

    private Set<TourUnit> tourUnitCreatedSet = new HashSet<>();

    private Set<Tour> tourUnitUpdatedSet = new HashSet<>();

    private Set<Guide> guideSet = new HashSet<>();

    private UserAccount userAccount;
    
    public String getFullName() {
    	return this.firstname +" " + this.lastname;
    }
}
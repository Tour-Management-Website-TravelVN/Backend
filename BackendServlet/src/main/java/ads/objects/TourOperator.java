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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Set<Tour> getTourCreatedSet() {
		return tourCreatedSet;
	}

	public void setTourCreatedSet(Set<Tour> tourCreatedSet) {
		this.tourCreatedSet = tourCreatedSet;
	}

	public Set<Tour> getTourUpdatedSet() {
		return tourUpdatedSet;
	}

	public void setTourUpdatedSet(Set<Tour> tourUpdatedSet) {
		this.tourUpdatedSet = tourUpdatedSet;
	}

	public Set<TourUnit> getTourUnitCreatedSet() {
		return tourUnitCreatedSet;
	}

	public void setTourUnitCreatedSet(Set<TourUnit> tourUnitCreatedSet) {
		this.tourUnitCreatedSet = tourUnitCreatedSet;
	}

	public Set<Tour> getTourUnitUpdatedSet() {
		return tourUnitUpdatedSet;
	}

	public void setTourUnitUpdatedSet(Set<Tour> tourUnitUpdatedSet) {
		this.tourUnitUpdatedSet = tourUnitUpdatedSet;
	}

	public Set<Guide> getGuideSet() {
		return guideSet;
	}

	public void setGuideSet(Set<Guide> guideSet) {
		this.guideSet = guideSet;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
}
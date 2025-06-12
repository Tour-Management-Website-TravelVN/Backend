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

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public Set<Booking> getBookingSet() {
		return bookingSet;
	}

	public void setBookingSet(Set<Booking> bookingSet) {
		this.bookingSet = bookingSet;
	}

	public Set<CompanionCustomer> getCompanionCustomerSet() {
		return companionCustomerSet;
	}

	public void setCompanionCustomerSet(Set<CompanionCustomer> companionCustomerSet) {
		this.companionCustomerSet = companionCustomerSet;
	}

	public Set<TourRating> getTourRatingSet() {
		return tourRatingSet;
	}

	public void setTourRatingSet(Set<TourRating> tourRatingSet) {
		this.tourRatingSet = tourRatingSet;
	}
    
    
}
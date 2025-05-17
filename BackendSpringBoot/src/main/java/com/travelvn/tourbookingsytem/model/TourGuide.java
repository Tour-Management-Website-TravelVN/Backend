package com.travelvn.tourbookingsytem.model;

import jakarta.persistence.*;
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
@Entity
@Table(name = "tour_guide")
public class TourGuide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_guide_id", nullable = false)
    private Integer id;

    @Column(name = "firstname", nullable = false, length = 40)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 10)
    private String lastname;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "gender", nullable = false)
    private Boolean gender = false;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false, length = 10)
    private String phoneNumber;

    @Column(name = "citizen_id", nullable = false, length = 12)
    private String citizenId;

    @Column(name = "hometown", nullable = false)
    private String hometown;

    @Column(name = "salary", nullable = false, precision = 19, scale = 3)
    private BigDecimal salary;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "card_id", nullable = false, length = 12)
    private String cardId;

    @Column(name = "language", nullable = false)
    private String language;

    @ToString.Exclude
    @OneToMany(mappedBy = "tourGuide")
    private Set<Guide> guideSet = new HashSet<>();

    @ToString.Exclude
    @OneToOne(mappedBy = "tourGuide")
    private UserAccount userAccount;
}
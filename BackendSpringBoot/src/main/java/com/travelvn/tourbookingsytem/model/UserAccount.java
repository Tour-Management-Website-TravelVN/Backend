package com.travelvn.tourbookingsytem.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "user_account")
public class UserAccount {
    @Id
    @Column(name = "username", nullable = false, unique = true, /*columnDefinition = "VARCHAR(40) COLLATE utf8mb4_unicode_ci",*/ length = 40)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "c_id")
    private Customer c;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "administrator_id")
    private Administrator administrator;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tour_guide_id")
    private TourGuide tourGuide;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tour_operator_id")
    private TourOperator tourOperator;

    @Column(name = "status", nullable = false, length = 4)
    private String status;

    @Column(name = "email", nullable = false)
    private String email;

}
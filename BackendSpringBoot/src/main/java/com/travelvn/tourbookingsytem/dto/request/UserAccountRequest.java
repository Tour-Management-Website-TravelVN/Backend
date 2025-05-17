package com.travelvn.tourbookingsytem.dto.request;

import com.travelvn.tourbookingsytem.dto.request.lite.CustomerRequestLite;
import com.travelvn.tourbookingsytem.exception.ErrorCode;
import com.travelvn.tourbookingsytem.validator.EmailConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserAccountRequest {
    @Size(min = 8, max = 40, message = "USERNAME_INVALID")
    @NotNull
    private String username;

    @Size(min = 6, max = 20, message = "PASSWORD_INVALID")
    @NotNull
    private String password;

    private CustomerRequestLite c;

    private AdministratorRequest administrator;

    private TourGuideRequest tourGuide;

    private TourOperatorRequest tourOperator;

    private String status;

    @EmailConstraint(message = "INVALID_EMAIL")
    private String email;
}

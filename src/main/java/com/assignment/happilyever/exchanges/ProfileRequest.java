package com.assignment.happilyever.exchanges;

import com.assignment.happilyever.model.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ProfileRequest {

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Status status;

    public ProfileRequest(String firstName, String lastName, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.status = Status.ACTIVE;
    }

    public ProfileRequest(String firstName, String lastName, Date dateOfBirth, Status status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
    }
}

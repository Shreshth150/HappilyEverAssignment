package com.assignment.happilyever.exchanges;

import com.assignment.happilyever.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfileResponse {

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Status status;

}

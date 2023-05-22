package com.contactlist.payload.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContactDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;
}

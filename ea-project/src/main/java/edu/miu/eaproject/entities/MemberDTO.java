package edu.miu.eaproject.entities;

import lombok.Data;

@Data
public class MemberDTO {
    private String firstName;

    private String lastName;
    private String email;
    private String password;
}

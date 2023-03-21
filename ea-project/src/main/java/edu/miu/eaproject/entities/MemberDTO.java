package edu.miu.eaproject.entities;

import edu.miu.eaproject.entities.enums.RoleType;
import lombok.Data;

@Data
public class MemberDTO {
    private String firstName;

    private String lastName;
    private String email;
    private String password;
    private RoleType role;

}

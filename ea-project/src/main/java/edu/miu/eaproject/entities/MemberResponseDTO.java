package edu.miu.eaproject.entities;

import edu.miu.eaproject.entities.enums.RoleType;
import lombok.Data;

@Data
public class MemberResponseDTO {
    private  long id;
    private String firstName;
    private String lastName;
    private String email;
    private RoleType role;
}

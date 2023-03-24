package edu.miu.eaproject.entities;

import edu.miu.eaproject.entities.enums.RoleType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class MemberDTO {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private String password;
    private RoleType role;

}

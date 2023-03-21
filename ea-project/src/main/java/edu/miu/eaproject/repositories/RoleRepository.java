package edu.miu.eaproject.repositories;

import edu.miu.eaproject.entities.Role;
import edu.miu.eaproject.entities.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    public Role findRoleByRole(RoleType role);
}

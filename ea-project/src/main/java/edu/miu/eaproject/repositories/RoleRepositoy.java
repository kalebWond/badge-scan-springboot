package edu.miu.eaproject.repositories;

import edu.miu.eaproject.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepositoy extends JpaRepository<Role,Long> {

    public Role findRoleByRoleIgnoreCase(String role);
}

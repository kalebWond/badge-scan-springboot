package edu.miu.eaproject.repositories;

import edu.miu.eaproject.entities.Role;
import edu.miu.eaproject.entities.enums.RoleType;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role,Long> {

    Role findByRole(RoleType role);
}

package edu.miu.eaproject.repositories;

import edu.miu.eaproject.entities.Role;
import edu.miu.eaproject.entities.enums.RoleType;
import edu.miu.waa.project.backend.domain.Role;
import edu.miu.waa.project.backend.enumSet.RoleType;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role,Long> {

    Role findByRole(RoleType role);
}

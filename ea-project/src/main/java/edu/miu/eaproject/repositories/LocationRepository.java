package edu.miu.eaproject.repositories;

import edu.miu.eaproject.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
    List<Location> findByPlans_Id(Long planId);
}

package edu.miu.eaproject.repositories;

import edu.miu.eaproject.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
    @Query(value = "select * from location_table l where l.planId=:planId",nativeQuery = true)
    List<Location> findByPlans_Id(Long planId);
}

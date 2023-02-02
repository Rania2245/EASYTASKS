package tn.itdevspace.easytask.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.itdevspace.easytask.domain.Maintenance;

/**
 * Spring Data JPA repository for the Maintenance entity.
 */
@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, UUID> {
    default Optional<Maintenance> findOneWithEagerRelationships(UUID id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Maintenance> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Maintenance> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct maintenance from Maintenance maintenance left join fetch maintenance.ressource",
        countQuery = "select count(distinct maintenance) from Maintenance maintenance"
    )
    Page<Maintenance> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct maintenance from Maintenance maintenance left join fetch maintenance.ressource")
    List<Maintenance> findAllWithToOneRelationships();

    @Query("select maintenance from Maintenance maintenance left join fetch maintenance.ressource where maintenance.id =:id")
    Optional<Maintenance> findOneWithToOneRelationships(@Param("id") UUID id);
}

package tn.itdevspace.easytask.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.itdevspace.easytask.domain.Projet;

/**
 * Spring Data JPA repository for the Projet entity.
 */
@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long> {
    default Optional<Projet> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Projet> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Projet> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct projet from Projet projet left join fetch projet.client",
        countQuery = "select count(distinct projet) from Projet projet"
    )
    Page<Projet> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct projet from Projet projet left join fetch projet.client")
    List<Projet> findAllWithToOneRelationships();

    @Query("select projet from Projet projet left join fetch projet.client where projet.id =:id")
    Optional<Projet> findOneWithToOneRelationships(@Param("id") Long id);
}

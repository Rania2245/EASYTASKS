package tn.itdevspace.easytask.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.itdevspace.easytask.domain.Livrable;

/**
 * Spring Data JPA repository for the Livrable entity.
 */
@Repository
public interface LivrableRepository extends JpaRepository<Livrable, Long> {
    default Optional<Livrable> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Livrable> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Livrable> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct livrable from Livrable livrable left join fetch livrable.projet",
        countQuery = "select count(distinct livrable) from Livrable livrable"
    )
    Page<Livrable> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct livrable from Livrable livrable left join fetch livrable.projet")
    List<Livrable> findAllWithToOneRelationships();

    @Query("select livrable from Livrable livrable left join fetch livrable.projet where livrable.id =:id")
    Optional<Livrable> findOneWithToOneRelationships(@Param("id") Long id);
}

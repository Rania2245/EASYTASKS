package tn.itdevspace.easytask.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.itdevspace.easytask.domain.ChargeJournaliere;

/**
 * Spring Data JPA repository for the ChargeJournaliere entity.
 */
@Repository
public interface ChargeJournaliereRepository extends JpaRepository<ChargeJournaliere, UUID> {
    default Optional<ChargeJournaliere> findOneWithEagerRelationships(UUID id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<ChargeJournaliere> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<ChargeJournaliere> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct chargeJournaliere from ChargeJournaliere chargeJournaliere left join fetch chargeJournaliere.ressource",
        countQuery = "select count(distinct chargeJournaliere) from ChargeJournaliere chargeJournaliere"
    )
    Page<ChargeJournaliere> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct chargeJournaliere from ChargeJournaliere chargeJournaliere left join fetch chargeJournaliere.ressource")
    List<ChargeJournaliere> findAllWithToOneRelationships();

    @Query(
        "select chargeJournaliere from ChargeJournaliere chargeJournaliere left join fetch chargeJournaliere.ressource where chargeJournaliere.id =:id"
    )
    Optional<ChargeJournaliere> findOneWithToOneRelationships(@Param("id") UUID id);
}

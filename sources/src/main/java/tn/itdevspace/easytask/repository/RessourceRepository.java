package tn.itdevspace.easytask.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tn.itdevspace.easytask.domain.Ressource;

/**
 * Spring Data JPA repository for the Ressource entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RessourceRepository extends JpaRepository<Ressource, UUID> {}

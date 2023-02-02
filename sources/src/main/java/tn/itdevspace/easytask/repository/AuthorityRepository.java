package tn.itdevspace.easytask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.itdevspace.easytask.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}

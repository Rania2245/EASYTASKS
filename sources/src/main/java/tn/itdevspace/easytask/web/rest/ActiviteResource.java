package tn.itdevspace.easytask.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;
import tn.itdevspace.easytask.domain.Activite;
import tn.itdevspace.easytask.repository.ActiviteRepository;
import tn.itdevspace.easytask.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.itdevspace.easytask.domain.Activite}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ActiviteResource {

    private final Logger log = LoggerFactory.getLogger(ActiviteResource.class);

    private static final String ENTITY_NAME = "activite";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ActiviteRepository activiteRepository;

    public ActiviteResource(ActiviteRepository activiteRepository) {
        this.activiteRepository = activiteRepository;
    }

    /**
     * {@code POST  /activites} : Create a new activite.
     *
     * @param activite the activite to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new activite, or with status {@code 400 (Bad Request)} if the activite has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/activites")
    public ResponseEntity<Activite> createActivite(@Valid @RequestBody Activite activite) throws URISyntaxException {
        log.debug("REST request to save Activite : {}", activite);
        if (activite.getId() != null) {
            throw new BadRequestAlertException("A new activite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Activite result = activiteRepository.save(activite);
        return ResponseEntity
            .created(new URI("/api/activites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /activites/:id} : Updates an existing activite.
     *
     * @param id the id of the activite to save.
     * @param activite the activite to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated activite,
     * or with status {@code 400 (Bad Request)} if the activite is not valid,
     * or with status {@code 500 (Internal Server Error)} if the activite couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/activites/{id}")
    public ResponseEntity<Activite> updateActivite(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Activite activite
    ) throws URISyntaxException {
        log.debug("REST request to update Activite : {}, {}", id, activite);
        if (activite.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, activite.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!activiteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Activite result = activiteRepository.save(activite);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, activite.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /activites/:id} : Partial updates given fields of an existing activite, field will ignore if it is null
     *
     * @param id the id of the activite to save.
     * @param activite the activite to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated activite,
     * or with status {@code 400 (Bad Request)} if the activite is not valid,
     * or with status {@code 404 (Not Found)} if the activite is not found,
     * or with status {@code 500 (Internal Server Error)} if the activite couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/activites/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Activite> partialUpdateActivite(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Activite activite
    ) throws URISyntaxException {
        log.debug("REST request to partial update Activite partially : {}, {}", id, activite);
        if (activite.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, activite.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!activiteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Activite> result = activiteRepository
            .findById(activite.getId())
            .map(existingActivite -> {
                if (activite.getRefAct() != null) {
                    existingActivite.setRefAct(activite.getRefAct());
                }
                if (activite.getDescription() != null) {
                    existingActivite.setDescription(activite.getDescription());
                }
                if (activite.getDateDebut() != null) {
                    existingActivite.setDateDebut(activite.getDateDebut());
                }
                if (activite.getDateFin() != null) {
                    existingActivite.setDateFin(activite.getDateFin());
                }
                if (activite.getRaf() != null) {
                    existingActivite.setRaf(activite.getRaf());
                }
                if (activite.getEtat() != null) {
                    existingActivite.setEtat(activite.getEtat());
                }

                return existingActivite;
            })
            .map(activiteRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, activite.getId().toString())
        );
    }

    /**
     * {@code GET  /activites} : get all the activites.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of activites in body.
     */
    @GetMapping("/activites")
    public List<Activite> getAllActivites(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Activites");
        if (eagerload) {
            return activiteRepository.findAllWithEagerRelationships();
        } else {
            return activiteRepository.findAll();
        }
    }

    /**
     * {@code GET  /activites/:id} : get the "id" activite.
     *
     * @param id the id of the activite to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the activite, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/activites/{id}")
    public ResponseEntity<Activite> getActivite(@PathVariable Long id) {
        log.debug("REST request to get Activite : {}", id);
        Optional<Activite> activite = activiteRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(activite);
    }

    /**
     * {@code DELETE  /activites/:id} : delete the "id" activite.
     *
     * @param id the id of the activite to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/activites/{id}")
    public ResponseEntity<Void> deleteActivite(@PathVariable Long id) {
        log.debug("REST request to delete Activite : {}", id);
        activiteRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

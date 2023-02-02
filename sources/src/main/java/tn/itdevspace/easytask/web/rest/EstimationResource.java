package tn.itdevspace.easytask.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;
import tn.itdevspace.easytask.domain.Estimation;
import tn.itdevspace.easytask.repository.EstimationRepository;
import tn.itdevspace.easytask.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.itdevspace.easytask.domain.Estimation}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class EstimationResource {

    private final Logger log = LoggerFactory.getLogger(EstimationResource.class);

    private static final String ENTITY_NAME = "estimation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EstimationRepository estimationRepository;

    public EstimationResource(EstimationRepository estimationRepository) {
        this.estimationRepository = estimationRepository;
    }

    /**
     * {@code POST  /estimations} : Create a new estimation.
     *
     * @param estimation the estimation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new estimation, or with status {@code 400 (Bad Request)} if the estimation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/estimations")
    public ResponseEntity<Estimation> createEstimation(@RequestBody Estimation estimation) throws URISyntaxException {
        log.debug("REST request to save Estimation : {}", estimation);
        if (estimation.getId() != null) {
            throw new BadRequestAlertException("A new estimation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Estimation result = estimationRepository.save(estimation);
        return ResponseEntity
            .created(new URI("/api/estimations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /estimations/:id} : Updates an existing estimation.
     *
     * @param id the id of the estimation to save.
     * @param estimation the estimation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated estimation,
     * or with status {@code 400 (Bad Request)} if the estimation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the estimation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/estimations/{id}")
    public ResponseEntity<Estimation> updateEstimation(
        @PathVariable(value = "id", required = false) final UUID id,
        @RequestBody Estimation estimation
    ) throws URISyntaxException {
        log.debug("REST request to update Estimation : {}, {}", id, estimation);
        if (estimation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, estimation.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!estimationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Estimation result = estimationRepository.save(estimation);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, estimation.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /estimations/:id} : Partial updates given fields of an existing estimation, field will ignore if it is null
     *
     * @param id the id of the estimation to save.
     * @param estimation the estimation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated estimation,
     * or with status {@code 400 (Bad Request)} if the estimation is not valid,
     * or with status {@code 404 (Not Found)} if the estimation is not found,
     * or with status {@code 500 (Internal Server Error)} if the estimation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/estimations/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Estimation> partialUpdateEstimation(
        @PathVariable(value = "id", required = false) final UUID id,
        @RequestBody Estimation estimation
    ) throws URISyntaxException {
        log.debug("REST request to partial update Estimation partially : {}, {}", id, estimation);
        if (estimation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, estimation.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!estimationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Estimation> result = estimationRepository
            .findById(estimation.getId())
            .map(existingEstimation -> {
                if (estimation.getDate() != null) {
                    existingEstimation.setDate(estimation.getDate());
                }
                if (estimation.getValeurJour() != null) {
                    existingEstimation.setValeurJour(estimation.getValeurJour());
                }
                if (estimation.getValeurHeure() != null) {
                    existingEstimation.setValeurHeure(estimation.getValeurHeure());
                }
                if (estimation.getPriseEnCharge() != null) {
                    existingEstimation.setPriseEnCharge(estimation.getPriseEnCharge());
                }
                if (estimation.getType() != null) {
                    existingEstimation.setType(estimation.getType());
                }

                return existingEstimation;
            })
            .map(estimationRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, estimation.getId().toString())
        );
    }

    /**
     * {@code GET  /estimations} : get all the estimations.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of estimations in body.
     */
    @GetMapping("/estimations")
    public List<Estimation> getAllEstimations(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Estimations");
        if (eagerload) {
            return estimationRepository.findAllWithEagerRelationships();
        } else {
            return estimationRepository.findAll();
        }
    }

    /**
     * {@code GET  /estimations/:id} : get the "id" estimation.
     *
     * @param id the id of the estimation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the estimation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/estimations/{id}")
    public ResponseEntity<Estimation> getEstimation(@PathVariable UUID id) {
        log.debug("REST request to get Estimation : {}", id);
        Optional<Estimation> estimation = estimationRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(estimation);
    }

    /**
     * {@code DELETE  /estimations/:id} : delete the "id" estimation.
     *
     * @param id the id of the estimation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/estimations/{id}")
    public ResponseEntity<Void> deleteEstimation(@PathVariable UUID id) {
        log.debug("REST request to delete Estimation : {}", id);
        estimationRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

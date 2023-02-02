package tn.itdevspace.easytask.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
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
import tn.itdevspace.easytask.domain.ChargeJournaliere;
import tn.itdevspace.easytask.repository.ChargeJournaliereRepository;
import tn.itdevspace.easytask.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.itdevspace.easytask.domain.ChargeJournaliere}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ChargeJournaliereResource {

    private final Logger log = LoggerFactory.getLogger(ChargeJournaliereResource.class);

    private static final String ENTITY_NAME = "chargeJournaliere";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChargeJournaliereRepository chargeJournaliereRepository;

    public ChargeJournaliereResource(ChargeJournaliereRepository chargeJournaliereRepository) {
        this.chargeJournaliereRepository = chargeJournaliereRepository;
    }

    /**
     * {@code POST  /charge-journalieres} : Create a new chargeJournaliere.
     *
     * @param chargeJournaliere the chargeJournaliere to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chargeJournaliere, or with status {@code 400 (Bad Request)} if the chargeJournaliere has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/charge-journalieres")
    public ResponseEntity<ChargeJournaliere> createChargeJournaliere(@Valid @RequestBody ChargeJournaliere chargeJournaliere)
        throws URISyntaxException {
        log.debug("REST request to save ChargeJournaliere : {}", chargeJournaliere);
        if (chargeJournaliere.getId() != null) {
            throw new BadRequestAlertException("A new chargeJournaliere cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChargeJournaliere result = chargeJournaliereRepository.save(chargeJournaliere);
        return ResponseEntity
            .created(new URI("/api/charge-journalieres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /charge-journalieres/:id} : Updates an existing chargeJournaliere.
     *
     * @param id the id of the chargeJournaliere to save.
     * @param chargeJournaliere the chargeJournaliere to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chargeJournaliere,
     * or with status {@code 400 (Bad Request)} if the chargeJournaliere is not valid,
     * or with status {@code 500 (Internal Server Error)} if the chargeJournaliere couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/charge-journalieres/{id}")
    public ResponseEntity<ChargeJournaliere> updateChargeJournaliere(
        @PathVariable(value = "id", required = false) final UUID id,
        @Valid @RequestBody ChargeJournaliere chargeJournaliere
    ) throws URISyntaxException {
        log.debug("REST request to update ChargeJournaliere : {}, {}", id, chargeJournaliere);
        if (chargeJournaliere.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, chargeJournaliere.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chargeJournaliereRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ChargeJournaliere result = chargeJournaliereRepository.save(chargeJournaliere);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chargeJournaliere.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /charge-journalieres/:id} : Partial updates given fields of an existing chargeJournaliere, field will ignore if it is null
     *
     * @param id the id of the chargeJournaliere to save.
     * @param chargeJournaliere the chargeJournaliere to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chargeJournaliere,
     * or with status {@code 400 (Bad Request)} if the chargeJournaliere is not valid,
     * or with status {@code 404 (Not Found)} if the chargeJournaliere is not found,
     * or with status {@code 500 (Internal Server Error)} if the chargeJournaliere couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/charge-journalieres/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ChargeJournaliere> partialUpdateChargeJournaliere(
        @PathVariable(value = "id", required = false) final UUID id,
        @NotNull @RequestBody ChargeJournaliere chargeJournaliere
    ) throws URISyntaxException {
        log.debug("REST request to partial update ChargeJournaliere partially : {}, {}", id, chargeJournaliere);
        if (chargeJournaliere.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, chargeJournaliere.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chargeJournaliereRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ChargeJournaliere> result = chargeJournaliereRepository
            .findById(chargeJournaliere.getId())
            .map(existingChargeJournaliere -> {
                if (chargeJournaliere.getDate() != null) {
                    existingChargeJournaliere.setDate(chargeJournaliere.getDate());
                }
                if (chargeJournaliere.getType() != null) {
                    existingChargeJournaliere.setType(chargeJournaliere.getType());
                }
                if (chargeJournaliere.getDuree() != null) {
                    existingChargeJournaliere.setDuree(chargeJournaliere.getDuree());
                }
                if (chargeJournaliere.getDescription() != null) {
                    existingChargeJournaliere.setDescription(chargeJournaliere.getDescription());
                }

                return existingChargeJournaliere;
            })
            .map(chargeJournaliereRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chargeJournaliere.getId().toString())
        );
    }

    /**
     * {@code GET  /charge-journalieres} : get all the chargeJournalieres.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chargeJournalieres in body.
     */
    @GetMapping("/charge-journalieres")
    public List<ChargeJournaliere> getAllChargeJournalieres(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all ChargeJournalieres");
        if (eagerload) {
            return chargeJournaliereRepository.findAllWithEagerRelationships();
        } else {
            return chargeJournaliereRepository.findAll();
        }
    }

    /**
     * {@code GET  /charge-journalieres/:id} : get the "id" chargeJournaliere.
     *
     * @param id the id of the chargeJournaliere to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chargeJournaliere, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/charge-journalieres/{id}")
    public ResponseEntity<ChargeJournaliere> getChargeJournaliere(@PathVariable UUID id) {
        log.debug("REST request to get ChargeJournaliere : {}", id);
        Optional<ChargeJournaliere> chargeJournaliere = chargeJournaliereRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(chargeJournaliere);
    }

    /**
     * {@code DELETE  /charge-journalieres/:id} : delete the "id" chargeJournaliere.
     *
     * @param id the id of the chargeJournaliere to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/charge-journalieres/{id}")
    public ResponseEntity<Void> deleteChargeJournaliere(@PathVariable UUID id) {
        log.debug("REST request to delete ChargeJournaliere : {}", id);
        chargeJournaliereRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

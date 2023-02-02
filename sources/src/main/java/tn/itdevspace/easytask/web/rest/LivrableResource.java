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
import tn.itdevspace.easytask.domain.Livrable;
import tn.itdevspace.easytask.repository.LivrableRepository;
import tn.itdevspace.easytask.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.itdevspace.easytask.domain.Livrable}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LivrableResource {

    private final Logger log = LoggerFactory.getLogger(LivrableResource.class);

    private static final String ENTITY_NAME = "livrable";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LivrableRepository livrableRepository;

    public LivrableResource(LivrableRepository livrableRepository) {
        this.livrableRepository = livrableRepository;
    }

    /**
     * {@code POST  /livrables} : Create a new livrable.
     *
     * @param livrable the livrable to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new livrable, or with status {@code 400 (Bad Request)} if the livrable has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/livrables")
    public ResponseEntity<Livrable> createLivrable(@Valid @RequestBody Livrable livrable) throws URISyntaxException {
        log.debug("REST request to save Livrable : {}", livrable);
        if (livrable.getId() != null) {
            throw new BadRequestAlertException("A new livrable cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Livrable result = livrableRepository.save(livrable);
        return ResponseEntity
            .created(new URI("/api/livrables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /livrables/:id} : Updates an existing livrable.
     *
     * @param id the id of the livrable to save.
     * @param livrable the livrable to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated livrable,
     * or with status {@code 400 (Bad Request)} if the livrable is not valid,
     * or with status {@code 500 (Internal Server Error)} if the livrable couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/livrables/{id}")
    public ResponseEntity<Livrable> updateLivrable(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Livrable livrable
    ) throws URISyntaxException {
        log.debug("REST request to update Livrable : {}, {}", id, livrable);
        if (livrable.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, livrable.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!livrableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Livrable result = livrableRepository.save(livrable);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, livrable.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /livrables/:id} : Partial updates given fields of an existing livrable, field will ignore if it is null
     *
     * @param id the id of the livrable to save.
     * @param livrable the livrable to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated livrable,
     * or with status {@code 400 (Bad Request)} if the livrable is not valid,
     * or with status {@code 404 (Not Found)} if the livrable is not found,
     * or with status {@code 500 (Internal Server Error)} if the livrable couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/livrables/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Livrable> partialUpdateLivrable(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Livrable livrable
    ) throws URISyntaxException {
        log.debug("REST request to partial update Livrable partially : {}, {}", id, livrable);
        if (livrable.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, livrable.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!livrableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Livrable> result = livrableRepository
            .findById(livrable.getId())
            .map(existingLivrable -> {
                if (livrable.getRefLivrable() != null) {
                    existingLivrable.setRefLivrable(livrable.getRefLivrable());
                }
                if (livrable.getDateDebut() != null) {
                    existingLivrable.setDateDebut(livrable.getDateDebut());
                }
                if (livrable.getDateFin() != null) {
                    existingLivrable.setDateFin(livrable.getDateFin());
                }
                if (livrable.getDescription() != null) {
                    existingLivrable.setDescription(livrable.getDescription());
                }
                if (livrable.getEtat() != null) {
                    existingLivrable.setEtat(livrable.getEtat());
                }

                return existingLivrable;
            })
            .map(livrableRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, livrable.getId().toString())
        );
    }

    /**
     * {@code GET  /livrables} : get all the livrables.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of livrables in body.
     */
    @GetMapping("/livrables")
    public List<Livrable> getAllLivrables(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Livrables");
        if (eagerload) {
            return livrableRepository.findAllWithEagerRelationships();
        } else {
            return livrableRepository.findAll();
        }
    }

    /**
     * {@code GET  /livrables/:id} : get the "id" livrable.
     *
     * @param id the id of the livrable to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the livrable, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/livrables/{id}")
    public ResponseEntity<Livrable> getLivrable(@PathVariable Long id) {
        log.debug("REST request to get Livrable : {}", id);
        Optional<Livrable> livrable = livrableRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(livrable);
    }

    /**
     * {@code DELETE  /livrables/:id} : delete the "id" livrable.
     *
     * @param id the id of the livrable to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/livrables/{id}")
    public ResponseEntity<Void> deleteLivrable(@PathVariable Long id) {
        log.debug("REST request to delete Livrable : {}", id);
        livrableRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

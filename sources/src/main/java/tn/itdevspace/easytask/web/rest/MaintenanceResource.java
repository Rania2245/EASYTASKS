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
import tn.itdevspace.easytask.domain.Maintenance;
import tn.itdevspace.easytask.repository.MaintenanceRepository;
import tn.itdevspace.easytask.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.itdevspace.easytask.domain.Maintenance}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MaintenanceResource {

    private final Logger log = LoggerFactory.getLogger(MaintenanceResource.class);

    private static final String ENTITY_NAME = "maintenance";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MaintenanceRepository maintenanceRepository;

    public MaintenanceResource(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    /**
     * {@code POST  /maintenances} : Create a new maintenance.
     *
     * @param maintenance the maintenance to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new maintenance, or with status {@code 400 (Bad Request)} if the maintenance has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/maintenances")
    public ResponseEntity<Maintenance> createMaintenance(@Valid @RequestBody Maintenance maintenance) throws URISyntaxException {
        log.debug("REST request to save Maintenance : {}", maintenance);
        if (maintenance.getId() != null) {
            throw new BadRequestAlertException("A new maintenance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Maintenance result = maintenanceRepository.save(maintenance);
        return ResponseEntity
            .created(new URI("/api/maintenances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /maintenances/:id} : Updates an existing maintenance.
     *
     * @param id the id of the maintenance to save.
     * @param maintenance the maintenance to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated maintenance,
     * or with status {@code 400 (Bad Request)} if the maintenance is not valid,
     * or with status {@code 500 (Internal Server Error)} if the maintenance couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/maintenances/{id}")
    public ResponseEntity<Maintenance> updateMaintenance(
        @PathVariable(value = "id", required = false) final UUID id,
        @Valid @RequestBody Maintenance maintenance
    ) throws URISyntaxException {
        log.debug("REST request to update Maintenance : {}, {}", id, maintenance);
        if (maintenance.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, maintenance.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!maintenanceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Maintenance result = maintenanceRepository.save(maintenance);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, maintenance.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /maintenances/:id} : Partial updates given fields of an existing maintenance, field will ignore if it is null
     *
     * @param id the id of the maintenance to save.
     * @param maintenance the maintenance to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated maintenance,
     * or with status {@code 400 (Bad Request)} if the maintenance is not valid,
     * or with status {@code 404 (Not Found)} if the maintenance is not found,
     * or with status {@code 500 (Internal Server Error)} if the maintenance couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/maintenances/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Maintenance> partialUpdateMaintenance(
        @PathVariable(value = "id", required = false) final UUID id,
        @NotNull @RequestBody Maintenance maintenance
    ) throws URISyntaxException {
        log.debug("REST request to partial update Maintenance partially : {}, {}", id, maintenance);
        if (maintenance.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, maintenance.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!maintenanceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Maintenance> result = maintenanceRepository
            .findById(maintenance.getId())
            .map(existingMaintenance -> {
                if (maintenance.getDescription() != null) {
                    existingMaintenance.setDescription(maintenance.getDescription());
                }
                if (maintenance.getProduit() != null) {
                    existingMaintenance.setProduit(maintenance.getProduit());
                }
                if (maintenance.getSolution() != null) {
                    existingMaintenance.setSolution(maintenance.getSolution());
                }
                if (maintenance.getEtat() != null) {
                    existingMaintenance.setEtat(maintenance.getEtat());
                }
                if (maintenance.getDateDebut() != null) {
                    existingMaintenance.setDateDebut(maintenance.getDateDebut());
                }
                if (maintenance.getDateFin() != null) {
                    existingMaintenance.setDateFin(maintenance.getDateFin());
                }
                if (maintenance.getDuree() != null) {
                    existingMaintenance.setDuree(maintenance.getDuree());
                }

                return existingMaintenance;
            })
            .map(maintenanceRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, maintenance.getId().toString())
        );
    }

    /**
     * {@code GET  /maintenances} : get all the maintenances.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of maintenances in body.
     */
    @GetMapping("/maintenances")
    public List<Maintenance> getAllMaintenances(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Maintenances");
        if (eagerload) {
            return maintenanceRepository.findAllWithEagerRelationships();
        } else {
            return maintenanceRepository.findAll();
        }
    }

    /**
     * {@code GET  /maintenances/:id} : get the "id" maintenance.
     *
     * @param id the id of the maintenance to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the maintenance, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/maintenances/{id}")
    public ResponseEntity<Maintenance> getMaintenance(@PathVariable UUID id) {
        log.debug("REST request to get Maintenance : {}", id);
        Optional<Maintenance> maintenance = maintenanceRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(maintenance);
    }

    /**
     * {@code DELETE  /maintenances/:id} : delete the "id" maintenance.
     *
     * @param id the id of the maintenance to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/maintenances/{id}")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable UUID id) {
        log.debug("REST request to delete Maintenance : {}", id);
        maintenanceRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

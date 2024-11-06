package com.hrm.app.web.rest;

import com.hrm.app.domain.TotalAttendSalary;
import com.hrm.app.repository.TotalAttendSalaryRepository;
import com.hrm.app.service.TotalAttendSalaryService;
import com.hrm.app.service.dto.EmployeeAttendDTO;
import com.hrm.app.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api/total-attend-salaries")
public class TotalAttendSalaryResource {

    private static final Logger LOG = LoggerFactory.getLogger(TotalAttendSalaryResource.class);

    private static final String ENTITY_NAME = "totalAttendSalary";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TotalAttendSalaryService totalAttendSalaryService;

    private final TotalAttendSalaryRepository totalAttendSalaryRepository;

    public TotalAttendSalaryResource(
        TotalAttendSalaryService totalAttendSalaryService,
        TotalAttendSalaryRepository totalAttendSalaryRepository
    ) {
        this.totalAttendSalaryService = totalAttendSalaryService;
        this.totalAttendSalaryRepository = totalAttendSalaryRepository;
    }

    @PostMapping("")
    public ResponseEntity<TotalAttendSalary> createTotalAttendSalary(@RequestBody TotalAttendSalary totalAttendSalary)
        throws URISyntaxException {
        LOG.debug("REST request to save TotalAttendSalary : {}", totalAttendSalary);
        if (totalAttendSalary.getId() != null) {
            throw new BadRequestAlertException("A new totalAttendSalary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        totalAttendSalary = totalAttendSalaryService.save(totalAttendSalary);
        return ResponseEntity.created(new URI("/api/total-attend-salaries/" + totalAttendSalary.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, totalAttendSalary.getId().toString()))
            .body(totalAttendSalary);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TotalAttendSalary> updateTotalAttendSalary(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TotalAttendSalary totalAttendSalary
    ) throws URISyntaxException {
        LOG.debug("REST request to update TotalAttendSalary : {}, {}", id, totalAttendSalary);
        if (totalAttendSalary.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, totalAttendSalary.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!totalAttendSalaryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        totalAttendSalary = totalAttendSalaryService.update(totalAttendSalary);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, totalAttendSalary.getId().toString()))
            .body(totalAttendSalary);
    }

    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TotalAttendSalary> partialUpdateTotalAttendSalary(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TotalAttendSalary totalAttendSalary
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update TotalAttendSalary partially : {}, {}", id, totalAttendSalary);
        if (totalAttendSalary.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, totalAttendSalary.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!totalAttendSalaryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TotalAttendSalary> result = totalAttendSalaryService.partialUpdate(totalAttendSalary);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, totalAttendSalary.getId().toString())
        );
    }

    @GetMapping("")
    public List<TotalAttendSalary> getAllTotalAttendSalaries() {
        LOG.debug("REST request to get all TotalAttendSalaries");
        return totalAttendSalaryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TotalAttendSalary> getTotalAttendSalary(@PathVariable("id") Long id) {
        LOG.debug("REST request to get TotalAttendSalary : {}", id);
        Optional<TotalAttendSalary> totalAttendSalary = totalAttendSalaryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(totalAttendSalary);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTotalAttendSalary(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete TotalAttendSalary : {}", id);
        totalAttendSalaryService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

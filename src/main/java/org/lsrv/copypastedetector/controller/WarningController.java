package org.lsrv.copypastedetector.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.lsrv.copypastedetector.entity.Warning;
import org.lsrv.copypastedetector.service.WarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class WarningController {
    private final WarningService warningService;

    @PostMapping("/warning")
    public ResponseEntity<Warning> createWarning(@RequestBody Warning warning) {
        try {
            return new ResponseEntity<>(warningService.createWarning(warning), HttpStatus.CREATED);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/warning/{id}")
    public ResponseEntity<Warning> getWarning(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(warningService.getWarning(id), HttpStatus.OK);
        } catch (EntityNotFoundException enfe) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/warning/{id}")
    public ResponseEntity<Warning> updateWarning(@PathVariable Long id, @RequestBody Warning warning) {
        try {
            return new ResponseEntity<>(warningService.updateWarning(id, warning), HttpStatus.OK);
        } catch (EntityNotFoundException enfe) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/warning/{id}")
    public ResponseEntity<Void> deleteWarning(@PathVariable Long id) {
        try {
            warningService.deleteWarning(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException enfe) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/warning")
    public List<Warning> getAllWarnings() {
        return warningService.getAllWarnings();
    }

    @GetMapping("/session/{id}/warning")
    public List<Warning> getWarningsBySessionId(@PathVariable Long id) {
        return warningService.getWarningsBySessionId(id);
    }
}

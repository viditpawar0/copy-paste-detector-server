package org.lsrv.copypastedetector.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.lsrv.copypastedetector.entity.Warning;
import org.lsrv.copypastedetector.repo.WarningRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WarningService {
    private final WarningRepository warningRepository;

    public Warning createWarning(Warning warning) {
        if (warning.getId() != null) {
            throw new IllegalArgumentException("Warning id is already set!");
        }
        return warningRepository.save(warning);
    }

    public Warning getWarning(Long id) {
        return warningRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Warning with id " + id + " does not exist!"));
    }

    public Warning updateWarning(Long id, Warning warning) {
        checkWarningExists(id);
        warning.setId(id);
        return warningRepository.save(warning);
    }

    public void deleteWarning(Long id) {
        checkWarningExists(id);
        warningRepository.deleteById(id);
    }

    public List<Warning> getAllWarnings() {
        return warningRepository.findAll();
    }

    public List<Warning> getWarningsBySessionId(Long sessionId) {
        return warningRepository.findWarningsBySession_Id(sessionId);
    }

    private void checkWarningExists(Long id) {
        if (!warningRepository.existsById(id)) {
            throw new EntityNotFoundException("Warning with id " + id + " does not exist!");
        }
    }
}

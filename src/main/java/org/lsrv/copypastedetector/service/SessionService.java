package org.lsrv.copypastedetector.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.lsrv.copypastedetector.entity.Session;
import org.lsrv.copypastedetector.repo.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;

    public Session createSession(Session session) {
        if (session.getId() != null) {
            throw new IllegalArgumentException("Session id is already set!");
        }
        return sessionRepository.save(session);
    }

    public Session getSession(Long id) {
        return sessionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Session with id " + id + " not found"));
    }

    public Session updateSession(Long id, Session session) {
        checkSessionExists(id);
        session.setId(id);
        return sessionRepository.save(session);
    }

    public void deleteSession(Long id) {
        checkSessionExists(id);
        sessionRepository.deleteById(id);
    }

    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    private void checkSessionExists(Long id) {
        if (!sessionRepository.existsById(id)) {
            throw new EntityNotFoundException("Session with id " + id + " not found");
        }
    }
}

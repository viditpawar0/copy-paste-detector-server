package org.lsrv.copypastedetector.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lsrv.copypastedetector.entity.Session;
import org.lsrv.copypastedetector.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
public class SessionController {
    private final SessionService sessionService;

    @PostMapping("/session")
    public ResponseEntity<Session> createSession(@RequestBody Session session) {
        try {
            return new ResponseEntity<>(sessionService.createSession(session), HttpStatus.CREATED);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/session/{id}")
    public ResponseEntity<Session> getSession(@PathVariable Long id) {
        System.out.println(sessionService.getSession(id).getEndsAt());
        try {
            return new ResponseEntity<>(sessionService.getSession(id), HttpStatus.OK);
        } catch (EntityNotFoundException enfe) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/session/{id}")
    public ResponseEntity<Session> updateSession(@PathVariable Long id, @RequestBody Session session) {
        try {
            return new ResponseEntity<>(sessionService.updateSession(id, session), HttpStatus.OK);
        } catch (EntityNotFoundException enfe) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/session/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        try {
            sessionService.deleteSession(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException enfe) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/session")
    public List<Session> getAllSessions() {
        return sessionService.getAllSessions();
    }
}

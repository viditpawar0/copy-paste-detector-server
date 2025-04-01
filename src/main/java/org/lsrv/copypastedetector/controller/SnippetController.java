package org.lsrv.copypastedetector.controller;

import jakarta.persistence.EntityNotFoundException;
import org.lsrv.copypastedetector.entity.Snippet;
import org.lsrv.copypastedetector.service.SnippetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SnippetController {
    SnippetService snippetService;

    @Autowired
    public SnippetController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    @PostMapping("/snippet")
    public ResponseEntity<Snippet> createSnippet(@RequestBody Snippet snippet) {
        try {
            return new ResponseEntity<>(snippetService.createSnippet(snippet), HttpStatus.CREATED);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/snippet/{id}")
    public ResponseEntity<Snippet> getSnippet(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(snippetService.getSnippet(id), HttpStatus.OK);
        } catch (EntityNotFoundException enfe) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/snippet/{id}")
    public ResponseEntity<Snippet> updateSnippet(@PathVariable Long id, @RequestBody Snippet snippet) {
        try {
            return new ResponseEntity<>(snippetService.updateSnippet(id, snippet), HttpStatus.OK);
        } catch (EntityNotFoundException enfe) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/snippet/{id}")
    public ResponseEntity<Void> deleteSnippet(@PathVariable Long id) {
        try {
            snippetService.deleteSnippet(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException enfe) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/snippet")
    public List<Snippet> getAllSnippets() {
        return snippetService.getAllSnippets();
    }

    @GetMapping("/session/{id}/snippet")
    public List<Snippet> getSnippetsBySessionId(@PathVariable Long id) {
        return snippetService.getSnippetsBySessionId(id);
    }
}

package org.lsrv.copypastedetector.service;

import jakarta.persistence.EntityNotFoundException;
import org.lsrv.copypastedetector.entity.Snippet;
import org.lsrv.copypastedetector.repo.SnippetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnippetService {
    private final SnippetRepository snippetRepository;

    @Autowired
    public SnippetService(SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository;
    }

    public Snippet createSnippet(Snippet snippet) {
        if (snippet.getId() != null) {
            throw new IllegalArgumentException("Snippet id is already set!");
        }
        return snippetRepository.save(snippet);
    }

    public Snippet getSnippet(Long id) {
        return snippetRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Snippet with id " + id + " does not exist!"));
    }

    public Snippet updateSnippet(Long id, Snippet snippet) {
        checkSnippetExists(id);
        snippet.setId(id);
        return snippetRepository.save(snippet);
    }

    public void deleteSnippet(Long id) {
        checkSnippetExists(id);
        snippetRepository.deleteById(id);
    }

    public List<Snippet> getAllSnippets() {
        return snippetRepository.findAll();
    }

    public List<Snippet> getSnippetsBySessionId(Long sessionId) {
        return snippetRepository.findSnippetsBySession_Id(sessionId);
    }

    private void checkSnippetExists(Long id) {
        if (!snippetRepository.existsById(id)) {
            throw new EntityNotFoundException("Snippet with id " + id + " does not exist!");
        }
    }
}

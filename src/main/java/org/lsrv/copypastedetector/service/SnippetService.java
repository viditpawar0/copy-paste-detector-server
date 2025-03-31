package org.lsrv.copypastedetector.service;

import org.lsrv.copypastedetector.repo.SnippetRepository;
import org.springframework.stereotype.Service;

@Service
public class SnipperService {
    private final SnippetRepository snippetRepository;

    public SnipperService(SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository;
    }
}

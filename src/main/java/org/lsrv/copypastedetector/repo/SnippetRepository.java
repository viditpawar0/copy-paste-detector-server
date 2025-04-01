package org.lsrv.copypastedetector.repo;

import org.lsrv.copypastedetector.entity.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SnippetRepository extends JpaRepository<Snippet, Long> {
    List<Snippet> findSnippetsBySession_Id(Long sessionId);
}

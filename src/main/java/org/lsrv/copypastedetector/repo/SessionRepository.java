package org.lsrv.copypastedetector.repo;

import org.lsrv.copypastedetector.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}

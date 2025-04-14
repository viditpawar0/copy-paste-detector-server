package org.lsrv.copypastedetector.repo;

import org.lsrv.copypastedetector.entity.Warning;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarningRepository extends JpaRepository<Warning, Long> {
    List<Warning> findWarningsBySession_Id(Long sessionId);
}

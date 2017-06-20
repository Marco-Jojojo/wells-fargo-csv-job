package com.peiwc.billing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peiwc.billing.domain.WFMamErrLog;

/**
 * repository DAO for {@link WFMamErrLog}
 */
@Repository
public interface WFMamErrLogRepository extends JpaRepository<WFMamErrLog, Integer> {

}

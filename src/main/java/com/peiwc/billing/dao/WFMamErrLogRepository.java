package com.peiwc.billing.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.peiwc.billing.domain.WFMamErrLog;

/**
 * repository DAO for {@link WFMamErrLog}
 */
@Repository
public interface WFMamErrLogRepository extends JpaRepository<WFMamErrLog, Integer> {

	@Query("select w from WFMamErrLog w where w.cycleNumber = :cycleNumber")
	List<WFMamErrLog> getErrorsFromCycleNumber(@Param("cycleNumber") int cycleNumber);

}

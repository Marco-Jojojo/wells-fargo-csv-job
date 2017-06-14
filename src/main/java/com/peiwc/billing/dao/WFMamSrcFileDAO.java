package com.peiwc.billing.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.peiwc.billing.domain.WFMamSrcFile;

/**
 * DAO For retrieving {@link WFMamSrcFile } .
 */
@Repository("wfMamSrcFileDAO")
public interface WFMamSrcFileDAO extends JpaRepository<WFMamSrcFile, Integer> {

	/**
	 * find by cycle Number
	 *
	 * @param cycleNumber
	 * @return a list of {@link WFMamSrcFile} by cycle number
	 */
	@Query("select w from WFMamSrcFile w where w.id.cycleNumber = :cycleNumber")
	List<WFMamSrcFile> findByCycleNumber(@Param("cycleNumber") int cycleNumber);

}

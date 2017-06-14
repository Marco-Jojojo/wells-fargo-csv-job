package com.peiwc.billing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peiwc.billing.domain.WFMamOpHDRTRLR;

/**
 * repository for {@link WFMamOpHDRTRLR}
 */
@Repository("wfMamOpHDRTRLRRepository")
public interface WFMamOpHDRTRLRRepository extends JpaRepository<WFMamOpHDRTRLR, Integer> {

}

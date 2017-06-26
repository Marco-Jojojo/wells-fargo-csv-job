package com.peiwc.billing.process.billing1;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.peiwc.billing.domain.WFMamSrcFile;

public class SrcFileMapperForTwoYearsPolicies implements RowMapper<WFMamSrcFile>{

	@Override
	public WFMamSrcFile mapRow(ResultSet rs, int rowNumber) throws SQLException {
		WFMamSrcFile wfMamSrcFile = new WFMamSrcFile();
		wfMamSrcFile.setReferenceNumber(Integer.toString(rs.getInt("POLICY_NUMBER")));
		wfMamSrcFile.setSecondaryAuth(Integer.toString(rs.getInt("SUBMISSION_NUMBER")));
		return wfMamSrcFile;
	}

}

package com.peiwc.billing.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.peiwc.billing.domain.WFMamSrcFile;
import com.peiwc.billing.domain.WFMamSrcFilePK;

public class SrcFileMapperForApplyCredits implements RowMapper<WFMamSrcFile> {

	@Override
	public WFMamSrcFile mapRow(final ResultSet rs, final int rowNumber) throws SQLException {
		final WFMamSrcFile wfMamSrcFile = new WFMamSrcFile();
		final WFMamSrcFilePK id = new WFMamSrcFilePK();
		id.setCycleNumber(rs.getInt("CYCLE_NUMBER"));
		wfMamSrcFile.setId(id);
		wfMamSrcFile.setSecondaryAuth(Integer.toString(rs.getInt("SECONDARY_AUTH")));
		wfMamSrcFile.setAmountDue(rs.getFloat("AMOUNT_DUE"));
		return wfMamSrcFile;
	}

}

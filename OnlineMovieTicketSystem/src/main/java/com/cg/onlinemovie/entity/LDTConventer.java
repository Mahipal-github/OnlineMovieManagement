package com.cg.onlinemovie.entity;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


/******************************************************************************************************************************
 * @author : Mahipal Reddy
 * Description : This is the LDTConverter class where it will convert local dates into sql dates and vice versa for all Modules.
 * Version : 1.0 
 * Created Date : 04-SEP-2020
*******************************************************************************************************************************/

@Converter(autoApply = true)
public class LDTConventer implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate ldt) {
		if (ldt != null)
			return Date.valueOf(ldt); // convert LocalDate into java.sql.Date
		return null;
	}

	@Override
	public LocalDate convertToEntityAttribute(Date sqldt) {
		if (sqldt != null)
			return sqldt.toLocalDate();
		return null;
	}

}

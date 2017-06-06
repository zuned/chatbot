/**
 * All rights reserved.hCentive.
 */
package com.hcentive.hackathon.commons.morphia;

import java.math.BigDecimal;

import org.mongodb.morphia.converters.SimpleValueConverter;
import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.mapping.MappedField;
import org.mongodb.morphia.mapping.MappingException;

/**
 * @author Nitin.Gupta
 * 
 */
public class BigDecimalConverter extends TypeConverter implements
		SimpleValueConverter {

	public BigDecimalConverter() {
		super(BigDecimal.class);
	}

	@Override
	public Object encode(Object value, MappedField optionalExtraInfo) {
		return value.toString();
	}

	@Override
	public Object decode(Class targetClass, Object fromDBObject,
			MappedField optionalExtraInfo) throws MappingException {
		if (fromDBObject == null)
			return null;
		try {
			BigDecimal bigDecimal = new BigDecimal(fromDBObject.toString());
		} catch (Exception e) {
			System.out.println("*****" + fromDBObject.toString() + "*****");
			return null;
		}

		return new BigDecimal(fromDBObject.toString());
	}
}
package com.hcentive.hackathon.commons.utils;

import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Map;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

/**
 * Utils for writing out a CSV file
 * 
 * @author Nitin Gupta
 * 
 */
public class CsvWritingUtils {

	public static void writeToOutputStream(String filePath,
			Collection<? extends Object> beans, String[] header) throws Exception {
		ICsvBeanWriter beanWriter = null;

		try {

			beanWriter = new CsvBeanWriter(new FileWriter(filePath),
					CsvPreference.STANDARD_PREFERENCE);

			// write the header
			beanWriter.writeHeader(header);

			// write the beans
			for (final Object object : beans) {
				beanWriter.write(object, header);
			}
		} finally {
			if (beanWriter != null) {
				beanWriter.close();
			}
		}
	}

	public static void writeMapToOutputStream(String filePath,
			Collection<Map<String, String>> beans, String[] header,
			CellProcessor[] processors, CsvPreference preference)
			throws Exception {
		ICsvMapWriter mapWriter = null;

		try {

			mapWriter = new CsvMapWriter(new FileWriter(filePath), preference);

			// write the header
			mapWriter.writeHeader(header);

			// write the beans
			for (final Map<String, String> object : beans) {
				mapWriter.write(object, header, processors);
			}
		} finally {
			if (mapWriter != null) {
				mapWriter.close();
			}
		}
	}
	
	public static void writeToOutputStream(OutputStream os,
			Collection<? extends Object> beans, String[] header) throws Exception {
		
		ICsvBeanWriter beanWriter = null;

		try {

			beanWriter = new CsvBeanWriter(new OutputStreamWriter(os),
					CsvPreference.STANDARD_PREFERENCE);

			// write the header
			beanWriter.writeHeader(header);

			// write the beans
			for (final Object object : beans) {
				beanWriter.write(object, header);
			}
		} finally {
			if (beanWriter != null) {
				beanWriter.close();
			}
		}
		
		
	}
}

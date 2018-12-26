/**
 * 
 */
package com.shab.artificon.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.shab.artificon.model.ProblemDto;

/**
 * @author zentere
 *
 */
public class CSVReaderUtils {

	@SuppressWarnings("deprecation")
	public static List<ProblemDto> convertCsvToBean(String path) throws FileNotFoundException {

		Map<String, String> mapping = new HashMap<String, String>();
		mapping.put("problem", "problem");
		mapping.put("categories", "categories");
		mapping.put("solution", "solution");

		HeaderColumnNameTranslateMappingStrategy<ProblemDto> strategy = new HeaderColumnNameTranslateMappingStrategy<ProblemDto>();
		strategy.setType(ProblemDto.class);
		strategy.setColumnMapping(mapping);

		CSVReader csvReader = new CSVReader(new FileReader(path));
		CsvToBean<ProblemDto> csvToBean = new CsvToBean<ProblemDto>();
		List<ProblemDto> list = csvToBean.parse(strategy, csvReader);
		return list;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		List<ProblemDto> problems = convertCsvToBean("/home/zentere/Downloads/Problem.csv");
		System.out.println(problems.size());
	}
}

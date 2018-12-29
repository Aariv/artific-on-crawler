/**
 * 
 */
package com.shab.artificon.resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shab.artificon.model.ProblemDto;
import com.shab.artificon.repository.ProblemRepository;
import com.shab.artificon.utils.CSVReaderUtils;

/**
 * @author zentere
 *
 */
@RestController
@RequestMapping("/api/")
public class ProblemResource {

	@Autowired
	private ProblemRepository problemRepository;
	
	@PostMapping("/uploadCsv")
	public String uploadDocxFile(@RequestParam("file") MultipartFile file) throws FileNotFoundException, IOException {
		List<ProblemDto> problems = CSVReaderUtils.convertCsvToBean(convert(file).getAbsolutePath());
		problemRepository.saveAll(problems);
		return "Success";
	}

	@GetMapping("problems")
	public List<ProblemDto> getAllProblems() {
		List<ProblemDto> target = new ArrayList<ProblemDto>();
		problemRepository.findAll().forEach(target::add);
		return target;
	}
	
	@GetMapping("problems/{problemName}")
	public List<String> getInstallationStepsByProblem(@PathVariable String problemName) {
		List<ProblemDto> problems = problemRepository.findByProblem(problemName.trim());
		List<String> steps = new ArrayList<>();
		for (ProblemDto problemDto : problems) {
			steps.add(problemDto.getSolution());
		}
		return steps;
	}
	
	@DeleteMapping("problems")
	public String deleteAllProblem() {
		problemRepository.deleteAll();
		return "Success";
	}
	
	public static File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}
}

/**
 * 
 */
package com.shab.artificon.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shab.artificon.model.ProblemDto;
import com.shab.artificon.repository.ProblemRepository;

/**
 * @author zentere
 *
 */
@RestController
@RequestMapping("/api/")
public class ProblemResource {

	@Autowired
	private ProblemRepository problemRepository;

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
}

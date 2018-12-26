/**
 * 
 */
package com.shab.artificon.repository;

import org.springframework.data.repository.CrudRepository;

import com.shab.artificon.model.ProblemDto;
import java.lang.String;
import java.util.List;

/**
 * @author zentere
 *
 */
public interface ProblemRepository extends CrudRepository<ProblemDto, Integer> {

	List<ProblemDto> findByProblem(String problem);
}

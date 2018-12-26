package com.shab.artificon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_problem")
public class ProblemDto {
	@Id
	@GeneratedValue
	private Integer id;
	public String problem;
	public String categories;
	public String solution;

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	@Override
	public String toString() {
		return "ProblemDto [problem=" + problem + ", categories=" + categories + ", solution=" + solution + "]";
	}

}
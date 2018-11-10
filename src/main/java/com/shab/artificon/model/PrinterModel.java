/**
 * 
 */
package com.shab.artificon.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author zentere
 *
 */
@Entity
@Table(name = "tbl_printer_model")
public class PrinterModel {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	@ElementCollection(targetClass = String.class)
	private List<String> steps;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "printer_id")
	private Printer printer;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the steps
	 */
	public List<String> getSteps() {
		return steps;
	}

	/**
	 * @param steps
	 *            the steps to set
	 */
	public void setSteps(List<String> steps) {
		this.steps = steps;
	}

	/**
	 * @return the printer
	 */
	public Printer getPrinter() {
		return printer;
	}

	/**
	 * @param printer
	 *            the printer to set
	 */
	public void setPrinter(Printer printer) {
		this.printer = printer;
	}

}

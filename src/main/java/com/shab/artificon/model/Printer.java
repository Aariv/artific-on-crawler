/**
 * 
 */
package com.shab.artificon.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author zentere
 *
 */
@Entity
@Table(name = "tbl_printer")
public class Printer {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	@JsonManagedReference
	@OneToMany(mappedBy = "printer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PrinterModel> printerModels = new ArrayList<PrinterModel>();

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
	 * @return the printerModels
	 */
	public List<PrinterModel> getPrinterModels() {
		return printerModels;
	}

	/**
	 * @param printerModels
	 *            the printerModels to set
	 */
	public void setPrinterModels(List<PrinterModel> printerModels) {
		this.printerModels = printerModels;
	}

}

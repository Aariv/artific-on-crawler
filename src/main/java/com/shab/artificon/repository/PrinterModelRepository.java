/**
 * 
 */
package com.shab.artificon.repository;

import org.springframework.data.repository.CrudRepository;

import com.shab.artificon.model.PrinterModel;

/**
 * @author zentere
 *
 */
public interface PrinterModelRepository extends CrudRepository<PrinterModel, Integer> {

	PrinterModel findByName(String name);
}

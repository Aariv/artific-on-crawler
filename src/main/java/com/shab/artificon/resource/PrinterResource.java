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

import com.shab.artificon.model.Printer;
import com.shab.artificon.repository.PrinterRepository;

/**
 * @author zentere
 *
 */
@RestController
@RequestMapping("/api/")
public class PrinterResource {

	@Autowired
	private PrinterRepository printerRepository;

	@GetMapping("printers")
	public List<Printer> getAllPrinters() {
		List<Printer> target = new ArrayList<Printer>();
		printerRepository.findAll().forEach(target::add);
		return target;
	}

	@GetMapping("printers/{name}")
	public Printer getPrintersByName(@PathVariable Integer id) {
		return printerRepository.findById(id).get();
	}
}

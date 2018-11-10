/**
 * 
 */
package com.shab.artificon.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shab.artificon.model.Printer;
import com.shab.artificon.model.PrinterModel;
import com.shab.artificon.repository.PrinterModelRepository;
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
	
	@Autowired
	private PrinterModelRepository printerModelRepository;

	@GetMapping("printers")
	public List<Printer> getAllPrinters() {
		List<Printer> target = new ArrayList<Printer>();
		printerRepository.findAll().forEach(target::add);
		return target;
	}
	
	@GetMapping("printers/{name}")
	public List<String> getPrintersByName(@PathVariable String name) {
		Function<List<PrinterModel>, List<String>> models = (modelsFromDb) -> {
			List<String> resultSet = new ArrayList<>();
			for (PrinterModel printerModel : modelsFromDb) {
				resultSet.add(printerModel.getName());
			}
			return resultSet;
		};
		List<String> resultSet = models.apply(printerRepository.findByName(name).getPrinterModels());
		
		return resultSet;
	}
	
	@GetMapping("printersInstallationSteps/{modelName}")
	public List<String> getInstallationStepsByModel(@PathVariable String modelName) {
		return printerModelRepository.findByName(modelName.trim()).getSteps();
	}
}

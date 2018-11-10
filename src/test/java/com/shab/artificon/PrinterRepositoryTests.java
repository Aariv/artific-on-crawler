package com.shab.artificon;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shab.artificon.model.Printer;
import com.shab.artificon.model.PrinterModel;
import com.shab.artificon.repository.PrinterRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrinterRepositoryTests {
	
	@Autowired
	private PrinterRepository printerRepository;

	
	@Test
	public void insertPrinter() {
		Printer printer = new Printer();
		printer.setName("HP");

		PrinterModel hp_OfficeJet_8600 = new PrinterModel();
		hp_OfficeJet_8600.setName("Hp_OfficeJet_8600");

		List<String> steps = new ArrayList<>();
		steps.add("");
		steps.add("");
		
		hp_OfficeJet_8600.setSteps(steps);

		printer.getPrinterModels().add(hp_OfficeJet_8600);

		printerRepository.save(printer);

		Printer dbPrinter = printerRepository.findById(printer.getId()).get();

		assertNotNull(dbPrinter);
		assertNotNull(dbPrinter.getPrinterModels());

		List<PrinterModel> models = dbPrinter.getPrinterModels();

		assertTrue(models.contains(hp_OfficeJet_8600));
	}
}

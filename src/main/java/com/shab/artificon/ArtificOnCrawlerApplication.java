package com.shab.artificon;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.shab.artificon.model.Printer;
import com.shab.artificon.model.PrinterModel;
import com.shab.artificon.repository.PrinterRepository;

@SpringBootApplication
public class ArtificOnCrawlerApplication implements CommandLineRunner {

	@Autowired
	private PrinterRepository printerRepository;

	public static void main(String[] args) {
		SpringApplication.run(ArtificOnCrawlerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Printer printer = new Printer();
		printer.setName("HP");

		PrinterModel hp_OfficeJet_8600 = new PrinterModel();
		hp_OfficeJet_8600.setName("Hp_OfficeJet_8600");
		
		hp_OfficeJet_8600.setPrinter(printer);

		List<String> steps = new ArrayList<>();
		steps.add("Review this document for detailed technical data, such as model numbers");
		steps.add("system requirements, print speeds, connectivity types,");

		hp_OfficeJet_8600.setSteps(steps);

		printer.getPrinterModels().add(hp_OfficeJet_8600);

		printerRepository.save(printer);

		Printer dbPrinter = printerRepository.findById(printer.getId()).get();
		
		System.out.println(dbPrinter.toString());
	}
}

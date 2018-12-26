package com.shab.artificon;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.shab.artificon.model.Printer;
import com.shab.artificon.model.PrinterModel;
import com.shab.artificon.model.ProblemDto;
import com.shab.artificon.repository.PrinterRepository;
import com.shab.artificon.repository.ProblemRepository;
import com.shab.artificon.utils.CSVReaderUtils;

@SpringBootApplication
public class ArtificOnCrawlerApplication implements ApplicationRunner {

	@Autowired
	private PrinterRepository printerRepository;
	
	@Autowired
	private ProblemRepository problemRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ArtificOnCrawlerApplication.class, args);
	}

	private List<PrinterModel> createHpPrinterModels(Printer printer) {
		List<String> printerModels = new ArrayList<>();
		printerModels.add("PageWide Enterprise 6");
		printerModels.add("Officejet Pro 9");
		printerModels.add("Officejet 4");
		printerModels.add("LaserJet Pro 3");
		printerModels.add("LaserJet 116");
		printerModels.add("Deskjet Ink Advantage 5");
		printerModels.add("Deskjet 10");
		printerModels.add("DesignJet 10");
		List<PrinterModel> models = new ArrayList<>(); 
		for (String string : printerModels) {
			PrinterModel pModel = new PrinterModel();
			pModel.setName(string);
			pModel.setPrinter(printer);
			pModel.setSteps(createStepsforPrinterModel());
			models.add(pModel);
		}
		return models;
	}

	private List<String> createStepsforPrinterModel() {
		List<String> steps = new ArrayList<>();
		steps.add("Click Connect a new printer.");
		steps.add("Select the connection type when prompted, and then follow the on-screen instructions to set up the printer.");
		steps.add("Turn off the printer, and then restart your computer.");
		steps.add("Turn on the printer, and then open HP Printer Assistant.");
		return steps;
	}
	
	private List<String> createStepsforOfficeJet() {
		List<String> steps = new ArrayList<>();
		steps.add("Review this document for detailed technical data, such as model numbers");
		steps.add("system requirements, print speeds, connectivity types,");
		return steps;
	}
	
	private List<String> createStepsforOfficeJet4() {
		List<String> steps = new ArrayList<>();
		steps.add("Review this document for detailed technical data, such as model numbers");
		steps.add("system requirements, print speeds, connectivity types,");
		return steps;
	}
	
	private List<String> createStepsforLaserJet() {
		List<String> steps = new ArrayList<>();
		steps.add("Review this document for detailed technical data, such as model numbers");
		steps.add("system requirements, print speeds, connectivity types,");
		return steps;
	}
	
	private List<String> createStepsforDeskjet() {
		List<String> steps = new ArrayList<>();
		steps.add("Review this document for detailed technical data, such as model numbers");
		steps.add("system requirements, print speeds, connectivity types,");
		return steps;
	}
	
	private List<String> createStepsforDeskjet10() {
		List<String> steps = new ArrayList<>();
		steps.add("Review this document for detailed technical data, such as model numbers");
		steps.add("system requirements, print speeds, connectivity types,");
		return steps;
	}
	
	
	private List<String> createStepsforDesignJet10() {
		List<String> steps = new ArrayList<>();
		steps.add("Review this document for detailed technical data, such as model numbers");
		steps.add("system requirements, print speeds, connectivity types,");
		return steps;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		for (String name : args.getOptionNames()){
			System.out.println("arg-" + name + "=" + args.getOptionValues(name));
			String filePath = args.getOptionValues(name).get(0);
			if(filePath != null) {
				System.out.println("File Path is " + filePath);
				List<ProblemDto> problems = CSVReaderUtils.convertCsvToBean(filePath);
				problemRepository.saveAll(problems);
			}
		}
		
	}
}

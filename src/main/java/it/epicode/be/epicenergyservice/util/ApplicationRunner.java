package it.epicode.be.epicenergyservice.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import it.epicode.be.epicenergyservice.model.Comune;
import it.epicode.be.epicenergyservice.model.Provincia;
import it.epicode.be.epicenergyservice.repository.ComuneRepository;
import it.epicode.be.epicenergyservice.repository.ProvinciaRepository;

@Component
public class ApplicationRunner implements CommandLineRunner {

	@Autowired
	ComuneRepository comuneRepository;

	@Autowired
	ProvinciaRepository provinciaRepository;

	@Override
	public void run(String... args) throws Exception {

		CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
		try (CSVReader reader = new CSVReaderBuilder(new FileReader("provinceitaliane.csv")).withCSVParser(csvParser)
				.withSkipLines(1).build()) {
			String[] values = null;
			List<Provincia> province = provinciaRepository.findAll();

			if (province.isEmpty()) {
				while ((values = reader.readNext()) != null) {
					Provincia provincia = new Provincia();
					provincia.setSigla(values[0]);
					provincia.setNome(values[1]);
					provincia.setRegione(values[2]);
					provinciaRepository.save(provincia);

				}
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		CSVParser csvParser1 = new CSVParserBuilder().withSeparator(';').build();
		try (CSVReader reader1 = new CSVReaderBuilder(new FileReader("comuni-italiani.csv")).withCSVParser(csvParser1)
				.withSkipLines(1).build()) {
			String[] values1 = null;
			List<Provincia> province = provinciaRepository.findAll();
			List<Comune> comuni = comuneRepository.findAll();

			if (comuni.isEmpty()) {
				while ((values1 = reader1.readNext()) != null) {
					Comune comune = new Comune();
					for (Provincia p : province) {
						if (p.getNome().equals(values1[3]) && !values1[3].isBlank()) {
							comune.setNome(values1[2]);
							comune.setProvincia(p);
							comuneRepository.save(comune);

						}
					}

				}

			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}

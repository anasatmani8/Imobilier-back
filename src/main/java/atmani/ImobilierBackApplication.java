package atmani;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import atmani.model.Imobilier;
import atmani.repository.ImobilierRepo;

@SpringBootApplication
public class ImobilierBackApplication implements CommandLineRunner {

	@Autowired
	ImobilierRepo imobilierRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(ImobilierBackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		imobilierRepo.save(new Imobilier("Maison", "mzyana w kbira",true));
		imobilierRepo.save(new Imobilier("villa", "mzyana w kbira w tfat7 n3am a sidi",true));
		imobilierRepo.save(new Imobilier("appartement", "mzyana w 9adia l mowdafin",true));
		
		
	}

}

package atmani;

//import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*import atmani.model.Achat;
import atmani.model.Type;*/
//import atmani.model.User;
import atmani.repository.ImobilierRepo;
import atmani.repository.UserRepo;

@SpringBootApplication
public class ImobilierBackApplication implements CommandLineRunner {

	@Autowired
	ImobilierRepo imobilierRepo;
	
	@Autowired
	UserRepo userRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(ImobilierBackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {	
		
		System.out.println(imobilierRepo.findById(8).toString());
		
		
	}

}

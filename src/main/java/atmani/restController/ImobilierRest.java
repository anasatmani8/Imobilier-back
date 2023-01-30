package atmani.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import atmani.model.Imobilier;
import atmani.repository.ImobilierRepo;

@RequestMapping(path = "/imobilier")
@RestController
@CrossOrigin("*")
public class ImobilierRest {
	
	@Autowired
	ImobilierRepo imobilierRepo;
	
	@GetMapping(path="/get")
	public List<Imobilier> get(@RequestParam(required = false) String filterValue) {
		return imobilierRepo.findAll();
	}

}

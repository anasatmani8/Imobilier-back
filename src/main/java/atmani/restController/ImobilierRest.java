package atmani.restController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import atmani.constents.ImobilierConstents;
import atmani.model.Imobilier;
import atmani.repository.ImobilierRepo;
import atmani.services.ImobilierService;
import atmani.utils.CafeUtils;

@RequestMapping(path = "/imobilier")
@RestController
@CrossOrigin("*")
public class ImobilierRest {
	
	@Autowired
	ImobilierRepo imobilierRepo;
	
	@Autowired
	ImobilierService imobilierService;
	
	@GetMapping(path="/get")
	public List<Imobilier> get(@RequestParam(required = false) String filterValue) {
		return imobilierRepo.findAll();
	}
	
	@PostMapping(path="/add")
	public ResponseEntity<String> addImobilier(@RequestBody(required = true) Map<String, String> requestMap){
		try {
			return imobilierService.addImobilier(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(ImobilierConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(path="/delete/{id}")

	ResponseEntity<String> delete(@PathVariable Integer id){
		try {
			return imobilierService.deleteImobilier(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(ImobilierConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

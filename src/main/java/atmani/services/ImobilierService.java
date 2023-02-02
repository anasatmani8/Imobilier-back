package atmani.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import atmani.model.Achat;
import atmani.model.Location;

public interface ImobilierService {

	ResponseEntity<String> addImobilier(Map<String, String> requestMap);

	ResponseEntity<String> deleteImobilier(Integer id);

	ResponseEntity<List<Location>> getAllLocations();

	ResponseEntity<List<Achat>> getAllAchats();

}

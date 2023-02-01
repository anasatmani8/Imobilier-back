package atmani.services;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface ImobilierService {

	ResponseEntity<String> addImobilier(Map<String, String> requestMap);

	ResponseEntity<String> deleteImobilier(Integer id);

}

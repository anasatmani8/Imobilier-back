package atmani.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import atmani.model.Achat;
import atmani.model.Location;

public interface ImobilierService {

	ResponseEntity<String> addImobilier(Map<String, String> requestMap, MultipartFile[] files);

	ResponseEntity<String> deleteImobilier(Integer id);

	ResponseEntity<List<Location>> getAllLocations();

	ResponseEntity<List<Achat>> getAllAchats();

	ResponseEntity<String> updateStatus(Map<String, String> requestMap);

	ResponseEntity<String> updateAchat(Map<String, String> requrstMap);

	//ResponseEntity<List<Optional<?>>> getAchatDetail(int id);

}

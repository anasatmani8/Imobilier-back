package atmani.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import atmani.model.ImoAchat;

public interface ImoAchatService {

	ResponseEntity<List<ImoAchat>> getAllAchats();
	ResponseEntity<List<ImoAchat>> getAllAchatsAdmin();
	ResponseEntity<List<ImoAchat>> getAllLocationAdmin();
	ResponseEntity<List<ImoAchat>> getAllLocation();
	public ResponseEntity<String> deleteAchat(Integer id);
	public ImoAchat getAchatDetailsById(Integer id);
	ResponseEntity<String> updateStatus(Map<String, String> requestMap);
}

package atmani.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import atmani.model.Achat;
import atmani.model.ImoAchat;

public interface ImoAchatService {

	ResponseEntity<List<ImoAchat>> getAllAchats();
	ResponseEntity<List<ImoAchat>> getAllLocation();
	public ResponseEntity<String> deleteAchat(Integer id);
	public ImoAchat getAchatDetailsById(Integer id);
}

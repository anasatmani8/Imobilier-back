package atmani.servicesIMP;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import atmani.JWT.CustomerUsersDetailsService;
import atmani.JWT.JwtFilter;
import atmani.JWT.JwtUtil;
import atmani.constents.ImobilierConstents;
import atmani.model.Achat;
import atmani.model.Imobilier;
import atmani.model.Type;
import atmani.repository.ImobilierRepo;
import atmani.services.ImobilierService;
import atmani.utils.CafeUtils;
import atmani.utils.EmailUtils;
@Service
public class ImobilierServiceIMP implements ImobilierService {

	@Autowired
	ImobilierRepo imobilierRepo;
	
	@Autowired
	CustomerUsersDetailsService customerUsersDetailsService;

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	JwtFilter jwtFilter;

	@Autowired
	EmailUtils emailUtils;

	Logger log = (Logger) LoggerFactory.getLogger(UserServiceIMP.class);

	
	@Override
	public ResponseEntity<String> addImobilier(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		System.out.println("11");
		try {
			if (customerUsersDetailsService.getUserDetail().getRole().equalsIgnoreCase("admin")) {
				if (validateProductMap(requestMap, false)) {
					imobilierRepo.save(geImobiliertMap(requestMap, false));
					return CafeUtils.getResponseEntity("Imobilier Added Successfully", HttpStatus.OK);
				} else {
					return CafeUtils.getResponseEntity(ImobilierConstents.INVALID_DATA, HttpStatus.BAD_REQUEST);
				}
			} else {
				return CafeUtils.getResponseEntity(ImobilierConstents.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(ImobilierConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private boolean validateProductMap(Map<String, String> requestMap, boolean validateId) {
		if (requestMap.containsKey("title")) {
			if (requestMap.containsKey("id") && validateId) {
				return true;
			} else if (!validateId)
				return true;
		}
		return false;
	}
	
	private Imobilier geImobiliertMap(Map<String, String> requestMap, Boolean isAdd) {
		Achat achat = new Achat();
		achat.setDateAchat(new Date());
		achat.setAdresse(requestMap.get("adresse"));
		achat.setAvailable(true);
		achat.setDescription(requestMap.get("description"));
		achat.setPrice(Integer.parseInt(requestMap.get("price")));
		achat.setRooms(Integer.parseInt(requestMap.get("rooms")));
		achat.setSurface(Integer.parseInt(requestMap.get("surface")));
		achat.setTitle(requestMap.get("title"));
		achat.setType(Type.ACHAT);
		
		return achat;
	}

}

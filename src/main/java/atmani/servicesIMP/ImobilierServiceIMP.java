package atmani.servicesIMP;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import atmani.JWT.CustomerUsersDetailsService;
import atmani.JWT.JwtFilter;
import atmani.JWT.JwtUtil;
import atmani.constents.ImobilierConstents;
import atmani.model.Achat;
import atmani.model.AchatDetail;
import atmani.model.Imobilier;
import atmani.model.Location;
import atmani.model.Type;
import atmani.repository.ImobilierRepo;
import atmani.services.ImobilierService;
import atmani.utils.CafeUtils;
import atmani.utils.EmailUtils;


@Service
@Transactional
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
	
	@Override

	public ResponseEntity<String> deleteImobilier(Integer id) throws ObjectOptimisticLockingFailureException {

		System.out.println("12");
		try {
			if (customerUsersDetailsService.getUserDetail().getRole().equalsIgnoreCase("admin")) {
				Optional<Imobilier> optional = imobilierRepo.findById(id);
				if (optional.isPresent() == true) {
					System.out.println(optional.toString());
					System.out.println(customerUsersDetailsService.getUserDetail().getEmail()+" who made the operation");
					switch (optional.get().getType()) {
					case ACHAT:
						System.out.println("suppression d'achat"+id);
						imobilierRepo.deleteById(id);
						break;
						
					case LOCATION:
						System.out.println("suppression de location");
						imobilierRepo.deleteById(id); 
						break;
					}
					
					return CafeUtils.getResponseEntity("Imobilier deleted successfully", HttpStatus.OK);
				} else {
					return CafeUtils.getResponseEntity("Imobilier id not found :/", HttpStatus.OK);
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

		Imobilier imobilier = new Imobilier();
		imobilier.setAdresse(requestMap.get("adresse"));
		imobilier.setAvailable(requestMap.get("available"));
		imobilier.setDescription(requestMap.get("description"));
		imobilier.setPrice(Integer.parseInt(requestMap.get("price")));
		imobilier.setRooms(Integer.parseInt(requestMap.get("rooms")));
		imobilier.setSurface(Integer.parseInt(requestMap.get("surface")));
		imobilier.setTitle(requestMap.get("title"));
		imobilier.setType(Type.valueOf(requestMap.get("type")));

		Achat achat = null;
		Location location = null;

		switch (Type.valueOf(requestMap.get("type"))) {
		case ACHAT:
			System.out.println("case achat detected");
			achat = new Achat(requestMap.get("title"), requestMap.get("description"),
					Integer.parseInt(requestMap.get("price")), requestMap.get("available"), requestMap.get("adresse"),
					Integer.parseInt(requestMap.get("surface")), Integer.parseInt(requestMap.get("rooms")),
					Type.valueOf(requestMap.get("type")), new Date());

			return achat;
		case LOCATION:
			System.out.println("case location detected");
			location = new Location(requestMap.get("title"), requestMap.get("description"),
					Integer.parseInt(requestMap.get("price")), requestMap.get("available"), requestMap.get("adresse"),
					Integer.parseInt(requestMap.get("surface")), Integer.parseInt(requestMap.get("rooms")),
					Type.valueOf(requestMap.get("type")), new Date(), new Date());

		}
		if (Type.valueOf(requestMap.get("type")).equals(Type.ACHAT)) {
			return achat;
		} else {
			return location;
		}
	} 
	
	private Achat getAchat(Map<String, String> requestMap, Boolean isAdd) {

		Achat  imobilier = new Achat();
		imobilier.setAdresse(requestMap.get("adresse"));
		imobilier.setAvailable(requestMap.get("available"));
		imobilier.setDescription(requestMap.get("description"));
		imobilier.setPrice(Integer.parseInt(requestMap.get("price")));
		imobilier.setRooms(Integer.parseInt(requestMap.get("rooms")));
		imobilier.setSurface(Integer.parseInt(requestMap.get("surface")));
		imobilier.setTitle(requestMap.get("title"));
		imobilier.setType(Type.valueOf(requestMap.get("type")));
		imobilier.setDateAchat(new Date());
		

			return imobilier;
		
	} 

	@Override
	public ResponseEntity<List<Location>> getAllLocations() {
		try {
			return new ResponseEntity<>(imobilierRepo.getAllLocations(), HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Achat>> getAllAchats() {
		try {
			return new ResponseEntity<>(imobilierRepo.getAllAchat(), HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	public ResponseEntity<List<Optional<?>>> getAchatDetail(int id) {
		// TODO Auto-generated method stub
		try {
			return new ResponseEntity<>(imobilierRepo.getAllAchatDetail(id), HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
		try {
			if (customerUsersDetailsService.getUserDetail().getRole().equalsIgnoreCase("admin")) {
				Optional<Imobilier> optional = imobilierRepo.findById(Integer.parseInt(requestMap.get("id")));
				if (optional.isPresent() == true) {
					
					imobilierRepo.updateStatus(requestMap.get("available"), Integer.parseInt(requestMap.get("id")));
					return CafeUtils.getResponseEntity("Product Status updated successfully", HttpStatus.OK);
				} else {
					return CafeUtils.getResponseEntity("Product id not found :/", HttpStatus.OK);
				}
			} else {
				return CafeUtils.getResponseEntity(ImobilierConstents.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return CafeUtils.getResponseEntity(ImobilierConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateAchat(Map<String, String> requrstMap) {
		try {
			if (customerUsersDetailsService.getUserDetail().getRole().equalsIgnoreCase("admin")) {
				if (validateProductMap(requrstMap, true)) {
					System.out.println(requrstMap.get(("id")));
					Optional<Imobilier> product = imobilierRepo.findById(Integer.parseInt(requrstMap.get(("id"))));
					System.out.println(product.toString());
					if (product.isPresent() == true) {
						Achat productN = getAchat(requrstMap, true);
						System.out.println(productN.toString()+"achat");
						
						
							imobilierRepo.updateAchat(productN.getRooms(), productN.getAdresse(), productN.getAvailable(), productN.getDescription(),productN.getPrice(),
									productN.getSurface(), productN.getTitle(), new Date(), Integer.parseInt(requrstMap.get(("id"))));
							
						

						return CafeUtils.getResponseEntity("Product Updated Successfuly", HttpStatus.OK);
					} else {
						return CafeUtils.getResponseEntity("Product not found :/", HttpStatus.OK);
					}
				} else {
					return CafeUtils.getResponseEntity(ImobilierConstents.INVALID_DATA, HttpStatus.BAD_REQUEST);
				}
			}
			return CafeUtils.getResponseEntity(ImobilierConstents.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(ImobilierConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	

}

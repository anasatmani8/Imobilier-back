package atmani.servicesIMP;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import atmani.JWT.CustomerUsersDetailsService;

import atmani.constents.ImobilierConstents;
import atmani.model.ImoAchat;
import atmani.repository.ImoAchatRepo;
import atmani.services.ImoAchatService;
import atmani.utils.CafeUtils;

@Service
public class ImoAchatServiceIMP implements ImoAchatService {
	@Autowired
	ImoAchatRepo achatRepo;
	@Autowired
	CustomerUsersDetailsService customerUsersDetailsService;

	public ResponseEntity<String>  addAchat(ImoAchat achat) {
		try {
			if (customerUsersDetailsService.getUserDetail().getRole().equalsIgnoreCase("admin")) {
				System.out.println(achat);
				achatRepo.save(achat);
				return CafeUtils.getResponseEntity("Immobilier Added Successfully", HttpStatus.OK);
				
			}else {
				return CafeUtils.getResponseEntity(ImobilierConstents.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception ex) { 
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(ImobilierConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@Override
	public ResponseEntity<List<ImoAchat>> getAllAchats() {
		try {
			
			return new ResponseEntity<>(achatRepo.getAllAchat(), HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@Override
	public ResponseEntity<List<ImoAchat>> getAllAchatsAdmin() { 
		try {
			
			return new ResponseEntity<>(achatRepo.getAllAchatAdmin(), HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	public ResponseEntity<List<ImoAchat>> getAllLocationAdmin() { 
		try {
			
			return new ResponseEntity<>(achatRepo.getAllLocationAdmin(), HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> deleteAchat(Integer id) {
		// TODO Auto-generated method stub
		try {
			if (customerUsersDetailsService.getUserDetail().getRole().equalsIgnoreCase("admin")) {
				achatRepo.deleteById(id);
				System.out.println("delete done");
				return CafeUtils.getResponseEntity("Product deleted successfully", HttpStatus.OK);
			} else {
				return CafeUtils.getResponseEntity(ImobilierConstents.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(ImobilierConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ImoAchat getAchatDetailsById(Integer id) {
		// TODO Auto-generated method stub
		return achatRepo.findById(id).get();
	}
	@Override
	public ResponseEntity<List<ImoAchat>> getAllLocation() {
		try {
			
			return new ResponseEntity<>(achatRepo.getAllLocation(), HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@Override
	public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
		try {
			System.out.println("2");
			System.out.println(customerUsersDetailsService.getUserDetail().getRole().equalsIgnoreCase("admin")+" role");
			if (customerUsersDetailsService.getUserDetail().getRole().equalsIgnoreCase("admin")) {
				System.out.println("3");
				Optional<ImoAchat> optional = achatRepo.findById(Integer.parseInt(requestMap.get("id")));
				if (optional.isPresent() == true) {
					System.out.println(requestMap.get("available"));
					achatRepo.updateStatus(requestMap.get("available"), Integer.parseInt(requestMap.get("id")));
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
}

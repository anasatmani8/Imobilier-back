package atmani.restController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import atmani.constents.ImobilierConstents;

import atmani.model.ImageModel;
import atmani.model.ImoAchat;
import atmani.model.Type;
import atmani.repository.ImoAchatRepo;
import atmani.servicesIMP.ImoAchatServiceIMP;
import atmani.utils.CafeUtils;
import atmani.utils.EmailUtils;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/imoAchat")
public class ImoAchatRest {
	
	@Autowired
	ImoAchatServiceIMP achatService;
	
	@Autowired
	ImoAchatRepo achatRepo;
	
	@Autowired
	EmailUtils emailUtils;
	
	@PostMapping(path="/addAchat", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	ResponseEntity<String> addAchat(@RequestPart("imoAchat") ImoAchat achat, @RequestPart("file") MultipartFile[] files) {
		System.out.println("start");
		System.out.println(files);
		try {
			Set<ImageModel> images = uploadImage(files);
			achat.setImoAchatImages(images);
			achat.setType(Type.ACHAT);
			return achatService.addAchat(achat);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return CafeUtils.getResponseEntity(ImobilierConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	/*@PostMapping(path="/vente")
	ResponseEntity<String> vente(@RequestBody(required = true) Map<String, String> requestMap) {
		
		try {
			emailUtils.forgotMail("anasatmani8@gmail.com", requestMap.get("subject"), "test");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return CafeUtils.getResponseEntity(ImobilierConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}*/
	
	@PostMapping(path="/addLocation", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	ResponseEntity<String> addLocation(@RequestPart("imoAchat") ImoAchat achat, @RequestPart("file") MultipartFile[] files) {
		System.out.println("start");
		System.out.println(files);
		try {
			Set<ImageModel> images = uploadImage(files);
			achat.setImoAchatImages(images);
			achat.setType(Type.LOCATION);
			return achatService.addAchat(achat);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return CafeUtils.getResponseEntity(ImobilierConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	public Set<ImageModel> uploadImage(MultipartFile [] multipartFiles) throws IOException{
		Set<ImageModel> images = new HashSet<>();
		
		for (MultipartFile file : multipartFiles) {
			ImageModel image = new ImageModel(
					file.getBytes(),
					file.getOriginalFilename(),
					file.getContentType()
					);
			System.out.println(image+" image");
			images.add(image);
		}
		return images;
		
	}
	
	@GetMapping(path="/getAchatDetailsById/{id}")
	public ImoAchat getAchatDetailsById(@PathVariable("id") Integer id) {
		return achatService.getAchatDetailsById(id);
	}
	
	@GetMapping(path = "/achat")
	ResponseEntity<List<ImoAchat>> getAllAchat() {
		try {
			return achatService.getAllAchats();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(path = "/achatAdmin")
	ResponseEntity<List<ImoAchat>> getAllAchatAdmin() {
		try {
			return achatService.getAllAchatsAdmin();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(path = "/location")
	ResponseEntity<List<ImoAchat>> getAllLocation() {
		try {
			return achatService.getAllLocation();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(path="/delete/{id}")
	ResponseEntity<String> deleteAchat(@PathVariable("id") Integer id) {
		try {
			return achatService.deleteAchat(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(ImobilierConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(path = "/updateStatus")
	ResponseEntity<String> updateStatus(@RequestBody(required = true) Map<String, String> requestMap) {
		try {
			System.out.println("1");
			System.out.println(requestMap);
			int id = Integer.parseInt(requestMap.get("id"));
			ImoAchat achat = achatRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
			achat.setAvailable(requestMap.get("available"));
			achatRepo.save(achat);
			return CafeUtils.getResponseEntity("Status updated successfully", HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(ImobilierConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

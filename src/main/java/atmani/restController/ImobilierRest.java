package atmani.restController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import atmani.constents.ImobilierConstents;
import atmani.model.Achat;
import atmani.model.Image;
import atmani.model.Imobilier;
import atmani.model.Location;
import atmani.repository.ImobilierRepo;
import atmani.services.ImobilierService;
import atmani.utils.CafeUtils;
import atmani.utils.ImageUtility;

@RequestMapping(path = "/imobilier")
@RestController
@CrossOrigin("*")
public class ImobilierRest {

	@Autowired
	ImobilierRepo imobilierRepo;

	@Autowired
	ImobilierService imobilierService;
	
	@Autowired
    private atmani.repository.ImageRepo ImageRepo;

    @GetMapping(path = {"/get/image/{name}"})
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {

        final Optional<Image> dbImage = ImageRepo.findByName(name);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(ImageUtility.decompressImage(dbImage.get().getImage()));
    }
    @GetMapping(path = {"/get/image/info/{name}"})
    public Image getImageDetails(@PathVariable("name") String name) throws IOException {

        final Optional<Image> dbImage = ImageRepo.findByName(name);

        return Image.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .image(ImageUtility.decompressImage(dbImage.get().getImage())).build();
    }
    @PostMapping("/upload/image")
    public void uplaodImage( @RequestParam("image") MultipartFile file, @RequestParam("image2") MultipartFile file2, @RequestParam("id") int id)
            throws IOException {
    	Imobilier imobilier = new Imobilier(id);
    	
    	if (file!= null) {
    		ImageRepo.save(Image.builder()
    				.imobilier(imobilier)
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .image(ImageUtility.compressImage(file.getBytes())).build());
    		
		}
    	
    	if (file2!= null) {
    		ImageRepo.save(Image.builder()
    				.imobilier(imobilier)
                    .name(file2.getOriginalFilename())
                    .type(file2.getContentType())
                    .image(ImageUtility.compressImage(file2.getBytes())).build());
    		
		}
    	
    	
    }

	@GetMapping(path = "/get")
	public List<Imobilier> get() {
		System.out.println("1111111111");
		return imobilierRepo.findAll();
	}

	@PostMapping(path = "/add")
	public ResponseEntity<String> addImobilier(@RequestBody(required = true) Map<String, String> requestMap) {
		try {
			return imobilierService.addImobilier(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(ImobilierConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(path = "/delete/{id}")

	ResponseEntity<String> delete(@PathVariable Integer id) {
		try {
			return imobilierService.deleteImobilier(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(ImobilierConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(path = "/location")
	ResponseEntity<List<Location>> getAllLocation() {
		try {
			return imobilierService.getAllLocations();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(path = "/achat")
	ResponseEntity<List<Achat>> getAllAchat() {
		try {
			return imobilierService.getAllAchats();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(path = "/updateStatus")
	ResponseEntity<String> updateStatus(@RequestBody(required = true) Map<String, String> requestMap) {
		try {
			return imobilierService.updateStatus(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(ImobilierConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(path="/update")
	ResponseEntity<String> updateAchat(@RequestBody(required = true) Map<String, String> requrstMap){
		
		try {
			return imobilierService.updateAchat(requrstMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(ImobilierConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}

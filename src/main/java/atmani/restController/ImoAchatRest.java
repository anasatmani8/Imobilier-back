package atmani.restController;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import atmani.model.Image;
import atmani.model.ImageModel;
import atmani.model.ImoAchat;
import atmani.servicesIMP.ImoAchatService;

@RestController
public class ImoAchatRest {
	
	@Autowired
	ImoAchatService achatService;
	
	@PostMapping(path="addAchat", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ImoAchat addAchat(@RequestPart("imoAchat") ImoAchat achat, @RequestPart("file") MultipartFile[] files) {
		System.out.println("start");
		System.out.println(files);
		try {
			Set<ImageModel> images = uploadImage(files);
			achat.setImoAchatImages(images);
			return achatService.addAchat(achat);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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

}

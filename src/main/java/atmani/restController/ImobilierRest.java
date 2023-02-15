package atmani.restController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import atmani.constents.ImobilierConstents;
import atmani.model.Achat;

import atmani.model.Image;
import atmani.model.ImoAchat;
import atmani.model.Imobilier;
import atmani.model.Location;
import atmani.repository.ImoAchatRepo;
import atmani.repository.ImobilierRepo;
import atmani.services.ImobilierService;
import atmani.servicesIMP.ImoAchatServiceIMP;
import atmani.utils.CafeUtils;
import atmani.utils.ImageUtility;

@RequestMapping(path = "/imobilier")
@RestController
@CrossOrigin("*")
public class ImobilierRest {

	@Autowired
	ImobilierRepo imobilierRepo;
	
	@Autowired
	ImoAchatServiceIMP achatService;
	
	@Autowired 
	ServletContext context;
	
	@Autowired
	atmani.repository.ImageRepo imageRepository;

	@Autowired
	ImobilierService imobilierService;

	@Autowired
	private atmani.repository.ImageRepo ImageRepo;

	@GetMapping(path = { "/get/image/{name}" })
	public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {

		final Optional<Image> dbImage = ImageRepo.findByName(name);
 
		return ResponseEntity.ok().contentType(MediaType.valueOf(dbImage.get().getType()))
				.body(ImageUtility.decompressImage(dbImage.get().getImage()));
	}

	@GetMapping(path = { "/get/image/info/{name}" })
	public Image getImageDetails(@PathVariable("name") String name) throws IOException {

		final Optional<Image> dbImage = ImageRepo.findByName(name);

		return Image.builder().name(dbImage.get().getName()).type(dbImage.get().getType())
				.image(ImageUtility.decompressImage(dbImage.get().getImage())).build();
	}
	
	
	
	

	/*@PostMapping("/upload/image")
	public ResponseEntity<Void> uplaodImage(@RequestParam("files") MultipartFile[] multipartFiles,
			@RequestParam("id") int id) throws IOException {
		
		Imobilier imobilier = new Imobilier(id);
		
		
		boolean isExist = new File("C:\\Users\\Admin\\Desktop\\Etude\\JEE S5\\Imobilier-back\\src\\main\\FilesP").exists();
		if (!isExist) {
			new File("C:\\Users\\Admin\\Desktop\\Etude\\JEE S5\\Imobilier-back\\src\\main\\FilesP").mkdir();
			System.out.println("--------------- mkdir");
		}
		
		try {
			System.out.println("Files liste");
			for (MultipartFile file : multipartFiles) {
				System.out.println("file Name : "+file.getOriginalFilename());
				System.out.println("file Size : "+file.getSize());
				System.out.println("file Type : "+file.getContentType());
				System.out.println("------------------------------------");
				save(file);
				if (file != null) {
					ImageRepo.save(Image.builder().imobilier(imobilier).name(file.getOriginalFilename())
							.type(file.getContentType()).image(ImageUtility.compressImage(file.getBytes())).build());
				}
				
			}
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		
		/*String fileName = file.getOriginalFilename();
		String newFileName = FilenameUtils.getBaseName(fileName)+"."+FilenameUtils.getExtension(fileName);
		File serverFilename = new File("C:\\Users\\Admin\\Desktop\\Etude\\JEE S5\\Imobilier-back\\src\\main\\FilesP"+File.separator+newFileName);
		System.out.println(serverFilename);
		try {
			System.out.println("image");
			FileUtils.writeByteArrayToFile(serverFilename, file.getBytes());
			
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		/*if (file != null) {
			/*ImageRepo.save(Image.builder().imobilier(imobilier).name(file.getOriginalFilename())
					.type(file.getContentType()).image(ImageUtility.compressImage(file.getBytes())).build());
		}*/

		/*if (file2 != null) {
			/*ImageRepo.save(Image.builder().imobilier(imobilier).name(file2.getOriginalFilename())
					.type(file2.getContentType()).image(ImageUtility.compressImage(file2.getBytes())).build());
		
	}
	}*/
	private String save(MultipartFile file) {
		try {
		String fileName = file.getOriginalFilename();
		
		byte[] bytes = file.getBytes();
		java.nio.file.Path path = Paths.get("C:\\Users\\Admin\\Desktop\\Etude\\JEE S5\\Imobilier-back\\src\\main\\FilesP\\"+fileName);
		java.nio.file.Files.write(path, bytes);
		return fileName;
		}catch (Exception e) {
			return null;
		}
		
	}
	
	
	@GetMapping(path = "/getImages/{id}")
    public ResponseEntity<List<String>>getImages(@PathVariable("id") int name)throws IOException{
        List<String>images=new ArrayList<>();
        final Optional<Image> dbImage = imageRepository.findById(name);
        String encode64=null;
        try {
            String extention=dbImage.get().getType();
            encode64= Base64.getEncoder().encodeToString(dbImage.get().getImage());
            System.out.println(encode64);
            images.add("data:"+extention+";base64,"+encode64);

        }catch (Exception e){

        }
        return new ResponseEntity<List<String>>(images,HttpStatus.ACCEPTED);
    }
	
	
	

	@GetMapping(path = "/get")
	public List<Imobilier> get() {

		return imobilierRepo.findAll();
	}

	@PostMapping(path = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<String> addImobilier(@RequestPart("imobilier") Map<String, String> requestMap, @RequestPart("file") MultipartFile[] files) {
		try {
			return imobilierService.addImobilier(requestMap, files);
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


	
	/*@GetMapping(path="/achatDetails/{id}")
	ResponseEntity<List<Optional<?>>> getAchatDetails(@PathVariable int id){
		
		try {
			return imobilierService.getAchatDetail(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}*/
	

	@PostMapping(path = "/updateStatus")
	ResponseEntity<String> updateStatus(@RequestBody(required = true) Map<String, String> requestMap) {
		try {
			return imobilierService.updateStatus(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(ImobilierConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(path = "/update")
	ResponseEntity<String> updateAchat(@RequestBody(required = true) Map<String, String> requrstMap) {

		try {
			return imobilierService.updateAchat(requrstMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(ImobilierConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

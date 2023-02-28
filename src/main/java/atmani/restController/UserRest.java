package atmani.restController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import atmani.constents.ImobilierConstents;
import atmani.model.User;
import atmani.services.UserService;
import atmani.utils.CafeUtils;

@RequestMapping(path = "/user")
@RestController
@CrossOrigin("*")
public class UserRest {
	
	@Autowired
	UserService userService;
	
	@PostMapping(path = "/signup")
	public ResponseEntity<String> Signup(@RequestBody(required = true) Map<String, String> requestMap) {
		return null;
	}

	@PostMapping(path = "/login")
	public ResponseEntity<String> Login(@RequestBody(required = true) Map<String, String> requestMap) {
		try {
			return userService.login(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(ImobilierConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	


	@GetMapping(path = "/get")
	public ResponseEntity<List<User>> getAllUser() {
		return null;
	}

	@PostMapping("/update")
	public ResponseEntity<String> Update(@RequestBody(required = true) Map<String, String> requestMap) {
		return null;
	}

	@GetMapping("/checkToken")
	public ResponseEntity<String> checkToken() {
		return null;
	}
	
	@PostMapping("/changePassword")
	public ResponseEntity<String> changePassword(@RequestBody(required = true) Map<String, String> requestMap) {
		return null;
	}
	
	@PostMapping("/forgotPassword")
	public ResponseEntity<String> forgotPassword(@RequestBody(required = true) Map<String, String> requesMap) {
		return null;
	}
	

}

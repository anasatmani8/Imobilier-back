package atmani.servicesIMP;


//import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.google.common.base.Strings;
import atmani.JWT.CustomerUsersDetailsService;
import atmani.JWT.JwtFilter;
import atmani.JWT.JwtUtil;
import atmani.constents.ImobilierConstents;
import atmani.model.User;
import atmani.repository.UserRepo;
import atmani.services.UserService;
import atmani.utils.CafeUtils;
import atmani.utils.EmailUtils;


@Service
public class UserServiceIMP implements UserService {

	@Autowired
	UserRepo userDao;

	@Autowired
	AuthenticationManager authenticationManager;

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
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		log.info("Inside signup / ", requestMap);
		System.out.printf("Inside signup / ", requestMap);

		try {
			if (validateSignUp(requestMap)) {
				User user = userDao.findByEmail(requestMap.get("email"));
				if (Objects.isNull(user)) {
					userDao.save(getUserFromMap(requestMap));
					return CafeUtils.getResponseEntity("Successfully registred", HttpStatus.OK);
				} else {
					System.out.println("Already existing email");
					return CafeUtils.getResponseEntity("Email already exits", HttpStatus.BAD_REQUEST);
				}

			} else {
				System.out.println("invalid data");
				return CafeUtils.getResponseEntity(ImobilierConstents .INVALID_DATA, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(ImobilierConstents .SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	private boolean validateSignUp(Map<String, String> requestMap) {
		if (requestMap.containsKey("name") && requestMap.containsKey("contactNumber") && requestMap.containsKey("email")
				&& requestMap.containsKey("password")) {
			return true;
		}
		return false;
	}

	private User getUserFromMap(Map<String, String> requeMap) {
		User user = new User(requeMap.get("name"), requeMap.get("contactNumber"), requeMap.get("email"), requeMap.get("password"), "false", "user");
		/*user.setName(requeMap.get("name"));
		user.setEmail(requeMap.get("email"));
		user.setConctactnumber(requeMap.get("contactNumber"));
		user.setPassword(requeMap.get("password"));
		user.setStatus("false");
		user.setRole("user");*/
		return user;
	}

	@Override
	public ResponseEntity<String> login(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		System.out.println("Inside login 1");
		log.info("Inside login 2");
		try {
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password")));
			if (auth.isAuthenticated()) {
				if (customerUsersDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")) {
					log.info("login ok");
					return new ResponseEntity<String>(
							"{\"token\":\""
									+ jwtUtil.generateToken(customerUsersDetailsService.getUserDetail().getEmail(),
											customerUsersDetailsService.getUserDetail().getRole())
									+ "\"}",
							HttpStatus.OK);

				} else {
					return new ResponseEntity<String>("{\"message\":\"" + "Wait for admin aproval." + "\"}",
							HttpStatus.BAD_REQUEST);
				}
			}

		} catch (Exception ex) {
			// log.error("{}", ex);
			ex.printStackTrace();
		}
		return new ResponseEntity<String>("{\"message\":\"" + "Bad Credentials." + "\"}", HttpStatus.BAD_REQUEST);
	}

	/*@Override
	public ResponseEntity<List<User>> getAllUser() {
		// TODO Auto-generated method stub
		System.out.println("inside /get");
		System.out.println(customerUsersDetailsService.getUserDetail().getRole() + " role");
		try {
			if (customerUsersDetailsService.getUserDetail().getRole().equalsIgnoreCase("admin")) {
				return new ResponseEntity<List<User>>(userDao.getAllUser(), HttpStatus.OK);
			} else {
				System.out.println("forbidden");
				return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}*/

/*	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		try { //
			if (customerUsersDetailsService.getUserDetail().getRole().equalsIgnoreCase("admin")) {
				Optional<User> optional = userDao.findById(Integer.parseInt(requestMap.get("id")));
				if (optional.isPresent() == true) {
					userDao.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
					System.out.println(requestMap.get("status") + " status");
					System.out.println(optional.get().getEmail() + " modified user");
					System.out.println(
							customerUsersDetailsService.getUserDetail().getEmail() + " who made the operation");
					sendMailToAllAdmin(customerUsersDetailsService.getUserDetail().getEmail(), requestMap.get("status"),
							optional.get().getEmail(), userDao.getAllAdmin());
					return CafeUtils.getResponseEntity("User status updated successfully", HttpStatus.OK);
				} else {
					return CafeUtils.getResponseEntity("User id does not exist", HttpStatus.OK);
				}
			} else {
				return CafeUtils.getResponseEntity(ImobilierConstents .UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(ImobilierConstents .SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
*/
/*	private void sendMailToAllAdmin(String from, String status, String user, List<String> allAdmin) {
		allAdmin.remove(from);
		if (status != null && status.equalsIgnoreCase("true")) {
			emailUtils.sendSimpleMessage("Account aprouved", "User:- " + user + "\n is approved by \nADMIN:-" + from,
					allAdmin);
		} else {
			emailUtils.sendSimpleMessage("Account disabled", "User:- " + user + "\n is disabled by \nADMIN:-" + from,
					allAdmin);
		}

	}*/

	@Override
	public ResponseEntity<String> checkToken() {

		return CafeUtils.getResponseEntity("true", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		try {
			System.out
					.println(customerUsersDetailsService.getUserDetail().getEmail() + " the email of the current user");
			User userObj = userDao.findByEmail(customerUsersDetailsService.getUserDetail().getEmail());
			System.out.println("1");
			System.out.println(userObj.getEmail());
			if (!userObj.equals(null)) {
				System.out.println(userObj.getPassword() + " old password \n" + requestMap.get("oldPassword")
						+ " oldPassword \n" + requestMap.get("newPassword") + " newPassword");
				if (userObj.getPassword().equals(requestMap.get("oldPassword"))) {
					System.out.println("2");
					userObj.setPassword(requestMap.get("newPassword"));
					userDao.save(userObj);
					return CafeUtils.getResponseEntity("Password updated successfully", HttpStatus.OK);
				}
				return CafeUtils.getResponseEntity("Incorrect old password", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return CafeUtils.getResponseEntity(ImobilierConstents .SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(ImobilierConstents .SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
		try {
			System.out.println("test");
			User user = userDao.findByEmail(requestMap.get("email"));
			if (!Objects.isNull(user) && !Strings.isNullOrEmpty(user.getEmail())) 
				emailUtils.forgotMail(user.getEmail(), "Credentials by Maroc Immo Consulting", user.getPassword());
			
			return CafeUtils.getResponseEntity("Check your email for Credentials", HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(ImobilierConstents .SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

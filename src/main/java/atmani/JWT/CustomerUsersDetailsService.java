package atmani.JWT;

import java.util.ArrayList;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import atmani.model.User;
import atmani.repository.UserRepo;
import atmani.servicesIMP.UserServiceIMP;



@Service
public class CustomerUsersDetailsService implements UserDetailsService { 
	
	@Autowired
	UserRepo userDao;
	
	private User userDetail;
	
	Logger log = (Logger) LoggerFactory.getLogger(UserServiceIMP.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//
		log.info("Inside loadUserByUsername {} ", username);
		System.out.printf("Inside loadUserByUsername {} ", username);
		// TODO Auto-generated method stub
		System.out.println(username);
		userDetail = userDao.findByEmail(username);
		if (!Objects.isNull(userDetail)) {
			System.out.println("1");
			return new org.springframework.security.core.userdetails.User(userDetail.getEmail(), userDetail.getPassword(), new ArrayList<>());
			
		}else
			System.out.println("2");
			throw new UsernameNotFoundException("User Not Found!");
	}
	
	public User getUserDetail() {
		return userDetail;
	}

}
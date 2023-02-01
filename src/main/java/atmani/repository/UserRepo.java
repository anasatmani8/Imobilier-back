package atmani.repository;

//import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import atmani.model.User;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, Integer> {

	User findByEmail(@Param("email") String email);

	/*@Query(value = "", nativeQuery = true)
	List<User> getAllUser();
	
	@Query(value = "", nativeQuery = true)
	List<String> getAllAdmin();
	
	@Modifying
	@Query(value = "")
	Integer updateStatus(@Param("status") String status, @Param("id") int id);
	*/
	

}

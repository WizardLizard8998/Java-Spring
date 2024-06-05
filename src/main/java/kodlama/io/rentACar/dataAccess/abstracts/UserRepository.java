package kodlama.io.rentACar.dataAccess.abstracts;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kodlama.io.rentACar.entities.concretes.Users;
//import kodlama.io.rentACar.responses.GetUserResponse;

public interface UserRepository extends JpaRepository<Users, Integer>{
	
	//create an method for custom query search  
	@Query(nativeQuery=true,
			value = "select * from rentacar.users as u where u.username= :username and u.password = :password ;")
	Users  FindByUsernamePassword(@Param("username") String username , @Param("password") String password);
	
	
	@Query(nativeQuery =true, 
			value= "Select * from rentacar.users as u where u.username= :username")
	Users FindByUsername(@Param("username") String username);
	

}

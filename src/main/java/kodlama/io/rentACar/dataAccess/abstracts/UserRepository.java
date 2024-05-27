package kodlama.io.rentACar.dataAccess.abstracts;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;


import kodlama.io.rentACar.entities.concretes.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	

}

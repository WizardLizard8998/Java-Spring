package kodlama.io.rentACar.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserResponse {

	int id;
	
	String username;
	
	String password;

	
}

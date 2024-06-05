package kodlama.io.rentACar.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class LoginResponseAuth {

	private String username;
	private String token;
	
}

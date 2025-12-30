package user_service.user_service.Exceptions;

import org.bouncycastle.util.Exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequest extends RuntimeException {

	public BadRequest(String message)
	{
		super(message);
	}

	
	
	
}

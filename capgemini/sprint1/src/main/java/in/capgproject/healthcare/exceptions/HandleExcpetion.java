package in.capgproject.appointment.exception;

import java.util.Date;


import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HandleExcpetion {
	
	@ResponseBody
	@ResponseStatus(value=HttpStatus.FORBIDDEN)
	@ExceptionHandler({ForBiddenException.class,DataAlreadyExists.class})
	public ErrorMapper handleInvalidConflict(Exception ex, HttpServletRequest req) {
		String msg=ex.getMessage();
		String uri=req.getRequestURL().toString();
		return new ErrorMapper(uri, msg, new Date(),ex.getClass().getName());
	}
	
	@ResponseBody
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ExceptionHandler({UserCreationError.class,ConflictException.class})
	public ErrorMapper userCreationConflict(Exception ex, HttpServletRequest req) {
		String msg=ex.getLocalizedMessage();
		String uri=req.getRequestURL().toString();
		return new ErrorMapper(uri, msg, new Date(),ex.getClass().getName());
	}
	
	@ResponseBody
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ExceptionHandler({
		DiagnosticCenterNotFoundException.class,
		PatientNotFoundException.class,
		UserNotFoundException.class,
		TestResultNotFoundException.class,
		DataNotFoundInDataBase.class,
		AppointmentNotFoundException.class,
		InvalidAppointmentStatusException.class
	})
	public ErrorMapper handleConflict(Exception ex, HttpServletRequest req) {
		String msg=ex.getMessage();
		String uri=req.getRequestURL().toString();
		return new ErrorMapper(uri, msg, new Date(),ex.getClass().getName());
	}

}

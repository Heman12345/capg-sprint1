package in.capgproject.appointment.serviceimpl;

import org.springframework.stereotype.Component;

import in.capgproject.appointment.service.InputValidator;
@Component
public class InputValidationImpl implements InputValidator {

	@Override
	public boolean nameValidator(String name) {
		return name.matches( "[A-Z][a-z]{3,}" );
	}

	@Override
	public boolean contactValidator(String contact) {
		return contact.matches("[0-9]{10}");
	}

	@Override
	public boolean emailValidator(String email) {
		return email.matches("^(.+)@(.+)$");
	}

	@Override
	public boolean passwordValidator(String password) {
		return password.length()>=3;
	}

	@Override
	public boolean usernameValidator(String username) {
		return username.length()>=3;
	}

}

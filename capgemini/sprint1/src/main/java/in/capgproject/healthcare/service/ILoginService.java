package in.capgproject.appointment.service;

import org.springframework.stereotype.Service;

import in.capgproject.appointment.domain.User;
import in.capgproject.appointment.exception.UserNotFoundException;

@Service
public interface ILoginService {
	public User loginWithData(String username,String password)throws UserNotFoundException;
	public User logoutPresentUser(String userName)throws UserNotFoundException;

}

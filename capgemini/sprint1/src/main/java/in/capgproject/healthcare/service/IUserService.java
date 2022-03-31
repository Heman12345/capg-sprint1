package in.capgproject.appointment.service;




import org.springframework.stereotype.Service;

import in.capgproject.appointment.domain.User;
import in.capgproject.appointment.exception.UserCreationError;
import in.capgproject.appointment.exception.UserNotFoundException;
@Service
public interface IUserService {

	User validateUser(String username, String password) throws Exception;
	public User addUser(User user) throws UserCreationError;
	public User removeUser(User user) throws UserNotFoundException;
	User updateUser(User user) throws UserNotFoundException ;
	Iterable<User> getAll();

	
}

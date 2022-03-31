package in.capgproject.appointment.service;

import org.springframework.stereotype.Service;

import in.capgproject.appointment.domain.User;
import in.capgproject.appointment.exception.DataNotFoundInDataBase;
import in.capgproject.appointment.exception.UserCreationError;

@Service
public interface IAdminService {
	public void registerAdmin(String username, String password) throws UserCreationError;
	public User updateAdmin(User user) throws UserCreationError, DataNotFoundInDataBase;
	public User deleteAdmin(int id) throws DataNotFoundInDataBase;

}

package in.capgproject.appointment.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.capgproject.appointment.domain.User;
import in.capgproject.appointment.exception.DataNotFoundInDataBase;
import in.capgproject.appointment.exception.UserCreationError;
import in.capgproject.appointment.repository.IAdminRepository;
import in.capgproject.appointment.service.IAdminService;
@Service
public class IAdminServiceImpl implements IAdminService {
	@Autowired
	IAdminRepository adminRepo;
	
	@Autowired
	InputValidator validate;

	@Override
	public void registerAdmin(String username, String password) throws UserCreationError {
		if(!validate.usernameValidator(username))throw new UserCreationError("Check Username !!!!");
		if(!validate.passwordValidator(password))throw new UserCreationError("Cannot register this admin with this password");
		if(adminRepo.existsByusername(username)) throw new UserCreationError("username Already exists");
		User u  = new User(username,password,"ADMIN");
		adminRepo.save(u);
		// TODO Auto-generated method stub
		
	}

	@Override
	public User updateAdmin(User user) throws UserCreationError, DataNotFoundInDataBase {
		if(!validate.usernameValidator(user.getUsername()))throw new UserCreationError("Check Username !!!!");
		if(!validate.passwordValidator(user.getPassword()))throw new UserCreationError("Cannot register this admin with this password");
		if(!adminRepo.existsById(user.getUserid())) throw new DataNotFoundInDataBase("No Such User Exists with id : "+user.getUserid());
		adminRepo.save(user);
		return adminRepo.findById(user.getUserid()).get();
	}

	@Override
	public User deleteAdmin(int id) throws DataNotFoundInDataBase {
		if(!adminRepo.existsById(id)) throw new DataNotFoundInDataBase("No Such User Exists with id : "+id);
		User user1 = adminRepo.findById(id).get();
		adminRepo.delete(user1);
		return user1;
	}

	

}

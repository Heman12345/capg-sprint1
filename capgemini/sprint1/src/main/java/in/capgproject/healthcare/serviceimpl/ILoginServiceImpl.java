package in.capgproject.appointment.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.capgproject.appointment.repository.*;
import in.capgproject.appointment.domain.User;
import in.capgproject.appointment.exception.UserNotFoundException;
import in.capgproject.appointment.repository.IUserRepository;
import in.capgproject.appointment.service.ILoginService;
@Service
public class ILoginServiceImpl implements ILoginService {
	@Autowired
	private QueryClassPersisitContext qcp;
	
	@Autowired
	private IUserRepository uRepo;

	@Override
	public User loginWithData(String username, String password) throws UserNotFoundException {
		User user;
		user = qcp.findByUserName(username);
		if(user.isLoggedIn())throw new UserNotFoundException("User Already Logged In ");
		if(!user.getPassword().equals(password))throw new UserNotFoundException("Invalid Password");
		user.setLoggedIn(true);
		uRepo.save(user);
		return user;
	}

	@Override
	public User logoutPresentUser(String userName) throws UserNotFoundException {
		User user = qcp.findByUserName(userName);
		user.setLoggedIn(false);
		return uRepo.save(user);
	}
}

package in.capgproject.appointment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.capgproject.appointment.domain.User;
@Repository
public interface IUserRepository extends CrudRepository<User, Integer>{
	public boolean existsByusername(String username);

}

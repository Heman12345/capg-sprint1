package in.capgproject.appointment.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.capgproject.appointment.domain.Patient;
import in.capgproject.appointment.domain.User;
@Repository
public interface IPatientRepository extends CrudRepository<Patient, Integer> {
	
List<Patient> findAllByname(String name);
	
	Patient findByuser(User user);

}

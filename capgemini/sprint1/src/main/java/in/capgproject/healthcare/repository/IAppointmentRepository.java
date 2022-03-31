package in.capgproject.appointment.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.capgproject.appointment.domain.Appointment;
import in.capgproject.appointment.domain.ApprovalStatus;
import in.capgproject.appointment.domain.Patient;
import in.capgproject.appointment.domain.TestResult;
@Repository
public interface IAppointmentRepository extends CrudRepository<Appointment, Integer> {
	Set<Appointment> findBypatient(Patient patient);

	List<Appointment> findAllByapprovalStatus(ApprovalStatus statusnotapproved);

	List<Appointment> findAllBytestResult(TestResult testResult);
	
	

}

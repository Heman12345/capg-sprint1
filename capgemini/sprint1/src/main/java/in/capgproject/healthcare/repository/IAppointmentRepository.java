package in.capgproject.appointment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.capgproject.appointment.domain.Appointment;

@Repository
public interface IAppointmentRepository extends CrudRepository<Appointment, Integer>{

}

package in.capgproject.appointment.service;

import java.util.List;
import java.util.Set;

import in.capgproject.appointment.domain.Appointment;
import in.capgproject.appointment.domain.Patient;
import in.capgproject.appointment.domain.TestResult;

public interface IAppointmentService {
	
	Appointment addAppointment(Appointment appointment,String patientID,String diagnosticCenterID,List<Integer> testID);
	
	Appointment removeAppointment(Appointment appointment);
	
	Set<Appointment> viewAppointments(int patientID);
	
	Appointment viewAppointment(int appointmentID);
	
	Appointment updateAppointment(Appointment appointment, List<Integer> testResultId,String patientID , 
			String diagnosticCenterID, List<Integer> testIds);
	

	List<Appointment> getApppointmentList(int centreId, String test, String status);
	
	
	 
	
	

}

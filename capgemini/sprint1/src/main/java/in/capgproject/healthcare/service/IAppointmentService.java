package in.capgproject.appointment.service;


import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import in.capgproject.appointment.domain.Appointment;
import in.capgproject.appointment.domain.Patient;
import in.capgproject.appointment.domain.TestResult;
import in.capgproject.appointment.exception.AppointmentNotFoundException;
import in.capgproject.appointment.exception.DataAlreadyExists;
import in.capgproject.appointment.exception.DataNotFoundInDataBase;
import in.capgproject.appointment.exception.DiagnosticCenterNotFoundException;
import in.capgproject.appointment.exception.InvalidAppointmentStatusException;
import in.capgproject.appointment.exception.PatientNotFoundException;
import in.capgproject.appointment.exception.TestResultNotFoundException;

@Service
public interface IAppointmentService {
	

	Appointment addAppointment(Appointment appointment,String patientID,String diagnosticCenterID,List<Integer> testID) throws DataAlreadyExists, DataNotFoundInDataBase;
	
	Appointment removeAppointment(Appointment appointment)throws AppointmentNotFoundException;
	
	Set<Appointment> viewAppointments(int patientID) throws AppointmentNotFoundException, PatientNotFoundException;
	
	Appointment viewAppointment(int appointmentID)throws AppointmentNotFoundException;
	
	Appointment updateAppointment(Appointment appointment, List<Integer> testResultId,String patientID , 
			String diagnosticCenterID, List<Integer> testIds)throws AppointmentNotFoundException, PatientNotFoundException, DiagnosticCenterNotFoundException, TestResultNotFoundException, DataNotFoundInDataBase ;
	

	List<Appointment> getApppointmentList(int centreId, String test, String status) throws InvalidAppointmentStatusException, AppointmentNotFoundException;
	Appointment verify( int appointmentID , boolean approved) throws AppointmentNotFoundException;
	Patient getPatient(int appID) throws PatientNotFoundException;


	TestResult setTestResult(int appointmentId, int testResId) throws AppointmentNotFoundException, TestResultNotFoundException;


	Iterable<Appointment> getAll();
	

}

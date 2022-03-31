package in.capgproject.appointment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.capgproject.appointment.domain.Patient;
import in.capgproject.appointment.domain.TestResult;
import in.capgproject.appointment.exception.DataAlreadyExists;
import in.capgproject.appointment.exception.DataNotFoundInDataBase;
@Service 
public interface IPatientService {
	
	Patient registerPatient(Patient patient, int userID) throws DataAlreadyExists, DataNotFoundInDataBase;
	Patient updatePatientDetails(Patient patient) throws DataNotFoundInDataBase;
	Patient viewPatient(String patientUserName) throws DataNotFoundInDataBase;
	List<TestResult> getAllTestResult(String patientUserName) throws DataNotFoundInDataBase;
	TestResult viewTestResult(int testResultId) throws DataNotFoundInDataBase;
	Patient deletePatient(Patient patient) throws DataNotFoundInDataBase;
	Iterable<Patient> getAll();


}

package in.capgproject.appointment.web;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.capgproject.appointment.domain.Patient;
import in.capgproject.appointment.domain.TestResult;
import in.capgproject.appointment.exception.DataAlreadyExists;
import in.capgproject.appointment.exception.DataNotFoundInDataBase;
import in.capgproject.appointment.exception.ForBiddenException;
import in.capgproject.appointment.service.IPatientService;





@RestController
@RequestMapping("/patient")
@CrossOrigin("http://localhost:4200")
public class PatientController {

	@Autowired
	IPatientService patientService;

	@Autowired
	LoginController logCon;
	
	@PostMapping("/registerpatient/{userID}")
	public Patient registerPatient(@RequestBody Patient patient,@PathVariable int userID) throws DataAlreadyExists, DataNotFoundInDataBase {
		return patientService.registerPatient(patient,userID);
	}
	
	@PutMapping("/updatepatient")
	public Patient updatePatientDetails(@RequestBody Patient patient)
			throws DataNotFoundInDataBase, ForBiddenException {
		return patientService.updatePatientDetails(patient);
	}
	
	@GetMapping("/viewpatient/{userid}")
	Patient viewPatient(@PathVariable String userid) throws ForBiddenException, DataNotFoundInDataBase {
		return patientService.viewPatient(userid);
	}
	
	@GetMapping("/viewtestresult/{testResultId}")
	TestResult viewTestResult(@PathVariable int testResultId) throws Exception {
		return patientService.viewTestResult(testResultId);
	}

	@DeleteMapping("/deletePatient")
	Patient deletePatient(@RequestBody Patient patient) throws DataNotFoundInDataBase, ForBiddenException {
		return patientService.deletePatient(patient);
	}
	
	@GetMapping("/getAll")
	public Iterable<Patient> getAll(){
		return patientService.getAll();
	}

}

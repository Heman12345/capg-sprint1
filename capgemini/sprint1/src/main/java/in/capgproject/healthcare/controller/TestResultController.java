package in.capgproject.appointment.web;

import java.util.List;

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
import in.capgproject.appointment.exception.TestResultNotFoundException;
import in.capgproject.appointment.service.ITestResultService;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/testresult")
public class TestResultController {
	
	@Autowired
	ITestResultService testresultService;

	@Autowired
	LoginController logCon;
	
	@PostMapping("/addresult")
	public TestResult addTestResult(@RequestBody TestResult tr) throws ForBiddenException, DataAlreadyExists {
		return testresultService.addTestResult(tr);
	}
	
	@PutMapping("/updateresult")
	public TestResult updateResult(@RequestBody TestResult tr) throws DataNotFoundInDataBase, ForBiddenException {
		return testresultService.updateResult(tr);
	}
	
	@DeleteMapping("/removeresult/{id}")
	public Iterable<TestResult> removeTestResult(@PathVariable int id) throws ForBiddenException, TestResultNotFoundException {
		return testresultService.removeTestResult(id);
	}
	
	@GetMapping("/viewresultsbypatient/{patientID}")
	public List<TestResult> viewResultsByPatient(@PathVariable int patientID) throws Exception {
		Patient pat = new Patient();
		try {
			pat.setPatientId(patientID);
		} catch (Exception e) {
			throw new Exception("This is Not An ID");
		}
		return testresultService.viewResultsByPatient(pat);
	}
	
	@GetMapping("/getAllTestResults")
	public Iterable<TestResult> getAllTestResults(){
		return testresultService.getAll();
	}
	@GetMapping("/resultbyid/{id}")
	public TestResult getById(@PathVariable int id) throws DataNotFoundInDataBase {
		return testresultService.getById(id);
	}


}

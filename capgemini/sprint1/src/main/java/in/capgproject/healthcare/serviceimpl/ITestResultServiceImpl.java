
package in.capgproject.appointment.serviceimpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.capgproject.appointment.domain.Patient;
import in.capgproject.appointment.domain.TestResult;
import in.capgproject.appointment.exception.DataAlreadyExists;
import in.capgproject.appointment.exception.DataNotFoundInDataBase;
import in.capgproject.appointment.repository.ITestResultRepository;
import in.capgproject.appointment.repository.QueryClassPersisitContext;
import in.capgproject.appointment.service.ITestResultService;
import in.capgproject.appointment.exception.*;

@Service
public class ITestResultServiceImpl implements ITestResultService {

	@Autowired
	ITestResultRepository resultrepo;
	
	@Autowired
	QueryClassPersisitContext qcp;

	@Override
	public TestResult addTestResult(TestResult tr) throws DataAlreadyExists {
		if(resultrepo.existsById(tr.getTestResultid())) throw new DataAlreadyExists("Test Result Already exists with id :"+tr.getTestResultid());
		return resultrepo.save(tr);
	}

	@Override
	public TestResult updateResult(TestResult tr) throws DataNotFoundInDataBase {
		if(!resultrepo.existsById(tr.getTestResultid()))throw new DataNotFoundInDataBase("TestResult Not Found in DataBase to update");
		return resultrepo.save(tr);
	}

	@Override
	public Iterable<TestResult> removeTestResult(int id) throws TestResultNotFoundException {
		if(!resultrepo.existsById(id)) throw new TestResultNotFoundException("Test Result Does Not Exist  "+ id);
		TestResult tr = resultrepo.findById(id).get();
		resultrepo.deleteById(id);
		return resultrepo.findAll();
		
	}

	@Override
	public List<TestResult> viewResultsByPatient(Patient patient)
			throws DataNotFoundInDataBase, TestResultNotFoundException {
		List<TestResult> testRes =  qcp.viewResultsByPatient(patient);
		if(testRes.size() ==0 )throw new DataNotFoundInDataBase("User/Tests Doesn't Exits");
		return testRes;
	}

	@Override
	public Iterable<TestResult> getAll() {
		return resultrepo.findAll();
	}

	@Override
	public TestResult getById(int id) throws DataNotFoundInDataBase {
		return resultrepo.findById(id).orElseThrow(()-> new DataNotFoundInDataBase("No Patient With ID "+id));
	}
	
	
}

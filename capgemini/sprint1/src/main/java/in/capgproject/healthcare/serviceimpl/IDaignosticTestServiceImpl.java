package in.capgproject.appointment.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.capgproject.appointment.domain.DiagnosticTest;
import in.capgproject.appointment.exception.DataAlreadyExists;
import in.capgproject.appointment.exception.DataNotFoundInDataBase;
import in.capgproject.appointment.repository.IDiagnosticTestRepository;
import in.capgproject.appointment.repository.QueryClassPersisitContext;
import in.capgproject.appointment.service.IDiagnosticTestService;
@Service 
public class IDaignosticTestServiceImpl implements IDiagnosticTestService {
	@Autowired
	IDiagnosticTestRepository testRepo;
	@Autowired
	QueryClassPersisitContext qcp;

	@Override
	public List<DiagnosticTest> getAllTest() {
		return testRepo.findAll();
	}

	@Override
	public DiagnosticTest addNewTest(DiagnosticTest test) throws DataAlreadyExists {
		if(testRepo.existsById(test.getDiagonasticTestid())) throw new DataAlreadyExists("Test Already Exists Use Update To Change");
		return testRepo.save(test);
	}

	@Override
	public List<DiagnosticTest> getTestsOfDiagnosticCenter(int centerId) throws Exception {
		List<DiagnosticTest> tests = qcp.getTestsOfDiagnosticCenter(centerId);
		if(tests.size()==0)throw new DataNotFoundInDataBase("No Diagnostic Tests Exist");
		return tests;
	}

	@Override
	public DiagnosticTest updateTestDetail(DiagnosticTest test) throws DataNotFoundInDataBase {
		
		if(!testRepo.existsById(test.getDiagonasticTestid())) throw new DataNotFoundInDataBase("No test Exist with id : "+test.getDiagonasticTestid()+" To Update");
		
		return testRepo.save(test);
	}

	@Override
	public DiagnosticTest removeTestFromDiagnosticCenter(int centerId, int test) throws Exception {
		return qcp.removeTestFromDiagnosticCenter(centerId, test);

	
		
	}

	@Override
	public DiagnosticTest getTestById(int diagnosticTestid) throws DataNotFoundInDataBase {
		return testRepo.findById(diagnosticTestid).get();
	}
	

}

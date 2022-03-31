package in.capgproject.appointment.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.capgproject.appointment.domain.DiagnosticCenter;
import in.capgproject.appointment.domain.DiagnosticTest;

import in.capgproject.appointment.exception.ConflictException;
import in.capgproject.appointment.exception.DataNotFoundInDataBase;
import in.capgproject.appointment.repository.IDiagnosticCenterRepositoryInt;
import in.capgproject.appointment.repository.ITestRepository;
import in.capgproject.appointment.service.ITestService;
@Service
public class ITestServiceImpl implements ITestService {

	@Autowired
	ITestRepository testrepo;
	
	@Autowired
	IDiagnosticCenterRepositoryInt centerRepo;
	

	@Override
	public DiagnosticTest addTest(DiagnosticTest test) throws DataNotFoundInDataBase {
		if(!testrepo.existsById(test.getDiagonasticTestid())) throw new DataNotFoundInDataBase("Test Already Exists");
		return testrepo.save(test);
	}

	@Override
	public DiagnosticTest updateTest(DiagnosticTest test) throws DataNotFoundInDataBase {
		if(!testrepo.existsById(test.getDiagonasticTestid())) throw new DataNotFoundInDataBase("Test Does Not Exist");
		return testrepo.save(test);	}

	@Override
	public DiagnosticTest removeTest(int diagnosticTestid) throws DataNotFoundInDataBase, ConflictException {
		if(!testrepo.existsById(diagnosticTestid)) throw new DataNotFoundInDataBase("Test Does Not Exist");
		DiagnosticTest tes = testrepo.findById(diagnosticTestid).get();
		try {
		testrepo.delete(tes);
		}
		catch(Exception e ) {
			throw new ConflictException("This Test Is linked With Previous Other Entity So it is Preferebale Not to delete");
		}
		return tes;
	}

	@Override
	public List<DiagnosticTest> viewAllTest() {
		return testrepo.findAll();
	}

	@Override
	public DiagnosticTest addTestInCenter(int testID, int centerId) throws DataNotFoundInDataBase {
		DiagnosticTest test = testrepo.findById(testID)
				.orElseThrow(()-> new DataNotFoundInDataBase(testID+" test Not Found"));
		DiagnosticCenter center = centerRepo.findById(centerId)
				.orElseThrow(()-> new DataNotFoundInDataBase(centerId+" center Not Found"));
		test.setDiagnosticCenter(center);
		center.getTests().add(test);
		testrepo.save(test);
		return test;
	}

	
	
}

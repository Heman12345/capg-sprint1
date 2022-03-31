package in.capgproject.appointment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.capgproject.appointment.domain.DiagnosticTest;
import in.capgproject.appointment.exception.DataAlreadyExists;
import in.capgproject.appointment.exception.DataNotFoundInDataBase;

@Service
public interface IDiagnosticTestService {
	public List<DiagnosticTest> getAllTest();
	DiagnosticTest addNewTest(DiagnosticTest test) throws DataAlreadyExists;
	List<DiagnosticTest> getTestsOfDiagnosticCenter(int centerId) throws Exception;
	DiagnosticTest updateTestDetail(DiagnosticTest test) throws DataNotFoundInDataBase;
	DiagnosticTest removeTestFromDiagnosticCenter(int centerId, int test) throws Exception;
	DiagnosticTest getTestById(int diagnosticTestid) throws DataNotFoundInDataBase;

}

package in.capgproject.appointment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.capgproject.appointment.domain.DiagnosticTest;
import in.capgproject.appointment.exception.ConflictException;
import in.capgproject.appointment.exception.DataNotFoundInDataBase;

@Service
public interface ITestService {

	public DiagnosticTest addTest(DiagnosticTest test) throws DataNotFoundInDataBase;
	public DiagnosticTest updateTest(DiagnosticTest test) throws DataNotFoundInDataBase;
	public DiagnosticTest removeTest(int diagnosticTestid) throws DataNotFoundInDataBase, ConflictException;
	public List<DiagnosticTest> viewAllTest();
	DiagnosticTest addTestInCenter(int testID, int centerId) throws DataNotFoundInDataBase;

}

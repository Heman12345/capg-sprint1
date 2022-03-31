package in.capgproject.appointment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.capgproject.appointment.domain.Patient;
import in.capgproject.appointment.domain.TestResult;
import in.capgproject.appointment.exception.DataAlreadyExists;
import in.capgproject.appointment.exception.DataNotFoundInDataBase;
import in.capgproject.appointment.exception.TestResultNotFoundException;


@Service
public interface ITestResultService {
	public TestResult addTestResult(TestResult tr)throws DataAlreadyExists;
	public TestResult updateResult(TestResult tr) throws DataNotFoundInDataBase;
	public Iterable<TestResult> removeTestResult(int id)throws TestResultNotFoundException;
	public List<TestResult> viewResultsByPatient(Patient patient) throws DataNotFoundInDataBase, TestResultNotFoundException;
	public Iterable<TestResult> getAll();
	public TestResult getById(int id) throws DataNotFoundInDataBase;

}

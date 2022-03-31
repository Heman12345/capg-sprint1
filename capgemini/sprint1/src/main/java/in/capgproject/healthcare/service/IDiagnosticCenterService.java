package in.capgproject.appointment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.capgproject.appointment.domain.Appointment;
import in.capgproject.appointment.domain.DiagnosticCenter;
import in.capgproject.appointment.domain.DiagnosticTest;
import in.capgproject.appointment.exception.DataNotFoundInDataBase;
import in.capgproject.appointment.exception.DiagnosticCenterNotFoundException;

@Service
public interface IDiagnosticCenterService {
	public List<DiagnosticCenter> getAllDiagnosticCenters();
	public DiagnosticCenter addDiagnosticCenter(DiagnosticCenter diagnosticCenter) throws Exception;
	public DiagnosticCenter getDiagnosticCenterById(int diagnosticCenterId) throws DataNotFoundInDataBase;
	public DiagnosticCenter updateDiagnosticCenter(DiagnosticCenter diagnosticCenter) throws DataNotFoundInDataBase;
	List<DiagnosticTest> viewTestDetails(int diagnosticCenterId);
	DiagnosticTest addTest(int diagnosticcenterId, int testid) throws DataNotFoundInDataBase;
	DiagnosticCenter getDiagnosticCenter(String centername) throws DataNotFoundInDataBase;
	DiagnosticCenter removeDiagnosticCenter(int id) throws DiagnosticCenterNotFoundException;
	List<Appointment> getListOfAppointments(String centerName);

}

package in.capgproject.appointment.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import in.capgproject.appointment.domain.DiagnosticCenter;
import in.capgproject.appointment.domain.DiagnosticTest;

@Repository
public interface IDiagnosticCenterRepository {
	List<DiagnosticTest> viewTestDetails(int diagnosticCenterId);
	DiagnosticCenter getDiagnosticCenter(String centername);

}

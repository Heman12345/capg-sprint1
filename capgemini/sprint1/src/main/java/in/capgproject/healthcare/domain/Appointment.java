package in.capgproject.appointment.domain;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate appointmentDate;
	@Enumerated(EnumType.STRING)
	private ApprovalStatus approvalStatus;
	@ManyToMany
	private Set<DiagnosticTest> diagnosticTests;
	@ManyToOne
	private Patient patient;
	@OneToOne
	private DiagnosticCenter diagnosticCenter;
	@OneToMany(mappedBy = "appointment",cascade = CascadeType.REMOVE)
	private Set<TestResult> testResult;
	
	public Appointment() {
		super();
		
	}
	

	public Appointment(int id, LocalDate appointmentDate, ApprovalStatus approvalStatus,
			Set<DiagnosticTest> diagnosticTests, Patient patient, DiagnosticCenter diagnosticCenter,
			Set<TestResult> testResult) {
		super();
		this.id = id;
		this.appointmentDate = appointmentDate;
		this.approvalStatus = approvalStatus;
		this.diagnosticTests = diagnosticTests;
		this.patient = patient;
		this.diagnosticCenter = diagnosticCenter;
		this.testResult = testResult;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public ApprovalStatus getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(ApprovalStatus approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Set<DiagnosticTest> getDiagnosticTests() {
		return diagnosticTests;
	}

	public void setDiagnosticTests(Set<DiagnosticTest> diagnosticTests) {
		this.diagnosticTests = diagnosticTests;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public DiagnosticCenter getDiagnosticCenter() {
		return diagnosticCenter;
	}

	public void setDiagnosticCenter(DiagnosticCenter diagnosticCenter) {
		this.diagnosticCenter = diagnosticCenter;
	}

	public Set<TestResult> getTestResult() {
		return testResult;
	}

	public void setTestResult(Set<TestResult> testResult) {
		this.testResult = testResult;
	}
	
	
	
	

}

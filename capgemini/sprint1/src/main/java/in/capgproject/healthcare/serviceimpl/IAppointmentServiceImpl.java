package in.capgproject.appointment.serviceimpl;


import in.capgproject.appointment.service.IAppointmentService;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.capgproject.appointment.domain.Appointment;
import in.capgproject.appointment.domain.ApprovalStatus;
import in.capgproject.appointment.domain.DiagnosticCenter;
import in.capgproject.appointment.domain.DiagnosticTest;
import in.capgproject.appointment.domain.Patient;
import in.capgproject.appointment.domain.TestResult;
import in.capgproject.appointment.exception.AppointmentNotFoundException;
import in.capgproject.appointment.exception.DataAlreadyExists;
import in.capgproject.appointment.exception.DataNotFoundInDataBase;
import in.capgproject.appointment.exception.DiagnosticCenterNotFoundException;
import in.capgproject.appointment.exception.InvalidAppointmentStatusException;
import in.capgproject.appointment.exception.PatientNotFoundException;
import in.capgproject.appointment.exception.TestResultNotFoundException;
import in.capgproject.appointment.repository.IAppointmentRepository;
import in.capgproject.appointment.repository.IDiagnosticCenterRepositoryInt;
import in.capgproject.appointment.repository.IDiagnosticTestRepository;
import in.capgproject.appointment.repository.IPatientRepository;
import in.capgproject.appointment.repository.ITestResultRepository;
import in.capgproject.appointment.repository.QueryClassPersisitContext;

@Service
public class IAppointmentServiceImpl implements IAppointmentService {
	

	@Autowired
	private IAppointmentRepository iar;
	@Autowired
	IPatientRepository patRepo;
	
	@Autowired
	IDiagnosticCenterRepositoryInt centerRepo;
	
	@Autowired
	IDiagnosticTestRepository testRepo;
	
	@Autowired
	ITestResultRepository testResRepo;
	
	@Autowired
	QueryClassPersisitContext qcp;

	@Override
	public Appointment addAppointment(Appointment appointment, String patientID, String diagnosticCenterID,
			List<Integer> testID) throws DataAlreadyExists, DataNotFoundInDataBase {

		if(iar.existsById(appointment.getId()))throw new DataAlreadyExists("Appointment Already Exists Use Update To Change");
		
		DiagnosticCenter preDC = new DiagnosticCenter();
		Set<DiagnosticTest> preDTs = new HashSet<>();
		Patient prePatient = new Patient();
		try {
		if(patientID != null) {
			prePatient= patRepo.findById(Integer.parseInt(patientID))
					.orElseThrow(()-> new DataNotFoundInDataBase("Patient Not Found With ID : "+patientID));
			appointment.setPatient(prePatient);
		}
		if(diagnosticCenterID != null) {
			preDC = centerRepo.findById(Integer.parseInt(diagnosticCenterID))
					.orElseThrow(()-> new DataNotFoundInDataBase("Diagnostic Center Not Found With ID : "+diagnosticCenterID));
			appointment.setDiagnosticCenter(preDC);
		}
		if(testID!=null) {
		for(int id : testID) {
			DiagnosticTest pretest = testRepo.findById(id)
					.orElseThrow(()-> new DataNotFoundInDataBase("Diagnostic Test Not Found With ID : "+id));
			preDTs.add(pretest);
			pretest.setDiagnosticCenter(preDC);
			testRepo.save(pretest);
		}
		}
		}
		catch(NumberFormatException e) {
			throw new DataNotFoundInDataBase("Please Check The ID's");
		}
		appointment.setDiagnosticTests(preDTs);
		
		preDC.getTests().addAll(preDTs);
		
		iar.save(appointment);
		
		return appointment;
	}

	@Override
	public Appointment removeAppointment(Appointment appointment) throws AppointmentNotFoundException {
		if(!iar.existsById(appointment.getId())) throw new AppointmentNotFoundException("No Appointment found to remove");
		Appointment app = iar.findById(appointment.getId()).get();
		iar.delete(app);
		return app;
	}

	@Override
	public Set<Appointment> viewAppointments(int patientID)
			throws AppointmentNotFoundException, PatientNotFoundException {
		Set<Appointment> apps =iar.findBypatient(patRepo.findById(patientID)
				.orElseThrow(()->new PatientNotFoundException("No Such Patient")));
		if(apps.size()==0)throw new AppointmentNotFoundException("No Appointments For You Yet");
		return apps;
	}

	@Override
	public Appointment viewAppointment(int appointmentID) throws AppointmentNotFoundException {
		if(!iar.existsById(appointmentID)) throw new AppointmentNotFoundException("No appointments Found with ID : "+appointmentID );
		return iar.findById(appointmentID).get();
	}

	@Override
	public Appointment updateAppointment(Appointment appointment, List<Integer> testResultId, String patientID,
			String diagnosticCenterID, List<Integer> testIds)throws AppointmentNotFoundException, PatientNotFoundException, DiagnosticCenterNotFoundException,
			TestResultNotFoundException, DataNotFoundInDataBase {

		if(!iar.existsById(appointment.getId())) {
			throw new AppointmentNotFoundException("Appointment Does Not Exist To Update");
		}
		
		if(testResultId!= null) {
			Set<TestResult> tr= appointment.getTestResult();
			for(int i : testResultId) {
				if(testResRepo.existsById(i))tr.add(testResRepo.findById(i).get());
				else throw new TestResultNotFoundException("Test Result Does Not Exist with id : "+i);
			}
				
		}
		try {
			if(patientID != null) {
				Patient prePatient = new Patient();
				prePatient= patRepo.findById(Integer.parseInt(patientID))
						.orElseThrow(()-> new DataNotFoundInDataBase("Patient Not Found With ID : "+patientID));
				appointment.setPatient(prePatient);
			}
			if(diagnosticCenterID != null) {
				DiagnosticCenter preDC = new DiagnosticCenter();
				preDC = centerRepo.findById(Integer.parseInt(diagnosticCenterID))
						.orElseThrow(()-> new DataNotFoundInDataBase("Diagnostic Center Not Found With ID : "+diagnosticCenterID));
				appointment.setDiagnosticCenter(preDC);
			}
			if(testIds!=null) {
			for(int id : testIds) {
				DiagnosticTest pretest = testRepo.findById(id)
						.orElseThrow(()-> new DataNotFoundInDataBase("Diagnostic Test Not Found With ID : "+id));
				appointment.getDiagnosticTests().add(pretest);
			}
		}
		}
		catch(NumberFormatException e) {
			throw new DataNotFoundInDataBase("Please Check The ID's");
		}
		
		
		iar.save(appointment);
		return appointment;
	}

	@Override
	public List<Appointment> getApppointmentList(int centreId, String test, String status)
			throws InvalidAppointmentStatusException, AppointmentNotFoundException {
		ApprovalStatus stat;
		try {
			 stat = ApprovalStatus.valueOf(status);
		}
		catch(Exception e) {
			throw new InvalidAppointmentStatusException("Invaild AppointMent Status"+status);
		}
		List<Appointment> apps = qcp.getAppointmentList(centreId, test,stat);
		if(apps.size() ==0) throw new AppointmentNotFoundException("No Such Appointment Exists");
		return apps;
	}

	@Override
	public Appointment verify(int appointmentID, boolean approved) throws AppointmentNotFoundException {
		Appointment app = iar.findById(appointmentID)
				.orElseThrow(()->new AppointmentNotFoundException("No Appointment with id "+appointmentID));
		if(approved)app.setApprovalStatus(ApprovalStatus.approved);
		else app.setApprovalStatus(ApprovalStatus.cancelled);
		return iar.save(app);
	}

	@Override
	public Patient getPatient(int appID) throws PatientNotFoundException {
		return iar.findById(appID).orElseThrow(()->new PatientNotFoundException("No Appointment With Id "+appID)).getPatient();
	}

	@Override
	public TestResult setTestResult(int appointmentId, int testResId)
			throws AppointmentNotFoundException, TestResultNotFoundException {
		Appointment app = iar.findById(appointmentId)
				.orElseThrow(()->new AppointmentNotFoundException("No Appointment With Id "+appointmentId));
		TestResult tr = testResRepo.findById(testResId)
				.orElseThrow(() -> new TestResultNotFoundException("No TestResult With Id "+testResId));
		app.getTestResult().add(tr);
		tr.setAppointment(app);
		iar.save(app);
		testResRepo.save(tr);
		return tr;
	}

	@Override
	public Iterable<Appointment> getAll() {
		return iar.findAll();
	}
}
	


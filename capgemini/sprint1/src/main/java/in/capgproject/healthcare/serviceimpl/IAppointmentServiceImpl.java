package in.capgproject.appointment.serviceimpl;

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
import in.capgproject.appointment.exception.DataNotFoundInDataBase;
import in.capgproject.appointment.exception.InvalidAppointmentStatusException;
import in.capgproject.appointment.repository.IAppointmentRepository;
import in.capgproject.appointment.service.IAppointmentService;
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
	public Appointment addAppointment(Appointment appointment, String patientID, String diagnosticCenterID,List<Integer> testID) {
		
		
		if(iar.existsById(appointment.getId()));
		
		DiagnosticCenter DC = new DiagnosticCenter();
		Set<DiagnosticTest> DTs = new HashSet<>();
		Patient patient = new Patient();
		try {
		if(patientID != null) {
			patient= patRepo.findById(Integer.parseInt(patientID))
					.orElseThrow(()-> new DataNotFoundInDataBase("Patient Not Found With ID : "+patientid));
			appointment.setPatient(patient);
		}
		if(diagnosticCenterID != null) {
			DC = centerRepo.findById(Integer.parseInt(diagnosticCenterID))
					.orElseThrow(()-> new DataNotFoundInDataBase("Diagnostic Center Not Found With ID : "+diagnosticCenterID));
			appointment.setDiagnosticCenter(DC);
		}
		if(testID!=null) {
		for(int id : testID) {
			DiagnosticTest test = testRepo.findById(id)
					.orElseThrow(()-> new DataNotFoundInDataBase("Diagnostic Test Not Found With ID : "+id));
			DTs.add(test);
			test.setDiagnosticCenter(DC);
			testRepo.saveAndFlush(test);
		}
		}
		}
		catch(NumberFormatException e) {
			throw new DataNotFoundInDataBase("Please Check The ID's");
		}
		appointment.setDiagnosticTests(DTs);
		
		DC.getTests().addAll(DTs);
		
		iar.save(appointment);
		
		return appointment;
	}
		
	

	@Override
	public Appointment removeAppointment(Appointment appointment) {
		if(!iar.existsById(appointment.getId())) throw new AppointmentNotFoundException("No Appointment found to remove");
		Appointment app = iar.findById(appointment.getId()).get();
		iar.delete(app);
		return app;
	}

		

	@Override
	public Set<Appointment> viewAppointments(int patientID) {
		Set<Appointment> apps =iar.findBypatient(patRepo.findById(patientID)
				.orElseThrow(()->new PatientNotFoundException("No Such Patient")));
		if(apps.size()==0)throw new AppointmentNotFoundException("No Appointments For You Yet");
		return apps;
		
	}

	@Override
	public Appointment viewAppointment(int appointmentID) {
		
		if(!iar.existsById(appointmentID)) throw new AppointmentNotFoundException("No appointments Found with ID : "+appointmentID );
		return iar.findById(appointmentID).get();
		}
	}

	@Override
	public Appointment updateAppointment(Appointment appointment, List<Integer> testResultId, String patientID,
			String diagnosticCenterID, List<Integer> testIds) {
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
				Patient patient = new Patient();
				patient= patRepo.findById(Integer.parseInt(patientID))
						.orElseThrow(()-> new DataNotFoundInDataBase("Patient Not Found With ID : "+patientID));
				appointment.setPatient(patient);
			}
			if(diagnosticCenterID != null) {
				DiagnosticCenter DC = new DiagnosticCenter();
				DC = centerRepo.findById(Integer.parseInt(diagnosticCenterID))
						.orElseThrow(()-> new DataNotFoundInDataBase("Diagnostic Center Not Found With ID : "+diagnosticCenterID));
				appointment.setDiagnosticCenter(DC);
			}
			if(testIds!=null) {
			for(int id : testIds) {
				DiagnosticTest test = testRepo.findById(id)
						.orElseThrow(()-> new DataNotFoundInDataBase("Diagnostic Test Not Found With ID : "+id));
				appointment.getDiagnosticTests().add(test);
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
	public List<Appointment> getApppointmentList(int centreId, String test, String status) {
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
}

	
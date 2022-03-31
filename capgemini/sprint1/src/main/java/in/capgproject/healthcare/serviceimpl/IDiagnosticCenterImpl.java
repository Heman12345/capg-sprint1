package in.capgproject.appointment.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import in.capgproject.appointment.domain.Appointment;
import in.capgproject.appointment.domain.DiagnosticCenter;
import in.capgproject.appointment.domain.DiagnosticTest;
import in.capgproject.appointment.exception.DataNotFoundInDataBase;
import in.capgproject.appointment.exception.DiagnosticCenterNotFoundException;
import in.capgproject.appointment.repository.IAppointmentRepository;
import in.capgproject.appointment.repository.IDiagnosticCenterRepositoryInt;
import in.capgproject.appointment.repository.ITestRepository;
import in.capgproject.appointment.service.IDiagnosticCenterService;
@Service
public class IDiagnosticCenterImpl implements IDiagnosticCenterService {
	@Autowired
	IDiagnosticCenterRepositoryInt centerDao;
	
	@Autowired
	IAppointmentRepository appointmentDao;
	
	@Autowired
	ITestRepository test;

	@Override
	public List<DiagnosticCenter> getAllDiagnosticCenters() {
		return centerDao.findAll();
	}

	@Override
	public DiagnosticCenter addDiagnosticCenter(DiagnosticCenter diagnosticCenter) throws Exception {
		if(centerDao.existsById(diagnosticCenter.getDiagonasticCenterid())){
			throw new Exception("Diagnostic Center with given Id already exists.");
		}
		else
		centerDao.save(diagnosticCenter);
		return diagnosticCenter;
	}

	@Override
	public DiagnosticCenter getDiagnosticCenterById(int diagnosticCenterId) throws DataNotFoundInDataBase {
		if(!centerDao.existsById(diagnosticCenterId))throw new DataNotFoundInDataBase("Diagnostic Center Not Found");
		return centerDao.findById(diagnosticCenterId).get();
	
	}

	@Override
	public DiagnosticCenter updateDiagnosticCenter(DiagnosticCenter diagnosticCenter) throws DataNotFoundInDataBase {
		if(!centerDao.existsById(diagnosticCenter.getDiagonasticCenterid())) throw new DataNotFoundInDataBase("Diagnostic Center Not Found");
		centerDao.saveAndFlush(diagnosticCenter);
		return diagnosticCenter;
	}

	@Override
	public List<DiagnosticTest> viewTestDetails(int diagnosticCenterId) {
		return centerDao.viewTestDetails(diagnosticCenterId);
	}

	@Override
	public DiagnosticTest addTest(int diagnosticcenterId, int testid) throws DataNotFoundInDataBase {
		DiagnosticTest t = test.getOne(testid);
		DiagnosticCenter c = centerDao.getOne(diagnosticcenterId);
		if(t==null || c==null) throw new DataNotFoundInDataBase("Center/test does Not Exist");
		c.getTests().add(t);
		t.setDiagnosticCenter(c);
		test.save(t);
		centerDao.save(c);
		return t;;
	}

	@Override
	public DiagnosticCenter getDiagnosticCenter(String centername) throws DataNotFoundInDataBase {
		DiagnosticCenter dc = centerDao.getDiagnosticCenter(centername);
		if(dc==null) throw new DataNotFoundInDataBase("Diagnostic Center Not Found");
		return dc;
	}

	@Override
	public DiagnosticCenter removeDiagnosticCenter(int id) throws DiagnosticCenterNotFoundException {
		Optional<DiagnosticCenter> op=centerDao.findById(id);
		if(op.isPresent()) {
			centerDao.deleteById(id);
			return op.get();
		}
		else throw new DiagnosticCenterNotFoundException("Diagnostic Center with given Id doesn't exist.");
	}

	@Override
	public List<Appointment> getListOfAppointments(String centerName) {
		return appointmentDao.findAll();
	}

}

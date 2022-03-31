package in.capgproject.appointment.repository;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import in.capgproject.appointment.domain.Appointment;
import in.capgproject.appointment.domain.ApprovalStatus;
import in.capgproject.appointment.domain.DiagnosticCenter;
import in.capgproject.appointment.domain.DiagnosticTest;
import in.capgproject.appointment.domain.Patient;
import in.capgproject.appointment.domain.TestResult;
import in.capgproject.appointment.domain.User;
import in.capgproject.appointment.exception.TestResultNotFoundException;
import in.capgproject.appointment.exception.UserNotFoundException;

@Repository
public class QueryClassPersisitContext {
	@PersistenceContext
	EntityManager eManager;
	
	public List<Appointment> getAppointmentList(int centreId,String test,ApprovalStatus status){
		TypedQuery<Appointment> exe = eManager.createQuery("select a from Appointment a join a.diagnosticTests d where"
				+ " a.diagnosticCenter.diagonasticCenterid = :id and d.testName like :test and a.approvalStatus like :status", Appointment.class);
		exe.setParameter("id", centreId);
		exe.setParameter("test",test);
		exe.setParameter("status",status);
		List<Appointment> result = exe.getResultList();
		return result;
	}
	
	public List<Appointment> viewAppointments( String patientName){
		TypedQuery<Appointment> qry = eManager.createQuery("select a from Appointment a where a.patient.name like :pname",Appointment.class);
		qry.setParameter("pname",patientName);
		return qry.getResultList();
	}
	
	public List<DiagnosticTest> getTestsOfDiagnosticCenter(int centerId){
		
		TypedQuery<DiagnosticTest> exe = eManager.createQuery("select d from DiagnosticTest d join d.diagnosticCenter dc where dc.diagonasticCenterid like :id", DiagnosticTest.class);
		exe.setParameter("id", centerId);
		List<DiagnosticTest> resultList = exe.getResultList();
		return resultList;
	}
	public  DiagnosticTest getTestById(int diagnosticTestid) {
		TypedQuery<DiagnosticTest> exe=eManager.createQuery("select d from DiagnosticTest where d.diagnosticTestid like :id", DiagnosticTest.class);
		exe.setParameter("id", diagnosticTestid);
		DiagnosticTest result = exe.getSingleResult();
		return result;
	}
	@Transactional
	public DiagnosticTest removeTestFromDiagnosticCenter(int centerId, int test) {
		Query qry = eManager.createQuery("select c from DiagnosticCenter c where c.diagonasticCenterid = :id");
		qry.setParameter("id", centerId);
		DiagnosticCenter diagnosticCenter = (DiagnosticCenter) qry.getSingleResult();
		qry = eManager.createQuery("select t from DiagnosticTest t where t.diagonasticTestid = :tid");
		qry.setParameter("tid", test);
		DiagnosticTest diagnosticTest = (DiagnosticTest) qry.getSingleResult();
		diagnosticTest.setDiagnosticCenter(null);
		diagnosticCenter.getTests().remove(diagnosticTest);
		eManager.persist(diagnosticTest);
		eManager.persist(diagnosticCenter);
		return diagnosticTest;
	}
	
	public List<TestResult> getAllTestResult(String patientUserName) {
		TypedQuery<TestResult> qry = eManager.createQuery("select t from TestResult t join t.appointment a where a.patient.name like :n",TestResult.class);
		qry.setParameter("n", patientUserName);
		List<TestResult> tr = qry.getResultList();
		return tr;
	}
	public List<TestResult> viewResultsByPatient(Patient patient) throws TestResultNotFoundException{
		TypedQuery<TestResult> qry = eManager.createQuery("select t from TestResult t join t.appointment a where a.patient.id = :id",TestResult.class);
		qry.setParameter("id", patient.getPatientId());
		List<TestResult> tr = qry.getResultList();
		if(tr.size()==0)throw new TestResultNotFoundException("Test Result Not Found");
		return tr;
	}
	public User findByUserName(String username) throws UserNotFoundException {
		TypedQuery<User> qry = eManager.createQuery("select u from User u where u.username like :name",User.class);
		qry.setParameter("name", username);
		List<User> user = qry.getResultList();
		if(user.size()==0)throw new UserNotFoundException("User Not Available !!"+username);
		return user.get(0);
	}
	
	

}

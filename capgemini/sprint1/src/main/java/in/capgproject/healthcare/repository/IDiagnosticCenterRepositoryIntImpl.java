package in.capgproject.appointment.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import in.capgproject.appointment.domain.DiagnosticCenter;
import in.capgproject.appointment.domain.DiagnosticTest;
@Repository
public class IDiagnosticCenterRepositoryIntImpl implements IDiagnosticCenterRepository {
	@PersistenceContext
	EntityManager em;

	@Override
	public List<DiagnosticTest> viewTestDetails(int diagnosticCenterId) {
		TypedQuery<DiagnosticTest> q=em.createQuery("select a from DiagnosticTest a join a.diagnosticCenter d "
				+ " where d.diagonasticCenterid = :id",DiagnosticTest.class);
		q.setParameter("id", diagnosticCenterId);
		return q.getResultList();
	}

	@Override
	public DiagnosticCenter getDiagnosticCenter(String centername) {
		TypedQuery<DiagnosticCenter> q=em.createQuery("select s from DiagnosticCenter s where s.name = :name",DiagnosticCenter.class);
		q.setParameter("name", centername);
		return q.getResultList().get(0);
	}

}

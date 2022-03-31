package in.capgproject.appointment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.capgproject.appointment.domain.DiagnosticCenter;
@Repository
public interface IDiagnosticCenterRepositoryInt  extends IDiagnosticCenterRepository,CrudRepository<DiagnosticCenter, Integer>{ 

}

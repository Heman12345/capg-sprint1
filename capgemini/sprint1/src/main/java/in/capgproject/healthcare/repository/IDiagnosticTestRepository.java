package in.capgproject.appointment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.capgproject.appointment.domain.DiagnosticTest;
@Repository
public interface IDiagnosticTestRepository extends CrudRepository<DiagnosticTest, Integer> {

}

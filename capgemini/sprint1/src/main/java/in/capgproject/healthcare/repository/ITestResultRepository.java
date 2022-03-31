package in.capgproject.appointment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.capgproject.appointment.domain.TestResult;
@Repository
public interface ITestResultRepository extends CrudRepository<TestResult, Integer>{

}

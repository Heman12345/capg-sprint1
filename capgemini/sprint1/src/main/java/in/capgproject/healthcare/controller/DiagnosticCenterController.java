package in.capgproject.appointment.web;

import in.capgproject.appointment.domain.Appointment;

import in.capgproject.appointment.domain.DiagnosticCenter;
import in.capgproject.appointment.domain.DiagnosticTest;
import in.capgproject.appointment.exception.DataNotFoundInDataBase;
import in.capgproject.appointment.exception.DiagnosticCenterNotFoundException;
import in.capgproject.appointment.exception.ForBiddenException;
import in.capgproject.appointment.service.IDiagnosticCenterService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin("http://localhost:4200")

@RestController
@RequestMapping("/DiagnosticCenter")
public class DiagnosticCenterController {
	
	@Autowired
	IDiagnosticCenterService centerService;

	@Autowired
	LoginController logCon;
	
	@GetMapping("/getDiagnosticCenters")
	public List<DiagnosticCenter> getDiagnosticCenters() throws ForBiddenException {
		return centerService.getAllDiagnosticCenters();
	}
	
	@PostMapping("/addCenter")
	public DiagnosticCenter addDiagnosticCenter(@RequestBody DiagnosticCenter diagnosticCenter) throws Exception {
		return centerService.addDiagnosticCenter(diagnosticCenter);

	}
	
	@GetMapping("/getDiagnosticCenter/{diagnosticCenterId}")

	public DiagnosticCenter getDiagnosticCenterById(@PathVariable int diagnosticCenterId)
			throws DiagnosticCenterNotFoundException, DataNotFoundInDataBase, ForBiddenException {

		return centerService.getDiagnosticCenterById(diagnosticCenterId);

	}
	
	@PutMapping("/updateDiagnosticCenter")
	public DiagnosticCenter updateDiagnosticCenter(@RequestBody DiagnosticCenter diagnosticCenter)
			throws DataNotFoundInDataBase, ForBiddenException {
		return centerService.updateDiagnosticCenter(diagnosticCenter);
	}
	
	@GetMapping("/viewTestDetails/{diagnosticCenterId}")
	public List<DiagnosticTest> viewTestDetails(@PathVariable int diagnosticCenterId)
			throws ForBiddenException {
		return centerService.viewTestDetails(diagnosticCenterId);
	}
	
	@PostMapping("/addTest/{diagnosticcenterId}/{testid}")
	public DiagnosticTest addTest(@PathVariable int diagnosticcenterId, @PathVariable int testid)
			throws DataNotFoundInDataBase, ForBiddenException {
		return centerService.addTest(diagnosticcenterId, testid);
	}
	
	@GetMapping("/getDiagnosticCenterbyname/{centername}")
	public DiagnosticCenter getDiagnosticCenter(@PathVariable String centername)
			throws DataNotFoundInDataBase, ForBiddenException {
		return centerService.getDiagnosticCenter(centername);
	}
	
	@DeleteMapping("/removeDiagnosticCenter/{id}")
	public DiagnosticCenter removeDiagnosticCenter(@PathVariable int id)
			throws DiagnosticCenterNotFoundException, ForBiddenException {
		return centerService.removeDiagnosticCenter(id);
	}
	
	@GetMapping("/appointments/{centerName}")
	public List<Appointment> getListOfAppointments(@PathVariable String centerName) throws ForBiddenException {
		return centerService.getListOfAppointments(centerName);
	}



}

package in.capgproject.appointment.web;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.capgproject.appointment.domain.Appointment;
import in.capgproject.appointment.service.IAppointmentService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/Appointment")
public class AppointmentController {
	
	@Autowired
	private IAppointmentService appser;
	
	@PostMapping(value = "/addappointment")
	public Appointment addAppointment(@RequestBody Appointment appointment,
			@RequestParam(required = true) String patientID, @RequestParam(required = true) String diagnosticCenterID,
			@RequestParam(required = true) List<Integer> testIds) throws Exception {
		return appser.addAppointment(appointment, patientID, diagnosticCenterID, testIds);
	}
	
	@DeleteMapping("/removeappointment")
	public Appointment removeAppointment(@RequestBody Appointment appointment) throws Exception {
		return appser.removeAppointment(appointment);
	}
	@GetMapping("/viewappointments/{patientId}")
	public Set<Appointment> viewAppointments(@PathVariable int patientId) throws Exception {
		return appser.viewAppointments(patientId);
	}
	@GetMapping("/viewappointment/{appointmentId}")
	public Appointment viewAppointment(@PathVariable int appointmentId) throws Exception {
		return appser.viewAppointment(appointmentId);
	}
	@PutMapping("/updateappointment")
	public Appointment updateAppointment(@RequestBody Appointment appointment,
			@RequestParam(required = false) List<Integer> testResultId,
			@RequestParam(required = false) String patientID, @RequestParam(required = false) String diagnosticCenterID,
			@RequestParam(required = false) List<Integer> testIds) throws Exception {
		return appser.updateAppointment(appointment, testResultId, patientID, diagnosticCenterID, testIds);
	}
	@GetMapping("/getappointmentlist")
	public List<Appointment> getApppointmentList(@RequestParam String diagnosticCenterid, @RequestParam String testName,
			@RequestParam String appointmentStatus) throws Exception {
		return appser.getApppointmentList(Integer.parseInt(diagnosticCenterid), testName, appointmentStatus);
	}

}

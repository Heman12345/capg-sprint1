package in.capgproject.appointment.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class DiagnosticTest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int diagonasticTestid;
	private String testName;
	private double testPrice;
	private String normalValue;
	private String units;
	
	@JsonIgnore
	@ManyToOne
	private DiagnosticCenter diagnosticCenter;
	
	
	public DiagnosticTest() {
		
	}
	public DiagnosticTest(String testName, double testPrice, String normalValue, String units,
			DiagnosticCenter diagnosticCenter) {
		super();
		this.testName = testName;
		this.testPrice = testPrice;
		this.normalValue = normalValue;
		this.units = units;
		this.diagnosticCenter = diagnosticCenter;
	}
	public int getDiagonasticTestid() {
		return diagonasticTestid;
	}
	public void setDiagonasticTestid(int diagonasticTestid) {
		this.diagonasticTestid = diagonasticTestid;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public double getTestPrice() {
		return testPrice;
	}
	public void setTestPrice(double testPrice) {
		this.testPrice = testPrice;
	}
	public String getNormalValue() {
		return normalValue;
	}
	public void setNormalValue(String normalValue) {
		this.normalValue = normalValue;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public DiagnosticCenter getDiagnosticCenter() {
		return diagnosticCenter;
	}
	public void setDiagnosticCenter(DiagnosticCenter diagnosticCenter) {
		this.diagnosticCenter = diagnosticCenter;
	}
	

}

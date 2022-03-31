package in.capgproject.appointment.domain;

public enum ApprovalStatus {
	

	statusnotapproved("statusnotapproved"),approved("approved"),cancelled("cancelled");
	private String status;
	
	ApprovalStatus(String status ) {
		this.setStatus(status);
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}

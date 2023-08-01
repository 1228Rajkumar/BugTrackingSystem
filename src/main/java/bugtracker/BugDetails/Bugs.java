package bugtracker.BugDetails;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Bugs {
	private int bugId;
	private String bugName;
	private String description;
	private String module;
	private String priority;
	private String solution;
	private String status;
	private int assignedToId;
	private int raisedById;
	private String assignedToName="_";
	private String raisedByName = "_";
	private String raisedDate="_";
	private String solvedDate="_";
	
	
	public int getBugId() {
		System.out.println("bugID :"+bugId);
		return bugId;
	}

	public void setBugId(int bugId) {
		
		this.bugId = bugId;
	}

	public String getBugName() {
		return bugName;
	}

	public void setBugName(String bugName) {
		this.bugName = bugName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAssignedToId() {
		return assignedToId;
	}

	public void setAssignedToId(int assignedTo) {
		this.assignedToId = assignedTo;
	}

	public int getRaisedById() {
		return raisedById;
	}

	public void setRaisedById(int raisedBy) {
		this.raisedById = raisedBy;
	}

	public String getRaisedDate() {
		return raisedDate;
	}

	public void setRaisedDate(String raisedDate) {
		this.raisedDate = raisedDate;
	}

	public String getSolvedDate() {
		return solvedDate;
	}

	public void setSolvedDate(String solvedDate) {
		this.solvedDate = solvedDate;
	}
	public String getAssignedToName() {
		return assignedToName;
	}

	public void setAssignedToName(String assignedToName) {
		this.assignedToName = assignedToName;
	}

	public String getRaisedByName() {
		return raisedByName;
	}

	public void setRaisedByName(String raisedByName) {
		this.raisedByName = raisedByName;
	}
	
	
}

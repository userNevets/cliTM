package de.steven.clitaskmanager.userinterface.dto;

import de.steven.clitaskmanager.utils.Status;

public class UpdateTaskCommand extends UserCommand {
	private int id;
	private String title;
	private String description;
	private Status status;

	public UpdateTaskCommand() {}

	public UpdateTaskCommand(int id, Status status) {
		this.id = id;
		this.status = status;

		this.title = null;
		this.description = null;
	}

	public UpdateTaskCommand(int id, String title, String description, Status status) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}

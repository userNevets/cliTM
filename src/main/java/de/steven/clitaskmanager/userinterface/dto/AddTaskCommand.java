package de.steven.clitaskmanager.userinterface.dto;

public class AddTaskCommand extends UserCommand {
	String title;
	String description;

	public AddTaskCommand() {}

	public AddTaskCommand(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}

package de.steven.clitaskmanager.userinterface.dto;

public class DeleteTaskCommand extends UserCommand{
	private int id;
	private String title;
	private boolean all;

	public DeleteTaskCommand() {}

	public DeleteTaskCommand(int id, String title) {
		this.id = id;
		this.title = title;
		this.all = false;
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

	public boolean isAll() {
		return all;
	}

	public void setAll(boolean all) {
		this.all = all;
	}
}
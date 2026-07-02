package de.steven.clitaskmanager.userinterface.dto;

public class PrintTaskCommand extends UserCommand{
	private int id;
	private boolean all;

	public PrintTaskCommand() {}

	public PrintTaskCommand(int id) {
		this.id = id;
		this.all = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isAll() {
		return all;
	}

	public void setAll(boolean all) {
		this.all = all;
	}
}

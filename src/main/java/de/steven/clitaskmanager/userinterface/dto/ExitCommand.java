package de.steven.clitaskmanager.userinterface.dto;

public class ExitCommand extends UserCommand{
	private boolean exit;

	public ExitCommand() {
		this.exit = true;
	}

	public boolean isExit() {
		return exit;
	}
}

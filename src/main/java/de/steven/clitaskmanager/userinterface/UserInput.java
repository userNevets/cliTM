package de.steven.clitaskmanager.userinterface;

import de.steven.clitaskmanager.userinterface.dto.CommandParameter;
import de.steven.clitaskmanager.userinterface.dto.UserCommand;

public interface UserInput {

	UserCommand extractCommand(String commandInput);
	CommandParameter extractParameter(String commandParameter);

}

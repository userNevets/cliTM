package de.steven.clitaskmanager.userinterface;

import java.util.ArrayList;
import java.util.List;

import de.steven.clitaskmanager.userinterface.dto.AddTaskCommand;
import de.steven.clitaskmanager.userinterface.dto.CommandParameter;
import de.steven.clitaskmanager.userinterface.dto.DeleteTaskCommand;
import de.steven.clitaskmanager.userinterface.dto.ExitCommand;
import de.steven.clitaskmanager.userinterface.dto.PrintTaskCommand;
import de.steven.clitaskmanager.userinterface.dto.UpdateTaskCommand;
import de.steven.clitaskmanager.userinterface.dto.UserCommand;
import de.steven.clitaskmanager.utils.Status;

public class CommandExtractor implements UserInput {

	//TODO: Fix, bei mehreren Parametern schlägt es fehl
	@Override
	public CommandParameter extractParameter(String commandParameter) {

		if (commandParameter.contains("id")) {
			String parameter = commandParameter.substring(0, commandParameter.indexOf(" "));
			String parameterValue = commandParameter.substring(3);
			return new CommandParameter(formatValue(parameter), formatValue(parameterValue));
		} else if (commandParameter.contains(" ")) {
			String parameter = commandParameter.substring(0, commandParameter.indexOf(" "));
			String parameterValue = commandParameter.substring(commandParameter.indexOf('"'));
			return new CommandParameter(formatValue(parameter), formatValue(parameterValue));
		}
		String parameter = commandParameter.substring(0);

		return	new CommandParameter(parameter);
	}

//	@Override
//	public CommandParameter extractParameters(String commandParameter) {
//		String[] parameters = commandParameter.split(";");
//		return new CommandParameter(formatValue(parameters[0]), formatValue(parameters[1]));
//	}

	@Override
	public UserCommand extractCommand(String commandInput) {
		String[] commandArray = getFormattedCommandArray(commandInput);
		String command = commandArray[0].trim();
		List<CommandParameter> parameters = extractCommandParameters(commandArray);

		return switch (command) {
			case "add task" -> createAddTaskCommand(parameters);
			case "update task" -> createUpdateTaskCommand(parameters);
			case "delete task" -> createDeleteTaskCommand(parameters);
			case "print", "print task" -> createPrintTaskCommand(parameters);
			case "exit taskmanager", "exit tm" -> createExitTaskManagerCommand();
			default -> createExitTaskManagerCommand();

		};
	}

	private String[] getFormattedCommandArray(String commandInput) {
		String[] commandArray = getCommandArray(commandInput);
		for (int i = 0; i < commandArray.length; i++) {
			if (commandArray[i].contains(";"))
				commandArray[i] = commandArray[i].replace(";", "");
			commandArray[i] = commandArray[i].trim();
		}
		return commandArray;
	}

	private List<CommandParameter> extractCommandParameters(String[] commandArray) {
		List<CommandParameter> parameters = new ArrayList<>();
		if (commandArray.length != 2) {
			for (int i = 1; i < commandArray.length; i++) {
				parameters.add(extractParameter(commandArray[i]));
			}
		}
		else {
			parameters.add(extractParameter(commandArray[1]));
		}
		return parameters;
	}

	private UserCommand createExitTaskManagerCommand() {
		return new ExitCommand();
	}

	private UserCommand createPrintTaskCommand(List<CommandParameter> parameters) {
		PrintTaskCommand printCommand = new PrintTaskCommand();
		for (CommandParameter p : parameters) {
			if ("id".equals(p.getParameter())) {
				printCommand.setId(Integer.parseInt(p.getParameterValue()));
			}
			else if ("all".equals(p.getParameter()) || "a".equals(p.getParameter())) {
				printCommand.setAll(true);
			}
		}

		return printCommand;
	}


	private UserCommand createDeleteTaskCommand(List<CommandParameter> parameters) {
		DeleteTaskCommand updateCommand = new DeleteTaskCommand();
		for (CommandParameter p : parameters) {
			if ("id".equals(p.getParameter())) {
				updateCommand.setId(Integer.parseInt(p.getParameterValue()));
			}
			else if ("t".equals(p.getParameter())) {
				updateCommand.setTitle(p.getParameterValue());
			}
		}

		return updateCommand;
	}

	private UserCommand createUpdateTaskCommand(List<CommandParameter> parameters) {
		UpdateTaskCommand updateCommand = new UpdateTaskCommand();
		for (CommandParameter p : parameters) {
			if ("id".equals(p.getParameter())) {
				updateCommand.setId(Integer.parseInt(p.getParameterValue()));
			}
			else if ("t".equals(p.getParameter())) {
				updateCommand.setTitle(p.getParameterValue());
			}
			else if ("d".equals(p.getParameter())) {
				updateCommand.setDescription(p.getParameterValue());
			}
			else if ("s".equals(p.getParameter())) {
				updateCommand.setStatus(Status.valueOf(p.getParameterValue()));
			}
		}

		return updateCommand;
	}

	private UserCommand createAddTaskCommand(List<CommandParameter> parameters) {
		AddTaskCommand addcommand = new AddTaskCommand();
		for (CommandParameter p : parameters) {
			if ("t".equals(p.getParameter())) {
				addcommand.setTitle(p.getParameterValue());
			}
			else if ("d".equals(p.getParameter())) {
				addcommand.setDescription(p.getParameterValue());
			}
		}

		return addcommand;
	}

	private String formatValue(String s) {
		return s.replace("\"", " ").trim();
	}

	private String[] getCommandArray(String commandInput) {
		return commandInput.contains("-") ? commandInput.split("-") : getCommandInputWithoutParameter(commandInput);
	}

	private String[] getCommandInputWithoutParameter(String commandInput) {
		return new String[] {commandInput};
	}
}

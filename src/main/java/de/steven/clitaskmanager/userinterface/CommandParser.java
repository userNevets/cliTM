package de.steven.clitaskmanager.userinterface;

import java.util.List;

import de.steven.clitaskmanager.service.model.Task;
import de.steven.clitaskmanager.service.usecases.CreateUsecase;
import de.steven.clitaskmanager.service.usecases.DeleteUsecase;
import de.steven.clitaskmanager.service.usecases.ReadUsecase;
import de.steven.clitaskmanager.service.usecases.UpdateUsecase;
import de.steven.clitaskmanager.userinterface.dto.AddTaskCommand;
import de.steven.clitaskmanager.userinterface.dto.ExitCommand;
import de.steven.clitaskmanager.userinterface.dto.PrintTaskCommand;
import de.steven.clitaskmanager.userinterface.dto.UpdateTaskCommand;
import de.steven.clitaskmanager.userinterface.dto.UserCommand;
import de.steven.clitaskmanager.service.serialisierung.TaskSerializer;
import de.steven.clitaskmanager.utils.Status;

public class CommandParser {

	private final CreateUsecase createTaskService;
	private final ReadUsecase readTaskService;
	private final UpdateUsecase updateTaskService;
	private final DeleteUsecase deleteTaskService;
	private final TaskSerializer translator;

	public CommandParser(TaskSerializer translator, CreateUsecase createTaskService, ReadUsecase readTaskService, UpdateUsecase updateTaskService, DeleteUsecase deleteTaskService) {
		this.createTaskService = createTaskService;
		this.readTaskService = readTaskService;
		this.updateTaskService = updateTaskService;
		this.deleteTaskService = deleteTaskService;
		this.translator = translator;
	}

	public boolean applyCommand(UserCommand commandInput, boolean running) {

		switch (commandInput) {
			case AddTaskCommand addTaskCommand -> executeAddTaskCommand(addTaskCommand);
			case PrintTaskCommand printTaskCommand -> executePrintTaskCommand(printTaskCommand);
			case UpdateTaskCommand updateTaskCommand -> executeUpdateTaskCommand(updateTaskCommand);
			case ExitCommand exitCommand -> running = executeExitCommand(exitCommand);
			default -> running = executeExitCommand();
		}

		return running;
	}

	private boolean executeExitCommand(ExitCommand exitCommand) {
		return exitCommand.isExit();
	}

	private boolean executeExitCommand() {
		return true;
	}

	private void executeUpdateTaskCommand(UpdateTaskCommand updateTaskCommand) {
		Task taskWithUpdatedData = new Task.Builder()
				.id(updateTaskCommand.getId())
				.name(updateTaskCommand.getTitle())
				.description(updateTaskCommand.getDescription())
				.status(updateTaskCommand.getStatus())
				.build();

		Task updatedTask = this.updateTaskService.updateTask(taskWithUpdatedData);
		System.out.println("Task updated:");
		System.out.print(translator.serializeTask(updatedTask));
	}

	private void executePrintTaskCommand(PrintTaskCommand printTaskCommand) {
		if (printTaskCommand.isAll()) {
			List<Task> allTasks = this.readTaskService.getAllTasks();
			System.out.println(translator.serializeTasks(allTasks));
		} else {
			Task task = this.readTaskService.getTaskById(printTaskCommand.getId());
			System.out.println(translator.serializeTask(task));
		}
		
	}

	private void executeAddTaskCommand(AddTaskCommand addTaskCommand) {
		String title = "";
		String description = "";
		if (addTaskCommand.getTitle() != null) {
			title = addTaskCommand.getTitle();
		}
		if (addTaskCommand.getDescription() != null) {
			description = addTaskCommand.getDescription();
		}

		Task addedTask;
		System.out.println("-> Task added:");
		if (!"".equals(title) &&  !"".equals(description)) {
			addedTask = this.createTaskService.createTask(title, description);
		}
		else {
			addedTask = this.createTaskService.createTask(title);
		}


		System.out.println(translator.serializeTask(addedTask));
	}
}

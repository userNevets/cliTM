package de.steven.clitaskmanager.service.serialisierung;

import java.util.List;

import de.steven.clitaskmanager.service.model.Task;
import de.steven.clitaskmanager.service.TaskService;

public interface TaskSerializer {

	String serializeTask(Task task);
	String serializeTasks(List<Task> tasks);
}

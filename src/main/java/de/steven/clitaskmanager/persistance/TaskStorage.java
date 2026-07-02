package de.steven.clitaskmanager.persistance;

import java.util.List;

import de.steven.clitaskmanager.service.model.Task;

public interface TaskStorage {

	//Create
	Task createTask(Task task);
	//READ
	Task getTaskById(int id);
	Task getTaskByName(String  name);
	List<Task> getAllTasks();
	//UPDATE
	Task updateTaskById(int id, Task task);
	//DELETE
	Task deleteTaskById(int id);
	Task deleteTaskByName(String name);

}

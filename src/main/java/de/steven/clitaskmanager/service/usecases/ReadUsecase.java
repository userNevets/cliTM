package de.steven.clitaskmanager.service.usecases;

import java.util.List;

import de.steven.clitaskmanager.service.model.Task;

public interface ReadUsecase {

	Task getTaskById(int id);
	List<Task> getTaskByName(String name);
	List<Task> getAllTasks();
}

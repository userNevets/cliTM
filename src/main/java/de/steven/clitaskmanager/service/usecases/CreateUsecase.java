package de.steven.clitaskmanager.service.usecases;

import de.steven.clitaskmanager.service.model.Task;

public interface CreateUsecase {

	Task createTask(String name);
	Task createTask(String name, String description);
}

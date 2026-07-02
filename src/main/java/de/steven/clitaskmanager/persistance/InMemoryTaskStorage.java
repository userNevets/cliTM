package de.steven.clitaskmanager.persistance;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import de.steven.clitaskmanager.service.model.Task;

public class InMemoryTaskStorage implements TaskStorage {
	private final HashMap<Integer, Task> tasks = new HashMap<>();


	@Override
	public Task createTask(Task task) {
		this.tasks.put(task.getId(), task);
		return task;
	}

	@Override
	public Task getTaskById(int id) {
		return  tasks.get(id);
	}

	@Override
	public Task getTaskByName(String name) {
		List<Task> allTasks = getAllTasks();
		Optional<Task> optionalDbTask = allTasks.stream()
				.filter(dbTask -> dbTask.getName().equals(name))
				.findFirst();
		Task dbTask = optionalDbTask.get();
		return dbTask;
	}

	@Override
	public List<Task> getAllTasks() {
		return tasks.values().stream().toList();
	}

	@Override
	public Task updateTaskById(int id, Task task) {
		tasks.remove(id);
		return createTask(task);
	}

	@Override
	public Task deleteTaskById(int id) {
		return tasks.remove(id);
	}

	@Override
	public Task deleteTaskByName(String name) {
		Task toBeDeleted = getTaskByName(name);
		return tasks.remove(toBeDeleted.getId());
	}
}

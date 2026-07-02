package de.steven.clitaskmanager.service;

import java.util.List;

import de.steven.clitaskmanager.persistance.TaskStorage;
import de.steven.clitaskmanager.service.model.Task;
import de.steven.clitaskmanager.service.usecases.CreateUsecase;
import de.steven.clitaskmanager.service.usecases.DeleteUsecase;
import de.steven.clitaskmanager.service.usecases.ReadUsecase;
import de.steven.clitaskmanager.service.usecases.UpdateUsecase;
import de.steven.clitaskmanager.utils.Status;

public class TaskService implements CreateUsecase, DeleteUsecase, ReadUsecase, UpdateUsecase {

    TaskStorage storage;
    int idCounter = 0;


    public TaskService(TaskStorage storage) {
        this.storage = storage;
    }

    public void updateTask(int id, Task updatedTask) {
        Task task = storage.getTaskById(id);

        Task overriddenTask = overrideTask(task, updatedTask);
        
        this.storage.deleteTaskById(task.getId());
        this.storage.updateTaskById(task.getId(), overriddenTask);

//        System.out.println("Task successfully updated:");
//        System.out.println("Original task: \n" + translator.serializeTask(task));
//        System.out.println("Updated task: \n" + translator.serializeTask(overriddenTask));
    }

    private Task overrideTask(Task task, Task updatedTask) {
        Task overriddenTask = new Task.Builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .status(task.getStatus())
                .build();

        if (!updatedTask.getName().equals(overriddenTask.getName())) {
            overriddenTask.setName(updatedTask.getName());
        }
        if (!updatedTask.getStatus().equals(overriddenTask.getStatus())) {
            overriddenTask.setStatus(updatedTask.getStatus());
        }

        return overriddenTask;
    }

    @Override
    public Task createTask(String name) {
        return storage.createTask(new Task(name));
    }

    @Override
    public Task createTask(String name, String description) {
       return storage.createTask(new Task(name, description));
    }

    @Override
    public Task getTaskById(int id) {
        return storage.getTaskById(id);
    }

    @Override
    public List<Task> getTaskByName(String name) {
        return getAllTasks().stream()
                .filter(t -> t.getName().equals(name))
                .toList();
    }

    @Override
    public List<Task> getAllTasks() {
        return storage.getAllTasks();
    }

    @Override
    public void deleteTask(int id) {
        this.storage.deleteTaskById(id);
        System.out.println("Task deleted.");
    }

    //TODO: Updaten über den Namen hinzufügen
    @Override
    public Task updateTask(Task task) {
        Task taskFromMem = getTaskById(task.getId());

        if (!taskFromMem.getName().equals(task.getName()))
            taskFromMem.setName(task.getName());

        if (!taskFromMem.getDescription().equals(task.getDescription()) && "".equals(task.getDescription()))
            taskFromMem.setDescription(task.getDescription());

        if (task.getStatus() != null && !taskFromMem.getStatus().equals(task.getStatus())) {
            taskFromMem.setStatus(task.getStatus());
        }

        return taskFromMem;
    }
}

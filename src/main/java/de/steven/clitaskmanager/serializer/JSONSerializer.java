package de.steven.clitaskmanager.serializer;

import java.util.List;

import de.steven.clitaskmanager.service.model.Task;
import de.steven.clitaskmanager.service.serialisierung.TaskSerializer;

public class JSONSerializer implements TaskSerializer {

    @Override
    public String serializeTask(Task task) {
        return """
            {
                "id": %d,
                "task": "%s",
                "description": "%s",
                "status": "%s"
            }
        """.formatted(
                task.getId(),
                task.getName(),
                task.getDescription(),
                task.getStatus()
        );
    }

    @Override
    public String serializeTasks(List<Task> tasks) {
        StringBuilder taskListElements = new StringBuilder();
        for (Task task : tasks) {
            taskListElements.append(
                    serializeTask(task) + ","
            );
        }

        return "[ \n"
            + taskListElements.toString()
            + "]"
        ;
    }
}

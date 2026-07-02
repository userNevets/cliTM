package de.steven.clitaskmanager;

import java.util.Scanner;

import de.steven.clitaskmanager.persistance.InMemoryTaskStorage;
import de.steven.clitaskmanager.persistance.TaskStorage;
import de.steven.clitaskmanager.service.TaskService;
import de.steven.clitaskmanager.service.serialisierung.TaskSerializer;
import de.steven.clitaskmanager.userinterface.CLInterface;
import de.steven.clitaskmanager.userinterface.CommandExtractor;
import de.steven.clitaskmanager.userinterface.CommandParser;
import de.steven.clitaskmanager.serializer.JSONSerializer;

public class ApplicationFactory {

	CLInterface createApplication() {
		Scanner scanner = new Scanner(System.in);
		TaskSerializer translator = new JSONSerializer();
		TaskStorage storage = new InMemoryTaskStorage();
		CommandExtractor commandExtractor = new CommandExtractor();
		TaskService taskService = new TaskService(storage);
		CommandParser commandParser = new CommandParser(
				translator,
				taskService,
				taskService,
				taskService,
				taskService
		);


		return new CLInterface(
				scanner,
				translator,
				commandExtractor,
				commandParser
		);


	}
}

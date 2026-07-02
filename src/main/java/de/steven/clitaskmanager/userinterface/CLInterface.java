package de.steven.clitaskmanager.userinterface;

import java.util.Scanner;

import de.steven.clitaskmanager.userinterface.dto.UserCommand;
import de.steven.clitaskmanager.service.serialisierung.TaskSerializer;

public class CLInterface {
    private final Scanner scanner;
    private final CommandExtractor commandExtractor;
    private final TaskSerializer translator; //MUSS noch entfernt werden
    private final CommandParser commandParser;


    public CLInterface(
            Scanner scanner,
            TaskSerializer taskSerializer,
            CommandExtractor commandExtractor,
            CommandParser commandParser
    ) {
        this.scanner = scanner;
        this.translator = taskSerializer;
        this.commandExtractor = commandExtractor;
        this.commandParser = commandParser;
    }

    public void startProgram() {

        printStarterText();


        boolean running = true;
        while (running) {
            System.out.print("-> ");
            String userInput = this.scanner.nextLine();
            UserCommand commandInput = commandExtractor.extractCommand(userInput);
            boolean stillRunning = commandParser.applyCommand(commandInput, running);
            running = stillRunning;
       }
    }

    public void printStarterText() {
        String startText = """
                Welcome to CLI Task Manger!!

                As the name implise, this is a task manager.

                Tutorial:
                add task -t "Hausgaben machen" ; -b "muss am schreibtisch erledigt werden"
                
                update task -id 1 ; -t "putzen"; -b "käse"
                "add task -tn" - Adds new Task
                "add task -taskname " - Adds new Task
                "update task -id:[number]" - lets you update a task with the id
                "delete task -id:[number]" - delets task with the id
                "print all tasks" - prints all tasks
                "print task -id:[number] - prints a task with the chosen id
                "exit ctm" - to end programm

                ---------------------------------------------------------------
            
                """
        ;

        System.out.println(startText);

        System.out.println("TASK MANAGER CLI:");
    }
}

package de.steven.clitaskmanager;

import de.steven.clitaskmanager.userinterface.CLInterface;
import de.steven.clitaskmanager.userinterface.CommandExtractor;
import de.steven.clitaskmanager.userinterface.dto.CommandParameter;
import de.steven.clitaskmanager.userinterface.dto.UserCommand;

public class App {


    public static void main(String[] args) throws Exception {
        ApplicationFactory applicationFactory = new ApplicationFactory();
        CLInterface clInterface = applicationFactory.createApplication();
        clInterface.startProgram();

//        CommandExtractor extractor = new CommandExtractor();
//        String test = "add task -t;\"Testaufgabe\" -d;\"Testbeschreibung\"";
//        String t = "-t;\"test\"";
//
//        CommandParameter com = extractor.extractParameters(t);



    }

    
}

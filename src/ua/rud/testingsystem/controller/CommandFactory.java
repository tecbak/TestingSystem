package ua.rud.testingsystem.controller;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static CommandFactory instance = new CommandFactory();
    private Map<String, Command> commands = new HashMap<>();

    private CommandFactory() {
    }

    public static CommandFactory getInstance() {
        return instance;
    }

    /**
     * Provides a command according to request's parameter "command"
     *
     * @param wrapper a request wrapper containing request with "command" parameter
     * @return a command according to "command" parameter
     * or {@code null} if either request have no "command" parameter,
     * "command" parameter is empty or there's no such a command
     */
    public Command getCommand(RequestWrapper wrapper) {
        String action = wrapper.getRequestParameter("command");
        Command command;

        if (action == null || action.isEmpty()) {
            return null;
        }

        /*
         * If command has been already created - return it,
         * Otherwise, create new command, put it to the map storing commands
         * and return the command
         */
        if (commands.containsKey(action)) {
            command = commands.get(action);
        } else {
            String className = "ua.rud.testingsystem.controller.commands." + firstUpper(action) + "Command";
            try {
                command = (Command) Class.forName(className).newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
            commands.put(action, command);
        }

        return command;
    }

    private String firstUpper(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}

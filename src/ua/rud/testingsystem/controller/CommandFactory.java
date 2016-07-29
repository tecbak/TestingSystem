package ua.rud.testingsystem.controller;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * A factory for creating {@link Command}s
 */
public class CommandFactory {
    private static CommandFactory instance = new CommandFactory();
    private Map<String, Command> commands = new HashMap<>();
    private Logger logger;

    private CommandFactory() {
        this.logger = Logger.getLogger(this.getClass());
        this.logger.setLevel(Level.ERROR);
    }

    /**
     * Get instance of this factory
     *
     * @return instance of this factory
     */
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
                logger.error(e);
                return null;
            }
            commands.put(action, command);
        }

        return command;
    }

    /**
     * Make first letter capitalized
     *
     * @param expression a {@link String} to change the first letter to capital one
     * @return an expression with capitalized first letter
     */
    private String firstUpper(String expression) {
        return expression.substring(0, 1).toUpperCase() + expression.substring(1);
    }
}

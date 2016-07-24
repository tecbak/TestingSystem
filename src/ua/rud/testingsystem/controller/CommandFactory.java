package ua.rud.testingsystem.controller;

import ua.rud.testingsystem.controller.commands.*;
import ua.rud.testingsystem.controller.commands.navigation.LoginCommand;
import ua.rud.testingsystem.controller.commands.navigation.MenuCommand;
import ua.rud.testingsystem.controller.commands.navigation.RegisterCommand;
import ua.rud.testingsystem.controller.commands.navigation.TestCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static CommandFactory instance = new CommandFactory();
    private Map<String, Command> commands;

    private CommandFactory() {
        commands = new HashMap<String, Command>() {{
            put("login", new LoginCommand());
            put("authorization", new AuthorizationCommand());
            put("register", new RegisterCommand());
            put("registration", new RegistrationCommand());
            put("logout", new LogoutCommand());
            put("menu", new MenuCommand());
            put("start", new StartCommand());
            put("test", new TestCommand());
            put("complete", new CompleteCommand());
        }};
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
            command = null;
        } else {
            command = commands.get(action);
        }
        return command;
    }
}

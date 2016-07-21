package ua.rud.testingsystem.controller;

public class CommandFactory {
    public static CommandFactory instance = new CommandFactory();

    private CommandFactory() {
    }

    public static CommandFactory getInstance() {
        return instance;
    }

    public Command getCommand(RequestWrapper request) {
        String action = request.getRequestParameter("command");

        if (action == null || action.isEmpty()) {
            //// TODO: 17.07.2016 realization here
        }

        CommandEnum commandEnum = CommandEnum.valueOf(action.toUpperCase());
        return commandEnum.getCommand();
    }
}

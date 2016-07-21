package ua.rud.testingsystem.controller;

import ua.rud.testingsystem.controller.commands.*;

public enum CommandEnum {
    LOGIN {{
        command = new LoginCommand();
    }},

    AUTHORIZATION {{
        command = new AuthorizationCommand();
    }},

    REGISTER {{
        command = new RegisterCommand();
    }},

    REGISTRATION {{
        command = new RegistrationCommand();
    }},

    ERROR {{
        command = new ErrorCommand();
    }};

    Command command;
    public Command getCommand() {
        return command;
    }
}

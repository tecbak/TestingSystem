package ua.rud.testingsystem.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(request);

        System.out.println(Arrays.toString(request.getParameterValues("id")));


//        { // TODO: 22.07.2016 TESTS
//            MenuJdbc dao = MenuJdbc.getInstance();
//            List<Subject> subjects = dao.getSubjects();
//            for (Subject subject : subjects) {
//                System.out.println(subject);
//            }
//        }

        CommandFactory factory = CommandFactory.getInstance();
        Command command = factory.getCommand(requestWrapper);
        String page = command.execute(requestWrapper);

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}

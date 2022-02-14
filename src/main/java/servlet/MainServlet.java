package servlet;

import frontcontroller.CommandManager;
import frontcontroller.ServletCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {

    private CommandManager commandManager;

    public void init() throws ServletException {
        commandManager = new CommandManager();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GET");
        ServletCommand command = commandManager.getGetCommand(req);
        String loadedPage = command.execute(req, resp);
        req.getRequestDispatcher(loadedPage).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST");
        ServletCommand command = commandManager.getPostCommand(req);
        String loadedMapping = command.execute(req, resp);
        resp.sendRedirect(req.getContextPath() + loadedMapping);
    }
}
package Servlets;

import Logic.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by SmiLeX on 09.04.2018.
 */
@WebServlet(name = "CheckLogin", urlPatterns = {"/CheckLogin","/checklogin"})

public class CheckLogin extends HttpServlet
{

    private JDBConnector jdbConnector;
    private String login;
    private boolean check;


    private void initialize()
    {

        jdbConnector = new JDBConnector();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Check Login");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        initialize();

        if (request.getParameter("json") != null)
        {
            String json = request.getParameter("json");
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(json);
            login = jsonObject.get("login").toString().replace("\"", "");
            check = jdbConnector.checkUser(login);
        }


        Gson gson = new Gson();
        String jsonArr;
        jsonArr = gson.toJson(check ? 1 : 0);

        response.getWriter().println(jsonArr);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
}


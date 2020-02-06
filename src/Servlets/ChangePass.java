package Servlets;

import Logic.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jdk.nashorn.internal.parser.JSONParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by SmiLeX on 09.04.2018.
 */
@WebServlet(name = "ChangePass", urlPatterns = {"/ChangePass","/changepass"})

public class ChangePass extends HttpServlet
{

    private JDBConnector jdbConnector;
    private String login, newPass;
    private boolean check;

    private void initialize()
    {

        jdbConnector = new JDBConnector();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("POST");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        initialize();
        if (request.getParameter("json") != null)
        {
            String json = request.getParameter("json");
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(json);
            login = jsonObject.get("login").toString().replace("\"", "");
            newPass = jsonObject.get("pass").toString().replace("\"", "");

            try
            {
               check = jdbConnector.ChangePass(login, newPass);
            } catch (Exception e)
            {
                e.printStackTrace();
            }


            response.getWriter().print(check ? 1:0);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

}
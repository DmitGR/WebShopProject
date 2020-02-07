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


@WebServlet(name = "UserInfo", urlPatterns = {"/admin-panel/UserInfo","/admin-panel/userInfo"})

public class UserInfo extends HttpServlet
{

    private JDBConnector jdbConnector;
    private int userid;
    private User user;

    private void initialize()
    {
        jdbConnector = new JDBConnector();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("UserInfo");
        initialize();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        if (request.getParameter("json") != null)
        {
            String json = request.getParameter("json");
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(json);
            userid = Integer.parseInt(jsonObject.get("user").toString().replace("\"", ""));
        }

        try
        {

            user = jdbConnector.GetUserInfo(userid);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String jsonArr = gson.toJson(user);

        response.getWriter().print(jsonArr);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

}



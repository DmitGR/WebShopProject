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
@WebServlet(name = "Verification", urlPatterns = {"/Verification","/check"})

public class Verification extends HttpServlet
{
    
   private JDBConnector jdbConnector;
   private String code;
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
            code = jsonObject.get("code").toString().replace("\"", "");

        }

        check = jdbConnector.Verification(code);

        Gson gson = new Gson();
        String jsonArr = gson.toJson(check ? 1:0);
        response.getWriter().print(jsonArr);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
}


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
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by SmiLeX on 09.04.2018.
 */
@WebServlet(name = "Authorisation", urlPatterns = {"/Authorisation","/auth"})

public class Authorization extends HttpServlet
{

    private String login;
    private String email;
    private String password;

    private JDBConnector jdbConnector;
    private HttpSession session;

    private User user;


    private void initialize()
    {

        jdbConnector = new JDBConnector();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        session = request.getSession();
        initialize();

        if(request.getParameter("logout") != null)
        {
            session.invalidate();
            user=null;
           response.sendRedirect("index.jsp");
           return; 
        }
       else if (request.getParameter("json") != null)
        {
            String json = request.getParameter("json");
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(json);
            login = jsonObject.get("login").toString().replace("\"", "");
            password = jsonObject.get("pass").toString().replace("\"", "");

            user = jdbConnector.Authorisation(login, password);
        }
        else if(session!= null && session.getAttribute("auth")!= null)
        {
            user = jdbConnector.Authorisation(session.getAttribute("auth_login").toString(), session.getAttribute("auth_pass").toString());

          //  Gson gson = new Gson();
          //  String jsonArr = gson.toJson(user);
          //  response.getWriter().print(jsonArr);

            //  response.getWriter().println("Log: " + session.getAttribute("auth_login"));
            //  response.getWriter().println("Log: " + session.getAttribute("auth_pass"));
            //   user = jdbConnector.Authorisation(session.getAttribute("auth_login").toString(), session.getAttribute("auth_pass").toString());
        }
        
        if (user != null)
        {
            if(user.getVerified() > 0)
            {
                session.setAttribute("auth", true);
                session.setAttribute("auth_login", login);
                session.setAttribute("auth_pass", password);
            }
            Gson gson = new Gson();
            String jsonArr = gson.toJson(user);
            response.getWriter().print(jsonArr);
        } else
            response.getWriter().print(0);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
       
    }
}

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
import java.sql.SQLException;

@WebServlet(name = "Registration", urlPatterns = {"/Registration","/reg"})

public class Registration extends HttpServlet
{

    private final String mailSubject = "Письмо подтверждение регистрации";
    private final String mailBody = "Ваша ссылка для акивации учётной записи: http://localhost:8080/Verification.jsp?code=";

    private JDBConnector jdbConnector;
    private User newUser;
    private boolean status;


    private void initialize()
    {

        jdbConnector = new JDBConnector();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Registration");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        initialize();
        if (request.getParameter("json") != null)
        {
            status = false;
            String json = request.getParameter("json");
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(json);
            String login = jsonObject.get("login").toString().replace("\"", "");
            String password = jsonObject.get("password").toString().replace("\"", "");
            String surname = jsonObject.get("surname").toString().replace("\"", "");
            String name = jsonObject.get("name").toString().replace("\"", "");
            String email = jsonObject.get("email").toString().replace("\"", "");
            String phone = jsonObject.get("phone").toString().replace("\"", "");
            int type = Integer.parseInt(jsonObject.get("type").toString().replace("\"", ""));

            newUser = new User(login, password, surname, name, type, email,  phone);
        }

        try
        {
         status = jdbConnector.AddUser(newUser);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }


        try {
            MailSender.sendMail(new MailSender(),mailSubject,mailBody+HashCodeGen.MD5(newUser.getEmail()+newUser.getLogin()),newUser.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }


        Gson gson = new Gson();
        String jsonArr = gson.toJson(status ? 1 : 0);


        response.getWriter().print(jsonArr);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

/*    private void SendMail(String code)
    {
        try
        {
            MailSender sender = new MailSender();
            sender.sendMail(mailSubject,
                    mailBody + code,
                    newUser.getEmail());
        } catch (Exception ex)
        {
            System.out.println("Sending Error " + ex);
        }
        finally {
            System.out.println("Email send to " + newUser.getEmail());

        }
    }*/
}
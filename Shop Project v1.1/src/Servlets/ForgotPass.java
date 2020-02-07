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


@WebServlet(name = "ForgotPass", urlPatterns = {"/ForgotPass","/forgot"})

public class ForgotPass extends HttpServlet
{

    private JDBConnector jdbConnector;
    private String login, email, newPass ;
    private static final int passLenght = 6;


    private final String mailSubject = "Письмо для восстановления пароля";
    private final String mailBody = "Ваш новый пароль: ";

    private void initialize()
    {

        jdbConnector = new JDBConnector();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Send new Password ");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        initialize();
        if (request.getParameter("json") != null)
        {
            String json = request.getParameter("json");
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(json);
            login = jsonObject.get("login").toString().replace("\"", "");

            try
            {
                // Генерация нового пароля и отправка на почту
               newPass = CodeGenerator.randomString(passLenght);
                email = jdbConnector.NewPass(login, newPass);
                MailSender.sendMail(new MailSender(),mailSubject,mailBody+newPass,email);
            } catch (Exception e)
            {
                e.printStackTrace();
            }


            response.getWriter().print(email);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
}

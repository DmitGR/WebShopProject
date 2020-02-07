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
@WebServlet(name = "ForgotPass", urlPatterns = {"/ForgotPass","/forgot"})

public class ForgotPass extends HttpServlet
{

    private JDBConnector jdbConnector;
    private String login, email, newPass ;
    private static final int passLenght = 5;

    private final String mailUser = "bigdeal.webshop@gmail.com";
    private final String mailPass = "BigDealShop";
    private final String mailSubject = "Письмо для восстановления пароля";
    private final String mailBody = "Ваш новый пароль: ";

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

            try
            {
               newPass = CodeGenerator.randomString(passLenght);
                email = jdbConnector.NewPass(login, newPass);
                SendPass(email);
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

    private void SendPass(String email)
    {
        try
        {
            MailSender sender = new MailSender(mailUser, mailPass);
            sender.sendMail(mailSubject,
                    mailBody + newPass,
                    mailUser,
                    email);
        } catch (
                Exception ex)

        {
            System.out.println("Sending Error " + ex);
        }
    }
}

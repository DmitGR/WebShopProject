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

/**
 * Created by SmiLeX on 09.04.2018.
 */
@WebServlet(name = "Registration", urlPatterns = {"/Registration","/reg"})

public class Registration extends HttpServlet
{

    private final String mailUser = "bigdeal.webshop@gmail.com";
    private final String mailPass = "BigDealShop";
    private final String mailSubject = "Письмо подтверждение регистрации";
    private final String mailBody = "Ваша ссылка для акивации учётной записи: http://localhost:8070/Verification.jsp?code=";

    private JDBConnector jdbConnector;
    private User newUser;
    private boolean status;


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
            String login = jsonObject.get("login").toString().replace("\"", "");
            String password = jsonObject.get("password").toString().replace("\"", "");
            String surname = jsonObject.get("surname").toString().replace("\"", "");
            String name = jsonObject.get("name").toString().replace("\"", "");
            String email = jsonObject.get("email").toString().replace("\"", "");
            String phone = jsonObject.get("phone").toString().replace("\"", "");

            newUser = new User(login, password, surname, name, email, phone);
        }

        try
        {
         status = jdbConnector.AddUser(newUser);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        SendMail(HashCodeGen.MD5(newUser.getEmail()));


        Gson gson = new Gson();
        String jsonArr = gson.toJson(status ? 1 : 0);


        response.getWriter().print(jsonArr);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    private void SendMail(String code)
    {
        try
        {
            MailSender sender = new MailSender(mailUser, mailPass);
            sender.sendMail(mailSubject,
                    mailBody + code,
                    mailUser,
                    newUser.getEmail());
        } catch (Exception ex)
        {
            System.out.println("Sending Error " + ex);
        }
    }
}
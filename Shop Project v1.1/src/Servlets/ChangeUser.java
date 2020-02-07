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
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet(name = "ChangeUser", urlPatterns = {"/admin-panel/ChangeUser","/admin-panel/changeUser"})

public class ChangeUser extends HttpServlet
{

    private JDBConnector jdbConnector;
    private int check;

    private final String mailSubject = "Ваш профиль был изменён администратором";
    private final String mailBody = "Ваша профиль успешно изменён. \n Можете проверить http://localhost:8080/index.jsp";

    private void initialize()
    {

        jdbConnector = new JDBConnector();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Change User Profile");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        initialize();
        if (request.getParameter("json") != null)
        {
            check = -1;
            String json = request.getParameter("json");
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(json);
            Integer id = Integer.parseInt(jsonObject.get("id").toString().replace("\"", ""));
            String name = jsonObject.get("name").toString().replace("\"", "");
            String second_name = jsonObject.get("surname").toString().replace("\"", "");
            String patronymic = jsonObject.get("patronymic").toString().replace("\"", "");
            String email = jsonObject.get("email").toString().replace("\"", "");
            BigDecimal balance = BigDecimal.valueOf(Double.parseDouble(jsonObject.get("balance").toString().replace("\"", "")));
            String phone = jsonObject.get("phone").toString().replace("\"", "");
            try
            {
                check = jdbConnector.ChangeUserInfo(new User(id,second_name,name,patronymic,email,phone,balance));
                if(check > 0)
                    MailSender.sendMail(new MailSender(),mailSubject,mailBody,jdbConnector.getUserEmail(check));
            } catch (Exception e)
            {
                e.printStackTrace();
            }


            response.getWriter().print(check > 0);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

}
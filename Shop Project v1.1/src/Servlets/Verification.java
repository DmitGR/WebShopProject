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


@WebServlet(name = "Verification", urlPatterns = {"/Verification","/GetSession"})

public class Verification extends HttpServlet
{
    
   private JDBConnector jdbConnector;
   private String code;
   private int check;

    String mailSubject = "Письмо подтверждение почты";
    String mailBody = "Ваша почта успешно подтверждена. \n Можете авторизоваться http://localhost:8080/index.jsp";

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
            check = 0;
            String json = request.getParameter("json");
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(json);
            code = jsonObject.get("code").toString().replace("\"", "");

        }

        check = jdbConnector.Verification(code);

       if(check > 0) {
           try {
               MailSender.sendMail(new MailSender(),mailSubject,mailBody,jdbConnector.getUserEmail(check));
           } catch (Exception e) {
               e.printStackTrace();
           }
       }


        Gson gson = new Gson();
        String jsonArr = gson.toJson(check > 0);
        response.getWriter().print(jsonArr);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
    /*private void SendMail(String email)
    {
        try
        {
            MailSender sender = new MailSender();
            String mailSubject = "Письмо подтверждение почты";
            String mailBody = "Ваша почта успешно подтверждена. \n Можете авторизоваться http://localhost:8080/index.jsp";
            sender.sendMail(mailSubject,
                    mailBody,
                    email);
        } catch (Exception ex)
        {
            System.out.println("Sending Error " + ex);
        }
        finally {
            System.out.println("Email send to " + email);

        }
    }*/
}


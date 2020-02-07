package Servlets;

import Logic.JDBConnector;
import Logic.MailSender;
import Logic.Product;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddProduct", urlPatterns = {"/AddProduct","/addProduct"})

public class AddProduct extends HttpServlet
{

    private JDBConnector jdbConnector;
    private int user_id, subcateg_id, price,count;
    private int check;
    private String name, description, img_url;
    private final String mailSubject = "WebShop добавление товара";
    private final String mailBody = "Ваша товар успешно добавлен на продажу. \nWebShop: http://localhost:8080/index.jsp";

    private void initialize()
    {
        jdbConnector = new JDBConnector();
        // Default photo
        img_url = "/img/photo.png";
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("AddProduct");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        initialize();

        if (request.getParameter("json") != null)
        {
            String json = request.getParameter("json");
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(json);

            subcateg_id = Integer.parseInt(jsonObject.get("subcateg_id").toString().replace("\"", ""));
            user_id = Integer.parseInt(jsonObject.get("user_id").toString().replace("\"", ""));
            price = Integer.parseInt(jsonObject.get("price").toString().replace("\"", ""));
            count = Integer.parseInt(jsonObject.get("count").toString().replace("\"", ""));


            name = jsonObject.get("name").toString().replace("\"", "");
            description = jsonObject.get("description").toString().replace("\"", "");
            img_url = "/img/upload_img/"+jsonObject.get("img").toString().replace("\"", "");

        }

        try
        {

            check = jdbConnector.AddProduct(new Product(subcateg_id,user_id,price,count,name,description,img_url));
            if(check>0)
                MailSender.sendMail(new MailSender(),mailSubject,mailBody,jdbConnector.getUserEmail(check));


        } catch (Exception e)
        {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String jsonArr = gson.toJson(check > 0);

        response.getWriter().print(jsonArr);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

}



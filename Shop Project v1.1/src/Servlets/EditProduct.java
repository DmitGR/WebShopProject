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

@WebServlet(name = "EditProduct", urlPatterns = {"/EditProduct","/editProduct"})

public class EditProduct extends HttpServlet
{

    private JDBConnector jdbConnector;
    private int prod_id, user_id, subcateg_id, price, count;
    private int check;
    private String name, description, img_url;
    private final String mailSubject = "WebShop редактирование товара";
    private final String mailBody = "Информация о вашем товар успешно обновлена. \nWebShop: http://localhost:8080/index.jsp";

    private void initialize()
    {
        jdbConnector = new JDBConnector();
        // Default photo
        img_url = "/img/photo.png";
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("EditProduct");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        initialize();

        if (request.getParameter("json") != null)
        {
            String json = request.getParameter("json");
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(json);

            prod_id = Integer.parseInt(jsonObject.get("prod_id").toString().replace("\"", ""));
            subcateg_id = Integer.parseInt(jsonObject.get("subcateg_id").toString().replace("\"", ""));
            price = Integer.parseInt(jsonObject.get("price").toString().replace("\"", ""));
            count = Integer.parseInt(jsonObject.get("count").toString().replace("\"", ""));


            name = jsonObject.get("name").toString().replace("\"", "");
            description = jsonObject.get("description").toString().replace("\"", "");
            if(description.length() >= 500)
                description = jsonObject.get("description").toString().replace("\"", "").substring(0,500);

            img_url = "/img/upload_img/"+jsonObject.get("img").toString().replace("\"", "");

        }

        try
        {

            check = jdbConnector.EditProduct(new Product(prod_id,img_url,name,price,description,subcateg_id,count));
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



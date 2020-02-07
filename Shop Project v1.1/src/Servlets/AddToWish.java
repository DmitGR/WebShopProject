package Servlets;

import Logic.JDBConnector;
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

@WebServlet(name = "AddToWish", urlPatterns = {"/AddToWish","/addToWish"})

public class AddToWish extends HttpServlet
{

    private JDBConnector jdbConnector;
    private int user_id, product_id;
    private boolean check;

    private void initialize()
    {
        jdbConnector = new JDBConnector();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("AddToWishProduct");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        initialize();

        if (request.getParameter("json") != null)
        {
            String json = request.getParameter("json");
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(json);
            product_id =  Integer.parseInt(jsonObject.get("product_id").toString().replace("\"", ""));
            user_id = Integer.parseInt(jsonObject.get("user_id").toString().replace("\"", ""));
        }

        try
        {
            check = jdbConnector.AddToWish(user_id,product_id);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String jsonArr = gson.toJson(check ? 1 : 0);

        response.getWriter().print(jsonArr);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

}



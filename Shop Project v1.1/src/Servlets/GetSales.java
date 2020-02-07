package Servlets;

import Logic.JDBConnector;
import Logic.Product;
import Logic.Sale;
import Logic.Sort;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetSales", urlPatterns = {"/GetSales","/getSales","/admin-panel/GetSales"})

public class GetSales extends HttpServlet
{

    private ObservableList<Sale> items;
    private JDBConnector jdbConnector;
    private int user_id;

    private void initialize()
    {

        jdbConnector = new JDBConnector();
        items = FXCollections.observableArrayList();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("GetSales");
        initialize();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        if (request.getParameter("json") != null && !request.getParameter("json").equals(""))
        {
            String json = request.getParameter("json");
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(json);

            user_id = Integer.parseInt(jsonObject.get("id").toString().replace("\"", ""));
        }

        try
        {
            items = jdbConnector.GetSales(user_id);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String jsonArr = gson.toJson(items);

        response.getWriter().print(jsonArr);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

}


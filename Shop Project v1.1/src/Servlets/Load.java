package Servlets;

import Logic.*;
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

@WebServlet(name = "Load", urlPatterns = {"/Load","/load"})

public class Load extends HttpServlet
{

    private ObservableList<Product> items;
    private JDBConnector jdbConnector;
    private int sort , category, subcategory, page_limit;


    private void initialize()
    {
        jdbConnector = new JDBConnector();
        items = FXCollections.observableArrayList();
        page_limit = 20;
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Load");
        initialize();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        int page = 0;
        if (request.getParameter("json") != null)
        {
            String json = request.getParameter("json");
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(json);

            sort = Integer.parseInt(jsonObject.get("filterSort").toString().replace("\"", ""));
            category = Integer.parseInt(jsonObject.get("category").toString().replace("\"", ""));
            subcategory = Integer.parseInt(jsonObject.get("subcategory").toString().replace("\"", ""));
            page = Integer.parseInt(jsonObject.get("page").toString().replace("\"", ""));

        }

        try{

            items = jdbConnector.GetSortedData(sort,category,subcategory, page, page_limit);

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


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
@WebServlet(name = "Search", urlPatterns = {"/Search","/search"})

public class Search extends HttpServlet
{

   private ObservableList<Product> items;
   private JDBConnector jdbConnector;
   private StringBuffer data;
   private String search;

    private void initialize()
    {

        jdbConnector = new JDBConnector();
        data = new StringBuffer();
        items = FXCollections.observableArrayList();
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
            search = jsonObject.get("search").toString().replace("\"", "");
        }

        try
        {  
            items = jdbConnector.GetData(search);

        } catch (SQLException e)
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


  //  public void responseToJS(HttpServletResponse response) throws IOException
  //  {
  //      Gson gson = new Gson();
  //      String gson_1 = gson.toJson(list_I_N_D);
  //      String gson_2 = gson.toJson(list_I_T);
  //      String[] jsonArr = {gson_1, gson_2};
  //      String gsonReturn = gson.toJson(jsonArr);
  //      response.getWriter().write(gsonReturn);
  //  }


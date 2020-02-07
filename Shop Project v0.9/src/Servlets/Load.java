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
@WebServlet(name = "Load", urlPatterns = {"/Load","/test"})

public class Load extends HttpServlet
{

    private ObservableList<Product> items;
    private JDBConnector jdbConnector;
    private StringBuffer data;
    private String type, sort, system;
    private String[] brand;
    private int min_price, max_price;
    private boolean New, Sale, Leader;

    private void initialize()
    {

        jdbConnector = new JDBConnector();
        data = new StringBuffer();
        items = FXCollections.observableArrayList();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("POST");
        initialize();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

      
        if (request.getParameter("json") != null)
        {
            String json = request.getParameter("json");
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(json);
            type = jsonObject.get("typeSort").toString().replace("\"", "");
            brand = jsonObject.get("manufacturersSort").toString().replace("\"", "").replace("[", "").replace("]", "").split(",");
            sort = jsonObject.get("filterSort").toString().replace("\"", "");
            system = jsonObject.get("osSort").toString().replace("\"", "");
            min_price = Integer.parseInt(jsonObject.get("min_price").toString().replace("\"", ""));
            max_price = Integer.parseInt(jsonObject.get("max_price").toString().replace("\"", ""));
            New = Boolean.parseBoolean(jsonObject.get("hotSortNew").toString().replace("\"", ""));
            Sale = Boolean.parseBoolean(jsonObject.get("hotSortSale").toString().replace("\"", ""));
            Leader = Boolean.parseBoolean(jsonObject.get("hotSortLeader").toString().replace("\"", ""));

        }

        try
        {                    // Тут возможности вывод, можно по сервлетам раскидать
            // items = jdbConnector.GetData();                              
            // items = jdbConnector.GetCategoryData(Category.Tablet);
            //items = jdbConnector.getFilteredData("none",new String[] {},"name-desc", "iOS",0,0,false,false,false);
            items = jdbConnector.getFilteredData(type, brand, sort, system, min_price, max_price, New, Sale, Leader);

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


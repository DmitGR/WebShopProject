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
import java.sql.SQLException;

@WebServlet(name = "UsersSearch", urlPatterns = {"/admin-panel/UsersSearch","/usersSearch"})

public class UsersSearch extends HttpServlet {

    private ObservableList<User> users;
    private JDBConnector jdbConnector;
    private String search;


    private void initialize() {
        jdbConnector = new JDBConnector();
        users = FXCollections.observableArrayList();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UsersSearch");
        initialize();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        if (request.getParameter("json") != null) {
            String json = request.getParameter("json");
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(json);
            if (!jsonObject.get("All").getAsBoolean()) {
                search = jsonObject.get("search").toString().replace("\"", "");
                try {

                    users = jdbConnector.SearchUsers(search);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    users = jdbConnector.SearchUsers("");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        Gson gson = new Gson();
        String jsonArr = gson.toJson(users);

        response.getWriter().print(jsonArr);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
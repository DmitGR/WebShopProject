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

@WebServlet(name = "BuyProduct", urlPatterns = {"/BuyProduct","/buyProduct"})

public class BuyProduct extends HttpServlet
{

    private JDBConnector jdbConnector;
    private int prod_id,seller_id,buyer_id, price,count;
    private String address;
    private int check;
    private final String mailSubjectSeller = "WebShop Покупка товара";
    private final String mailSubjectBuyer = "WebShop Заказ товара";
    private final String mailBodySeller = ".\n Адрес Доставки: ";
    private final String mailBodyBuyer = "Ваша заказ успешно оформлен.\n Адрес Доставки: ";
    private void initialize()
    {
        jdbConnector = new JDBConnector();
        check = -1;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("BuyProduct");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        initialize();

        if (request.getParameter("json") != null)
        {
            String json = request.getParameter("json");
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(json);

            prod_id = Integer.parseInt(jsonObject.get("prod_id").toString().replace("\"", ""));
            seller_id = Integer.parseInt(jsonObject.get("seller_id").toString().replace("\"", ""));
            buyer_id = Integer.parseInt(jsonObject.get("buyer_id").toString().replace("\"", ""));
            address = jsonObject.get("address").toString().replace("\"", "");
            price = Integer.parseInt(jsonObject.get("price").toString().replace("\"", ""));
            count = Integer.parseInt(jsonObject.get("count").toString().replace("\"", ""));

        }

        try
        {
            // Отправка на почту покупателя и продавца
            if(!address.equals(""))
                check = jdbConnector.BuyProduct(prod_id,buyer_id,address,price,count);
            if(check>0) {
                MailSender.sendMail(new MailSender(), mailSubjectBuyer, mailBodyBuyer + address + "\nНомер заказа: " + check, jdbConnector.getUserEmail(buyer_id));
                MailSender.sendMail( new MailSender(), mailSubjectSeller, "У вас новый заказ: "+jdbConnector.GetProductInfo(prod_id).getName() + "Количество: "+ count+
                mailBodySeller + address,jdbConnector.getUserEmail(seller_id));
            }


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



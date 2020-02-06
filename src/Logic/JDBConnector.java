package Logic;

import Logic.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.*;
import java.sql.Date;
import java.util.*;

public class JDBConnector
{
    private static String URL;
    private static String USER;
    private static String PASSWORD;
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement preparedStatement;

    public JDBConnector()
    {
        URL = "jdbc:mysql://localhost:3306/mysql?useSSL=false";
        USER = "root";
        PASSWORD = "root";
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
        } catch (ClassNotFoundException e)
        {
            System.out.println("Error in driver connection: " + e);
        } catch (SQLException e)
        {
            System.out.println("Error in connection to DB: " + e);
        }
    }
    
    public ObservableList<Product> GetData(String search) throws SQLException
    {
        ObservableList<Product> temp = FXCollections.observableArrayList();
        try
        {
            String query = "SELECT * FROM db_shop.products where name Like '%" + search + "%' or brand LIKE '%" + search + "%' or mini_description LIKE '%" + search + "%' or full_description LIKE '%" + search +
                    "%' or features LIKE '%" + search + "%'";

            // statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery(query);
            System.out.println("From base");

            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String image = resultSet.getString("image");
                String system = resultSet.getString("operationSystem");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String brand = resultSet.getString("brand");
                String mini_description = resultSet.getString("mini_description");
                String full_description = resultSet.getString("full_description");
                String features = resultSet.getString("features");
                Date dateTime = resultSet.getDate("DateTime");
                int isNew = resultSet.getInt("new");
                int isLeader = resultSet.getInt("leader");
                int isSale = resultSet.getInt("sale");
                String type = resultSet.getString("type");
                System.out.println("ID: " + id + " Name: " + name);

                temp.add(new Product(id, image, name, price, brand, mini_description, full_description,
                        features, dateTime, isNew, isLeader, isSale, type, system));

            }

        } catch (SQLException e)
        {
            System.out.println("Error in get data: " + e);
        }
        return temp;
    }

    public ObservableList<Product> getFilteredData(String type, String[] brand, String sort, String system, int min_price, int max_price, boolean New, boolean Sale, boolean Leader) throws SQLException
    {
        ObservableList<Product> temp = FXCollections.observableArrayList();
        ObservableList<Product> list = getSortedData(sort);

        if (!type.equals("none") && !type.equals(""))
        {
            for (Product item : list
                    )
            {
                if (item.getType().equals(type))
                {
                    temp.add(item);
                }
            }
            list = temp;
            temp = FXCollections.observableArrayList();
        }

        if (brand.length > 0 && !brand[0].equals(""))
        {
            for (Product item : list
                    )
            {
                for (int i = 0; i < brand.length;i++)
                {
                    if (item.getBrand().equals(brand[i]))
                    {
                        temp.add(item);
                    }
                }
            }
            list = temp;
            temp = FXCollections.observableArrayList();
        }
        
        if(!system.equals("none") && !system.equals(""))
        {
            for (Product item : list
                    )
            {
                if (item.getSystem().equals(system))
                {
                    temp.add(item);
                }
            }
            list = temp;
            temp = FXCollections.observableArrayList();
        }

        if (max_price > 0)
        {
            for (Product item : list
                    )
            {
                if (item.getPrice() >= min_price && item.getPrice() <= max_price)
                {
                    temp.add(item);
                }
            }
            list = temp;
            temp = FXCollections.observableArrayList();
        }

        if (New)
        {
            for (Product item : list
                    )
            {
                if (item.getIsNew() == 1)
                {
                    temp.add(item);
                }
            }
            list = temp;
            temp = FXCollections.observableArrayList();
        }

        if (Sale)
        {
            for (Product item : list
                    )
            {
                if (item.getIsSale() == 1)
                {
                    temp.add(item);
                }
            }
            list = temp;
            temp = FXCollections.observableArrayList();
        }

        if (Leader)
        {
            for (Product item : list
                    )
            {
                if (item.getIsLeader() == 1)
                {
                    temp.add(item);
                }
            }
            list = temp;
            temp = FXCollections.observableArrayList();
        }

        return list;


    }

    public ObservableList<Product> getSortedData(String sort) throws SQLException
    {
        ObservableList<Product> temp = FXCollections.observableArrayList();
        String query = "SELECT * from db_shop.Products ";
        switch (sort)
        {
            case "name-asc":
                query += "ORDER BY name ASC";
                break;
            case "name-desc":
                query += "ORDER BY name DESC";
                break;
            case "price-asc":
                query += "ORDER BY price ASC";
                break;
            case "price-desc":
                query += "ORDER BY price DESC";
                break;
            case "date":
                query += "ORDER BY datetime DESC";
                break;
        }
        
        try                
        {
            resultSet = statement.executeQuery(query);
            System.out.println("From base");

            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String image = resultSet.getString("image");
                String system = resultSet.getString("operationSystem");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String brand = resultSet.getString("brand");
                String mini_description = resultSet.getString("mini_description");
                String full_description = resultSet.getString("full_description");
                String features = resultSet.getString("features");
                Date dateTime = resultSet.getDate("DateTime");
                int isNew = resultSet.getInt("new");
                int isLeader = resultSet.getInt("leader");
                int isSale = resultSet.getInt("sale");
                String type = resultSet.getString("type");
                System.out.println("ID: " + id + " Name: " + name);

                temp.add(new Product(id, image, name, price, brand, mini_description, full_description,
                        features, dateTime, isNew, isLeader, isSale, type, system));

            }

        } catch (SQLException e)
        {
            System.out.println("Error in get data: " + e);
        }
        return temp;
    }
    
    public Product getByID(int ID) throws SQLException
    {
        String query = "SELECT * FROM db_shop.products where id = "+ID;
        resultSet = statement.executeQuery(query);
        System.out.println("From base");
        resultSet.next();
            int id = resultSet.getInt("id");
            String image = resultSet.getString("image");
            String system = resultSet.getString("operationSystem");
            String name = resultSet.getString("name");
            int price = resultSet.getInt("price");
            String brand = resultSet.getString("brand");
            String mini_description = resultSet.getString("mini_description");
            String full_description = resultSet.getString("full_description");
            String features = resultSet.getString("features");
            Date dateTime = resultSet.getDate("DateTime");
            int isNew = resultSet.getInt("new");
            int isLeader = resultSet.getInt("leader");
            int isSale = resultSet.getInt("sale");
            String type = resultSet.getString("type");
            System.out.println("ID: " + id + " Name: " + name);
        

            return new Product(id, image, name, price, brand, mini_description, full_description,
                    features, dateTime, isNew, isLeader, isSale, type, system);
            
    }
    
    public void Clear()
    {
        try
        {
            statement.execute("DELETE from db_shop.Products");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void SetData(Logic.Product Product) throws SQLException
    {
        try
        {
            String INSERT = "INSERT INTO db_shop.products (name, price, category) VALUES (?,?,?)";
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, Product.getName());
            preparedStatement.setDouble(2,Product.getPrice());
            preparedStatement.setString(3,Product.getCategory());
            preparedStatement.execute();
            UpdateID();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            preparedStatement.close();
        }
    }

    public boolean AddUser(User user) throws SQLException
    {
        try
        {                                                                                                                   //1 2 3 4 5 6 7 8 9 0
            String INSERT = "INSERT INTO db_shop.users (login, password, surname, name, email, phone, verified, code) VALUES (?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, HashCodeGen.MD5(user.getPassword()));
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.setInt(7, 0);
            preparedStatement.setString(8, HashCodeGen.MD5(user.email));
            preparedStatement.execute();
            UpdateID();
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        } finally
        {
            preparedStatement.close();
            return true;
        }
    }
    
    public boolean Verification(String code)
    {
        int userID = 0;
        try
        {
            String query = "SELECT * FROM db_shop.users WHERE code = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,code);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            userID = resultSet.getInt("id");
            if (userID != 0)
            {
                query = "UPDATE db_shop.users SET code = null , verified = 1 WHERE id = "+ userID;
                statement.execute(query);
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return userID != 0;
    }

    public User Authorisation(String login, String password)
    {
        int userID = 0;
        int check = 0;
        String pass = HashCodeGen.MD5(password);
        try
        {
            String query = "SELECT * FROM db_shop.users WHERE (login = ?  OR email = ? ) AND password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, pass);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            userID = resultSet.getInt("id");
            check = resultSet.getInt("verified");
            if (userID != 0)
            {
                login = resultSet.getString("login");
                String email = resultSet.getString("email");
                String surname = resultSet.getString("surname");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");

                return new User(userID, check, login, password, surname, name, email, phone);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;

    }

    public String NewPass(String login, String newPass)
    {
        int userID = 0;
        try
        {
            String query = "SELECT * FROM db_shop.users WHERE login = ?  OR email = ? ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, login);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            userID = resultSet.getInt("id");
            String email;
            if (userID != 0)
            {
                email = resultSet.getString("email");
                query = "UPDATE db_shop.users set password = ? WHERE email = ? ";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, HashCodeGen.MD5(newPass));
                preparedStatement.setString(2, email);

                preparedStatement.execute();
                return email;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;

    }
    
    public boolean ChangePass(String login, String newPass)
    {
        String query = "UPDATE db_shop.users set password = ? WHERE login = ? ";
        try
        {
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, HashCodeGen.MD5(newPass));
        preparedStatement.setString(2, login);
        preparedStatement.execute();
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            return true;
        }
    }
    
    public boolean checkUser(String data)
    {
        int userID = 0;
        try
        {
            String query = "SELECT * FROM db_shop.users WHERE email = ? || login = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, data);
            preparedStatement.setString(2, data);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            userID = resultSet.getInt("id");               

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return userID != 0;
    }
    

    public void UpdateData(Product Product, int id) throws SQLException
    {
        try
        {
            String INSERT = "UPDATE product_list.products set name = ?, price = ?,category = ?, WHERE id = " + id;
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, Product.getName());
            preparedStatement.setDouble(2,Product.getPrice());
            preparedStatement.setString(3,Product.getCategory());
            preparedStatement.execute();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            preparedStatement.close();
        }
    }

    public void UpdateID()
    {
        try
        {
            statement.addBatch("SET @id=0;");
            statement.addBatch("update db_shop.users set id=@id:=@id+1 ");
            statement.executeBatch();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void Delete(Product product,int id)
    {
        try
        {
            String INSERT = "DELETE from product_list.products WHERE name = ? AND price = ? AND category = ? AND id = " + id ;
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getCategory());
            preparedStatement.execute();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                preparedStatement.close();
            } catch (SQLException e)
            {
                System.out.println("Delete error");
                e.printStackTrace();
            }
        }
    }
}

package Logic;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
/**
* Класс для работы с баззой данных
 */
public class JDBConnector {
    private static String URL;
    private static String USER;
    private static String PASSWORD;
    private static Connection connection; // Класс для установления соединения с БД
    private static Statement statement; // Класс для выполнения простых запросов
    private static PreparedStatement preparedStatement; // Класс для выполнения запросов c передачей данных
    private static CallableStatement callableStatement ;// Класс для выполнения запросов к хранимым процедурам
    private static ResultSet resultSet; // Класс для хранения полученных данных


    public JDBConnector() {

        URL = "jdbc:mysql://localhost:3306/web_shop?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        USER = "root";
        PASSWORD = "toor";
        try {
            Class.forName("com.mysql.jdbc.Driver"); // Подключение JDB драйвера
            connection = DriverManager.getConnection(URL, USER, PASSWORD); // Установка соединения
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            System.out.println("Error in driver connection: " + e);
        } catch (SQLException e) {
            System.out.println("Error in connection to DB: " + e);
        }
    }

    public ObservableList<Product> Search(String search) throws SQLException {
        ObservableList<Product> temp = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM web_shop.products where name Like '%" + search + "%' or products.description LIKE '%" + search + "%' or products.price LIKE '%" + search + "%' or products.date LIKE '%" + search +
                    "%' or products.seller_id LIKE '%" + search + "%'";

            // statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery(query);
            System.out.println("From base");
            while (resultSet.next()) {
                if (resultSet.getRow() > 0) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    String mini_description = "";

                    if (description.length() > 115)
                        mini_description = description.substring(0, 115) + "...";
                    else
                        mini_description = description;
                    int price = resultSet.getInt("price");
                    String img_url = resultSet.getString("img_url");
                    int subcategory_id = resultSet.getInt("subcategory_id");
                    int count = resultSet.getInt("count");
                    Date dateTime = resultSet.getDate("date");
                    System.out.println("ID: " + id + " Name: " + name);
                    temp.add(new Product(id, img_url, name, price, mini_description, description, dateTime, subcategory_id, count));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error in get data: " + e);
        }
        return temp;
    }

    public ObservableList<Product> GetSortedData(int sort, int categ_id, int subcat_id, int page, int page_limit) throws SQLException {
        ObservableList<Product> temp = FXCollections.observableArrayList();
        ObservableList<Product> ref = FXCollections.observableArrayList();

        try {
            String query = "";
            if (subcat_id > 0) {
                query = "{call getSubCategoryProducts(?,?) }";
                callableStatement = connection.prepareCall(query);

                callableStatement.setInt("subCategory_id", subcat_id);
                callableStatement.setInt("sort", sort);
            } else if (categ_id > 0) {
                query = "{call getCategoryProducts(?,?) }";
                callableStatement = connection.prepareCall(query);
                callableStatement.setInt("category_id", categ_id);
                callableStatement.setInt("sort", sort);
            } else {
                query = "{call getAllProducts(?) }";
                callableStatement = connection.prepareCall(query);
                callableStatement.setInt("sort", sort);
            }

            // statement = connection.prepareStatement(query);

            resultSet = callableStatement.executeQuery();
            System.out.println("From base");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String mini_description = "";

                if (description.length() > 115)
                    mini_description = description.substring(0, 115) + "...";
                else
                    mini_description = description;

                int price = resultSet.getInt("price");
                String img_url = resultSet.getString("img_url");
                int category_id = resultSet.getInt("category_id");
                int subcategory_id = resultSet.getInt("subcategory_id");
                int count = resultSet.getInt("count");
                Date dateTime = resultSet.getDate("date");
                System.out.println("ID: " + id + " Name: " + name);
                temp.add(new Product(id, img_url, name, price, mini_description, description, dateTime, category_id, subcategory_id, count));
            }


            page*=page_limit;
            for (int i = page-page_limit; i < page_limit; i++) {
                if(page_limit > temp.size())
                    page_limit = temp.size();
                ref.add(temp.get(i));
            }

        } catch (SQLException e) {
            System.out.println("Error in get data: " + e);
        }
        return ref;
    }

    public Product GetProductInfo(int id) {
        Product temp = null;
        try {

            String query = "{call getProductInfo(?) }";
            callableStatement = connection.prepareCall(query);

            callableStatement.setInt("id", id);

            resultSet = callableStatement.executeQuery();
            System.out.println("From base");
            while (resultSet.next()) {

                String img_url = resultSet.getString("img_url");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String description = resultSet.getString("description");
                Date dateTime = resultSet.getDate("date");
                int count = resultSet.getInt("count");
                String sellerName = resultSet.getString("sellerName") + " " + resultSet.getString("sellerSecName");
                int sellerID = resultSet.getInt("sellerID");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                int subcategory = resultSet.getInt("Sub");
                int category = resultSet.getInt("Categ");


                System.out.println("ID: " + id + " Name: " + name);
                temp = new Product(id, img_url, name, price, description, dateTime, count, subcategory, category, new User(sellerID, sellerName, email, phone));
            }

        } catch (SQLException e) {
            System.out.println("Error in get data: " + e);
        }
        return temp;
    }

    public boolean AddUser(User user) throws SQLException {
        try {                                                                                                                   //1 2 3 4 5 6 7 8 9 0
            String INSERT = "INSERT INTO web_shop.users (name, second_name, type, balance, login, email,pass_hash,confirmed,phone, code) VALUES (?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getType());
            preparedStatement.setBigDecimal(4, user.getBalance());
            preparedStatement.setString(5, user.getLogin());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, HashCodeGen.MD5(user.getPassword()));
            preparedStatement.setInt(8, user.getVerified());
            preparedStatement.setString(9, user.getPhone());
            preparedStatement.setString(10, HashCodeGen.MD5(user.getEmail() + user.getLogin()));

            preparedStatement.execute();
            //  UpdateID();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            preparedStatement.close();
            return true;
        }
    }

    public int Verification(String code) {
        int userID = 0;
        try {
            String query = "SELECT * FROM web_shop.users WHERE code = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, code);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (resultSet.getRow() > 0)
                userID = resultSet.getInt("id");
            if (userID != 0) {
                query = "UPDATE web_shop.users SET code = null , confirmed = 1 WHERE id = " + userID;
                statement.execute(query);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userID;
    }

    public User Authorisation(String login, String password) {
        int userID;
        int check;
        String pass = HashCodeGen.MD5(password);
        try {
            String query = "SELECT * FROM web_shop.users WHERE (login = ?  OR email = ? ) AND pass_hash = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, pass);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (resultSet.getRow() > 0) {
                userID = resultSet.getInt("id");
                check = resultSet.getInt("confirmed");
                login = resultSet.getString("login");
                String email = resultSet.getString("email");
                String surname = resultSet.getString("second_name");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                BigDecimal balance = resultSet.getBigDecimal("balance");
                int type = resultSet.getInt("type");


                return new User(userID, check, login, password, surname, name, email, phone, balance, type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public String NewPass(String login, String newPass) {
        int userID = 0;
        try {
            String query = "SELECT * FROM web_shop.users WHERE login = ?  OR email = ? ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, login);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            userID = resultSet.getInt("id");
            String email;
            if (userID != 0) {
                email = resultSet.getString("email");
                query = "UPDATE web_shop.users SET pass_hash = ? WHERE email = ? ";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, HashCodeGen.MD5(newPass));
                preparedStatement.setString(2, email);

                preparedStatement.execute();
                return email;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public int ChangePass(String login, String newPass) {
        int userID = -1;
        String query = "UPDATE web_shop.users SET pass_hash = ? WHERE login = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, HashCodeGen.MD5(newPass));
            preparedStatement.setString(2, login);
            preparedStatement.execute();
            query = "SELECT id FROM web_shop.users WHERE login = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (resultSet.getRow() > 0)
                userID = resultSet.getInt("id");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userID;
    }

    public boolean checkUser(String data) {
        int userID = 0;
        try {
            String query = "SELECT * FROM web_shop.users WHERE email = ? || login = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, data);
            preparedStatement.setString(2, data);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (resultSet.getRow() > 0)
                userID = resultSet.getInt("id");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userID != 0;
    }

    public String getUserEmail(int id) {
        String email = "";
        try {
            String query = "SELECT * FROM web_shop.users WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (resultSet.getRow() > 0)
                email = resultSet.getString("email");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return email;
    }

    public ObservableList<Product> GetProductList(int id) {

        ObservableList<Product> temp = FXCollections.observableArrayList();
        try {
            String query = "{call getProductList(?) }";
            callableStatement = connection.prepareCall(query);

            callableStatement.setInt("id", id);

            resultSet = callableStatement.executeQuery();
            System.out.println("From base");
            while (resultSet.next()) {
                int prod_id = resultSet.getInt("id");
                String img_url = resultSet.getString("img_url");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");

                String description = resultSet.getString("description");
                if (description.length() > 115)
                    description = description.substring(0, 115) + "...";

                Date dateTime = resultSet.getDate("date");
                int count = resultSet.getInt("count");

                System.out.println("ID: " + prod_id + " Name: " + name);
                Product product = new Product(prod_id, img_url, name, price, description, dateTime, count);
                temp.add(product);
            }

        } catch (SQLException e) {
            System.out.println("Error in get data: " + e);
        }
        return temp;
    }

    public boolean Remove(int product_id, int user_id) {
        int sellerID = 0;
        try {
            String INSERT = "SELECT seller_id FROM web_shop.products WHERE id = ?";
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, product_id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (resultSet.getRow() > 0)
                sellerID = resultSet.getInt("seller_id");

            if (user_id == sellerID) {
                RemoveFromWish(product_id, user_id);
                RemoveFromSale(product_id, user_id);
            } else
                RemoveFromWish(product_id, user_id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                preparedStatement.close();

            } catch (SQLException e) {
                System.out.println("Delete error");
                e.printStackTrace();
            }
        }
    }

    private boolean RemoveFromWish(int product_id, int user_id) {
        try {
            String INSERT = "DELETE FROM web_shop.wish_list WHERE user_id = ? AND product_id = ?";
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, product_id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                preparedStatement.close();

            } catch (SQLException e) {
                System.out.println("Delete error");
                e.printStackTrace();
            }
        }
    }

    private boolean RemoveFromSale(int product_id, int user_id) {
        try {
            String INSERT = "DELETE FROM web_shop.products WHERE seller_id = ? AND id = ?";
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, product_id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                preparedStatement.close();

            } catch (SQLException e) {
                System.out.println("Delete error");
                e.printStackTrace();
            }
        }
    }

    public int BuyProduct(int product_id, int buyer_ID, String address, int price, int count) {
        try {
            String INSERT = "{call BuyProduct(?,?,?,?,?,?)}";
            callableStatement = connection.prepareCall(INSERT);
            callableStatement.setInt(1, product_id);
            callableStatement.setInt(2, GetSellerID(product_id));
            callableStatement.setInt(3, buyer_ID);
            callableStatement.setString(4, address);
            callableStatement.setInt(5, price);
            callableStatement.setInt(6, count);


            resultSet = callableStatement.executeQuery();

            resultSet.next();
            return resultSet.getInt("NewID");
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int AddProduct(Product product) {
        try {
            String INSERT = "{call AddProduct(?,?,?,?,?,?,?)}";

            callableStatement = connection.prepareCall(INSERT);
            callableStatement.setString(1, product.getName());
            callableStatement.setString(2, product.getFull_description());
            callableStatement.setInt(3, product.getPrice());
            callableStatement.setInt(4, product.getSubcategory());
            callableStatement.setString(5, product.getImage());
            callableStatement.setInt(6, product.getSeller().getId());
            callableStatement.setInt(7, product.getCount());

            resultSet = callableStatement.executeQuery();

            resultSet.next();
            int id = resultSet.getInt("NewID");

            AddToWish(product.getSeller().getId(), id);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return product.getSeller().getId();
    }

    public int EditProduct(Product product) {
        int check;
        try {
            String INSERT = "{call EditProduct(?,?,?,?,?,?,?)}";
            callableStatement = connection.prepareCall(INSERT);
            callableStatement.setString(1, product.getName());
            callableStatement.setString(2, product.getFull_description());
            callableStatement.setInt(3, product.getPrice());
            callableStatement.setInt(4, product.getSubcategory());
            callableStatement.setString(5, product.getImage());
            callableStatement.setInt(6, product.getCount());
            callableStatement.setInt(7, product.getId());


            resultSet = callableStatement.executeQuery();

            resultSet.next();
            check = resultSet.getInt("checkid");
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return check;
    }

    public boolean AddToWish(int user_id, int product_id) {
        String query = "{call AddToWish(?,?) }";
        try {
            callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, user_id);
            callableStatement.setInt(2, product_id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        try {
            callableStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ObservableList<User> SearchUsers(String search) throws SQLException {
        ObservableList<User> temp = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM web_shop.users where (name Like '%" + search + "%' or second_name LIKE '%" + search + "%' or type LIKE '%" + search + "%' or balance LIKE '%" + search + "%' or email LIKE '%" + search +
                    "%' or phone LIKE '%" + search + "%' or second_name LIKE '%" + search + "%') AND confirmed = 1";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (resultSet.getRow() > 0) {
                do {
                    int userID = resultSet.getInt("id");
                    String surname = resultSet.getString("second_name");
                    String name = resultSet.getString("name");
                    BigDecimal balance = resultSet.getBigDecimal("balance");
                    int type = resultSet.getInt("type");


                    temp.add(new User(userID, type, surname, name, balance));
                } while (resultSet.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return temp;
    }

    public User GetUserInfo(int id) {

        User user = null;
        try {
            String query = "SELECT * FROM users WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            System.out.println("From base");
            resultSet.next();
            if (resultSet.getRow() > 0) {

                String surname = resultSet.getString("second_name");
                String name = resultSet.getString("name");
                String patronymic = resultSet.getString("patronymic");
                BigDecimal balance = resultSet.getBigDecimal("balance");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");


                System.out.println("ID: " + id + " Name: " + name);
                user = new User(surname, name, patronymic, email, phone, balance);

            }
        } catch (SQLException e) {
            System.out.println("Error in get data: " + e);
        }
        return user;
    }

    public int ChangeUserInfo(User user) throws SQLException {
        try {                                                                                                                   //1 2 3 4 5 6 7 8 9 0
            String INSERT = "UPDATE web_shop.users SET name = ?,second_name = ?, patronymic = ?,balance = ?,email = ?,phone = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getPatronymic());
            preparedStatement.setBigDecimal(4, user.getBalance());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.setInt(7, user.getId());

            preparedStatement.execute();
            //  UpdateID();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            preparedStatement.close();
            return user.getId();
        }
    }

    private int GetSellerID(int prodID) {

        int seller = 0;
        try {
            String query = "SELECT seller_id FROM products WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, prodID);
            resultSet = preparedStatement.executeQuery();
            System.out.println("From base");
            resultSet.next();
            if (resultSet.getRow() > 0) {

                seller = Integer.parseInt(resultSet.getString("second_name"));

            }
        } catch (SQLException e) {
            System.out.println("Error in get data: " + e);
        }
        return seller;
    }

    public ObservableList<Sale> GetSales(int user_id) {
        ObservableList<Sale> temp = FXCollections.observableArrayList();
        try {
            String INSERT = "{call GetSales(?)}";
            callableStatement = connection.prepareCall(INSERT);
            callableStatement.setInt(1, user_id);

            resultSet = callableStatement.executeQuery();
            //resultSet.next();
            while (resultSet.next()) {
            if (resultSet.getRow() > 0) {

                    int sale_id = resultSet.getInt("id");
                    int buyer_id = Integer.parseInt(resultSet.getString("buyer_id"));
                    int seller_id = Integer.parseInt(resultSet.getString("seller_id"));
                    int product_id = Integer.parseInt(resultSet.getString("ProductID"));

                    String seller_name = resultSet.getString("SellerName");
                    String seller_surname = resultSet.getString("SellerSurname");
                    String buyer_name = resultSet.getString("BuyerName");
                    String buyer_surname = resultSet.getString("BuyerSurname");
                    String product_name = resultSet.getString("ProductName");
                    int product_price = Integer.parseInt(resultSet.getString("ProductPrice"));
                    Date dateTime = resultSet.getDate("date");
                    String address = resultSet.getString("delivery_address");


                    System.out.println("ID: " + sale_id + " Seller: " + seller_name);
                    Sale sale = new Sale(sale_id, new User(seller_id,seller_surname,seller_name), new User(buyer_id,buyer_surname,buyer_name), dateTime, address,
                            new Product(product_id,product_name,product_price));
                    temp.add(sale);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  temp;
    }
}
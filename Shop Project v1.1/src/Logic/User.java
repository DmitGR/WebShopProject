package Logic;

import java.math.BigDecimal;
import java.math.BigInteger;

public class User
{
  private int id,verified , type;
  private String login, password, surname, name, patronymic, email, phone;
  private BigDecimal balance;

    public User(int id,String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public User(int id, int verified, String login, String password, String surname, String name, String patronymic, String email, String phone, String address)
    {
        this.id = id;
        this.verified=verified;
        this.login = login;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.email = email;
        this.phone = phone;
    }

    public User(String login, String password, String surname, String name, int type, String email, String phone)
    {
        this.login = login;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.type = type;
        this.balance = BigDecimal.ZERO;
        this.verified = 0;
    }

    public User(int id, int verified, String login, String password, String surname, String name, String email, String phone, BigDecimal balance, int type)
    {
        this.id = id;
        this.verified = verified;
        this.login = login;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.balance = balance;
        this.type = type;
    }

    public User(int verified, int type, String login, String password, String surname, String name, String email, String phone, BigDecimal balance) {
        this.verified = verified;
        this.type = type;
        this.login = login;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.balance = balance;
    }

    public User(int user_id) {
        this.id = user_id;
    }

    public User(int id, int type, String surname, String name, BigDecimal balance) {
        this.id = id;
        this.type = type;
        this.surname = surname;
        this.name = name;
        this.balance = balance;
    }

    public User(String surname, String name, String patronymic, String email, String phone, BigDecimal balance) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.email = email;
        this.phone = phone;
        this.balance = balance;
    }

    public User(int id, String surname, String name, String patronymic, String email, String phone, BigDecimal balance) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.email = email;
        this.phone = phone;
        this.balance = balance;
    }

    public User(int id, String surname, String name) {
        this.id = id;
        this.surname = surname;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

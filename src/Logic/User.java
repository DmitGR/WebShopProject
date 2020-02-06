package Logic;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by SmiLeX on 14.06.2018.
 */
public class User
{
    int id,verified;
    String login, password, surname, name, patronymic, email, phone, address;

    public User(String login, String password, String surname, String name, String patronymic, String email,String phone,String address)
    {
        this.login = login;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.email = email;
        this.phone = phone;
        this.address = address;
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
        this.address = address;
    }

    public User(String login, String password, String surname, String name, String email, String phone)
    {
        this.login = login;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public User(int id, int verified, String login, String password, String surname, String name, String email, String phone)
    {
        this.id = id;
        this.verified = verified;
        this.login = login;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getName()
    {
        return name;
    }

    public int getVerified()
    {
        return verified;
    }

    public void setVerified(int verified)
    {
        this.verified = verified;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPatronymic()
    {
        return patronymic;
    }

    public void setPatronymic(String patronymic)
    {
        this.patronymic = patronymic;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

}

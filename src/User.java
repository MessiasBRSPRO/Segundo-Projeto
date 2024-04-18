import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class User {
    private String mail;
    /*The encapsulation is one of main pillars of OOP, the objective is give access's restriction
    to data into the attributes avoiding changes or access the value of attributes.
    * */
    private String password;

    private static List<User> userList = new ArrayList<>();
    /*this is a Collection. The arrayList
    * is a linear data's Structure than not is synchronized, and different of
    * vector, the size of array is dynamic.The data are saved in array sequentially
    * and each data have a index(starts in index 0) */

    public User(String mail, String password){
        this.mail = MailValidator.mailValidatorMethod(mail);
        this.password = PasswordValidator.passwordValidator(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(mail, user.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail);
    }

    public static void registerUserInArrayList(User user){
        if(!(userList.contains(user))){
            userList.add(user);
            System.out.println("User:"+user.getMail() + " hass been registered.");
        }else{
            System.out.println("The mail:"+user.getMail());
            System.out.println("This email/user already exists!");
        }
    }

    public static List<User> getUserList() {
        return Collections.unmodifiableList(userList);
    }

    public String getMail() {
        return mail;
        // this is an getter method, he return a data attributed in the attribute
    }

    public String getPassword() {
        return password;
    }
}

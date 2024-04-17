public class User {
    private String mail;
    /*The encapsulation is one of main pillars of OOP, the objective is give access's restriction
    to data into the attributes avoiding changes or access the value of attributes.
    * */
    private String password;

    public User(String mail, String password){
        this.mail = MailValidator.mailValidatorMethod(mail);
        this.password = PasswordValidator.passwordValidator(password);
    }

    public String getMail() {
        return mail;
        // this is an getter method, he return a data attributed in the attribute
    }

    public String getPassword() {
        return password;
    }
}

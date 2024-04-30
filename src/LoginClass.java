import javax.security.auth.login.LoginException;
import java.util.Scanner;

public class LoginClass {
    private DAOUser daoUser;
    private boolean isLogged;
    private EmailService emailService;

    public LoginClass(){
        this.daoUser = new DAOUser();
        this.isLogged = false;
        this.emailService = new EmailService();
    }

    public void startLogin(){
        Scanner inputMain = new Scanner(System.in);

        System.out.println("= = = = = = LOGIN = = = = = =");

        while(!isLogged){

            System.out.println("Email:");
            String inputMail = inputMain.next();

            System.out.println("Password:");
            String inputPassword = inputMain.next();

            final User user = new User("", inputMail, inputPassword);

            //Sarching the user in DataBase postgreSQL
            daoUser.specifiedRowSearch(user);
            boolean resultsRow = DAOUser.getTotalRows() == 1;

            if(!(resultsRow)){
                System.out.println("User email or password invalid or don't exists");
            }else {
                System.out.println("Access allowed");
                isLogged = true;
            }
        }

    }
}

import javax.security.auth.login.LoginException;
import java.util.Scanner;

public class LoginClass {
    /*This class is responsible to give access to existent users to application*/
    private static DAOUser daoUser = new DAOUser();
    private boolean logged = false;
    public void loginMethod(){
        Scanner input = new Scanner(System.in);
        while(!(logged)){
            System.out.println(" = = = = = = LOGIN'S SCREEN = = =  = = = ");
            System.out.println("Insert your mail:");
            String inputMail = input.next();

            System.out.println("Insert your password:");
            String inputPassword = input.next();

            User user = new User("", inputMail, inputPassword);
            daoUser.specifiedRowSearch(user);
            boolean results = DAOUser.getTotalRows() == 1;

            try{
                if(!(results)){
                    throw new LoginException("Mail or password is invalid or don't exists, try again");
                }else {
                    logged = true;
                    System.out.println("Access conceded");
                }
            }catch (LoginException e ){
                System.out.println("an exception has occurred:"+e.getMessage());
            }
        }


    }
}

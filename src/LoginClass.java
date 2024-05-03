import javax.security.auth.login.LoginException;
import java.util.Scanner;

public class LoginClass {
    private DAOUser daoUser;
    private boolean isLogged;
    private EmailService emailService;
    private final Scanner inputMain = new Scanner(System.in);

    public LoginClass(){
        this.daoUser = new DAOUser();
        this.isLogged = false;
        this.emailService = new EmailService();
    }

    public void startLogin(){
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
                System.out.println("Your has forgotten the password?[1]yes[2]no");
                int optionForgotten = inputMain.nextInt();

                switch (optionForgotten){
                    case 1:
                        this.forgetMyPassword(user);
                        break;
                    case 2:
                        System.out.println("try login again");
                        break;
                    default:
                        try{
                            throw new InvalidOptionException("Invalid option, choose 1 or 2!");
                        }catch (InvalidOptionException e){
                            System.out.println("an exception has occurred:"+e.getMessage());
                        }
                        break;

                }
            }else {
                System.out.println("Access allowed");
                isLogged = true;
            }
        }

    }
    private void forgetMyPassword(User user){
        emailService.sendVerificationCode(user.getMail());
        int codeInvalid = 0;
        boolean changed = false;
        System.out.println("An Verification code has sent to your Register's Mail");
        while(!changed){
            System.out.println("Insert the 6 digits Code here:");
            int codeInput = inputMain.nextInt();

            try{
                if(!(codeInput == emailService.getCodeVerification())){
                    codeInvalid++;
                    System.out.println("Try again");
                    if(codeInvalid == 5){
                        System.out.println("Exceeded tentatives, please try again later! ");
                    }
                    throw new VerificationCodeException("code verification is incorrect");
                }else{
                    System.out.println("Insert the new password:");
                    String newPassword = inputMain.next();
                    user.setPassword(newPassword);
                    daoUser.updatePassword(newPassword, user);
                    changed = true;
                    isLogged = true;
                }
            }catch (VerificationCodeException e){
                System.out.println("an exception has occurred:"+e.getMessage());
            }
        }
    }

    private void forgetMyMail(User user){}
}

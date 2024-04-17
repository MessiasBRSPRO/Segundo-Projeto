public class MailValidator {
    public static String mailValidatorMethod(String mail){
        try{
            if(!(mail.contains("@") && mail.contains(".com"))){
                throw new MailException("Your mail is invalid, please try again with valid mail.");
            }
        }catch (MailException e){
            System.out.println("an Exception has occurred:"+e.getMessage());
            return null;
        }
        return mail;
    }
}

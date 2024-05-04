package br.com.SegundoProjeto.Validators;

import br.com.SegundoProjeto.Exceptions.PasswordException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    //This class is responsible for the validation of br.com.SegundoProjeto.Services.User's password
    public static String passwordValidator(String password){
        String regex = "^[a-zA-Z]{4,}[0-9]{1,}[!@#$%¬&*()-+=]{1,}$"; // <- this is a regular expressions;
        /*A regular Expressions is an grammatical patterns than will be searched in CharLets, extern archives and others.
        * */

        Pattern patternRegex = Pattern.compile(regex);//In java this class is responsible for identify the regex(secretly regex is String)
        Matcher searchPattern = patternRegex.matcher(password);// linked to Pattern, this class is responsible to search the
        // regex in designed String or other.

        try{
            boolean passwordIsSecure = searchPattern.find(); // Matcher uses this method to do the search
            if(!(passwordIsSecure)){
                System.out.println("The inserted password:"+password);
                System.out.println("Your password must have, min 4 characters(upper or lower), min 1 number and min 1 special character");
                throw new PasswordException("your password is weak and don't meets the requirements.");
            }
        }catch (PasswordException e){
            System.out.println("an Exception has occurred:"+e.getMessage());
            return null;
        }

        return password;

    }
}

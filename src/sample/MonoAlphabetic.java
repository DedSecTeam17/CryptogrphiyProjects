package sample;

import sun.dc.pr.PRError;

import java.util.HashMap;
import java.util.Map;

public class MonoAlphabetic {
    private static String alphabetic="abcdefghijklmnopqrstuvwxyz";
    private static String alphabetic_encrypted="eyfqwdtcrjbganxoilzmpshkvu";

public  static  void  main (String a []){





   String message=doForMessage("axceaawq",getAllAplhabetic("REVERSE"));
   System.out.println(message);
}
    public  static  Map<Character,Character> getAllAplhabetic(String type){
        Map<Character,Character> map=new HashMap<>();
        for (int i=0; i<alphabetic.length(); i++){
            char char1;
            char char2;
            if (type.equals("REVERSE")){
                 char1=alphabetic_encrypted.charAt(i);
                 char2=alphabetic.charAt(i);
                map.put(char1,char2);
            }else if (type.equals("NORMAL")){
                 char1=alphabetic.charAt(i);
                 char2=alphabetic_encrypted.charAt(i);
                map.put(char1,char2);
            }

        }
return  map;

    }




    private static   char doForChar(char ch,Map<Character,Character> map){
        char beforeencription=map.get(ch);
        return  beforeencription;
    }


    private  static  String doForMessage(String message,Map<Character,Character> map){
        String newMessage="";
        for (int i=0; i< message.length(); i++){

            char ch=message.charAt(i);
            char newChar=map.get(ch);

            newMessage+=newChar;

        }

        return  newMessage;

    }

















}

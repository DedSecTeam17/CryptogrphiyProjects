package sample.Transposition;

import sample.FilesHandler;

import java.util.Arrays;

public class TranspositionAlgorithm  extends FilesHandler{


    private String alphapetic = "abcdefghijklmnopqrstuvwxyz";
    private String alphapetic_reverse = "zyxwvutsrqponmlkjihgfedcba";


    public String MessageEncryption(String Plain, String Key){
        Plain = Plain.toLowerCase();
        Plain = Plain.replace(" ", "");
        Plain.trim();
        Key = Key.toLowerCase();
        Key = Key.replace(" ", "");
        String Cipher = "";
        char [] key_array = Key.toCharArray();

        int row = (Plain.length()/Key.length())+1;

        int column = Key.length();

        int empty_field = 0;
        int increment = 0;
        char [][] Matrix = new char [row][column];
        for(int i = 0 ; i < row ; i++){
            for(int j=0; j<column ; j++){
                if(increment < Plain.length()){
                    Matrix[i][j] = Plain.charAt(increment);
                    increment++;
                }
                else{
                    Matrix[i][j] = '_';
                    empty_field++;
                }
            }
        }
        int position = 0;
        char [] Sorted_key = new char [key_array.length];
        for(int i=0; i<key_array.length ;i++){
            Sorted_key [i] = key_array [i];
        }
        Arrays.sort(Sorted_key);
        for(int i=0 ; i<column ; i++){
            position = Key.indexOf(Sorted_key[i]);
            for(int j=0 ; j<row ; j++){
                Cipher += Matrix[j][position];
            }
        }
        return Cipher;
    }
    public String MessageDencryption(String Cipher, String Key){
        Cipher = Cipher.toLowerCase();
        Cipher = Cipher.trim();
        Key = Key.toLowerCase();
        String Plain = "";
        int row = Math.round(Cipher.length()/Key.length());
        int column = Key.length();
        int position;
        int increment = 0;
        char [][] Matrix = new char [row][column];
        char [] cipher_array = Cipher.toCharArray();
        char [] sorted_key = Key.toCharArray();
        Arrays.sort(sorted_key);
        for(int i=0 ; i<column ; i++){
            position = Key.indexOf(sorted_key[i]);
            for(int j=0 ; j<row ; j++){
                Matrix[j][position] = cipher_array[increment];
                increment++;
            }
        }
        int count=0;
        for(int i = 0 ; i<row ; i++){
            for(int j=0; j<column ; j++){
                if (Matrix[i][j]!='_')
                {
                    Plain += Matrix[i][j];
                }else {
                    continue;
                }
            }
        }
        return Plain;
    }
}

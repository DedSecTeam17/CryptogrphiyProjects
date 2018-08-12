package sample;
import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
public class CaserAlgorithm extends  FilesHandler {
    private static final String _EnglishAlphabet = "abcdefghijklmnopqrstuvwxyz";
    public Map<Character, Integer> getAllEnglishAlphabet() {
        Map<Character, Integer> EnglishAlphabet = new HashMap<>();
        for (int ALPHABET_CHARACTER_POSSESSION = 0; ALPHABET_CHARACTER_POSSESSION < _EnglishAlphabet.length(); ALPHABET_CHARACTER_POSSESSION++) {
            char CurrentCharacterInAlphabet = _EnglishAlphabet.charAt(ALPHABET_CHARACTER_POSSESSION);
            EnglishAlphabet.put(CurrentCharacterInAlphabet, ALPHABET_CHARACTER_POSSESSION);
        }
        EnglishAlphabet.put(' ',26);


        return EnglishAlphabet;
    }
    public Map<Integer, Character> getAllEnglishAlphabetReversed() {
        Map<Integer, Character> ReversedEnglishAlphabet = new HashMap<>();
        for (int ALPHABET_CHARACTER_POSSESSION = 0; ALPHABET_CHARACTER_POSSESSION < _EnglishAlphabet.length(); ALPHABET_CHARACTER_POSSESSION++) {
            char CurrentCharacterInAlphabet = _EnglishAlphabet.charAt(ALPHABET_CHARACTER_POSSESSION);
            ReversedEnglishAlphabet.put(ALPHABET_CHARACTER_POSSESSION, CurrentCharacterInAlphabet);
        }
        ReversedEnglishAlphabet.put(26,' ');

        return ReversedEnglishAlphabet;
    }
    public String MessageEncryption(String message, Map<Character, Integer> EnglishAlphabet, Map<Integer, Character> ReversedEnglishAlphabet, int MessageKey) {
        StringBuilder EncryptedMessage = new StringBuilder();
        List<Character> list=new ArrayList<>();
        for (int i = 0; i < message.length(); i++) {
            char messageCharacter = message.charAt(i);

//            EncryptedMessage += (CharacterEncryption(MessageKey, messageCharacter, EnglishAlphabet, ReversedEnglishAlphabet));
            list.add((CharacterEncryption(MessageKey, messageCharacter, EnglishAlphabet, ReversedEnglishAlphabet)));
        }
        System.out.println(Arrays.toString(list.toArray()));
        for (int i=0 ;i<list.size(); i++){
            EncryptedMessage.append(list.get(i));

        }

        return EncryptedMessage.toString();
    }
    public String MessageDencryption(String message, Map<Character, Integer> EnglishAlphabet, Map<Integer, Character> ReversedEnglishAlphabet, int MessageKey) {

        StringBuilder DecryptedMessage = new StringBuilder();
        List<Character> list=new ArrayList<>();
        for (int i = 0; i < message.length(); i++) {
            char messageCharacter = message.charAt(i);
            System.out.println(messageCharacter);

            list.add((CharacterDecryption(MessageKey, messageCharacter, EnglishAlphabet, ReversedEnglishAlphabet)));
        }
        for (int i=0 ;i<list.size(); i++){
            DecryptedMessage.append(list.get(i));

        }
        return DecryptedMessage.toString();
    }
    public char CharacterEncryption(int MessageKey, char DecryptedCharacter, Map<Character, Integer> EnglishAlphabet, Map<Integer, Character> ReversedEnglishAlphabet) {
        int CharacterPositionInAlphabet = Math.floorMod((EnglishAlphabet.get(DecryptedCharacter) + MessageKey), EnglishAlphabet.size());
        return ReversedEnglishAlphabet.get(CharacterPositionInAlphabet);
    }
    public char CharacterDecryption(int MessageKey, char EncryptedCharacter, Map<Character, Integer> EnglishAlphabet, Map<Integer, Character> ReversedEnglishAlphabet) {
        int CharacterPositionInAlphabet = Math.floorMod((EnglishAlphabet.get(EncryptedCharacter) - MessageKey), EnglishAlphabet.size());
        return ReversedEnglishAlphabet.get(CharacterPositionInAlphabet);
    }
}

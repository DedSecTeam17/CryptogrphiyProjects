package sample;

import java.util.HashMap;
import java.util.Map;

 class PlayFairAlgorithm extends FilesHandler {
    static   final String _EnglishAlphabet = "abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    static   Map<Character, Integer> getAllEnglishAlphabet() {
        Map<Character, Integer> EnglishAlphabet = new HashMap<>();
        for (int ALPHABET_CHARACTER_POSSESSION = 0; ALPHABET_CHARACTER_POSSESSION < _EnglishAlphabet.length(); ALPHABET_CHARACTER_POSSESSION++) {
            char CurrentCharacterInAlphabet = _EnglishAlphabet.charAt(ALPHABET_CHARACTER_POSSESSION);
            System.out.println(CurrentCharacterInAlphabet);
            EnglishAlphabet.put(CurrentCharacterInAlphabet, ALPHABET_CHARACTER_POSSESSION);
        }


        return EnglishAlphabet;
    }
    static   Map<Integer, Character> getAllEnglishAlphabetReversed() {
        Map<Integer, Character> ReversedEnglishAlphabet = new HashMap<>();
        for (int ALPHABET_CHARACTER_POSSESSION = 0; ALPHABET_CHARACTER_POSSESSION < _EnglishAlphabet.length(); ALPHABET_CHARACTER_POSSESSION++) {
            char CurrentCharacterInAlphabet = _EnglishAlphabet.charAt(ALPHABET_CHARACTER_POSSESSION);
            ReversedEnglishAlphabet.put(ALPHABET_CHARACTER_POSSESSION, CurrentCharacterInAlphabet);
        }
        

        return ReversedEnglishAlphabet;
    }


    static   String MessageEncryption(String message, Map<Character, Integer> EnglishAlphabet, Map<Integer, Character> ReversedEnglishAlphabet, String MessageKey) {
        StringBuilder EncryptedMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            if (message.length()==MessageKey.length()){
                char messageCharacter = message.charAt(i);
                char keyCharachter=MessageKey.charAt(i);

                int messageCharPos=EnglishAlphabet.get(messageCharacter);
                int keyCharPos=EnglishAlphabet.get(keyCharachter);

                int newPos=Math.floorMod((messageCharPos+keyCharPos),EnglishAlphabet.size());
                char newChar=ReversedEnglishAlphabet.get(newPos);
                EncryptedMessage.append(newChar);


            }else {
                System.out.println("Message and Key should have the same size " +
                        "!!!!1");
            }

        }

//        System.out.println(Arrays.toString(list.toArray()));


        return EncryptedMessage.toString();
    }
    static   String MessageDecryption(String message, Map<Character, Integer> EnglishAlphabet, Map<Integer, Character> ReversedEnglishAlphabet, String MessageKey) {
        StringBuilder EncryptedMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            if (message.length() == MessageKey.length()) {
                char messageCharacter = message.charAt(i);
                char keyCharachter = MessageKey.charAt(i);

                int messageCharPos = EnglishAlphabet.get(messageCharacter);
                int keyCharPos = EnglishAlphabet.get(keyCharachter);

                if (messageCharPos < keyCharPos) {

                    int newPos = Math.floorMod((messageCharPos - keyCharPos), EnglishAlphabet.size());
                    char newChar = ReversedEnglishAlphabet.get(newPos);
                    EncryptedMessage.append(newChar);

                } else {
                    int newPos = Math.floorMod((EnglishAlphabet.size() + (messageCharPos - keyCharPos)), EnglishAlphabet.size());
                    char newChar = ReversedEnglishAlphabet.get(newPos);
                    EncryptedMessage.append(newChar);
                }
            }

        }
        return EncryptedMessage.toString();
    }






}

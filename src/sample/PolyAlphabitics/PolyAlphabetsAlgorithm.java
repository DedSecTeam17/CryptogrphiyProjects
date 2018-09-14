package sample.PolyAlphabitics;

import sample.FilesHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PolyAlphabetsAlgorithm extends FilesHandler {
    public final boolean left = false;
    public final boolean right = true;
    public final String _EnglishAlphabet = "abcdefghijklmnopqrstuvwxyz ";

    public Map<Character, Integer> getAllEnglishAlphabet() {
        Map<Character, Integer> EnglishAlphabet = new HashMap<>();
        for (int ALPHABET_CHARACTER_POSSESSION = 0; ALPHABET_CHARACTER_POSSESSION < _EnglishAlphabet.length(); ALPHABET_CHARACTER_POSSESSION++) {
            char CurrentCharacterInAlphabet = _EnglishAlphabet.charAt(ALPHABET_CHARACTER_POSSESSION);
            EnglishAlphabet.put(CurrentCharacterInAlphabet, ALPHABET_CHARACTER_POSSESSION);
        }


        return EnglishAlphabet;
    }

    public Map<Integer, Character> getAllEnglishAlphabetReversed() {
        Map<Integer, Character> ReversedEnglishAlphabet = new HashMap<>();
        for (int ALPHABET_CHARACTER_POSSESSION = 0; ALPHABET_CHARACTER_POSSESSION < _EnglishAlphabet.length(); ALPHABET_CHARACTER_POSSESSION++) {
            char CurrentCharacterInAlphabet = _EnglishAlphabet.charAt(ALPHABET_CHARACTER_POSSESSION);
            ReversedEnglishAlphabet.put(ALPHABET_CHARACTER_POSSESSION, CurrentCharacterInAlphabet);
        }
//        ReversedEnglishAlphabet.put(26,' ');

        return ReversedEnglishAlphabet;
    }

    public String StringFormlizer(String Text, int number_of_rules) {
        if (Text.length() % number_of_rules != 0) {
            do {
                Text += "_";
//            System.out.println("Size"+Text.length());
            } while (Text.length() % number_of_rules != 0);
        }
        return Text;

    }


    /**
     * this method encrypted method and @return cipher text as String type
     *
     * @param plainText this is the normal text message to be encrypted
     * @param rules     rules will implemented on the plain text to be implemented on plain text
     * @return cipher text as String type
     */
    public String encryption(String plainText, List<Rule> rules) throws StringIndexOutOfBoundsException {
        // number of rules to be applied on plain text
        int NumberOfRules = rules.size();
        // formalize String to be token form . this make every block has same size if size id odd
        // then fill string with under score
        plainText = StringFormlizer(plainText, NumberOfRules);
        int counter = 0;
        StringBuilder block = new StringBuilder();
        StringBuilder CipherText = new StringBuilder();
        for (int i = 0; i < plainText.length(); i += NumberOfRules) {
            for (int j = 1; j <= NumberOfRules; j++) {
                char ch = plainText.charAt(counter);
                block.append(ch);
                counter += 1;
            }
            CipherText.append(BlockCipher(block.toString(), rules));
            block.delete(0, block.length());
        }
        return CipherText.toString();
    }

    public String decryption(String CipherText, List<Rule> rules) throws StringIndexOutOfBoundsException {
        int NumberOfRules = rules.size();
        CipherText = StringFormlizer(CipherText, NumberOfRules);
        int counter = 0;
        StringBuilder block = new StringBuilder();
        StringBuilder PlainText = new StringBuilder();
        for (int i = 0; i < CipherText.length(); i += NumberOfRules) {
            for (int j = 1; j <= NumberOfRules; j++) {
                char ch = CipherText.charAt(counter);
                block.append(ch);
                counter += 1;
            }
            PlainText.append(BlockDeCipher(block.toString(), rules));
            block.delete(0, block.length());
        }
        return PlainText.toString();
    }

    public char applyRuleOnCharacter(String type, int amount, boolean direction, char ch, String oprationType) {
        switch (oprationType) {
            case "encryption":
                switch (type) {
                    case "shift":
                        if (direction) {
                            if (ch != '_') {
                                int current_pos = getAllEnglishAlphabet().get(ch);
                                int newPos = Math.floorMod((current_pos + amount), getAllEnglishAlphabet().size());
                                return getAllEnglishAlphabetReversed().get(newPos);
                            }
                        } else {
                            if (ch != '_') {
                                int current_pos = getAllEnglishAlphabet().get(ch);
                                int newPos = Math.floorMod((current_pos - amount), getAllEnglishAlphabet().size());
                                return getAllEnglishAlphabetReversed().get(newPos);
                            }
                        }
                        break;
                }
                break;
            case "decryption":
                switch (type) {
                    case "shift":
                        if (direction) {
                            if (ch != '_') {
                                int current_pos = getAllEnglishAlphabet().get(ch);
                                int newPos = Math.floorMod((current_pos - amount), getAllEnglishAlphabet().size());
                                return getAllEnglishAlphabetReversed().get(newPos);
                            }
                        } else {
                            if (ch != '_') {
                                int current_pos = getAllEnglishAlphabet().get(ch);
                                int newPos = Math.floorMod((current_pos + amount), getAllEnglishAlphabet().size());
                                return getAllEnglishAlphabetReversed().get(newPos);
                            }
                        }
                        break;
                }
        }
        return '_';
    }
    public String BlockCipher(String Block, List<Rule> rules) {
        StringBuilder BlockCiphered = new StringBuilder();
        for (int i = 0; i < Block.length(); i++) {
            char cipher_char = applyRuleOnCharacter(rules.get(i).getType(), rules.get(i).getAmount(), rules.get(i).isDirection(), Block.charAt(i), "encryption");
            if (cipher_char != '_')
                BlockCiphered.append(cipher_char);
        }
        return BlockCiphered.toString();
    }
    public String BlockDeCipher(String Block, List<Rule> rules) {
        StringBuilder BlockDeCiphered = new StringBuilder();
        for (int i = 0; i < Block.length(); i++) {
            char decipher_char = applyRuleOnCharacter(rules.get(i).getType(), rules.get(i).getAmount(), rules.get(i).isDirection(), Block.charAt(i), "decryption");
            if (decipher_char != '_')
                BlockDeCiphered.append(decipher_char);
        }
        return BlockDeCiphered.toString();
    }
}

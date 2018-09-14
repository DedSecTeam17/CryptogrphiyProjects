package sample.RSA;

import sample.FilesHandler;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RsaAlgorithm extends FilesHandler
{
    private final      BigInteger one = new BigInteger("1");
    private final      SecureRandom random = new SecureRandom();
    private      List<Byte> hex=new ArrayList<>();

    public BigInteger privateKey;
    public BigInteger publicKey;
    public BigInteger modulus;

    // generate an N-bit (roughly) public and private key
    RsaAlgorithm() {

    }

    public  void  generateKey(int N)
    {
        BigInteger p = BigInteger.probablePrime(N, random);
        BigInteger q = BigInteger.probablePrime(N, random);
//        65537
//        8473
//        32881
//        PHI OF N
        BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));

//        n
        modulus = p.multiply(q);
//        publicKey = new BigInteger("65537");
        publicKey=BigInteger.probablePrime(8,random);


        // common value in practice = 2^16 + 1
//        d = e^-1 mod phi
        privateKey = publicKey.modInverse(phi);
    }





    private      String StringFormlizer(String message) {
        do {
            message += "_";

        } while (message.length() != 8);
        return message;
    }
    public      String Cipherion(String cipherText, RsaAlgorithm key) {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < cipherText.length(); i++) {
            int character = cipherText.charAt(i);
            BigInteger message = new BigInteger(String.valueOf(character));
            BigInteger streamCipher = key.streamCipher(message);
            System.out.println(streamCipher.toString());
            String ency_with_under = streamCipher.toString();
            ency_with_under = StringFormlizer(ency_with_under);
            hex.add(streamCipher.byteValue());
            stringBuilder.append(ency_with_under);
        }
        return stringBuilder.toString();
    }
    public      String DeCipherion(String cipherText, RsaAlgorithm key) {
        StringBuilder block = new StringBuilder();
        int count = 0;
        StringBuilder plainText = new StringBuilder();
        for (int i = 0; i < cipherText.length(); i += 8) {
            for (int h = 0; h < 8; h++) {
                char ch = cipherText.charAt(count);
                if (ch != '_') {
                    block.append(ch);
                }
                count++;
            }
            //decry
            BigInteger bigInteger = key.streamDeCipher(BigInteger.valueOf(Long.parseLong(block.toString())));
            System.out.println(bigInteger.toString());
            plainText.append((char) Integer.parseInt(bigInteger.toString()));
            block = new StringBuilder();
        }
        return plainText.toString();
    }
    private BigInteger streamCipher(BigInteger message) {
        return message.modPow(publicKey, modulus);
    }
    private BigInteger streamDeCipher(BigInteger streamCiphered) {
        return streamCiphered.modPow(privateKey, modulus);
    }
}

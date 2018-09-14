package sample.RSA;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import sample.CipherOperations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RsaViewModel  {
    private static RsaViewModel ourInstance = new RsaViewModel();

    public static RsaViewModel getInstance() {
        return ourInstance;
    }

    private RsaViewModel() {
    }
    RsaAlgorithm rsaAlgorithm=new RsaAlgorithm();


    public  Map<String,String>  generateKey()
    {
        Map<String,String> map=new HashMap<>();
        rsaAlgorithm.generateKey(8);
        map.put("PRIVATE_KEY", String.valueOf(rsaAlgorithm.privateKey));
        map.put("PUBLIC_KEY", String.valueOf(rsaAlgorithm.publicKey));
        map.put("MODULUS", String.valueOf(rsaAlgorithm.modulus));
        return  map;
    }

    public void   bindRealEncryption(JFXTextField message   ) {
        String decryptedMessage = rsaAlgorithm.Cipherion(message.getText(),rsaAlgorithm);

        message.setText(decryptedMessage);
    }
    //    Handler
    public void   bindRealDecryption(JFXTextField message   ) {
        String decryptedMessage = rsaAlgorithm.DeCipherion(message.getText(), rsaAlgorithm);

        message.setText(decryptedMessage);
    }
    public Map<String, String> bindUploadFile(TextArea file_result, Label file_path) throws FileNotFoundException {
        Map<String, String> file_meta_data = rsaAlgorithm.ReadMessageFromFile();
        file_result.setText(file_meta_data.get("file_data"));
        file_path.setText(file_meta_data.get("file_name"));
        return file_meta_data;
    }
    public String bindFileEncryption(String messageFromFile, String messageFilePath, TextArea file_result ) throws IOException {
        String encryptedMessage = rsaAlgorithm.Cipherion(messageFromFile,rsaAlgorithm);
        rsaAlgorithm.OverWriteMessageIntoFile(messageFilePath,encryptedMessage);
        file_result.setText(rsaAlgorithm.ReadMessageFromFile(messageFilePath));
        return rsaAlgorithm.ReadMessageFromFile(messageFilePath);
    }
    public String bindFileDecryption(String messageFromFile, String messageFilePath, TextArea file_result ) throws IOException {
        String decryptedMessage = rsaAlgorithm.DeCipherion(messageFromFile,rsaAlgorithm);
        rsaAlgorithm.OverWriteMessageIntoFile(messageFilePath,decryptedMessage);
        file_result.setText(rsaAlgorithm.ReadMessageFromFile(messageFilePath));
        return rsaAlgorithm.ReadMessageFromFile(messageFilePath);
    }

    public  void saveEditOnFile(TextArea textArea, String filePath) throws IOException {
        rsaAlgorithm.OverWriteMessageIntoFile(filePath,textArea.getText());
//        String message=rsaAlgorithm.ReadMessageFromFile(filePath);


    }
    public  void deleteAllFileContent(String filePath, TextArea textArea) throws IOException {
        rsaAlgorithm.OverWriteMessageIntoFile(filePath,"");
        textArea.setText("");
    }


}

package sample.Transposition;


import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import sample.CipherOperations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class TranspositionViewModel  implements CipherOperations {
    private static TranspositionViewModel ourInstance = new TranspositionViewModel();


    public static TranspositionViewModel getInstance() {
        return ourInstance;
    }

    private TranspositionViewModel() {
    }

    
    TranspositionAlgorithm transpositionAlgorithm=new TranspositionAlgorithm();
    public void   bindRealEncryption(JFXTextField message, JFXTextField key) {
        String decryptedMessage = transpositionAlgorithm.MessageEncryption(message.getText(), key.getText()   );
        message.setText(decryptedMessage);
    }
    //    Handler
    public void   bindRealDecryption(JFXTextField message, JFXTextField key) {
        String decryptedMessage = transpositionAlgorithm.MessageDencryption(message.getText(), key.getText());

        message.setText(decryptedMessage);
    }
    public Map<String, String> bindUploadFile(TextArea file_result, Label file_path) throws FileNotFoundException {
        Map<String, String> file_meta_data = transpositionAlgorithm.ReadMessageFromFile();
        file_result.setText(file_meta_data.get("file_data"));
        file_path.setText(file_meta_data.get("file_name"));
        return file_meta_data;
    }
    public String bindFileEncryption(String messageFromFile, String messageFilePath, TextArea file_result, JFXTextField files_key) throws IOException {
        String encryptedMessage = transpositionAlgorithm.MessageEncryption(messageFromFile,files_key.getText());
        transpositionAlgorithm.OverWriteMessageIntoFile(messageFilePath,encryptedMessage);
        file_result.setText(transpositionAlgorithm.ReadMessageFromFile(messageFilePath));
        return transpositionAlgorithm.ReadMessageFromFile(messageFilePath);
    }
    public String bindFileDecryption(String messageFromFile, String messageFilePath, TextArea file_result, JFXTextField files_key) throws IOException {
        String decryptedMessage = transpositionAlgorithm.MessageDencryption(messageFromFile,files_key.getText());
        transpositionAlgorithm.OverWriteMessageIntoFile(messageFilePath,decryptedMessage);
        file_result.setText(transpositionAlgorithm.ReadMessageFromFile(messageFilePath));
        return transpositionAlgorithm.ReadMessageFromFile(messageFilePath);
    }
    public  void saveEditOnFile(TextArea textArea, String filePath) throws IOException {
        transpositionAlgorithm.OverWriteMessageIntoFile(filePath,textArea.getText());
//        String message=transpositionAlgorithm.ReadMessageFromFile(filePath);


    }
    public  void deleteAllFileContent(String filePath, TextArea textArea) throws IOException {
        transpositionAlgorithm.OverWriteMessageIntoFile(filePath,"");
        textArea.setText("");
    }
    
    
    
    
    
}

package sample;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class RailFenceViewModel implements CipherOperations {
    private static RailFenceViewModel ourInstance = new RailFenceViewModel();
    public static RailFenceViewModel getInstance() {
        return ourInstance;
    }
    private RailFenceAlgorithm  railFence = new RailFenceAlgorithm();
    private RailFenceViewModel() {
    }
    public void   bindRealEncryption(JFXTextField message, JFXTextField key) {
        String decryptedMessage =  railFence.MessageEncryption(message.getText(),  Integer.parseInt(key.getText()));
        message.setText(decryptedMessage);
    }
    public void   bindRealDecryption(JFXTextField message, JFXTextField key) {
        String decryptedMessage =  railFence.MessageDcryption(message.getText(), Integer.parseInt(key.getText()));
        message.setText(decryptedMessage);
    }
    public Map<String, String> bindUploadFile(TextArea file_result, Label file_path) throws FileNotFoundException {
        Map<String, String> file_meta_data =  railFence.ReadMessageFromFile();
        file_result.setText(file_meta_data.get("file_data"));
        file_path.setText(file_meta_data.get("file_name"));
        return file_meta_data;
    }
    public String bindFileEncryption(String messageFromFile, String messageFilePath, TextArea file_result, JFXTextField files_key) throws IOException {
        String encryptedMessage =  railFence.MessageEncryption(messageFromFile,   Integer.parseInt(files_key.getText()));
         railFence.OverWriteMessageIntoFile(messageFilePath,encryptedMessage);
        file_result.setText( railFence.ReadMessageFromFile(messageFilePath));
        return  railFence.ReadMessageFromFile(messageFilePath);
    }
    public String bindFileDecryption(String messageFromFile, String messageFilePath, TextArea file_result, JFXTextField files_key) throws IOException {
        String decryptedMessage =  railFence.MessageDcryption(messageFromFile,  Integer.parseInt(files_key.getText()));
         railFence.OverWriteMessageIntoFile(messageFilePath,decryptedMessage);
        file_result.setText( railFence.ReadMessageFromFile(messageFilePath));
        return  railFence.ReadMessageFromFile(messageFilePath);
    }
    public  void saveEditOnFile(TextArea textArea, String filePath) throws IOException {
         railFence.OverWriteMessageIntoFile(filePath, textArea.getText());
//        String message= railFence.ReadMessageFromFile(filePath);


    }
    public  void deleteAllFileContent(String filePath, TextArea textArea) throws IOException {
         railFence.OverWriteMessageIntoFile(filePath,"");
        textArea.setText("");
    }
}

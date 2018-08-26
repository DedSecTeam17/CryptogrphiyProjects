package sample.PolyAlphabitics;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PolyAlphabeticViewModel  {
    private static PolyAlphabeticViewModel ourInstance = new PolyAlphabeticViewModel();
    private  PolyAlphabetsAlgorithm polyAlphabetsAlgorithm=new PolyAlphabetsAlgorithm();

    public static PolyAlphabeticViewModel getInstance() {
        return ourInstance;
    }

    private PolyAlphabeticViewModel() {
    }

    public void   bindRealEncryption(JFXTextField message, List<Rule> ruleList) {
        String decryptedMessage =  polyAlphabetsAlgorithm.encryption(message.getText(),ruleList);
        message.setText(decryptedMessage);
    }
    public void   bindRealDecryption(JFXTextField message,  List<Rule> ruleList) {
        String decryptedMessage =  polyAlphabetsAlgorithm.decryption(message.getText(),ruleList);
        message.setText(decryptedMessage);
    }
    public Map<String, String> bindUploadFile(TextArea file_result, Label file_path) throws FileNotFoundException {
        Map<String, String> file_meta_data =  polyAlphabetsAlgorithm.ReadMessageFromFile();
        file_result.setText(file_meta_data.get("file_data"));
        file_path.setText(file_meta_data.get("file_name"));
        return file_meta_data;
    }
    public String bindFileEncryption(String messageFromFile, String messageFilePath, TextArea file_result, List<Rule> ruleList) throws IOException {
        String encryptedMessage =  polyAlphabetsAlgorithm.encryption(messageFromFile,   ruleList);
        polyAlphabetsAlgorithm.OverWriteMessageIntoFile(messageFilePath,encryptedMessage);
        file_result.setText( polyAlphabetsAlgorithm.ReadMessageFromFile(messageFilePath));
        return  polyAlphabetsAlgorithm.ReadMessageFromFile(messageFilePath);
    }
    public String bindFileDecryption(String messageFromFile, String messageFilePath, TextArea file_result,List<Rule> ruleList) throws IOException {
        String decryptedMessage =  polyAlphabetsAlgorithm.decryption(messageFromFile, ruleList);
        polyAlphabetsAlgorithm.OverWriteMessageIntoFile(messageFilePath,decryptedMessage);
        file_result.setText( polyAlphabetsAlgorithm.ReadMessageFromFile(messageFilePath));
        return  polyAlphabetsAlgorithm.ReadMessageFromFile(messageFilePath);
    }
    public  void saveEditOnFile(TextArea textArea, String filePath) throws IOException {
        polyAlphabetsAlgorithm.OverWriteMessageIntoFile(filePath, textArea.getText());
//        String message= railFence.ReadMessageFromFile(filePath);
    }
    public  void deleteAllFileContent(String filePath, TextArea textArea) throws IOException {
        polyAlphabetsAlgorithm.OverWriteMessageIntoFile(filePath,"");
        textArea.setText("");
    }
}

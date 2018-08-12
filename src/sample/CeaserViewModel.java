package sample;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class CeaserViewModel implements CipherOperations {
    private static CeaserViewModel ourInstance = new CeaserViewModel();

    public static CeaserViewModel getInstance() {
        return ourInstance;
    }

    CaserAlgorithm caserEncryption = new CaserAlgorithm();

    private CeaserViewModel() {
    }
    public void   bindRealEncryption(JFXTextField message, JFXTextField key) {
        String decryptedMessage = caserEncryption.MessageEncryption(message.getText(), caserEncryption.getAllEnglishAlphabet(), caserEncryption.getAllEnglishAlphabetReversed(), Integer.parseInt(key.getText()));
        message.setText(decryptedMessage);
    }
//    Handler
    public void   bindRealDecryption(JFXTextField message, JFXTextField key) {
        String decryptedMessage = caserEncryption.MessageDencryption(message.getText(), caserEncryption.getAllEnglishAlphabet(), caserEncryption.getAllEnglishAlphabetReversed(), Integer.parseInt(key.getText()));
        message.setText(decryptedMessage);
    }
    public Map<String, String> bindUploadFile(TextArea file_result, Label file_path) throws FileNotFoundException {
        Map<String, String> file_meta_data = caserEncryption.ReadMessageFromFile();
        file_result.setText(file_meta_data.get("file_data"));
        file_path.setText(file_meta_data.get("file_name"));
        return file_meta_data;
    }
    public String bindFileEncryption(String messageFromFile, String messageFilePath, TextArea file_result, JFXTextField files_key) throws IOException {
        String encryptedMessage = caserEncryption.MessageEncryption(messageFromFile, caserEncryption.getAllEnglishAlphabet(), caserEncryption.getAllEnglishAlphabetReversed(), Integer.parseInt(files_key.getText()));
            caserEncryption.OverWriteMessageIntoFile(messageFilePath,encryptedMessage);
            file_result.setText(caserEncryption.ReadMessageFromFile(messageFilePath));
        return caserEncryption.ReadMessageFromFile(messageFilePath);
    }
    public String bindFileDecryption(String messageFromFile, String messageFilePath, TextArea file_result, JFXTextField files_key) throws IOException {
        String decryptedMessage = caserEncryption.MessageDencryption(messageFromFile, caserEncryption.getAllEnglishAlphabet(), caserEncryption.getAllEnglishAlphabetReversed(), Integer.parseInt(files_key.getText()));
        caserEncryption.OverWriteMessageIntoFile(messageFilePath,decryptedMessage);
        file_result.setText(caserEncryption.ReadMessageFromFile(messageFilePath));
        return caserEncryption.ReadMessageFromFile(messageFilePath);
    }
    public  void saveEditOnFile(TextArea textArea, String filePath) throws IOException {
        caserEncryption.OverWriteMessageIntoFile(filePath,textArea.getText());
//        String message=caserEncryption.ReadMessageFromFile(filePath);


    }
    public  void deleteAllFileContent(String filePath, TextArea textArea) throws IOException {
        caserEncryption.OverWriteMessageIntoFile(filePath,"");
        textArea.setText("");
    }
}

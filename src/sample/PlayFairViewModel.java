package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class PlayFairViewModel implements  CipherOperations {
    private static PlayFairViewModel ourInstance = new PlayFairViewModel();

    public static PlayFairViewModel getInstance() {
        return ourInstance;
    }

    PlayFairAlgorithm playFair = new PlayFairAlgorithm();

    private PlayFairViewModel() {
    }
    public void   bindRealEncryption(JFXTextField message, JFXTextField key) {
        String decryptedMessage = playFair.MessageEncryption(message.getText(), playFair.getAllEnglishAlphabet(), playFair.getAllEnglishAlphabetReversed(), key.getText());
        message.setText(decryptedMessage);
    }
    //    Handler
    public void   bindRealDecryption(JFXTextField message, JFXTextField key) {
        String decryptedMessage = playFair.MessageDecryption(message.getText(), playFair.getAllEnglishAlphabet(), playFair.getAllEnglishAlphabetReversed(), key.getText());
        message.setText(decryptedMessage);
    }
    public Map<String, String> bindUploadFile(TextArea file_result, Label file_path) throws FileNotFoundException {
        Map<String, String> file_meta_data = playFair.ReadMessageFromFile();
        file_result.setText(file_meta_data.get("file_data"));
        file_path.setText(file_meta_data.get("file_name"));
        return file_meta_data;
    }
    public String bindFileEncryption(String messageFromFile, String messageFilePath, TextArea file_result, JFXTextField files_key) throws IOException {
        String encryptedMessage = playFair.MessageEncryption(messageFromFile, playFair.getAllEnglishAlphabet(), playFair.getAllEnglishAlphabetReversed(), files_key.getText());
        playFair.OverWriteMessageIntoFile(messageFilePath,encryptedMessage);
        file_result.setText(playFair.ReadMessageFromFile(messageFilePath));
        return playFair.ReadMessageFromFile(messageFilePath);
    }
    public String bindFileDecryption(String messageFromFile, String messageFilePath, TextArea file_result, JFXTextField files_key) throws IOException {
        String decryptedMessage = playFair.MessageDecryption(messageFromFile, playFair.getAllEnglishAlphabet(), playFair.getAllEnglishAlphabetReversed(), files_key.getText());
        playFair.OverWriteMessageIntoFile(messageFilePath,decryptedMessage);
        file_result.setText(playFair.ReadMessageFromFile(messageFilePath));
        return playFair.ReadMessageFromFile(messageFilePath);
    }
    public  void saveEditOnFile(TextArea textArea, String filePath) throws IOException {
        playFair.OverWriteMessageIntoFile(filePath,textArea.getText());
    }
    public  void deleteAllFileContent(String filePath, TextArea textArea) throws IOException {
        playFair.OverWriteMessageIntoFile(filePath,"");
        textArea.setText("");
    }
}

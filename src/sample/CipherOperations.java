package sample;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

   public interface CipherOperations {


       void   bindRealEncryption(JFXTextField message, JFXTextField key);

       void   bindRealDecryption(JFXTextField message, JFXTextField key);

       Map<String, String> bindUploadFile(TextArea file_result, Label file_path) throws FileNotFoundException;

       String bindFileEncryption(String messageFromFile, String messageFilePath, TextArea file_result, JFXTextField files_key) throws IOException;

        String bindFileDecryption(String messageFromFile, String messageFilePath, TextArea file_result, JFXTextField files_key) throws IOException;

        void saveEditOnFile(TextArea textArea, String filePath) throws IOException;

        void deleteAllFileContent(String filePath, TextArea textArea) throws IOException;
}

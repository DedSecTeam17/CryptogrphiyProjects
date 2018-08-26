package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class RailFenceEncryptionController implements Initializable {
    public JFXTextField fence_key;
    public JFXButton fence_decry;
    public JFXTextField fence_message;
    public JFXButton fence_encry;
    public JFXButton fence_upload_file;
    public JFXButton fence_save_edited_file;
    public JFXButton fence_delete_file_contetn;
    public JFXButton fence_decry_file;
    public JFXButton fence_encry_file;
    public Label file_path;
    public TextArea fence_file_result;
    public JFXTextField fence_files_key;
    private String messageFilePath;
    private String messageFromFile;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        fence_encry.setOnAction(event -> {
             RailFenceViewModel.getInstance().bindRealEncryption(fence_message,fence_key);
        });
        fence_decry.setOnAction(event ->{
                     RailFenceViewModel.getInstance().bindRealDecryption(fence_message,fence_key);

                }
        );

        fence_save_edited_file.setOnAction(event -> {
            try {
                RailFenceViewModel.getInstance().saveEditOnFile(fence_file_result,messageFilePath);
                messageFromFile=fence_file_result.getText();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fence_delete_file_contetn.setOnAction(event -> {
            try {
                RailFenceViewModel.getInstance().deleteAllFileContent(messageFilePath,fence_file_result);
                messageFromFile="";
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fence_upload_file.setOnAction(event -> {
            try {
                Map<String,String> file_meta_data=  RailFenceViewModel.getInstance().bindUploadFile(fence_file_result,file_path);
                this.messageFromFile=file_meta_data.get("file_data");
                this.messageFilePath=file_meta_data.get("file_name");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        fence_encry_file.setOnAction(event -> {
            try {
                this.messageFromFile=  RailFenceViewModel.getInstance().bindFileEncryption(messageFromFile,messageFilePath,fence_file_result,fence_files_key);
                 RailFenceViewModel.getInstance().bindFileEncryption(messageFromFile,messageFilePath,fence_file_result,fence_files_key);
                this.messageFromFile=  RailFenceViewModel.getInstance().bindFileEncryption(messageFromFile,messageFilePath,fence_file_result,fence_files_key);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fence_decry_file.setOnAction(event -> {
            try {
                this.messageFromFile=  RailFenceViewModel.getInstance().bindFileDecryption(messageFromFile,messageFilePath,fence_file_result,fence_files_key);
                 RailFenceViewModel.getInstance().bindFileDecryption(messageFromFile,messageFilePath,fence_file_result,fence_files_key);
                this.messageFromFile=  RailFenceViewModel.getInstance().bindFileDecryption(messageFromFile,messageFilePath,fence_file_result,fence_files_key);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}

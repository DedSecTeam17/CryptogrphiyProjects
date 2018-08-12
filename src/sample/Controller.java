package sample;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    @FXML
    public JFXButton upload_file;
    @FXML
    public Label file_path;
    @FXML
    public JFXButton decry_file;
    @FXML
    public JFXButton encry_file;
    @FXML
    public JFXButton encry;
    @FXML
    public JFXButton decry;
    @FXML
    public JFXTextField key;
    @FXML
    public JFXTextField message;
    @FXML


    public TextArea file_result;
    public JFXTextField files_key;
    public JFXButton save_edited_file;
    public JFXButton delete_file_contetn;
    private String messageFromFile="";
    private String messageFilePath="";
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JFXDialog dialog = new JFXDialog();
        dialog.setContent(new Label("Content"));


        save_edited_file.setOnAction(event -> {
            try {
          CeaserViewModel.getInstance().saveEditOnFile(file_result,messageFilePath);
          messageFromFile=file_result.getText();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        delete_file_contetn.setOnAction(event -> {
            try {
                CeaserViewModel.getInstance().deleteAllFileContent(messageFilePath,file_result);
                messageFromFile="";
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        encry.setOnAction(event -> {
            CeaserViewModel.getInstance().bindRealEncryption(message,key);
        });
        decry.setOnAction(event ->{
                    CeaserViewModel.getInstance().bindRealDecryption(message,key);

                }
        );
        upload_file.setOnAction(event -> {
            try {
                Map<String,String> file_meta_data= CeaserViewModel.getInstance().bindUploadFile(file_result,file_path);
                this.messageFromFile=file_meta_data.get("file_data");
                this.messageFilePath=file_meta_data.get("file_name");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        encry_file.setOnAction(event -> {
            try {
                this.messageFromFile= CeaserViewModel.getInstance().bindFileEncryption(messageFromFile,messageFilePath,file_result,files_key);
                CeaserViewModel.getInstance().bindFileEncryption(messageFromFile,messageFilePath,file_result,files_key);
                this.messageFromFile= CeaserViewModel.getInstance().bindFileEncryption(messageFromFile,messageFilePath,file_result,files_key);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        decry_file.setOnAction(event -> {
            try {
                this.messageFromFile= CeaserViewModel.getInstance().bindFileDecryption(messageFromFile,messageFilePath,file_result,files_key);
                CeaserViewModel.getInstance().bindFileDecryption(messageFromFile,messageFilePath,file_result,files_key);
                this.messageFromFile= CeaserViewModel.getInstance().bindFileDecryption(messageFromFile,messageFilePath,file_result,files_key);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

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

public class PlayFairEncryptionController implements Initializable {
    public JFXTextField  play_fair_key;
    public JFXButton  play_fair_decry;
    public JFXTextField  play_fair_message;
    public JFXButton  play_fair_encry;
    public JFXButton  play_fair_upload_file;
    public JFXButton  play_fair_save_edited_file;
    public JFXButton  play_fair_delete_file_contetn;
    public JFXButton  play_fair_decry_file;
    public JFXButton  play_fair_encry_file;
    public Label file_path;
    public TextArea  play_fair_file_result;
    public JFXTextField  play_fair_files_key;
    public JFXTextField  string_size;
    public JFXButton  shuffle;
    public JFXButton  shuffle_real;



    private String messageFilePath;
    private String messageFromFile;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        shuffle.setOnAction(event -> {
            RandomString rendom=new RandomString(play_fair_file_result.getText().length());
            String randomKey=rendom.nextString();

            play_fair_files_key.setText(randomKey);

        });


        shuffle_real.setOnAction(event -> {
            RandomString rendom=new RandomString(play_fair_message.getText().length());
            String randomKey=rendom.nextString();
            play_fair_key.setText(randomKey);

        });
         play_fair_save_edited_file.setOnAction(event -> {
            try {
                 PlayFairViewModel.getInstance().saveEditOnFile( play_fair_file_result,messageFilePath);
                messageFromFile= play_fair_file_result.getText();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
         play_fair_delete_file_contetn.setOnAction(event -> {
            try {
                 PlayFairViewModel.getInstance().deleteAllFileContent(messageFilePath, play_fair_file_result);
                messageFromFile="";
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
         play_fair_encry.setOnAction(event -> {
             PlayFairViewModel.getInstance().bindRealEncryption( play_fair_message, play_fair_key);
        });
         play_fair_decry.setOnAction(event ->{
                     PlayFairViewModel.getInstance().bindRealDecryption( play_fair_message, play_fair_key);

                }
        );
         play_fair_upload_file.setOnAction(event -> {
            try {
                Map<String,String> file_meta_data=   PlayFairViewModel.getInstance().bindUploadFile( play_fair_file_result,file_path);
                this.messageFromFile=file_meta_data.get("file_data");
                this.messageFilePath=file_meta_data.get("file_name");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
         play_fair_encry_file.setOnAction(event -> {
            try {
                this.messageFromFile=   PlayFairViewModel.getInstance().bindFileEncryption(messageFromFile,messageFilePath, play_fair_file_result, play_fair_files_key);
//                 PlayFairViewModel.getInstance().bindFileEncryption(messageFromFile,messageFilePath, play_fair_file_result, play_fair_files_key);
//                this.messageFromFile=   PlayFairViewModel.getInstance().bindFileEncryption(messageFromFile,messageFilePath, play_fair_file_result, play_fair_files_key);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
         play_fair_decry_file.setOnAction(event -> {
            try {
                this.messageFromFile=   PlayFairViewModel.getInstance().bindFileDecryption(messageFromFile,messageFilePath, play_fair_file_result, play_fair_files_key);
//                 PlayFairViewModel.getInstance().bindFileDecryption(messageFromFile,messageFilePath, play_fair_file_result, play_fair_files_key);
//                this.messageFromFile=   PlayFairViewModel.getInstance().bindFileDecryption(messageFromFile,messageFilePath, play_fair_file_result, play_fair_files_key);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}

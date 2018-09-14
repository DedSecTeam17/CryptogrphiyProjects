package sample.RSA;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;


public class RsaController implements Initializable
{


    @FXML
    private JFXButton decry;

    @FXML
    private JFXTextField message;

    @FXML
    private JFXButton encry;

    @FXML
    private JFXButton compress;

    @FXML
    private Label encry_hex;

    @FXML
    private JFXTextField key;

    @FXML
    private JFXButton generate_keys;

    @FXML
    private Label public_key;

    @FXML
    private Label private_key;

    @FXML
    private Label modulus;

    @FXML
    private JFXButton upload_file;

    @FXML
    private JFXButton save_edited_file;

    @FXML
    private JFXButton delete_file_contetn;

    @FXML
    private JFXButton decry_file;

    @FXML
    private JFXButton encry_file;

    @FXML
    private Label file_path;

    @FXML
    private TextArea file_result;

    @FXML
    private JFXTextField files_key;

    private String messageFromFile="";
    private String messageFilePath="";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JFXDialog dialog = new JFXDialog();
        dialog.setContent(new Label("Content"));

        generate_keys.setOnAction(event -> {
          Map<String,String> map=  RsaViewModel.getInstance().generateKey();
          private_key.setText("Private Key :"+map.get("PRIVATE_KEY"));
          public_key.setText("Public Key :"+map.get("PUBLIC_KEY"));
//          modulus.setText(map.get("MODULUS"));



        });





        save_edited_file.setOnAction(event -> {
            try {
                 RsaViewModel.getInstance().saveEditOnFile(file_result,messageFilePath);
                messageFromFile=file_result.getText();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        delete_file_contetn.setOnAction(event -> {
            try {
                 RsaViewModel.getInstance().deleteAllFileContent(messageFilePath,file_result);
                messageFromFile="";
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        encry.setOnAction(event -> {
            RsaViewModel.getInstance().bindRealEncryption(message);
            encry_hex.setText(message.getText().toString());

//             RsaViewModel.getInstance().bindRealEncryption(message);
        });
        decry.setOnAction(event ->{
            RsaViewModel.getInstance().bindRealDecryption(message);
            encry_hex.setText(message.getText().toString());


                }
        );
        compress.setOnAction(event -> {
            StringBuilder sb = new StringBuilder();
            for (byte aByteData : message.toString().getBytes()) {
                sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
            }
            encry_hex.setText(sb.toString());

        });
        upload_file.setOnAction(event -> {
            try {
                Map<String,String> file_meta_data=   RsaViewModel.getInstance().bindUploadFile(file_result,file_path);
                this.messageFromFile=file_meta_data.get("file_data");
                this.messageFilePath=file_meta_data.get("file_name");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        encry_file.setOnAction(event -> {
            try {
                this.messageFromFile=   RsaViewModel.getInstance().bindFileEncryption(messageFromFile,messageFilePath,file_result );


            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        decry_file.setOnAction(event -> {
            try {
                this.messageFromFile=   RsaViewModel.getInstance().bindFileDecryption(messageFromFile,messageFilePath,file_result);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    
    
}

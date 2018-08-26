package sample.PolyAlphabitics;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class PolyAlphaController implements Initializable {

    @FXML
    private JFXTextField message;

    @FXML
    private JFXButton decry;

    @FXML
    private JFXButton encry;

    @FXML
    private Label round;

    @FXML
    private JFXButton add_rule;

    @FXML
    private JFXButton remove_rule;

    @FXML
    private JFXComboBox<String> type;

    @FXML
    private JFXComboBox<Integer> amount;

    @FXML
    private JFXComboBox<String> direction;

    @FXML
    private JFXTreeTableView<RuleItem> RulesTreeTableView;

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
    Rule rule = new Rule();
    List<Rule> ruleList;
    private String getMessage;
    private String CipherText = "";
    private String PlainText = "";
    private String messageFilePath;
    private String messageFromFile;
    private ObservableList<RuleItem> codeObservableList;
    private int _round=1;
    public void initialize(URL location, ResourceBundle resources) {
        round.setText("0");
        decry.setDisable(true);
        try {
            ruleList = RuleModel.getInstance().getAllRules();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            SetupRulesTableAndReloadDataIntoIt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 26; i++) {
            amount.getItems().add(i);
        }
        type.getItems().add("shift");
        type.getItems().add("replace");
        direction.getItems().add("left");
        direction.getItems().add("right");

        add_rule.setOnAction(event -> {
            String type = this.type.getSelectionModel().getSelectedItem();
            int amount = this.amount.getSelectionModel().getSelectedItem();
            String directionCombo = this.direction.getSelectionModel().getSelectedItem();
            boolean direction;
            if (directionCombo.equals("left"))
                direction = false;
            else direction = true;

            try {
                RuleModel.getInstance().addRuleToDb(type, amount, direction);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                SetupRulesTableAndReloadDataIntoIt();
            } catch (Exception e) {
                e.printStackTrace();
            }

//            ruleList.add(new Rule(type, amount, direction));

        });
        remove_rule.setOnAction(event -> {
            int r_index = RulesTreeTableView.getSelectionModel().getSelectedIndex();
            RuleItem RuleItem = codeObservableList.get(r_index);
            StringProperty DeletedIndexRow = RuleItem.id;
            String x = DeletedIndexRow.toString();
            String z = x.replace("[", "").replace("]", "").replace("value", "").replace("StringProperty : ", "").replaceAll(" ", "");
            int index = Integer.parseInt(z);
            try {
                RuleModel.getInstance().deleteRule(index);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {

                SetupRulesTableAndReloadDataIntoIt();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        encry.setOnAction(event -> {
            if (_round < 10) {
                decry.setDisable(false);
                _round += 1;
                round.setText(String.valueOf(_round));
                try {
                    ruleList = RuleModel.getInstance().getAllRules();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
//                getMessage=message.getText();
                PolyAlphabeticViewModel.getInstance().bindRealEncryption(message, ruleList);
//                message.setText(cipherText);
            } else {
                encry.setDisable(true);

            }

        });
        decry.setOnAction(event -> {
            if (_round > 1) {
                encry.setDisable(false);
                _round += -1;
                round.setText(String.valueOf(_round));
//                getMessage=message.getText();
                PolyAlphabeticViewModel.getInstance().bindRealDecryption(message, ruleList);
//                message.setText(plaintText);
            } else {
                decry.setDisable(true);
            }


        });







         save_edited_file.setOnAction(event -> {
            try {
                PolyAlphabeticViewModel.getInstance().saveEditOnFile( file_result,messageFilePath);
                messageFromFile= file_result.getText();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
         delete_file_contetn.setOnAction(event -> {
            try {
                PolyAlphabeticViewModel.getInstance().deleteAllFileContent(messageFilePath, file_result);
                messageFromFile="";
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
         upload_file.setOnAction(event -> {
            try {
                Map<String,String> file_meta_data=  PolyAlphabeticViewModel.getInstance().bindUploadFile( file_result,file_path);
                this.messageFromFile=file_meta_data.get("file_data");
                this.messageFilePath=file_meta_data.get("file_name");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
         encry_file.setOnAction(event -> {
            try {
                this.messageFromFile=  PolyAlphabeticViewModel.getInstance().bindFileEncryption(messageFromFile,messageFilePath, file_result, ruleList);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
         decry_file.setOnAction(event -> {
            try {
                this.messageFromFile=  PolyAlphabeticViewModel.getInstance().bindFileDecryption(messageFromFile,messageFilePath, file_result, ruleList);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }




    private void SetupRulesTableAndReloadDataIntoIt() throws Exception {
        JFXTreeTableColumn<RuleItem, String> type = new JFXTreeTableColumn<>("type");
        type.setPrefWidth(79.5);
        type.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<RuleItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<RuleItem, String> param) {
                return param.getValue().getValue().type;
            }
        });


        JFXTreeTableColumn<RuleItem, String> amount = new JFXTreeTableColumn<>("amount");
        amount.setPrefWidth(79.5);
        amount.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<RuleItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<RuleItem, String> param) {
                return param.getValue().getValue().amount;
            }
        });


        JFXTreeTableColumn<RuleItem, String> direction = new JFXTreeTableColumn<>("direction");
        direction.setPrefWidth(79.5);
        direction.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<RuleItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<RuleItem, String> param) {
                return param.getValue().getValue().direction;
            }
        });


        JFXTreeTableColumn<RuleItem, String> id = new JFXTreeTableColumn<>("id");
        id.setPrefWidth(79.5);
        id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<RuleItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<RuleItem, String> param) {
                return param.getValue().getValue().id;
            }
        });

        try {


            codeObservableList = FXCollections.observableArrayList();
            List<Rule> ruleList = RuleModel.getInstance().getAllRules();


            for (int i = 0; i < ruleList.size(); i++) {
                String _id = String.valueOf(ruleList.get(i).getId());
                String _type = ruleList.get(i).getType();
                String _amount = String.valueOf(ruleList.get(i).getAmount());
                String _direction = String.valueOf(ruleList.get(i).isDirection());


                codeObservableList.add(new RuleItem(_type, _amount, _direction, _id));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        final TreeItem<RuleItem> root = new RecursiveTreeItem<RuleItem>(codeObservableList, RecursiveTreeObject::getChildren);


        RulesTreeTableView.getColumns().setAll(type, amount, direction, id);
        RulesTreeTableView.setRoot(root);
        RulesTreeTableView.setShowRoot(false);


    }
    class RuleItem extends RecursiveTreeObject<RuleItem> {
        StringProperty type;
        StringProperty amount;
        StringProperty direction;
        StringProperty id;

        public RuleItem(String type, String amount, String direction, String id) {
            this.type = new SimpleStringProperty(type);
            this.amount = new SimpleStringProperty(amount);
            this.direction = new SimpleStringProperty(direction);
            this.id = new SimpleStringProperty(id);
        }
    }

}

package sample;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RootController implements Initializable {
    public JFXHamburger hum;
    public JFXDrawer drawer;
    public AnchorPane main_pane;
    public AnchorPane root_pane;
    private HamburgerSlideCloseTransition burgerTask;
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("layouts/welcome.fxml"));
            root_pane.getChildren().add(anchorPane);
            VBox box = FXMLLoader.load(getClass().getResource("layouts/drawer_content.fxml"));
            HBox CESAER = (HBox) box.getChildren().get(3);
            HBox RAIL_FENCE = (HBox) box.getChildren().get(5);
//            
            HBox HOME = (HBox) box.getChildren().get(1);
            HBox PLAY_FAIR = (HBox) box.getChildren().get(7);

            AnchorPane welcome = FXMLLoader.load(getClass().getResource("layouts/welcome.fxml"));
            AnchorPane sample = FXMLLoader.load(getClass().getResource("layouts/CeaserEncryption.fxml"));
            AnchorPane RailFenceEncryption = FXMLLoader.load(getClass().getResource("layouts/RailFenceEncryption.fxml"));
            AnchorPane PlayFair = FXMLLoader.load(getClass().getResource("layouts/RSA.fxml"));
            List<AnchorPane> list = new ArrayList<>();
            list.add(welcome);
            list.add(sample);
            list.add(PlayFair);
            list.add(RailFenceEncryption);
//
            ConfigureTabsForSideNav(CESAER, RAIL_FENCE, HOME,PLAY_FAIR ,"caser_sipher", drawer,sample,list);
            ConfigureTabsForSideNav(RAIL_FENCE, CESAER, HOME,PLAY_FAIR ,"rail_fence", drawer,RailFenceEncryption,list);
            ConfigureTabsForSideNav(PLAY_FAIR, CESAER, HOME,RAIL_FENCE ,"play_fair", drawer,PlayFair,list);
            ConfigureTabsForSideNav(HOME, CESAER, RAIL_FENCE,PLAY_FAIR ,"home", drawer,welcome,list);
//
            drawer.setSidePane(box);
            burgerTask = new HamburgerSlideCloseTransition(hum);
            burgerTask.setRate(-1);
            hum.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                burgerTask.setRate(burgerTask.getRate() * -1);
                burgerTask.play();
                if (drawer.isClosed()) {
                    drawer.open();
                } else {
                    drawer.close();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    "caser_sipher"  mono_sipher
    private void ConfigureTabsForSideNav(HBox hBox, HBox RAIL_FENCE, HBox h3,HBox h4, String CaseButton,  JFXDrawer drawer, AnchorPane mainPane, List<AnchorPane> removbalePane) {
        for (Node node : hBox.getChildren()) {
            if (node.getAccessibleText() != null) {
                node.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    if (node.getAccessibleText().equals(CaseButton)) {
                        Node node1 = hBox.getChildren().get(0);
                        Node node2 = RAIL_FENCE.getChildren().get(0);
                        Node node3 = h3.getChildren().get(0);
                        Node node4=h4.getChildren().get(0);
                        node1.setStyle("-fx-background-color: #4ECDC4;\n");
                        node2.setStyle("-fx-background-color: white;\n");
                        node3.setStyle("-fx-background-color: white;\n");
                        node4.setStyle("-fx-background-color: white;\n");
                        burgerTask.setRate(burgerTask.getRate() * -1);
                        burgerTask.play();
                        if (drawer.isClosed()) {
                            drawer.open();
                        } else {
                            drawer.close();
                        }
                        for (AnchorPane pane : removbalePane){
                            if (pane!=mainPane){
                                root_pane.getChildren().remove(pane);
                            }
                        }
                        root_pane.getChildren().add(mainPane);
                    }
                });
            }
        }
    }
}

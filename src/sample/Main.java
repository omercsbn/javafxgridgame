package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {

    Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        startMenu();
    }

    public void startLevel(int level,LoadGameData loadGameData){
        Parent root = renderLevel(level,loadGameData);
        Scene scene = new Scene(root, 650, 720);
        scene.getStylesheets().add("style.css");
        stage.setTitle("Level: " + level);
        stage.setScene(scene);
        stage.show();
    }
    public void startMenu(){
        Parent root = renderMenu();
        Scene scene = new Scene(root, 650, 720);
        scene.getStylesheets().add("style.css");
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }


    public VBox renderMenu(){
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(50);
        vbox.setAlignment(Pos.CENTER);

        CellLogic.setHighScore(SaveAndLoadData.loadHighScore());


        Text welcomeText = new Text("Welcome to The Grid Game");
        Text highScoreText = new Text("High Score: " + CellLogic.getHighScore());


        Button newGameBtn = new Button("New game");
        Button resumeBtn = new Button("Resume game");
        Button quitBtn = new Button("Quit");

        welcomeText.getStyleClass().add("menu-text");
        highScoreText.getStyleClass().add("menu-score-text");
        newGameBtn.getStyleClass().add("menu-button");
        resumeBtn.getStyleClass().add("menu-button");
        quitBtn.getStyleClass().add("menu-button");


        newGameBtn.setOnMouseClicked(event -> {
            startLevel(1,null);
        });
        quitBtn.setOnMouseClicked(event -> {
            stage.close();
        });
        resumeBtn.setOnMouseClicked(event -> {
            LoadGameData data = SaveAndLoadData.loadGame();
            if(data != null){
                System.out.println("load level: " + data.getCurrentLevel());
                CellLogic.setScore(data.getCurrentScore());
                startLevel(data.getCurrentLevel(),data);
            }
        });

        vbox.getChildren().addAll(welcomeText,highScoreText,newGameBtn,resumeBtn,quitBtn);

        return vbox;
    }


    public VBox renderLevel(int level,LoadGameData loadGameData) {

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(8);

        HBox topPane = new HBox();
        topPane.setPadding(new Insets(0,30,0,30));
        topPane.setSpacing(230);
        Text levelText = new Text("Level: " + level);
        levelText.getStyleClass().add("red-text");

        Text scoreText = new Text(" " + CellLogic.getScore());
        Text highScoreText = new Text("HS: " + CellLogic.getHighScore());
        scoreText.getStyleClass().add("red-text");
        highScoreText.getStyleClass().add("red-text");

        CellLogic.setScoreText(scoreText);
        CellLogic.setHighScoreText(highScoreText);
        CellLogic.setCurrentLevel(level);

        topPane.setMinWidth(1280);
        topPane.setMinHeight(20);

        topPane.getChildren().addAll(levelText,scoreText,highScoreText);

        Grid centerPane;
        if(loadGameData != null) {
            System.out.println("yes : grid");
            centerPane = loadGameData.getGrid();
        }
        else{
            centerPane = SaveAndLoadData.loadLevel(level);
        }

        stage.setOnCloseRequest(event -> {

            SaveAndLoadData.saveGame(centerPane,level,CellLogic.getScore());
//            Alert closeConfirmation = new Alert(
//                    Alert.AlertType.CONFIRMATION,
//                    "Do you want to save game?"
//            );
//            Button okButton = (Button) closeConfirmation.getDialogPane().lookupButton(
//                    ButtonType.OK
//            );
//            okButton.setText("Save");
//            closeConfirmation.setHeaderText("Save game");
//            closeConfirmation.initModality(Modality.APPLICATION_MODAL);
//            closeConfirmation.initOwner(stage);
//
//            closeConfirmation.setX(stage.getX() + stage.getWidth() / 2);
//            closeConfirmation.setY(stage.getY() + stage.getHeight() / 2);
//
//            Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
//            if (!ButtonType.OK.equals(closeResponse.get())) {
//
//                event.consume();
//            }
//            else{
//                stage.close();
//            }

        });


        HBox bottomPane = new HBox();
        Button nextLevelBtn = new Button("Next Level >> ");
        Text bottomText = new Text("--- Text --");
        CellLogic.setLogText(bottomText);
        bottomText.getStyleClass().add("red-text");

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinSize(10, 1);
        nextLevelBtn.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);

        nextLevelBtn.setVisible(false);
        CellLogic.setNextLevelBtn(nextLevelBtn);

        nextLevelBtn.setOnMouseClicked(event -> {

            startLevel(level+1,null);
        });

        bottomPane.getChildren().addAll(bottomText,spacer,nextLevelBtn);


        vbox.getChildren().addAll(topPane,centerPane,bottomPane);


        return vbox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

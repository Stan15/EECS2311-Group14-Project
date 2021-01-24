package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;


public class GUI extends Application {

    private TextArea textArea = new TextArea();
    private VBox dropArea;
    private String[] supportedTypes = {"txt"};
    private List<File> tabFiles = new ArrayList<File>();
    private Tab fileInputTab;

    @Override
    public void start(Stage stage) {
        stage.setTitle("T2X");
        stage.setHeight(500);
        stage.setWidth(700);

        VBox parent = new VBox();
        parent.setId("parent");
        parent.getStylesheets().add("GUI/style.css");

        TabPane tabPane = new TabPane();

        Tab textInputTab = new Tab();
        textInputTab.setText("Text field");
        textInputTab.setContent(this.textArea);

        this.fileInputTab = new Tab();
        fileInputTab.setText("Select file");
        this.fileInputTab.setContent(this.getSelectFileControls(stage));

        tabPane.getTabs().addAll(textInputTab, fileInputTab);


        VBox convertHeading = new VBox();
        convertHeading.setPadding(new Insets(10));
        Label convertLabel = new Label("Convert");
        convertLabel.setId("convertLabel");
        convertLabel.setTextFill(Color.web("#707070"));
        convertLabel.setFont(Font.font(15));

        Separator separator = new Separator();

        convertHeading.getChildren().addAll(convertLabel, separator);


        HBox bttnContainer = new HBox();
        bttnContainer.setId("bttnContainer");
        Button convertGuitarBttn = new Button("Guitar");
        Button convertDrumBttn = new Button("Drums");

        bttnContainer.getChildren().addAll(convertGuitarBttn, convertDrumBttn);

        parent.getChildren().addAll(tabPane, convertHeading, bttnContainer);

        Scene scene = new Scene(parent);

        stage.setScene(scene);
        stage.show();
    }

    private Node getSelectFileControls(Stage stage) {
        VBox container = new VBox();
        this.dropArea = new VBox();
        container.setId("fileSelectOuterContainer");
        this.dropArea.setId("fileSelectInnerContainer");

        this.dropArea.setOnDragOver(new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != dropArea
                        && event.getDragboard().hasFiles() && isValidTextFile(event.getDragboard().getFiles())) {
                    /* allow for both copying and moving, whatever user chooses */
                    fileHover();
                    event.acceptTransferModes(TransferMode.COPY);
                }
                event.consume();
            }
        });

        this.dropArea.setOnDragExited(new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                fileExit();
                event.consume();
            }
        });

        this.dropArea.setOnDragDropped(new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    addTabFiles(db.getFiles());
                    resetFileDropRegion(stage);
                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        VBox fileDropRegion = getFileDropRegion(stage);

        this.dropArea.getChildren().add(fileDropRegion);
        container.getChildren().add(this.dropArea);
        return container;
    }

    private void resetFileDropRegion(Stage stage) {
        this.dropArea.getChildren().clear();
        this.dropArea.getChildren().add(getFileDropRegion(stage));
    }


    private boolean isValidTextFile(List<File> files) {
        for (File file : files) {
            if (file==null) {
                return false;
            }

            String fileName = file.getName();
            int dotIndex = fileName.lastIndexOf('.');
            if (dotIndex == -1) {
                return false;
            }else {
                boolean isValid = false;
                for (String ext : this.supportedTypes) {
                    if (fileName.substring(dotIndex + 1).equals(ext)) {
                        isValid = true;
                    }
                }
                if (!isValid) {
                    return false;
                }
            }
        }
        return true;
    }

    private void fileHover() {
        this.dropArea.setStyle("-fx-border-color: #70aaff");
    }

    private VBox getFileDropRegion(Stage stage) {
        VBox fileDropRegion = new VBox();
        if (this.tabFiles==null || this.tabFiles.isEmpty()) {
            Label text1 = new Label("Drag a text file here");
            Label text2 = new Label("Or if you prefer...");

            Button selectFileBttn = new Button("Pick a file from your device");
            selectFileBttn.setId("fileSelectButton");

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Image");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            //Adding action on the menu item
            selectFileBttn.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    //Opening a dialog box
                    fileChooser.showOpenDialog(stage);
                }});


            fileDropRegion.getChildren().addAll(text1, text2, selectFileBttn);

            text1.setTextFill(Color.web("#707070"));
            text2.setTextFill(Color.web("#707070"));

            text1.setFont(Font.font(20));
            text2.setFont(Font.font(10));
        }else {
            for (File file : this.tabFiles) {
                HBox fileDisplay = new HBox();
                Label fileLabel = new Label(file.getName());
                Button button = new Button("X");
                button.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        removeTabFile(file, stage);
                    }
                });
                fileDisplay.getChildren().addAll(fileLabel, button);
                fileDropRegion.getChildren().add(fileDisplay);
            }
        }

        return fileDropRegion;
    }

    private void fileExit() {
        this.dropArea.setStyle("-fx-border-color: #707070");
    }

    private void addTabFiles(List<File> tabFiles) {
        if (this.tabFiles!=null && this.tabFiles.size()>5) {return;}
        Set<String> files = new HashSet<>();

        if (this.tabFiles!=null && this.tabFiles.isEmpty()) {
            for (File file : this.tabFiles) {
                files.add(file.getName());
            }
        }


        for (File file : tabFiles) {
            if (!files.contains(file.getName())) {
                this.tabFiles.add(file);
                files.add(file.getName());
            }
        }
        this.tabFiles = tabFiles;
    }

    private void removeTabFile(File file, Stage stage) {
        this.tabFiles.remove(file);
        resetFileDropRegion(stage);
    }


}

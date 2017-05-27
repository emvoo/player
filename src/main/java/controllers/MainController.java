package controllers;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Song;

import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Marcin Wisniewski on 26/05/17.
 */
public class MainController implements Initializable{
    MediaPlayer player;
    ObservableList<Song> songs = FXCollections.observableArrayList();

    @FXML private TableView<Song> table;
    @FXML private TableColumn<Song, Integer> id;
    @FXML private TableColumn<Song, String> title, artist, album, length;

    @FXML private AnchorPane root;
    @FXML private MenuItem openFile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initiateTable();
        singleFileOpen();
//        addManyFiles();
    }

//    private void addManyFiles() {
//        openFile.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                Stage stage = (Stage) root.getScene().getWindow();
//                FileChooser fc = new FileChooser();
//                fc.setTitle("LOL");
//                fc.getExtensionFilters().add(
//                        new FileChooser.ExtensionFilter("mp3", "*.mp3")
//                );
//                File file = fc.showOpenDialog(stage);
//                if(file != null){
//                    String uri = file.toURI().toString();
//                    Media song = new Media(uri);
//                    MediaPlayer player = new MediaPlayer(song);
//                    player.play();
//                }
//            }
//        });
//    }

    private void singleFileOpen() {
        openFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Stage stage = (Stage) root.getScene().getWindow();
                FileChooser fc = new FileChooser();
                fc.setTitle("Choose File");
                fc.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("mp3", "*.mp3")
                );
                File file = fc.showOpenDialog(stage);
                if(file != null){
                    if(player != null)
                        player.stop();
                    table.getItems().clear();
                    songs.clear();
                    String uri = file.toURI().toString();
                    Media song = new Media(uri);
                    player = new MediaPlayer(song);
                    player.setOnReady(new Runnable() {
                        @Override
                        public void run() {
                            double duration = song.getDuration().toMinutes();
                            duration = Math.floor(duration*100)/100;
                            String len = String.valueOf(duration);
                            len = len.replace(".", ":");
                            System.out.println("Duration: " + duration);
                            int id = songs.size()+1;
                            String t = "";
                            String art = "";
                            String alb = "";
                            for (Map.Entry<String, Object> entry : song.getMetadata().entrySet()){
                                System.out.println(entry.getKey() + ": " + entry.getValue());

                                if(entry.getKey().equals("title"))
                                    t = (String) entry.getValue();
                                if(entry.getKey().equals("artist"))
                                    art = (String) entry.getValue();
                                if(entry.getKey().equals("album"))
                                    alb = (String) entry.getValue();
                            }
                            Song s = new Song(id, t, art, alb, len);


                            songs.add(s);
                            table.getItems().addAll(songs);
                            player.play();
                        }
                    });
                }
            }
        });
    }

    private void initiateTable() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        artist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        album.setCellValueFactory(new PropertyValueFactory<>("album"));
        length.setCellValueFactory(new PropertyValueFactory<>("length"));
        // set automatic columns width
        table.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );
        id.setMaxWidth( 1f * Integer.MAX_VALUE * 10 ); // 50% width
        title.setMaxWidth( 1f * Integer.MAX_VALUE * 30 ); // 30% width
        artist.setMaxWidth( 1f * Integer.MAX_VALUE * 30 ); // 20% width
        album.setMaxWidth( 1f * Integer.MAX_VALUE * 20 ); // 20% width
        length.setMaxWidth( 1f * Integer.MAX_VALUE * 10 ); // 20% width
    }

    @FXML public void prev(){}
    @FXML public void play(){
        // pamietaj zeby sprawidzc czy gra bo jak gra to pause
        // jak nie gra to play
    }
    @FXML public void stop(MouseEvent event){
        player.stop();
    }

    @FXML public void next(){}

    @FXML public void close(){
        Platform.exit();
    }


}

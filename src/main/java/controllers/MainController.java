package controllers;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Song;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Marcin Wisniewski on 26/05/17.
 */
public class MainController implements Initializable{
    private static double xOffset = 0;
    private static double yOffset = 0;
    ObservableList<Song> songs = FXCollections.observableArrayList();

    @FXML private TableView<Song> table;
    @FXML private TableColumn<Song, Integer> id;
    @FXML private TableColumn<Song, String> title, artist, album;
    @FXML private TableColumn<Song, Double> length;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initiateTable();
        Song song = new Song(1, "title", "artist", "album", 3.25);
        songs.add(song);
        table.getItems().addAll(songs);
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
    @FXML public void stop(){}
    @FXML public void next(){}

    @FXML public void close(){
        Platform.exit();
    }


}

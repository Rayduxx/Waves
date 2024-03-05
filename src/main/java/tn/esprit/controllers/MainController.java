package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import tn.esprit.application.Main;
import tn.esprit.controllers.ProdContoller;
import tn.esprit.models.ChordModel;
import tn.esprit.models.MainModel;
import tn.esprit.objects.ExternInterface;
import tn.esprit.objects.MidiInterface;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController extends Controller
{

    public BorderPane main_pane;
    public HBox state_bar;

    public MenuBar menu_bar;
    public MenuItem menu_file_new;
    public MenuItem menu_file_open;
    public MenuItem menu_file_save;
    public MenuItem menu_file_saveas;
    public MenuItem menu_file_quit;
    public MenuItem menu_edit_undo;
    public MenuItem menu_edit_redo;
    public MenuItem menu_view_set_light_theme;
    public MenuItem menu_view_set_dark_theme;
    public MenuItem menu_help_about;
    public MenuItem menu_options_options;

    public Slider master_volume_slider;
    public TextField sequencer_tempo;


    public SplitPane splitPaneHorizontal;
    public SplitPane splitPaneVertical;

    private Stage popup;

    private Scene scene;

    /* Containers */
    @FXML
    private Pane mainContainer;
    public AnchorPane secondContainer;
    public AnchorPane sideContainer;
    private Pane popup_container;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /* On initialise le model "principal" */
        this.model = new MainModel();

        this.model.midiInterface = new MidiInterface();
        this.model.mainExternInterface = new ExternInterface();

        if ( System.getProperty("os.name").equals("Linux") )
            this.model.sink = this.model.mainExternInterface.getAlsaSinkNumberFromPid();

        /* On initialise les murphystudio.models secondaires */
        this.model.chordModel = new ChordModel();


        /* Assignation des actions de la barre des menus */
        menu_file_new.setOnAction(e -> System.out.println("New"));
        menu_file_open.setOnAction(e -> loadFile());
        menu_file_save.setOnAction(e -> System.out.println("Save"));
        menu_file_saveas.setOnAction(e -> saveFile());
        menu_file_quit.setOnAction(e -> exit());
        menu_edit_undo.setOnAction(e -> System.out.println("Undo"));
        menu_edit_redo.setOnAction(e -> System.out.println("Redo"));
        menu_options_options.setOnAction(e -> setPopup("options.fxml", 600, 400));
        menu_view_set_dark_theme.setOnAction(e -> {this.setDarkTheme();});
        menu_view_set_light_theme.setOnAction(e -> {this.setLightTheme();});
        menu_help_about.setOnAction(e -> setPopup("about.fxml", 360, 180));


        this.popup = new Stage();
        this.popup.initModality(Modality.APPLICATION_MODAL);
        //this.popup.initOwner(Main.getPrimaryStage());
        this.popup.initOwner(ProdContoller.getPrimaryStage());
        this.popup_container = new Pane();

        Scene popup_scene = new Scene(popup_container);
        this.popup.setScene(popup_scene);
        this.popup.setResizable(false);

        sequencer_tempo.setText("120");

        /* ---- < Main Event Listener >  ---- */

        sequencer_tempo.textProperty().addListener((observable, oldValue, newValue) -> {
            // Only numeric
            if ( newValue.isEmpty() || Objects.equals(newValue, "")) return;
            if ( !newValue.matches("\\d") )
            {
                String value = newValue.replaceAll("[^\\d]", "");
                sequencer_tempo.setText(value);
                model.midiInterface.tempo = (Integer.parseInt(value));
            }

        });

        master_volume_slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if ( this.model.mainExternInterface.volume != null )
            {
                float val = newValue.floatValue() / 100.0f;
                this.model.mainExternInterface.volume.setValue(val);
            }
            else if ( System.getProperty("os.name").equals("Linux") )
            {
                double val = newValue.doubleValue() / 10;
                try {
                    for ( Integer s : this.model.sink )
                        Runtime.getRuntime().exec("pactl set-sink-input-volume " + s + " " + val);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        this.master_volume_slider.setValue(50);


        /* ---- </ Main Event Listener > ---- */

        try {
            loadView("ChordSorter.fxml", secondContainer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            loadView("ChordMaker.fxml", sideContainer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* On charge la vue par defaut (piste_layout) */
        try {
            Pane piste_layout = loadView("piste_layout.fxml");
            mainContainer.getChildren().setAll(piste_layout);
        } catch (IOException e) {
            e.printStackTrace();
        }

        splitPaneVertical.setDividerPosition(0,0.63);
        splitPaneHorizontal.setDividerPosition(0, 0);

    }

    private void loadView(String viewName, Pane container) throws IOException {
        /* On récupère la vue dans un pane */
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/" + viewName));
        Node newPane = fxmlLoader.load();

        /* On charge le controller et on lui passe le model */
        Controller ctrl = fxmlLoader.getController();

        if(ctrl != null)
        {
            if ( ctrl.getClass() == ChordMakerController.class ) model.chordMakerController = (ChordMakerController) ctrl;
            if ( ctrl.getClass() == ChordSorterController.class ) model.chordSorterController = (ChordSorterController) ctrl;
            if ( ctrl.getClass() == PisteLayoutController.class ) model.pisteLayoutController = (PisteLayoutController) ctrl;
            ctrl.setModel(model);
        }

        /* On l'affiche dans le container */
        container.getChildren().setAll(newPane);
    }

    public void exit() {
        //model.player.synthesizer.close();
        //model.player.receiver.close();
        //model.player.sequencer.close();
        model.mainExternInterface.close();
        System.exit(0);

    }

    private void loadFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open MIDI File");

        // Set extension filter if needed
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("MIDI files (*.midi, *.mid)", "*.midi", "*.mid");
        fileChooser.getExtensionFilters().add(extFilter);

        //File file = fileChooser.showOpenDialog(Main.getPrimaryStage());
        File file = fileChooser.showOpenDialog(ProdContoller.getPrimaryStage());

        if (file != null) {
            try {
                // Replace the following line with your actual logic to handle loading MIDI files
                // Example: model.midiInterface.loadMidiFile(file);
                System.out.println("File selected: " + file.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save MIDI File");

        // Set extension filter if needed
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("MIDI files (*.midi, *.mid)", "*.midi", "*.mid");
        fileChooser.getExtensionFilters().add(extFilter);

        // Set initial directory to user's desktop
        String userHome = System.getProperty("user.home");
        fileChooser.setInitialDirectory(new File(userHome + File.separator + "Desktop"));

        //File file = fileChooser.showSaveDialog(Main.getPrimaryStage());
        File file = fileChooser.showSaveDialog(ProdContoller.getPrimaryStage());

        if (file != null) {
            try {
                // Replace the following line with your actual logic to handle saving MIDI files
                // Example: model.midiInterface.saveMidiFile(file);
                System.out.println("File saved to: " + file.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void setDarkTheme()
    {
        this.resetTheme();
        this.main_pane.getStyleClass().add("dark");
        this.popup_container.getStyleClass().add("dark");
    }

    public void setLightTheme()
    {
        this.resetTheme();
        this.main_pane.getStyleClass().add("light");
        this.popup_container.getStyleClass().add("light");
    }

    //Besoin de retirer la classe dark si elle est déjà présente pour ne pas la dupliquer
    public void resetTheme(){
        this.main_pane.getStyleClass().remove("dark");
        this.main_pane.getStyleClass().remove("light");
        this.popup_container.getStyleClass().remove("dark");
        this.popup_container.getStyleClass().remove("light");
    }

    public void setPopup(String viewName, int width, int height)
    {
        String title = viewName.substring(0, 1).toUpperCase() + viewName.substring(1).replace(".fxml", "");
        this.popup.setTitle(title);

        try {
            loadView(viewName, popup_container);
        } catch (IOException e) {
            e.printStackTrace();
        }

        popup.setHeight(height+30);
        popup.setWidth(width);
        popup.show();
    }

    public void setKeyPressed(KeyCode code) {
        switch ( code )
        {
            case DELETE:
                model.chordSorterController.deleteSelected();
                break;
            case ADD:
                model.chordSorterController.isRandomTile = true;
                model.chordSorterController.createTile();
                break;
            case PAGE_DOWN:
                model.chordSorterController.previousTile();
                break;
            case PAGE_UP:
                model.chordSorterController.nextTile();
                break;
        }
    }
}
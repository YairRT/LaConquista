import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.collections.ObservableList;

public class Main extends Application{

  private static int[][] arregloMapa;
  private static Mapa mapaPrincipal;
  public static void main(String[] args) {
    PersonajePrincipal diego = new PersonajePrincipal("Dieguapo");
    Villano pedrito = new Villano("Pedriro", 10, 5, "sandwich");
    diego.adquirirExperiencia(20);
    //comenzarPelea(diego, pedrito);
    mapaPrincipal = new Mapa(12, 5, diego);
    mapaPrincipal.imprimirPlano();
    arregloMapa = mapaPrincipal.getPlano();
    launch(args);
  }

  public void start(Stage primaryStage){
    mapaPrincipal.start(primaryStage);
  }
}

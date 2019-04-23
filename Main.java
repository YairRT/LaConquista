import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.collections.ObservableList;


public class Main extends Application{
  /*
  Main:
  Debe haber un menu para crear un personaje mapaPrincipal
  Despues, debera aparecer en el mapa principal.
  Debera recorrerlo para llegar al primer Nivel y superarlo
  Seguira asi hasta completar la historia y llegar al jefe.
  */

  private Mapa mapaPrincipal;
  private static PersonajePrincipal heroe;
  private Stage primaryStage;
  private Main main;

  public static void main(String[] args) {
    //mapaPrincipal = new Mapa(12, 5, diego);
    //mapaPrincipal.imprimirPlano();
    launch(args);
  }

  public void start(Stage primaryStage){
	  this.main = main;
	  this.primaryStage = primaryStage;
    CharacterSelection menu = new CharacterSelection(heroe);
    primaryStage.setScene(menu.createCharacterSelection());
    menu.getWarriorButton().addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
        System.out.println("Boton guerrero presionado");
				menu.definirHeroe("guerrero");
        heroe = menu.getHeroe();
        mapaPrincipal = new Mapa(10, 5, heroe,10,main);
        primaryStage.setScene(mapaPrincipal.createMapa());
			}
		});
    menu.getTlatoaniButton().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
        System.out.println("Boton tlatoani presionado");
        menu.definirHeroe("tlatoani");
        heroe = menu.getHeroe();
        mapaPrincipal = new Mapa(10, 5, heroe,10, main);
        primaryStage.setScene(mapaPrincipal.createMapa());
      }
		});
    menu.getPriestButton().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
		public void handle(MouseEvent e){
      System.out.println("Boton sacerdote presionado");
			menu.definirHeroe("sacerdote");
      heroe = menu.getHeroe();
      mapaPrincipal = new Mapa(10, 5, heroe,10, main);
      primaryStage.setScene(mapaPrincipal.createMapa());
		}
		});
	primaryStage.show();
  }
  public void setSceneBatalla(PersonajePrincipal heroe, Villano villano, Main main){
	  Battle battle = new Battle(heroe, villano);
	  primaryStage.setScene(battle.createBattleScene());
  }
}

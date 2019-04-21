import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class Main extends Application {
  /*
  Main:
  Debe haber un menu para crear un personaje mapaPrincipal
  Despues, debera aparecer en el mapa principal.
  Debera recorrerlo para llegar al primer Nivel y superarlo
  Seguira asi hasta completar la historia y llegar al jefe.
  */

  private static Mapa mapaPrincipal;
  private static PersonajePrincipal heroe;

  public static void main(String[] args) {

    //mapaPrincipal = new Mapa(12, 5, diego);
    //mapaPrincipal.imprimirPlano();
    launch(args);
  }

  public void start(Stage primaryStage){
    CharacterSelection menu = new CharacterSelection(heroe);
    primaryStage.setScene(menu.createCharacterSelection());
    primaryStage.show();
    menu.getWarriorButton().addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
        System.out.println("Boton guerrero presionado");
				menu.definirHeroe("guerrero");
        heroe = menu.getHeroe();
        mapaPrincipal = new Mapa(10, 5, heroe);
        primaryStage.setScene(mapaPrincipal.createMapa());
			}
		});
    menu.getTlatoaniButton().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
        System.out.println("Boton tlatoani presionado");
        menu.definirHeroe("tlatoani");
        heroe = menu.getHeroe();
        mapaPrincipal = new Mapa(10, 5, heroe);
        primaryStage.setScene(mapaPrincipal.createMapa());
      }
		});
    menu.getPriestButton().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
		public void handle(MouseEvent e){
      System.out.println("Boton sacerdote presionado");
			menu.definirHeroe("sacerdote");
      heroe = menu.getHeroe();
      mapaPrincipal = new Mapa(10, 5, heroe);
      primaryStage.setScene(mapaPrincipal.createMapa());
		}
		});
  }
}

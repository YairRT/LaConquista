import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.collections.ObservableList;

public class Battle {

	private PersonajePrincipal heroe;
	private Villano enemigo;
	private Scene battleScene;

	public Battle(PersonajePrincipal heroe, Villano enemigo){
		this.heroe = heroe;
		this.enemigo = enemigo;
	}

	public Scene createBattleScene(){
		BorderPane bp = new BorderPane();
		Label lb1 = new Label(heroe.getNombre());
		Label lb2 = new Label(enemigo.getNombre());
		bp.setLeft(lb1);
		bp.setRight(lb2);

		//Se crean los botones para poder atacar al adversario
		Button normalAttack = new Button("Ataque normal");
		battleScene = new Scene(bp);
		return battleScene;
	}
	/*

	public static boolean comenzarPelea(PersonajePrincipal heroe, Villano enemigo){
    Scanner sc = new Scanner(System.in);
    if (Math.random() > 0.5) {
      System.out.println("Es el turno de " + enemigo.getNombre());
      System.out.println(enemigo.toString());
      heroe.recibirAtaque(enemigo.atacar());
    }
    while (heroe.getHP() > 0 && enemigo.getHP() > 0) {
      System.out.println("Es el turno de " + heroe.getNombre());
      System.out.println(heroe.toString());
      System.out.println("Deseas atacar o abrir inventario: [A / I]");
      String respuesta = sc.next(); sc.nextLine();
      if (respuesta.equals("I")) {
        while(respuesta != "11"){
          Objeto[] inventario = heroe.getInventario();
          System.out.println("Ingresa el numero del objeto a utilizar o ingresa 11 para salir.");
          int inventarioVacio = 0;
          for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] != null) {
              if (inventario[i].getTipoObjeto() != 5) {
                System.out.println(i + inventario[i].toString());
              }
            } else {
              inventarioVacio++;
            }
          }
          if (inventarioVacio==10) {
            System.out.println("Inventario vacio. Ingresa 11 para continuar");
          }
          respuesta = sc.next();sc.nextLine();
          if (respuesta.equals("11")) {
            break;
          }
          try {
            heroe.usarObjeto(Integer.parseInt(respuesta));
          } catch(NumberFormatException e){
            continue;
          }
        }
      }
      enemigo.recibirAtaque(heroe.atacar());
      if (enemigo.getHP() <= 0) {
        return true;
      }
      System.out.println("Es el turno de " + enemigo.getNombre());
      System.out.println(enemigo.toString());
      heroe.recibirAtaque(enemigo.atacar());
    }
    return false;
  } */
}

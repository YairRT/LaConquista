import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.collections.ObservableList;

public class Mapa {
  private  int[][] plano;
  private int[] ubicacionHeroe = new int[2];
  private int numObjetos;
  private Objeto[] objetosRegados;
  private int[][] ubicacionesObjetos;
  private int[][] ubicacionesVillanos;
  private PersonajePrincipal heroeMapa;
  public int numVillanos;
  private static Label[][] botonesMapa;
  private Villano[] villanosRegados;
  private int nuevaX;
  private int nuevaY;
  private int tamanio;
  private Battle battle;
  private int villanosEnfrentados = 0;
  private Main main;

  /*
  Valores del arreglo:
  0 camino libre
  1 obstaculo
  2 objeto de inventario
  9 heroe
  */

  public Mapa(int tamanio, int itemsColocar, PersonajePrincipal heroe, int numVillanos, Main main){
	  this.main = main;
	  this.numVillanos = numVillanos;
	  this.tamanio = tamanio;
    heroeMapa = heroe;
    plano = new int[tamanio][tamanio];
    ubicacionHeroe[0] = heroe.getUbiacionHeroeMP()[1]; //x
    ubicacionHeroe[1] = heroe.getUbiacionHeroeMP()[0]; //y
    plano[ubicacionHeroe[0]][ubicacionHeroe[1]] = 9;
    for (int i = 0; i < tamanio; i++) {
      for (int j = 0; j < tamanio; j++) {
        if (i == 0 || i == tamanio-1 || j == 0 || j==tamanio-1) {
          plano[i][j] = 1;
        }
      }
    }
    numObjetos =itemsColocar;
    objetosRegados = new Objeto[numObjetos];
	villanosRegados = new Villano[numVillanos];
    ubicacionesObjetos = new int[numObjetos][2];
	ubicacionesVillanos = new int[numVillanos][2];
    for (int i = 0; i < itemsColocar; i++) {
      int ubiSecretaI; int ubiSecretaJ;
      do {
        ubiSecretaI = (int)Math.floor(Math.random()*tamanio);
        ubiSecretaJ = (int)Math.floor(Math.random()*tamanio);
      } while (plano[ubiSecretaI][ubiSecretaJ] != 0);
      ubicacionesObjetos[i][0] = ubiSecretaJ;
      ubicacionesObjetos[i][1] = ubiSecretaI;
      plano[ubiSecretaI][ubiSecretaJ] = 2;
      objetosRegados[i] = itemAleatorio();
    }
	for(int i = 0;i<numVillanos;i++){
		int ubiSecretaI; int ubiSecretaJ;
		do{
		ubiSecretaI = (int)Math.floor(Math.random()*tamanio);
		ubiSecretaJ = (int)Math.floor(Math.random()*tamanio);
	} while (plano[ubiSecretaI][ubiSecretaJ] != (0) && plano[ubiSecretaI][ubiSecretaJ] != (2));
	ubicacionesVillanos[i][0] = ubiSecretaJ;
    ubicacionesVillanos[i][1] = ubiSecretaI;
	plano[ubiSecretaI][ubiSecretaJ] = 3;
	villanosRegados[i] = crearVillano("Villano", "Ataque");
	}
  }

  public static Objeto itemAleatorio(){
    String nombreItem;
    switch ((int)Math.floor(Math.random()*4) + 1) {
      case 1: nombreItem = "Espada"; return new Objeto(nombreItem, "arma");
      case 2: nombreItem = "Casco"; return new Objeto(nombreItem, "armadura");
      case 3: nombreItem = "Pocion Energia"; return new Objeto(nombreItem, "masEnergia");
      default: nombreItem = "Pocion Vida"; return new Objeto(nombreItem, "masVida");
    }
  }

  public int[][] getPlano(){
    return plano;
  }

  public int[] getUbicacionHeroe(){
    return ubicacionHeroe;
  }

  public int getNumObjetos(){
    return numObjetos;
  }
  public int getNumVillanos(){
	  return numVillanos;
  }

  public int getUbicacionObjeto(int indice1, int indice2){
    return ubicacionesObjetos[indice1][indice2];
  }
  public int getUbicacionVillanos(int indice1, int indice2){
	return ubicacionesVillanos[indice1][indice2];
  }

  public Objeto[] getObjetosRegados(){
    return objetosRegados;
  }

  public void setUbicacionHeroe(int x, int y){
    ubicacionHeroe[0] = x;
    ubicacionHeroe[1] = y;
  }

  public PersonajePrincipal getHeroeMapa(){
    return heroeMapa;
  }

  public void moverHeroe(int desplazamientoX, int desplazamientoY){
    nuevaX = getUbicacionHeroe()[0]+desplazamientoX;
    nuevaY = getUbicacionHeroe()[1]+desplazamientoY;
    switch (plano[nuevaY][nuevaX]) {
      case 0: cambiarHeroePlano(nuevaY, nuevaX); setUbicacionHeroe(nuevaX, nuevaY); break;
      case 1: break;
      case 2: cambiarHeroePlano(nuevaY, nuevaX); setUbicacionHeroe(nuevaX, nuevaY);
      int indiceObjeto=0;
      System.out.println("Has encontrado un objeto");
      botonesMapa[getUbicacionHeroe()[1]][getUbicacionHeroe()[0]].getStyleClass().remove("fondo-rojo");
      for (int i = 0; i < getNumObjetos(); i++) {
        if (getUbicacionObjeto(i, 0) == nuevaX && getUbicacionObjeto(i, 1) == nuevaY) {
          indiceObjeto = i;
        }
      }
	  getHeroeMapa().addInventario(getObjetosRegados()[indiceObjeto]);break;
	  case 3: cambiarHeroePlano(nuevaX, nuevaY);setUbicacionHeroe(nuevaX, nuevaY);
	  int indiceVillano=0;
	  botonesMapa[getUbicacionHeroe()[1]][getUbicacionHeroe()[0]].getStyleClass().remove("fondo-azul");
	  for(int i = 0; i < getNumVillanos(); i++) {
        if (getUbicacionVillanos(i, 0) == nuevaX && getUbicacionVillanos(i, 1) == nuevaY) {
          break;
        }
	  }
	  break;
	}
	if(plano[nuevaY][nuevaX]==3){
		main.setSceneBatalla(heroeMapa,getVillano(), main);
	}
  }
  
  public Villano getVillano(){
	  return villanosRegados[villanosEnfrentados++];
  }

  public void cambiarHeroePlano(int i, int j){
    plano[getUbicacionHeroe()[1]][getUbicacionHeroe()[0]] = 0;
    plano[i][j] = 9;
  }

  public void imprimirPlano(){
    for (int i = 0; i < plano.length; i++) {
      for (int j = 0; j < plano.length; j++) {
        System.out.print(/*(getUbicacionHeroe()[0]==j && getUbicacionHeroe()[1]==i?9:*/plano[i][j] + " ");
      }
      System.out.println();
    }
  }

  public Scene createMapa(){
    GridPane grid = new GridPane();
    Scene scene = new Scene(grid);
    scene.getStylesheets().add("estilos.css");
    //scene.getStylesheets();
    botonesMapa = new Label[getPlano().length][getPlano()[0].length];
    for (int i = 0; i < getPlano().length; i++) {
      for (int j = 0; j < getPlano()[i].length; j++) {
        botonesMapa[i][j] = new Label(""+getPlano()[i][j]);
        grid.add(botonesMapa[i][j], j, i, 1, 1);
        switch (getPlano()[i][j]) {
          case 1: botonesMapa[i][j].getStyleClass().add("fondo-negro"); break;
          case 2: botonesMapa[i][j].getStyleClass().add("fondo-rojo"); break;
		  case 3: botonesMapa[i][j].getStyleClass().add("fondo-azul");break;
          case 9: botonesMapa[i][j].getStyleClass().add("fondo-verde");break;
          default: botonesMapa[i][j].getStyleClass().add("fondo-blanco");
        }
      }
    }
    scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>(){
      public void handle(KeyEvent e){
        int x = getUbicacionHeroe()[0];
        int y = getUbicacionHeroe()[1];
        botonesMapa[y][x].getStyleClass().remove("fondo-verde");
        switch(e.getCode().getName()){
          case "W": moverHeroe(0, -1); break;
          case "A": moverHeroe(-1, 0); break;
          case "S": moverHeroe(0, 1); break;
          case "D": moverHeroe(1, 0); break;
        }
        x = getUbicacionHeroe()[0];
        y = getUbicacionHeroe()[1];
        botonesMapa[y][x].getStyleClass().add("fondo-verde");
      }
    });
    return scene;
  }
   private Villano crearVillano(String nombre, String nombreAtaque){
    int puntosDefensa = (int)Math.ceil(Math.random()*5);
    return new Villano(nombre, getHeroeMapa().getXP(), puntosDefensa, nombreAtaque);
  }
  
  public int getNuevaX(){
	  return nuevaX;
  }
  public int getNuevaY(){
	  return nuevaY;
  }

}

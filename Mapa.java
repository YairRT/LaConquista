public class Mapa{
  public  int[][] plano;
  private int[] ubicacionHeroe = new int[2];
  private int numObjetos;
  private Objeto[] objetosRegados;
  private int[][] ubicacionesObjetos;
  private PersonajePrincipal heroeMapa;

  /*
  Valores del arreglo:
  0 camino libre
  1 obstaculo
  2 objeto de inventario
  */

  public Mapa(int tamanio, int itemsColocar, PersonajePrincipal heroe){
    heroeMapa = heroe;
    plano = new int[tamanio][tamanio];
    ubicacionHeroe[0] = 1; //x
    ubicacionHeroe[1] = 1; //y
    for (int i = 0; i < tamanio; i++) {
      for (int j = 0; j < tamanio; j++) {
        if (i == 0 || i == tamanio-1 || j == 0 || j==tamanio-1) {
          plano[i][j] = 1;
        }
      }
    }
    numObjetos =itemsColocar;
    objetosRegados = new Objeto[numObjetos];
    ubicacionesObjetos = new int[numObjetos][2];
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

  public int[] getUbicacionHeroe(){
    return ubicacionHeroe;
  }

  public int getNumObjetos(){
    return numObjetos;
  }

  public int getUbicacionObjeto(int indice1, int indice2){
    return ubicacionesObjetos[indice1][indice2];
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
    int nuevaX = getUbicacionHeroe()[0]+desplazamientoX;
    int nuevaY = getUbicacionHeroe()[1]+desplazamientoY;
    switch (plano[nuevaX][nuevaY]) {
      case 0: setUbicacionHeroe(nuevaX, nuevaY); break;
      case 1: break;
      case 2: setUbicacionHeroe(nuevaX, nuevaY);
      int indiceObjeto=0;
      for (int i = 0; i < getNumObjetos(); i++) {
        if (getUbicacionObjeto(i, 0) == nuevaX && getUbicacionObjeto(i, 1) == nuevaY) {
          indiceObjeto = i;
        }
      }
      getHeroeMapa().addInventario(getObjetosRegados()[indiceObjeto]);
    }
  }

  public void imprimirPlano(){
    for (int i = 0; i < plano.length; i++) {
      for (int j = 0; j < plano.length; j++) {
        System.out.print((getUbicacionHeroe()[0]==j && getUbicacionHeroe()[1]==i?9:plano[i][j]) + " ");
      }
      System.out.println();
    }
  }
}

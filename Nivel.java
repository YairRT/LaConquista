import java.util.Scanner;
public class Nivel extends Mapa {
  Villano[] villanos;
  int[][] ubicacionVillanos;

  /*
  Valores del arreglo:
  0 camino libre
  1 obstaculo
  2 objeto de inventario
  3 enemigo
  */

  public Nivel(int tamanio, int itemsColocar, PersonajePrincipal persona){
    super(tamanio, itemsColocar, persona);
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
  }
}

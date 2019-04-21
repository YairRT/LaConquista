import java.util.Scanner;
public class Nivel extends Mapa {

  /*
  Valores del arreglo:
  0 camino libre
  1 obstaculo
  2 objeto de inventario
  3 enemigo
  9 heroe
  */

  //Antes de entrar a un nivel tiene que guardar la ubicacion del heroe en el mapa general

  public Nivel(int tamanio, int itemsColocar, PersonajePrincipal persona, int numVillanos){
    super(tamanio, itemsColocar, persona, numVillanos);
  }

  public void moverHeroe(int desplazamientoX, int desplazamientoY){
    int nuevaX = getUbicacionHeroe()[0]+desplazamientoX;
    int nuevaY = getUbicacionHeroe()[1]+desplazamientoY;
    switch (getPlano()[nuevaX][nuevaY]) {
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
	  break;
	  case 3: setUbicacionHeroe(nuevaX, nuevaY);
	  int indiceVillano=0;
	  for(int i = 0; i < getNumVillanos(); i++) {
        if (getUbicacionVillanos(i, 0) == nuevaX && getUbicacionVillanos(i, 1) == nuevaY) {
          break;
        }
      }
    }
  }
 
}

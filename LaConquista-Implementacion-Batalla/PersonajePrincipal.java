import java.util.Scanner;
public class PersonajePrincipal extends Personaje{
  public static Scanner sc = new Scanner(System.in);
  private Objeto[] inventario = new Objeto[10];
  private AtaqueEspecial[] habilidadesEspeciales = new AtaqueEspecial[3];
  //arreglo para guardar la ubicacion del personajes en el mapa principal
  private int[] ubicacionHeroeMP = new int[2];

  public PersonajePrincipal(String nombre){
    super(nombre, 0, 0);
    setUbicacionHeroeMP(1, 1);
  }

  public int[] getUbiacionHeroeMP(){
    return ubicacionHeroeMP;
  }

  public void setUbicacionHeroeMP(int i, int j){
    ubicacionHeroeMP[0] = i;
    ubicacionHeroeMP[1] = j;
  }

  public Objeto[] getInventario(){
    return inventario;
  }

  public void addInventario(Objeto item){
    for (int i = 0; i < 10; i++) {
      if (inventario[i] == null) {
        inventario[i] = item;
      }
    }
  }

  public void usarObjeto(int indice){
    Objeto item = inventario[indice];
    switch (item.getTipoObjeto()) {
      case 1: setAtaque(getAtaque() + item.getAumentoAtaque());break;
      case 2: setDefensa(getDefensa() + item.getAumentoDefensa()); break;
      case 3: setEnergia(getEnergia()+ item.getAumentoEnergia()); if(getEnergia() > 100){setEnergia(100);} removeInventario(indice); break;
      case 4: setHP(getHP()+ item.getAumentoVida()); if(getHP() > 100){setHP(100);} removeInventario(indice); break;
      default: System.out.println("No se puede usar");
    }
  }

  public void removeInventario(int index){
    inventario[index] = null;
  }

  public int atacar(){
    //primero se deben mostrar los ataques disponibles al usuario
    System.out.println("1 Ataque normal");
    /*Guardar en un arreglo la informacion de cada ataque.
    El primer indice guarda el daño total y el segundo el costo energetico*/
    int[][] listaAtaques = new int[4][2];
    listaAtaques[0][0] = getAtaque();
    listaAtaques[0][1] = 10;
    int j = 1;
    for (int i = 0; i < 3; i++) {
      if (habilidadesEspeciales[i] != null) {
        if(habilidadesEspeciales[i].getCostoEnergetico() <= getEnergia()){
          j++;
          System.out.println(j+" "+habilidadesEspeciales[i].toString());
          //Guardar la informacion del ataque especial disponible en el arreglo
          listaAtaques[j-1][0] = habilidadesEspeciales[i].getAumentoAtaque()*getAtaque();
          listaAtaques[j-1][1] = habilidadesEspeciales[i].getCostoEnergetico();
        }
      }
    }
    int eleccion = sc.nextInt()-1;
    if (eleccion >= j) {
      //Si el usuario ingresa un numero correcto
      eleccion = 0;
    }
    //Hacer un ataque y restarle la energia al personaje
    setEnergia(getEnergia()-listaAtaques[eleccion][1]+10);
    System.out.println("Ataque: " + listaAtaques[eleccion][0]);
    return listaAtaques[eleccion][0];
  }

  public void adquirirExperiencia(int puntosExperiencia){
    setXP(puntosExperiencia+getXP());
    if (getXP() > 29) {
      habilidadesEspeciales[2] = new AtaqueEspecial("Habilidad 3", 2);
    }
    if (getXP()>19) {
      habilidadesEspeciales[1] = new AtaqueEspecial("Habilidad 2", 1);
    }
    if (getXP()>9) {
      habilidadesEspeciales[0] = new AtaqueEspecial("Habilidad 1", 0);
    }
  }
}

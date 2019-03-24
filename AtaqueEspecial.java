public class AtaqueEspecial{
  private String nombre;
  private int costoEnergetico;
  private int aumentoAtaque;

  public AtaqueEspecial(String nombre, int indice){
    this.nombre = nombre;
    switch (indice) {
      case 0: costoEnergetico = 20; aumentoAtaque = 2; break;
      case 1: costoEnergetico = 30; aumentoAtaque = 3; break;
      case 2: costoEnergetico = 40; aumentoAtaque = 4; break;
      default: System.out.println("El indice proporcionado como argumento no es valido");
    }
  }

  public int getCostoEnergetico(){
    return costoEnergetico;
  }

  public int getAumentoAtaque(){
    return aumentoAtaque;
  }

  public String toString(){
    return nombre + "\n  Energia: "+ costoEnergetico;
  }
}

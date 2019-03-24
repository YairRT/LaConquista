public class Personaje {
  private String nombre;//nombre del personaje
  private int hP;//vida del personaje con maximo de 100
  private int xP;//experiencia con un maximo de treinta
  private int ataque;//daño a inflingir a un enemigo
  private int defensa;//cuanto daño restar al ataque de enemigo
  private int energia; //energia disponible para hacer ataques especiales

  public Personaje(String nombre, int experiencia, int puntosAtaque, int puntosDefensa){
    this.nombre = nombre;
    hP = 100;
    xP = experiencia;
    ataque = puntosAtaque;
    defensa = puntosDefensa;
    energia = 100;
  }

  public String getNombre(){
    return nombre;
  }

  public int getHP(){
    return hP;
  }

  public void setHP(int healthPoints){
    hP = healthPoints;
  }

  public int getXP(){
    return xP;
  }

  public void setXP(int experiencePoints){
    xP = experiencePoints;
    if (xP > 30) {
      xP = 30;
    }
  }

  public int getAtaque(){
    return ataque;
  }

  public void setAtaque(int puntosAtaque){
    ataque = puntosAtaque;
  }

  public int getDefensa(){
    return defensa;
  }

  public void setDefensa(int puntosDefensa){
    defensa = puntosDefensa;
  }

  public int getEnergia(){
    return energia;
  }

  public void setEnergia(int puntos){
    energia = puntos;
  }

  public String toString(){
    return getNombre()+"\nHP: "+getHP()+"\nXP: "+getXP()+"\nAtaque: "+getAtaque()+"\nDefensa: "+getDefensa()+"\nEnergia: "+getEnergia();
  }

  public void recibirAtaque(int danio){
    if (danio > getDefensa()) {
      setHP(getHP()-danio+getDefensa());
    }
  }
}

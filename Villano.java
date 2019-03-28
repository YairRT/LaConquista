public class Villano extends Personaje{
  private AtaqueEspecial habilidadEspecial;

  public Villano(String nombre, int experiencia, int puntosDefensa, String nombreAtaque){
    super(nombre, experiencia, puntosDefensa);
    if (getXP()>20) {
      habilidadEspecial = new AtaqueEspecial(nombreAtaque, 2);
    } else if (getXP() > 19) {
      habilidadEspecial = new AtaqueEspecial(nombreAtaque, 1);
    } else if(getXP() > 9){
      habilidadEspecial = new AtaqueEspecial(nombreAtaque, 0);
    }
  }

  public int atacar(){
    if (Math.random() > 0.5 && getXP() > 9 ) {
      if (getEnergia() >= habilidadEspecial.getCostoEnergetico()) {
        System.out.println("Ataque especial: " + habilidadEspecial.getAumentoAtaque()*getAtaque());
        setEnergia(getEnergia() - habilidadEspecial.getCostoEnergetico() + 10);
        return habilidadEspecial.getAumentoAtaque()*getAtaque();
      }
    }
    System.out.println("Ataque normal: " + getAtaque());
    return getAtaque();
  }
}

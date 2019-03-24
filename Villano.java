public class Villano extends Personaje{
  private AtaqueEspecial habilidadEspecial;

  public Villano(String nombre, int experiencia, int puntosAtaque, int puntosDefensa, String nombreAtaque){
    super(nombre, experiencia, puntosAtaque, puntosDefensa);
    if (getXP()>30) {
      habilidadEspecial = new AtaqueEspecial(nombreAtaque, 2);
    } else if (getXP() > 20) {
      habilidadEspecial = new AtaqueEspecial(nombreAtaque, 1);
    } else if(getXP() > 10){
      habilidadEspecial = new AtaqueEspecial(nombreAtaque, 0);
    }
  }

  public int atacar(){
    if (Math.random() > 0.5 && habilidadEspecial != null ) {
      if (getEnergia() > habilidadEspecial.getCostoEnergetico()) {
        return habilidadEspecial.getAumentoAtaque()*getAtaque();
      }
    }
    return getAtaque();
  }
}

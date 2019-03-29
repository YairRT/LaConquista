public class Main {
  public static void main(String[] args) {
    PersonajePrincipal diego = new PersonajePrincipal("Dieguapo");
    Villano pedrito = new Villano("Pedriro", 10, 5, "sandwich");
    diego.adquirirExperiencia(20);
    //comenzarPelea(diego, pedrito);
    Mapa mapaPrincipal = new Mapa(12, 5, diego);
    mapaPrincipal.imprimirPlano();
  }
}

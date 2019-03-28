import java.util.Scanner;

public class Main {
  public static Scanner sc = new Scanner(System.in);
  public static void main(String[] args) {
    PersonajePrincipal diego = new PersonajePrincipal("Dieguapo");
    Villano pedrito = new Villano("Pedriro", 10, 5, "sandwich");
    diego.adquirirExperiencia(20);
    comenzarPelea(diego, pedrito);
  }

  public static boolean comenzarPelea(PersonajePrincipal heroe, Villano enemigo){
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

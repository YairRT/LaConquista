import java.util.Scanner;

public class Main {
  public static Scanner sc = new Scanner(System.in);
  public static void main(String[] args) {

  }

  public static void comenzarPelea(PersonajePrincipal heroe, Villano enemigo){
    System.out.print("Ha comenzado una batalla!\nEl primer turno es para: ");
    if (Math.random() > 0.5) {
      System.out.println(enemigo.getNombre());
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
          for (int i = 0; i < 10; i++) {
            if (inventario != null) {
              if (inventario[i].getTipoObjeto() != 5) {
                System.out.println(i + inventario[i].toString());
              }
            }
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
        break;
      }
      System.out.println("Es el turno de " + enemigo.getNombre());
      System.out.println(enemigo.toString());
      heroe.recibirAtaque(enemigo.atacar());
    }
  }
}

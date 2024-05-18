/* CLASE DE PRUEBA INICIAL

import java.util.*;

public class Principal {
    public static void main(String[] args) {

        Scanner entrada=new Scanner(System.in);

        System.out.println("Indique divisa que desea convertir");
        String monedaBase=entrada.nextLine();
        System.out.println("Indique divisa a la cual desea hacer la conversión");
        String monedaTarget=entrada.nextLine();
        System.out.println("Indique el monto de la divisa:");
        double monto=entrada.nextDouble();
        
        ConvertirMoneda nuevaConversion= new ConvertirMoneda();
        
        Moneda moneda=nuevaConversion.convierteMoneda(monedaBase, monedaTarget, monto);
        System.out.println(moneda);
    }
}*/

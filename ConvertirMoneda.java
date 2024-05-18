import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import com.google.gson.Gson;

public class ConvertirMoneda{

    public Moneda convierteMoneda(String monedaBase,String monedaTarget,double monto){
        URI direccion =URI.create("https://v6.exchangerate-api.com/v6/e6c360b5f78d532ed697c75f/pair/"+monedaBase+"/"+monedaTarget+"/"+monto);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();

        try {
            HttpResponse<String> response=client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                Gson gson = new Gson();
                Moneda resultado = gson.fromJson(response.body(), Moneda.class);
                // Asignar el valor de monto a la instancia de Moneda
                return new Moneda(resultado.base_code(), resultado.target_code(), resultado.conversion_rate(), resultado.conversion_result(), monto);
            } else {
                throw new IOException("Failed to fetch data from API: " + response.statusCode());
            }
        }   catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}






















        /*private static final String ApiURL="https://v6.exchangerate-api.com/v6/e6c360b5f78d532ed697c75f/pair/";*/
    
       
package org.example;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

public class CurrencyConverter {
    public static void main(String[] args) {
        String apiKey = "98b731647d35e432c045dce6"; // Reemplaza con tu clave de API
        String baseCurrency = "USD"; // Moneda base (por ejemplo, dólares)
        String targetCurrency = "EUR"; // Moneda objetivo (por ejemplo, euros)

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.realexchangerates.com/v1/latest/" + baseCurrency)
                .addHeader("Authorization", "Bearer " + apiKey)
                .build();

        try {
            Response response = client.newCall(request).execute();
            JSONObject jsonResponse = new JSONObject(response.body().string());
            double exchangeRate = jsonResponse.getJSONObject("rates").getDouble(targetCurrency);

            System.out.println("1 " + baseCurrency + " = " + exchangeRate + " " + targetCurrency);
            // Puedes realizar la conversión según tus necesidades
            double amountInBaseCurrency = 100; // Cantidad en dólares
            double convertedAmount = amountInBaseCurrency * exchangeRate;
            System.out.println(amountInBaseCurrency + " " + baseCurrency + " = " + convertedAmount + " " + targetCurrency);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


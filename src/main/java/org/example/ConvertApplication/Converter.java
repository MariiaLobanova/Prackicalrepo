package org.example.ConvertApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Currency;

public class Converter {

    public static void main(String[] args) {
        String  apiKey = "fca_live_2d7qLOt978jIKCWrYfG1SCrQ15LWYKFBkrocnsLu";
        CurrencyAPI client= new CurrencyAPI(apiKey);

        try {
            System.out.println("Status: " + client.getStatus());

            String[] currencies = {"EUR", "CAD"};
            System.out.println("Currencies: " + client.getCurrencies(currencies));

            System.out.println("Latest Exchange Rates: " + client.getLatestExchangeRates());

            String date = "2022-02-02";
            System.out.println("Historical Exchange Rates (2022-02-02): " + client.getHistoricalExchangeRates(date));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

class CurrencyAPI{
    private final String APIKEY;
    private final String BASEURL = "https://freecurrencyapi.com/api/";

    public CurrencyAPI(String apikey){
        this.APIKEY = apikey;
    }
    public String getStatus() throws IOException {
        String endpoint= BASEURL + "status?apikey="+APIKEY;
        return sendGetRequest(endpoint);
     }

     public String getCurrencies(String[] currencies) throws IOException {
        String currencyList = String.join(",", currencies);
        String endpoint = BASEURL + "currencies?apikey=" + APIKEY +"&currencies=" + currencyList;
        return sendGetRequest(endpoint);
     }
    public String getLatestExchangeRates() throws IOException {
        String endpoint = BASEURL + "latest?apikey=" + APIKEY;
        return sendGetRequest(endpoint);
    }

    public String getHistoricalExchangeRates(String date) throws IOException {
        String endpoint = BASEURL + "historical?apikey=" + APIKEY + "&date=" + date;
        return sendGetRequest(endpoint);
    }

    private  String sendGetRequest(String endpoint) throws IOException{
        URL url= new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine())!=null){
            response.append(line);
        }
        reader.close();
        return response.toString();
    }
}




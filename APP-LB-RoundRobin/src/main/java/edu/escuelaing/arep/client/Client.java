package edu.escuelaing.arep.client;

import okhttp3.*;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;


public class Client {

    private OkHttpClient httpClient;
    private String baseUrl="http://192.168.99.100:";
    private String[] ports={"8085","8086","8087"};
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private int serverNumber=0;

    public Client() {
        httpClient=new OkHttpClient();
    }


    /**
     *metodo get
     * @return the response of the method
     * @throws UnirestException exception
     */
    public String getMessages() throws UnirestException  {

        /**Request request = new Request.Builder()
         .url(baseUrl+ports[serverNumber]+path)
         .get()
         .build();
         Response response = httpClient.newCall(request).execute();
         return response.body().string();
         **/
        HttpResponse<String> apiResponse = Unirest.get(baseUrl+ports[serverNumber]+"/mensajes").asString();
        // System.out.println("Peticion GET de "+baseUrl+ports[serverNumber]);
        return apiResponse.getBody();

    }


    /**
     * metodo post
     * @param message The message to apply to the post method
     * @return the response of the method
     * @throws UnirestException  exception IO
     */
    public String postMessage( String message) throws UnirestException {
        /** RequestBody body = RequestBody.create(message,JSON);
         Request request = new Request.Builder()
         .url(baseUrl+ports[serverNumber]+path)
         .post(body)
         .build();
         Response response = httpClient.newCall(request).execute();
         return response.body().string();
         **/
        HttpResponse<String> apiResponse = Unirest.post(baseUrl+ports[serverNumber]+"/mensajes")
                .body(message)
                .asString();
        //  System.out.println("Peticion POST de "+baseUrl+ports[serverNumber]);
        return apiResponse.getBody();
    }


    /**
     * Load balancer balanceador
     */
    public void roundRobin(){
        this.serverNumber= (this.serverNumber+1)% ports.length;

    }
}

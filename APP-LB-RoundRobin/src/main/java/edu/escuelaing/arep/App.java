package edu.escuelaing.arep;

import static spark.Spark.*;

import edu.escuelaing.arep.client.Client;

/**
 * Clase Spark web server
 */
public class App
{
    /**
     * Inicializa el servidor web de spar, publica y recibe los mensajes
     * @param args ejecucion
     */
    public static void main( String[] args )
    {
        port(getPort());
        staticFileLocation("/web");
        Client cliente=new Client();

        get("/", (req, res) -> {
            res.redirect("index.html");
            return null;
        });

        get("/mensajes",(req, res) -> {
            res.status(200);
            res.type("application/json");
            String response=cliente.getMessages();
            cliente.roundRobin();
            return response;
        });

        post("/mensajes",(request, response) -> {
            cliente.postMessage(request.body());
            cliente.roundRobin();
            return "";
        });
    }

    /**
     * retorna el puerto en uso
     * @return el puerto usado
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 6001;
    }

}

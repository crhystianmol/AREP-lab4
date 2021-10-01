package edu.escuelaing.arep.virtual.docker;

import static spark.Spark.*;

import com.google.gson.Gson;

import edu.escuelaing.arep.virtual.docker.model.Mensaje;
import edu.escuelaing.arep.virtual.docker.persistencia.MensajePersistencia;
import edu.escuelaing.arep.virtual.docker.persistencia.impl.ConetionDB;

/**
 * Clase Spark web server
 */
public class SparkWebServer
{
    /**
     * Inicializa el servidor web de spark y publica los mensajes
     * @param args ejecucion
     */
    public static void main( String[] args  ) {
        MensajePersistencia persistencia= new ConetionDB();
        port(getPort());
        get("/mensajes", (req,res) -> {
            res.status(200);
            res.type("application/json");//aqui
            return new Gson().toJson(persistencia.leerMensajes());
        });

        post("/mensajes",(request, response) -> {
            Mensaje a =new Mensaje(request.body());
            persistencia.escribirMensajes(a);
            return "";
        });
    }

    /**
     * retorna el puerto en uso
     * @return el puerto usado
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 6001;//aqui
    }
}
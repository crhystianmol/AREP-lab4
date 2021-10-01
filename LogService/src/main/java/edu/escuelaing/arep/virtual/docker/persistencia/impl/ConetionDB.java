package edu.escuelaing.arep.virtual.docker.persistencia.impl;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import edu.escuelaing.arep.virtual.docker.model.Mensaje;
import edu.escuelaing.arep.virtual.docker.persistencia.MensajePersistencia;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.DB;
import com.mongodb.DBCollection;

public class ConetionDB implements MensajePersistencia {
    public MongoClientURI uri;
    public MongoClient cliente;

    /**
     * Constructor de la instancia a la base de datos
     */
    public ConetionDB() {

        uri = new MongoClientURI("mongodb://crhystianmol:3124037984@cluster0-shard-00-00.4qrc6.mongodb.net:27017,cluster0-shard-00-01.4qrc6.mongodb.net:27017,cluster0-shard-00-02.4qrc6.mongodb.net:27017/myFirstDatabase?ssl=true&replicaSet=atlas-m3yjou-shard-0&authSource=admin&retryWrites=true&w=majority");//aqui
        cliente= new MongoClient(uri);
    }

    /**
     * Escribe los mensajes a la base de datos
     */
    @Override
    public void escribirMensajes(Mensaje mensaje) {
        cliente= new MongoClient(uri);
        MongoDatabase basedeDatos= cliente.getDatabase("LabArep4");
        MongoCollection<Document> collection =basedeDatos.getCollection("mensajes");
        Document document=new Document();
        document.put("mensaje",mensaje.getMensaje());
        collection.insertOne(document);
    }


    /**
     * Carga y lee todos los mensajes
     * @return arraylist de los mensajes
     */
    @Override
    public ArrayList<Mensaje> leerMensajes() {
        MongoDatabase database = cliente.getDatabase("LabArep4");
        MongoCollection<Document> collection =database.getCollection("mensajes");
        FindIterable fit = collection.find();
        ArrayList<Document> docs = new ArrayList<Document>();
        ArrayList<Mensaje> mensajes=new ArrayList<Mensaje>();
        fit.into(docs);
        for (Document document:docs) {
            String mensaje= (String) document.get("mensaje");
            mensajes.add(new Mensaje(mensaje));
        }

        return mensajes;
    }
}

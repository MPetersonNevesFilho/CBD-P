package com.letraA.cbd;

import static com.mongodb.client.model.Filters.*;

import java.util.Random;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;

import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCommandException;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.MongoDatabase;
public class CbdApplication {

    public static void main(String[] args) {
        // Ocultar logs de MongoDB
        ((LoggerContext) LoggerFactory.getILoggerFactory()).getLogger("org.mongodb.driver").setLevel(Level.ERROR);

		System.out.println("\nIniciando programa <<<<<<<<\n");




        // Altere a string de conexão para apontar para o sua DB
        String urlString = "mongodb://localhost:27017";

		// Verificação de tempo de execução
		System.out.println("\n>>>>>>>>>> Verificando tempo de execução");
        perfomanceTime(urlString);

		// Criação de Indexes
		System.out.println("\n>>>>>>>>>> Criando Indexes");
		//createIndex(urlString);

		// Mongo Find
		System.out.println("\n>>>>>>>>>> Mongo Find");
		mongoFind(urlString);




		System.out.println("\nFinalizando programa <<<<<<<<\n");
    }

    private static void perfomanceTime(String urlString){
        try (MongoClient mongoClient = MongoClients.create(urlString)) {
            MongoDatabase allDatabase = mongoClient.getDatabase("cbd");
            MongoCollection<Document> restaurants = allDatabase.getCollection("restaurants");

            long tInicial = System.currentTimeMillis();
            FindIterable<Document> encontrados = restaurants.find(eq("localidade", "Bronx"));
            long tFinal = System.currentTimeMillis();
            System.out.println("Tempo decorrido procurando pelas localidades Bronx: " + (tFinal - tInicial));

            tInicial = System.currentTimeMillis();
            encontrados = restaurants.find(eq("gastronomia", "Bakery"));
            tFinal = System.currentTimeMillis();
            System.out.println("Tempo decorrido procurando por gastronomias Bakery: " + (tFinal - tInicial));

            Bson filter = text("Shop");
            tInicial = System.currentTimeMillis();
            encontrados = restaurants.find(filter);
            tFinal = System.currentTimeMillis();
            System.out.println("Tempo decorrido procurando Shop: " + (tFinal - tInicial));

             for(Document doc : encontrados) {
				System.out.println(doc.toJson());
            }
        }
    }

    private static void mongoFind(String urlString) {
		try (MongoClient mongoClient = MongoClients.create(urlString)) {
			MongoDatabase allDatabase = mongoClient.getDatabase("cbd");
            MongoCollection<Document> restaurants = allDatabase.getCollection("restaurants");

            Bson projectionFields = Projections.fields(
				Projections.include("restaurant_id", "localidade"),
				Projections.excludeId()
				);

				FindIterable<Document> encontrados = restaurants.find(eq("gastronomia", "Seafood")).projection(projectionFields);
				System.out.println("\n\n");

				int docsNumber = 0;
				for(Document doc : encontrados) {
					docsNumber++;
                System.out.println(doc.toJson());
            }
			System.out.println("\n");
			System.out.println("Número de documentos encontrados: " + docsNumber);
		}
    }

	private static void createIndex(String urlString){
		try (MongoClient mongoClient = MongoClients.create(urlString)) {
			MongoDatabase allDatabase = mongoClient.getDatabase("cbd");
			MongoCollection<Document> restaurants = allDatabase.getCollection("restaurants");

			String resultCreateIndex = restaurants.createIndex(Indexes.ascending("localidade"));
			System.out.println("Index: " + resultCreateIndex);

			resultCreateIndex = restaurants.createIndex(Indexes.ascending("gastronomia"));
			System.out.println("Index: " + resultCreateIndex);

			try {
				resultCreateIndex = restaurants.createIndex(Indexes.text("nome"));
				System.out.print("Index: ");
				System.out.println(String.format(resultCreateIndex));
			} catch (MongoCommandException e) {
					System.out.println(e);
			}

		}
	}
}
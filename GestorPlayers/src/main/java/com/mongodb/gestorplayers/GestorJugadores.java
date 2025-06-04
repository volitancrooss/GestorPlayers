/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mongodb.gestorplayers;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.Scanner;

public class GestorJugadores {
    static final String DB = "equipoFutbol";
    static final String COLL = "jugadores";

    public static void main(String[] args) {
        
        MongoClient client = MongoClients.create();
        MongoDatabase db = client.getDatabase(DB);
        MongoCollection<Document> col = db.getCollection(COLL);
        Scanner sc = new Scanner(System.in);
        int opt;
        
        do {
            System.out.println("=== GESTION DE JUGADORES ===");
            System.out.println("1. Registrar nuevo jugador");
            System.out.println("2. Mostrar todos los jugadores");
            System.out.println("3. Actualizar datos de jugador");
            System.out.println("4. Eliminar jugador");
            System.out.println("5. Salir");
            System.out.print("Seleccione opción: ");
            opt = sc.nextInt(); sc.nextLine();
            switch(opt) {
                case 1:
                    
                    System.out.print("Nombre: ");
                    String n = sc.nextLine();
                    
                    System.out.print("Edad: ");
                    int e = sc.nextInt(); sc.nextLine()
                            ;
                    col.insertOne(new Document("nombre", n).append("edad", e));
                    System.out.println("Jugador registrado.\n");
                    break;
                case 2:
                    System.out.println("\n-- Jugadores: --");
                    
                    for (Document d : col.find())
                        System.out.println("Nombre: " + d.getString("nombre") + ", Edad: " + d.getInteger("edad"));
                    System.out.println();
                    
                    break;
                case 3:
                    System.out.print("Nombre a actualizar: ");
                    String name = sc.nextLine();
                    
                    System.out.print("Nueva edad: ");
                    int ne = sc.nextInt(); sc.nextLine();
                    
                    col.updateOne(new Document("nombre", name), new Document("$set", new Document("edad", ne)));
                    
                    System.out.println("Datos actualizados.\n");
                    
                    break;
                case 4:
                    System.out.print("Nombre a eliminar: ");
                    String eliminar = sc.nextLine();
                    
                    col.deleteOne(new Document("nombre", eliminar));
                    System.out.println("Jugador eliminado.\n");
                    
                    break;
                case 5:
                    System.out.println("Saliendo... ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion no valida.\n");
        
            
            }
        
        
        } while (opt != 5);
        sc.close();
        client.close();
    }
}

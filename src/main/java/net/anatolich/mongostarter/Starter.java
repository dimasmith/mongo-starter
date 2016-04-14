package net.anatolich.mongostarter;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.AggregateIterable;
import com.mongodb.Block;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.ascending;

import static java.util.Arrays.asList;

import org.bson.Document;

public class Starter {

  public static void main(String[] args) {
    final Starter starter = new Starter();
    starter.start();
  }

  public void start() {
    MongoClient client = new MongoClient();
    MongoDatabase db = client.getDatabase("test");

    Document filter = new Document("$match", new Document("borough", "Queens").append("cuisine", "Brazilian"));
    Document aggregate = new Document("$group", new Document("_id", "$address.zipcode").append("count", new Document("$sum", 1)));

    MongoIterable<Document> restaurants = db.getCollection("restaurants")
                      .aggregate(asList(filter, aggregate));

    ResultPrinter resultPrinter = new ResultPrinter();
    restaurants.forEach(resultPrinter);

    System.out.printf("Resutls found: %d", resultPrinter.getCount());
  }

}

class ResultPrinter implements Block<Document> {

  private int count = 0;

  @Override
  public void apply(final Document document) {
    count++;
    System.out.println(document.toJson());
  }

  public int getCount() {
    return count;
  }
}

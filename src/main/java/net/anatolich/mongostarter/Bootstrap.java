package net.anatolich.mongostarter;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import net.anatolich.mongostarter.dao.RestaurantDao;
import net.anatolich.mongostarter.dao.MongoRestaurantDao;

import static com.mongodb.client.model.Sorts.ascending;

public class Bootstrap {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("test");
        RestaurantDao hotelsDao = new MongoRestaurantDao(db);

        final Application application = new Application(hotelsDao);
        application.start();
    }
}


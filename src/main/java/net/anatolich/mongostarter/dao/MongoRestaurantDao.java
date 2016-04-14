package net.anatolich.mongostarter.dao;

import com.mongodb.client.MongoDatabase;

import net.anatolich.mongostarter.domain.Restaurant;

import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public final class MongoRestaurantDao implements RestaurantDao {

    private final MongoDatabase db;

    public MongoRestaurantDao(MongoDatabase db) {
        this.db = db;
    }

    @Override
    public Iterable<Restaurant> findAll() {
        return db.getCollection("restaurants").find().map(this::mapDocumentToRestaurant);
    }

    @Override
    public Iterable<Restaurant> findAllByBorough(String borough) {
        return db.getCollection("restaurants").find(eq("borough", borough)).map(this::mapDocumentToRestaurant);
    }

    private Restaurant mapDocumentToRestaurant(Document document) {
        return Restaurant.builder()
                .borough(document.getString("borough"))
                .cuisine(document.getString("cuisine"))
                .name(document.getString("name"))
                .restaurantId(Integer.valueOf(document.getString("restaurant_id")))
                .build();
    }
}

package net.anatolich.mongostarter.dao;

import net.anatolich.mongostarter.domain.Restaurant;

public interface RestaurantDao {
    Iterable<Restaurant> findAll();

    Iterable<Restaurant> findAllByBorough(String borough);
}

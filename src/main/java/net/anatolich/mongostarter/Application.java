package net.anatolich.mongostarter;

import net.anatolich.mongostarter.dao.RestaurantDao;
import net.anatolich.mongostarter.domain.Restaurant;

public class Application {

    private final RestaurantDao hotelsDao;

    public Application(RestaurantDao hotelsDao) {
        this.hotelsDao = hotelsDao;
    }

    public void start() {
        Iterable<Restaurant> restaurants = hotelsDao.findAllByBorough("Queens");
        restaurants.forEach(System.out::println);
    }
}

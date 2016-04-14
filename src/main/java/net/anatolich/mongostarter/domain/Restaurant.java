package net.anatolich.mongostarter.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Restaurant {

    private final String name;
    private final String borough;
    private final String cuisine;
    private final Integer restaurantId;
}

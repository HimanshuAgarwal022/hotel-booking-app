package org.cleartrip.project.models;

import java.util.Comparator;

public class SortRatingsByRatingAsc implements Comparator<Rating> {

    public int compare(Rating a, Rating b) {

        return a.getRating() - b.getRating();
    }
}

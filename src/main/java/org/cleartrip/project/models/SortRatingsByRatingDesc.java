package org.cleartrip.project.models;

import java.util.Comparator;

public class SortRatingsByRatingDesc implements Comparator<Rating> {

    public int compare(Rating a, Rating b) {

        return b.getRating() - a.getRating();
    }
}

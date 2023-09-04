package org.cleartrip.project.models;

import java.util.Comparator;

public class SortHotelsByAv implements Comparator<Hotel> {

    public int compare(Hotel a, Hotel b) {

        return (b.getAv() - a.getAv())>0 ?1:0;
    }
}

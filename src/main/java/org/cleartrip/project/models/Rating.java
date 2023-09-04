package org.cleartrip.project.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@AllArgsConstructor
@Setter
public class Rating {
  private final int hotelId;
  private final String userId;
  private int rating;
  private String desc; 
  private final long timestamp = System.currentTimeMillis() / 1000;

  public String toString(){
    return "Rating - HotelId: "+Integer.toString(hotelId)+" user: "+userId+" Rating: "+Integer.toString(rating)+" review: "+desc;
  }

}

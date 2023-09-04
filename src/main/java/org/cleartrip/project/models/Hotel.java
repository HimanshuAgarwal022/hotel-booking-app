package org.cleartrip.project.models;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Hotel {
  private final int id;
  private final String name;
  private final Map<String, Rating> ratings = new HashMap<>();
  private boolean isPlus = false;
  private double av=0.0;

  public Hotel(int id, String name, boolean isPlus){
    this.id = id;
    this.name = name;
    this.isPlus = isPlus;
  }

  public String toString(){
    return "Hotel - id: "+Integer.toString(id)+" name: "+name+" rating: "+Double.toString(av)+" Plus_Hotel: "+Boolean.toString(isPlus);
  }

}

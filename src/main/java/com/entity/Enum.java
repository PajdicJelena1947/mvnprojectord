

package com.entity;

/**
 * Enum klasa predstavlja enume za entitete Lekar i Fizioterapeut
 * @author jelena.pajdic
 */
public enum Enum {
    L(Values.L),F(Values.F),K(Values.K);
    private Enum (String val) {
     
     if (!this.name().equals(val))
        throw new IllegalArgumentException("Error");
  }

    
    public static class Values {
     public static final String L= "L";
     public static final String F= "F";
     public static final String K= "K";
  }
}

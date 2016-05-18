/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

/**
 * Enum klasa predstavlja enume za entitete Lekar i Fizioterapeut
 * @author jelena.pajdic
 */
public enum EnumRola {
    L(Values.L),F(Values.F),K(Values.K);
    private EnumRola (String val) {
     
     if (!this.name().equals(val))
        throw new IllegalArgumentException("Error");
  }

    
    public static class Values {
     public static final String L= "L";
     public static final String F= "F";
     public static final String K= "K";
  }
}

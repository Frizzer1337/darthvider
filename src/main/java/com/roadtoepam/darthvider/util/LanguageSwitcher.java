package com.roadtoepam.darthvider.util;

import java.util.Locale;

public class LanguageSwitcher {
	
	 private static final Locale EN = Locale.ENGLISH;
	 private static final Locale RU = new Locale("ru", "RU");

	 public static Locale change(Locale locale){
	      if(EN.equals(locale)){
	          return RU;
	      } else {
	          return EN;
	      }
	    }

}

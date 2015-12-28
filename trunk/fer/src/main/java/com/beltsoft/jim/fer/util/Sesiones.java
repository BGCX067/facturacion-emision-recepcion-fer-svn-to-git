package com.beltsoft.jim.fer.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class Sesiones {
	 
    private Sesiones(){}
    
    public static final Map<String, HttpSession> SESIONES_MAP = new HashMap<String, HttpSession>();
	
}

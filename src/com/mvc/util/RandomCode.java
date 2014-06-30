package com.mvc.util;

import java.util.Random;

import org.junit.Test;
import org.springframework.stereotype.Service;

@Service
public class RandomCode {
	public String random2() {
		String s = "";
		String tex="";
		Random rn = new Random();
		int n = rn.nextInt(99999);
		tex = "" + n;
		if (tex.length() == 5) {
			s = tex;
		} else {
			s = "23426";
		}
		return s;
	}
	
	@Test
	public void testRandom2(){
		String s = random2();
		System.out.println(s);
		
	}
}

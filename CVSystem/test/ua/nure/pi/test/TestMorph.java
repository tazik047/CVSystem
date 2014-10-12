package ua.nure.pi.test;

import java.io.IOException;

import org.apache.lucene.morphology.LuceneMorphology;
import org.apache.lucene.morphology.russian.RussianAnalyzer;
import org.apache.lucene.morphology.russian.RussianLuceneMorphology;


public class TestMorph {

	public static void main(String[] args) throws IOException {
		LuceneMorphology luceneMorph = new RussianLuceneMorphology();
	    for(String t : luceneMorph.getNormalForms("админы"))
	    	System.out.println(t);
	}
}

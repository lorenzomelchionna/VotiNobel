package it.polito.tdp.nobel.model;

import java.util.Set;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Model model = new Model();
		Set<Esame> Sottoinsieme = model.calcolaSottoinsiemeEsami(20);
		
		for(Esame e : Sottoinsieme)
			System.out.println(e.toString());
		
	}

}

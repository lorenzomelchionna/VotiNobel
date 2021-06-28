package it.polito.tdp.nobel.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.nobel.db.EsameDAO;

public class Model {
	
	private List<Esame> Partenza;
	private Set<Esame> SoluzioneMigliore;
	private double mediaSoluzioneMigliore;
	
	public Model() {
		
		EsameDAO dao = new EsameDAO();
		this.Partenza = dao.getTuttiEsami();
		
	}
	
	public Set<Esame> calcolaSottoinsiemeEsami(int numeroCrediti) {
		
		Set<Esame> Parziale = new HashSet<Esame>();
		SoluzioneMigliore = new HashSet<Esame>();
		mediaSoluzioneMigliore = 0;
		
		//cerca1(Parziale, 0, numeroCrediti);
		cerca2(Parziale, 0, numeroCrediti);
		
		return SoluzioneMigliore;
		
	}

	//Complessità N!
	private void cerca1(Set<Esame> Parziale, int l, int m) {
		
		int crediti = sommaCrediti(Parziale);
		
		if(crediti > m) {
			return;
		}
		
		if(crediti == m) {
			
			double media = calcolaMedia(Parziale);
			
			if(media > mediaSoluzioneMigliore) {
				
				SoluzioneMigliore = new HashSet<Esame>(Parziale);
				mediaSoluzioneMigliore = media;
				
			}
			
			return;
			
		}
		
		if(l == Partenza.size()) 
			return;
		
		/*for(Esame e : Partenza) {
			
			if(!Parziale.contains(e)) {
				
				Parziale.add(e);
				cerca1(Parziale, l+1, m);
				Parziale.remove(e);
				
			}
			
		}*/
		
		for(int i=l; i<Partenza.size(); i++) {
			
			if(!Parziale.contains(Partenza.get(i))) {
				
				Parziale.add(Partenza.get(i));
				cerca1(Parziale, l+1, m);
				Parziale.remove(Partenza.get(i));
				
			}
			
		}
		
	}
	
	//Complessità 2^N
	private void cerca2(Set<Esame> Parziale, int l, int m) {		
		
		int crediti = sommaCrediti(Parziale);
		
		if(crediti > m) {
			return;
		}
		
		if(crediti == m) {
			
			double media = calcolaMedia(Parziale);
			
			if(media > mediaSoluzioneMigliore) {
				
				SoluzioneMigliore = new HashSet<Esame>(Parziale);
				mediaSoluzioneMigliore = media;
				
			}
			
			return;
			
		}
		
		if(l == Partenza.size()) 
			return;
		 
		Parziale.add(Partenza.get(l));
		cerca2(Parziale, l+1, m);
		
		Parziale.remove(Partenza.get(l));
		cerca2(Parziale, l+1, m);
		
	}

	public double calcolaMedia(Set<Esame> esami) {
		
		int crediti = 0;
		int somma = 0;
		
		for(Esame e : esami){
			crediti += e.getCrediti();
			somma += (e.getVoto() * e.getCrediti());
		}
		
		
		return somma/crediti;
		
	}
	
	public int sommaCrediti(Set<Esame> esami) {
		
		int somma = 0;
		
		for(Esame e : esami)
			somma += e.getCrediti();
		
		return somma;
		
	}

}

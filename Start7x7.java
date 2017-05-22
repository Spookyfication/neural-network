
public class Start7x7 {

	static int anzahlVersuche = 0;
	static int anzahlEingangsneuronen = 49;
	static int anzahlAusgangsneuronen = 12;
	static NeuronalesNetz nn = new NeuronalesNetz(anzahlEingangsneuronen, anzahlAusgangsneuronen);
	static int zufallszahl;
	static double[] eingangsNeuronen = new double[anzahlEingangsneuronen];
	static double[] ausgangsNeuronen = new double[anzahlAusgangsneuronen];
	static double[] soll;
	
	public static void main(String[] args) {		
		lerneSystemAn(1000);
		fehlerquoteErmitteln(1000);		
	}
	
	/*---------------------------------------------------------------
		Liefert eine binäre 7x7 Anzeige
	----------------------------------------------------------------*/
	private static double[] get7x7(int zufallszahl){
		double[][] m =	{ {	0,0,1,1,1,0,0,	//0
							0,1,0,0,0,1,0,
							0,1,0,0,1,1,0,
							0,1,0,1,0,1,0,
							0,1,1,0,0,1,0,
							0,1,0,0,0,1,0,
							0,0,1,1,1,0,0},
				
						  {	0,0,0,1,0,0,0,	//1
							0,0,1,1,0,0,0,
							0,1,0,1,0,0,0,
							0,0,0,1,0,0,0,
							0,0,0,1,0,0,0,
							0,0,0,1,0,0,0,
							0,1,1,1,1,1,0},
						  
						  {	0,0,1,1,1,0,0,	//2
							0,1,0,0,0,1,0,
							0,0,0,0,0,1,0,
							0,0,0,1,1,0,0,
							0,0,1,0,0,0,0,
							0,1,0,0,0,0,0,
							0,1,1,1,1,1,0},
						  
						  {	0,0,1,1,1,0,0,	//3
							0,1,0,0,0,1,0,
							0,0,0,0,0,1,0,
							0,0,1,1,1,0,0,
							0,0,0,0,0,1,0,
							0,1,0,0,0,1,0,
							0,0,1,1,1,0,0},
						  
						  {	0,0,0,0,1,0,0,	//4
							0,0,0,1,1,0,0,
							0,0,1,0,1,0,0,
							0,1,0,0,1,0,0,
							0,1,1,1,1,1,0,
							0,0,0,0,1,0,0,
							0,0,0,0,1,0,0},
						  
						  {	0,1,1,1,1,1,0,	//5
							0,1,0,0,0,0,0,
							0,1,1,1,0,0,0,
							0,0,0,0,0,1,0,
							0,0,0,0,0,1,0,
							0,1,0,0,0,1,0,
							0,0,1,1,1,0,0},
						  
						  {	0,0,1,1,1,0,0,	//6
							0,1,0,0,0,1,0,
							0,1,0,0,0,0,0,
							0,1,1,1,1,0,0,
							0,1,0,0,0,1,0,
							0,1,0,0,0,1,0,
							0,0,1,1,1,0,0},
						  
						  {	0,1,1,1,1,1,0,	//7
							0,0,0,0,0,1,0,
							0,0,0,0,1,0,0,
							0,0,0,1,0,0,0,
							0,0,0,1,0,0,0,
							0,0,0,1,0,0,0,
							0,0,0,1,0,0,0},
						  
						  {	0,0,1,1,1,0,0,	//8
							0,1,0,0,0,1,0,
							0,1,0,0,0,1,0,
							0,0,1,1,1,0,0,
							0,1,0,0,0,1,0,
							0,1,0,0,0,1,0,
							0,0,1,1,1,0,0},
						  
						  {	0,0,1,1,1,0,0,	//9
							0,1,0,0,0,1,0,
							0,1,0,0,0,1,0,
							0,0,1,1,1,1,0,
							0,0,0,0,0,1,0,
							0,1,0,0,0,1,0,
							0,0,1,1,1,0,0},
						  
						  {	0,0,0,1,0,0,0,	//A
							0,0,1,0,1,0,0,
							0,0,1,0,1,0,0,
							0,1,0,0,0,1,0,
							0,1,1,1,1,1,0,
							0,1,0,0,0,1,0,
							0,1,0,0,0,1,0},
						  
						  {	0,1,1,1,1,0,0,	//B
							0,1,0,0,0,1,0,
							0,1,0,0,0,1,0,
							0,1,1,1,1,0,0,
							0,1,0,0,0,1,0,
							0,1,0,0,0,1,0,
							0,1,1,1,1,0,0}};
		
		int zähler = 1;
		for(int i = 0; i < anzahlEingangsneuronen; i++){
			 if(m[zufallszahl][i] == 1){
				 //System.out.print("■ ");
			 }else{
				 //System.out.print("□ ");
			 }
			 
			
			 if(zähler == 7){
				 zähler = 0;
				 //System.out.print("\n");
			 }
			 zähler++;
		 }
		return m[zufallszahl];
	}
	
	
	/*---------------------------------------------------------------
		kontrolliertes Lernen mit x Schritten
	----------------------------------------------------------------*/
	public static void lerneSystemAn(int anzahlIterationen){
		long start, ende;
		start = System.currentTimeMillis();
		//1000 Iterationen für zuverlässiges Anlernen
		for(int i = 0; i < anzahlIterationen; i++){
			//einstellige Zufallszahl erzeugen
			zufallszahl=(int)(Math.random()*anzahlAusgangsneuronen);
			
			//Array anlegen, der an der Stelle der Zufallszahl 1 ist
			soll = new double[anzahlAusgangsneuronen];
			soll[zufallszahl] = 1;
			
			//Jedes Eingangsneuron steht für ein Segment der 7 Segment Anzeige
			eingangsNeuronen = get7x7(zufallszahl);
			
			//Ausgangsneuronen durch Musteranalyse bestimmen
			ausgangsNeuronen = nn.analysiereMuster(eingangsNeuronen);
			
			//System mit Delta Methode kontrolliert lernen lassen
			nn.lerne(eingangsNeuronen, ausgangsNeuronen, soll);
			
			anzahlVersuche++;
			
			//Ausgangsneuronen interpretieren und für Mensch lesbar darstellen
			untersucheErgebnis(ausgangsNeuronen, zufallszahl);
		}
		ende = System.currentTimeMillis();
		System.out.println("System angelernt mit " + anzahlIterationen + " Iterationen ("+(ende-start)/1000.0+" sek)");
	}
	
	
	/*--------------------------------------------------------------- 
		Interpretiert die Ausgabeneuronen und gibt diese aus 
	----------------------------------------------------------------*/
	private static int untersucheErgebnis(double[] ausgangsNeuronen, int zufallszahl){
		double größterWert = 0;
		int ergebnisZahl = 0;
		for( int j=0; j<anzahlAusgangsneuronen; j++ ){
			if (ausgangsNeuronen[j] > größterWert){
				größterWert = ausgangsNeuronen[j];
				ergebnisZahl = j;
			}
		}
		//System.out.println(anzahlVersuche + " - Gesucht: " + zufallszahl + " Ergebnis: "+ergebnisZahl);
		return ergebnisZahl;
	}
	
	
	/*---------------------------------------------------------------
		berechnet ohne zu lernen die aktuelle Fehlerquote 
	----------------------------------------------------------------*/
	private static void fehlerquoteErmitteln(int anzahlIterationen){
		int treffer = 0;
		
		//Iterationen um Durchschnitt zu ermitteln
		for(int i = 0; i < anzahlIterationen; i++){
			//einstellige Zufallszahl erzeugen
			zufallszahl=(int)(Math.random()*anzahlAusgangsneuronen);
			
			//Array anlegen, der an der Stelle der Zufallszahl 1 ist
			soll = new double[anzahlAusgangsneuronen];
			soll[zufallszahl] = 1;
			
			//Jedes Eingangsneuron steht für ein Segment der 7 Segment Anzeige
			eingangsNeuronen = get7x7(zufallszahl);
			
			//Ausgangsneuronen durch Musteranalyse bestimmen
			ausgangsNeuronen = nn.analysiereMuster(eingangsNeuronen);
			
			int ergebnis = untersucheErgebnis(ausgangsNeuronen, zufallszahl);
			if(ergebnis == zufallszahl){
				treffer++;
			}
		}
		
		System.out.println("Trefferquote " + (treffer*100.0)/anzahlIterationen + "%");
	}
}

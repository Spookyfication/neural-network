import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.text.*;

public class NeuronalesNetz
{
	private int anzahlEingangsneuronen = 0;
	private int anzahlAusgangsneuronen = 0;
	private double[]   zustand;
	private double[][] gewicht;
	private double[]   ausgangsWert;
	private double[]   eingangsWert;

	/*---------------------------------------------------------------
		Gewichtsmatrix initialisieren
	----------------------------------------------------------------*/
	public NeuronalesNetz(int anzahlEingangsneuronen, int anzahlAusgangsneuronen) {
		this.anzahlEingangsneuronen = anzahlEingangsneuronen;
		this.anzahlAusgangsneuronen = anzahlAusgangsneuronen;
		
		zustand = new double[anzahlAusgangsneuronen];
		gewicht = new double[anzahlAusgangsneuronen][anzahlEingangsneuronen];
		ausgangsWert = new double[anzahlAusgangsneuronen];
		eingangsWert = new double[anzahlEingangsneuronen];
		
		// Gewichtmatrix mit Zufallswerten füllen
		for( int i=0; i<anzahlAusgangsneuronen; i++ ){
			for( int j=0; j<anzahlEingangsneuronen; j++ ){
				gewicht[i][j] = Math.random()*.2+.1;
			}
		}
	}
	
	/*---------------------------------------------------------------
		Delta-Lernregel
	----------------------------------------------------------------*/
	public void lerne( double[] ein, double[] aus, double[] soll )
	{		
		// Lernrate
		double lern = .2;
		
		for( int i=0; i<anzahlEingangsneuronen; i++ ){
			for( int j=0; j<anzahlAusgangsneuronen; j++ ){
				gewicht[j][i] += lern*ein[i]*(soll[j]-aus[j]);
			}
		}
	}
	
	/*---------------------------------------------------------------
		Muster analysieren
	----------------------------------------------------------------*/
	public double[] analysiereMuster(double[] muster){
		//Ausgangsneuronen
		double[] erg = new double[anzahlAusgangsneuronen];
		
		// minimaler Wert der Zustandsfunktion
		double min = 0;
		
		// maximaler Wert der Zustandsfunktion
		double max = 1;	
		
		// Skalierungsfaktor
		double s   = 0.2;
		
		// Abklingkonstante
		double d   = 0.5;	
		
		// Ruhewert der Aktivität
		double c0  = 0.5;
		
		// Schwelle
		double theta = 1;
		
		// Steigung
		double sigma = 1;				
		
		//Gewichtmatrix mit Eingangsneuronen als Vektor multiplizieren
		for( int i=0; i<anzahlAusgangsneuronen; i++ )
		{
			for( int j=0; j<anzahlEingangsneuronen; j++ )
			{
				erg[i]+=gewicht[i][j]*muster[j];
			}
		}
		
		
		//DMA Aktivierungsfunktion
		for( int i=0; i<anzahlAusgangsneuronen; i++ ){
			if( erg[i]<0 ){
				zustand[i] += s*erg[i]*(zustand[i]-min)-d*(zustand[i]-c0);
			}else{
				zustand[i] += s*erg[i]*(max-zustand[i])-d*(zustand[i]-c0);
			}
		}
		
		erg = zustand;
		
		//Sigmoid-Funktion
		for( int i=0; i<anzahlAusgangsneuronen; i++ ){
			erg[i] = min+(max-min)/(1+Math.exp(-4*sigma*(erg[i]-theta)/(max-min)));
		}
		
		return erg;
	}
	
	/*---------------------------------------------------------------
		Gewichtsmatrix ausdrucken
	----------------------------------------------------------------*/
	public void druckeGewichte(){
		for(int i = 0; i < anzahlAusgangsneuronen; i++){
			for(int j = 0; j < anzahlEingangsneuronen; j++){
				System.out.print((Math.round(gewicht[i][j]*10)/10.0)+" ");
			}
			System.out.print("\n");
		}
	}
}
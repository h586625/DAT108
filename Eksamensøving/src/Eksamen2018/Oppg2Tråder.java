package Eksamen2018;

public class Oppg2Tråder {
	//Oppgave a
	//Lag to trådobjekt a og b, i Java og start dem. 
	//Begge skal være av typen Inkrementer.
	/*Klassene er gitt i oppgaven.*/
	
	Tall tall= newTall(0); // deltressurs
	Thread a= newThread(newInkrementer(tall));
	Thread b= newThread(newInkrementer(tall));
	a.start();
	b.start();
	b.join();
	a.join();
}

//b
//De to objektene deler en ressurs som er et tall-objekt av typen Tall.
//Referanse til denne er gitt som parameter i konstruktøren. 
//I metoden run()inkrementerer dedenne ressursen. 
//Forklar hva vi må gjøre for at de skal bli trådsikre?
//Husk at de kjører samtidig og vil kunne inkrementere tallet samtidig. 
//Foreslå endringer av programkodenfor at tallet blir inkrementert riktig.

	public synchronized int inkrement() {
		verdi++;
		returnverdi;
		}
	//Svar: Her er det mange muligheter, men den enkleste og kanskje beste er å gjøre metoden inkrement synkronisert 
	//(eller kallet av metoden, men jo mer begrenset, jo bedre er det). 
	//Et annet enkelt alternativ kan være å benytte AtomicInteger for variabelen verdi i Tall. 
	//Man kan også benytte semaforer og mutex, men det gir en unødvendig kompleks løsning. 
	//Å synkronisere run eller utenfor while-løkken i run, er en dårlig løsning.

	//c
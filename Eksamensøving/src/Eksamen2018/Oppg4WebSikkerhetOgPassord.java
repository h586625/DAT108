package Eksamen2018;

public class Oppg4WebSikkerhetOgPassord {

	//a) Hvordan kan/bør vi hjelpe brukere med å velge trygge passord? Hva regnes som et trygt
	//		passord?
	
	//Svar: 
	//være så langt som mulig, minst 5 ord eller 16 tegn ( Dette vil jeg anse som den viktigste delen av å lage et passord.)Setninger er en god måte å gjøre alle kravene under på
	//være unikt for hver enkelt nettside/brukerkonto
	//inneholde både tall, symboler, mellomrom og store/små bokstaver
	//helst ikke inkludere ord/tall som kan assosieres med deg eller tjenesten passordet gjelder for.
	
	
	
	//b) Beskriv i detalj hvordan passord bør lagres for å sikre dem mot cracking.
	//Hashing og salting 
	
	//Svar:
	//For å sikre passordet mot cracking bør det hashes+saltes (ekstra sikkerhet) i applikasjonskoden, før det lagres i databasen. 
	//Da unngår at vi at passordet er tilgjengelig som klartekst. 
	//Hashing algoritmen gjør at et passord blir til en enormt lang streng av tall og bokstaver som blir lagret i databasen i stedet for at det opprinnelige passordet blir lagret i databasen. 
	//Salting er en tilfeldig lang streng som blir lagt til i den hasingen som en ekstra sikkerhetsmekanisme for at det skal bli enda vanskeligere for å dekryptere hasingen.
}

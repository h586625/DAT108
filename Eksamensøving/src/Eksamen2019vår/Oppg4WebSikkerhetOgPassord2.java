package Eksamen2019vår;

public class Oppg4WebSikkerhetOgPassord2 {
	//Passord er viktige og sensitive opplysninger. Som utviklere har vi ansvar for å hjelpe brukere med å
	//velge trygge passord, og vi har ansvar for å overføre og lagre passord på en trygg måte.
	
	
	//a) Hvordan kan/bør vi hjelpe brukere med å velge trygge passord? Hva regnes som et trygt
		//passord?
	
		/*Momenter som bør være med i løsning:
			- Vi kan hjelpe bruker ved å opplyse om minimumskrav
			- Vi kan hjelpe bruker ved å veilede om gode og dårlige passord
			- Vi kan hjelpe bruker ved å gi tilbakemelding om passordstyrke
			- Det kan være kontraproduktivt å ha for strenge regler om hvilke tegn
			passordet må inneholde eller be brukeren om å bytte for ofte. Dette
			fører til at brukeren velger enklere passord, evt. ikke kan huske
			passordet, og må skrive det ned.
			- Trygge passord er passord som vanskelig lar seg gjette eller cracke
			med kjente cracking-teknikker.
			 - Passordlengde er det aller viktigste. Beskytter mot brute-force.
			 Et passord bør være minst 14 tegn.
			 - Å unngå passord som er kjente ord (fra ordliste) eller mye
			 brukte passord (fra lister over kjente passord) er også viktig.
			 Man bør velge et passord som "ingen" andre har valgt eller
			 klarer å gjette, f.eks. "DAT108 er superdupert". Unngå personlig
			 informasjon i passord.
			 - Det å kreve kombinasjoner av spesialtegn og store og små
			 bokstaver er ikke nødvendig om de andre reglene følges.
			NIST har endret sine anbefalinger ganske nylig, og det er helt greit om
			svaret avviker litt fra det som står over.*/
	
	
	//b) Beskriv i detalj hvordan passord bør lagres for å sikre dem mot cracking.
	
		//Løsning:
		/*Momenter som bør være med i løsning:
		- Hashing med en kryptografisk hashfunksjon (som f.eks. SHA256)
		- Tilfeldig Salt som legges til passordet FØR hashing
		- Key streching / iterering
		(Hash + Salt) lagres i passorddatabasen.
		Det er et pluss om man forteller hvorfor man gjør de ulike tingene.*/
}

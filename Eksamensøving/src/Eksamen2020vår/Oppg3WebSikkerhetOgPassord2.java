package Eksamen2020vår;

public class Oppg3WebSikkerhetOgPassord2 {
//a) Forklar hva du forstår med "untrusted data" i en web applikasjon?

	//Løsning: 
			/*Untrusted data is any user supplied data to a web application. Such data can come from
			web parameters and cookies, data from files, data from databases, data from web services,
			environment variables, and open ports.*/
			
//b) Gi to eksempler på sikkerhetsproblemer som kan være forårsaket av "untrusted data".

	//Løsning: SQL injection, Command injection, XSS, CSRF, (See OWASP Top10)
			
/*c) Se på kodesnutten nedenfor (som genererer en passordhash) til lagring av passord. Hvilke
			svakheter kan du se med å bruke denne koden til å lagre passord?*/
			/*public String generatePasswordHash (final String password)
			 throws NoSuchAlgorithmException {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] passbytes = password.getBytes();
			md.update(passbytes); // pass the password to the hash function
			byte[] passhash = md.digest(); // obtain the hash value of the password
			String hexOfHash = DatatypeConverter.printHexBinary(passhash); // hex value
			return hexOfHash;
			}*/
	//Løsning: 
			
			/*The code is missing per user random salt and a slow hash function to make it more secure in
			the following ways:
			Random salt will make two similar passwords different – difficulty level is increased for the attacker
			Random salt will increase the security of weak password (length and randomness)
			Random salt will increase the length of the rainbow table (precomputed hash table)
			Slow hash algorithms increases the length of time it takes to hack a given password. It's about
			causing an attacker to take a long time to generate a hash password.*/
			
//d) Forklar hvordan du kan forbedre koden for å forbedre passordssikkerheten.
			
	//Løsning: 
			//Can use per user random salt and a slow hash function (e.g. PBKDF):
			public String generateHashWithSaltAndIteration(final String password, byte[] salt, int keylength, int iteration) throws
			NoSuchAlgorithmException, InvalidKeySpecException {
				char[] passchar = password.toCharArray();
				PBEKeySpec pks = new PBEKeySpec(passchar, salt, iteration, keylength);
				SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
				byte[] keyhash = skf.generateSecret(pks).getEncoded();
				return DatatypeConverter.printHexBinary(keyhash);
			}
//e) Beskriv de forskjellige angrepsmetodene mot passord.
			
	//Løsning:
			/*1) Brute force attack: Trying out all possible character combinations to find passwords in the
			password database.
			
			2) Dictionary attack: Use a pre-compiled list that may contain hundreds of thousands of
			dictionary words and in some cases passwords that have been used in the past. Try each
			dictionary word for their existence in the password database
			
			3) Rainbow attack: Pre-compute the hashes of the passwords in a password dictionary using
			different hash algorithms and store them. Can then look up the password that belongs to
			different hash values.*/
}

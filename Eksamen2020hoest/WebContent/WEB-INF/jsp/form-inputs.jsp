<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Sidetittel</title>
</head>
<body>
	<h1>Sidetittel</h1>

    <div id="root">
		<form method="post" class="form">
	        <fieldset>
	          <div class="input">
		          <label for="tittel">Fornavn:</label>
		          <input type="text" name="tittel" id="tittel" value="${skjema.tittel}" required />
		          <span class="melding">${skjema.fornavnFeil}</span>
			  </div>
	
			  <div class="input">
		          <label for="passord">Passord:</label> 
		          <input type="password" name="passord" id="passord" value="${skjema.passord}" required />
		          <span class="melding">${skjema.passordFeil}</span>
	          </div>
	
			  <div class="input">
		          <span class="columnfirst">Kjønn:</span>
		          <span data-kjonn>
		            <label><input type="radio" name="kjonn" value="mann" ${skjema.kjonn eq "mann" ? "checked=\"checked\"" : ""} /> mann</label>
		            <label><input type="radio" name="kjonn" value="kvinne" ${skjema.kjonn eq "kvinne" ? "checked=\"checked\"" : ""} />kvinne</label>
		          </span>
		          <span class="melding">${skjema.kjonnFeil}</span>
	          </div>
	          
	          <input type="checkbox" id="yes" name="yes" ${skjema.checkbox eq "yes" ? "checked=\"checked\"" : ""}>
	          <input type="checkbox" id="no" name="no">
	          
	          <input type="hidden" name="min_id" value="${min.id}" />
	
			  <c:forEach var="item" items="${items}" varStatus="loop">
			  	  <input type="radio" name="item" value="${loop.index}" />
			  	  ${frukt}<br />
			  </c:forEach>
			
			  <c:forEach var="i" begin="1" end="5">
				  <input type="radio" name="rating" value="${6-i}" ${i eq 3 ? "checked" : ""} />
				  <img src="bilder/rating${6-i}.png"><br/>
			  </c:forEach>
	
			  <textarea name="tekst" rows="5" cols="40"></textarea>
	
	          <button type="submit">Meld meg på</button>
	        </fieldset>
	      </form>
	  </div>
</body>
</html>

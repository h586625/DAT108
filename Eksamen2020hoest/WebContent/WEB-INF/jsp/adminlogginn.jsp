<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Admin logg inn</title>
</head>
<body>
	<h1>Admin logg inn</h1>
	
	<p class="error"><c:out value="${login.feil}" /></p>
	
    <form method="post" class="form">
        <fieldset>
          <div class="input">
	          <label for="tittel">Adminpassord:</label>
	          <input type="passord" name=""passord"" id=""passord"" value="" required />
	      </div>
	      <button type="submit">Logg inn som admin</button>
	    </fieldset>
	  </form>
</body>
</html>

<!DOCTYPE html>
<html lang="fr">

<head>
	<meta charset="utf-8">
	<title>Connexion</title>

	<!-- Google Fonts -->
	<link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900' rel='stylesheet' type='text/css'>

	<link rel="stylesheet" href="css/animate.css">
	<!-- Custom Stylesheet -->
	<link rel="stylesheet" href="css/style.css">
	

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
</head>

<body>
	<div class="container">
		<div class="top">
			<h1 id="title" class="hidden"><span id="logo">Recherche <span>RT</span></span></h1>
		</div>
		<div class="login-box animated fadeInUp">
			<div class="box-header">
				<h2>Se connecter</h2>
			</div>
			<form method="post" action="<c:url value="/connexion" />">
				<c:if test="${empty sessionScope.sessionUtilisateur && !empty requestScope.intervalleConnexions }">
					<p class="info">(Vous ne vous etes pas connecté(e) depuis ce navigateur depuis ${requestScope.intervalleConnexions})</p>
				</c:if>
				<label for="email">Adresse email</label>
				<br/>
				<input type="email" id="email" name="email" value="<c:out value="${utilisateur.email }"/>">
				<br/>
				<span class="erreur">${form.erreurs['email']}</span>
				<br/>
				<label for="motdepasse">Mot de passe</label>
				<br/>
				<input type="password" id="motdepasse" name="motdepasse" value="">
				<br/>
				<span class="erreur">${form.erreurs['motdepasse']}</span>
				<br/>
				<label for="memoire"> Se souvenir de moi</label>
				<input type="checkbox" id="meoire" name="memoire">
				<br/>
				<button type="submit" >Connexion</button>
				<br/>
				<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
				<c:if test="${!empty sessionScope.sessionUtilisateur }">
				<p class="succes"> Vous etes connecté(e) avec l'adresse: ${sessionScope.sessionUtilisateur.email }</p></c:if>
			</form>
			<a href="#"><p class="small">Forgot your password?</p></a>
		</div>
	</div>
</body>

<script>
	$(document).ready(function () {
    	$('#logo').addClass('animated fadeInDown');
    	$("input:text:visible:first").focus();
	});
	$('#username').focus(function() {
		$('label[for="username"]').addClass('selected');
	});
	$('#username').blur(function() {
		$('label[for="username"]').removeClass('selected');
	});
	$('#password').focus(function() {
		$('label[for="password"]').addClass('selected');
	});
	$('#password').blur(function() {
		$('label[for="password"]').removeClass('selected');
	});
</script>

</html>
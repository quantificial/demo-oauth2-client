<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Demo</title>
<meta name="description" content="" />
<meta name="viewport" content="width=device-width" />

<base href="/" />

<link rel="stylesheet" type="text/css" href="/webjars/bootstrap/css/bootstrap.min.css" />
<script type="text/javascript" src="/webjars/jquery/jquery.min.js"></script>
<script type="text/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<style>

	 @font-face {
	     font-family: 'Lora-Regular';
	     src: url(fonts/Lora-Regular.woff2) format('woff2');
	 }
	 @font-face {
	     font-family: 'Lora-RegularItalic';
	     src: url(fonts/Lora-RegularItalic.woff2) format('woff2');
	 }
	 
	@import url(http://fonts.googleapis.com/css?family=Oswald:400,300,700);
	@import url(http://fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300ita‌​lic,400italic,500,500italic,700,700italic,900italic,900);
	@import url(http://fonts.googleapis.com/css?family=Lato:400,100,100italic,300,300ita‌​lic,400italic,500,500italic,700,700italic,900italic,900);
	@import url(http://fonts.googleapis.com/css?family=Lato:400,300,700);
	@import url(http://fonts.googleapis.com/css?family=PT+Sans:400,100,100italic,300,300ita‌​lic,400italic,500,500italic,700,700italic,900italic,900);
	@import url(http://fonts.googleapis.com/css?family=Lora:400,100,100italic,300,300ita‌​lic,400italic,500,500italic,700,700italic,900italic,900);

	body {
		font-family: 'Lora-Regular','Lato','Roboto', 'Oswald', sans-serif !important;	
	}
	
</style>
</head>
<body>
	<nav class="navbar navbar-inverse">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span> 
	      </button>
	      <a class="navbar-brand" href="#">SSO Demo</a>
	    </div>
	    <div class="collapse navbar-collapse" id="myNavbar">
	      <ul class="nav navbar-nav">
	        <li class="active"><a href="#">Home</a></li>
	        <li><a href="#">information</a></li>
	        <li><a href="#">site map</a></li> 
	         
	      </ul>
	      <#if !model["username"]?has_content>
	      <ul class="nav navbar-nav navbar-right">
	        <!--<li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>-->
	        <li><a href="/login/sso"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
	      </ul>
	      </#if>
	    </div>
	  </div>
	</nav>

<div class="container">	
	<div class="jumbotron">
	  <h1 class="display-4">Landing Page</h1>
	  <h2 class="display-4">SSO Application Client Demo</h2>
	  <p class="lead">This is repsonsive website, This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.</p>
	  <hr class="my-4">
	  <p>It uses utility classes for typography and spacing to space content out within the larger container.</p>
	  
 
	  
	  <hr class="my-4">
	  	 	  
	  											
			<#if model["username"]?has_content>
			
	   	    <div class="container authenticated">
	    	<div>
	    		<p>
	        		<span class="badge badge-pill badge-success">Success</span> Logged in as: ${model["username"]}</span>
	        	</p>
	        </div>
	        
	        <div>
	        	Your authorities are...
	        	
	        	<#list model["authorities"] as item>
	        		${item} 
	        	</#list>
	        </div>
	        
	        <div>
		        <p>....</p>	        
		        <p>${model["secret"]}</p>
		       	<p>....</p>    
		       	<p>Policy Information Retrieved from Remote Resources:</p> 
		       	<p>${model["policies"]}</p>
		       	
		       			       	  		       	  
		        
		        <p>...</p>
	        </div>
	        
            <!-- <button onClick="logout()" class="btn btn-primary">Logout</button> -->
            <p>
            	<a href="/logout"><button class="btn btn-primary btn-md" >Logout</button></a>
            </p>
	        </#if>
	        
	        
	    </div>
    
	</div>
</div>	

</body>
</html>
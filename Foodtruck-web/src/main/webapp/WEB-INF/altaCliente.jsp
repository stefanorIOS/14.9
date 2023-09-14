<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/reset.css">
    <link rel="stylesheet" href="style/header.css">
    <link rel="stylesheet" href="style/altaCliente.css">
    
<title>Foodtruck</title>
</head>
<body>
<jsp:include page="header.jsp"/>
	<div class="form">
        <form action="altacliente" method="post">
            <div class="form__container">
                <h1>Nuevo Cliente</h1>
                <div class="form__item">
                    <label>DNI</label>
                    <input name="dni" maxlength="8" minlength="8">
                </div>
                <div class="form__item">
                    <label>Nombre</label>
                    <input name="nombre">
                </div>
            
                <div class="form__item">
                    <label>Direcci�n</label>
                    <input name="direccion">
                </div>
                <button class="button" type="submit">Agregar</button>	
            </div>      
        </form>
    </div>
</body>
</html>
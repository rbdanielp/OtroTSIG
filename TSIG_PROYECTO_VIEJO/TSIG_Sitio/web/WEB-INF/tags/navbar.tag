<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <a class="navbar-brand" style="color: #ffffff">OBDULIO 2030</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTSIG" aria-controls="navbarTSIG" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarTSIG">
        <ul class="navbar-nav mr-auto">

            <c:choose>
                <c:when test="${empty nickUsr}">
                    <li class="nav-item">
                        <a class="nav-link" href="IniciarSesion">Iniciar Sesion</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="RegistrarCliente">Registrarse</a>
                    </li>
                </c:when>    
                <c:otherwise>
                    <li class="nav-item active">
                        <a class="nav-link" href="/Home">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item"><a class="nav-link " href="#"> <c:out value="${nickUsr}"/></a></li>
                    <li class="nav-item"><a class="nav-link " href="/CerrarSesion">Salir</a></li>
                    </c:otherwise>
                </c:choose>
            <!--Opciones para Administrador-->
            <c:choose>
                <c:when test="${not empty admin and admin}">
                    <li class="nav-item"><a class="nav-link" href="/registrarPunto">Registro de Punto de Interes</a></li>
                    <li class="nav-item"><a class="nav-link" href="/IngresarPromocion">Ingresar Promoción</a>
                    <li class="nav-item"><a class="nav-link" href="/RegistrarComentario">Registrar Comentario</a>
                    <li><a class="nav-link " href="/RegInfoTorneos">Datos Torneo</a></li>
                    </c:when>
                </c:choose>
        </ul>
    </div>
</nav>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>

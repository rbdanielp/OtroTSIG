<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


        <title>OBDULIO 2030</title>
        <meta charset="utf-8"><link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"
                integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
        <link rel="stylesheet" href="CSSs/newcss.css" type="text/css">
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@taglib prefix="ct" uri="/WEB-INF/tlds/tag_tsig.tld" %>


    </head>
    <body background="images/background.jpg"  background-size="100% 100%;"   >
        <!--Inicio Barra de navegacion-->
        <ct:navbar></ct:navbar>
        <!--Fin Barra de navegacion-->
        <!--<button  id="elbo" onclick='<script src="./WFS.js"></script>'>Texto de boton</button>-->
        <div class="container-fluid">


            <h1 style="text-align: center" class = "badge-info">Bienvenido </h1>
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <div id="div1" class="div1">                            
                            <embed src="componenteMapa.html" style="position: absolute; width:100%; height:100%; background-color: white" >                       
                        </div>
                        <div id="div2" class="div2">
                            <h3 style="text-align: center" class= "badge-info">Resultados Últimos partidos de <c:out value="${equipoUsr}"/></h3>

                            <table class="table table-striped table-dark">
                                <thead class="thead-dark">
                                    <tr>
                                        <th scope="col">Local</th>
                                        <th scope="col">Resultado</th>
                                        <th scope="col">Visitante</th>
                                        <th scope="col">Fecha</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${ultimosPartidos}" var="partido">
                                        <tr>
                                            <td><c:out value="${partido.getEquipo_1().getNombrePais()}"/></td>
                                            <td><c:out value="${partido.getGoles_1()} - ${partido.getGoles_2()}"/></td>
                                            <td><c:out value="${partido.getEquipo_2().getNombrePais()}"/></td>
                                            <td><c:out value="${partido.getStrFecha()}"/></td>
                                        </tr>

                                    </c:forEach>
                                </tbody>
                            </table>
                            <!----------Inicio Tabla Posiciones--------->
                            <h3 style="text-align: center" class= "badge-info">Tabla de Posiciones:</h3>
                            <table class="table table-dark">
                                <thead>
                                  <tr>
                                    <th>Pais</th>
                                    <th>PTS</th>
                                    <th>PJ</th>
                                    <th>PG</th>
                                    <th>PE</th>
                                    <th>PP</th>
                                    <th>GF</th>
                                    <th>GC</th>
                                    <th>DF</th>
                                  </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${tablaGrupo}" var="equipoE">
                                        <tr>
                                            <td><c:out value="${equipoE.getEquipo().getNombrePais()}"/></td>
                                            <td><c:out value="${equipoE.getPts()}"/></td>
                                            <td><c:out value="${equipoE.getPartidosJugados()}"/></td>
                                            <td><c:out value="${equipoE.getPartidosGanados()}"/></td>
                                            <td><c:out value="${equipoE.getPartidosEmpatados()}"/></td>
                                            <td><c:out value="${equipoE.getPartidosPerdidos()}"/></td>
                                            <td><c:out value="${equipoE.getGolesFavor()}"/></td>
                                            <td><c:out value="${equipoE.getGolesContra()}"/></td>
                                            <td><c:out value="${equipoE.getGolesFavor()-equipoE.getGolesContra()}"/></td>
                                            
                                        </tr>

                                    </c:forEach>
                                </tbody>
                                </tbody>
                              </table>
                            <!----------Fin Tabla Posiciones--------->
                        </div >
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

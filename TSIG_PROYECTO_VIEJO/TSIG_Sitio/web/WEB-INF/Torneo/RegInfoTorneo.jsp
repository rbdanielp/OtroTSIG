<%-- 
    Document   : RegInfoTorneo
    Created on : 21/05/2018, 02:00:55 AM
    Author     : tecnoinf
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Datos Torneo</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8"><link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
    crossorigin="anonymous"></script>

  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@taglib prefix="ct" uri="/WEB-INF/tlds/tag_tsig.tld" %>
</head>
<body>

        <!--Inicio Barra de navegacion-->
        <ct:navbar></ct:navbar>
        <!--Fin Barra de navegacion-->
        <div class="container-fluid">
            <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                <li class="nav-item">
              <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#pills-Resultado" role="tab" aria-controls="pills-Resultado" aria-selected="true">Resultado</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#pills-Partido" role="tab" aria-controls="pills-Partido" aria-selected="false">+Partido</a>
            </li>
          </ul>
          <div class="tab-content" id="pills-tabContent">
            <div class="tab-pane fade " id="pills-Partido" role="tabpanel" aria-labelledby="pills-home-tab">
                <div class="container-fluid " style="align-content: center ; max-width: 450px">
                    <div class="row">
                        <div class="col-md-12"> 
                            <form id="dataClient-form" name="fDataPartido" class="form-horizontal" action="RegistrarPartido" method="POST">
                                <h3>Nuevo encuentro</h3>
                                <div class="form-group " style="width: 100%">
                                    <label class="col-xs-12" for="filebutton">Equipo 1</label>
                                    <div class="col-xs-12">
                                        <select name="equipo1" ; style="width: 100%">
                                            <c:forEach items="${equipos}" var="item">
                                                <option value="${item}">${item}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <label class="col-xs-12" for="filebutton">Equipo 2</label>
                                    <div class="col-xs-12">
                                        <select name="equipo2" ; style="width: 100%">
                                            <c:forEach items="${equipos}" var="item">
                                                <option value="${item}">${item}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <label class="col-xs-12" for="filebutton">Grupo</label>
                                    <div class="col-xs-12">
                                        <select name="grupo" ; style="width: 100%">
                                            <c:forEach items="${grupos}" var="item">
                                                <option value="${item}">${item}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <label class="col-xs-12" for="filebutton">Estadio</label>
                                    <div class="col-xs-12">
                                        <select name="estadio" ; style="width: 100%">
                                            <c:forEach items="${estadios}" var="item">
                                                <option value="${item}">${item.getNombre()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <label class="col-xs-12">Fecha</label>
                                    <div class="col-xs-12"> <!-- Date input -->
                                        <div class="input-group date" data-provide="datepicker">
                                            <input type="text" name="fecha" class="form-control">
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-th"></span>
                                            </div>
                                        </div>
                                    </div>


                                <div class="col-xs-12" style="width: 100%; align-content: center">
                                        <button id="btnConfirmar" type="submit" class="btn btn-primary form-control" style="margin-top: 10px" >Confirmar</button>
                                </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="tab-pane fade show active" id="pills-Resultado" role="tabpanel" aria-labelledby="pills-profile-tab">
                <h2 style="text-align: center">Ingresar Resultado de Partido</h2>
                <!--Inicio del Registro+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
                  <div class="container-fluid " style="align-content: center ; max-width: 450px">
                      <div class="row">
                              <div class="col-md-12">
                                  <form name="fDataP" class="form-horizontal" action="RegistrarResultado" method="POST">  
                                      <div class="form-group " style="width: 100%">
                                          <label class="col-xs-12" for="filebutton">Partido</label>
                                          <div class="col-xs-12">
                                              <select name="partido" style="width: 100%">
                                                  <c:forEach items="${partidosJugados}" var="item">
                                                      <option value="${item.getId()}">${item.getEquipo_1().getNombrePais()}  vs   ${item.getEquipo_2().getNombrePais()} [${item.getStrFecha()}]</option>
                                                  </c:forEach>
                                              </select>
                                          </div>
                                          <br>
                                          <div class="col-xs-12">
                                              <br>
                                              <label for="golesE1"> Goles Equipo 1</label>
                                              <input type="number" name="golesE1" class="form-control" id="golesE1" placeholder="Goles Equipo 1">
                                              <br>
                                               <label for="golesE2">Goles Equipo 2</label>
                                              <input type="number" name="golesE2" class="form-control" id="golesE2" placeholder="Goles Equipo 2">
                                          </div>
                                          <div class="col-xs-12">
                                                  <button id="btnConfirmar" type="submit" class="btn btn-primary form-control" style="margin-top: 10px" >Confirmar</button>
                                          </div>
                                      </div>

                                  </form>
                              </div>
                      </div>
                  </div>
                <!--Fin Registro-->
            </div>
          </div>
        </div>
</body>
</html>

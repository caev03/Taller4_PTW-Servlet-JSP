<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Start Page</title>
    </head>
    <body>
        <h1>Collaborative Whiteboard:${user}</h1>
        <form action="mainController" method="POST">
            <button type="submit" value="Cerrar Sesión">Cerrar Sesión</button>
        </form>
        <table>
            <tr>
                <td>
                    <canvas id="myCanvas" width="150" height="150" style="border:1px solid #000000;"></canvas>
                </td>
                <td>
                    <form name="inputForm">
                        <table>

                            <tr>
                                <th>Color</th>
                                <td><input type="radio" name="color" value="#FF0000" checked="true">Red</td>
                                <td><input type="radio" name="color" value="#0000FF">Blue</td>
                                <td><input type="radio" name="color" value="#FF9900">Orange</td>
                                <td><input type="radio" name="color" value="#33CC33">Green</td>
                            </tr>

                            <tr>
                                <th>Shape</th>
                                <td><input type="radio" name="shape" value="square" checked="true">Square</td>
                                <td><input type="radio" name="shape" value="circle">Circle</td>
                                <td> </td>
                                <td> </td>
                            </tr>
                            <tr>
                                <th> </th>
                                <td><input type="submit" value="Send Snapshot" onclick="defineImageBinary(); return false;"></td>
                                <td><input type="checkbox" id="instant" value="Online" checked="true">Online</td>
                                <td> </td>
                                <td> </td>
                            </tr>

                        </table>


                    </form>
                </td>
            </tr>
        </table>
        
        <a id="downloadLnk" download="image.jpg">Download Canvas as JPG</a>
        <button onclick="hide()" id="usersssButton">Mostar usuarios conectados</button>
        <div id="usersss" style="display: none">
            <ul>
                <c:forEach var="usuario" items="${applicationScope.users}">
                    <li>${usuario}</li>
                    </c:forEach>
            </ul>
        </div>
        <script type="text/javascript" src="js/websocket.js"></script>
        <script type="text/javascript" src="js/whiteboard.js"></script>
    </body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Search</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="../css/main.css" rel="stylesheet">
    <style>.header_links {
        margin-top: 2rem;
        /* margin-bottom: 100px; */
    }

    .header_btn {
        margin-right: 10px;
    }

    .header_log_out {
        float: right;
        margin-bottom: 150px;
    }

    .header_link {

        background: url(../img/canada-lake-feb.jpg) no-repeat;
        -moz-background-size: 100%;
        -o-background-size: 100%;
        background-size: 100%;

    }

    .header_button {
        margin: 20px 0px 0px 0px;
        padding: 0px;
    }

    .main_content {
        padding: 0px;

        /* border: 2px solid #e3e3e3;
        /* background-color: #E9E9E9; */
        /* border-radius: 10px; */
        margin-top: 20px;


    }

    .text_content_main {
        font-size: 24px;
    }

    /* s */

    .control_input {

    }

    .date_control {

    }

    .search_bitton {
        margin-top: 20px;
    }

    /* .fly {
        padding: 10px;
    } */

    .fly_div {
        border: 1px solid #e3e3e3;
        /* margin: 20px; */
        padding: 20px;
    }

    .btn_fly {
        width: 10%;
    }</style>
</head>

<body>
<header>
    <div class="container-fluid">
        <div class="row header_link">
            <div class="col-12">
                <div class="header_links">
                    <input type="button" class="btn btn-outline-info header_log_out" value="log out ">
                </div>
            </div>
        </div>

    </div>
</header>

<section class="">
    <div class="container">
        <div class="row header_button">
            <div class="col-12">
                <input type="button" value="Main" class="btn btn-outline-info header_btn">
                <input type="button" class="btn btn-outline-info header_btn" value="User info">
                <input type="button" class="btn btn-outline-info header_btn" value="Find flight">
            </div>
        </div>
    </div>
</section>
<main>
    <form action="${pageContext.request.contextPath}/flights/search" method="get">
    <div class="container main_content">
        <div class="row">
            <div class="col-md-6 content_main_input">
                    <label for="from" class="text_content_main">From:</label>
                    <input type="text" name="departureAirport" class="form-control control_input" id="from">
                    <label for="to" class="text_content_main">To:</label>
                    <input type="text" name="arrivalAirport" class="form-control control_input" id="to">
                </div>
                <div class="col-md-6">
                    <label for="date" class="text_content_main">Date:</label>
                    <input class="form-control date_control" name="departureDateFrom" type="date" id="date">
                    <label for="date2" class="text_content_main">Date:</label>
                    <input class="form-control date_control" name="departureDateTo" type="date" id="date2">
                </div>

        </div>
        <div class="row">
            <div class="col-md-12">

                <button type="submit" class="btn btn-outline-primary btn-lg search_bitton">Search</button>

            </div>
        </div>
    </div>
        </form>
        <c:forEach items="${flights}" var="flight">
            <div class="row flight_content">
                <div class="col-md-12 fly">
                    <div class="fly_div">
                        <p>From: <span><c:out value="${flight.departureAirport}"></c:out> </span></p>
                        <p>To: <span> <c:out value="${flight.arrivalAirport}"></c:out> </span></p>
                        <p>Departure time:<span><c:out value="${flight.departureDate}"></c:out> </span></p>
                        <p>Arrival time:<span> <c:out value="${flight.arrivalDate}"></c:out> </span></p>
                        <form action="${pageContext.request.contextPath}/tickets/buy" method="get">
                            <input type="hidden" name="id" value="${flight.id}" />
                            <input type="submit" class="btn btn_fly btn-outline-success"  value="BUY!" />
                        </form>
                    </div>

                </div>
            </div>
        </c:forEach>
    </div>

</main>
</body>

</html>
<%--daa1bab66cbb2b154fbe99cf1dfdb247 --%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>서:행 #예약하기</title>

<!-- Bootstrap -->
<link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="../css/body.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js">
</script>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?

appkey=daa1bab66cbb2b154fbe99cf1dfdb247"></script>

<link rel="stylesheet"

href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat"

rel="stylesheet" type="text/css">
<!-- Google font -->
<link href="https://fonts.googleapis.com/css?
family=Montserrat:400,700,900" rel="stylesheet">

<!--

<link rel="stylesheet" href="../css/linearicons.css">
<link rel="stylesheet" href="../css/font-awesome.min.css">
<link rel="stylesheet" href="../css/bootstrap.css">
<link rel="stylesheet" href="../css/magnific-popup.css">
<link rel="stylesheet" href="../css/nice-select.css">
<link rel="stylesheet" href="../css/animate.min.css">

<link rel="stylesheet" href="../css/owl.carousel.css">
<link rel="stylesheet" href="../css/main.css"> -->

</head>
<body >

<header id="header">

<div class="container box_1170 main-menu">

<div class="row align-items-center justify-content-
between d-flex">

<div id="logo">

<a href="#"><img

src="../img/logo.png" alt="" title="" /></a>
</div>
<nav id="nav-menu-container">
<ul class="nav-menu">

<li class="menu-active"><a

href="#">Home</a></li>

<li><a

href="#">Category</a></li>

<li><a href="#">Archive</a>

</li>

<li class="#"><a

href="">Pages</a>

</li>

<li class="menu-has-
children"><a href="">Blog</a>

</li>
<li><a href="#">Contact</a>

</li>

</ul>
</nav>

</div>

</div>
</header>

<div id="booking" class="section">

<div class="section-center">
<div class="container">
<div class="row">

<div class="col-md-5">

<div class="booking-cta">

<!--vo.getName() -->
<h1>WXYZ Bar @

Aloft Seoul Myeongdong</h1>

</div>

</div>
<section>

<div class="col-md-6 col-md-offset-
1">

<div class="booking-form">

<form>
<div

class="row">
<div class="col-md-6">

<div class="form-group">

<input class="form-control" type="text">
<span class="form-label">Name</span>

</div>

</div>
<div class="col-md-6">

<div class="form-group">

<input class="form-control" type="email">
<span class="form-label">Email</span>

</div>

</div>

</div>
<div

class="row">
<div class="col-md-6">

<div class="form-group">

<input class="form-control" type="tel">
<span class="form-label">Phone</span>

</div>

</div>
<div class="col-md-3 col-sm-6">
<div class="form-group">

<span class="form-label">Guests</span>
<select class="form-control">
<option>1 Person</option>
<option>2 People</option>
<option>3 People</option>
<option>4 People</option>

</select>
<span class="select-arrow"></span>

</div>

</div>

</div>
<div

class="row">
<div class="col-md-6">

<div class="form-group">

<input class="form-control" type="date" required>
<span class="form-label">Date</span>

</div>

</div>

<div class="col-md-6">

<div class="form-group">

<span class="form-label">Time</span>
<select class="form-control">

<% // 시간 출력

int i=0;
for(i=13; i<18 ;i++)
{

%>

<option><%=i

%>:00</option>

<%

}

%>

</select>

</div>

</div>

</div>
<div

class="form-btn">
<button class="submit-btn">Book Now</button>

</div>
</form>

</div>

</div>
</section>

<div id="myCarousel" class="carousel slide text-center" data-ride="carousel">
<ol class="carousel-indicators">
<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
<li data-target="#myCarousel" data-slide-to="1"></li>
<li data-target="#myCarousel" data-slide-to="2"></li>
</ol>

<div class="carousel-inner" role="listbox">
<div class="item active">

<img src="https://media-cdn.tripadvisor.com/media/photo-
o/11/49/bb/63/wxyz-bar.jpg" alt="">

</div>
<div class="item">

<img src="https://media-cdn.tripadvisor.com/media/photo-
f/11/49/bb/64/wxyz-hall.jpg" alt="">

</div>

<div id="map" style="width:500px;height:300px; float:left;">

</div>

</div>

</div>

<a class="left carousel-control" href="#myCarousel" role="button" data-
slide="prev">

<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
<span class="sr-only">Previous</span>
</a>

<a class="right carousel-control" href="#myCarousel" role="button" data-
slide="next">

<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
<span class="sr-only">Next</span>
</a>
</div>
</div>
</div>

</div>

<script>

var container = document.getElementById('map');
var options = {

center: new kakao.maps.LatLng(33.450701,

126.570667),

level: 3

};
var map = new kakao.maps.Map(container, options);

</script>
<script src="js/jquery.min.js"></script>
<script>

$('.form-control').each(function () {
floatedLabel($(this));

});
$('.form-control').on('input', function () {

floatedLabel($(this));

});
function floatedLabel(input) {

var $field = input.closest('.form-group');
if (input.val()) {

$field.addClass('input-not-empty');

} else {

$field.removeClass('input-not-empty');

}

}

</script>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
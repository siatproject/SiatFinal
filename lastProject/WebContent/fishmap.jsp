<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width" initial-scale ="1">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
<title>Insert title here</title>
<style type="text/css">
    html, body {
      position: relative;
      height: 100%;
    }
    body {
      background: #fff;
      font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
      font-size: 14px;
      color:#000;
      margin: 0;
      padding: 0;
    }
    
</style>
</head>
<body>
<header>
 <nav class="navbar navbar-expand-lg navbar-dark bg-white">

<!--  Show this only on mobile to medium screens  -->
  <a class="navbar-brand d-lg-none" href="#">Navbar</a>

  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggle" aria-controls="navbarToggle" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

<!--  Use flexbox utility classes to change how the child elements are justified  -->
  <div class="collapse navbar-collapse justify-content-between" id="navbarToggle">
    <ul class="navbar-nav">
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown"><i class='fas fa-align-justify' style="color:#2196F3"></i></a>
      	<div class ="dropdown-menu">
      		<a class="dropdown-item" href="#">민물낚시</a>
      		<a class="dropdown-item" href="#">바다낚시</a>
      		<a class="dropdown-item" href="#">조황게시판</a>
      	</div>
      </li>
    </ul>
<!--   Show this only lg screens and up   -->
    <a class="navbar-brand d-none d-lg-block" href="#" style="color:#2196F3">SHIP GO</a>
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="#" style="color:#2196F3">로그인</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#" style="color:#2196F3">회원가입</a>
      </li>
    </ul>
  </div>
</nav>
</header>
<div class="row">
	<div class="col-lg-4">
		<h4 style="text-align:center">전체 또는 특정 지역</h4>
		<select class="form-control" id="regin">
		  <option value="">전국</option>
		  <option value="서울">서울</option>
		  <option value="경기">경기도</option>
		  <option value="인천">인천광역시</option>
		  <option value="강원">강원도</option>
		  <option value="충청북도">충청북도</option>
		  <option value="대전">대전광역시</option>
		  <option value="충청남도">충청남도</option>
		  <option value="경상북도">경상북도</option>
		  <option value="대구">대구광역시</option>
		  <option value="경상남도">경상남도</option>
		  <option value="울산">울산광역시</option>
		  <option value="전라북도">전라북도</option>
		  <option value="전라남도">전라남도</option>
		  <option value="부산">부산광역시</option>
		</select>
	</div>
	<div class="col-lg-4">
		<h4 style="text-align:center">어종 선택</h4>
		<select class="form-control" id="fishSpecies">
		  <option value="">전체</option>
		  <option value="붕어">붕어</option>
		  <option value="잉어">잉어</option>
		  <option value="향어">향어</option>
		</select>
	</div>
	<div class="col-lg-4">
		<button type="button" class="btn btn-primary" style="margin-top:35px" onclick="search()">검색</button>
	</div>
</div>

<div class="container">
	<div id="map" style="width:100%;height:400px;"></div>
	<div class="row">
		<div class="col-lg12">
			<div class="card-body" id="detail">
				
			</div>
		</div>
	</div>
</div>





<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=800ca059e8d14618fcc8e7060b5185ff&libraries=clusterer"></script>
<script type ="text/javascript">
function search(){
	var regin = document.getElementById("regin").value;
	var fishSpecies = document.getElementById("fishSpecies").value;
	var getDataUrl = "con?command=mapPage&regin=" + regin + "&fishSpecies=" + fishSpecies;
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var mapdata = JSON.parse(this.responseText);
			getMapData(mapdata);
		}
	};
	xhttp.open("GET", getDataUrl, true);
	xhttp.send();
}

function getMapData(data){
	
    var map = new daum.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
        center : new daum.maps.LatLng(36.2683, 127.6358), // 지도의 중심좌표 
        level : 14 // 지도의 확대 레벨 
    });
    
    // 마커 클러스터러를 생성합니다 
    var clusterer = new daum.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
        minLevel: 10 // 클러스터 할 최소 지도 레벨 
    });

    // 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
    var markers = data.positions.map(function(position, i) {
            var marker = new daum.maps.Marker({
                position : new daum.maps.LatLng(position.fishingLat, position.fishingLng),
                clickable : true
            });
            var makehtml = 	'<h2 class ="card-title">' + position.fishingName + '</h2>' + 
            				'<p class="card-text">낚시터 유형 : ' + position.fishingType + '<br>' +
            				'낚시터 주소 : ' + position.fishingAddress + '<br>' +
            				'낚시터 전화번호 : ' + position.fishingNumber + '<br>' +
            				'주요 어종 : ' + position.fishingSpecies + '<br>' +
            				'낚시터 가격 : ' + position.fishingPriece + '</p>';
            daum.maps.event.addListener(marker, 'click', function() {
            	document.getElementById('detail').innerHTML = makehtml;
          	});
            return marker;
    });
    

    daum.maps.event.addListener(clusterer, 'clusterclick', function(cluster) {
        // 현재 지도 레벨에서 1레벨 확대한 레벨
        var level = map.getLevel()-1;

        // 지도를 클릭된 클러스터의 마커의 위치를 기준으로 확대합니다
        map.setLevel(level, {anchor: cluster.getCenter()});
    });
    clusterer.addMarkers(markers);
}
</script>
<script src="jquery/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script> <!-- Menu Toggle Script -->
</body>
</html>
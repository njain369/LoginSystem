$(document).ready(function(){
  
  function AutoRefresh( t ) {
               setTimeout("location.reload(true);", t);
            }
AutoRefresh(6000000);
  var pos=[];
  var unit="C";
  var apiid="a433577734ebece2ce19de11909b8e56";
  var link=[];
  var temp=0,min=0,max=0,maxi=[0,0,0,0];
  var report=[];
  var report=[];
  var h=[],press=[],wspeed=[],tempp=[],tempmax=[],tempmin=[],rise=[],set=[];
  var h1=[],press1=[],wspeed1=[],tempp1=[],tempmax1=[],tempmin1=[];
  var rgeo;
  var linkr=[];
  var sr,ss,fsr,fss;
  var date1;
  
  var weekday = new Array(7);
  weekday[0] = "Sun";
  weekday[1] = "Mon";
  weekday[2] = "Tue";
  weekday[3] = "Wed";
  weekday[4] = "Thu";
  weekday[5] = "Fri";
  weekday[6] = "Sat";
  
  var fh=0,fpress=0,fwspeed=0,ftempp=0,ftempmax=0,ftempmin=0,frise=0,fset=0;
  
  var r;
  var ry;
  
  
  
  
  
  
  function sbox() {
       // var skey = document.getElementById('link_id').value;
        
    document.getElementById('link').onclick = function()
{
    skey = document.getElementById('link_id').value;
      document.write(skey);
};
    
    
    
    }
  
  
  function getdate()
  {
    var today = new Date();
var dd = today.getDate();
var mm = today.getMonth()+1; //January is 0!
var yyyy = today.getFullYear();

if(dd<10) {
    dd = '0'+dd
} 

if(mm<10) {
    mm = '0'+mm
} 

today = dd + '/' + mm + '/' + yyyy;
    date1= yyyy + '-' + mm + '-' + dd;
document.getElementById('txt1').innerHTML =today;
    
  }
  var rcity;
  function convertToC(fahrenheit) {
  var cel;
  cel = (fahrenheit -32)/1.8; 
  return cel.toFixed(3);
}
    
    function convertToF(celsius) {
  var fahrenheit;
  fahrenheit = (celsius * (9/5)) + 32; 
  return fahrenheit.toFixed(3);
}
    function pad(num) { 
  return ("0"+num).slice(-2);
}
function getTimeFromDate(timestamp) {
  var date = new Date(timestamp * 1000);
  var hours = date.getHours();
  var minutes = date.getMinutes();
  var seconds = date.getSeconds();
  return pad(hours)+":"+pad(minutes)+":"+pad(seconds)
}
  
  
  getdate();
  
 
  var input = document.getElementById("searchbox");
input.addEventListener("keyup", function(event) {
  event.preventDefault();
  if (event.keyCode === 13) {
    $("#searchbutton").click();
  }
});
  
  $("#searchbutton").click(function() {
  fh=0,fpress=0,fwspeed=0,ftempp=0,ftempmax=0,ftempmin=0;
    ss=0,sr=0,fsr=0,fss=0;
  rcity = document.getElementById("searchbox").value;
    link[0]="https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(SELECT%20woeid%20FROM%20geo.places%20WHERE%20text=\"("+pos[0]+","+pos[1]+")\")&format=json";
  //link[1]="https://api.openweathermap.org/data/2.5/forecast?lat="+pos[0]+"&lon="+pos[1]+"&appid="+apiid;//Upcoming data
  link[1]="https://api.openweathermap.org/data/2.5/weather?lat="+pos[0]+"&lon="+pos[1]+"&appid="+apiid+"&units=imperial";//Today's data
    $(".footer").html("<u>Coordinates</u> <br/><b>Latitude: </b><i>"+pos[0]+"</i><br/> <b>Longitude: </b><i>"+pos[1]);
    
  link[2]="https://api.weatherbit.io/v2.0/forecast/daily?&lat="+pos[0]+"&lon="+pos[1]+"&units=I&key=a3e19da5b27748a1b246d7caa320db8b";  
  link[3]="https://api.apixu.com/v1/forecast.json?key=c94f96cab7914f7a98c173036182012&q="+pos[0]+","+pos[1];
    
    
     linkr[0]="https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text=%27"+rcity+"%27)&format=json#";
    
linkr[1]="https://api.openweathermap.org/data/2.5/weather?q="+rcity+"&appid=a433577734ebece2ce19de11909b8e56&units=imperial";

linkr[2]="https://api.weatherbit.io/v2.0/forecast/daily?&city="+rcity+"&units=I&key=a3e19da5b27748a1b246d7caa320db8b";

linkr[3]="https://api.apixu.com/v1/forecast.json?key=c94f96cab7914f7a98c173036182012&q="+rcity;
    var tempp0;
      $.ajax({
      async: false ,
 url:linkr[1], success: function(results) {
   report[0]=results;
   h[0]=results.main.humidity;
   press[0]=results.main.pressure;
   wspeed[0]=results.wind.speed;
   tempp[0]=results.main.temp;
   tempmax[0]=results.main.temp_max;
   tempmin[0]=results.main.temp_min;
   rise[0]=results.sys.sunrise;
   set[0]=results.sys.sunset;
    }
});
    
    
 
    
    
    $.ajax({
      async: false ,
      
 url:linkr[1], success: function(results) {
   report[1]=results;
   h[1]=results.main.humidity;
   press[1]=results.main.pressure;
   wspeed[1]=results.wind.speed;
   tempp[1]=results.main.temp;
   tempmax[1]=results.main.temp_max;
   tempmin[1]=results.main.temp_min;
   rise[1]=results.sys.sunrise;
   set[1]=results.sys.sunset;
    }
});
    
    
  $.ajax({
    async:false,
 url:linkr[2], success: function(dresults){
    report[2]=dresults;
   h[2]=dresults.data[0].rh;
   press[2]=dresults.data[0].pres;
   wspeed[2]=dresults.data[0].wind_spd;
   tempp[2]=dresults.data[0].temp;
   tempmax[2]=dresults.data[0].max_temp;
   tempmin[2]=dresults.data[0].min_temp;
   rise[2]=dresults.data[0].sunrise_ts;
   set[2]=dresults.data[0].sunset_ts;
   
    } 
});
    
    
     $.ajax({async: false ,
 url:linkr[3], success: function(aresults){
    report[3]=aresults;
    h[3]=aresults.current.humidity;
    press[3]=aresults.current.pressure_mb;
    wspeed[3]=aresults.current.wind_mph;
    tempp[3]=aresults.current.temp_f;
    tempmax[3]=aresults.forecast.forecastday[0].day.maxtemp_f;
    tempmin[3]=aresults.forecast.forecastday[0].day.mintemp_f;
    rise[3]=aresults.forecast.forecastday[0].astro.sunrise;
   set[3]=aresults.forecast.forecastday[0].astro.sunset;
    }
});
   // document.write(rise[2]);
    sr=rise[1]+rise[2];
    sr=sr/2;
    
    
    fsr =getTimeFromDate(sr);
    
    
    ss=set[1]+set[2];
    ss=ss/2;
    
    
    fss =getTimeFromDate(ss);
  
    
    //  $(".max").text(tempmax);
    // document.write(tempp);
  //  document.write(h);
// document.write(tempmin);
//document.write(press);
    //document.write(wspeed);
  var i;
    for(i=0;i<report.length;i++)
     {
      fh+=parseInt(h[i]);
      fpress+=parseInt(press[i]);
      fwspeed+=parseInt(wspeed[i]);
      ftempp+=parseInt(tempp[i]);
      ftempmax+=parseInt(tempmax[i]);
      ftempmin+=parseInt(tempmin[i]);
     }
    
    
   // fh=(parseInt(h[0])+parseInt(h[1]))/2;
    ftempp=ftempp/report.length;
    fh=fh/report.length
    fpress=fpress/report.length
    fwspeed=fwspeed/report.length
    ftempmax=ftempmax/report.length
    ftempmin=ftempmin/report.length
    
    
    
    ftempp=convertToC(ftempp);
    ftempmax=convertToC(ftempmax);
    ftempmin=convertToC(ftempmin);
    
    
    change();
  
    $(".humidity").text(fh);
    $(".wind").text(fwspeed);
    $(".pressure").text(fpress);
    $(".rain").text(report[1].clouds.all);
    
    
   // document.write(searchInput);
  });
  
  function change(){
    $(".temp").text(ftempp);
    $(".max").text(ftempmax);
    $(".min").text(ftempmin);
   $(".location").text(report[1].name+", "+report[1].sys.country);
   // $(".location").text(rcity);
    //document.write(fh);
    $(".humidity").text(fh);
    $(".wind").text(fwspeed);
    $(".pressure").text(fpress);
    $(".rain").text(report[1].clouds.all);
    $(".description").text(report[1].weather[0].description.toUpperCase());
      $(".sunrise").text(fsr);
    $(".sunset").text(fss);
    $(".footer").html(" By City - "+rcity);
  }
  
  
  function startTime() {
  var today = new Date();
  var h = today.getHours();
  var m = today.getMinutes();
  var s = today.getSeconds();
  m = checkTime(m);
  s = checkTime(s);
  document.getElementById('txt').innerHTML ="<b>"
 + h + ":" + m + ":" + s+"</b>";
  var t = setTimeout(startTime, 500);
}
function checkTime(i) {
  if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
  return i;
}
  
  
  
  
  startTime();
  try{
  navigator.geolocation.getCurrentPosition(function(position) { 
  //Get Location & Link
  pos[0]=position.coords.latitude;
  pos[1]=position.coords.longitude;
 
    
  console.log("Latitude: "+pos[0]+" & Longitude: "+pos[1]);
    
 
    rgeo="https://us1.locationiq.com/v1/reverse.php?key=021fd4ac568a23&lat="+pos[0]+"&lon="+pos[1]+"&format=json";
    
   
    
    
    link[0]="https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(SELECT%20woeid%20FROM%20geo.places%20WHERE%20text=\"("+pos[0]+","+pos[1]+")\")&format=json";
  //link[1]="https://api.openweathermap.org/data/2.5/forecast?lat="+pos[0]+"&lon="+pos[1]+"&appid="+apiid;//Upcoming data
  link[1]="https://api.openweathermap.org/data/2.5/weather?lat="+pos[0]+"&lon="+pos[1]+"&appid="+apiid+"&units=imperial";//Today's data
    $(".footer").html("<u>Coordinates</u> <br/><b>Latitude: </b><i>"+pos[0]+"</i><br/> <b>Longitude: </b><i>"+pos[1]);
    
    
    
  link[2]="https://api.weatherbit.io/v2.0/forecast/daily?&lat="+pos[0]+"&lon="+pos[1]+"&units=I&key=a3e19da5b27748a1b246d7caa320db8b";  
  link[3]="https://api.apixu.com/v1/forecast.json?key=c94f96cab7914f7a98c173036182012&q="+pos[0]+","+pos[1];
    var displayname;
    var rcity;
    $.ajax({
      async: false ,
      
 url:rgeo, success: function(revgeo) {
   displayname=revgeo.address.road
   rcity=revgeo.address.city;
    }
});
  //  document.write(displayname);
    displayname=displayname+", "+rcity;
    
   
    
     $.ajax({
      async: false ,
 url:link[1], success: function(results) {
   report[0]=results;
   h[0]=results.main.humidity;
   press[0]=results.main.pressure;
   wspeed[0]=results.wind.speed;
   tempp[0]=results.main.temp;
   tempmax[0]=results.main.temp_max;
   tempmin[0]=results.main.temp_min;
   rise[0]=results.sys.sunrise;
   set[0]=results.sys.sunset;
    }
});
    
    
   
    //Wed Jun 20 19:20:44 +0000 2018"
   // var ydr= Date.parse("Wed Jun 20 19:20:44 +0000 2012")
    
   $.ajax({
      async: false ,
 url:link[1], success: function(results) {
   report[1]=results;
   h[1]=results.main.humidity;
   press[1]=results.main.pressure;
   wspeed[1]=results.wind.speed;
   tempp[1]=results.main.temp;
   tempmax[1]=results.main.temp_max;
   tempmin[1]=results.main.temp_min;
   rise[1]=results.sys.sunrise;
   set[1]=results.sys.sunset;
    }
});
    
    
  $.ajax({global: false,
    async:false,
 url:link[2], success: function(dresults){
    report[2]=dresults;
   h[2]=dresults.data[0].rh;
   press[2]=dresults.data[0].pres;
   wspeed[2]=dresults.data[0].wind_spd;
   tempp[2]=dresults.data[0].temp;
  tempmax[2]=dresults.data[0].max_temp;
  tempmin[2]=dresults.data[0].min_temp;
     
   rise[2]=dresults.data[0].sunrise_ts;
   set[2]=dresults.data[0].sunset_ts;
    } 
});
   
    
    $.ajax({async: false ,
 url:link[3], success: function(aresults){
    report[3]=aresults;
    h[3]=aresults.current.humidity;
    press[3]=aresults.current.pressure_mb;
    wspeed[3]=aresults.current.wind_mph;
    tempp[3]=aresults.current.temp_f;
    tempmax[3]=aresults.forecast.forecastday[0].day.maxtemp_f;
    tempmin[3]=aresults.forecast.forecastday[0].day.mintemp_f;
    rise[3]=aresults.forecast.forecastday[0].astro.sunrise;
    set[3]=aresults.forecast.forecastday[0].astro.sunset;
    }
});
  
     
    sr=rise[1]+rise[2];
    sr=sr/2;
    
    
    fsr =getTimeFromDate(sr);
    
    
    ss=set[1]+set[2];
    ss=ss/2;
    
    
    fss =getTimeFromDate(ss);
  

 // var n = weekday[d.getDay()]; 
    //document.write(d1);
    
    
   
 // document.write(h[3]);
   //document.write(press[3]);
   //document.write(tempp[3]);
  // document.write(tempmax[3]);
    
    var i;
    for(i=0;i<report.length;i++)
     {
      fh+=parseInt(h[i]);
      fpress+=parseInt(press[i]);
      fwspeed+=parseInt(wspeed[i]);
      ftempp+=parseInt(tempp[i]);
      ftempmax+=parseInt(tempmax[i]);
      ftempmin+=parseInt(tempmin[i]);
     }
   // fh=(parseInt(h[0])+parseInt(h[1]))/2;
    ftempp=ftempp/report.length;
    fh=fh/report.length
    fpress=fpress/report.length
    fwspeed=fwspeed/report.length
    ftempmax=ftempmax/report.length
    ftempmin=ftempmin/report.length
    
    ftempp=convertToC(ftempp);
    ftempmax=convertToC(ftempmax);
    ftempmin=convertToC(ftempmin);
    
      
     
    
    
     $(".temp").text(ftempp);
    $(".max").text(ftempmax);
      $(".min").text(ftempmin);
    //  $(".location").text(report[1].name+", "+report[1].sys.country);
    $(".location").text(rcity);
     $(".humidity").text(fh);
      $(".wind").text(fwspeed);
    $(".pressure").text(fpress);
    $(".rain").text(report[1].clouds.all);
     $(".description").text(report[1].weather[0].description.toUpperCase());
    $(".sunrise").text(fsr);
    $(".sunset").text(fss);
    
    
     var iplink = "https://api.ipify.org?format=json";
  var ipadd;
   $.ajax({global: false,
    async:false,
 url:iplink, success: function(results){
    
   ipadd=results.ip;
  
    } 
});
    
    pos[0].toFixed(5);
    pos[1].toFixed(5);
    
     var d = new Date(); // for now
var ho = d.getHours(); // => 9
var min= d.getMinutes(); // =>  30
var sec = d.getSeconds();
 var time1 = ho + ':' + min +':'+sec;
    
 
  
    
    $.ajax({
      type: "POST",
                    url: 'https://medicaljava.000webhostapp.com/savedetails.php',
                    data: {lat:pos[0],
                           long:pos[1],
                           date:date1,
                           time:time1,
                           ip:ipadd,
                           location: displayname,
                           ctemp:ftempp,
                           cmaxtemp:ftempmax,
                           cmintemp:ftempmin,
                           cpressure:fpress,
                           chumidity:fh,
                           temp1:tempp[1],
                           maxtemp1:tempmax[1],
                           mintemp1:tempmin[1],
                           pressure1:press[1],
                           humidity1:h[1],
                           temp2:tempp[2],
                           maxtemp2:tempmax[2],
                           mintemp2:tempmin[2],
                           pressure2:press[2],
                           humidity2:h[2]
                           
                      }
        
    });
    
  
    
    
   
    
    
  var notunit="F";
    //toogle button
    $(".sel a").on("click",function(){
      unit=this.id;
      
      if(unit=="C" && unit==notunit ){
      ftempp=convertToC(ftempp);
    ftempmax=convertToC(ftempmax);
    ftempmin=convertToC(ftempmin);
      
      notunit="F";
      }
      if(unit=="F" && unit==notunit){
        ftempp=convertToF(ftempp);
          ftempmax=convertToF(ftempmax);
            ftempmin=convertToF(ftempmin);
     
        notunit="C";
      }
      $(".temp").text(ftempp);
    $(".max").text(ftempmax);
      $(".min").text(ftempmin);
      $(".scale").text(unit);
      $("#"+unit).addClass("selected");
      $("#"+unit).removeClass("unselected");      
      $("#"+notunit).addClass("unselected");
      $("#"+notunit).removeClass("selected");
    });
    
      
   
});
}


catch(err){
  $(".footer").html("Your Browser was unable to detect location.<br/> Please enable location settings on your device & allow location access to this page.");
}
  
  
  
  
  
  
  
  
  
  
  
  
  
});

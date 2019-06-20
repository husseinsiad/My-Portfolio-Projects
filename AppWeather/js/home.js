$(document).ready(function () {
    $('#forecast').hide();
    $('#current').hide();
     $('.alert-danger').toggle();
    $('.btn-secondary').click(function(){
        var zip=$('#inputZip').val();
        var unit=$('#inputState').val();
  
            if(zip.length<5){
           
                $('.alert-danger').text('Zip code: please enter a 5-digit zip code')
                //location.reload();
                $('.alert-danger').show();
            }

        $.ajax({

            type: 'GET',
            url: 'https://api.openweathermap.org/data/2.5/weather?zip='+zip+',us&units='+unit+'&appid=e1802fcf11ed12119b86a05f1c8a75b1',
            success: function (data) {
                console.log(data)
                $('#forecast').toggle();
                $('#current').toggle();
                var weatherContent = $('#currentBlock2');
                var weatherContent2 = $('#currentBlock1');
                var forecastContent = $('#image');
                 $('span').text(data.name)
                    var weatherInfo = '<p>';
                    weatherInfo += 'Temprature: ' + data.main.temp +'<br>';
                    weatherInfo += 'humidity: ' + data.main.humidity+'%' +'<br>';
                    weatherInfo += 'wind: ' + data.wind.speed +'<br>';
                    weatherContent.append(weatherInfo);

                    var weatherInfo1 = '<p>';
                    weatherInfo1+='Haze: ' + data.weather[0].description +'<br>';
                    weatherContent2.append(weatherInfo1);
                    //Five Day Forecast
                    var image='http://openweathermap.org/img/w/';
                    var forecastInfo='<img src="'+image+data.weather[0].icon +'.png'+'" />'+data.weather[0].description+'<br>'
                    forecastInfo+= 'H'+data.main.temp_max+'F'+' '+'L'+data.main.temp_min+'F';
                    forecastContent.append(forecastInfo);

            },
            error: function () {
                alert('FAILURE!');
            }
        });
    })
  
});


function initMap() {
  let center = {
    lat: 0,
    lng: 0,
  }
  let map = new google.maps.Map(document.getElementById('map'), {
    zoom: 4,
    center: center,
  })

  navigator.geolocation.getCurrentPosition(
    (position) => {
      const newCenter = {
        lat: position.coords.latitude,
        lng: position.coords.longitude
      }
      map.setCenter(newCenter);
    }
  );



  function myAddMarker(params) {
    let contentString = `
    <div class="media msg">
      <div class="media-body">
        <h5 class="media-heading">
          ${params.username}
          <small class="time"><i class="fa fa-clock-o"></i> ${params.createdat} </small>
        </h5>
        <small class="col-lg-10">${params.message}</small>
        <div class="col-lg-12">
          ${params.likes} <i class="fa fa-thumbs-up" aria-hidden="true"></i>
        </div>
      </div>
    </div>`;

    let infoWindow = new google.maps.InfoWindow({
      content: contentString,
    });

    var gpsedPost = {
      lat: parseFloat(params.latitude),
      lng: parseFloat(params.longitude),
    };
    console.log(gpsedPost);

    var marker = new google.maps.Marker({
      position: gpsedPost,
      map: map,
      title: params.username,
    });

    marker.addListener('click', () => infoWindow.open(map, marker))

    return marker;
  }


  const data = Array.from(document.getElementById('data').childNodes).map(x => x.childNodes[1]).filter(e => e);
  const childData = data.map(e => Array.from(e.childNodes));
  const dataInJson = childData.map(n =>
        n.filter(
                x =>
                  x.id === 'username' ||
                  x.id === 'latitude' ||
                  x.id === 'longitude' ||
                  x.id === 'createdat' ||
                  x.id === 'likes' ||
                  x.id === 'message'
            )
    ).map(x => {
      return {
        username: x[0].innerText.trim(),
        createdat: x[1].innerText.trim(),
        likes: x[2].innerText.trim(),
        latitude: x[3].innerText.trim(),
        longitude: x[4].innerText.trim(),
        message: x[5].innerText.trim()
      };
    });

  const dataWithGeoLocation = dataInJson.filter(x => (x.latitude.length > 0) && (x.longitude.length > 0));
  const dataInMap = dataWithGeoLocation.map(x => {
    const marker = myAddMarker(x);
    return marker;
  });

};

<template>
  <div>

    <div id="map"></div>
    <div class="button-group">
    </div>
  </div>
</template>

<script>

import { listBike,  getBike} from "@/api/bike";

export default {
  name: "KaKaoMap",
  data() {
    return {
      markerPositions:[],
      markers: [],
      infowindow: null,
      total_list:[],
    };
  },
  created(){
    
    listBike(
      async ({ data }) => {
        this.total_list = data;
        if (this.markerPositions.length > 0) {
                this.markerPositions = [];
            }
            this.markerPositions = [];
            this.total_list.forEach((item) => {
                let marker = {
                    title: item.description,
                    latlng: new kakao.maps.LatLng(item.latitude, item.longitude),
                };
                this.markerPositions.push(marker);
            });

            this.displayMarkers(this.markerPositions);
      },
      (error) => {
        //alert(error);
      });

  },
    

  mounted() {
    /*global kakao*/
    if (!window.kakao || !window.kakao.maps) {
      const script = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=ebb1819968721206bc3d685fe4d060a9";
      script.addEventListener("load",() =>{
        kakao.maps.load(this.initMap);
      })
      document.head.appendChild(script);
    }else{
      this.initMap();
    }
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.566345, 126.977893,16),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
    },
    displayMarkers(markerPositions) {
      
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }
      var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
        for (var i = 0; i < markerPositions.length; i ++) {
      
        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35); 
        
        // 마커 이미지를 생성합니다    
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
        
        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            map: this.map, // 마커를 표시할 지도
            position: markerPositions[i].latlng, // 마커를 표시할 위치
            title : markerPositions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image : markerImage // 마커 이미지 
        });
        this.markers.push(marker);
      }
      }
    },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 1000px;
  height: 600px;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}
</style>
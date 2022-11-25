<template>
  <div>
    <h1>Select your TTareung-e</h1>
    <div class="display: flex">
      <b-button class="bg-info" @click="showland">명소 정보 보기</b-button>
      <b-button class="bg-success" @click="showpark">구역별 공원 정보 보기</b-button>
    </div>
    <b-form-select
      v-model="gugunCode"
      :options="gu_list"
      @change="guSelect"
      class="form-select"
    ></b-form-select>
    <div id="map"></div>
  </div>
</template>

<script>
import { listBike } from "@/api/bike";
import { listLand, listPark } from "@/api/parkland";

export default {
  name: "KaKaoMap",
  data() {
    return {
      gugunCode: null,
      markerPositions: [],
      parkmarkerPositions: [],
      landmarkerPositions: [],
      markers: [],
      parkmarkers: [],
      landmarkers: [],
      overlays: [],
      landoverlays: [],
      parkoverlays: [],
      total_list: [],
      parktotal_list: [],
      landtotal_list: [],
      gu_list: [
        "종로구",
        "중구",
        "용산구",
        "광진구",
        "동대문구",
        "중랑구",
        "성북구",
        "강북구",
        "도봉구",
        "노원구",
        "은평구",
        "서대문구",
        "마포구",
        "양천구",
        "강서구",
        "구로구",
        "금천구",
        "영등포구",
        "동작구",
        "관악구",
        "서초구",
        "강남구",
        "송파구",
        "강동구",
      ],
    };
  },
  created() {
    listPark(
      ({ data }) => {
        this.parktotal_list = data;
        if (this.parkmarkerPositions.length > 0) {
          this.parkmarkerPositions = [];
        }
        this.parktotal_list.forEach((item) => {
          let marker = {
            title: item.name,
            latlng: new kakao.maps.LatLng(item.latitude, item.longitude),
          };
          this.parkPositions.push(marker);
        });
      },
      (error) => {
        //alert(error);
      }
    );
    listLand(
      ({ data }) => {
        this.landtotal_list = data;
        if (this.landmarkerPositions.length > 0) {
          this.landmarkerPositions = [];
        }
        this.landtotal_list.forEach((item) => {
          let marker = {
            title: item.name,
            latlng: new kakao.maps.LatLng(item.latitude, item.longitude),
          };
          this.landmarkerPositions.push(marker);
        });
      },
      (error) => {
        //alert(error);
      }
    );
    listBike(
      ({ data }) => {
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
      }
    );
  },

  mounted() {
    /*global kakao*/
    if (!window.kakao || !window.kakao.maps) {
      const script = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=ebb1819968721206bc3d685fe4d060a9";
      script.addEventListener("load", () => {
        kakao.maps.load(this.initMap);
      });
      document.head.appendChild(script);
    } else {
      this.initMap();
    }
  },
  methods: {
    guSelect() {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
        this.overlays.forEach((overlay) => overlay.setMap(null));
      }
      this.markerPositions = [];

      console.log(this.total_list[0]);
      this.total_list.forEach((item) => {
        const arr = item.gugunRaw.split(" ");
        if (arr[1] == this.gugunCode) {
          let marker = {
            title: item.description,
            latlng: new kakao.maps.LatLng(item.latitude, item.longitude),
          };
          this.markerPositions.push(marker);
        }
      });

      // park
      if (this.parkmarkers.length > 0) {
        this.parkmarkers.forEach((marker) => marker.setMap(null));
        this.parkmarkers.forEach((overlay) => overlay.setMap(null));
      }

      this.parkPositions = [];
      this.parktotal_list.forEach((item) => {
        if (item.region == this.gugunCode) {
          let marker = {
            title: item.description,
            latlng: new kakao.maps.LatLng(item.latitude, item.longitude),
          };
          this.parkPositions.push(marker);
        }
      });

      for (let index = 0; index < this.markerPositions.length; index++) {
        if (this.markerPositions[index].title) {
          var moveLatLon = new kakao.maps.LatLng(
            this.markerPositions[index].latlng.Ma,
            this.markerPositions[index].latlng.La
          );
          this.map.setCenter(moveLatLon);
          break;
        }
      }

      this.displayMarkers(this.markerPositions);
    },
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.566345, 126.977893, 16),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
    },
    displayMarkers(markerPositions) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
        this.overlays.forEach((overlay) => overlay.setMap(null));
      }
      var imageSrc =
        "https://user-images.githubusercontent.com/73810834/203699310-69c1bbfb-312f-49e7-96c6-1de987c01591.png";
      for (var i = 0; i < markerPositions.length; i++) {
        if (markerPositions[i].title) {
          // 마커 이미지의 이미지 크기 입니다
          var imageSize = new kakao.maps.Size(24, 35);

          // 마커 이미지를 생성합니다
          var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

          // 마커를 생성합니다
          var marker = new kakao.maps.Marker({
            map: this.map, // 마커를 표시할 지도
            position: markerPositions[i].latlng, // 마커를 표시할 위치
            title: markerPositions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image: markerImage, // 마커 이미지
          });
          var content =
            '<div class="customoverlay">' +
            `  <a href="https://map.kakao.com/?q=${markerPositions[i].title}" target="_blank">` +
            `    <span class="title">${markerPositions[i].title}</span>` +
            "</div>";
          const overlay = new kakao.maps.CustomOverlay({
            map: this.map,
            yAnchor: 0,
            position: markerPositions[i].latlng,
            content: content,
          });

          this.overlays.push(overlay);

          this.markers.push(marker);
        }
      }
    }, // displayMarkers

    ///////////////////////////////////////////////////////////////////////// 여기부터 lAND

    landdisplayMarkers(landmarkerPositions) {
      if (this.landmarkers.length > 0) {
        this.landmarkers.forEach((marker) => marker.setMap(null));
        this.landoverlays.forEach((overlay) => overlay.setMap(null));
      }
      var imageSrc =
        "https://user-images.githubusercontent.com/73810834/203720241-c491243b-114d-48f8-af08-accf03b11a8f.png";
      for (var i = 0; i < landmarkerPositions.length; i++) {
        if (landmarkerPositions[i].title) {
          // 마커 이미지의 이미지 크기 입니다
          var imageSize = new kakao.maps.Size(24, 35);

          // 마커 이미지를 생성합니다
          var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

          // 마커를 생성합니다
          var marker = new kakao.maps.Marker({
            map: this.map, // 마커를 표시할 지도
            position: landmarkerPositions[i].latlng, // 마커를 표시할 위치
            title: landmarkerPositions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image: markerImage, // 마커 이미지
          });
          var content =
            '<div class="customoverlay">' +
            `  <a href="https://map.kakao.com/?q=${landmarkerPositions[i].title}" target="_blank">` +
            `    <span class="title">${landmarkerPositions[i].title}</span>` +
            "</div>";
          const overlay = new kakao.maps.CustomOverlay({
            map: this.map,
            yAnchor: 0,
            position: landmarkerPositions[i].latlng,
            content: content,
          });

          this.landoverlays.push(overlay);

          this.landmarkers.push(marker);
        }
      }
    }, // landdisplayMarkers

    parkdisplayMarkers(parkmarkerPositions) {
      if (this.parkmarkers.length > 0) {
        this.parkmarkers.forEach((marker) => marker.setMap(null));
        this.parkoverlays.forEach((overlay) => overlay.setMap(null));
      }
      var imageSrc =
        "https://user-images.githubusercontent.com/73810834/203699310-69c1bbfb-312f-49e7-96c6-1de987c01591.png";
      for (var i = 0; i < parkmarkerPositions.length; i++) {
        if (parkmarkerPositions[i].title) {
          // 마커 이미지의 이미지 크기 입니다
          var imageSize = new kakao.maps.Size(24, 35);

          // 마커 이미지를 생성합니다
          var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

          // 마커를 생성합니다
          var marker = new kakao.maps.Marker({
            map: this.map, // 마커를 표시할 지도
            position: parkmarkerPositions[i].latlng, // 마커를 표시할 위치
            title: parkmarkerPositions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image: markerImage, // 마커 이미지
          });
          var content =
            '<div class="customoverlay">' +
            `  <a href="https://map.kakao.com/?q=${parkmarkerPositions[i].title}" target="_blank">` +
            `    <span class="title">${parkmarkerPositions[i].title}</span>` +
            "</div>";
          const overlay = new kakao.maps.CustomOverlay({
            map: this.map,
            yAnchor: 0,
            position: parkmarkerPositions[i].latlng,
            content: content,
          });

          this.overlays.push(overlay);

          this.markers.push(marker);
        }
      }
    },

    showland() {
      this.landdisplayMarkers(this.landmarkerPositions);
    },
    showpark() {
      this.parkdisplayMarkers(this.parkmarkerPositions);
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
#map {
  width: 922px;
  height: 600px;
}
.customoverlay {
  position: relative;
  bottom: 85px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
}
.customoverlay:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.customoverlay a {
  display: block;
  text-decoration: none;
  color: #000;
  text-align: center;
  border-radius: 6px;
  font-size: 14px;
  font-weight: bold;
  overflow: hidden;
  background: rgb(28, 30, 66);
  background: rgb(2, 128, 2)
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right
    14px center;
}

.customoverlay .title {
  display: block;
  text-align: center;
  background: rgb(3, 3, 3);
  margin-right: 35px;
  padding: 10px 15px;
  font-size: 14px;
  font-weight: bold;
}
.customoverlay:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: -12px;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
</style>

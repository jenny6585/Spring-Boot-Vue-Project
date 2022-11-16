# 서울 따릉이 여행 설계서
                                서울 19반 10조
                                장수경 안려환

1. 요구사항 분석서
<table class="tg">
<thead>
  <tr>
    <th class="tg-c3ow">순번</th>
    <th class="tg-c3ow">요구사항명</th>
    <th class="tg-c3ow">요구사항 상세</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td class="tg-c3ow" colspan="3" style="border-color:inherit;text-align:center;vertical-align:top">기능적 요구사항</td>
  </tr>
  <tr>
    <td class="tg-c3ow">F01</td>
    <td class="tg-c3ow">따릉이 대여소 정보 수집</td>
    <td class="tg-c3ow">공공데이터 포털: 따릉이 데이터 가공 후 DB저장</td>
  </tr>
  <tr>
    <td class="tg-c3ow">F02</td>
    <td class="tg-c3ow">공원 정보 수집</td>
    <td class="tg-c3ow">공공데이터 포털: 
서울시 주요 공원현황 데이터 가공 후 DB저장</td>
  </tr>
  <tr>
    <td class="tg-c3ow">F03</td>
    <td class="tg-c3ow">맛집 정보 수집</td>
    <td class="tg-c3ow">공공데이터 포털: 서울시 관광 맛집 데이터 가공 후 DB저장</td>
  </tr>
  <tr>
    <td class="tg-c3ow">F04</td>
    <td class="tg-c3ow">관광 명소 정보 수집</td>
    <td class="tg-c3ow">공공데이터 포털: 서울시 관광 명소 데이터 가공 후 DB저장</td>
  </tr>
  <tr>
    <td class="tg-c3ow">F05</td>
    <td class="tg-c3ow">대여소 검색</td>
    <td class="tg-c3ow">구/동별 따릉이 대여소 검색 결과 데이터 제공</td>
  </tr>
  <tr>
    <td class="tg-c3ow">F06</td>
    <td class="tg-c3ow">관심 지역 상세</td>
    <td class="tg-c3ow">지도에 표시된 마커로 관련 정보 조회 및 관심지역 추가기능</td>
  </tr>
  <tr>
    <td class="tg-c3ow">F07</td>
    <td class="tg-c3ow">게시판 글 추가/수정/삭제</td>
    <td class="tg-c3ow">게시판 글쓰기 및 수정, 삭제와 첨부파일 업로드</td>
  </tr>
  <tr>
    <td class="tg-c3ow">F08</td>
    <td class="tg-c3ow">게시글 좋아요 기능</td>
    <td class="tg-c3ow">회원의 게시글 좋아요 DB 적용</td>
  </tr>
  <tr>
    <td class="tg-c3ow">F09</td>
    <td class="tg-c3ow">게시글 댓글기능</td>
    <td class="tg-c3ow">게시글 댓글 작성/수정/삭제 기능</td>
  </tr>
  <tr>
    <td class="tg-c3ow">F10</td>
    <td class="tg-c3ow">회원가입기능</td>
    <td class="tg-c3ow">회원 정보를 입력받아 유효성 검사 후 DB저장</td>
  </tr>
  <tr>
    <td class="tg-c3ow">F11</td>
    <td class="tg-c3ow">회원 정보 암호화기능</td>
    <td class="tg-c3ow">sha-256 aes 를 사용한 암호화</td>
  </tr>
  <tr>
    <td class="tg-c3ow">F12</td>
    <td class="tg-c3ow">로그인/로그아웃</td>
    <td class="tg-c3ow">DB 확인하여 유효성 검사 후 로그인 기능</td>
  </tr>
  <tr>
    <td class="tg-c3ow">F13</td>
    <td class="tg-c3ow">회원 정보수정/탈퇴</td>
    <td class="tg-c3ow">식별된 사용자의 정보를 수정 혹은 탈퇴</td>
  </tr>
  <tr>
    <td class="tg-c3ow">F14</td>
    <td class="tg-c3ow">회원 관심정보 삭제</td>
    <td class="tg-c3ow">favorite 탭에서 사용자의 관심 지역 삭제</td>
  </tr>
  
  <tr>
    <td class="tg-c3ow" colspan="3" style="border-color:inherit;text-align:center;vertical-align:top">비기능적 요구사항</td>
  </tr>
  <tr>
    <td class="tg-c3ow">NF01</td>
    <td class="tg-c3ow">공공데이터의 유효성</td>
    <td class="tg-c3ow">사용자가 요구하는 데이터를 제공하여야 한다.</td>
  </tr>
  <tr>
    <td class="tg-c3ow">NF02</td>
    <td class="tg-c3ow">응답성</td>
    <td class="tg-c3ow">사용자가 불편함을 느끼지 않는 빠른 응답성이 필요하다.</td>
  </tr>
  <tr>
    <td class="tg-c3ow">NF03</td>
    <td class="tg-c3ow">사용자 편의성</td>
    <td class="tg-c3ow">직관적인 구성으로 모든 사용자가 어려움 없이 사용할 수 있어야 한다.</td>
  </tr>
  <tr>
    <td class="tg-c3ow">NF04</td>
    <td class="tg-c3ow">가용성</td>
    <td class="tg-c3ow">모든 환경에서 작동이 되어야 한다.</td>
  </tr>
  <tr>
    <td class="tg-c3ow">NF05</td>
    <td class="tg-c3ow">보안성</td>
    <td class="tg-c3ow">사용자의 민감한 정보가 탈취되어서는 안된다.</td>
  </tr>
  <tr>
    <td class="tg-c3ow">NF05</td>
    <td class="tg-c3ow">MSA</td>
    <td class="tg-c3ow">REST API 적용</td>
  </tr>
</tbody>
</table>


2. 요구사항 정의서 (Usecase Diagram)
![11111](https://user-images.githubusercontent.com/73810834/202123150-95fed187-c1ef-4c3d-82c8-e9af9cc76fc6.PNG)


3. 테이블 구조도(ERD)
![222222](https://user-images.githubusercontent.com/73810834/202126495-a63a1b51-d3ba-4c7b-990b-93b5261ccba1.png)

4. 프로젝트 시나리오


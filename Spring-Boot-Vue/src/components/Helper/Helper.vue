    <template>
  <div :class="{ 'theme-helper': true, 'theme-helper-opened': opened }">
    <section class="widget theme-helper-content">
      <header class="theme-helper-header d-flex p-0">
        <div class="theme-helper-toggler" @click="toggle">
          <div class="theme-helper-spinner text-white">
            <i class="la la-cog"></i>
            <i class="la la-cog"></i>
          </div>
        </div>
        <h6>다른 웹사이트 지도</h6>
      </header>
      <div class="widget-body mt-3">
        <div class="mt-4">
          <a
            href="https://map.kakao.com/"
            target="_blank"
            role="button"
            class="btn btn-warning btn-rounded-f btn-block fs-mini text-white"
          >
            카카오 맵
          </a>
          <a
            href="https://www.google.co.kr/maps/?hl=ko"
            target="_blank"
            role="button"
            class="btn btn-danger btn-rounded-f btn-block fs-mini text-white"
          >
            구글 맵
          </a>
          <a
            href="https://map.naver.com/v5/?c=14138676.5922042,4511130.9846631,15,0,0,0,dh"
            target="_blank"
            role="button"
            class="btn btn-inverse btn-rounded-f btn-block fs-mini"
          >
            네이버 지도
          </a>
        </div>
      </div>
    </section>
  </div>
</template>

    <script>
export default {
  name: "Helper",
  data() {
    return {
      opened: false,
    };
  },
  methods: {
    toggle() {
      this.opened = !this.opened;
    },
  },
};
</script>

    <style src="./Helper.scss" lang="scss" scoped />

<template>
  <div class="tables-basic">
    <b-row>
      <b-col>
        <Widget title="<h5>맛집추천 <span class='fw-semi-bold'>게시판</span></h5>" customHeader>
          <div class="table-resposive">
           <b-table striped hover :items="articles" :fields="fields" @row-clicked="viewArticle">
          <template #cell(subject)="data">
            <router-link :to="{ name: 'boardview', params: { articleno: data.item.articleno } }">
              {{ data.item.subject }}
            </router-link>
          </template>
        </b-table>
          </div>
          <div class="clearfix">
            <div class="float-right">
              <b-button variant="default" class="mr-3" size="sm" @click="moveWrite">
                작성하기</b-button>
            </div>
            <p>부적절한 내용의 게시글은 사전 통보 없이 삭제될 수 있습니다.</p>
          </div>
        </Widget>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import { listArticle } from "@/api/board";
import Vue from "vue";
import Widget from "@/components/Widget/Widget";
import Sparklines from "../../components/Sparklines/Sparklines";

export default {
  name: "BoardList",
  components: { Widget, Sparklines },
  data() {
    return {
      articles: [],
      fields: [
        { key: "articleno", label: "글번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "id", label: "작성자", tdClass: "tdClass" },
        { key: "regtime", label: "작성일", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
      ],
    };
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    listArticle(
      param,
      ({ data }) => {
        this.articles = data;
      },
      (error) => {
        alert(error);
      }
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardwrite" });
    },
    viewArticle(article) {
      this.$router.push({
        name: "boardview",
        params: { articleno: article.articleno },
      });
    },
  },
};
</script>

<style src="./Boards.scss" lang="scss" scoped />

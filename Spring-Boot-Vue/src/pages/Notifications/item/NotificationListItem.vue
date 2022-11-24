<template>
  <b-tr>
    <b-td>{{ notificationno }}</b-td>
    <b-th class="text-left">
      <router-link
        :to="{
          name: 'notificationsview',
          params: { notificationno: notificationno },
        }"
        >{{ subject }}</router-link
      >
    </b-th>
    <b-td>{{ hit }}</b-td>
    <b-td>{{ id }}</b-td>
    <b-td>{{ regtime | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "NotificationListItem",
  props: {
    notificationno: Number,
    id: String,
    subject: String,
    hit: Number,
    regtime: String,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
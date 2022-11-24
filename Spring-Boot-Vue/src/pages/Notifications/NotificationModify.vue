<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글수정</h3></b-alert>
      </b-col>
    </b-row>
    <notification-input-item type="modify" />
  </b-container>
</template>

<script>
import NotificationInputItem from "@/pages/Notifications/item/NotificationInputItem";

export default {
  name: "notificationsmodify",
  components: {
    NotificationInputItem,
  },
};
</script>

<style></style>

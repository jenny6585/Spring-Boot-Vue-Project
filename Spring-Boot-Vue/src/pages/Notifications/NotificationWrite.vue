<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글작성</h3></b-alert>
      </b-col>
    </b-row>
    <notification-input-item type="register" />
  </b-container>
</template>

<script>
import NotificationInputItem from "@/pages/Notifications/item/NotificationInputItem";

export default {
  name: "notificationswrite",
  components: {
    NotificationInputItem,
  },
};
</script>

<style></style>

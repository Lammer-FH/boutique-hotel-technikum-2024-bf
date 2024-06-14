<template>
  <ion-datetime-button v-model="date" datetime="datetime"></ion-datetime-button>

  <ion-modal :keep-contents-mounted="true">
    <ion-datetime id="datetime"></ion-datetime>
  </ion-modal>
</template>

<script setup lang="ts">
  import { IonDatetime, IonDatetimeButton, IonModal } from '@ionic/vue';
  import { ref, watch } from 'vue';

  const date = ref();

  watch(date, (date) => console.log(date));
</script>

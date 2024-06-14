<template>
    <ion-page>
        <ion-header :translucent="true">
            <ion-toolbar>
                <ion-title>Room</ion-title>
            </ion-toolbar>
        </ion-header>
        <ion-content :fullscreen="true">
            <RoomDetail />
        </ion-content>
    </ion-page>
</template>

<script setup lang="ts">
    import RoomDetail from '../components/room-detail/RoomDetail.vue';
    import { IonContent, IonPage, IonHeader, IonTitle, IonToolbar } from '@ionic/vue';
</script>

<style scoped>
    ion-toolbar {
      --opacity: 0.5;
      backdrop-filter: blur(10px);
    }
</style>

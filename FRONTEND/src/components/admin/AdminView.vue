<template>
    <ion-page>
        <ion-header :translucent="true">
            <ion-toolbar>
                <ion-buttons slot="start">
                    <BackButton />
                </ion-buttons>
                <ion-title>Admin Area</ion-title>
            </ion-toolbar>
        </ion-header>
        <ion-content :fullscreen="true">
            <div class="controls">
                <AddRoomButton />
            </div>
            <RoomList :isAdmin="true" />
        </ion-content>
    </ion-page>
</template>

<script setup lang="ts">
    import BackButton from '../shared/BackButton.vue';
    import AddRoomButton from './AddRoomButton.vue';
    import RoomList from '../shared/room-list/RoomList.vue';
    import { IonContent, IonPage, IonHeader, IonTitle, IonToolbar, IonButtons } from '@ionic/vue';
</script>

<style scoped>
    ion-toolbar {
      --opacity: 0.5;
      backdrop-filter: blur(10px);
    }
    .controls {
        width: 100%;
        display: inline-flex;
        justify-content: center;
    }
</style>

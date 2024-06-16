<template>
    <ion-page>
        <ion-header :translucent="true">
            <ion-toolbar>
                <ion-title>Home</ion-title>
                <ion-buttons slot="end">
                    <AdminButton />
                    <RegisterButton />
                </ion-buttons>
            </ion-toolbar>
        </ion-header>
        <ion-content :fullscreen="true">
            <RoomList :isAdmin="false" />
        </ion-content>
    </ion-page>
</template>

<script setup lang="ts">
    import RoomList from '../shared/room-list/RoomList.vue'
    import AdminButton from './AdminButton.vue';
    import RegisterButton from './RegisterButton.vue';
    import { IonContent, IonPage, IonHeader, IonTitle, IonToolbar, IonButtons } from '@ionic/vue';
</script>

<style scoped>
    ion-toolbar {
      --opacity: 0.5;
      backdrop-filter: blur(10px);
    }
</style>

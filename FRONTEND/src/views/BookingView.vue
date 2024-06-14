<template>
    <ion-page>
        <ion-content :fullscreen="true">
            <RoomDetail />
        </ion-content>
    </ion-page>
</template>

<script setup lang="ts">
    import RoomDetail from '../components/room-detail/RoomDetail.vue';
    import { IonContent, IonPage } from '@ionic/vue';
</script>

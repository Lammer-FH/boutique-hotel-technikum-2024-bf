<template>
    <ion-page>
        <ion-header :translucent="true">
            <ion-toolbar>
                <ion-title>Home</ion-title>
                <ion-buttons slot="end">
                    <RegisterButton />
                </ion-buttons>
            </ion-toolbar>
        </ion-header>
        <ion-content :fullscreen="true">
            <div class="content">
                <RoomList :isAdmin="false" />
            </div>
        </ion-content>
    </ion-page>
</template>

<script setup lang="ts">
    import RoomList from '@/components/shared/room-list/RoomList.vue'
    import RegisterButton from '@/components/home/RegisterButton.vue';
    import { IonContent, IonPage, IonHeader, IonTitle, IonToolbar, IonButtons } from '@ionic/vue';
</script>

<style scoped>
    ion-toolbar {
      --opacity: 0.5;
      backdrop-filter: blur(10px);
    }

    .content {
        max-width: var(--max-content-width);
        margin-inline: auto;
    }
</style>

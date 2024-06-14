<template>
    <ion-page>
        <ion-header :translucent="true">
            <ion-toolbar>
                <ion-title>Book</ion-title>
            </ion-toolbar>
        </ion-header>
        <ion-content :fullscreen="true">
            <BookPage />
        </ion-content>
    </ion-page>
</template>

<script setup lang="ts">
    import BookPage from '../components/book/BookPage.vue';
    import { IonContent, IonPage, IonHeader, IonTitle, IonToolbar } from '@ionic/vue';
</script>

<style scoped>
    ion-toolbar {
      --opacity: 0.5;
      backdrop-filter: blur(10px);
    }
</style>

<template>
    <ion-app>
        <ion-header :translucent="true">
            <ion-toolbar>
                <ion-title>Boutique Hotel Group BF</ion-title>
            </ion-toolbar>
        </ion-header>
        <ion-router-outlet />
    </ion-app>
</template>

<script setup lang="ts">
    import { IonApp, IonRouterOutlet, IonHeader, IonTitle, IonToolbar } from '@ionic/vue';
</script>

<style scoped>
    ion-toolbar {
      --opacity: 0.5;
      backdrop-filter: blur(10px);
    }
</style>

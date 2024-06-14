<template>
    <ion-button @click="router.back()" fill="clear">
        <ion-icon :icon="chevronBackOutline"></ion-icon>
    </ion-button>
</template>

<script setup lang="ts">
    import { IonButton, IonIcon } from '@ionic/vue';
    import { chevronBackOutline } from 'ionicons/icons';
    import { useRouter } from 'vue-router';

    const router = useRouter();
</script>

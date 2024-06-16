<template>
    <ion-button @click="router.push('/admin')">
        <ion-icon :icon="cogOutline"></ion-icon>
    </ion-button>
</template>

<script setup lang="ts">
    import { IonButton, IonIcon } from '@ionic/vue';
    import { cogOutline } from 'ionicons/icons';
    import { useRouter } from 'vue-router';

    const router = useRouter();
</script>

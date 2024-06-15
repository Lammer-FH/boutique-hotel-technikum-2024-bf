<template>
    <ion-spinner v-if="loading" />
    <ion-icon v-else :icon="value ? checkmarkCircleOutline : closeCircleOutline" :color="value ? 'success' : 'danger'"></ion-icon>
</template>

<script setup lang="ts">
    import { IonIcon, IonSpinner } from '@ionic/vue';
    import { checkmarkCircleOutline, closeCircleOutline } from 'ionicons/icons';

    defineProps<{
        value: boolean;
        loading?: boolean;
    }>();
</script>

<style scoped>
    ion-spinner, ion-icon {
        width: 35px;
        height: 35px;
    }
</style>

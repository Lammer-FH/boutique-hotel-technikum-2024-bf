<template>
    <div class="page-controls">
        <ion-button @click="emit('prev')" :disabled="isFirstPage" fill="clear">
            <ion-icon :icon="chevronBackOutline"></ion-icon>
        </ion-button>
        <div>{{ currentPage + 1 }}</div>
        <ion-button @click="emit('next')" :disabled="isLastPage" fill="clear">
            <ion-icon :icon="chevronForwardOutline"></ion-icon>
        </ion-button>
    </div>
</template>

<script setup lang="ts">
    import { IonButton, IonIcon } from '@ionic/vue';
    import { chevronBackOutline, chevronForwardOutline } from 'ionicons/icons';
    import { defineProps } from 'vue';

    defineProps<{
        currentPage: number;
        isFirstPage: boolean;
        isLastPage: boolean;
    }>();

    const emit = defineEmits(['prev', 'next'])
</script>

<style scoped>
    .page-controls {
        width: 70%;
        display: inline-flex;
        justify-content: space-between;
        align-items: center;
        margin: 0 15%;
    }
</style>

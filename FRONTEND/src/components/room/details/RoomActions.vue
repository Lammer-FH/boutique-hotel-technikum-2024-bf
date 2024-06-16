<template>
    <div class="room-actions">
        <ion-button @click="emit('click')">
            Jetzt buchen
            <ion-icon slot="end" :icon="bedOutline"></ion-icon>
        </ion-button>
    </div>
</template>

<script setup lang="ts">
    import { IonButton, IonIcon } from '@ionic/vue';
    import { bedOutline } from 'ionicons/icons';

    const emit = defineEmits(['click'])

    defineProps<{
        roomId: number;
    }>();
</script>

<style scoped>
    .room-actions {
        width: 100%;
        display: inline-flex;
        justify-content: center;
    }

</style>

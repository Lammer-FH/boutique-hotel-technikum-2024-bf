<template>
    <ion-card>
        <ion-card-content>
            Von: <DatePicker />
        </ion-card-content>
    </ion-card>
</template>

<script setup lang="ts">
    import { IonCard, IonCardContent } from '@ionic/vue';
    import DatePicker from './DatePicker.vue';
    import { defineProps, onMounted } from 'vue';
    import { useRoomStore } from '../../stores/room';

    defineProps<{
        roomId: string
    }>()

    const roomStore = useRoomStore();

    onMounted(async () => {
        await roomStore.fetchRoom(Number(roomId));
    });
</script>

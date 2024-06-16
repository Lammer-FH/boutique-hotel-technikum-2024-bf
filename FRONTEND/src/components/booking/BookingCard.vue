<template>
    <ion-card>
        <img src="/img/hotel-room.jpg" alt="Room" style="width: 100%; height: 279.5px;" />
        <ion-card-header>
            <ion-card-title>
                {{ roomStore.room?.title }}
            </ion-card-title>
            <ion-card-subtitle>
                {{ date }}
            </ion-card-subtitle>
        </ion-card-header>
        <ion-card-content>
            {{ roomStore.room?.description }}
        </ion-card-content>
        <ion-button fill="clear" @click="editBooking">
            Bearbeiten
        </ion-button>
    </ion-card>
</template>

<script setup lang="ts">
    import { Booking } from '@/types/Booking';
    import {
        IonButton,
        IonCard,
        IonCardHeader,
        IonCardSubtitle,
        IonCardTitle,
        IonCardContent
    } from '@ionic/vue';
    import { useRoomStore } from '@/stores/room';
    import { computed, onMounted } from 'vue';

    const roomStore = useRoomStore();
    const props = defineProps<{
        booking: Booking;
    }>()

    const date = computed(() => {
        return `${new Date(props.booking.startDate).toLocaleDateString()} - ${new Date(props.booking.endDate).toLocaleDateString()}`;
    });

    // const emit = defineEmits(['delete'])

    onMounted(async () => {
        await roomStore.fetchRoom(props.booking.roomId);
    });

    function editBooking() {
        console.log('editBooking');
        throw new Error('Method not implemented.');
    }
</script>

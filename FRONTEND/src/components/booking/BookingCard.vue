<template>
    <ion-card>
        <img src="/img/hotel-room.jpg" alt="Room" style="width: 100%; height: 279.5px;" />
        <ion-card-header>
            <ion-card-title>
                {{ room?.title }}
            </ion-card-title>
            <ion-card-subtitle>
                {{ date }}
            </ion-card-subtitle>
        </ion-card-header>
        <ion-card-content>
            {{ room?.description }}
        </ion-card-content>
        <ion-button fill="clear" @click="editBooking">
            Bearbeiten
        </ion-button>
        <ion-button fill="clear" @click="showConfirmation">
            Bestätigung Anzeigen
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
    import { computed, onMounted, ref } from 'vue';
    import { useEditBookingStore } from '@/stores/edit-booking';
    import { useRouter } from 'vue-router';
    import * as roomService from '@/services/RoomService';
    import { Room } from '@/types/Room';

    const router = useRouter();

    const editBookingStore = useEditBookingStore();
    const room = ref<Room | null>(null);
    const props = defineProps<{
        booking: Booking;
    }>()

    const date = computed(() => {
        return `${new Date(props.booking.startDate).toLocaleDateString()} - ${new Date(props.booking.endDate).toLocaleDateString()}`;
    });

    const emit = defineEmits(['edit'])

    onMounted(async () => {
        room.value = await roomService.fetchRoomById(props.booking.roomId);
    });

    function editBooking() {
        editBookingStore.initBooking(props.booking);
        emit('edit', props.booking);
    }

    function showConfirmation() {
        router.push(`/confirmation/${props.booking.id}`);
    }

</script>

<style scoped>
    ion-card {
        width: var(--card-width);
    }
    img {
        object-fit: cover;
    }
</style>

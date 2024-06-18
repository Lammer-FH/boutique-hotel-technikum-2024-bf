<template>
    <ion-card>
        <ion-card-content>
                <ion-text>
                    <p>Von: {{ startDate }}</p>
                    <p>Bis: {{ endDate }}</p>
                    <p>Preis: {{ bookingStore.totalPrice }} €</p>
                    <p>Extras: {{ room.extra }}</p>
                </ion-text>
        </ion-card-content>
    </ion-card>
</template>

<script setup lang="ts">
    import { IonCard, IonCardContent, IonText } from '@ionic/vue';
    import { onMounted, watch, toRefs } from 'vue';
    import { useBookingStore } from '@/stores/booking';

    const props = defineProps<{
        room: Room;
    }>();

    const bookingStore = useBookingStore();

    const { 
        startDate, 
        endDate, 
    } = toRefs(bookingStore);

    watch(() => [startDate.value, endDate.value], async () => {
        await bookingStore.fetchAvailability(props.room.id);
    });

    onMounted(async () => {
        await bookingStore.fetchAvailability(props.room.id);
    });
</script>

<style scoped>
    ion-card-content {
        display: flex;
        flex-direction: column;
        gap: 1.5em;
    }
    .date-container {
        display: inline-flex;
        justify-content: space-evenly;
        align-items: center;
        width: 100%;
    }
    .centered-container {
        width: 100%;
        display: inline-flex;
        justify-content: center;
    }
</style>

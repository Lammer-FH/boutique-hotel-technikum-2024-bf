<template>
    <ion-card>
        <ion-card-content>
                <ion-text>
                    <h1>{{ room.title }}</h1>
                    <p>Von: {{ startDateString }}</p>
                    <p>Bis: {{ endDateString }}</p>
                    <p>Preis: {{ bookingStore.totalPrice }} €</p>
                    <p>Extras: {{ room.extra }}</p>
                </ion-text>
        </ion-card-content>
    </ion-card>
</template>

<script setup lang="ts">
    import { IonCard, IonCardContent, IonText } from '@ionic/vue';
    import { toRefs } from 'vue';
    import { useBookingStore } from '@/stores/booking';

    defineProps<{
        room: Room;
    }>();

    const bookingStore = useBookingStore();

    const { 
        startDateString, 
        endDateString, 
    } = toRefs(bookingStore);

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

<template>
    <ion-card>
        <ion-card-content>
                <ion-text>
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
    import { computed, toRefs } from 'vue';
    import { useBookingStore } from '@/stores/booking';

    defineProps<{
        room: Room;
    }>();

    const bookingStore = useBookingStore();

    const { 
        startDate, 
        endDate, 
    } = toRefs(bookingStore);

    const startDateString = computed(() => formatDate(startDate.value));
    const endDateString = computed(() => formatDate(endDate.value));

    function formatDate(date: Date) {
        const year = date.getFullYear();
        const month = date.getMonth() + 1;
        const day = date.getDate();
        return `${day}.${pad(month)}.${pad(year)}`;
    }

    function pad(value: number) {
        return String(value).padStart(2, '0');
}
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

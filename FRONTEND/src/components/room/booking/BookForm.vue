<template>
    <ion-card>
        <ion-card-content>
            <div class="date-container">
                <ion-label>Von</ion-label>
                <DatePicker v-model="startDate" :min="minStartDate" id="startDate" />
            </div>
            <div class="date-container">
                <ion-label>Bis</ion-label>
                <DatePicker v-model="endDate" :min="minEndDate" id="endDate" />
            </div>
            <div class="status-icon">
                <StatusIcon :value="isAvailable" :loading="isLoading" />
            </div>
        </ion-card-content>
    </ion-card>
</template>

<script setup lang="ts">
    import { IonCard, IonCardContent, IonLabel } from '@ionic/vue';
    import DatePicker from './DatePicker.vue';
    import StatusIcon from './StatusIcon.vue';
    import { onMounted, watch, toRefs } from 'vue';
    import { useBookingStore } from '@/stores/booking';

    const props = defineProps<{
        room: Room;
    }>();

    const bookingStore = useBookingStore();

    const { 
        isAvailable, 
        isLoading, 
        startDate, 
        endDate, 
        minStartDate, 
        minEndDate, 
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
    .status-icon {
        width: 100%;
        display: inline-flex;
        justify-content: center;
    }
</style>

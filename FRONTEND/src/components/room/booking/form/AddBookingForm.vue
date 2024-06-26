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
            <div class="centered-container">
                <StatusIcon :value="isAvailable" :loading="isLoading" />
            </div>
            <div v-if="!isRegistered" class="centered-container">
                <ion-text color="danger">
                    <p>User muss registriert sein</p>
                </ion-text>
            </div>
        </ion-card-content>
    </ion-card>
</template>

<script setup lang="ts">
    import { IonCard, IonCardContent, IonLabel, IonText } from '@ionic/vue';
    import DatePicker from '@/components/shared/fields/DatePicker.vue';
    import StatusIcon from '@/components/shared/fields/StatusIcon.vue';
    import { onMounted, watch, toRefs } from 'vue';
    import { useBookingStore } from '@/stores/booking';

    const props = defineProps<{
        room: Room;
        isRegistered: boolean;
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
    .centered-container {
        width: 100%;
        display: inline-flex;
        justify-content: center;
    }
</style>

import { defineStore } from 'pinia';
import { ref, computed } from 'vue';
import * as bookingService from '@/services/BookingService';

const ONE_DAY = 24 * 60 * 60 * 1000;

export const useBookingStore = defineStore('booking', () => {
    const isAvailable = ref(false);
    const isLoading = ref(true);

    const startDate = ref<Date>(new Date());
    const endDateHelper = ref<Date>(addDays(startDate.value, 1));
    const minStartDate = ref<Date>(new Date());
    const minEndDate = computed(() => new Date(startDate.value.valueOf() + ONE_DAY));
    const endDate = computed({
        get: () => endDateHelper.value.valueOf() < minEndDate.value.valueOf()
            ? minEndDate.value
            : endDateHelper.value,
        set: value => endDateHelper.value = value
    });

    async function fetchAvailability(roomId: number) {
        isLoading.value = true;
        try {
            isAvailable.value = await bookingService.fetchIsAvailable(roomId, startDate.value, endDate.value);
        }
        catch (error) {
            isAvailable.value = false;
        }
        isLoading.value = false;
    }

    function addDays(date: Date, days: number) {
        return new Date(date.valueOf() + days * ONE_DAY);
    }

    return {
        isAvailable,
        isLoading,
        startDate,
        endDate,
        minStartDate,
        minEndDate,
        fetchAvailability
    };
});

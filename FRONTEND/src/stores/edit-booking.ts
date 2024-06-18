import { defineStore } from 'pinia';
import { ref, computed } from 'vue';
import * as bookingService from '@/services/BookingService';
import { Booking } from '@/types/Booking';

const ONE_DAY = 24 * 60 * 60 * 1000;

export const useEditBookingStore = defineStore('edit-booking', () => {
    const bookingHelper = ref<Booking>({
        id: 0,
        breakfast: false,
        startDate: new Date(),
        endDate: new Date(),
        firstName: '',
        lastName: '',
        email: '',
        roomId: 0,
        userId: 0
    });

    const isAvailable = ref(false);
    const isLoading = ref(true);

    const booking = computed({
        get: () => ({
            ...bookingHelper.value,
            endDate: endDate.value,
        }),
        set: value => {
            bookingHelper.value = value;
            endDate.value = value?.endDate;
        }
    });

    const minStartDate = ref<Date>(new Date());
    const endDateHelper = ref<Date>(addDays(minStartDate.value, 1));
    const minEndDate = computed(() => addDays(bookingHelper.value.startDate, 1));
    const startDate = computed({
        get: () => bookingHelper.value.startDate,
        set: value => bookingHelper.value.startDate = value
    });
    const endDate = computed<Date>({
        get: () => endDateHelper.value.valueOf() < minEndDate.value.valueOf()
            ? minEndDate.value
            : endDateHelper.value,
        set: value => endDateHelper.value = value
    });

    async function fetchBooking(id: number) {
        try {
            booking.value = await bookingService.fetchBookingById(id);
        } catch (error) {
            console.error('error', error);
        }
    }

    function initBooking(data: Booking) {
        bookingHelper.value = {
            ...data,
            startDate: new Date(data.startDate),
            endDate: new Date(data.endDate),
        };

        endDateHelper.value = bookingHelper.value.endDate;
    }

    async function updateBooking(booking: Booking) {
        try {
            const createdBooking = await bookingService.updateBooking(booking);
            isAvailable.value = false;
            return createdBooking;
        }
        catch (error) {
            console.error('error', error);
            return Promise.reject(error);
        }
    }

    async function fetchAvailability() {
        isLoading.value = true;
        if (!booking.value) {
            isAvailable.value = false;
            return;
        }
        try {
            isAvailable.value = await bookingService.fetchIsAvailable(booking.value?.roomId, booking.value?.startDate, booking.value?.endDate);
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
        booking,
        fetchBooking,
        updateBooking,
        fetchAvailability,
        minStartDate,
        minEndDate,
        initBooking,
        isAvailable,
        isLoading,
        startDate,
        endDate,
    };
});

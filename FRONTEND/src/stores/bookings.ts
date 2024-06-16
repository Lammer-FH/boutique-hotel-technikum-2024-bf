import { defineStore } from 'pinia';
import { ref } from 'vue';
import { Booking } from '@/types/Booking';
import * as bookingService from '@/services/BookingService';

export const useBookingsStore = defineStore('bookings', () => {

    const bookings = ref<Booking[]>([]);

    async function fetchBookings() {
        try {
            bookings.value = await bookingService.fetchBookings();
        } catch (error) {
            console.error('error', error);
        }
    }

    return {
        bookings,
        fetchBookings,
    };
});

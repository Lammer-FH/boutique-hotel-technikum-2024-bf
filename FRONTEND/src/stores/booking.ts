import { defineStore } from 'pinia';
import { ref, computed } from 'vue';
import * as bookingService from '@/services/BookingService';
import { Booking } from '@/types/Booking';
import { User } from '@/types/User';

const ONE_DAY = 24 * 60 * 60 * 1000;

export const useBookingStore = defineStore('booking', () => {
    const bookingId = ref<number | undefined>(undefined);
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

    const startDateString = computed(() => formatDate(startDate.value));
    const endDateString = computed(() => formatDate(endDate.value));

    const roomPrice = ref<number>(0);
    const totalPrice = computed(() => roomPrice.value * (endDate.value.valueOf() - startDate.value.valueOf()) / ONE_DAY);

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

    async function fetchConfirmation(id: number): Promise<Booking | undefined> {
        if (!id)
            return;

        try {
            const result = await bookingService.fetchBookingById(id);
            startDate.value = new Date(result.startDate);
            endDate.value = new Date(result.endDate);

            return result;
        } catch (error) {
            console.error('error', error);
        }
    }

    async function createBooking(user: User, roomId: number) {
        const booking: Booking = {
            breakfast: true,
            startDate: startDate.value,
            endDate: endDate.value,
            firstName: user.firstName,
            lastName: user.lastName,
            email: user.email,
            roomId: roomId,
            userId: 1, // FIXME: This is a hack for now
        };
        try {
            const createdBooking = await bookingService.createBooking(booking);
            isAvailable.value = false;
            bookingId.value = createdBooking.id;
            return createdBooking;
        }
        catch (error) {
            console.error('error', error);
            return Promise.reject(error);
        }
    }

    function addDays(date: Date, days: number) {
        return new Date(date.valueOf() + days * ONE_DAY);
    }

    function formatDate(date: Date) {
        const year = date.getFullYear();
        const month = date.getMonth() + 1;
        const day = date.getDate();
        return `${day}.${pad(month)}.${pad(year)}`;
    }

    function pad(value: number) {
        return String(value).padStart(2, '0');
    }

    return {
        isAvailable,
        isLoading,
        startDate,
        endDate,
        minStartDate,
        minEndDate,
        startDateString,
        endDateString,
        fetchAvailability,
        createBooking,
        roomPrice,
        totalPrice,
        fetchConfirmation,
    };
});

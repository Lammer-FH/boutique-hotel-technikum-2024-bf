<template>
    <div v-if="bookingsStore.bookings.length === 0">
        <p>No bookings found</p>
    </div>
    <div v-else>
        <div v-for="booking in bookingsStore.bookings" :key="booking.id">
            <BookingCard :booking="booking" @edit="editBooking" />
        </div>
    </div>
</template>

<script setup lang="ts">
    import BookingCard from './BookingCard.vue';
    import { useBookingsStore } from '@/stores/bookings';
    import { onMounted, ref } from 'vue';

    const bookingsStore = useBookingsStore();
    const emit = defineEmits(['edit'])

    const isLoading = ref(true);

    onMounted(async () => {
        await bookingsStore.fetchBookings();
        isLoading.value = false;
    });

    function editBooking(booking: Booking) {
        emit('edit', booking);
    }
</script>

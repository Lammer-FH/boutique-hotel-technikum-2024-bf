<template>
    <ModalContainer v-model="isOpen" :confirm-disabled="!bookingStore.isAvailable || !userStore.isRegistered" @confirm="confirm" @close="cancel" :confirmLabel="confirmLabel" :cancelLabel="cancelLabel">
        <AddBookingForm v-if="showForm" :room="room" :isRegistered="userStore.isRegistered" />
        <BookingSummary v-else :room="room" />
    </ModalContainer>
</template>

<script setup lang="ts">
    import { Room } from '@/types/Room';
    import AddBookingForm from './form/AddBookingForm.vue';
    import ModalContainer from '@/components/shared/modal/ModalContainer.vue';
    import BookingSummary from './BookingSummary.vue';
    import { useBookingStore } from '@/stores/booking';
    import { useBookingsStore } from '@/stores/bookings';
    import { useUserStore } from '@/stores/user';
    import { computed, ref } from 'vue';
    import { useRouter } from 'vue-router';

    const router = useRouter();

    const isOpen = defineModel<boolean>();
    const showForm = ref(true);
    const confirmLabel = computed(() => showForm.value ? 'Confirm' : 'Jetzt Buchen');
    const cancelLabel = computed(() => showForm.value ? 'Abbrechen' : 'Bearbeiten');

    const bookingStore = useBookingStore();
    const userStore = useUserStore();
    const bookingsStore = useBookingsStore();

    const props = defineProps<{
        room: Room
    }>();

    bookingStore.roomPrice = props.room.price;

    async function confirm() {
        if (showForm.value) {
            showForm.value = false;
        } else {
            try {
                const booking = await bookingStore.createBooking(userStore.user, props.room.id);
                await bookingsStore.fetchBookings();
                isOpen.value = false;
                router.push(`/confirmation/${booking.id}`);
            } catch (error) {
                console.error('error', error);
            }
        }
    }

    function cancel() {
        if (!showForm.value) {
            showForm.value = true;
        } else {
            isOpen.value = false;
        }
    }

</script>

<style scoped>

</style>

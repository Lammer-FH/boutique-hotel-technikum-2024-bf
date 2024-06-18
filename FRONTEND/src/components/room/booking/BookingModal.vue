<template>
    <ModalContainer v-model="isOpen" :confirm-disabled="!bookingStore.isAvailable" @confirm="confirm" @close="cancel" :confirmLabel="confirmLabel" :cancelLabel="cancelLabel">
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
    import { useUserStore } from '@/stores/user';
    import { computed, ref } from 'vue';

    const isOpen = defineModel<boolean>();
    const showForm = ref(true);
    const confirmLabel = computed(() => showForm.value ? 'Confirm' : 'Jetzt Buchen');
    const cancelLabel = computed(() => showForm.value ? 'Abbrechen' : 'Bearbeiten');

    const bookingStore = useBookingStore();
    const userStore = useUserStore();

    const props = defineProps<{
        room: Room
    }>();

    bookingStore.roomPrice = props.room.price;

    async function confirm() {
        if (showForm.value) {
            showForm.value = false;
        } else {
            try {
                await bookingStore.createBooking(userStore.user, props.room.id);
            } catch (error) {
                console.error('error', error);
            }
            isOpen.value = false;
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

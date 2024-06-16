<template>
    <ModalContainer v-model="isOpen" :confirm-disabled="!bookingStore.isAvailable || !userStore.isRegistered" @confirm="confirm">
        <AddBookingForm :room="room" :isRegistered="userStore.isRegistered" />
    </ModalContainer>
</template>

<script setup lang="ts">
    import { Room } from '@/types/Room';
    import AddBookingForm from './form/AddBookingForm.vue';
    import ModalContainer from '@/components/shared/modal/ModalContainer.vue';
    import { useBookingStore } from '@/stores/booking';
    import { useUserStore } from '@/stores/user';

    const isOpen = defineModel<boolean>();

    const bookingStore = useBookingStore();
    const userStore = useUserStore();

    const props = defineProps<{
        room: Room
    }>();

    async function confirm() {
        try {
            await bookingStore.createBooking(userStore.user, props.room.id);
        } catch (error) {
            console.error('error', error);
        }
    }

</script>

<style scoped>

</style>

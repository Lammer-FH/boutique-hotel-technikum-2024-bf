<template>
    <ModalContainer v-model="isOpen" :confirm-disabled="!bookingStore.isAvailable" @confirm="confirm">
        <BookForm :room="room" />
    </ModalContainer>
</template>

<script setup lang="ts">
    import { Room } from '../../types/Room';
    import BookForm from './book/BookForm.vue';
    import ModalContainer from '../shared/modal/ModalContainer.vue';
    import { defineProps } from 'vue';
    import { useBookingStore } from '../../stores/booking';

    const isOpen = defineModel<boolean>();

    const bookingStore = useBookingStore();

    function confirm() {
        throw new Error('Method not implemented.');
    }

    defineProps<{
        room: Room
    }>();
</script>

<style scoped>

</style>

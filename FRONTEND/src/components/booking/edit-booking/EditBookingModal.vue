<template>
    <ModalContainer v-model="isOpen" :confirm-disabled="!editBookingStore.isAvailable" @confirm="confirm" @close="cancel">
        <EditBookingForm :booking="editBookingStore.booking" />
    </ModalContainer>
</template>

<script setup lang="ts">
    import EditBookingForm from './form/EditBookingForm.vue';
    import ModalContainer from '@/components/shared/modal/ModalContainer.vue';
    import { useEditBookingStore } from '@/stores/edit-booking';

    const emit = defineEmits(['close']);

    const isOpen = defineModel<boolean>();

    const editBookingStore = useEditBookingStore();

    async function confirm() {
        try {
            await editBookingStore.updateBooking(editBookingStore.booking);
        } catch (error) {
            console.error('error', error);
        }

        isOpen.value = false;
        emit('close');
    }

    function cancel() {
        isOpen.value = false;
        emit('close');
    }
</script>

<style scoped>

</style>

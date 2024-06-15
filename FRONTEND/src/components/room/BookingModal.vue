<template>
    <ion-modal :is-open="isOpen">
        <ion-header>
            <ion-toolbar>
                <ion-buttons slot="start">
                    <ion-button @click="cancel(false)">Cancel</ion-button>
                </ion-buttons>
                <ion-buttons slot="end" :strong="true">
                    <ion-button @click="confirm(false)" :disabled="!bookingStore.isAvailable">Confirm</ion-button>
                </ion-buttons>
            </ion-toolbar>
        </ion-header>
        <ion-content>
            <BookForm :room="room" />
        </ion-content>
    </ion-modal>
</template>

<script setup lang="ts">
    import { IonModal, IonHeader, IonToolbar, IonContent, IonButton, IonButtons } from '@ionic/vue';
    import { Room } from '../../types/Room';
    import BookForm from './book/BookForm.vue';
    import { defineProps } from 'vue';
    import { useBookingStore } from '../../stores/booking';

    const isOpen = defineModel<boolean>();

    const bookingStore = useBookingStore();

    defineProps<{
        room: Room
    }>();

    function cancel() {
        setOpen(false);
    }

    function confirm() {
        setOpen(false);
    }

    function setOpen(open: boolean) {
        isOpen.value = open;
    }
</script>

<style scoped>

</style>

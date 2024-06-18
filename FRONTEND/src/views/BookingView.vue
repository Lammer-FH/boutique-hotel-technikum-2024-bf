<template>
    <ion-page>
        <ion-header :translucent="true">
            <ion-toolbar>
                <ion-title>Buchungen</ion-title>
            </ion-toolbar>
        </ion-header>
        <ion-content :fullscreen="true">
            <div class="content">
                <BookingList @edit="editBooking" />
            </div>
            <EditBookingModal v-model="isEditBookingModalOpen" @close="refreshBookings" />
        </ion-content>
    </ion-page>
</template>

<script setup lang="ts">
    import BookingList from '@/components/booking/BookingList.vue'
    import EditBookingModal from '@/components/booking/edit-booking/EditBookingModal.vue';
    import { IonContent, IonPage, IonHeader, IonTitle, IonToolbar } from '@ionic/vue';
    import { ref } from 'vue';
    import { useBookingsStore } from '@/stores/bookings';

    const bookingsStore = useBookingsStore();

    const isEditBookingModalOpen = ref<boolean>(false);

    function editBooking() {
        isEditBookingModalOpen.value = true;
    }

    async function refreshBookings() {
        await bookingsStore.fetchBookings();
    }
</script>

<style scoped>
    ion-toolbar {
      --opacity: 0.5;
      backdrop-filter: blur(10px);
    }

    .content {
        max-width: var(--max-content-width);
        margin-inline: auto;
    }
</style>

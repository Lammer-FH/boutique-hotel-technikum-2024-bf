<template>
    <ion-page>
        <ion-header :translucent="true">
            <ion-toolbar>
                <ion-title>Buchungsbestätigung</ion-title>
                <ion-buttons slot="start">
                    <BackButton />
                </ion-buttons>
            </ion-toolbar>
        </ion-header>
        <ion-content :fullscreen="true">
            <div v-if="confirmation && roomStore.room" class="content">
                <img src="/img/hotel-room.jpg" alt="Room" class="image" />
                <div class="columns">
                    <div class="column">
                        <ion-text>
                            <h2>Angaben zu Person</h2>
                            <p>Vorname: {{ confirmation.firstName }}</p>
                            <p>Nachname: {{ confirmation.lastName }}</p>
                            <p>Email: {{ confirmation.email }}</p>
                        </ion-text>
                    </div>
                    <div class="column">
                        <h2>Hotel</h2>
                        <p>Zimmer: {{ roomStore.room?.title }}</p>
                        <p>Extras: {{ roomStore.room?.extra }}</p>
                        <p>Preis: {{ bookingStore.totalPrice }} €</p>
                        <p>Von: {{ bookingStore.startDateString }}</p>
                        <p>Bis: {{ bookingStore.endDateString }}</p>
                    </div>
                </div>
            </div>
        </ion-content>
    </ion-page>
</template>

<script setup lang="ts">
    import { IonContent, IonPage, IonHeader, IonTitle, IonToolbar, IonButtons, IonText } from '@ionic/vue';
    import BackButton from '@/components/shared/BackButton.vue';
    import { useRoute } from 'vue-router';
    import { useBookingStore } from '@/stores/booking';
    import { useRoomStore } from '@/stores/room';
    import { onMounted, ref } from 'vue';

    const route = useRoute();
    const bookingStore = useBookingStore();
    const roomStore = useRoomStore();

    const confirmation = ref<string | undefined>(undefined);

    onMounted(async () => {
        confirmation.value = await bookingStore.fetchConfirmation(route.params.id);
        await roomStore.fetchRoom(confirmation.value.roomId);
        bookingStore.roomPrice = roomStore.room?.price;
    });
</script>

<style scoped>
    ion-toolbar {
      --opacity: 0.5;
      backdrop-filter: blur(10px);
    }

    .content {
        max-width: var(--max-content-width);
        margin-inline: auto;
        padding: 1rem;
    }

    .image {
        border-radius: 16px;
        height: 30vh;
        width: 100%;
        object-fit: cover;
        box-shadow: 0 0 5px #000000;
        
        margin-bottom: 3rem;
    }
    .columns {
        display: flex;
        justify-content: center;
        flex-direction: row;
        flex-wrap: wrap;
        gap: 5rem;
    }
    .column {
        width: 350px;
    }
</style>

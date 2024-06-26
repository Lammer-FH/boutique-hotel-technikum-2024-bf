<template>
    <ion-page>
        <ion-header :translucent="true">
            <ion-toolbar>
                <ion-buttons slot="start">
                    <BackButton />
                </ion-buttons>
                <ion-title>{{ roomStore.room?.title }}</ion-title>
            </ion-toolbar>
        </ion-header>
        <ion-content :fullscreen="true">
            <div v-if="roomStore.room !== null">
                <div class="content">
                    <RoomDetail :room="roomStore.room" @click="setModelOpen(true)" />
                </div>
                <BookingModal :room="roomStore.room" v-model="isBookingModalOpen" />
            </div>
        </ion-content>
    </ion-page>
</template>

<script setup lang="ts">
    import RoomDetail from '@/components/room/RoomDetail.vue';
    import BookingModal from '@/components/room/booking/BookingModal.vue';
    import BackButton from '@/components/shared/BackButton.vue';
    import { IonContent, IonPage, IonHeader, IonTitle, IonToolbar, IonButtons } from '@ionic/vue';
    import { useRoomStore } from '@/stores/room';
    import { onMounted, watch, ref } from 'vue';
    import { useRoute } from 'vue-router';

    const roomStore = useRoomStore();
    const route = useRoute();

    const isBookingModalOpen = ref(false);

    const fetchRoomData = async (id: number) => {
        try {
            await roomStore.fetchRoom(id);
        } catch (e) {
            console.error(e);
        }
    };

    function setModelOpen(isOpen: boolean) {
        isBookingModalOpen.value = isOpen;
    }

    onMounted(async () => {
        await fetchRoomData(route.params.id);
    });

    watch(() => route.params.id, async (newId) => {
        if (newId && route.name === 'room')
            await fetchRoomData(newId);
    });
</script>

<style scoped>
    ion-toolbar {
      --opacity: 0.5;
      backdrop-filter: blur(10px);
    }

    .content {
        max-width: var(--max-text-width);
        margin-inline: auto;
    }
</style>

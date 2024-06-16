<template>
    <ion-page>
        <ion-header :translucent="true">
            <ion-toolbar>
                <ion-title>Admin Area</ion-title>
            </ion-toolbar>
        </ion-header>
        <ion-content :fullscreen="true">
            <div class="controls">
                <AddRoomButton @click="openAddRoomModal" />
                <AddRoomModal v-model="isAddRoomModalOpen" />
            </div>
            <RoomList :isAdmin="true" />
        </ion-content>
    </ion-page>
</template>

<script setup lang="ts">
    import AddRoomButton from '@/components/admin/AddRoomButton.vue';
    import AddRoomModal from '@/components/admin/add-room/AddRoomModal.vue';
    import RoomList from '@/components/shared/room-list/RoomList.vue';
    import { IonContent, IonPage, IonHeader, IonTitle, IonToolbar } from '@ionic/vue';
    import { ref } from 'vue';

    const isAddRoomModalOpen = ref<boolean>(false);

    function openAddRoomModal() {
        isAddRoomModalOpen.value = true;
    }
</script>

<style scoped>
    ion-toolbar {
      --opacity: 0.5;
      backdrop-filter: blur(10px);
    }
    .controls {
        width: 100%;
        display: inline-flex;
        justify-content: center;
    }
</style>

<template>
    <div v-if="isLoading">
        <div v-for="i in 10" :key="i">
            <SkeletonCard />
        </div>
    </div>
    <div v-if="roomsStore.rooms.length === 0">
        <p>No rooms found</p>
    </div>
    <div v-else>
        <div v-for="room in roomsStore.rooms" :key="room.id">
            <RoomCard :isAdmin="isAdmin" :room="room" @delete="deleteRoom" />
        </div>
    </div>
    <PageControls
        :current-page="roomsStore.currentPage"
        :is-first-page="roomsStore.isFirstPage"
        :is-last-page="roomsStore.isLastPage"
        @prev="prevPage"
        @next="nextPage" />
</template>

<script setup lang="ts">
    import RoomCard from './RoomCard.vue';
    import PageControls from './PageControls.vue';
    import SkeletonCard from './SkeletonCard.vue';
    import { useRoomsStore } from '../../../stores/rooms';
    import { onMounted, ref } from 'vue';

    const roomsStore = useRoomsStore();

    defineProps<{
        isAdmin: boolean;
    }>();

    const isLoading = ref(true);

    async function prevPage() {
        await roomsStore.decrementPage();
        scrollToTop();
    }

    async function nextPage() {
        await roomsStore.incrementPage();
        scrollToTop();
    }

    function scrollToTop() {
        document.getElementById('app')
            .scrollIntoView({ behavior: 'smooth' });
    }

    onMounted(async () => {
        await roomsStore.fetchRooms();
        isLoading.value = false;
    });

    async function deleteRoom(id: number) {
        await roomsStore.deleteRoom(id);
    }
</script>

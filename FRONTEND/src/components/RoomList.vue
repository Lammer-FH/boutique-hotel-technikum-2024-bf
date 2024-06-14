<template>
    <div v-if="roomsStore.rooms.length === 0">
        <p>No rooms found</p>
    </div>
    <div v-else>
        <div v-for="room in roomsStore.rooms" :key="room.id">
            <RoomCard :room="room" />
        </div>
    </div>
</template>
<script setup lang="ts">
import RoomCard from './RoomCard.vue';
import { useRoomsStore } from '../stores/rooms';
import { onMounted } from 'vue';

const roomsStore = useRoomsStore();

onMounted(async () => {
    console.log('roomsStore', roomsStore)
    await roomsStore.fetchRooms();
    console.log('initialized', roomsStore.rooms)
});
</script>

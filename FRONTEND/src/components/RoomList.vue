<template>
    <div>RoomList</div>
    <div v-if="roomsStore.rooms.length === 0">
        <p>No rooms found</p>
    </div>
    <div v-else>
        <div v-for="room in roomsStore.rooms" :key="room.id">
            <div class="room-card">
                <div class="room-card-title">
                    {{ room.title }}
                </div>
                <div class="room-card-description">
                    {{ room.description }}
                </div>
                <div class="room-card-price">
                    {{ room.price }}
                </div>
            </div>
        </div>
    </div>
</template>
<script setup lang="ts">
import { useRoomsStore } from '../stores/rooms';
import { onMounted } from 'vue';

const roomsStore = useRoomsStore();

onMounted(async () => {
    console.log('roomsStore', roomsStore)
    await roomsStore.fetchRooms();
    console.log('initialized', roomsStore.rooms)
});
</script>

import { defineStore } from 'pinia';
import { ref } from 'vue';
import { Room } from '../types/Room';
import * as service from '../services/RoomService';

export const useRoomsStore = defineStore('rooms', () => {
    const rooms = ref<any[]>([]);

    function initRooms(data: Room[]) {
        rooms.value = data;
    }

    async function fetchRooms() {
        try {
            const rooms = await service.fetchRooms()
            initRooms(rooms);
        } catch (error) {
            console.error('error', error)
        }
    }

    return { rooms, fetchRooms };
});

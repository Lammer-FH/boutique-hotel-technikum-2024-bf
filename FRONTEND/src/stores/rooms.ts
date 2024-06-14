import { defineStore } from 'pinia';
import { ref } from 'vue';
import { Room } from '../types/Room';
import * as roomsService from '../services/RoomService';

export const useRoomsStore = defineStore('rooms', () => {
    const rooms = ref<Room[]>([]);

    function initRooms(data: Room[]) {
        rooms.value = data;
    }

    async function fetchRooms() {
        try {
            const rooms = await roomsService.fetchRooms();
            initRooms(rooms);
        } catch (error) {
            console.error('error', error);
        }
    }

    return { rooms, fetchRooms };
});

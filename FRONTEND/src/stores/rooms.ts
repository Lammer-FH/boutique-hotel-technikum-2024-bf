import { defineStore } from 'pinia';
import http from '../axios';
import { ref } from 'vue';
import { Room } from '../types/Room';

export const useRoomsStore = defineStore('rooms', () => {
    const rooms = ref<any[]>([]);

    function initRooms(data: Room[]) {
        rooms.value = data;
    }

    async function fetchRooms() {
        try {
            const result = await http.get<Room[]>('/rooms');

            if (result.status === 200)
                initRooms(result.data);
        } catch (error) {
            console.error('error', error)
        }
    }

    return { rooms, fetchRooms };
});

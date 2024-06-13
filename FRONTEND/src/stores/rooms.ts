import { defineStore } from 'pinia';
import http from '../axios';
import { ref } from 'vue';

export const useRoomsStore = defineStore('rooms', () => {
    const rooms = ref<any[]>([]);

    function initRooms(data: any[]) {
        rooms.value = data;
    }

    async function fetchRooms() {
        try {
            const result = await http.get('/rooms');

            if (result.status === 200)
                initRooms(result.data);
        } catch (error) {
            console.error('error', error)
        }
    }

    return { rooms, fetchRooms };
});

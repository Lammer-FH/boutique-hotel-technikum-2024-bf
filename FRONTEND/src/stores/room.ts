import { defineStore } from 'pinia';
import { ref } from 'vue';
import { Room } from '@/types/Room';
import * as roomsService from '@/services/RoomService';

export const useRoomStore = defineStore('room', () => {
    const room = ref<Room | null>(null);

    function initRoom(data: Room) {
        room.value = data;
    }

    async function fetchRoom(id: number) {
        if (room.value?.id === id)
            return;

        try {
            const room = await roomsService.fetchRoomById(id);
            initRoom(room);
        } catch (error) {
            console.error('error', error);
        }
    }

    return { room, fetchRoom };
});

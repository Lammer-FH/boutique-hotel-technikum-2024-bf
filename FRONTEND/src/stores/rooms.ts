import { defineStore } from 'pinia';
import { ref } from 'vue';
import { Room } from '../types/Room';
import { computed } from 'vue';
import * as roomsService from '../services/RoomService';

export const useRoomsStore = defineStore('rooms', () => {
    const PAGE_SIZE = 10;

    const rooms = ref<Room[]>([]);
    const currentPage = ref(0);
    const isFirstPage = computed(() => currentPage.value === 0);
    const isLastPage = computed(() => rooms.value.length < PAGE_SIZE);

    async function incrementPage() {
        currentPage.value++;
        await fetchRooms();
    }

    async function decrementPage() {
        currentPage.value--;
        await fetchRooms();
    }

    async function fetchRooms() {
        try {
            rooms.value = await roomsService.fetchRooms(currentPage.value, PAGE_SIZE);
        } catch (error) {
            console.error('error', error);
        }
    }

    return { rooms, fetchRooms, currentPage, incrementPage, decrementPage, isFirstPage, isLastPage };
});

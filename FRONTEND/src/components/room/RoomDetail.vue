<template>
    <div v-if="roomStore.room !== null">
        <RoomImage />
        <RoomDescription :room="roomStore.room" />
        <RoomActions :roomId="roomStore.room.id" />
    </div>
</template>

<script setup lang="ts">
    import { useRoomStore } from '../../stores/room';
    import { onMounted, watch } from 'vue';
    import { useRoute } from 'vue-router';
    import RoomImage from './RoomImage.vue';
    import RoomDescription from './RoomDescription.vue';
    import RoomActions from './RoomActions.vue';

    const roomStore = useRoomStore();
    const route = useRoute();

    const fetchRoomData = async (id: number) => {
        try {
            await roomStore.fetchRoom(id);
        } catch (e) {
            console.error(e);
        }
    };

    onMounted(async () => {
        await fetchRoomData(route.params.id);
    });

    watch(() => route.params.id, async (newId) => {
        if (newId)
            await fetchRoomData(newId);
    });
</script>

<style scoped>
    .test {
        padding-top: 70px;
    }
</style>

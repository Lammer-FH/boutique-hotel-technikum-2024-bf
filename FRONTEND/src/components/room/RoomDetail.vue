<template>
    <RoomImage />
    <RoomDescription :room="room" />
    <RoomActions :roomId="room.id" @click="emit('click')" />
</template>

<script setup lang="ts">
    import RoomImage from './details/RoomImage.vue';
    import RoomDescription from './details/RoomDescription.vue';
    import RoomActions from './details/RoomActions.vue';

    const emit = defineEmits(['click'])

    defineProps<{
        room: Room
    }>()
</script>

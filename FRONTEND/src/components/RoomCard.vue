<template>
    <ion-card>
        <img src="/img/hotel-room.jpg" alt="Room" />
        <ion-card-header>
            <ion-card-title>
                {{ room.title }}
            </ion-card-title>
            <ion-card-subtitle>
                {{ room.price}} €/Tag
            </ion-card-subtitle>
        </ion-card-header>
        <ion-card-content>
            {{ room.description }}
        </ion-card-content>
    </ion-card>
</template>

<script setup lang="ts">
import { Room } from '../types/Room';

const props = defineProps<{
    room: Room
}>()
</script>

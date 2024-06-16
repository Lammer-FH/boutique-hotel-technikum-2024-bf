<template>
    <ion-card>
        <img src="/img/hotel-room.jpg" alt="Room" style="width: 100%; height: 279.5px;" />
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
        <ion-button v-if="!isAdmin" fill="clear" :router-link="`/room/${ room.id }`">
            Jetzt buchen
        </ion-button>
        <ion-button v-else fill="clear" @click="emit('delete', room.id)">
            Löschen
        </ion-button>
    </ion-card>
</template>

<script setup lang="ts">
    import { Room } from '@/types/Room';
    import {
        IonButton,
        IonCard,
        IonCardHeader,
        IonCardSubtitle,
        IonCardTitle,
        IonCardContent
    } from '@ionic/vue';

    const emit = defineEmits(['delete'])

    defineProps<{
        room: Room;
        isAdmin: boolean;
    }>()

</script>

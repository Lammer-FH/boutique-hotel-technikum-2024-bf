<template>
    <ion-card>
        <ion-card-content>
            Details: {{ room.description }}
            <br />
            Preis: {{ room.price }} €
            <br />
            Extras: {{ room.extra }}
        </ion-card-content>
    </ion-card>
</template>

<script setup lang="ts">
    import { IonCard, IonCardContent } from '@ionic/vue';
    import { Room } from '../../types/Room';
    import { defineProps } from 'vue';

    defineProps<{
        room: Room;
    }>();
</script>

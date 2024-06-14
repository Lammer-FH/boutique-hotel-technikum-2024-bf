<template>
    <ion-card>
        <ion-card-content>
            Von: <DatePicker />
        </ion-card-content>
    </ion-card>
</template>

<script setup lang="ts">
    import { IonCard, IonCardContent } from '@ionic/vue';
    import DatePicker from './DatePicker.vue';
    import { defineProps } from 'vue';

    defineProps<{
        room: Room
    }>();
</script>

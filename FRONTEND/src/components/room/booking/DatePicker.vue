<template>
    <ion-datetime-button
        :datetime="id"
        presentation="date"></ion-datetime-button>

    <ion-modal :keep-contents-mounted="true">
        <ion-datetime
            :id="id"
            v-model="dateString"
            presentation="date"
            :min="minString"></ion-datetime>
    </ion-modal>
</template>

<script setup lang="ts">
    import { IonDatetime, IonDatetimeButton, IonModal } from '@ionic/vue';
    import { defineModel, computed } from 'vue';

    const props = defineProps<{
        id: string;
        min: Date;
    }>();

    const date = defineModel<Date>({ required: true });

    const dateString = computed({
        get: () => toISOString(date.value),
        set: value => date.value = new Date(value)
    });

    const minString = computed(() => toISOString(props.min));

    function toISOString(date: Date) {
        return date.toISOString().split('T')[0];
    }
</script>

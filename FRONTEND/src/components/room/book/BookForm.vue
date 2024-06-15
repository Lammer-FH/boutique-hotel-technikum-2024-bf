<template>
    <ion-card>
        <ion-card-content>
            Von: <DatePicker v-model="startDate" :min="minStartDate" id="startDate" />
        </ion-card-content>
        <ion-card-content>
            Bis: <DatePicker v-model="endDate" :min="minEndDate" id="endDate" />
        </ion-card-content>
    </ion-card>
</template>

<script setup lang="ts">
    import { IonCard, IonCardContent } from '@ionic/vue';
    import DatePicker from './DatePicker.vue';
    import { computed,  defineProps, ref, watch } from 'vue';

    const ONE_DAY = 24 * 60 * 60 * 1000;

    const startDate = ref<Date>(new Date());
    const minStartDate = ref<Date>(new Date());
    const minEndDate = computed(() => new Date(startDate.value.valueOf() + ONE_DAY));
    const endDateHelper = ref<Date>(addDays(startDate.value, 1));
    const endDate = computed({
        get() {
            if (endDateHelper.value.valueOf() < minEndDate.value.valueOf())
                return minEndDate.value;
            else
                return endDateHelper.value;
        },
        set(value) {
            endDateHelper.value = value;
        }
    });

    function addDays(date: Date, days: number) {
        const result = new Date(date.valueOf() + days * ONE_DAY);
        console.log('add',result);
        return result;
    }

    defineProps<{
        room: Room;
    }>();
</script>

<template>
    <ion-card>
        <ion-card-content>
            <TextField label="Title" v-model="title" @isValid="updateIsTitleValid" />
            <TextareaField label="Description" v-model="description" @isValid="updateIsDescriptionValid" />
            <TextField label="Extra" v-model="extra" @isValid="updateIsExtraValid" />
            <PriceField label="Price" v-model="price" @isValid="updateIsPriceValid" />
        </ion-card-content>
    </ion-card>
</template>

<script setup lang="ts">
    import { IonCard, IonCardContent } from '@ionic/vue';
    import TextField from '@/components/shared/fields/TextField.vue';
    import PriceField from './fields/PriceField.vue';
    import TextareaField from './fields/TextareaField.vue';
    import { computed, ref, watchEffect } from 'vue';
    import { useRoomsStore } from '@/stores/rooms';

    const emit = defineEmits(['confirm', 'confirmEnabled']);

    defineExpose({
        confirm
    });

    const roomStore = useRoomsStore();

    const title = ref<string>('');
    const description = ref<string>('');
    const extra = ref<string>('');
    const price = ref<number | null>(null);

    const room = computed(() => ({
        title: title.value,
        description: description.value,
        extra: extra.value,
        price: price.value
    }));

    const isTitleValid = ref<boolean>(false);
    const isDescriptionValid = ref<boolean>(false);
    const isExtraValid = ref<boolean>(false);
    const isPriceValid = ref<boolean>(false);

    const isBlocked = ref<boolean>(false);

    const isValid = computed(() => 
        isTitleValid.value
        && isDescriptionValid.value
        && isExtraValid.value
        && isPriceValid.value
    );

    const isButtonEnabled = computed(() => isValid.value && !isBlocked.value);

    watchEffect(() => emit('confirmEnabled', isButtonEnabled.value));

    function updateIsTitleValid(value: boolean) {
        isTitleValid.value = value;
    }

    function updateIsDescriptionValid(value: boolean) {
        isDescriptionValid.value = value;
    }

    function updateIsExtraValid(value: boolean) {
        isExtraValid.value = value;
    }

    function updateIsPriceValid(value: boolean) {
        isPriceValid.value = value;
    }

    async function confirm() {
        isBlocked.value = true;
        try {
            await roomStore.createRoom(room.value);
        } catch (error) {
            console.error('error', error);
        }
        isBlocked.value = false;
    }
</script>

<style scoped>
    ion-card-content {
        display: flex;
        flex-direction: column;
        gap: 2em;
    }
</style>

<template>
    <ModalContainer v-model="isOpen" @confirm="confirm" @close="close" :confirmDisabled="!confirmEnabled">
        <AddRoomForm @confirmEnabled="updateConfirmEnabled" ref="form" />
    </ModalContainer>
</template>

<script setup lang="ts">
    import ModalContainer from '@/components/shared/modal/ModalContainer.vue';
    import AddRoomForm from './AddRoomForm.vue';
    import { ref } from 'vue';

    const isOpen = defineModel<boolean>();
    const confirmEnabled = ref<boolean>(false);

    const form = ref<InstanceType<typeof AddRoomForm | null>>(null);

    function updateConfirmEnabled(value: boolean) {
        confirmEnabled.value = value;
    }

    async function confirm() {
        await form.value?.confirm();
        isOpen.value = false;
    }

    function close() {
        isOpen.value = false;
    }
</script>

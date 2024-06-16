<template>
    <ion-modal :is-open="isOpen">
        <ion-header>
            <ion-toolbar>
                <ion-buttons slot="start">
                    <ion-button @click="cancel()">Cancel</ion-button>
                </ion-buttons>
                <ion-title v-if="!!title">{{ title }}</ion-title>
                <ion-buttons slot="end" :strong="true">
                    <ion-button @click="confirm()" :disabled="confirmDisabled">Confirm</ion-button>
                </ion-buttons>
            </ion-toolbar>
        </ion-header>
        <ion-content>
            <slot></slot>
        </ion-content>
    </ion-modal>
</template>

<script setup lang="ts">
    import { IonModal, IonHeader, IonToolbar, IonContent, IonButton, IonButtons, IonTitle } from '@ionic/vue';
    import { defineProps } from 'vue';

    const isOpen = defineModel<boolean>();
    const emit = defineEmits(['close', 'confirm']);

    withDefaults(defineProps<{
        confirmDisabled: boolean;
        title?: string;
    }>(), {
        confirmDisabled: false,
        title: undefined,
    });

    function cancel() {
        setOpen(false);
        emit('close');
    }

    function confirm(): void {
        setOpen(false);
        emit('confirm');
    }

    function setOpen(open: boolean) {
        isOpen.value = open;
    }
</script>

<style scoped>

</style>

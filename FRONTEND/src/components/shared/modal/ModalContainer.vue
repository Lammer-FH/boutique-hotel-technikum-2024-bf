<template>
    <ion-modal :is-open="isOpen">
        <ion-header>
            <ion-toolbar>
                <ion-buttons slot="start">
                    <ion-button @click="cancel()">{{ cancelLabel }}</ion-button>
                </ion-buttons>
                <ion-title v-if="!!title">{{ title }}</ion-title>
                <ion-buttons slot="end" :strong="true">
                    <ion-button @click="confirm()" :disabled="confirmDisabled">{{ confirmLabel }}</ion-button>
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

    const isOpen = defineModel<boolean>();
    const emit = defineEmits(['close', 'confirm']);

    withDefaults(defineProps<{
        cancelLabel?: string;
        confirmLabel?: string;
        confirmDisabled: boolean;
        title?: string;
    }>(), {
        cancelLabel: 'Cancel',
        confirmLabel: 'Confirm',
        confirmDisabled: false,
        title: undefined,
    });

    function cancel() {
        emit('close');
    }

    function confirm(): void {
        emit('confirm');
    }
</script>

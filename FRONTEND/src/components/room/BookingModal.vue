<template>
    <ion-modal :is-open="isOpen">
        <ion-header>
            <ion-toolbar>
                <ion-title>Modal</ion-title>
                <ion-buttons slot="end">
                    <ion-button @click="setOpen(false)">Close</ion-button>
                </ion-buttons>
            </ion-toolbar>
        </ion-header>
        <ion-content>
            <BookForm :room="room" />
        </ion-content>
    </ion-modal>
</template>

<script setup lang="ts">
    import { IonModal, IonHeader, IonTitle, IonToolbar, IonContent, IonButton, IonButtons } from '@ionic/vue';
    import { Room } from '../../types/Room';
    import BookForm from './book/BookForm.vue';
    import { defineProps } from 'vue';

    const isOpen = defineModel<boolean>();
    defineProps<{
        room: Room
    }>();

    function setOpen(open: boolean) {
        isOpen.value = open;
    }
</script>

<style scoped>

</style>

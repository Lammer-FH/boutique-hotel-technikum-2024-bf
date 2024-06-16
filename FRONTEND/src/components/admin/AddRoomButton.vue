<template>
    <ion-button @click="router.push('/admin/add-room')" fill="clear">
        <div class="button-content">
            <div>Zimmer hinzufügen</div>
            <ion-icon :icon="addCircleOutline"></ion-icon>
        </div>
    </ion-button>
</template>

<script setup lang="ts">
    import { IonButton, IonIcon } from '@ionic/vue';
    import { addCircleOutline } from 'ionicons/icons';
    import { useRouter } from 'vue-router';

    const router = useRouter();
</script>

<style scoped>
    .button-content {
        display: inline-flex;
        justify-content: center;
        align-items: center;
        gap: 1em;
    }

    ion-icon {
        width: 1.5em;
        height: 1.5em;
    }
</style>

<template>
    <ion-button v-if="!isRegistered" @click="router.push('/register')">
        <ion-icon :icon="personAddOutline"></ion-icon>
    </ion-button>
    <ion-button v-else @click="logout">
        <ion-icon :icon="logOutOutline"></ion-icon>
    </ion-button>
</template>

<script setup lang="ts">
    import { IonButton, IonIcon } from '@ionic/vue';
    import { useUserStore } from '../../stores/user';
    import { personAddOutline, logOutOutline } from 'ionicons/icons';
    import { useRouter } from 'vue-router';
    import { toRefs } from 'vue';

    const router = useRouter();
    const { isRegistered, logout } = toRefs(useUserStore());
</script>

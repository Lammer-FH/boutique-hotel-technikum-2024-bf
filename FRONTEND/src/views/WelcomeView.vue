<template>
    <ion-page>
        <ion-content :fullscreen="true">
            <div class="content">
                <img src="/img/hotel-room.jpg" class="image" />
                <ion-text class="text">
                    <h1>Willkommen im Boutique Hotel Group BF!</h1>
                </ion-text>
                <ion-button @click="router.push('/home')" shape="round" fill="outline" size="large">Enter</ion-button>
                <ion-buttons class="buttons">
                    <ion-button @click="router.push('/impressum')">Impressum</ion-button>
                    <ion-button @click="router.push('/about')">About</ion-button>
                </ion-buttons>
            </div>
        </ion-content>
    </ion-page>
</template>

<script setup lang="ts">
    import { IonContent, IonPage, IonButtons } from '@ionic/vue';
    import { useRouter } from 'vue-router';

    const router = useRouter();
</script>

<style scoped>
    ion-toolbar {
      --opacity: 0.5;
      backdrop-filter: blur(10px);
    }
    .content {
        max-width: var(--max-content-width);
        margin-inline: auto;
        height: 100dvh;
        padding: 1rem;

        align-items: center;
        justify-content: center;
        display: flex;
        flex-direction: column;
        flex-wrap: nowrap;
        gap: 3rem;

    }
    .image {
        border-radius: 16px;
        height: 30vh;
        width: 100%;
        object-fit: cover;
        box-shadow: 0 0 5px #000000;
    }

    .text {
        text-align: center;
    }

    .buttons {
        bottom: 1rem;
        position: absolute;
    }
</style>

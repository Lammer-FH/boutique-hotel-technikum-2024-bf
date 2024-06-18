<template>
    <ion-page>
        <ion-header :translucent="true">
            <ion-toolbar>
                <ion-buttons slot="start">
                    <BackButton />
                </ion-buttons>
                <ion-title>Register</ion-title>
            </ion-toolbar>
        </ion-header>
        <ion-content :fullscreen="true">
            <div class="content">
                <RegisterForm />
            </div>
        </ion-content>
    </ion-page>
</template>

<script setup lang="ts">
    import RegisterForm from '@/components/register/RegisterForm.vue';
    import BackButton from '@/components/shared/BackButton.vue';
    import { IonContent, IonPage, IonHeader, IonTitle, IonToolbar, IonButtons } from '@ionic/vue';
</script>

<style scoped>
    ion-toolbar {
      --opacity: 0.5;
      backdrop-filter: blur(10px);
    }

    .content {
        max-width: var(--max-text-width);
        margin-inline: auto;
    }
</style>

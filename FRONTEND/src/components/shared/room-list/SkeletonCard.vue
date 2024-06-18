<template>
    <ion-card>
        <ion-skeleton-text animated style="width: 100%; height: 279.5px;"></ion-skeleton-text>
        <ion-card-header>
            <ion-card-title>
                <ion-skeleton-text animated style="width: 40%; height: 24px; margin: 0;"></ion-skeleton-text>
            </ion-card-title>
            <ion-card-subtitle>
                <ion-skeleton-text animated style="width: 30%; height: 11px;"></ion-skeleton-text>
            </ion-card-subtitle>
        </ion-card-header>
        <ion-card-content>
            <ion-skeleton-text animated style="width: 60%;"></ion-skeleton-text>
        </ion-card-content>
        <ion-skeleton-text animated style="width: 109px; height: 19px; margin: 16px; 2.2em;"></ion-skeleton-text>
    </ion-card>
</template>

<script setup lang="ts">
    import {
        IonCard,
        IonCardHeader,
        IonCardSubtitle,
        IonCardTitle,
        IonCardContent,
        IonSkeletonText
    } from '@ionic/vue';
</script>

<style scoped>
    ion-card {
        width: var(--card-width);
    }
</style>

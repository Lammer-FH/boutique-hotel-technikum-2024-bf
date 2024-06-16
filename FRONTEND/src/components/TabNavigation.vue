<template>
    <ion-page>
        <ion-tabs>
            <ion-router-outlet></ion-router-outlet>
            <ion-tab-bar slot="bottom">
                <ion-tab-button tab="home" href="/home">
                    <ion-icon :icon="homeOutline" />
                        <ion-label>Home</ion-label>
                </ion-tab-button>

                <ion-tab-button tab="booking" href="/booking">
                    <ion-icon :icon="bookmarksOutline" />
                        <ion-label>Buchungen</ion-label>
                </ion-tab-button>

                <ion-tab-button tab="admin" href="/admin">
                    <ion-icon :icon="cogOutline" />
                        <ion-label>Admin</ion-label>
                </ion-tab-button>
            </ion-tab-bar>
        </ion-tabs>
    </ion-page>
</template>

<script setup lang="ts">
    import { IonPage, IonTabs, IonRouterOutlet, IonTabBar, IonTabButton, IonLabel, IonIcon } from '@ionic/vue';
    import { homeOutline, cogOutline, bookmarksOutline } from 'ionicons/icons';
</script>

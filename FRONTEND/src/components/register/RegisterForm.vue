<template>
    <ion-card>
        <ion-card-content>
            <TextField label="Vorname" v-model="firstName" @isValid="updateIsFirstNameValid" />
            <TextField label="Nachname" v-model="lastName" @isValid="updateIsLastNameValid" />
            <EmailField v-model="email" @isValid="updateIsEmailValid" />
            <PasswordField v-model="password" @isValid="updateIsPasswordValid" />
            <PasswordConfirmField :password="password" @isValid="updateIsPasswordConfirmValid" />
        </ion-card-content>
        <ion-card-content>
            <ion-button @click="register" :disabled="!isButtonEnabled">Register</ion-button>
        </ion-card-content>
    </ion-card>
</template>

<script setup lang="ts">
    import { IonCard, IonCardContent, IonButton } from '@ionic/vue';
    import TextField from '@/components/shared/fields/TextField.vue';
    import EmailField from './fields/EmailField.vue';
    import PasswordField from './fields/PasswordField.vue';
    import PasswordConfirmField from './fields/PasswordConfirmField.vue';
    import { useUserStore } from '@/stores/user';
    import { computed, ref, toRefs } from 'vue';
    import { useRouter } from 'vue-router';

    const router = useRouter();

    const userStore = useUserStore();

    const {
        password,
        firstName,
        lastName,
        email,
    } = toRefs(userStore);

    const isFirstNameValid = ref<boolean>(false);
    const isLastNameValid = ref<boolean>(false);
    const isEmailValid = ref<boolean>(false);
    const isPasswordValid = ref<boolean>(false);
    const isPasswordConfirmValid = ref<boolean>(false);

    const isBlocked = ref<boolean>(false);

    const isValid = computed(() => 
        isFirstNameValid.value
        && isLastNameValid.value
        && isEmailValid.value
        && isPasswordValid.value
        && isPasswordConfirmValid.value
    );

    const isButtonEnabled = computed(() => isValid.value && !isBlocked.value);

    function updateIsFirstNameValid(value: boolean) {
        isFirstNameValid.value = value;
    }

    function updateIsLastNameValid(value: boolean) {
        isLastNameValid.value = value;
    }

    function updateIsEmailValid(value: boolean) {
        isEmailValid.value = value;
    }

    function updateIsPasswordValid(value: boolean) {
        isPasswordValid.value = value;
    }

    function updateIsPasswordConfirmValid(value: boolean) {
        isPasswordConfirmValid.value = value;
    }

    async function register() {
        isBlocked.value = true;
        try {
            await userStore.register()
            router.push('/home');
        } catch (error) {
            console.error(error);
        }

        isBlocked.value = false;
    }
</script>

<style scoped>
    ion-card-content {
        display: flex;
        flex-direction: column;
        gap: 2em;
    }
</style>

<template>
    <ion-item>
        <ion-input
            label="Passwort"
            label-placement="stacked"
            :class="inputClass"
            type="password"
            required="true"
            error-message="Passwort muss mindestens {{ PASSWORD_MIN_LENGTH }} Zeichen lang sein"
            @ionBlur="markTouched"
            v-model="value">
            <ion-input-password-toggle slot="end"></ion-input-password-toggle>
        </ion-input>
    </ion-item>
</template>

<script setup lang="ts">
    import { IonItem, IonInput, IonInputPasswordToggle } from '@ionic/vue';
    import { computed, defineModel, ref, watchEffect } from 'vue';

    const PASSWORD_MIN_LENGTH = 8;

    const value = defineModel<string>();
    const emit = defineEmits(['isValid']);

    const isTouched = ref<boolean>(false);
    const isValid = computed(() => value.value.length >= PASSWORD_MIN_LENGTH);

    const inputClass = computed(() => ({
        active: true,
        'ion-touched': isTouched.value,
        'ion-invalid': !isValid.value,
        'ion-valid': isValid.value,
    }));

    function markTouched() {
        isTouched.value = true;
    }

    watchEffect(() => emit('isValid', isValid.value));
</script>

<template>
    <ion-item>
        <ion-input
            label="Passwort bestätigen"
            label-placement="stacked"
            :class="inputClass"
            type="password"
            required="true"
            error-message="Passwörter müssen übereinstimmen"
            @ionBlur="markTouched"
            v-model="passwordConfirm">
            <ion-input-password-toggle slot="end"></ion-input-password-toggle>
        </ion-input>
    </ion-item>
</template>

<script setup lang="ts">
    import { IonItem, IonInput, IonInputPasswordToggle } from '@ionic/vue';
    import { computed, ref, watchEffect } from 'vue';

    const props = defineProps<{
        password: string;
    }>();
    const passwordConfirm = ref<string>('');
    const emit = defineEmits(['isValid']);

    const isTouched = ref<boolean>(false);
    const isValid = computed(() => props.password === passwordConfirm.value);

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

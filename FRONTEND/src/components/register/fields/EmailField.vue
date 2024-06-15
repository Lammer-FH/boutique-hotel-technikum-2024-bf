<template>
    <ion-item>
        <ion-input
            label="E-Mail"
            label-placement="stacked"
            :class="inputClass"
            type="text"
            error-message="E-Mail ist ungültig"
            @ionBlur="markTouched"
            v-model="value"></ion-input>
    </ion-item>
</template>

<script setup lang="ts">
    import { IonItem, IonInput } from '@ionic/vue';
    import { computed, defineModel, ref, watchEffect } from 'vue';

    const value = defineModel<string>();
    const emit = defineEmits(['isValid']);

    const isTouched = ref<boolean>(false);
    const isValid = computed(() => {
        const result = value.value.match(
            /^(?=.{1,254}$)(?=.{1,64}@)[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/
        );
        return result !== null;
    });

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

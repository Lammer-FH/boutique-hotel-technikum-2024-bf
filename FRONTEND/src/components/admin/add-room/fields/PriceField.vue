<template>
    <ion-item>
        <ion-input
            :label="label"
            label-placement="stacked"
            :class="inputClass"
            error-message="{{ label }} ist zu kurz"
            type="number"
            @ionBlur="markTouched"
            v-model="valueString"></ion-input>
    </ion-item>
</template>

<script setup lang="ts">
    import { IonItem, IonInput } from '@ionic/vue';
    import { computed, ref, watchEffect } from 'vue';

    const props = defineProps<{
        label: string;
        modelValue: number | null;
    }>();

    const emit = defineEmits(['isValid', 'update:modelValue']);

    const valueString = ref(props.modelValue?.toString() ?? null);
    watchEffect(() => emit('update:modelValue', Number(valueString.value)));

    const value = computed({
        get: () => Number(valueString.value),
        set: newValue => {
            valueString.value = newValue?.toString() ?? null;
            emit('update:modelValue', newValue);
        }
    });

    const isTouched = ref<boolean>(false);
    const isValid = computed(() => value.value > 0);


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

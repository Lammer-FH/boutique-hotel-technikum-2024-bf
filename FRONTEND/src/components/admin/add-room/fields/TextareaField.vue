<template>
    <ion-item>
        <ion-textarea
            :label="label"
            label-placement="stacked"
            :class="inputClass"
            type="text"
            error-message="{{ label }} ist zu kurz"
            @ionBlur="markTouched"
            v-model="value"></ion-textarea>
    </ion-item>
</template>

<script setup lang="ts">
    import { IonItem, IonTextarea } from '@ionic/vue';
    import { computed, defineModel, ref, watchEffect } from 'vue';

    const value = defineModel<string>();
    defineProps<{
        label: string;
    }>();
    const emit = defineEmits(['isValid']);

    const isTouched = ref<boolean>(false);
    const isValid = computed(() => value.value.length > 0);

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

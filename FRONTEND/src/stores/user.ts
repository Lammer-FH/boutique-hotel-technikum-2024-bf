import { defineStore } from 'pinia';
import { ref, computed } from 'vue';
import { User } from '../types/User';
import * as userService from '../services/UserService';

export const useUserStore = defineStore('user', () => {
    const password = ref<string>('');
    const firstName = ref<string>('');
    const lastName = ref<string>('');
    const email = ref<string>('');

    const isRegistered = ref<boolean>(false);

    const user = computed<User | null>(() => {
        if (password.value === '' || firstName.value === '' || lastName.value === '' || email.value === '')
            return null;
        return { password: password.value, firstName: firstName.value, lastName: lastName.value, email: email.value };
    });

    async function register(): Promise<void> {
        if (user.value === null)
            return Promise.reject('No User');

        try {
            await userService.register(user.value);
            isRegistered.value = true;
            Promise.resolve();
        } catch (error) {
            console.error('error', error);
            Promise.reject(error);
        }
    }

    function logout(): void {
        isRegistered.value = false;
        password.value = '';
        firstName.value = '';
        lastName.value = '';
        email.value = '';
    }

    return { user, password, firstName, lastName, email, register, logout, isRegistered };
});

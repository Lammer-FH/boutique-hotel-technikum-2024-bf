import http from '../axios';
import { User } from '../types/User';

export async function register(user: User) {
    try {
        const result = await http.post<string>('/users/register', user);

        if (result.status === 200)
            return Promise.resolve(result.data);
    } catch (error) {
        console.error('error', error)
        return Promise.reject(error);
    }

    return Promise.reject('error');
}

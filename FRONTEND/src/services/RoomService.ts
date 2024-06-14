import http from '../axios';
import { Room } from '../types/Room';

export async function fetchRooms(): Promise<Room[]> {
    try {
        const result = await http.get<Room[]>('/rooms');

        if (result.status === 200)
            return Promise.resolve(result.data);
    } catch (error) {
        console.error('error', error)
        return Promise.reject(error);
    }

    return Promise.reject('error');
}

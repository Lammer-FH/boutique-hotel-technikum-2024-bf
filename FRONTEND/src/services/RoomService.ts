import http from '@/axios';
import { Room } from '@/types/Room';

export async function fetchRooms(page: number, size: number): Promise<Room[]> {
    try {
        const result = await http.get<Room[]>(
            '/rooms/page',
            { params: { page, size } }
        );

        if (result.status === 200)
            return Promise.resolve(result.data);
    } catch (error) {
        console.error('error', error)
        return Promise.reject(error);
    }

    return Promise.reject('error');
}

export async function fetchRoomById(id: number): Promise<Room> {
    try {
        const result = await http.get<Room>(`/rooms/${id}`);

        if (result.status === 200)
            return Promise.resolve(result.data);
    } catch (error) {
        console.error('error', error)
        return Promise.reject(error);
    }

    return Promise.reject('error');
}

export async function deleteRoom(id: number): Promise<void> {
    try {
        const result = await http.delete<void>(`/rooms/${id}`);

        if (result.status === 204)
            return Promise.resolve();
    } catch (error) {
        console.error('error', error)
        return Promise.reject(error);
    }

    return Promise.reject('error');
}

export async function createRoom(room: Room): Promise<Room> {
    try {
        const result = await http.post<Room>('/rooms', { ...room, available: true });

        if (result.status === 200)
            return Promise.resolve(result.data);
    } catch (error) {
        console.error('error', error)
        return Promise.reject(error);
    }

    return Promise.reject('error');
}

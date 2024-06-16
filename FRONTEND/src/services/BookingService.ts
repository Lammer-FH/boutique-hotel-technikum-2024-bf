import http from '@/axios';

export async function fetchIsAvailable(roomId: number, startDate: Date, endDate: Date): Promise<boolean> {
    const startDateString = formatDate(startDate);
    const endDateString = formatDate(endDate);
    try {
        const result = await http.get<boolean>(
            '/bookings/availability',
            { params: { roomId, startDate: startDateString, endDate: endDateString } }
        );

        if (result.status === 200)
            return Promise.resolve(result.data);
    } catch (error) {
        console.error('error', error)
        return Promise.reject(error);
    }

    return Promise.reject('error');
}

function formatDate(date: Date) {
    const year = date.getFullYear();
    const month = date.getMonth() + 1;
    const day = date.getDate();
    return `${year}-${pad(month)}-${pad(day)}`;
}

function pad(value: number) {
    return String(value).padStart(2, '0');
}

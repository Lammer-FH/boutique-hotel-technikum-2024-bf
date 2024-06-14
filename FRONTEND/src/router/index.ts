import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        redirect: '/home'
    },
    {
        path: '/home',
        name: 'home',
        component: () => import('../components/home/HomeView.vue')
    },
    {
        path: '/room/:id',
        name: 'room',
        component: () => import('../components/room/RoomView.vue')
    },
    {
        path: '/:pathMatch(.*)*',
        redirect: '/home'
    }
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
})

export default router

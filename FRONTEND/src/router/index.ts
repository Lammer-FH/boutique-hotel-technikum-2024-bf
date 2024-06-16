import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';
import TabNavigation from '@/components/TabNavigation.vue';

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        redirect: '/home'
    },
    {
        path: '/',
        component: TabNavigation,
        children: [
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
                path: '/register',
                name: 'register',
                component: () => import('../components/register/RegisterView.vue')
            },
            {
                path: '/admin',
                name: 'admin',
                component: () => import('../components/admin/AdminView.vue')
            },
        ],
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

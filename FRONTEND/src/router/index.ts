import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';
import TabNavigation from '@/components/TabNavigation.vue';

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        redirect: '/welcome'
    },
    {
        path: '/welcome',
        name: 'welcome',
        component: () => import('@/views/WelcomeView.vue')
    },
    {
        path: '/',
        component: TabNavigation,
        children: [
            {
                path: '/home',
                name: 'home',
                component: () => import('@/views/HomeView.vue')
            },
            {
                path: '/booking',
                name: 'booking',
                component: () => import('@/views/BookingView.vue')
            },
            {
                path: '/admin',
                name: 'admin',
                component: () => import('@/views/AdminView.vue')
            },
        ],
    },

    {
        path: '/room/:id',
        name: 'room',
        component: () => import('@/views/RoomView.vue')
    },
    {
        path: '/register',
        name: 'register',
        component: () => import('@/views/RegisterView.vue')
    },
    {
        path: '/impressum',
        name: 'impressum',
        component: () => import('@/views/ImpressumView.vue')
    },
    {
        path: '/about',
        name: 'about',
        component: () => import('@/views/AboutView.vue')
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

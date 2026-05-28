import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue'),
  },
  {
    path: '/',
    component: () => import('../views/Layout.vue'),
    redirect: '/activities',
    children: [
      {
        path: 'activities',
        name: 'ActivityList',
        component: () => import('../views/ActivityList.vue'),
      },
      {
        path: 'activities/create',
        name: 'ActivityCreate',
        component: () => import('../views/ActivityEdit.vue'),
      },
      {
        path: 'activities/:id/edit',
        name: 'ActivityEdit',
        component: () => import('../views/ActivityEdit.vue'),
      },
      {
        path: 'activities/:id/detail',
        name: 'ActivityDetail',
        component: () => import('../views/ActivityDetail.vue'),
      },
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('../views/Dashboard.vue'),
      },
      {
        path: 'campaign-admin/chunrihuanxin/:activityId',
        name: 'SpringRenewalAdmin',
        component: () => import('../views/campaign/SpringRenewalAdmin.vue'),
      },
    ],
  },
  // 具体活动 H5 页面（公开访问，无需登录）
  {
    path: '/campaign/chunrihuanxin/:activityId',
    name: 'ChunriHuanxin',
    component: () => import('../views/campaign/ChunriHuanxin.vue'),
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  // 具体活动页面允许公开访问
  if (to.name === 'ChunriHuanxin') {
    next()
    return
  }
  if (to.name !== 'Login' && !token) {
    next('/login')
  } else if (to.name === 'Login' && token) {
    next('/')
  } else {
    next()
  }
})

export default router

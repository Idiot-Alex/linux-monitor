import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

const children = [
  {
    path: '/server/list',
    name: 'server-list',
    component: () => import('../pages/server/list')
  }
];

const router = new Router({
  mode: 'hash',
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../pages/layout'),
      children: children,
      redirect: '/server/list'
    }
  ]
});

export default router;

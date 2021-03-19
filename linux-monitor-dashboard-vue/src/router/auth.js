import router from './index';
import store from '../store/index';
import Vue from 'vue';

// no need to check list
const accessList = [
  '/login'
]

router.beforeEach((to, from, next) => {
  if (accessList.indexOf(to.path) !== -1) {
    next();
    return;
  }
  // check user info
  if (!store.getters.getUserInfo) {
    if (from.path !== '/') {
      Vue.prototype.$notify.error({
        title: 'No Access',
        message: `from: ${from.path} ==> to: ${to.path}`,
        offset: 100
      });
    }
    next('/login');
    return;
  }
  next();
})
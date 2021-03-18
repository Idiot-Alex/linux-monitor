import Vue from 'vue';
import App from './App.vue';

import './assets/reset.css';
import 'element-ui/lib/theme-chalk/index.css';
import {
  Button, Notification
} from 'element-ui';

import router from './router';

Vue.config.productionTip = false;
Vue.use(Button);
Vue.use(Notification);

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');

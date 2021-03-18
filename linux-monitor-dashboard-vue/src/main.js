import Vue from 'vue';
import App from './App.vue';

import './assets/reset.css';
import 'element-ui/lib/theme-chalk/index.css';
import {
  Button, Notification,
  Container, Header, Aside, Main,
  Menu, Submenu, MenuItem,
} from 'element-ui';

import router from './router';

Vue.config.productionTip = false;
Vue.use(Button);
Vue.use(Notification);
Vue.use(Container);
Vue.use(Header);
Vue.use(Aside);
Vue.use(Main);
Vue.use(Menu);
Vue.use(Submenu);
Vue.use(MenuItem);

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');

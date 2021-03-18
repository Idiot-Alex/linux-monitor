import Vue from 'vue';
import App from './App.vue';

import './assets/reset.css';
import 'element-ui/lib/theme-chalk/index.css';
import {
  Button, Notification,
  Container, Header, Aside, Main,
  Menu, Submenu, MenuItem, MenuItemGroup,
} from 'element-ui';

import router from './router';
import store from './store';

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
Vue.use(MenuItemGroup);

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app');

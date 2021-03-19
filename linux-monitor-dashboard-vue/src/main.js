import Vue from 'vue';
import App from './App.vue';

import './assets/reset.css';
import 'element-ui/lib/theme-chalk/index.css';
import {
  Notification, Button,
  Container, Header, Aside, Main, Card,
  Menu, Submenu, MenuItem, MenuItemGroup,
  Form, FormItem, Input,
  Row, Col,
} from 'element-ui';

import router from './router';
import store from './store';
import './router/auth';

Vue.config.productionTip = false;
Vue.prototype.$notify = Notification;
Vue.use(Button);
Vue.use(Container);
Vue.use(Header);
Vue.use(Aside);
Vue.use(Main);
Vue.use(Card);
Vue.use(Menu);
Vue.use(Submenu);
Vue.use(MenuItem);
Vue.use(MenuItemGroup);
Vue.use(Form);
Vue.use(FormItem);
Vue.use(Input);
Vue.use(Row);
Vue.use(Col);

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app');

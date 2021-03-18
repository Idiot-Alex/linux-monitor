import Vue from 'vue';
import App from './App.vue';

import {
  Button, Notification
} from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/reset.css';

Vue.config.productionTip = false;
Vue.use(Button);
Vue.use(Notification);

new Vue({
  render: h => h(App),
}).$mount('#app');

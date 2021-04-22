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
  Progress,
} from 'element-ui';

import router from './router';
import store from './store';
import './router/auth';

// 引入 echarts 核心模块，核心模块提供了 echarts 使用必须要的接口。
import * as echarts from 'echarts/core';
// 引入柱状图图表，图表后缀都为 Chart
import {
  GaugeChart, PieChart
} from 'echarts/charts';
// 引入提示框，标题，直角坐标系组件，组件后缀都为 Component
import {
  TitleComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent,
  ToolboxComponent,
  VisualMapComponent,
  TimelineComponent,
  CalendarComponent
} from 'echarts/components';
// 引入 Canvas 渲染器，注意引入 CanvasRenderer 或者 SVGRenderer 是必须的一步
import { CanvasRenderer } from 'echarts/renderers';

// 注册必须的组件
echarts.use([
  TitleComponent, TooltipComponent, GridComponent,
  LegendComponent, ToolboxComponent, VisualMapComponent, TimelineComponent, CalendarComponent,
  CanvasRenderer,
  GaugeChart, PieChart
]);

Vue.config.productionTip = false;
Vue.prototype.$notify = Notification;
Vue.prototype.$echarts = echarts;
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
Vue.use(Progress);

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app');

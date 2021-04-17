<template>
  <div>
    <!-- filter -->
    <el-row :gutter="10">
      <el-col :span="6">
        <el-input v-model="listQuery.host" placeholder="Host name"/>
      </el-col>
      <el-col :span="6">
        <el-button type="primary" @click="loadData">Seach</el-button>
      </el-col>
    </el-row>
    <!-- server status card -->
    <el-row class="server-cards">
      <el-card v-for="server in serverList" :key="server.id">
        <div slot="header" class="clearfix">
          <span>{{server.host}}</span>
          <el-button style="float: right; padding: 3px 5px" type="text">top</el-button>
          <el-button style="float: right; padding: 3px 5px" type="text">terminal</el-button>
        </div>
        <div class="load-avg">
          <el-progress class="one" type="circle" :show-text="false" :percentage="calcLoadAvg(server.one, server.cores)" :width="80"></el-progress>
          <el-progress class="five" type="circle" :show-text="false" :percentage="calcLoadAvg(server.five, server.cores)" :width="60"></el-progress>
          <el-progress class="fifteen" type="circle" :show-text="false" :percentage="calcLoadAvg(server.fiften, server.cores)" :width="40"></el-progress>
        </div>
        <div :id="server.host"></div>
      </el-card>
    </el-row>
  </div>
</template>
<script>
import { getServerList, getServerProperties } from '../../api/server';
import { Chart } from '@antv/g2';
export default {
  data() {
    return {
      serverList: [],
      serverProperties: [],
      listQuery: {
        pageNo: 1,
        pageSize: 10
      },
      timer: null
    }
  },
  created() {
    this.loadData();
    this.loadProperties();
  },
  beforeDestroy() {
    if (this.timer) {
      clearInterval(this.timer);
    }
  },
  methods: {
    // load server list
    loadData() {
      getServerList(this.listQuery).then(res => {
        if (res.code === 200) {
          this.serverList = res.data.dataList;
        }
      })
    },
    // load server property
    loadProperties() {
      getServerProperties().then(res => {
        if (res.code === 200) {
          this.serverProperties = res.data;
        }
      })
      if (!this.timer) {
        this.timer = setInterval(() => {
          getServerProperties().then(res => {
            if (res.code === 200) {
              res.data.forEach(element => {
                this.serverList.forEach(server => {
                  if (element.host === server.host) {
                    server = Object.assign(server, element);
                    this.drawLoadAvgChart(server.host);
                  }
                })
              });
            }
          })
          this.serverList = [...this.serverList];
        }, 3000);
      }
    },
    // 计算负载
    calcLoadAvg(loadAvg, cores) {
      if (!loadAvg) {
        return 1;
      }
      if (cores === 0) {
        return 1
      }
      return loadAvg / cores * 100;
    },
    // 负载图表
    drawLoadAvgChart(containerId) {
      const data = [
        { type: '1-3秒', value: 0.16 },
        { type: '4-10秒', value: 0.125 },
        { type: '11-30秒', value: 0.2 },
        { type: '1-3分', value: 0.2 },
        { type: '3-10分', value: 0.05 },
        { type: '10-30分', value: 0.01 },
        { type: '30+分', value: 0.015 },
      ];

      const chart = new Chart({
        container: document.getElementById(containerId),
        autoFit: true,
        height: 500,
      });
      chart.data(data);

      chart.axis(false);
      chart.tooltip({
        showMarkers: false,
      });
      chart.coordinate('theta', { innerRadius: 0.35 });
      chart.legend(false);
      chart
        .interval({
          background: { style: { fill: '#F0F0F0', fillOpacity: 1 }},
        })
        .position('type*value')
        .color('type', (val) => {
          const { colors10 } = chart.getTheme();
          if (val === '10-30分' || val === '30+分') {
            return '#ff4d4f';
          }
          return colors10[0];
        })
        .style({
          lineCap: 'round',
          
        });
      chart.theme({
        maxColumnWidth: 12,
        minColumnWidth: 12,
      })
      chart.interaction('element-active')
      chart.annotation().html({
        position: ['50%', '50%'],
        html: () => {
          return `<div style="transform:translate(-50%,-50%)">
            <img alt="" width="100" height="80" src="https://gw.alipayobjects.com/mdn/rms_ef85c6/afts/img/A*0DYiQKP08cQAAAAAAAAAAAAAARQnAQ">
          </div>`
        }
      })
      chart.render();
    }
  }
}
</script>
<style scoped>
.server-cards {
  margin-top: 10px;
}
.load-avg {
  position: relative;
  display: inline-flex;
  width: 100px;
  height: 100px;
}
.one {
  position: absolute;
}
.five {
  position: absolute;
  margin-top: 15px;
  margin-left: 15px;
}
.fifteen {
  position: absolute;
  margin-top: 30px;
  margin-left: 30px;
}
</style>
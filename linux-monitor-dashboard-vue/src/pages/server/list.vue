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
        <div :id="server.host" class="load-avg"></div>
        <div :id="`${server.host}e`" class="load-avg"></div>
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
                    this.drawLoadAvgChart(server);
                    this.drawChart(server);
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
    drawChart(server) {
      if (!server.echart) {
        var chartDom = document.getElementById(server.host + 'e');
        server.echart = this.$echarts.init(chartDom, 'dark');
      }
      let option = {
        series: [{
            type: 'gauge',
            startAngle: 90,
            endAngle: -270,
            pointer: {
                show: false
            },
            progress: {
                show: true,
                overlap: false,
                roundCap: true,
                clip: false,
                itemStyle: {
                    borderWidth: 1,
                    borderColor: '#464646'
                }
            },
            axisLine: {

                lineStyle: {
                    width: 40
                }
            },
            splitLine: {
                show: false,
                distance: 0,
                length: 10
            },
            axisTick: {
                show: false
            },
            axisLabel: {
                show: false,
                distance: 10
            },
            data: [{
                value: this.calcLoadAvg(server.fifteen, server.cores),
                name: 'Perfect',
                title: {
                    offsetCenter: ['0%', '-30%']
                },
                detail: {
                    offsetCenter: ['0%', '-20%']
                }
            },
            {
                value: this.calcLoadAvg(server.five, server.cores),
                name: 'Good',
                title: {
                    offsetCenter: ['0%', '0%']
                },
                detail: {
                    offsetCenter: ['0%', '10%']
                }
            },
            {
                value: this.calcLoadAvg(server.one, server.cores),
                name: 'Commonly',
                title: {
                    offsetCenter: ['0%', '30%']
                },
                detail: {
                    offsetCenter: ['0%', '40%']
                }
            }
            ],
            title: {
                fontSize: 14
            },
            detail: {
                width: 30,
                height: 14,
                fontSize: 14,
                color: 'auto',
                borderColor: 'auto',
                borderRadius: 20,
                borderWidth: 1,
                formatter: '{value}%'
            }
        }]
      };
      server.echart.setOption(option);
    },
    drawLoadAvgChart(server) {
      // 先销毁图表
      if (server.chart) {
        server.chart.destroy();
      }
      const data = [
        { type: '1m', value: `${this.calcLoadAvg(server.one, server.cores)}%` },
        { type: '5m', value: `${this.calcLoadAvg(server.five, server.cores)}%` },
        { type: '15m', value: `${this.calcLoadAvg(server.fifteen, server.cores)}%` }
      ];

      server.chart = new Chart({
        container: document.getElementById(server.host),
        autoFit: true,
        height: 500,
      });
      server.chart.data(data);

      server.chart.axis(false);
      server.chart.tooltip({
        showMarkers: false,
      });
      server.chart.coordinate('theta', { innerRadius: 0.35 });
      server.chart.legend(false);
      server.chart.interval({
          background: { style: { fill: '#F0F0F0', fillOpacity: 1 }},
        })
        .position('type*value')
        // .color('type', (val) => {
        //   const { colors10 } = chart.getTheme();
        //   return colors10[0];
        // })
        .style({
          lineCap: 'round',
        });
      server.chart.theme({
        maxColumnWidth: 12,
        minColumnWidth: 12,
      })
      server.chart.interaction('element-active')
      server.chart.annotation().html({
        position: ['50%', '50%'],
        html: () => {
          return `<div style="transform:translate(-50%,-50%)">
            <img alt="" width="100" height="80" src="https://gw.alipayobjects.com/mdn/rms_ef85c6/afts/img/A*0DYiQKP08cQAAAAAAAAAAAAAARQnAQ">
          </div>`
        }
      })
      server.chart.render();
    }
  }
}
</script>
<style scoped>
.server-cards {
  margin-top: 10px;
}
.load-avg {
  width: 200px;
  height: 200px;
}
</style>
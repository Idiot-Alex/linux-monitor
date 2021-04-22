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
        <div :id="`${server.host}-load-avg`" class="load-avg chart"></div>
        <div :id="`${server.host}-mem`" class="mem chart"></div>
      </el-card>
    </el-row>
  </div>
</template>
<script>
import { getServerList, getServerProperties } from '../../api/server';
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
                    this.drawMemChart(server);
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
        return 0.1;
      }
      if (cores === 0) {
        return 0.1
      }
      return Number(loadAvg / cores * 100).toFixed(2);
    },
    // 负载图表
    drawLoadAvgChart(server) {
      if (!server.load_avg_echart) {
        let load_avg_echart = document.getElementById(`${server.host}-load-avg`);
        server.load_avg_echart = this.$echarts.init(load_avg_echart, 'dark');
        server.load_avg_option = {
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
                  value: this.calcLoadAvg(server.one, server.cores),
                  name: '1m',
                  title: {
                      offsetCenter: ['0%', '-55%']
                  },
                  detail: {
                      offsetCenter: ['0%', '-35%']
                  }
              },
              {
                  value: this.calcLoadAvg(server.five, server.cores),
                  name: '5m',
                  title: {
                      offsetCenter: ['0%', '-15%']
                  },
                  detail: {
                      offsetCenter: ['0%', '5%']
                  }
              },
              {
                  value: this.calcLoadAvg(server.fifteen, server.cores),
                  name: '15m',
                  title: {
                      offsetCenter: ['0%', '25%']
                  },
                  detail: {
                      offsetCenter: ['0%', '45%']
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
                  color: 'inherit',
                  borderColor: 'inherit',
                  borderRadius: 20,
                  borderWidth: 1,
                  formatter: '{value}%'
              }
          }]
        };
      }
      server.load_avg_option.series[0].data[0].value = this.calcLoadAvg(server.one, server.cores);
      server.load_avg_option.series[0].data[1].value = this.calcLoadAvg(server.five, server.cores);
      server.load_avg_option.series[0].data[2].value = this.calcLoadAvg(server.fifteen, server.cores);
      server.load_avg_echart.setOption(server.load_avg_option, true);
    },
    // 内存图表
    drawMemChart(server) {
      if (!server.mem_echart) {
        let mem_echart = document.getElementById(`${server.host}-mem`);
        server.mem_echart = this.$echarts.init(mem_echart, 'dark');
        server.mem_option = {
            tooltip: {
                trigger: 'item'
            },
            legend: {
                top: '5%',
                left: 'center'
            },
            series: [
                {
                    name: '内存 MB',
                    type: 'pie',
                    radius: ['40%', '70%'],
                    avoidLabelOverlap: false,
                    label: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        label: {
                            show: true,
                            fontSize: '40',
                            fontWeight: 'bold'
                        }
                    },
                    labelLine: {
                        show: false
                    },
                    data: [
                        {value: server.memUsed, name: '已使用'},
                        {value: server.memCache, name: '页面缓存'},
                        {value: server.memFree, name: '可用'}
                    ]
                }
            ]
        };
      }
      server.mem_option.series[0].data[0].value = server.memUsed;
      server.mem_option.series[0].data[1].value = server.memCache;
      server.mem_option.series[0].data[2].value = server.memFree;
      server.mem_echart.setOption(server.mem_option, true);
    }
  }
}
</script>
<style scoped>
.server-cards {
  margin-top: 10px;
}
.load-avg {
  width: 300px;
  height: 300px;
}
.mem {
  width: 300px;
  height: 300px;
}
.chart {
  display: table-cell;
}
</style>
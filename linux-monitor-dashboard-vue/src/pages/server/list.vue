<template>
  <div>
    <!-- filter -->
    <el-row :gutter="10">
      <el-col :span="6">
        <el-input v-model="listQuery.host" placeholder="Host name"/>
      </el-col>
      <el-col :span="6">
        <el-button type="primary" @click="loadData">Seach</el-button>
        <el-button type="primary" @click="addServer">Add</el-button>
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
        <div class="charts">
          <div :id="`${server.host}-load-avg`" class="load-avg chart"></div>
          <div :id="`${server.host}-mem`" class="mem chart"></div>
          <div :id="`${server.host}-cpu`" class="cpu chart"></div>
        </div>
      </el-card>
    </el-row>
    <!-- edit dialog -->
    <el-dialog title="Edit Server" :visible.sync="dialogVisible" width="30%">
      <el-form ref="form" :model="temp" label-width="100px">
        <el-form-item label="Host Name">
          <el-input v-model="temp.host" placeholder="Host Name"/>
        </el-form-item>
        <el-form-item label="User">
          <el-input v-model="temp.user" placeholder="User"/>
        </el-form-item>
        <el-form-item label="Password">
          <el-input type="password" v-model="temp.password" placeholder="Password"/>
        </el-form-item>
        <el-form-item label="Port">
          <el-input-number v-model.number="temp.port" placeholder="Port"/>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="submitServer">Confirm</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import { getServerList, getServerProperties, editServer } from '../../api/server';
export default {
  data() {
    return {
      serverList: [],
      serverProperties: [],
      listQuery: {
        pageNo: 1,
        pageSize: 10
      },
      timer: null,
      dialogVisible: false,
      temp: {}
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
                    this.drawCpuChart(server);
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
              startAngle: -90,
              endAngle: -450,
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
                bottom: '5px',
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
    },
    // CPU 图表
    drawCpuChart(server) {
      if (!server.cpu_echart) {
        let cpu_echart = document.getElementById(`${server.host}-cpu`);
        server.cpu_echart = this.$echarts.init(cpu_echart, 'dark');
        server.cpu_option = {
            series: [{
                type: 'gauge',
                anchor: {
                    show: true,
                    showAbove: true,
                    size: 18,
                    itemStyle: {
                        color: '#FAC858'
                    }
                },
                pointer: {
                    icon: 'path://M2.9,0.7L2.9,0.7c1.4,0,2.6,1.2,2.6,2.6v115c0,1.4-1.2,2.6-2.6,2.6l0,0c-1.4,0-2.6-1.2-2.6-2.6V3.3C0.3,1.9,1.4,0.7,2.9,0.7z',
                    width: 2,
                    length: '80%',
                    offsetCenter: [0, '8%']
                },

                progress: {
                    show: true,
                    overlap: true,
                    roundCap: true
                },
                axisLine: {
                    roundCap: true
                },
                data: [{
                    value: 0,
                    name: '系统',
                    title: {
                        offsetCenter: ['-90%', '90%']
                    },
                    detail: {
                        offsetCenter: ['-90%', '115%']
                    }
                },
                {
                    value: 0,
                    name: '用户',
                    title: {
                        offsetCenter: ['-30%', '90%']
                    },
                    detail: {
                        offsetCenter: ['-30%', '115%']
                    }
                },
                {
                    value: 0,
                    name: 'IO 等待',
                    title: {
                        offsetCenter: ['30%', '90%']
                    },
                    detail: {
                        offsetCenter: ['30%', '115%']
                    }
                },
                {
                    value: 0,
                    name: 'STEAL',
                    title: {
                        offsetCenter: ['90%', '90%']
                    },
                    detail: {
                        offsetCenter: ['90%', '115%']
                    }
                },
                {
                    value: 0,
                    name: 'CPU'
                }
                ],
                title: {
                    fontSize: 14
                },
                detail: {
                    width: 40,
                    height: 14,
                    fontSize: 14,
                    color: '#fff',
                    backgroundColor: 'inherit',
                    borderRadius: 3,
                    formatter: '{value}%'
                }
            }]
        };
        
      }
      server.cpu_option.series[0].data[0].value = server.cpuSys || 0;
      server.cpu_option.series[0].data[1].value = server.cpuUsr || 0;
      server.cpu_option.series[0].data[2].value = server.cpuIoWait || 0;
      server.cpu_option.series[0].data[3].value = server.cpuSteal || 0;
      server.cpu_option.series[0].data[4].value = server.cpuUsage || 0;
      server.cpu_echart.setOption(server.cpu_option, true);
    },
    // add server
    addServer() {
      this.temp = {
        port: 22
      };
      this.dialogVisible = true;
    },
    // submit server
    submitServer() {
      editServer(this.temp).then(res => {
        if (res.code === 200) {
          this.$notify.success({
            title: 'Success',
            message: res.message,
            offset: 100,
            duration: 2 * 1000
          });
          // close dialog
          this.dialogVisible = false;
          this.loadData();
        }
      })
    }
  }
}
</script>
<style scoped>
.server-cards {
  margin-top: 10px;
}
.charts {
  display: flex;
}
.load-avg {
  width: 300px;
  height: 300px;
}
.mem {
  width: 300px;
  height: 300px;
}
.cpu {
  width: 300px;
  height: 300px;
}
.chart {
  flex: 0 0 300px;
}
</style>
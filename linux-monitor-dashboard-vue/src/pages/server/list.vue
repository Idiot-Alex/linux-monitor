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
          <el-progress class="one" type="circle" :show-text="false" :percentage="calcLoadAvg(server.one, server.cores)" :width="100"></el-progress>
          <el-progress class="five" type="circle" :show-text="false" :percentage="calcLoadAvg(server.five, server.cores)" :width="70"></el-progress>
          <el-progress class="fifteen" type="circle" :show-text="false" :percentage="calcLoadAvg(server.fiften, server.cores)" :width="40"></el-progress>
        </div>
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
                  }
                })
              });
            }
          })
          this.serverList = [...this.serverList];
        }, 3000);
      }
    },
    calcLoadAvg(loadAvg, cores) {
      if (!loadAvg) {
        return 1;
      }
      if (cores === 0) {
        return 1
      }
      return loadAvg / cores * 100;
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
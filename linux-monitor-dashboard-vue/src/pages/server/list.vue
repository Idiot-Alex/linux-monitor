<template>
  <div>
    <!-- filter -->
    <el-row :gutter="10">
      <el-col :span="6">
        <el-input v-model="listQuery.host" placeholder="Host name"/>
      </el-col>
      <el-col :span="6">
        <el-button type="primary">Seach</el-button>
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
        <div class="load-avg" v-for="i in 4" :key="i">
          <el-progress class="one" type="circle" :percentage="25" :width="100"></el-progress>
          <el-progress class="five" type="circle" :percentage="25" :width="60"></el-progress>
          <el-progress class="fifteen" type="circle" :percentage="25" :width="30"></el-progress>
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
      }
    }
  },
  created() {
    this.loadData();
    this.loadProperties();
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
  margin-top: 20px;
  margin-left: 20px;
}
.fifteen {
  position: absolute;
  margin-top: 35px;
  margin-left: 35px;
}
</style>
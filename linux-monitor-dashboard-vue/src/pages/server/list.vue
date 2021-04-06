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
        {{server.host}}
      </el-card>
    </el-row>
    <el-progress type="circle" :percentage="25" :width="100"></el-progress>
    <el-progress type="circle" :percentage="25" :width="60"></el-progress>
    <el-progress type="circle" :percentage="25" :width="30"></el-progress>
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
</style>
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
        ...
      </el-card>
    </el-row>
  </div>
</template>
<script>
import { getServerList } from '../../api/server';
export default {
  data() {
    return {
      serverList: [],
      listQuery: {
        pageNo: 1,
        pageSize: 10,
        host: ''
      }
    }
  },
  created() {
    this.loadData();
    console.log(this.serverList)
  },
  methods: {
    // load server list
    loadData() {
      getServerList(this.listQuery).then(res => {
        console.log(res)
        if (res.code === 200) {
          this.serverList = res.dataList;
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
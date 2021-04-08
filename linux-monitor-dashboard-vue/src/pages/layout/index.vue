<template>
  <el-container>
    <side-menu />
    <el-container>
      <el-header>
        <top-header />
      </el-header>
      <el-main class="main">
        <keep-alive>
          <router-view/>
        </keep-alive>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
import SideMenu from './side-menu';
import TopHeader from './top-header';
export default {
  components: {
    SideMenu,
    TopHeader,
  },
  data() {
    return {

    }
  }
}
</script>
<style>
.main {
  height: calc(100vh - 100px);
  overflow-y: scroll;
}
</style>
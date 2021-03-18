<template>
  <el-container>
    <el-aside class="aside">
      <side-menu />
    </el-aside>
    <el-container>
      <el-header>
        <top-header />
      </el-header>
      <el-main>main</el-main>
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
.aside {
  width: auto !important;
}
</style>
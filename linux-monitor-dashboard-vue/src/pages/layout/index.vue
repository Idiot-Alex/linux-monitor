<template>
  <el-container>
    <side-menu />
    <el-container>
      <el-header>
        <top-header />
      </el-header>
      <el-main>
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

</style>
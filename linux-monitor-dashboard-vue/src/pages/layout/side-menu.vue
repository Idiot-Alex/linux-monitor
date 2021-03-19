<template>
  <el-menu
    :collapse="getIsCollapse"
    default-active="1-1"
    class="el-menu-vertical"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b">
    <el-submenu :index="menu.id" v-for="menu in menuList" :key="menu.id">
      <template slot="title">
        <i :class="menu.icon"></i>
        <span>{{menu.title}}</span>
      </template>
      <el-menu-item :index="subMenu.id" v-for="subMenu in menu.children" :key="subMenu.id">{{subMenu.title}}</el-menu-item>
    </el-submenu>
  </el-menu>
</template>
<script>
import { mapGetters } from 'vuex';
import { MENU_LIST } from '../../utils/const';
export default {
  data() {
    return {
      menuList: []
    }
  },
  computed: {
    ...mapGetters([
      'getIsCollapse'
    ])
  },
  mounted() {
    this.menuList = MENU_LIST;
  }
}
</script>
<style>
.el-menu-vertical {
  max-width: 200px;
  height: 100vh;
  transition: 0.2s;
}
.el-menu-vertical:not(.el-menu--collapse) {
    width: 200px;
  }
</style>
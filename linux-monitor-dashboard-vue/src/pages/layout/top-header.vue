<template>
  <div class="header">
    <i class="el-icon-s-fold collapse"></i>
    <i class="el-icon-s-unfold collapse"></i>
  </div>
</template>
<script>
export default {
  data() {
    return {

    }
  }
}
</script>
<style>
.el-header {
  padding: 0;
}
</style>
<style scoped>
.header {
  height: 60px;
  background-color: #545c64;
}
.collapse {
  line-height: 60px;
  margin: 0 5px;
  font-size: 25px;
  color: #fff;
  cursor: pointer;
}
</style>
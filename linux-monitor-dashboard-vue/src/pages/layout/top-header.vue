<template>
  <div class="header">
    <i v-if="getIsCollapse" class="el-icon-s-unfold collapse" @click="collapse"></i>
    <i v-else class="el-icon-s-fold collapse" @click="collapse"></i>
  </div>
</template>
<script>
import { mapGetters } from 'vuex';
export default {
  data() {
    return {

    }
  },
  computed: {
    ...mapGetters([
      'getIsCollapse'
    ])
  },
  methods: {
    // collapse aside menu
    collapse() {
      this.$store.dispatch('collapse');
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
  z-index: 99;
}
.collapse {
  line-height: 60px;
  margin: 0 5px;
  font-size: 25px;
  color: #fff;
  cursor: pointer;
}
</style>
<template>
  <div>
    <el-card class="login-card">
      <h2 class="title">Linux Monitor Dashboard</h2>
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="用户名">
          <el-input v-model="form.userName"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password" type="password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>
<script>
import { adminUserLogin } from '../../api/admin-user';
export default {
  data() {
    return {
      form: {
        userName: '',
        password: ''
      }
    }
  },
  methods: {
    onSubmit() {
      adminUserLogin(this.form).then(res => {
        if (res.code === 200) {
          this.$notify.success({
            title: 'Success',
            message: res.message,
            offset: 100
          })
        }
      })
    }
  }
}
</script>
<style scoped>
.title {
  text-align: center;
}
.login-card {
  position: absolute;
  top: 40%;
  left: 50%;
  transform: translate(-50%,-50%);
  width: 500px;
}
</style>
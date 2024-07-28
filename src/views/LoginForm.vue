<template>
  <div class="image-background">
    <el-card class="container">
      <h2>登录</h2>
      <el-form :model="form" ref="form">
        <el-form-item label="用户名">
          <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input type="password" v-model="form.password" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-button type="primary" @click="login">登录</el-button>
        <el-button type="default" @click="goToRegister" class="register-button">注册</el-button>
      </el-form>
    </el-card>
  </div>

</template>

<script>
import { userLoginService } from '@/api/user.js';

export default {
  data() {
    return {
      form: {
        username: '',
        password: ''
      }
    };
  },
  methods: {
    async login() {
      try {
        const response = await userLoginService(this.form);
        const result = response.data;

        if (result.code === 0) {
          alert(result.message ? result.message : '登录成功');
      this.$router.push('/welcome')
        } else {
          alert(result.message ? result.message : '登录失败');
        }
      } catch (error) {
        alert('登录请求失败');
      }
    },
    goToRegister() {
      this.$router.push('/register');
    }
  }
};
</script>
<style scoped>
body {
  font-family: Arial, sans-serif;
  margin: 0;
  overflow: hidden;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.image-background {
  background-image: url('../assets/bk.webp'); /* 返回两级目录到项目根目录，然后进入assets文件夹 */
  background-size: cover;
  background-position: center; /* 确保背景图片居中 */
  background-repeat: no-repeat;
  height: 100vh; /* 使背景图片占满整个视口高度 */
  width: 100vw; /* 使背景图片占满整个视口宽度 */
  position: absolute; /* 使背景图片覆盖整个屏幕 */
  top: 0;
  left: 0;
  display: flex;
  justify-content: center;
  align-items: center;
}

.container {
  background: rgba(255, 255, 255, 0.8); /* 添加半透明背景色 */
  padding: 40px;
  border-radius: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 600px; /* 调整为更大的宽度 */
  min-width: 400px; /* 设置最小宽度 */
  text-align: center;
  transform: translateX(-300px); /* 向左移动50px */
}

h2 {
  margin-bottom: 20px;
  color: #333;
}

.el-form-item__label {
  color: #333;
}

.el-input__inner {
  background-color: rgba(255, 255, 255, 0.9);
}

.el-button--primary {
  background-color: #ff7043;
  border-color: #ff7043;
}

.el-button--primary:hover {
  background-color: #ff5722;
  border-color: #ff5722;
}
</style>
<template>
  <div class="image-background">
    <el-card class="container">
      <h2>注册</h2>
      <el-form :model="form" :rules="rules" ref="form">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="form.password" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="confirmPassword">
          <el-input type="password" v-model="form.confirmPassword" placeholder="请确认密码"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入手机号"></el-input>
        </el-form-item>
        <el-button type="primary" @click="register">注册</el-button>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { userRegisterService } from '@/api/user.js';

export default {
  data() {
    return {
      form: {
        username: '',
        email: '',
        password: '',
        confirmPassword: '',
        phone: '',
        address: ''
      },
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'}
        ],
        email: [
          {required: true, message: '请输入邮箱', trigger: 'blur'},
          {type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ],
        confirmPassword: [
          {required: true, message: '请确认密码', trigger: 'blur'},
          {
            validator: (rule, value, callback) => {
              if (value !== this.form.password) {
                callback(new Error('两次输入的密码不一致'));
              } else {
                callback();
              }
            }, trigger: 'blur'
          }
        ],
        address: [
          {required: true, message: '请输入地址', trigger: 'blur'}
        ],
        phone: [
          {required: true, message: '请输入手机号', trigger: 'blur'},
          {pattern: /^[0-9]{11}$/, message: '请输入正确的手机号', trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    async register() {
      try {
        const response = await userRegisterService(this.form);
        const result = response.data;

        console.log('Result Code:', result.code);
        console.log('Result Message:', result.message);

        if (result.code=== 0) {
          alert(result.message ? result.message : '注册成功');
      this.$router.push('/login')
        } else {
          alert('注册失败');
        }
      } catch (error) {
        console.error('注册请求失败', error);
        alert('注册请求失败');
      }
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
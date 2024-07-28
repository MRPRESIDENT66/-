<template>
  <div>
    <h2>修改密码</h2>
    <div>
      旧密码：
      <input type="password" v-model="oldPassword" placeholder="请输入旧密码">
    </div>
    <div>
      新密码：
      <input type="password" v-model="newPassword" placeholder="请输入新密码6-12位字母、数字及字符组合">
    </div>
    <div>
      确认新密码：
      <input type="password" v-model="confirmPassword" placeholder="请再次输入新密码6-12位字母、数字及字符组合">
    </div>
    <button @click="save">保存</button>
  </div>
</template>


<script>
import {changePassword} from "@/api/user.js";

export default {
  name: 'PChangePassword',
  data() {
    return {
      oldPassword: '',
      newPassword: '',
      confirmPassword: ''
    };
  },
  methods: {
    async save() {
      if (this.newPassword !== this.confirmPassword) {
        alert('新密码与确认新密码不匹配');
        return;
      }
      const response = await changePassword(this.oldPassword, this.newPassword, this.confirmPassword);
      const result = response.data;
      if (result.code === 0) {
        alert(result.data);
      }
      if (result.code === 1) {
        alert(result.message);
      }

    }
  }
};
</script>


<style>
/* 添加你的样式 */


.component-container {
  background-color: #fff;
  padding: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
}

h2 {
  margin-bottom: 20px;
  color: #333;
}

.input-container {
  display: flex;
  flex-direction: column;
  margin-bottom: 10px;
}

.input-container label {
  margin-bottom: 5px;
}

input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  box-sizing: border-box;
}

button {
  margin-top: 10px;
  margin-right: 10px;
  padding: 10px 20px;
  background-color: #ff0000; /* 浅绿色 */
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #ff0000;
}

.action-button {
  margin-right: 20px; /* 增加按钮之间的间距 */
}


</style>
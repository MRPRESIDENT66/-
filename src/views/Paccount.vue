<template>
  <el-container>
    <el-header>
      <h2>用户信息</h2>
    </el-header>
    <el-main>
      <el-form :model="user" label-width="100px" class="user-form">
        <el-form-item label="用户名:">
          <el-input v-model="user.username"></el-input>
        </el-form-item>
        <el-form-item label="邮箱:">
          <el-input v-model="user.email"></el-input>
        </el-form-item>
        <el-form-item label="手机号:">
          <el-input v-model="user.phoneNumber"></el-input>
        </el-form-item>
        <el-form-item label="地址:">
          <el-input v-model="user.address"></el-input>
        </el-form-item>
        <el-form-item label="创建时间:">
          <el-input v-model="user.createdAt" disabled></el-input>
        </el-form-item>
        <el-form-item label="更新时间:">
          <el-input v-model="user.updatedAt" disabled></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="updateUserInfo">修改</el-button>
        </el-form-item>
      </el-form>
    </el-main>
  </el-container>
</template>

<script>
import {getUserInfo} from "@/api/user.js";
import {onMounted, reactive} from "vue";
import {updateuser} from "@/api/user.js"

export default {
  setup() {
    const user = reactive({
      userId: '',
      username: '',
      email: '',
      phoneNumber: '',
      address: '',
      createdAt: '',
      updatedAt: '',
    });
    onMounted(async () => {
      try {
        const response = await getUserInfo();
        Object.assign(user, response.data.data);
      } catch (error) {
        console.error('Error fetching user info:', error);
      }
    });
    const updateUserInfo=async () =>{
      try{
        const response = await updateuser(user);
        const result = response.data;
        if (result.code=== 0) {
          alert(result.message ? result.message : '修改成功');
          //this.$router.push('/login')
        } if(result.code===1) {
          alert('修改失败');
        }
      }catch (error) {
        alert('修改发生错误');
      }

    }



    return{
      user,
      updateUserInfo
    };


  }
}
</script>

<style scoped>
.user-form {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  background-color: #f9f9f9;
}
</style>


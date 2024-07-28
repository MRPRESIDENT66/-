<template>
    <div id="app" class="app-container">
      <div class="sidebar">
        <div class="logo">
          <img src="@/assets/brand.jpg" alt="logo">
        </div>
        <nav>
          <ul>
            <li><router-link to="/welcome">返回首页</router-link></li>
            <li><router-link to="/fashion">点餐</router-link></li>
            <li><router-link to="/discount">优惠活动</router-link></li>
            <li><router-link to="/cart">购物车</router-link></li>
            <li><router-link to="/sending">配送信息</router-link></li>
            <li class="active">我的账户</li>
          </ul>
        </nav>
        <div class="contact-info">
          <p>114514-1919810</p >
          <p>下北泽备案信息</p >
        </div>
      </div>
      <div class="main-content">
        <div class="blank">
          <div class="header">
            <button class="avatar-btn" @click="showAvatarSelection = !showAvatarSelection">
              <img :src="selectedAvatar" alt="User Avatar" class="avatar" style="width: 100px; height: 100px;">
            </button>
      <!-- 备选头像区域，根据 showAvatarSelection 控制是否显示 -->
            <div class="avatar-selection" v-if="showAvatarSelection">
              <img v-for="(avatar, index) in avatars" :key="index" :src="avatar" :alt="'Avatar ' + (index + 1)"
                  :class="{ selected: selectedAvatar === avatar }" @click="selectAvatar(avatar)">
            </div>
          </div>
          <div class="navigation">
              <button @click="currentView = 'Paccount'">个人信息</button>
              <button @click="currentView = 'PAddressList'">我的地址</button>
              <button @click="currentView = 'PChangePassword'">修改密码</button>
          </div>
        <component :is="currentView" @change-view="changeView"></component>
        </div>
      </div>
    </div>
  </template>
  
 <script>
  import Paccount from './Paccount.vue';
  import PChangePassword from './PChangePassword.vue';
  import PAddressList from './PAddressList.vue';
  import PAddAddress from './PAddAddress.vue';
  
  import avatar1 from '@/assets/male.jpg';
  import avatar2 from '@/assets/female.png';
  
  
  export default {
    data() {
      return {
        currentView: 'Paccount',
        selectedAvatar: avatar1, // 默认头像
        avatars: [avatar1, avatar2,],
        showAvatarSelection: false // 控制是否显示备选头像
      };
    },
    components: {
      Paccount,
      PChangePassword,
      PAddressList,
      PAddAddress
    },
    methods: {
      changeView(view) {
        this.currentView = view;
      },
      selectAvatar(avatar) {
        this.selectedAvatar = avatar;
        this.showAvatarSelection = false; // 选择后隐藏备选头像
      }
    }
  };
  </script>
<style scoped>
  .app-container {
    display: flex;
    height: 100vh;
    font-family: Arial, sans-serif;
  }
  
  .sidebar {
    width: 250px;
    background-color: #f7f7f7;
    padding: 20px;
    box-shadow: 2px 0 5px rgba(0,0,0,0.1);
  }
  
  .sidebar .logo {
    text-align: center;
    margin-bottom: 20px;
  }
  
  .sidebar .logo img {
    max-width: 100%;
  }
  
  .sidebar nav ul {
    list-style: none;
    padding: 0;
  }
  
  .sidebar nav ul li {
    padding: 10px 0;
    font-size: 18px;
    cursor: pointer;
  }
  
  .sidebar nav ul li:hover {
    color: #ff0000;
  }
  
  .sidebar .contact-info {
    position: absolute;
    bottom: 20px;
    font-size: 14px;
  }
  
  .main-content {
    flex: 1; /* 保持flex布局的伸缩性 */
    width: 100vw; /* 设置容器宽度为100% */
    max-width: 900px; /* 可以设置一个最大宽度，根据需要调整 */
    background-color: #fff;
    padding: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
    overflow: hidden;
  }
  
  .promo-image {
    width: 100%; /* 设置图片宽度为100% */
    height: auto; /* 设置图片高度为100% */
    object-fit: fill; /* 拉伸图片以填满容器，可能会裁剪图片 */
  }
  .blank {
  display: flex;
  flex-direction: column;
  align-items: center; /* 水平居中 */
  justify-content: center;
  margin: 0 10px; /* 添加右侧边距 */
  padding: 20px;
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 添加阴影 */
  border-radius: 8px; /* 添加圆角 */
}

/* 头部样式 */
.header {
  margin-bottom: 20px;
  display: flex;
  align-items: center;
  justify-content: space-between; /* 平均分布项目 */
  padding: 10px 20px;
  background-color: #ffffff;
  color: white;
  border-radius: 5px;
}

/* 导航样式 */
.navigation {
  margin-bottom: 20px;
  display: flex;
  align-items: center;
}

/* 导航按钮样式 */
.nav-button {
  margin-right: 10px;
  padding: 10px 20px;
  background-color: #ff0000; /* 浅绿色 */
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s; /* 平滑过渡效果 */
}

.nav-button:hover {
  background-color: #ff0000;
}

/* 组件容器样式 */
.component-container {
  margin-bottom: 20px;
  padding: 20px;
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
}

/* 输入框样式 */
input {
  display: block;
  margin-bottom: 10px;
  padding: 10px;
  width: calc(100% - 22px); /* 宽度减去padding */
  border: 1px solid #ddd;
  border-radius: 4px;
  box-sizing: border-box; /* 防止边框和内填充撑大盒子 */
}

/* 动作按钮样式 */
.action-button {
  margin-top: 10px;
  margin-right: 20px;
  padding: 10px 20px;
  background-color: #ff0000;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.action-button:hover {
  background-color: #ff0000;
}

/* 头像按钮样式 */
.avatar-btn {
  background: none;
  border: none;
  padding: 0;
  cursor: pointer;
}

/* 头像选择样式 */
.avatar-selection {
  display: flex;
  align-items: center;
  gap: 10px;
}

.avatar-selection img {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  cursor: pointer;
  border: 2px solid transparent;
  transition: border-color 0.3s;
}

.avatar-selection img.selected {
  border-color: #000;
}
</style>
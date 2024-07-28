<template>
  <div id="app" class="app-container">
    <div class="sidebar">
      <div class="logo">
        <img src="@/assets/brand.jpg" alt="logo">
      </div>
      <nav>
        <ul>
          <li class="active">返回首页</li>
          <li><router-link to="/fashion">点餐</router-link></li>
          <li><router-link to="/discount">优惠活动</router-link></li>
          <li><router-link to="/cart">购物车</router-link></li>
          <li><router-link to="/sending">配送信息</router-link></li>
          <li><router-link to="/Pmainpage">我的账户</router-link></li>
        </ul>
      </nav>
      <div class="contact-info">
        <p>114514-1919810</p >
        <p>下北泽备案信息</p >
      </div>
    </div>
    <div class="main-content">
      <img src="@/assets/background.jpg" alt="promo" class="promo-image">
    </div>
  </div>
</template>

<script>
export default {
  name: 'App',
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
</style>
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
            <li class="active">配送信息</li>
            <li><router-link to="/Pmainpage">我的账户</router-link></li>
          </ul>
        </nav>
        <div class="contact-info">
          <p>114514-1919810</p >
          <p>下北泽备案信息</p >
        </div>
      </div>
      <div class="main-content">
        <div class="delivery-container">
            <img src="@/assets/map2.jpg" alt="Delivery" class="delivery-banner">
            <div class="delivery-info">
                <div class="info-header">
                    <!-- 物流信息标题或图片 -->
                </div>
                <ul class="item-list">
                  <li v-for="item in orders" :key="item.orderId" class="item">
                    订单号: {{ item.orderId }}<br>
<!--                    用户名: {{ item.userId }}<br>-->
                    总价格: ¥{{ item.totalAmount }}<br>
                    订单状态: {{ item.status }}<br>
                    创建时间: {{ item.createdAt }}
<!--                    更新时间: {{ item.updatedAt }}-->
                  </li>
                </ul>
            </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import {onMounted, reactive} from "vue";
  import {checkoutinfo, getUserInfo, updateuser} from "@/api/user.js";

  // 假设你有一个获取订单信息的API方法
  // import { fetchOrders } from '@/api/user';
  //
  export default {
    setup() {
      const user = reactive({
          orderId: '',
          userId: '',
          totalAmount: '',
          status: '',
          createdAt: '',
          updatedAt: '',
        });
      const orders = reactive([]);
      onMounted(async () => {
        try {
          const response = await checkoutinfo();
          Object.assign(user, response.data.data); // 假设用户信息在 response.data.data.user 中
          orders.splice(0, orders.length);
          orders.push(...response.data.data);
        } catch (error) {
          console.error('Error fetching user info:', error);
        }
      });

      return{
        orders,
      };


    },
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
  .delivery-container {
    flex: 1; /* 保持flex布局的伸缩性 */
    width: 100vw;
  display: flex;
  flex-direction: column;
  height: 100vh;
  overflow-y: auto;
}

.delivery-banner {
  width: 100%;
  object-fit: cover;
  height: 50%; /* 占容器高度的一半 */
}

.delivery-info {
  flex: 1;
  background-color: #f7f7f7;
  padding: 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.info-header {
  /* 可以添加一些标题或图片样式 */
  width: 100%;
  text-align: center;
  margin-bottom: 20px;
}

.item-list {
  list-style-type: none;
  padding: 0;
  width: 100%;
}

.item {
  background-color: #ffffff;
  padding: 10px;
  margin-bottom: 10px;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  text-align: left;
  width: 80%; /* 或者其他宽度，根据实际需求调整 */
}
</style>
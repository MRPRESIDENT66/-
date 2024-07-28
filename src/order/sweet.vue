<template>
  <div id="app">
    <div class="sidebar">
      <div class="logo">
        <img src="@/assets/brand.jpg" alt="logo">
      </div>
      <nav>
        <ul>
          <li><router-link to="/welcome">返回首页</router-link></li>
          <li><router-link to="/fashion">潮新品</router-link></li>
          <li><router-link to="/pizza">比萨</router-link></li>
          <li><router-link to="/steak">牛排</router-link></li>
          <li><router-link to="/rice">面饭</router-link></li>
          <li><router-link to="/dessert">小食</router-link></li>
          <li class="active">甜品</li>
          <li><router-link to="/drink">饮料</router-link></li>
          <li><router-link to="/soup">汤类</router-link></li>
          <li><router-link to="/single">个人悠享</router-link></li>
          <li><router-link to="/discount">套餐优惠</router-link></li>
        </ul>
      </nav>
    </div>
    <div class="main-content">
      <header class="header">
        <div class="header-content">
          <span>晚间营业至24:00啦！</span>
        </div>
      </header>
      <section class="products">
        <div class="product-category">
          <h2>甜品</h2>
          <div class="product-list">
            <div class="product-card" v-for="(product, index) in products" :key="index">
              <img :src="product.image" :alt="product.name">
              <div class="product-info">
                <h3>{{ product.name }}</h3>
                <p>{{ product.description }}</p >
                <p>¥ {{ product.price }}</p >
                <div class="quantity-control">
                  <button @click="updateQuantity(product.id, -1)">-</button>
                  <span>{{ product.quantity }}</span>
                  <button @click="updateQuantity(product.id, 1)">+</button>
                </div>
              </div>
            </div>
          </div>
          <div v-if="message" class="message">{{ message }}</div>
        </div>
      </section>
    </div>
  </div>
</template>

<script>
import { addcart } from "@/api/user.js";
export default {
name: 'App',
data() {
  return {
    products: [
      {
        id: 16,
        name: "夏日甜品",
        description: "清新的仙草搭配Q弹的芋圆，冰凉甜蜜，是夏日消暑的最佳甜品。每一口都能感受到热带风情，带来清凉一夏。",
        price: 30,
        image: new URL('@/assets/sweet1.jpg', import.meta.url).href,
        quantity: 0,
      },
      {
        id: 17,
        name: "草莓蛋糕",
        description: "松软的蛋糕层与细腻的奶油层层叠加，顶部和夹层间点缀着新鲜草莓，酸甜可口，是庆祝生日或任何特别时刻的完美选择。",
        price: 25,
        image: new URL('@/assets/sweet2.jpg', import.meta.url).href,
        quantity: 0,
      },
      {
        id: 18,
        name: "草莓巧克力点心",
        description: "精选优质巧克力，包裹着新鲜草莓，外层香脆，内里多汁，甜中带酸，每一口都是甜蜜与清新的完美结合。",
        price: 20,
        image: new URL('@/assets/sweet3.jpg', import.meta.url).href,
        quantity: 0,
      }
      // 可以添加更多的商品
    ],
    message: '',
    code:''
  };
},
methods: {
  async updateQuantity(productId, change) {
    try {
      //传递参数
      const response = await addcart(productId * change);
      //alert(response.data.data);
      //在前端显示商品数量
      const product = this.products.find(product => product.id === productId);
      if (product) {
        product.quantity += change;
        if (product.quantity < 0) {
          product.quantity = 0;
        }
      }
      //根据返回值的错误码来判断输出提示信息
      this.code=response.data.code;
      if(this.code===1){
        this.message = response.data.message;
      }
      if(this.code===0) {
        this.message = response.data.data;
      }
      // 清除消息
      setTimeout(() => {
        this.message = '';
      }, 500);

    } catch (error) {
      console.error(`Error updating product ${productId}:`, error.response ? error.response.data : error.message);
    }
  }
}
};
</script>

<style scoped>
#app {
  display: flex;
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

.sidebar nav ul li.active {
  color: #ff0000;
}

.sidebar nav ul li:hover {
  color: #ff0000;
}

.main-content {
flex: 1; /* 保持flex布局的伸缩性 */
width: 100vw; /* 设置容器宽度为100% */
max-width: 1000px; /* 可以设置一个最大宽度，根据需要调整 */
background-color: #fff;
padding: 20px;
display: flex;
justify-content: center;
align-items: center;
overflow: hidden;
}

.header {
  background-color: #ffd700;
  padding: 10px;
  text-align: center;
  font-size: 20px;
  font-weight: bold;
  color: #333;
  margin-bottom: 20px;
}

.products {
  flex: 1;
}

.product-category {
  margin-bottom: 20px;
}

.product-category h2 {
  margin-bottom: 10px;
  font-size: 24px;
  border-bottom: 2px solid #ff0000;
  padding-bottom: 5px;
}

.product-list {
  display: flex;
  flex-wrap: wrap;
}

.product-card {
  width: 200px;
  border: 1px solid #ddd;
  border-radius: 10px;
  margin: 10px;
  padding: 10px;
  text-align: center;
  box-shadow: 0 2px 5px rgba(0,0,0,0.1);
}

.product-card img {
  max-width: 100%;
  border-radius: 10px;
}

.product-info {
  margin-top: 10px;
}

.product-info h3 {
  font-size: 18px;
  margin: 10px 0;
}

.product-info p {
  font-size: 16px;
  margin: 5px 0;
}

.product-info button {
  background-color: #ff0000;
  color: #fff;
  border: none;
  padding: 5px 10px;
  cursor: pointer;
  border-radius: 5px;
}

.product-info button:hover {
  background-color: #cc0000;
}
 .quantity-control {
display: flex;
align-items: center;
justify-content: center;
gap: 10px;
}

.quantity-control button {
background-color: #ff0000;
color: #fff;
border: none;
padding: 5px 10px;
cursor: pointer;
border-radius: 5px;
}

.quantity-control button:hover {
background-color: #cc0000;
}

.quantity-control span {
font-size: 16px;
} 
</style>